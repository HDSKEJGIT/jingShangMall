package com.hsh.dongzi.jinshang.ui.login.register;

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
import com.hsh.dongzi.jinshang.presenter.login.register.RegisterPresenter;
import com.hsh.dongzi.jinshang.utils.ToastUtil;
import com.hsh.dongzi.jinshang.weiget.CommTitle;
import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView, TextWatcher {
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
    @BindView(R.id.et_realname)
    EditText etRealname;
    @BindView(R.id.et_pswd)
    EditText etPswd;
    @BindView(R.id.et_pswd_again)
    EditText etPswdAgain;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_v_code)
    EditText etVCode;
    @BindView(R.id.tv_v_code)
    TextView tvVCode;
    @BindView(R.id.et_i_code)
    EditText etICode;
    @BindView(R.id.ll_agreement)
    LinearLayout llAgreement;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    private CountDownTimer timer;
    @Override
    protected int layoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        CommTitle title = new CommTitle(this, "注册", "登录");
        title.setTRightColor(getResources().getColor(R.color.Cl_e8000e));
        title.setTRightOnClickListener(view ->backward());
        title.setBackOnClickListener(view -> backward());
        etUsername.addTextChangedListener(this);
        etPswd.addTextChangedListener(this);
        etPswdAgain.addTextChangedListener(this);
        etPhone.addTextChangedListener(this);
        etVCode.addTextChangedListener(this);
    }

    @OnClick({R.id.tv_v_code, R.id.ll_agreement, R.id.tv_register})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_v_code:
                if (mPresenter.checkPhone()) {
                    toGetVCode();
                }
                break;
            case R.id.ll_agreement:
                forward(RegisterAgreementActivity.class);
                break;
            case R.id.tv_register:
                mPresenter.checkInput();
                break;
        }
    }


    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getRealname() {
        return etRealname.getText().toString().trim();
    }

    @Override
    public String getPswd() {
        return etPswd.getText().toString().trim();
    }

    @Override
    public String getPswdAgain() {
        return etPswdAgain.getText().toString().trim();
    }

    @Override
    public String getPhone() {
        return etPhone.getText().toString().trim();
    }

    @Override
    public String getVCode() {
        return etVCode.getText().toString().trim();
    }

    @Override
    public String getICode() {
        return etICode.getText().toString().trim();
    }

    @Override
    public void toGetVCode() {
        mPresenter.getVCode(this);
    }

    @Override
    public void toRegister() {
        mPresenter.register(this);
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
    public void onRegisterRequest() {
        ToastUtil.showToastSafe("注册成功");
        backwardDelay();
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

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String username = getUsername();
        String realname = getRealname();
        String pswd = getPswd();
        String pswdAgain = getPswdAgain();
        String phone = getPhone();
        String vCode = getVCode();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(realname) || TextUtils.isEmpty(pswd) || TextUtils.isEmpty(pswdAgain)
                || TextUtils.isEmpty(phone) || TextUtils.isEmpty(vCode)) {
            tvRegister.setClickable(false);
            tvRegister.setSelected(false);
        } else {
            tvRegister.setClickable(true);
            tvRegister.setSelected(true);
        }
    }
}
