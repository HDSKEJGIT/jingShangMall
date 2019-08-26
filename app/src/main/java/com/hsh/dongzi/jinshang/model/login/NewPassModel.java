package com.hsh.dongzi.jinshang.model.login;

import com.hsh.dongzi.jinshang.base.mvp.BaseModel;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.net.HttpFunction;
import com.hsh.dongzi.jinshang.utils.Base64Utils;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class NewPassModel extends BaseModel {

    public static NewPassModel getInstance() {
        return getPresent(NewPassModel.class);
    }

    /**
     * 找回密码2
     *
     * @param username
     * @param password
     * @param observer
     */
    public void findPswd2(String username, String password, String token, Observer<BaseResponse> observer) {
        addParams("username", username);
        addParams("password", Base64Utils.setBase64(password));
        addParams("token", token);
        Observable observable = mServletApi.findPswd2(mParams).map(new HttpFunction());
        toSubscribe(observable, observer);
    }
}
