package com.hsh.dongzi.jinshang.model.main.classfiy.search;

import com.hsh.dongzi.jinshang.base.mvp.BaseModel;
import com.hsh.dongzi.jinshang.net.HttpFunction;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class SearchModel extends BaseModel {
    public static SearchModel getInstance() {
        return getPresent(SearchModel.class);
    }



    /**
     * 获取分类页面搜索到的数据
     */
    public void getSearchData(String keyword, Observer<SearchKeyWordBean> observer){
        addParams("searchKey",keyword);
        addParams("type","0");
        Observable observable = mServletApi.getSearchData(mParams).map(new HttpFunction<>());
        toSubscribe(observable, observer);
    }

    /**
     * 获取分类页面根据条件检索到的数据
     */
    public void getSearchCondition(){

    }

}
