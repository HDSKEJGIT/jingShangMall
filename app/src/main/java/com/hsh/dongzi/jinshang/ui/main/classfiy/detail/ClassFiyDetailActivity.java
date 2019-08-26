package com.hsh.dongzi.jinshang.ui.main.classfiy.detail;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.base.mvp.BaseActivity;
import com.hsh.dongzi.jinshang.presenter.main.classfiy.detail.ClassFiyDetailPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class ClassFiyDetailActivity extends BaseActivity<ClassFiyDetailPresenter> implements ClassFiyDetailView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.iv_shop_name)
    ImageView ivShopName;
    @BindView(R.id.ll_shop_name)
    LinearLayout llShopName;
    @BindView(R.id.iv_shop_type)
    ImageView ivShopType;
    @BindView(R.id.iv_shop_picture)
    ImageView ivShopPicture;
    @BindView(R.id.tv_shop_standard)
    TextView tvShopStandard;
    @BindView(R.id.tv_shop_describe)
    TextView tvShopDescribe;
    @BindView(R.id.rv_select_spec)
    RecyclerView rvSelectSpec;
    @BindView(R.id.rv_result_list)
    RecyclerView rvResultList;
    @BindView(R.id.cl_shop_type)
    ConstraintLayout clShopType;

    @Override
    protected void beforeView() {
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_class_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected ClassFiyDetailPresenter createPresenter() {
        return new  ClassFiyDetailPresenter(this);
    }

    @OnClick({R.id.ll_shop_name,R.id.cl_shop_type})
    public void onCLick(View v){
        switch (v.getId()){
            case R.id.ll_shop_name:
                break;
            case R.id.cl_shop_type:
                break;
        }

    }

}
