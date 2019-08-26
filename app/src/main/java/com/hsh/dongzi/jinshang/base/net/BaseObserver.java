package com.hsh.dongzi.jinshang.base.net;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;
import com.hsh.dongzi.jinshang.handlers.ProgressDialogHandler;
import com.hsh.dongzi.jinshang.utils.LogUtil;
import com.hsh.dongzi.jinshang.utils.PlatformUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lijiacheng on 2017/10/7.
 */

public abstract class BaseObserver<T> implements Observer<T> {

    protected abstract void onBaseError(Throwable t);

    protected abstract void onBaseNext(T data);

    protected abstract boolean isNeedProgressDialog();

    protected abstract String getTitleMsg();

    private ProgressDialogHandler mProgressDialogHandler;
    private BaseImpl mBaseImpl;
    private PlatformUtil mPlatform;

    public BaseObserver(BaseImpl baseImpl) {
        mBaseImpl = baseImpl;
        if (null != mBaseImpl) {
            if (null == mProgressDialogHandler) {
                mProgressDialogHandler = new ProgressDialogHandler(baseImpl.getContext());
            }
        }
        mPlatform = PlatformUtil.get();
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG, getTitleMsg()).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        //显示进度条
        if (isNeedProgressDialog()) {
            showProgressDialog();
        }
        if (null != mBaseImpl) {
            if (null != d) {
                mBaseImpl.addDisposable(d);
            }
        }
    }

    @Override
    public void onNext(final T value) {
        //成功
        LogUtil.d("http is onNext");
        if (null != value) {
            mPlatform.execute(new Runnable() {
                @Override
                public void run() {
                    onBaseNext(value);
                }
            });
        }
    }

    @Override
    public void onError(final Throwable e) {
        //关闭进度条
        LogUtil.e("http is onError");
        if (isNeedProgressDialog()) {
            dismissProgressDialog();
        }
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                onBaseError(e);
            }
        });
    }

    @Override
    public void onComplete() {
        //关闭进度条
        if (isNeedProgressDialog()) {
            dismissProgressDialog();
        }
    }
}
