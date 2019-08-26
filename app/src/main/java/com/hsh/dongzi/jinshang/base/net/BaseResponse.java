package com.hsh.dongzi.jinshang.base.net;

import com.google.gson.annotations.SerializedName;
import com.hsh.dongzi.jinshang.enums.ConstantCodeEnums;

import java.io.Serializable;

/**
 * Created by ljc on 2016/9/18.
 */
public class BaseResponse<T> implements Serializable {
    @SerializedName("result")
    private int result;
    @SerializedName("message")
    private String message;
    @SerializedName("token")
    private String token;
    @SerializedName("data")
    private T data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * API是否请求成功
     *
     * @return 成功返回true, 失败返回false
     */
    public boolean isRequestSuccess() {
        return result == ConstantCodeEnums.REQUEST_SUCCESS;
    }
}
