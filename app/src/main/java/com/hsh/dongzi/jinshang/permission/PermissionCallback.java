package com.hsh.dongzi.jinshang.permission;

public interface PermissionCallback {
    // 跳到系统的APP设置界面
    default void jumpedToSetting(PermissionInfo... permissionInfos){}
    // 拒绝跳到系统APP设置界面
    default void negativedToSetting(PermissionInfo... permissionInfos){}
    // 从系统APP设置界面返回
    default void resultsFromSystemSettingOfApp(PermissionInfo[] grantedPermissionInfos, PermissionInfo[] deniedPermissionInfos){}
    // 当申请权限被用户拒绝时，是否直接弹框要求再次授权
    default boolean shouldRequestAgain(){ return false; }

    // 请求结果
    void requestResult(PermissionInfo[] permissionInfos, PermissionInfo[] deniedInfos);
}
