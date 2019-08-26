package com.hsh.dongzi.jinshang.model.main;

import com.hsh.dongzi.jinshang.MyApplication;
import com.hsh.dongzi.jinshang.base.mvp.BaseModel;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.enums.SPEnums;
import com.hsh.dongzi.jinshang.model.login.BuyerCompanyBean;
import com.hsh.dongzi.jinshang.model.login.UserBean;
import com.hsh.dongzi.jinshang.net.HttpFunction;
import com.hsh.dongzi.jinshang.utils.SPUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by lijiacheng on 2017/10/18.
 */

public class MainModel extends BaseModel {
    public static MainModel getInstance() {
        return getPresent(MainModel.class);
    }

    /**
     * 获取接口返回用户数据
     *
     * @param observer
     */
    public void getUserInfo(Observer<UserBean> observer) {
        Observable observable = mServletApi.getUserInfo().map(new HttpFunction());
        toSubscribe(observable, observer);
    }

//    /**
//     * 检查更新
//     *
//     * @param observer
//     */
//    public void checkVersion(Observer<AppVersionBean> observer) {
//        addParams("apptype", "android");
//        addParams("version", JsBuyersApplication.getInstance().getVersionCode() + "");
//        addParams("channel", JsBuyersApplication.getInstance().getChannel());
//        Observable observable = mServletApi.checkVersion(mParams).map(new HttpFunction());
//        toSubscribe(observable, observer);
//    }

//    /**
//     * 用户绑定极光推送设备id
//     *
//     * @param id             用户id
//     * @param registrationId
//     */
//    public void updateRegistrationId(String id, String registrationId, Observer<BaseResponse> observer) {
//        addParams("id", id);
//        addParams("registrationid", registrationId);
//        Observable observable = mServletApi.updateRegistrationId(mParams).map(new HttpFunction());
//        toSubscribe(observable, observer);
//    }

    /**
     * 保存用户数据
     *
     * @param user
     */
    public void saveUserInfo(UserBean user) {
        user.setLogined(true);
        SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).putObject(SPEnums.USERINFO, user);
    }

    /**
     * 获取本地缓存用户数据
     *
     * @return
     */
    public UserBean getUserInfo() {
        return (UserBean) SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).getObject(SPEnums.USERINFO);
    }

    /**
     * 清空本地缓存用户数据
     */
    public void clearUserInfo() {
        SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).putObject(SPEnums.USERINFO, null);
    }

    /**
     * 保存用户公司数据
     *
     * @param buyerCompany
     */
    public void saveUserCompanyInfo(BuyerCompanyBean buyerCompany) {
        SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).putObject(SPEnums.BUYERCOMPANYINFO, buyerCompany);
    }

    /**
     * 获取本地缓存用户公司数据
     *
     * @return
     */
    public BuyerCompanyBean getUserCompanyInfo() {
        BuyerCompanyBean buyerCompany = (BuyerCompanyBean) SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).getObject(SPEnums.BUYERCOMPANYINFO);
        if (buyerCompany == null) {
            buyerCompany = new BuyerCompanyBean();
        }
        return buyerCompany;
    }

    /**
     * 清空本地缓存用户公司数据
     */
    public void clearUserCompanyInfo() {
        SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).putObject(SPEnums.BUYERCOMPANYINFO, null);
    }

//    /**
//     * 获取用户下订单各种状态下的数量
//     *
//     * @param observer
//     */
//    public void getOrderStateNum(Observer<SubmitOrderSuccessBean> observer) {
//        Observable observable = mServletApi.getOrderStateNum().map(new HttpFunction());
//        toSubscribe(observable, observer);
//    }
}
