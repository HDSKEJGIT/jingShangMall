package com.hsh.dongzi.jinshang.model.main.classfiy;

import java.io.Serializable;

public class ChildListBean implements Serializable {
    private int id;
    private String name;
    private int parentid;
    private String title;
    private String keywords;
    private String description;
    private String img;
    private int sort;
    private Double brokeragerate;
    private Double servicesrate;
    private String catetype;
    private int level;

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

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Double getBrokeragerate() {
        return brokeragerate;
    }

    public void setBrokeragerate(Double brokeragerate) {
        this.brokeragerate = brokeragerate;
    }

    public Double getServicesrate() {
        return servicesrate;
    }

    public void setServicesrate(Double servicesrate) {
        this.servicesrate = servicesrate;
    }

    public String getCatetype() {
        return catetype;
    }

    public void setCatetype(String catetype) {
        this.catetype = catetype;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
