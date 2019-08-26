package com.hsh.dongzi.jinshang.base.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hsh.dongzi.jinshang.enums.BroadcastEnums;
import com.hsh.dongzi.jinshang.model.login.UserBean;
import com.hsh.dongzi.jinshang.model.main.MainModel;
import com.hsh.dongzi.jinshang.utils.LogUtil;
import com.hsh.dongzi.jinshang.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lijiacheng on 2017/12/7.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseImpl {
    private CompositeDisposable mCompositeDisposable;

    protected P mPresenter;

    public BaseActivity mActivity;
    private Unbinder unbinder;

    protected boolean isCreate = false;
    protected boolean isVisiable = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mCompositeDisposable) {
            mCompositeDisposable = new CompositeDisposable();
        }
        if (null == mPresenter) {
            mPresenter = createPresenter();
        }
        beforView();
        View view = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        isCreate=true;
        lazyLoad();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected void beforView() {
    }

    protected abstract int layoutRes();

    protected abstract P createPresenter();

    protected abstract void initView();

    public P getmPresenter() {
        return mPresenter;
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (BaseActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mCompositeDisposable) {
            mCompositeDisposable.clear();
        }
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean checkUnLogin() {
        UserBean user = MainModel.getInstance().getUserInfo();
        if (user == null || !user.isLogined()) {
            mActivity.sendBroadcast(new Intent(BroadcastEnums.LOGIN));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addDisposable(Disposable disposable) {
        if (null != mCompositeDisposable) {
            mCompositeDisposable.add(disposable);
        }
        return true;
    }

    @Override
    public void loginError() {
        LogUtil.e("逻辑异常错误");
    }

    @Override
    public void showErrorToast(String errMsg) {
        ToastUtil.showToastSafe(errMsg);
    }

    @Override
    public void showEmptyListUI() {

    }

    @Override
    public void showErrorNetUI() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            isVisiable=true;
            lazyLoad();
        }else{
            isVisiable=false;
        }
    }

    protected void lazyLoad(){

    }
}
