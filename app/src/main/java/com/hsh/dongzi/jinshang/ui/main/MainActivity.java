package com.hsh.dongzi.jinshang.ui.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.base.mvp.BaseActivity;
import com.hsh.dongzi.jinshang.enums.BroadcastEnums;
import com.hsh.dongzi.jinshang.enums.OtherEnums;
import com.hsh.dongzi.jinshang.enums.SPEnums;
import com.hsh.dongzi.jinshang.model.login.UserBean;
import com.hsh.dongzi.jinshang.model.main.browser.BrowserBean;
import com.hsh.dongzi.jinshang.model.main.home.BannerBean;
import com.hsh.dongzi.jinshang.model.main.MainModel;
import com.hsh.dongzi.jinshang.presenter.main.MainPresenter;
import com.hsh.dongzi.jinshang.receiver.LoginReceiver;
import com.hsh.dongzi.jinshang.receiver.UserInfoReceiver;
import com.hsh.dongzi.jinshang.ui.main.browser.BrowserActivity;
import com.hsh.dongzi.jinshang.ui.main.browser.BrowserView;
import com.hsh.dongzi.jinshang.ui.main.classfiy.ClassFiyFragment;
import com.hsh.dongzi.jinshang.ui.main.home.HomeFragment;
import com.hsh.dongzi.jinshang.ui.main.mine.MineFragment;
import com.hsh.dongzi.jinshang.ui.main.shopping.ShoppingFragment;
import com.hsh.dongzi.jinshang.utils.EventUtil;
import com.hsh.dongzi.jinshang.utils.LogUtil;
import com.hsh.dongzi.jinshang.utils.SPUtils;
import com.hsh.dongzi.jinshang.utils.ToastUtil;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    @BindView(R.id.v_fragment)
    FrameLayout vFragment;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_class)
    LinearLayout llClass;
    @BindView(R.id.tv_nums)
    TextView tvNums;
    @BindView(R.id.rl_shopping)
    RelativeLayout rlShopping;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;
    @BindView(R.id.iv_ad)
    ImageView ivAd;
    @BindView(R.id.tv_timer)
    TextView tvTimer;
    @BindView(R.id.rl_ad)
    RelativeLayout rlAd;

    private int nums = 0;
    private int first_fragment;
    private boolean isNewIntent;

    HomeFragment homeFragment;
    ClassFiyFragment classFragment;
    ShoppingFragment shopFragment;
    MineFragment mineFragment;

    private CountDownTimer timer;
    private String webUrl;
    private BannerBean adData;

    private LoginReceiver loginReceiver;

    private UserInfoReceiver userInfoReceiver;
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.d("onNewIntent");
        int new_first_fragment = intent.getIntExtra("data", HOMEFRAGMENT_LABEL);
        if (new_first_fragment != first_fragment) {
            isNewIntent = true;
        } else {
            isNewIntent = false;
        }
        first_fragment = new_first_fragment;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isNewIntent) {
            setSelectFragment();
        }
    }

    @Override
    protected void beforeView() {
        mSwipeBackHelper.setSwipeBackEnable(true);
        nums = SPUtils.getInstance(this).getIntValue(SPEnums.SHOPCARNUMS);
        adData = (BannerBean) getIntent().getSerializableExtra("adData");
        first_fragment = getIntent().getIntExtra("data", HOMEFRAGMENT_LABEL);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mPresenter.checkData();
        registerBrodcast();
        setSelectFragment();
        updateNums(nums);

        tvTimer.setOnClickListener(view -> closeAd());
        ivAd.setOnClickListener(view -> mPresenter.checkWebUrl(webUrl));
    }


    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


    private void registerBrodcast() {
        LogUtil.d("注册广播");
        IntentFilter loginFilter = new IntentFilter();
        loginFilter.addAction(BroadcastEnums.LOGIN);
        loginReceiver = new LoginReceiver();
        registerReceiver(loginReceiver, loginFilter);

        IntentFilter userInfoFilter = new IntentFilter();
        userInfoFilter.addAction(BroadcastEnums.USERINFO);
        userInfoReceiver = new UserInfoReceiver();
        registerReceiver(userInfoReceiver, userInfoFilter);
//
//        IntentFilter shopCarFilter = new IntentFilter();
//        shopCarFilter.addAction(BroadcastEnums.SHOPCAR);
//        shopCarReceiver = new ShopCarReceiver();
//        registerReceiver(shopCarReceiver, shopCarFilter);

        /*
        TODO 暂时不需要下载apk广播
        IntentFilter apkDownloadFilter = new IntentFilter();
        apkDownloadFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        apkDownLoadReceiver = new ApkDownLoadReceiver();
        registerReceiver(apkDownLoadReceiver, apkDownloadFilter);
        */
    }

    @Override
    public BannerBean getAdData() {
        return adData;
    }

    @Override
    public void showAd() {
        rlAd.setVisibility(View.VISIBLE);
        webUrl = adData.getUrl();
        Glide.with(this).load(OtherEnums.imgUrlHead + adData.getImgs()).into(ivAd);
        startTimer();
    }

    @Override
    public void splashAd() {
        rlAd.setVisibility(View.GONE);
        cancleTimer();
    }

    /**
     * 关闭广告
     */
    @Override
    public void closeAd() {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(rlAd, "alpha", 1f, 0f);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(rlAd, "scaleX", 1f, 2f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(rlAd, "scaleY", 1f, 2f);
        AnimatorSet set = new AnimatorSet();
        //同时沿X,Y轴放大，且改变透明度，然后移动
        set.play(alphaAnimator).with(scaleXAnimator).with(scaleYAnimator);
        //都设置3s，也可以为每个单独设置
        set.setDuration(300);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                rlAd.setVisibility(View.GONE);
                cancleTimer();
            }
        });
        set.start();
    }

    /**
     * 加载广告链接
     */
    @Override
    public void loadWebUrl() {
        BrowserBean browser = new BrowserBean();
        browser.setUrl(webUrl);
        browser.setMode(BrowserView.MODE_DEFAULT);
        Intent intent = new Intent();
        intent.putExtra("data", browser);
        forward(BrowserActivity.class);
    }

    @Override
    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.v_fragment, fragment).commit();
        setFragment(fragment);
    }

    @Override
    public void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }



    private void setSelectFragment() {
        LogUtil.d("设置选中页面");
        switch (first_fragment) {
            case HOMEFRAGMENT_LABEL:
                OnClick(llHome);
                break;
            case ClASSFRAGMENT_LABEL:
                OnClick(llClass);
                break;
            case SHOPPINGFRAGMENT_LABEL:
                OnClick(rlShopping);
                break;
            case MINEFRAGMENT_LABEL:
                OnClick(llMine);
                break;
        }
        isNewIntent = false;
    }

    @Override
    public void updateNums(int nums) {
        this.nums = nums;
        if (nums < 1) {
            tvNums.setVisibility(View.GONE);
        } else {
            tvNums.setVisibility(View.VISIBLE);
        }
        if (nums > 99) {
            tvNums.setText("···");
        } else {
            tvNums.setText(nums + "");
        }
        SPUtils.getInstance(this).putIntValue(SPEnums.SHOPCARNUMS, nums);
    }

    /**
     * 给fragment赋值
     *
     * @param fragment
     */
    private void setFragment(Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            homeFragment = (HomeFragment) fragment;
        } else if (fragment instanceof ClassFiyFragment) {
            classFragment = (ClassFiyFragment) fragment;
        } else if (fragment instanceof ShoppingFragment) {
            shopFragment = (ShoppingFragment) fragment;
        } else if (fragment instanceof MineFragment) {
            mineFragment = (MineFragment) fragment;
        } else {
            loginError();
        }
    }

    @OnClick({R.id.ll_home, R.id.ll_class, R.id.rl_shopping, R.id.ll_mine})
    public void OnClick(View v) {
        Fragment fragment = null;
        int label = -1;
        switch (v.getId()) {
            case R.id.ll_home:
                resetMenu();
                llHome.setSelected(true);
                fragment = homeFragment;
                label = HOMEFRAGMENT_LABEL;
                break;
            case R.id.ll_class:
                resetMenu();
                llClass.setSelected(true);
                fragment = classFragment;
                label = ClASSFRAGMENT_LABEL;
                break;
            case R.id.rl_shopping:
                if (checkUnLogin()){
                    return;
                }
                resetMenu();
                rlShopping.setSelected(true);
                fragment = shopFragment;
                label = SHOPPINGFRAGMENT_LABEL;
                break;
            case R.id.ll_mine:
                if (checkUnLogin()){
                    return;
                }
                resetMenu();
                llMine.setSelected(true);
                fragment = mineFragment;
                label = MINEFRAGMENT_LABEL;
                break;
        }
        first_fragment = label;
        mPresenter.checkFragmentChange(fragment, label);
    }

    private void resetMenu() {
        llHome.setSelected(false);
        llClass.setSelected(false);
        rlShopping.setSelected(false);
        llMine.setSelected(false);
        mPresenter.hideFragment(homeFragment, classFragment, shopFragment, mineFragment);
    }
    /**
     * 开启广告倒计时
     */
    public void startTimer() {
        if (timer == null) {
            timer = new CountDownTimer(4000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    tvTimer.setText("跳过 " + ((millisUntilFinished / 1000)) + " S");
                }

                @Override
                public void onFinish() {
                    closeAd();
                }

            };
        }
        timer.start();
    }

    /**
     * 取消广告倒计时
     */
    public void cancleTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (EventUtil.isDoubleHit()) {
                exit();
            } else {
                ToastUtil.showToastSafe(getResources().getString(R.string.text_press_two_exit));
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void unRegisterBrodcast() {
        unregisterReceiver(loginReceiver);
        unregisterReceiver(userInfoReceiver);
////        unregisterReceiver(shopCarReceiver);
        /*
        TODO 暂时不需要下载apk广播
        unregisterReceiver(apkDownLoadReceiver);
        */
    }

    @Override
    public void onResume() {
        super.onResume();
        UserBean user = MainModel.getInstance().getUserInfo();
        if (user == null || !user.isLogined()) {
            tvNums.setVisibility(View.GONE);
        } else {
            updateNums(nums);
        }
    }

    @Override
    protected void onDestroy() {
        cancleTimer();
        unRegisterBrodcast();
        super.onDestroy();
    }
}
