package com.hsh.dongzi.jinshang.base.mvp;

import android.text.TextUtils;

import com.hsh.dongzi.jinshang.base.net.BaseRetrofit;
import com.hsh.dongzi.jinshang.net.ServletApi;
import com.hsh.dongzi.jinshang.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

public class BaseModel extends BaseRetrofit {
    protected Map<String, String> mParams = new HashMap<>();
    protected ServletApi mServletApi;

    public BaseModel(){
        super();
        mServletApi = mRetrofit.create(ServletApi.class);
    }
    @Override
    protected Map<String, String> getCommonMap() {
        Map<String,String> commonMap = new HashMap<>();
        return commonMap;
    }


    protected void addParams(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            LogUtil.e("the key is null");
            return;
        }
        mParams.put(key, value);
    }

    protected void addParams(Map<String, String> params) {
        if (null == params) {
            LogUtil.e("the map is null");
            return;
        }
        mParams.putAll(params);
    }
}
