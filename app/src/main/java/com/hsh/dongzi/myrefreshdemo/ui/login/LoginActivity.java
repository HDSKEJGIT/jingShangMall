package com.hsh.dongzi.myrefreshdemo.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsh.dongzi.myrefreshdemo.R;
import com.hsh.dongzi.myrefreshdemo.base.mvp.BaseActivity;
import com.hsh.dongzi.myrefreshdemo.presenter.login.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView, TextWatcher {
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
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_pswd)
    EditText etPswd;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.ll_to_register)
    LinearLayout llToRegister;
    @BindView(R.id.tv_phone_login)
    TextView tvPhoneLogin;
    @BindView(R.id.ll_wx)
    LinearLayout llWx;
    @BindView(R.id.ll_qq)
    LinearLayout llQq;

    @Override
    protected int layoutRes() {
        return R.layout.activity_login;

    }

    @Override
    protected void initView() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @OnClick({R.id.tv_login,R.id.tv_phone_login, R.id.tv_forget, R.id.ll_to_register, R.id.ll_qq, R.id.ll_wx})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                //mPresenter.login(this);
                break;
            case R.id.tv_forget:
                //startActivity(ForgetActivity.class);
                break;
            case R.id.tv_phone_login:
               // startActivityForResult(LoginPhoneActivity.class, REGISTER_REQUEST_CODE);
                break;
            case R.id.ll_to_register:
                //startActivityForResult(RegisterActivity.class, REGISTER_REQUEST_CODE);
                break;
            case R.id.ll_qq:

                break;
            case R.id.ll_wx:

                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String username = getUserName();
        String pswd = getUserPassWord();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pswd)) {
            tvLogin.setClickable(false);
            tvLogin.setSelected(false);
        } else {
            tvLogin.setClickable(true);
            tvLogin.setSelected(true);
        }
    }

    @Override
    public String getUserName() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getUserPassWord() {
        return etPswd.getText().toString().trim();
    }
}
