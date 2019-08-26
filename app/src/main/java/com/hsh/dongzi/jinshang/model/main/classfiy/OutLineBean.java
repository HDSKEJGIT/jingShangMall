package com.hsh.dongzi.jinshang.model.main.classfiy;

import java.io.Serializable;

public class OutLineBean implements Serializable {
    private int id;
    private String name;
    private boolean isSelect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
