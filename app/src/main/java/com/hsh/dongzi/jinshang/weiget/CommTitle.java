package com.hsh.dongzi.jinshang.weiget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.base.mvp.BaseActivity;

/**
 * Created by lijiacheng on 2017/12/8.
 */

public class CommTitle {
    ImageView ivBack;
    TextView tvTitle;
    TextView tvTxt;
    TextView tvRight;
    ImageView ivRight;

    protected BaseActivity mActivity;

    public CommTitle() {
    }

    public CommTitle(Context context, String title) {
        this(context, title, "", -1);
    }

    public CommTitle(Context context, String title, String right) {
        this(context, title, right, -1);
    }

    public CommTitle(Context context, String title, int img) {
        this(context, title, "", img);
    }

    public CommTitle(Context context, String title, String right, int img) {
        mActivity = (BaseActivity) context;
        initView();
        tvTitle.setText(title);
        if (!TextUtils.isEmpty(right)) {
            setTRight(right);
        }
        if (img != -1) {
            setIRight(img);
        }
    }

    private void initView() {
        ivBack = (ImageView) mActivity.findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
        tvTitle = (TextView) mActivity.findViewById(R.id.tv_title);
        tvTxt = (TextView) mActivity.findViewById(R.id.tv_txt);
        tvRight = (TextView) mActivity.findViewById(R.id.tv_right);
        ivRight = (ImageView) mActivity.findViewById(R.id.iv_right);
    }

    public void setTitle(int titleRes) {
        setTitle(mActivity.getResources().getString(titleRes));
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTxt(int txtRes) {
        setTxt(mActivity.getResources().getString(txtRes));
    }

    public void setTxt(String txt) {
        tvTxt.setText(txt);
    }

    public void setTxtVisible(int Visible) {
        tvTxt.setVisibility(Visible);
    }

    public void setTRight(int tRightRes) {
        setTRight(mActivity.getResources().getString(tRightRes));
    }

    public void setTRightColor(int color) {
        tvRight.setTextColor(color);
    }

    public void setTRight(String tRight) {
        tvRight.setText(tRight);
        tvRight.setVisibility(View.VISIBLE);
        ivRight.setVisibility(View.GONE);
    }

    public void setIRight(int imgRes) {
        ivRight.setImageResource(imgRes);
        ivRight.setVisibility(View.VISIBLE);
        tvRight.setVisibility(View.GONE);
    }

    public void setTRVisibility(int visibility) {
        tvRight.setVisibility(visibility);
    }

    public void setIRVisibility(int visibility) {
        ivRight.setVisibility(visibility);
    }

    public void setBackOnClickListener(View.OnClickListener listener) {
        ivBack.setOnClickListener(listener);
    }

    public void setTitleOnClickListener(View.OnClickListener listener) {
        tvTitle.setOnClickListener(listener);
    }

    public void setTitleOnClickListener(String title, View.OnClickListener listener) {
        setTitle(title);
        tvTitle.setOnClickListener(listener);
    }

    public void setTxtOnClickListener(View.OnClickListener listener) {
        tvTxt.setOnClickListener(listener);
    }

    public void setTxtOnClickListener(String title, View.OnClickListener listener) {
        setTxt(title);
        tvTxt.setOnClickListener(listener);
    }

    public void setTRightOnClickListener(View.OnClickListener listener) {
        tvRight.setOnClickListener(listener);
    }

    public void setTRightOnClickListener(String tRight, View.OnClickListener listener) {
        setTRight(tRight);
        tvRight.setOnClickListener(listener);
    }

    public void setIRightOnClickListener(View.OnClickListener listener) {
        ivRight.setOnClickListener(listener);
    }

    public void setIRightOnClickListener(int imgRes, View.OnClickListener listener) {
        setIRight(imgRes);
        ivRight.setOnClickListener(listener);
    }
}
