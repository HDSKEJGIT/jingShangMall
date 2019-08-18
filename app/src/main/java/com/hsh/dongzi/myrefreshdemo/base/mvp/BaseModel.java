package com.hsh.dongzi.myrefreshdemo.base.mvp;

import android.text.TextUtils;

import com.hsh.dongzi.myrefreshdemo.base.net.BaseRetrofit;
import com.hsh.dongzi.myrefreshdemo.net.ServletApi;
import com.hsh.dongzi.myrefreshdemo.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

public class BaseModel extends BaseRetrofit {
    protected Map<String, Object> mParams = new HashMap<>();
    protected ServletApi mServletApi;

    public BaseModel(){
        super();
        mServletApi = mRetrofit.create(ServletApi.class);
    }
    @Override
    protected Map<String, Object> getCommonMap() {
        Map<String,Object> commonMap = new HashMap<>();
        return commonMap;
    }


    protected void addParams(String key, Object value) {
        if (TextUtils.isEmpty(key)) {
            LogUtil.e("the key is null");
            return;
        }
        mParams.put(key, value);
    }

    protected void addParams(Map<String, Object> params) {
        if (null == params) {
            LogUtil.e("the map is null");
            return;
        }
        mParams.putAll(params);
    }
}
