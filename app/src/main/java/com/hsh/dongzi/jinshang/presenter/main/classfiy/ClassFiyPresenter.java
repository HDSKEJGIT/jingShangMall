package com.hsh.dongzi.jinshang.presenter.main.classfiy;

import android.text.TextUtils;

import com.hsh.dongzi.jinshang.base.mvp.BaseImpl;
import com.hsh.dongzi.jinshang.base.mvp.BasePresenter;
import com.hsh.dongzi.jinshang.base.net.BaseResponse;
import com.hsh.dongzi.jinshang.base.net.RequestFailObserver;
import com.hsh.dongzi.jinshang.model.main.classfiy.ClassFiyBean;
import com.hsh.dongzi.jinshang.model.main.classfiy.ClassFiyModel;
import com.hsh.dongzi.jinshang.ui.main.classfiy.ClassFiyView;
import com.hsh.dongzi.jinshang.utils.StringUtils;
import com.hsh.dongzi.jinshang.utils.ToastUtil;

public class ClassFiyPresenter extends BasePresenter<ClassFiyView> {
    public ClassFiyPresenter(ClassFiyView classFiyView) {
        super(classFiyView);
    }


    public void checkAndGoSearch() {
        String search = getView().getSearch();
        if (StringUtils.isEmpty(search)) {
            ToastUtil.showToastSafe("请输入要搜索的内容");
           return;
        }
        getView().goSearch(getView().getSearch());

    }

    /**
     * 获取分类数据
     */
    public void getClassData(final BaseImpl baseImpl){
        ClassFiyModel.getInstance().getClassFiyInfo(new RequestFailObserver<ClassFiyBean>(baseImpl,"正在加载") {
            @Override
            protected void onBaseNext(ClassFiyBean data) {
                getView().reuqestSucceed(data);
            }

            @Override
            protected void onBaseError(Throwable t) {
                getView().requestErroy();
            }
        });
    }
}
