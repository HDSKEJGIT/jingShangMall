package com.hsh.dongzi.jinshang.model.login;

import com.hsh.dongzi.jinshang.base.mvp.BaseModel;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.net.HttpFunction;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class ForgetModel extends BaseModel {
    public static ForgetModel getInstance() {
        return getPresent(ForgetModel.class);
    }

    /**
     * 忘记密码验证码
     *
     * @param mobile
     * @param observer
     */
    public void getVCode(String mobile, Observer<BaseResponse> observer) {
        addParams("mobile", mobile);
        addParams("type", "findpwd");
        Observable observable = mServletApi.getVCode(mParams).map(new HttpFunction());
        toSubscribe(observable, observer);
    }



    /**
     * 找回密码1
     *
     * @param username
     * @param mobile
     * @param mobileCode
     * @param observer
     */
    public void findPswd1(String username, String mobile, String mobileCode, Observer<BaseResponse> observer) {
        addParams("username", username);
        addParams("mobile", mobile);
        addParams("mobileCode", mobileCode);
        Observable observable = mServletApi.findPswd1(mParams).map(new HttpFunction());
        toSubscribe(observable, observer);
    }

}
