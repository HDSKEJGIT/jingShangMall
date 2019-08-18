package com.hsh.dongzi.myrefreshdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.hsh.dongzi.myrefreshdemo.utils.AppUtils;


public class MyApplication extends AppUtils {
    private static Context context;
    public boolean isDebug = true;

    /**
     * 主线程ID
     */
    public static int mMainThreadId = -1;
    /**
     * 主线程
     */
    public static Thread mMainThread;
    /**
     * 主线程Handler
     */
    public static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    public static Looper mMainLooper;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
    }


    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }


    /**
     * 获取域名URL
     */
    public static String getURL() {
        String url = null;
        boolean isDebug = context.getResources().getBoolean(R.bool.isDebug);
        if (isDebug) {
            url = context.getResources().getString(R.string.local_url);
        } else {
            try {
                url = context.getResources().getString(R.string.online_url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    public static boolean isDabug(){
        boolean isDebug = context.getResources().getBoolean(R.bool.isDebug);
        if (isDebug){
            return true;
        }
        return false;
    }
}
