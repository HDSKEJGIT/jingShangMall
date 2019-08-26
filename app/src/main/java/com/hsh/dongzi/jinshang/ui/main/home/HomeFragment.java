package com.hsh.dongzi.jinshang.ui.main.home;

import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.base.mvp.BaseFragment;
import com.hsh.dongzi.jinshang.presenter.main.home.HomePresenter;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {
    @Override
    protected int layoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }
}
