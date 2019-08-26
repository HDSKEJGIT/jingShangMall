package com.hsh.dongzi.jinshang.exceptions;


import com.hsh.dongzi.jinshang.enums.ConstantCodeEnums;

/**
 * Created by lijiacheng on 2017/10/7.
 */

public class ApiException extends RuntimeException {

    private int mErrorCode;

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }

    /**
     * 判断是否是token失效
     *
     * @return 失效返回true, 否则返回false;
     */
    public boolean isTokenExpried() {
        return mErrorCode == ConstantCodeEnums.EXCEPTION_TOKEN;
    }
}
