package com.hsh.dongzi.jinshang.ui.login.phonelogin;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;

public interface PhoneLoginView extends BaseImpl {

    /**
     * 获取手机号
     *
     * @return
     */
    String getUserPhone();
    /**
     * 获取短信验证码
     *
     * @return
     */
    String getSmscode();

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

    /**
     * 获取注册验证码回调
     */
    void onVCodeRequest();
}
