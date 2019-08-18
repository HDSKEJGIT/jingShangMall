package com.hsh.dongzi.myrefreshdemo.ui.welcome;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hsh.dongzi.myrefreshdemo.HomeActivity;
import com.hsh.dongzi.myrefreshdemo.MainActivity;
import com.hsh.dongzi.myrefreshdemo.presenter.welcome.WelcomePresenter;
import com.jaeger.library.StatusBarUtil;

import io.reactivex.disposables.Disposable;

public class WelcomeActivity extends AppCompatActivity implements WelcomeView {
    private WelcomePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucent(this,0);
            if (null == mPresenter){
                mPresenter = createPresenter();
            }
        init();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPresenter.checkPermissions();
        } else {
            mPresenter.isFirstShow();
        }
    }

    protected  WelcomePresenter createPresenter(){
        return new WelcomePresenter(this);
    }

    @Override
    public void toSplash() {
        //Toast.makeText(this,"当前应该跳引导页",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void toMain() {
        //Toast.makeText(this,"当前应该跳主页",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    /**
     * 申请权限
     */
    @Override
    public void requestPermissions(String[] permissions) {
        ActivityCompat.requestPermissions(this,
                permissions, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("yes","dasd");
                } else {//申请被取消
                    Log.d("yes","dasd");
                }
                mPresenter.isFirstShow();
                return;
            }
        }
    }


    @Override
    public boolean addDisposable(Disposable disposable) {
        return false;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginError() {

    }

    @Override
    public void showErrorToast(String errMsg) {

    }

    @Override
    public void showEmptyListUi() {

    }

    @Override
    public void showErrorNetUi() {

    }

    @Override
    public boolean checkUnLogin() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter){
            mPresenter.detachView();
        }
    }
}
