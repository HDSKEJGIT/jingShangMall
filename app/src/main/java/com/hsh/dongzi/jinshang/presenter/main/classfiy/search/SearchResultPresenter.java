package com.hsh.dongzi.jinshang.presenter.main.classfiy.search;

import com.hsh.dongzi.jinshang.base.mvp.BasePresenter;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.base.net.RequestFailObserver;
import com.hsh.dongzi.jinshang.model.main.classfiy.search.SearchKeyWordBean;
import com.hsh.dongzi.jinshang.model.main.classfiy.search.SearchModel;
import com.hsh.dongzi.jinshang.ui.main.classfiy.search.SearchResultView;
import com.hsh.dongzi.jinshang.utils.StringUtils;
import com.hsh.dongzi.jinshang.utils.ToastUtil;

public class SearchResultPresenter extends BasePresenter<SearchResultView> {
    public SearchResultPresenter(SearchResultView searchResultView) {
        super(searchResultView);
    }

    /**
     * 检查输入框
     */
    public void checkEdit(){
        String search = getView().getSearch();
        if (StringUtils.isEmpty(search)){
            ToastUtil.showToastSafe("请输入要搜索的内容");
        }
        getSearch(search);
    }

    /**
     * 获取分类页面搜索到的数据
     */
    public void getSearch(String search){
        SearchModel.getInstance().getSearchData(search, new RequestFailObserver<SearchKeyWordBean>() {
            @Override
            protected void onBaseNext(SearchKeyWordBean data) {
                getView().searchSucceed(data);
            }
        });
    }

    /**
     * 获取分类页面条件检索的数据
     */
    public void getSearchCondition(){

    }

}
