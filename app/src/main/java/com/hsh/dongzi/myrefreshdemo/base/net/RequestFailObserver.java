package com.hsh.dongzi.myrefreshdemo.base.net;

import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.net.ParseException;
import android.text.TextUtils;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.hsh.dongzi.myrefreshdemo.MyApplication;
import com.hsh.dongzi.myrefreshdemo.base.mvp.BaseImpl;
import com.hsh.dongzi.myrefreshdemo.enums.BroadcastEnums;
import com.hsh.dongzi.myrefreshdemo.exceptions.ApiException;
import com.hsh.dongzi.myrefreshdemo.utils.LogUtil;
import com.hsh.dongzi.myrefreshdemo.utils.ToastUtil;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * 请求失败订阅者
 * Created by lijiacheng on 2017/10/7.
 */

public abstract class RequestFailObserver<T> extends BaseObserver<T> {
    private boolean isNeedProgress;
    private String titleMsg;
    private boolean isShowError;

    public RequestFailObserver() {
        this(null, null, true);
    }

    public RequestFailObserver(BaseImpl base) {
        this(base, null, true);
    }

    public RequestFailObserver(BaseImpl base, String titleMsg) {
        this(base, titleMsg, true);
    }

    public RequestFailObserver(BaseImpl base, boolean isShowError) {
        this(base, null, isShowError);
    }

    public RequestFailObserver(BaseImpl base, String titleMsg, boolean isShowError) {
        super(base);
        this.titleMsg = titleMsg;
        if (TextUtils.isEmpty(titleMsg)) {
            this.isNeedProgress = false;
        } else {
            this.isNeedProgress = true;
        }
        this.isShowError = isShowError;
    }

    @Override
    protected boolean isNeedProgressDialog() {
        return isNeedProgress;
    }

    @Override
    protected String getTitleMsg() {
        return titleMsg;
    }

    @Override
    protected void onBaseError(Throwable t) {
        StringBuffer sb = new StringBuffer();
        if (t instanceof NetworkErrorException || t instanceof UnknownHostException || t instanceof ConnectException) {
            sb.append("网络异常");
        } else if (t instanceof SocketTimeoutException || t instanceof InterruptedIOException || t instanceof TimeoutException) {
            sb.append("请求超时");
        } else if (t instanceof JsonSyntaxException) {
            sb.append("类型转化错误");
        } else if (t instanceof JsonParseException
                || t instanceof JSONException
                || t instanceof ParseException) {   //  解析错误
            sb.append("解析错误");
        } else if (t instanceof ApiException) {
            if (((ApiException) t).isTokenExpried()) {
                sb.append("Token出错");
                MyApplication.getInstance().getApplicationContext().sendBroadcast(new Intent(BroadcastEnums.LOGIN));
            } else {
                sb.append(t.getMessage());
            }
        } else {
            sb.append(t.getMessage());
        }
        LogUtil.e("onBaseError: Throwable=" + t.getMessage());
        LogUtil.e("onBaseError: " + sb.toString());
        if (isShowError) ToastUtil.showToastSafe(sb.toString());
    }
}
