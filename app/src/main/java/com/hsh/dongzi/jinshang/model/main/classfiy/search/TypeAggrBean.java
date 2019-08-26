package com.hsh.dongzi.jinshang.model.main.classfiy.search;

import java.io.Serializable;

public class TypeAggrBean implements Serializable {
    private int doccount;
    private String name;
    private int type;

    public int getDoccount() {
        return doccount;
    }

    public void setDoccount(int doccount) {
        this.doccount = doccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
