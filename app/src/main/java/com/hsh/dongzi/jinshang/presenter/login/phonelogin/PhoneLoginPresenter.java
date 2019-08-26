package com.hsh.dongzi.jinshang.presenter.login.phonelogin;

import android.text.TextUtils;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;
import com.hsh.dongzi.jinshang.base.mvp.BasePresenter;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.base.net.RequestFailObserver;
import com.hsh.dongzi.jinshang.model.login.PhoneLoginModel;
import com.hsh.dongzi.jinshang.ui.login.phonelogin.PhoneLoginView;
import com.hsh.dongzi.jinshang.utils.LogUtil;
import com.hsh.dongzi.jinshang.utils.StringUtils;
import com.hsh.dongzi.jinshang.utils.ToastUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneLoginPresenter extends BasePresenter<PhoneLoginView> {
    public PhoneLoginPresenter(PhoneLoginView phoneLoginView) {
        super(phoneLoginView);
    }

    /**
     * 检查手机号
     *
     * @return
     */
    public boolean checkPhone() {
        String phone = getView().getUserPhone();
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
     * 验证手机号
     *
     * @param baseImpl
     */
    public void checkPhone(final BaseImpl baseImpl) {
        PhoneLoginModel.getInstance().checkPhone(getView().getUserPhone()
                , new RequestFailObserver<BaseResponse>(baseImpl, "验证码发送中") {
                    @Override
                    protected void onBaseNext(BaseResponse data) {

                        LogUtil.i(data.getMessage()+"=="+data.getResult());
                        if (data.getResult() == 0){
                            getVCode(baseImpl);

                        }else ToastUtil.showToastSafe("手机号不存在");

                    }
                });
    }

    /**
     * 获取手机验证码
     *
     * @param baseImpl
     */
    public void getVCode(final BaseImpl baseImpl) {
        PhoneLoginModel.getInstance().getVCode(getView().getUserPhone()
                , new RequestFailObserver<BaseResponse>(baseImpl) {
                    @Override
                    protected void onBaseNext(BaseResponse data) {
                        getView().onVCodeRequest();
                    }
                });
    }

    /**
     * 检查数据
     *
     */
    public boolean checkData(){
        String code = getView().getSmscode().trim();
        if (!checkPhone())
            return false;
        if (TextUtils.isEmpty(code)){
            getView().showErrorToast("请输入验证码");
            return false;
        }
        return true;
    }


    /**
     * 登录
     *
     * @param baseImpl
     */
    public void loginByPhone(final BaseImpl baseImpl) {
        PhoneLoginModel.getInstance().loginByPhone(getView().getUserPhone(), getView().getSmscode(), new RequestFailObserver<BaseResponse>(baseImpl, "正在登录") {
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
