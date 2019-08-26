package com.hsh.dongzi.jinshang.presenter.main;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.hsh.dongzi.jinshang.base.mvp.BasePresenter;
import com.hsh.dongzi.jinshang.ui.main.MainView;
import com.hsh.dongzi.jinshang.ui.main.classfiy.ClassFiyFragment;
import com.hsh.dongzi.jinshang.ui.main.home.HomeFragment;
import com.hsh.dongzi.jinshang.ui.main.mine.MineFragment;
import com.hsh.dongzi.jinshang.ui.main.shopping.ShoppingFragment;

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView mainView) {
        super(mainView);
    }


    /**
     * 验证广告数据
     */
    public void checkData() {
        if (getView().getAdData() == null) {
            getView().splashAd();
        } else {
            getView().showAd();
        }
    }

    public void checkWebUrl(String url){
        if (!TextUtils.isEmpty(url)) {
            getView().loadWebUrl();
        }
    }



    /**
     * 匹配对应fragment
     *
     * @param fragment
     * @param label
     */
    public void checkFragmentChange(Fragment fragment, int label) {
        if (fragment == null) {
            if (getView().HOMEFRAGMENT_LABEL == label) {
                fragment = new HomeFragment();
            } else if (getView().ClASSFRAGMENT_LABEL == label) {
                fragment = new ClassFiyFragment();
            } else if (getView().SHOPPINGFRAGMENT_LABEL == label) {
                fragment = new ShoppingFragment();
            } else if (getView().MINEFRAGMENT_LABEL == label) {
                fragment = new MineFragment();
            } else {
                getView().loginError();
                return;
            }
            getView().addFragment(fragment);
        } else {
            getView().showFragment(fragment);
        }
    }

    /**
     * 隐藏所有存在的fragment
     *
     * @param fragments
     */
    public void hideFragment(Fragment... fragments) {
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                getView().hideFragment(fragment);
            }
        }
    }
}
