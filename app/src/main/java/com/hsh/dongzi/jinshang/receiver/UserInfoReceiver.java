package com.hsh.dongzi.jinshang.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hsh.dongzi.jinshang.base.mvp.BaseActivity;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.base.net.RequestFailObserver;
import com.hsh.dongzi.jinshang.model.login.UserBean;
import com.hsh.dongzi.jinshang.model.main.MainModel;
import com.hsh.dongzi.jinshang.utils.LogUtil;


/**
 * Created by lijiacheng on 2017/12/7.
 */

public class UserInfoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        LogUtil.e("接收到获取用户信息广播");
        MainModel.getInstance().getUserInfo(new RequestFailObserver<UserBean>((BaseActivity) context) {
            @Override
            protected void onBaseNext(UserBean data) {
                LogUtil.e("广播更新用户信息,保存到本地");
                long curentTime = System.currentTimeMillis();
                int sequence = (int) (curentTime - (curentTime / 1000) * 1000);
                LogUtil.d("sequence=" + sequence);
//                String registrationId = JPushInterface.getRegistrationID(context);
//                LogUtil.d("registrationId=" + registrationId);
//                JPushInterface.setAlias(context, sequence, data.getMobile());
                MainModel.getInstance().saveUserInfo(data);
//                MainModel.getInstance().updateRegistrationId(data.getId() + "", registrationId,
//                        new RequestFailObserver<BaseResponse>(null, false) {
//                            @Override
//                            protected void onBaseNext(BaseResponse data) {
//                                LogUtil.d("更新registrationId成功");
//                            }
//                        });
            }
        });
    }
}
