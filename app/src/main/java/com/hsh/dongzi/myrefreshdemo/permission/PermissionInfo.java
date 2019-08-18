package com.hsh.dongzi.myrefreshdemo.permission;

import android.support.annotation.NonNull;

public class PermissionInfo {
    private String _permission;
    private String _name;
    private String _message;
    // 是否为必开权限，如果是，则当用户点击不再提示，是否强制弹框申请
    private boolean _isRequired = true;

    public String getPermission() {
        return _permission;
    }

    public void setPermission(@NonNull String permission) {
        _permission = permission;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getMessage() {
        return _message;
    }

    public void setMessage(String message) {
        _message = message;
    }

    public boolean isRequired() {
        return _isRequired;
    }

    public void setRequired(boolean required) {
        _isRequired = required;
    }
}
