package com.hsh.dongzi.myrefreshdemo.ui.login;

import com.hsh.dongzi.myrefreshdemo.base.mvp.BaseImpl;

public interface LoginView extends BaseImpl {

    String getUserName();

    String getUserPassWord();

    /**
     * 登录成功
     */
    void onLoginRequest();

    /**
     * 登录失败
     *
     * @param t
     */
    void onLoginFailed(Throwable t);
}
