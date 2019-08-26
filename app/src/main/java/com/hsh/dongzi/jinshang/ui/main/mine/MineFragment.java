package com.hsh.dongzi.jinshang.ui.main.mine;

import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.base.mvp.BaseFragment;
import com.hsh.dongzi.jinshang.presenter.main.mine.MinePresenter;

public class MineFragment extends BaseFragment<MinePresenter> implements MineView{
    @Override
    protected int layoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected MinePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }
}
