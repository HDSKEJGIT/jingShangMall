package com.hsh.dongzi.jinshang.ui.login.forgetpass.newpass;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;

public interface NewPassView extends BaseImpl {

    String getPswd();

    String getPswdAgain();

    /**
     * 去找回密码
     */
    void toFindPswd();


    String getToken();

    String getUserName();


    void onFindPswd2Request();
}
