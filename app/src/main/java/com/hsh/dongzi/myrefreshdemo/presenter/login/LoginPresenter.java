package com.hsh.dongzi.myrefreshdemo.presenter.login;

import com.hsh.dongzi.myrefreshdemo.base.mvp.BaseImpl;
import com.hsh.dongzi.myrefreshdemo.base.mvp.BasePresenter;
import com.hsh.dongzi.myrefreshdemo.base.net.BaseResponse;
import com.hsh.dongzi.myrefreshdemo.base.net.RequestFailObserver;
import com.hsh.dongzi.myrefreshdemo.model.login.LoginModel;
import com.hsh.dongzi.myrefreshdemo.ui.login.LoginView;

public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView loginView) {
        super(loginView);
    }

    /**
     * 登录
     *
     * @param baseImpl
     */
    public void login(final BaseImpl baseImpl) {
        LoginModel.getInstance().login(getView().getUserName(), getView().getUserPassWord(), new RequestFailObserver<BaseResponse>(baseImpl, "正在登录") {
            @Override
            protected void onBaseNext(BaseResponse data) {
                getView().onLoginRequest();
            }

            @Override
            protected void onBaseError(Throwable t) {
                getView().onLoginFailed(t);
            }
        });
    }
}
