package com.hsh.dongzi.myrefreshdemo.permission;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;


import com.hsh.dongzi.myrefreshdemo.utils.AppUtils;
import com.hsh.dongzi.myrefreshdemo.utils.DeviceUtils;
import com.hsh.dongzi.myrefreshdemo.utils.ThreadUtils;
import com.hsh.dongzi.myrefreshdemo.utils.VivoPermissionUtil;

import java.util.ArrayList;

/**
 * 未做多线程并发处理，请确保主线程单独调用
 * 同时申请的权限数量不得超过无符号16位数字最大值，但最后两个数字为保留值，即不得超过65533。如果超过了，你特么想什么呢？？？？
 */
public class Permission {
    private PermissionCallback _permissionCallback;
    private ArrayList<PermissionInfo> requestingPermissionInfos = new ArrayList<>();
    private ArrayList<PermissionInfo> deniedPermissionInfos = new ArrayList<>();

    private static class Holder {
        private static Permission instance = new Permission();
    }

    private Permission() {
    }

    public static Permission getInstance() {
        return Holder.instance;
    }

    public Permission setCallback(PermissionCallback callback) {
        this._permissionCallback = callback;
        return this;
    }

    public boolean isGranted(@NonNull String permission) {
        return ContextCompat.checkSelfPermission(AppUtils.getCurrentActivity() != null ? AppUtils.getCurrentActivity() : AppUtils.getContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isDenied(@NonNull String permission) {
        return ContextCompat.checkSelfPermission(AppUtils.getCurrentActivity() != null ? AppUtils.getCurrentActivity() : AppUtils.getContext(), permission) == PackageManager.PERMISSION_DENIED;
    }

    public String[] isGranted(String... permissions) {
        ArrayList<String> grantedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (isGranted(permission)) {
                grantedPermissions.add(permission);
            }
        }
        return grantedPermissions.toArray(new String[0]);
    }

    public String[] isDenied(String... permissions) {
        ArrayList<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (isDenied(permission)) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions.toArray(new String[0]);
    }

    /**
     * 在deniedPermissionInfos中的可能被granted 也可能仍是denied
     * 而已经granted的也可能在系统设置中被用户denied
     * 所以在检查的时候，需要从requestingPermissionInfos中查
     */
    public void processActivityResult() {
        ArrayList<PermissionInfo> deniedAgain = new ArrayList<>();
        ArrayList<PermissionInfo> granted = new ArrayList<>();
        for (PermissionInfo p : this.requestingPermissionInfos) {
            if (isDenied(p.getPermission())) {
                deniedAgain.add(p);
            } else {
                granted.add(p);
            }
        }
        if (_permissionCallback != null) {
            _permissionCallback.resultsFromSystemSettingOfApp(granted.toArray(new PermissionInfo[0]), deniedAgain.toArray(new PermissionInfo[0]));
        }
    }

    public void processRequestPermissionResult(int requestCode, @NonNull int[] grantResults) {
        if (requestCode == PermissionRequestCode.REQUEST_CODE) {
            ArrayList<PermissionInfo> grantedPermissions = new ArrayList<>();
            for (int i = 0; i < grantResults.length; i++) {
                PermissionInfo permissionInfo = this.requestingPermissionInfos.get(i);
                int res = grantResults[i];
                if (res == PackageManager.PERMISSION_GRANTED) {
                    grantedPermissions.add(permissionInfo);
                } else {
                    if (permissionInfo.isRequired()) {
                        this.deniedPermissionInfos.add(permissionInfo);
                    }
                }
            }

            PermissionInfo[] permissionInfos = deniedPermissionInfos.toArray(new PermissionInfo[0]);

            if (_permissionCallback != null) {
                _permissionCallback.requestResult(grantedPermissions.toArray(new PermissionInfo[0]), permissionInfos);

                if (_permissionCallback.shouldRequestAgain() && deniedPermissionInfos.size() > 0)
                    this.showRationalDialog(permissionInfos);
            }
        }
    }

    public boolean requestPermission(PermissionInfo permissionInfo) {
        return this.requestPermissions(permissionInfo);
    }

    /**
     * 该方法请求权限时，默认均为必选授权
     *
     * @return boolean
     */
    public boolean requestPermissions(String... permissions) {
        if (Build.VERSION.SDK_INT >= 23) {
            ArrayList<PermissionInfo> ps = new ArrayList<>();
            for (String p : permissions) {
                PermissionInfo permissionInfo = new PermissionInfo();
                permissionInfo.setPermission(p);
                ps.add(permissionInfo);
            }
            boolean isHavePermissions = this.requestPermissions(ps.toArray(new PermissionInfo[0]));
            //判断是否是vivo手机
            if (DeviceUtils.isVIVO()) {
                if (VivoPermissionUtil.getInstance().requestPermission(permissions)) {
                    return true;
                } else {
                    return false;
                }
            }else {
                return isHavePermissions;
            }
        }
        return true;
    }

    public boolean requestPermissions(PermissionInfo... permissionInfos) {
        this.resetQueues();

        if (Build.VERSION.SDK_INT >= 23 && AppUtils.getCurrentActivity() != null) {
            ArrayList<String> needRequestPermissions = new ArrayList<>();

            for (PermissionInfo p : permissionInfos) {
                //传入的权限添加入一个新的集合
                this.requestingPermissionInfos.add(p);

                if (isDenied(p.getPermission())) {
                    //被拒接的权限
                    boolean bool = AppUtils.getCurrentActivity().shouldShowRequestPermissionRationale(p.getPermission());
                    if (bool) {
                        // 被用户拒绝的权限，如为非必须授权权限，将不再要求授权
                        if (p.isRequired()) {
                            this.deniedPermissionInfos.add(p);
                        }
                    } else {
                        needRequestPermissions.add(p.getPermission());
                    }
                } else if (!isGranted(p.getPermission())) {
                    needRequestPermissions.add(p.getPermission());
                }
            }

            if (needRequestPermissions.size() > 0) {
                // 有需要申请的权限
                ActivityCompat.requestPermissions(AppUtils.getCurrentActivity(), needRequestPermissions.toArray(new String[0]), PermissionRequestCode.REQUEST_CODE);
                return false;
            } else if (this.deniedPermissionInfos.size() > 0) {
                // 有被拒绝的权限 发起再次授权申请
                this.showRationalDialog(this.deniedPermissionInfos.toArray(new PermissionInfo[0]));
                return false;
            }
        }
        return true;
    }

    public void goAppSetting() {
        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", DeviceUtils.getPackName(), null);
        intent.setData(uri);

        AppUtils.getCurrentActivity().startActivityForResult(intent, PermissionRequestCode.REQUEST_SETTING);
    }

    /**
     * 对于提示信息暂时做指定内容显示，后续改为特定UI，并显示每条需要申请的权限信息，或开放自定义UI
     *
     * @param pis
     */
    private void showRationalDialog(final PermissionInfo... pis) {
        if (!ThreadUtils.isMainThread()) {
            return;
        }
        String title = "无法访问部分系统权限";
        if (pis.length == 1) {
            String permissionName = pis[0].getName();
            if (permissionName != null) {
                title = "无法访问\"" + permissionName + "\"";
            } else {
                title = "无法访问系统权限";
            }
        }
        String message = "为了不影响您的正常使用，请进入系统设置打开相应权限。";
        new AlertDialog.Builder(AppUtils.getCurrentActivity() != null ? AppUtils.getCurrentActivity() : AppUtils.getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        goAppSetting();
                        if (_permissionCallback != null) {
                            _permissionCallback.jumpedToSetting(deniedPermissionInfos.toArray(new PermissionInfo[0]));
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (_permissionCallback != null) {
                            _permissionCallback.negativedToSetting(deniedPermissionInfos.toArray(new PermissionInfo[0]));
                        }
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void resetQueues() {
        if (!this.requestingPermissionInfos.isEmpty())
            this.requestingPermissionInfos.clear();
        if (!this.deniedPermissionInfos.isEmpty())
            this.deniedPermissionInfos.clear();
    }
}
