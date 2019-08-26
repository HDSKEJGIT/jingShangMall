package com.hsh.dongzi.jinshang.weiget;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

public class MyKprogressHUD {
    private KProgressHUD kProgressHUD;
    private static MyKprogressHUD instance = null;

    private MyKprogressHUD(){}

    public static MyKprogressHUD getInstance(){
        if (null == instance){
            instance = new MyKprogressHUD();
        }
        return instance;
    }

    public void show(Context context,String lable){
        if (null == kProgressHUD){
            kProgressHUD = KProgressHUD.create(context);
        }
        kProgressHUD.setLabel(lable)
                .show();
    }

    public void dismiss(){
        if (null != kProgressHUD){
            kProgressHUD.dismiss();
            kProgressHUD = null;
        }
    }
}
