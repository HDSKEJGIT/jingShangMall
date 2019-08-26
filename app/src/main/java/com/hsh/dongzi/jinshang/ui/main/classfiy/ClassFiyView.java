package com.hsh.dongzi.jinshang.ui.main.classfiy;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;
import com.hsh.dongzi.jinshang.model.main.classfiy.ClassFiyBean;

public interface ClassFiyView extends BaseImpl {

    /**
     * 获取输入框内容
     */
    String getSearch();

    /**
     * 跳转到搜索页面 携带参数（搜索内容）
     */
    void goSearch(String content);

    /**
     * 接口请求成功的回调
     */

    void reuqestSucceed(ClassFiyBean data);

    /**
     * 接口请示失败的回调
     */

    void requestErroy();
}
