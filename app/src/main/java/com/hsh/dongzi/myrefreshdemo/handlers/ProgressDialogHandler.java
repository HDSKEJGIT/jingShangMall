package com.hsh.dongzi.myrefreshdemo.handlers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * 显示dialog的handler
 * Created by lijiacheng on 2017/10/7.
 */
public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    //private ProgressDialog pd;
    private KProgressHUD mKProgressHUD;

    private Context context;
    //private boolean cancelable;

    //, boolean cancelable
    public ProgressDialogHandler(Context context) {
        super();
        this.context = context;
        //this.cancelable = cancelable;
    }

    private void initProgressDialog(String title) {
        if (null == mKProgressHUD){
            String lable = title+"...";
            mKProgressHUD = KProgressHUD.create(context)
                    .setLabel(lable)
                    .show();

        }
//        if (pd == null) {
//            pd = new ProgressDialog(context);
//            if (TextUtils.isEmpty(title)) {
//                title = "加载中,请稍后....";
//            }
//            pd.setMessage(title);
//            pd.setCancelable(cancelable);
//            if (cancelable) {
//                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialogInterface) {
//                        dismissProgressDialog();
//                    }
//                });
//            }
//
//            if (!pd.isShowing()) {
//                pd.show();
//            }
//        }
    }

    private void dismissProgressDialog() {
//        if (pd != null) {
//            pd.dismiss();
//            pd = null;
//        }
        if (null != mKProgressHUD){
            mKProgressHUD.dismiss();
            mKProgressHUD = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                String title = (String) msg.obj;
                initProgressDialog(title);
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

}
