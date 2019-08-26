package com.hsh.dongzi.jinshang.presenter.login;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;
import com.hsh.dongzi.jinshang.base.mvp.BasePresenter;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.base.net.RequestFailObserver;
import com.hsh.dongzi.jinshang.model.login.LoginModel;
import com.hsh.dongzi.jinshang.ui.login.LoginView;

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
