package com.hsh.dongzi.jinshang.model.login;

import com.hsh.dongzi.jinshang.MyApplication;
import com.hsh.dongzi.jinshang.base.mvp.BaseModel;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.enums.SPEnums;
import com.hsh.dongzi.jinshang.net.HttpFunction;
import com.hsh.dongzi.jinshang.utils.Base64Utils;
import com.hsh.dongzi.jinshang.utils.SPUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class LoginModel extends BaseModel {
    public static LoginModel getInstance() {
        return getPresent(LoginModel.class);
    }

    /**
     * 登录
     *
     * @param phone
     * @param password
     * @param observer
     */
    public void login(String phone, String password, Observer<BaseResponse> observer) {
        addParams("username", phone);
        addParams("password", Base64Utils.setBase64(password));
        Observable observable = mServletApi.login(mParams).map(new HttpFunction());
        toSubscribe(observable, observer);
    }

    /**
     * 退出登录
     *
     * @param observer
     */
    public void loginOut(Observer<BaseResponse> observer) {
        Observable observable = mServletApi.loginOut().map(new HttpFunction());
        toSubscribe(observable, observer);
    }

    /**
     * 保存cookieSession
     *
     * @param cookie
     */
    public void saveCookieSession(String cookie) {
        SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).putObject(SPEnums.SESSION, cookie);
    }

    /**
     * 获取cookieSession
     *
     * @return
     */
    public String getCookieSession() {
        return SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).getStringValue(SPEnums.SESSION);
    }

    /**
     * 保存cookieToken
     *
     * @param cookie
     */
    public void saveCookieToken(String cookie) {
        SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).putObject(SPEnums.TOKEN, cookie);
    }

    /**
     * 获取cookieToken
     *
     * @return
     */
    public String getCookieToken() {
        return SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).getStringValue(SPEnums.TOKEN);
    }

    /**
     * 清除cookieToken
     */
    public void clearCookie() {
        SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).putObject(SPEnums.SESSION, "");
        SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).putObject(SPEnums.TOKEN, "");
    }
}
