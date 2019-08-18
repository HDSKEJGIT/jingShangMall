package com.hsh.dongzi.myrefreshdemo.base.mvp;

import android.content.Context;

import io.reactivex.disposables.Disposable;

public interface BaseImpl {
    int PERMISSION_REQUEST_CODE = 111;

    boolean addDisposable(Disposable disposable);

    Context getContext();

    void  loginError();

    void  showErrorToast(String errMsg);

    void  showEmptyListUi();

    void showErrorNetUi();

    boolean checkUnLogin();

}
