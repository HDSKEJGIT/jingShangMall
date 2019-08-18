package com.hsh.dongzi.myrefreshdemo.utils;

import android.util.Base64;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class Base64Utils {
    //加密
    public static String setBase64(String str) {
        return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
    }

    // 解密
    public static String getFromBase64(String s) {
        return new String(Base64.decode(s, Base64.DEFAULT));
    }
}
