package com.hsh.dongzi.jinshang.ui.main.classfiy.search;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;
import com.hsh.dongzi.jinshang.model.main.classfiy.search.SearchKeyWordBean;

public interface SearchResultView extends BaseImpl {
    /**
     * 获取输入框内容
     */
    String getSearch();

    /**
     * 请求关键字检索接口成功的回调
     */
    void searchSucceed(SearchKeyWordBean data);
}
