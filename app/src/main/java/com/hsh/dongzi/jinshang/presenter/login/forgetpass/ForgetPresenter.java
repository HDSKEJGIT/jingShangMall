package com.hsh.dongzi.jinshang.presenter.login.forgetpass;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;
import com.hsh.dongzi.jinshang.base.mvp.BasePresenter;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.base.net.RequestFailObserver;
import com.hsh.dongzi.jinshang.model.login.ForgetModel;
import com.hsh.dongzi.jinshang.ui.login.forgetpass.ForgetView;
import com.hsh.dongzi.jinshang.utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgetPresenter extends BasePresenter<ForgetView> {
    public ForgetPresenter(ForgetView forgetView) {
        super(forgetView);
    }

    /**
     * 检查手机号
     */
    public void checkPhone() {
        String phone = getView().getPhone();
        String regex = StringUtils.REGEX_MOBILE;
        if (phone.length() != 11) {
            getView().showErrorToast("请输入11位手机号");
            return;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        if (!isMatch) {
            getView().showErrorToast("请输入有效的手机号");
            return;
        }
        getView().toGetVCode();
    }

    /**
     * 获取手机验证码
     *
     * @param baseImpl
     */
    public void getVCode(final BaseImpl baseImpl) {
        ForgetModel.getInstance().getVCode(getView().getPhone()
                , new RequestFailObserver<BaseResponse>(baseImpl, "验证码发送中") {
                    @Override
                    protected void onBaseNext(BaseResponse data) {
                        getView().onVCodeRequest();
                    }
                });
    }


    /**
     * 忘记密码-验证
     *
     * @param baseImpl
     */
    public void findPswd1(final BaseImpl baseImpl) {
        ForgetModel.getInstance().findPswd1(getView().getUsername(), getView().getPhone(), getView().getVCode()
                , new RequestFailObserver<BaseResponse>(baseImpl) {
                    @Override
                    protected void onBaseNext(BaseResponse data) {
                        getView().onFindPswd1Request(data.getToken());
                    }
                });
    }

}
