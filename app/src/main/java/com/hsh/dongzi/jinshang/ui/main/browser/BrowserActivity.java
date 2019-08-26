package com.hsh.dongzi.jinshang.ui.main.browser;

import com.hsh.dongzi.jinshang.base.mvp.BaseActivity;
import com.hsh.dongzi.jinshang.presenter.main.browser.BrowserPresenter;

public class BrowserActivity extends BaseActivity<BrowserPresenter> implements BrowserView {
    @Override
    protected int layoutRes() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected BrowserPresenter createPresenter() {
        return null;
    }
}
