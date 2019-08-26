package com.hsh.dongzi.jinshang.base.mvp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.enums.BroadcastEnums;
import com.hsh.dongzi.jinshang.managers.AppManager;
import com.hsh.dongzi.jinshang.model.login.UserBean;
import com.hsh.dongzi.jinshang.model.main.MainModel;
import com.hsh.dongzi.jinshang.utils.LogUtil;
import com.hsh.dongzi.jinshang.utils.ToastUtil;
import com.hsh.dongzi.jinshang.weiget.MyKprogressHUD;
import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseImpl, BGASwipeBackHelper.Delegate{
    public CompositeDisposable mCompositeDisposable;
    protected P mPresenter;

    protected BGASwipeBackHelper mSwipeBackHelper;

    public static final int NO_SETCONIENTVIEW = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSwipeBask();
        super.onCreate(savedInstanceState);
        setPortrait();
        setStatusBarColor(Color.WHITE,0);
        if (null == mCompositeDisposable){
            mCompositeDisposable = new CompositeDisposable();
        }
        if (null == mPresenter){
            mPresenter = createPresenter();
        }
        AppManager.getInstance().addActivity(this);
        beforeView();
        if (layoutRes() != NO_SETCONIENTVIEW){
            setContentView(layoutRes());
        }
        ButterKnife.bind(this);
        initView();
    }

    private void initSwipeBask(){
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }

    protected void beforeView(){}

    protected abstract int layoutRes();

    protected abstract void initView();

    protected abstract P createPresenter();

    protected void setPortrait(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
    }

    protected void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha);
        StatusBarUtil.setLightMode(this);
    }

    @Override
    public void showErrorToast(String errMsg) {
        ToastUtil.showToastSafe(errMsg);
    }

    @Override
    public boolean addDisposable(Disposable disposable) {
        if (null != mCompositeDisposable){
            mCompositeDisposable.add(disposable);
        }
        return true;
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginError() {
        LogUtil.e("逻辑异常错误");
    }

    @Override
    public void showEmptyListUI() {

    }

    @Override
    public void showErrorNetUI() {

    }

    @Override
    public boolean checkUnLogin() {
        UserBean user = MainModel.getInstance().getUserInfo();
        if (user == null || !user.isLogined()) {
            LogUtil.d("发送未登录广播");
            sendBroadcast(new Intent(BroadcastEnums.LOGIN), BroadcastEnums.BRODCAST_PERMISSION);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {

    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    /**
     * 跳转到下一个 Activity，不销毁当前 Activity
     *
     * @param cls 下一个 Activity 的 Class
     */
    public void forward(Class<?> cls) {
        mSwipeBackHelper.forward(cls);
    }

    /**
     * 跳转到下一个 Activity，不销毁当前 Activity
     *
     * @param cls         下一个 Activity 的 Class
     * @param requestCode 请求码
     */
    public void forward(Class<?> cls, int requestCode) {
        mSwipeBackHelper.forward(cls, requestCode);
    }

    /**
     * 跳转到下一个 Activity,不销毁当前 Activity
     *
     * @param intent 下一个 Activity 的意图对象
     */
    public void forward(Intent intent) {
        mSwipeBackHelper.forward(intent);
    }

    /**
     * 跳转到下一个 Activity,不销毁当前 Activity
     *
     * @param intent      下一个 Activity 的意图对象
     * @param requestCode 请求码
     */
    public void forward(Intent intent, int requestCode) {
        mSwipeBackHelper.forward(intent, requestCode);
    }

    /**
     * 跳转到下一个 Activity，并且销毁当前 Activity
     *
     * @param cls 下一个 Activity 的 Class
     */
    public void forwardAndFinish(Class<?> cls) {
        MyKprogressHUD.getInstance().dismiss();
        mSwipeBackHelper.forwardAndFinish(cls);
    }

    /**
     * 跳转到下一个 Activity，销毁当前 Activity
     *
     * @param intent 下一个 Activity 的意图对象
     */
    public void forwardAndFinish(Intent intent) {
        MyKprogressHUD.getInstance().dismiss();
        mSwipeBackHelper.forwardAndFinish(intent);
    }

    /**
     * 回到上一个 Activity，并销毁当前 Activity
     */
    public void backward() {
        MyKprogressHUD.getInstance().dismiss();
        mSwipeBackHelper.backward();
    }

    /**
     * 回到上一个 Activity，并销毁当前 Activity（应用场景：欢迎、登录、注册这三个界面）
     *
     * @param cls 上一个 Activity 的 Class
     */
    public void backwardAndFinish(Class<?> cls) {
        MyKprogressHUD.getInstance().dismiss();
        mSwipeBackHelper.backwardAndFinish(cls);
    }

    /**
     * 滑动返回上一个 Activity，并销毁当前 Activity
     */
    public void swipeBackward() {
        MyKprogressHUD.getInstance().dismiss();
        mSwipeBackHelper.swipeBackward();
    }

    /**
     * 退出应用程序
     */
    public void exit(){
        AppManager.getInstance().appExit(this);
    }


    public void backwardDelay() {
        new Handler().postDelayed(() -> backward(), 200);
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判定当前是否需要隐藏
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1], bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;

    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard(IBinder token) {
        if (token != null) {
            try {
                InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (im.isActive()) {
                    im.hideSoftInputFromWindow(token, InputMethodManager.RESULT_UNCHANGED_SHOWN);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mCompositeDisposable){
            mCompositeDisposable.clear();
        }
        if (null != mPresenter){
            mPresenter.detachView();
        }
        AppManager.getInstance().removeActivity(this);
    }
}
