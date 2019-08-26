package com.hsh.dongzi.jinshang.ui.login.forgetpass;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;

public interface ForgetView extends BaseImpl {

    String getUsername();

    String getPhone();

    String getVCode();

    /**
     * 获取注册验证码
     */
    void toGetVCode();


    /**
     * 获取注册验证码回调
     */
    void onVCodeRequest();

    /**
     *
     * @param token
     * 完成账号手机号的验证
     * 点击下一步的请求后的回调
     */
    void onFindPswd1Request(String token);
}
