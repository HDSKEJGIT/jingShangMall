package com.hsh.dongzi.jinshang.model.login;

import com.hsh.dongzi.jinshang.base.mvp.BaseModel;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.net.HttpFunction;
import com.hsh.dongzi.jinshang.utils.Base64Utils;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class RegisterModel extends BaseModel {
    public static RegisterModel getInstance() {
        return getPresent(RegisterModel.class);
    }


    /**
     * 注册验证码
     *
     * @param mobile
     * @param observer
     */
    public void getVCode(String mobile, Observer<BaseResponse> observer) {
        addParams("mobile", mobile);
        addParams("type", "register");
        Observable observable = mServletApi.getVCode(mParams).map(new HttpFunction());
        toSubscribe(observable, observer);
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param mobile
     * @param mobileCode
     * @param invitecode
     * @param observer
     */
    public void register(String username, String realname, String password, String mobile, String mobileCode, String invitecode, Observer<BaseResponse> observer) {
        addParams("username", username);
        addParams("realname", realname);
        addParams("password", Base64Utils.setBase64(password));
        addParams("mobile", mobile);
        addParams("mobileCode", mobileCode);
        addParams("invitecode", invitecode);
        Observable observable = mServletApi.register(mParams).map(new HttpFunction());
        toSubscribe(observable, observer);
    }
}
