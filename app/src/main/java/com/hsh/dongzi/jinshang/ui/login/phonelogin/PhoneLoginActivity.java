package com.hsh.dongzi.jinshang.ui.login.phonelogin;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.base.mvp.BaseActivity;
import com.hsh.dongzi.jinshang.enums.BroadcastEnums;
import com.hsh.dongzi.jinshang.presenter.login.phonelogin.PhoneLoginPresenter;
import com.hsh.dongzi.jinshang.ui.login.register.RegisterActivity;
import com.hsh.dongzi.jinshang.utils.ToastUtil;
import com.hsh.dongzi.jinshang.weiget.CommTitle;

import butterknife.BindView;
import butterknife.OnClick;

public class PhoneLoginActivity extends BaseActivity<PhoneLoginPresenter> implements PhoneLoginView, TextWatcher {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_txt)
    TextView tvTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_i_code)
    EditText etICode;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.et_v_code)
    EditText etVCode;
    @BindView(R.id.tv_v_code)
    TextView tvVCode;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.ll_to_register)
    LinearLayout llToRegister;
    @BindView(R.id.tv_phone_login)
    TextView tvPhoneLogin;
    private CountDownTimer timer;
    @Override
    protected int layoutRes() {
        return R.layout.activity_phone_login;
    }

    @Override
    protected void initView() {
        new CommTitle(this, "手机号登录").setBackOnClickListener(view -> backward());

        etPhone.addTextChangedListener(this);
        etVCode.addTextChangedListener(this);
    }

    @Override
    protected PhoneLoginPresenter createPresenter() {
        return new PhoneLoginPresenter(this);
    }

    @OnClick({R.id.ll_to_register,R.id.tv_phone_login,R.id.tv_login,R.id.tv_v_code})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_to_register:
                //去注册
                forward(RegisterActivity.class);
                break;
            case R.id.tv_phone_login:
                //密码登录
                backward();
                break;
            case R.id.tv_login:
                //登录按钮
                if (mPresenter.checkData()){
                    mPresenter.loginByPhone(this);
                }
                break;
            case R.id.tv_v_code:
                if (mPresenter.checkPhone()) {
                    mPresenter.checkPhone(this);//验证手机号是否存在
                }
                break;
        }

    }

    @Override
    public String getUserPhone() {
        return etPhone.getText().toString().trim();
    }

    @Override
    public String getSmscode() {
        return etVCode.getText().toString().trim();
    }

    @Override
    public void onLoginRequest() {
        ToastUtil.showToastSafe("登录成功");
        sendBroadcast(new Intent(BroadcastEnums.USERINFO));
        backward();
    }

    @Override
    public void onLoginFailed(Throwable t) {
        ToastUtil.showToastSafe("登录失败：" + t.getMessage());
    }

    @Override
    public void onVCodeRequest() {
        ToastUtil.showToastSafe("验证码已发送");
        tvVCode.setClickable(false);
        tvVCode.setSelected(true);
        tvVCode.setText("重新发送60s");
        startTimer();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String phone = getUserPhone();
        String smscode = getSmscode();
        if (TextUtils.isEmpty(phone)  || TextUtils.isEmpty(smscode)) {
            tvLogin.setClickable(false);
            tvLogin.setSelected(false);
        } else {
            tvLogin.setClickable(true);
            tvLogin.setSelected(true);
        }
    }

    /**
     * 开启广告倒计时
     */
    public void startTimer() {
        if (timer == null) {
            timer = new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    tvVCode.setText("重新发送" + ((millisUntilFinished / 1000)) + "s");
                }

                @Override
                public void onFinish() {
                    tvVCode.setText("获取验证码");
                    tvVCode.setClickable(true);
                    tvVCode.setSelected(false);
                    cancleTimer();
                }

            };
        }
        timer.start();
    }

    /**
     * 取消广告倒计时
     */
    public void cancleTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onDestroy() {
        cancleTimer();
        super.onDestroy();
    }
}
