package com.hsh.dongzi.jinshang.ui.welcome;

import android.Manifest;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;

public interface WelcomeView extends BaseImpl {
    String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    void toSplash();

    void toMain();

    void requestPermissions(String[] permissions);
}
