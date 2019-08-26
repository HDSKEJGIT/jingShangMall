package com.hsh.dongzi.jinshang.ui.login.register;

import android.content.pm.ActivityInfo;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.base.mvp.BaseActivity;
import com.hsh.dongzi.jinshang.base.mvp.BasePresenter;
import com.hsh.dongzi.jinshang.enums.OtherEnums;
import com.hsh.dongzi.jinshang.weiget.CommTitle;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import cn.bingoogolapple.swipebacklayout.BGAKeyboardUtil;

public class RegisterAgreementActivity extends BaseActivity {
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
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.scroll)
    ScrollView scroll;

    @Override
    protected int layoutRes() {
        return R.layout.activity_register_agreement;
    }

    @Override
    protected void initView(){
        CommTitle title = new CommTitle(this, "紧商注册协议" );
        title.setBackOnClickListener(view -> {
            BGAKeyboardUtil.closeKeyboard(RegisterAgreementActivity.this);
            finish();
            RegisterAgreementActivity.this.overridePendingTransition(R.anim.bga_sbl_activity_backward_enter, R.anim.bga_sbl_activity_backward_exit);
        });
        tvContent.setText(OtherEnums.RegisterProxy);
        scroll.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
