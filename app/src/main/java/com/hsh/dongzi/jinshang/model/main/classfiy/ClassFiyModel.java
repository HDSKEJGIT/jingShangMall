package com.hsh.dongzi.jinshang.model.main.classfiy;

import com.hsh.dongzi.jinshang.base.mvp.BaseModel;
import com.hsh.dongzi.jinshang.net.HttpFunction;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class ClassFiyModel extends BaseModel {
    public static ClassFiyModel getInstance() {
        return getPresent(ClassFiyModel.class);
    }

    /**
     * 获取分类接口返回的数据
     */
    public void getClassFiyInfo(Observer<ClassFiyBean> observer) {
        Observable observable = mServletApi.getClassFiyInfo().map(new HttpFunction());
        toSubscribe(observable, observer);
    }
}
