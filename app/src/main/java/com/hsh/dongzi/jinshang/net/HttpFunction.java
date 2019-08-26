package com.hsh.dongzi.jinshang.net;


import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.exceptions.ApiException;
import com.hsh.dongzi.jinshang.utils.LogUtil;

import io.reactivex.functions.Function;

/**
 * Created by lijiacheng on 2017/10/7.
 */

public class HttpFunction<T> implements Function<BaseResponse<T>, T> {

    @Override
    public T apply(BaseResponse<T> response) throws Exception {
        LogUtil.d("Begin HttpFunction");
        if (!response.isRequestSuccess()) {
            throw new ApiException(response.getResult(), response.getMessage());
        }
        LogUtil.d("HttpFunction Return");
        return null == response.getData() ? (T) response : response.getData();
    }
}
