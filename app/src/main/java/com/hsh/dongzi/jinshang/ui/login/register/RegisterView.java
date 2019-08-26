package com.hsh.dongzi.jinshang.ui.login.register;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;

public interface RegisterView extends BaseImpl {
    String getUsername();

    String getRealname();

    String getPswd();

    String getPswdAgain();

    String getPhone();

    String getVCode();

    String getICode();

    /**
     * 获取注册验证码
     */
    void toGetVCode();

    /**
     * 去注册
     */
    void toRegister();

    /**
     * 获取注册验证码回调
     */
    void onVCodeRequest();

    /**
     * 注册回调
     */
    void onRegisterRequest();
}
