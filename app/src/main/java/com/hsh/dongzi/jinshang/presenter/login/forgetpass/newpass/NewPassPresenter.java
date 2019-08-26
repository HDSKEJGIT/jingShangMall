package com.hsh.dongzi.jinshang.presenter.login.forgetpass.newpass;


import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;
import com.hsh.dongzi.jinshang.base.mvp.BasePresenter;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.base.net.RequestFailObserver;
import com.hsh.dongzi.jinshang.model.login.ForgetModel;
import com.hsh.dongzi.jinshang.model.login.NewPassModel;
import com.hsh.dongzi.jinshang.ui.login.forgetpass.newpass.NewPassView;

public class NewPassPresenter extends BasePresenter<NewPassView> {
    public NewPassPresenter(NewPassView newPassView) {
        super(newPassView);
    }


    /**
     * 检查密码
     */
    public void checkPswd() {
        String pswd = getView().getPswd();
        String pswdAgain = getView().getPswdAgain();
        if (pswd.length() < 6 || pswd.length() > 20) {
            getView().showErrorToast("请输入6~20位的密码");
            return;
        }
        if (!pswd.equals(pswdAgain)) {
            getView().showErrorToast("两次密码不一致");
            return;
        }
        getView().toFindPswd();
    }


    /**
     * 忘记密码-修改
     *
     * @param baseImpl
     */
    public void findPswd2(final BaseImpl baseImpl) {
        NewPassModel.getInstance().findPswd2(getView().getUserName(), getView().getPswd(), getView().getToken()
                , new RequestFailObserver<BaseResponse>(baseImpl, "找回密码中...") {
                    @Override
                    protected void onBaseNext(BaseResponse data) {
                        getView().onFindPswd2Request();
                    }
                });
    }

}
