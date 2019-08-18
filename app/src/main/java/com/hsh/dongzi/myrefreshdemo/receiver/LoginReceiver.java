package com.hsh.dongzi.myrefreshdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hsh.dongzi.myrefreshdemo.ui.login.LoginActivity;
import com.hsh.dongzi.myrefreshdemo.utils.LogUtil;


/**
 * Created by lijiacheng on 2017/12/7.
 */

public class LoginReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.e("接收到未登录广播");
        Intent it = new Intent(context, LoginActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
    }
}
