package com.hsh.dongzi.jinshang.presenter.login.register;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;
import com.hsh.dongzi.jinshang.base.mvp.BasePresenter;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.base.net.RequestFailObserver;
import com.hsh.dongzi.jinshang.model.login.RegisterModel;
import com.hsh.dongzi.jinshang.ui.login.register.RegisterView;
import com.hsh.dongzi.jinshang.utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPresenter extends BasePresenter<RegisterView> {
    public RegisterPresenter(RegisterView registerView) {
        super(registerView);
    }

    /**
     * 获取手机验证码
     *
     * @param baseImpl
     */
    public void getVCode(final BaseImpl baseImpl) {
        RegisterModel.getInstance().getVCode(getView().getPhone()
                , new RequestFailObserver<BaseResponse>(baseImpl, "验证码发送中...") {
                    @Override
                    protected void onBaseNext(BaseResponse data) {
                        getView().onVCodeRequest();
                    }
                });
    }

    /**
     * 检查手机号
     *
     * @return
     */
    public boolean checkPhone() {
        String phone = getView().getPhone();
        String regex = StringUtils.REGEX_MOBILE;
        if (phone.length() != 11) {
            getView().showErrorToast("请输入11位手机号");
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        if (!isMatch) {
            getView().showErrorToast("请输入有效的手机号");
            return false;
        }
        return true;
    }

    /**
     * 检查注册输入
     */
    public void checkInput() {
        String username = getView().getUsername();
        String pswd = getView().getPswd();
        String pswdAgain = getView().getPswdAgain();
        if (username.length() < 6 || username.length() > 20) {
            getView().showErrorToast("请输入6~20位的用户名");
            return;
        }
        if (pswd.length() < 6 || pswd.length() > 20) {
            getView().showErrorToast("请输入6~20位的密码");
            return;
        }
        if (!pswd.equals(pswdAgain)) {
            getView().showErrorToast("两次密码不一致");
            return;
        }
        if (!checkPhone()) {
            return;
        }
        getView().toRegister();
    }

    /**
     * 注册
     *
     * @param baseImpl
     */
    public void register(final BaseImpl baseImpl) {
        RegisterModel.getInstance().register(getView().getUsername(), getView().getRealname(), getView().getPswd(), getView().getPhone(), getView().getVCode(), getView().getICode()
                , new RequestFailObserver<BaseResponse>(baseImpl, "注册中") {
                    @Override
                    protected void onBaseNext(BaseResponse data) {
                        getView().onRegisterRequest();
                    }
                });
    }

}
