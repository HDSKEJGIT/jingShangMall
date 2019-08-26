package com.hsh.dongzi.jinshang.base.net;

import android.accounts.NetworkErrorException;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.hsh.dongzi.jinshang.MyApplication;
import com.hsh.dongzi.jinshang.model.login.LoginModel;
import com.hsh.dongzi.jinshang.utils.LogUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lijiacheng on 2017/10/7.
 */

public abstract class BaseRetrofit {
    protected String baseUrl = MyApplication.getURL();
    protected Retrofit mRetrofit;

    private static final int DEFAULT_TIME = 15;    //默认超时时间
    private final long RETRY_TIMES = 1;   //重订阅次数

    public BaseRetrofit() {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
            builder.connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
//            builder.cookieJar();
            //设置拦截器
            if (MyApplication.isDabug()) {
                builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));//日志拦截器
            }
            builder.addInterceptor(RequestInterceptor);//设置请求头拦截器
            builder.addNetworkInterceptor(ResponseInterceptor);//设置响应头拦截器
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())//增加返回值为String的支持
                    .addConverterFactory(GsonConverterFactory.create())//增加返回类型为Gson的支持
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//增加返回值为Oservable<T>的支持
                    .build();
        }
    }

    //公共参数
    protected abstract Map<String, String> getCommonMap();

    protected <T> void toSubscribe(Observable<T> observable, Observer<T> observer) {
        observable
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //TODO 在主线程的进度条操作
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())    // 指定doOnSubscribe()发生在主线程
                .observeOn(AndroidSchedulers.mainThread())  // 指定Subscriber的回调发生在主线程
                .timeout(DEFAULT_TIME, TimeUnit.SECONDS)    //重连间隔时间
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    private int mRetryCount;

                    @Override
                    public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {
                                if ((throwable instanceof NetworkErrorException
                                        || throwable instanceof ConnectException
                                        || throwable instanceof SocketTimeoutException
                                        || throwable instanceof TimeoutException) && mRetryCount < 1) {
                                    mRetryCount++;
                                    return Observable.timer(2000, TimeUnit.MILLISECONDS);
                                }
                                return Observable.error(throwable);
                            }
                        });
                    }
                })
                .subscribe(observer);   //订阅
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected static <T> T getPresent(Class<T> cls) {
        T instance = null;
        try {
            instance = cls.newInstance();
            if (instance == null) {
                return null;
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Interceptor RequestInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String cookie = "";
            String cookieToken = LoginModel.getInstance().getCookieToken();
            String cookieSession = LoginModel.getInstance().getCookieSession();
            if (!TextUtils.isEmpty(cookieToken)) {
                cookieToken = cookieToken.substring(cookieToken.indexOf("Cookie") + 6);
                cookie += "WapTokenCookieName=" + cookieToken + ";";
            }
            if (!TextUtils.isEmpty(cookieSession)) {
                cookieSession = cookieSession.substring(cookieSession.indexOf("Cookie") + 6);
                cookie += "SESSION=" + cookieSession + ";";
            }
            LogUtil.e("设置cookie:" + cookie);
            request = request.newBuilder()
                    .header("Cookie", cookie)
                    .build();
            return chain.proceed(request);
        }
    };
    protected Interceptor ResponseInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            List<String> cookies = originalResponse.headers("Set-Cookie");
            String cookieStr = "";
            if (cookies != null && cookies.size() > 0) {
                for (int i = 0; i < cookies.size(); i++) {
                    cookieStr += cookies.get(i) + ";";
                }
                String url = originalResponse.request().url().toString();
//                if (url.contains(OtherEnums.loginUrl)) {
                cookieStr = cookieStr.replace(" ", "");
                String[] list = cookieStr.split(";");
                for (int i = 0; i < list.length; i++) {
                    String[] values = list[i].split("=");
                    if (values.length >= 2) {
                        if (values[0].contains("WapTokenCookieName")) {
                            cookieStr = "Cookie" + values[1];
                            LogUtil.e("保存WapTokenCookieName=" + cookieStr);
                            LoginModel.getInstance().saveCookieToken(cookieStr);
                        }
                        if (values[0].contains("SESSION")) {
                            cookieStr = "Cookie" + values[1];
                            LogUtil.e("保存SESSION=" + cookieStr);
                            LoginModel.getInstance().saveCookieSession(cookieStr);
                            break;
                        }
                    }
                }
//                }
            }
            return originalResponse;
        }
    };
}
