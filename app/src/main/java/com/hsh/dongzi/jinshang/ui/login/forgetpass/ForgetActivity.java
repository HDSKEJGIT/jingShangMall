package com.hsh.dongzi.jinshang.ui.login.forgetpass;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.base.mvp.BaseActivity;
import com.hsh.dongzi.jinshang.presenter.login.forgetpass.ForgetPresenter;
import com.hsh.dongzi.jinshang.ui.login.forgetpass.newpass.NewPassActivity;
import com.hsh.dongzi.jinshang.utils.LogUtil;
import com.hsh.dongzi.jinshang.utils.ToastUtil;
import com.hsh.dongzi.jinshang.weiget.CommTitle;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetActivity extends BaseActivity<ForgetPresenter> implements ForgetView, TextWatcher {

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
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_v_code)
    EditText etVCode;
    @BindView(R.id.tv_v_code)
    TextView tvVCode;
    @BindView(R.id.tv_next)
    TextView tvNext;

    private CountDownTimer timer;
    @Override
    protected int layoutRes() {
        return R.layout.activity_forget;
    }

    @Override
    protected void initView() {
        CommTitle title = new CommTitle(this, "找回密码");
        title.setBackOnClickListener(view -> {
            backward();
        });
        etUsername.addTextChangedListener(this);
        etPhone.addTextChangedListener(this);
        etVCode .addTextChangedListener(this);
    }

    @Override
    protected ForgetPresenter createPresenter() {
        return new ForgetPresenter(this);
    }

    @OnClick({R.id.tv_next, R.id.tv_v_code})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_next:
                if (v.isSelected())
                mPresenter.findPswd1(this);
                break;
            case R.id.tv_v_code:
                mPresenter.checkPhone();
                break;
        }
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString().trim();
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
    public void toGetVCode() {
        mPresenter.getVCode(this);
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
    public void onFindPswd1Request(String token) {
        Intent intent = new Intent(this,NewPassActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("userName",getUsername());
        forwardAndFinish(intent);
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
        String userName = getUsername();
        String phone = getPhone();
        String code = getVCode();

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
            tvNext.setClickable(false);
            tvNext.setSelected(false);
        } else {
            tvNext.setClickable(true);
            tvNext.setSelected(true);
        }
    }


}
