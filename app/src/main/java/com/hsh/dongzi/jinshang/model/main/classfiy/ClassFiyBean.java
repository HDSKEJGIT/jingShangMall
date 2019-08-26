package com.hsh.dongzi.jinshang.model.main.classfiy;

import java.io.Serializable;
import java.util.List;

public class ClassFiyBean implements Serializable {
   private List<OutLineBean> outline;
   private List<DetailBean> detail;

    public List<OutLineBean> getOutline() {
        return outline;
    }

    public void setOutline(List<OutLineBean> outline) {
        this.outline = outline;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }
}
