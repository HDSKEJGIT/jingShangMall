package com.hsh.dongzi.jinshang.model.main.classfiy;

import java.io.Serializable;
import java.util.List;

public class DetailBean implements Serializable {
    private String parentid;
    private List<ParentListBean> list;

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public List<ParentListBean> getList() {
        return list;
    }

    public void setList(List<ParentListBean> list) {
        this.list = list;
    }
}
