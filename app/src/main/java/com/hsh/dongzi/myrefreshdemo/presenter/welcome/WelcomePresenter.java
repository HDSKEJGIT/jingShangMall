package com.hsh.dongzi.myrefreshdemo.presenter.welcome;

import android.app.Dialog;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.hsh.dongzi.myrefreshdemo.base.mvp.BasePresenter;
import com.hsh.dongzi.myrefreshdemo.permission.Permission;
import com.hsh.dongzi.myrefreshdemo.permission.PermissionCallback;
import com.hsh.dongzi.myrefreshdemo.permission.PermissionInfo;
import com.hsh.dongzi.myrefreshdemo.ui.welcome.WelcomeView;

import java.util.ArrayList;
import java.util.List;

public class WelcomePresenter extends BasePresenter<WelcomeView> {
    private boolean isFrist = true;

    public WelcomePresenter(WelcomeView welcomeView) {
        super(welcomeView);
    }

    /**
     * 验证是否第一次打开app
     */
    public void isFirstShow() {
//        if (SplashModel.getInstance().isFirstLoad()) {
//            SplashModel.getInstance().setFirstLoad(false);
            getView().toSplash();
//        } else {
//            getView().toMain();
        }



        /**
         * 检查权限
         */
    public void checkPermissions() {
        List<String> requestPermissionList = new ArrayList<>();
        for (String permission : getView().permissions) {
            if (ContextCompat.checkSelfPermission(getView().getContext(),
                    permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionList.add(permission);
            }
        }
        if (requestPermissionList.isEmpty()) {
            isFirstShow();
        } else {
            getView().requestPermissions(requestPermissionList.toArray(new String[requestPermissionList.size()]));
        }
    }

//    public boolean checkPermissions(){
//
//        Permission.getInstance().setCallback(new PermissionCallback() {
//
//            @Override
//            public boolean shouldRequestAgain() {
//                return true;
//            }
//
//            @Override
//            public void requestResult(PermissionInfo[] permissionInfos, PermissionInfo[] deniedInfos) {
//                if (deniedInfos.length == 0){
//                    isFirstShow();
//                }
//            }
//
//            @Override
//            public void negativedToSetting(PermissionInfo... permissionInfos) {
//                //getView().promptPermission();
//                Log.i("dasdas","Dasdas");
//            }
//
//            @Override
//            public void resultsFromSystemSettingOfApp(PermissionInfo[] grantedPermissionInfos, PermissionInfo[] deniedPermissionInfos) {
//                //getView().promptPermission();
//                Log.i("dasdas","Dasdas");
//            }
//        });
//
//        if (Permission.getInstance().requestPermissions(getView().permissions)){
//            return true;
//        }
//        return false;
//       }
}
