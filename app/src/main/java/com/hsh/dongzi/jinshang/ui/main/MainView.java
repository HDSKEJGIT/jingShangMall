package com.hsh.dongzi.jinshang.ui.main;

import android.support.v4.app.Fragment;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;
import com.hsh.dongzi.jinshang.model.main.home.BannerBean;

public interface MainView extends BaseImpl {
    int HOMEFRAGMENT_LABEL = 1;
    int ClASSFRAGMENT_LABEL = 2;
    int SHOPPINGFRAGMENT_LABEL = 3;
    int MINEFRAGMENT_LABEL = 4;



    void updateNums(int nums);

    BannerBean getAdData();

    /**
     * 广告详情展示
     */
    void showAd();

    /**
     * 广告跳过
     */
    void splashAd();
    /**
     * 广告关闭
     */
    void closeAd();

    /**
     * 加载广告页
     */
    void loadWebUrl();

    /**
     * 添加fragment
     *
     * @param fragment
     */
    void addFragment(Fragment fragment);

    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    void hideFragment(Fragment fragment);

    /**
     * 显示fragment
     *
     * @param fragment
     */
    void showFragment(Fragment fragment);

}
