package com.hsh.dongzi.jinshang.ui.login.forgetpass.newpass;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.base.mvp.BaseActivity;
import com.hsh.dongzi.jinshang.presenter.login.forgetpass.newpass.NewPassPresenter;
import com.hsh.dongzi.jinshang.utils.ToastUtil;
import com.hsh.dongzi.jinshang.weiget.CommTitle;

import butterknife.BindView;
import butterknife.OnClick;

public class NewPassActivity extends BaseActivity<NewPassPresenter> implements NewPassView, TextWatcher {
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
    @BindView(R.id.et_pswd)
    EditText etPswd;
    @BindView(R.id.et_pswd_again)
    EditText etPswdAgain;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    private String token;
    private String userName;
    @Override
    protected int layoutRes() {
        return R.layout.activity_new_pass;
    }

    @Override
    protected void initView() {
        CommTitle title = new CommTitle(this, "设置密码");
        title.setBackOnClickListener(view -> {
            backward();
        });
        etPswd.addTextChangedListener(this);
        etPswdAgain.addTextChangedListener(this);

        token = "";
        token = getIntent().getStringExtra("token");
        userName = "";
        userName = getIntent().getStringExtra("userName");
    }

    @Override
    protected NewPassPresenter createPresenter() {
        return new NewPassPresenter(this);
    }

    @OnClick({R.id.tv_ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ok:
                mPresenter.checkPswd();
                break;
        }

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
    public void toFindPswd() {
        mPresenter.findPswd2(this);
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void onFindPswd2Request() {
        ToastUtil.showToastSafe("设置新密码成功");
        backward();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String passWord = getPswd();
        String passAgain = getPswdAgain();
        if (TextUtils.isEmpty(passWord) || TextUtils.isEmpty(passAgain)){
            tvOk.setClickable(false);
            tvOk.setSelected(false);
        }else {
            tvOk.setClickable(true);
            tvOk.setSelected(true);
        }
    }

}
