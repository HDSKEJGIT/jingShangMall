package com.hsh.dongzi.myrefreshdemo.net;

import com.hsh.dongzi.myrefreshdemo.base.net.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
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
    Observable<BaseResponse> login(@FieldMap Map<String, Object> params);

    /**
     * 退出登录接口
     *
     * @return
     */
    @POST("rest/buyer/wap/logout")
    Observable<BaseResponse> loginOut();
}
