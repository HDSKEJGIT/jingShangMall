package com.hsh.dongzi.jinshang.ui.main.shopping;

import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.base.mvp.BaseFragment;
import com.hsh.dongzi.jinshang.presenter.main.shopping.ShoppingPresenter;

public class ShoppingFragment extends BaseFragment<ShoppingPresenter> implements ShoppingView {
    @Override
    protected int layoutRes() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected ShoppingPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }
}
