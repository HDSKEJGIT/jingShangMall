package com.hsh.dongzi.jinshang.model.login;

import com.hsh.dongzi.jinshang.base.mvp.BaseModel;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.net.HttpFunction;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class PhoneLoginModel extends BaseModel {
    public static PhoneLoginModel getInstance() {
        return getPresent(PhoneLoginModel.class);
    }



    /**
     * 登录
     *
     * @param phone
     * @param phonecode
     * @param observer
     */
    public void loginByPhone(String phone, String phonecode, Observer<BaseResponse> observer) {
        addParams("mobile", phone);
        addParams("mobileCode", phonecode);
        Observable observable = mServletApi.loginByPhone(mParams).map(new HttpFunction());
        toSubscribe(observable, observer);
    }
    /**
     * 注册验证码
     *
     * @param mobile
     * @param observer
     */
    public void getVCode(String mobile, Observer<BaseResponse> observer) {
        addParams("mobile", mobile);
        addParams("type", "verification");
        Observable observable = mServletApi.getVCode(mParams).map(new HttpFunction());
        toSubscribe(observable, observer);
    }

    /**
     * 验证手机号是否存在
     *
     * @param mobile
     * @param observer
     */
    public void checkPhone(String mobile, Observer<BaseResponse> observer) {
        addParams("mobile", mobile);

        Observable observable = mServletApi.checkPhoneIsExist(mParams);
        toSubscribe(observable, observer);
    }
}
