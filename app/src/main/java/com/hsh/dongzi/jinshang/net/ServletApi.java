package com.hsh.dongzi.jinshang.net;

import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.model.login.UserBean;
import com.hsh.dongzi.jinshang.model.main.classfiy.ClassFiyBean;
import com.hsh.dongzi.jinshang.model.main.classfiy.search.SearchKeyWordBean;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServletApi {
    /**
     * 登录接口
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("rest/buyer/wap/login")
    Observable<BaseResponse> login(@FieldMap Map<String, String> params);

    /**
     * 退出登录接口
     *
     * @return
     */
    @POST("rest/buyer/wap/logout")
    Observable<BaseResponse> loginOut();

    /**
     * 获取手机验证码接口
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("rest/front/mobile/genMobileCode")
    Observable<BaseResponse> getVCode(@FieldMap Map<String, String> params);

    /**
     * 注册接口
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("rest/buyer/registerMember")
    Observable<BaseResponse> register(@FieldMap Map<String, String> params);

    /**
     * 手机号登录接口
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("rest/buyer/wap/loginByMobile")
    Observable<BaseResponse> loginByPhone(@FieldMap Map<String, String> params);

    /**
     * 验证手机号是否存在
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("rest/common/exisMobile")
    Observable<BaseResponse> checkPhoneIsExist(@FieldMap Map<String, String> params);


    /**
     * 找回密码1接口
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("rest/forgetPassword/step1")
    Observable<BaseResponse> findPswd1(@FieldMap Map<String, String> params);



    /**
     * 找回密码2接口
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("rest/forgetPassword/step2")
    Observable<BaseResponse> findPswd2(@FieldMap Map<String, String> params);


    /**
     * 获取用户数据
     *
     * @return
     */
    @POST("rest/common/db/userInfo")
    Observable<BaseResponse<UserBean>> getUserInfo();


    /**
     * 获取分类数据
     */

    @POST("rest/common/app/getProductCategories")
    Observable<BaseResponse<ClassFiyBean>> getClassFiyInfo();

    /**
     * 获取分类关键字搜索数据
     */
    @POST("rest/front/product/level3/list")
    Observable<BaseResponse<SearchKeyWordBean>> getSearchData(@FieldMap Map<String, String> params);

}
