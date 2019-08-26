package com.hsh.dongzi.jinshang.model.main.classfiy.search;

import android.app.Dialog;

import java.io.Serializable;

public class ResultSearchBean implements Serializable {
    private String productname;
    private String level3;
    private String stand;
    private String material;
    private String packagetype;
    private Double pdstorenum;
    private String storename;
    private String cardnum;
    private String storeunit;
    private Double prodprice;


    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPackagetype() {
        return packagetype;
    }

    public void setPackagetype(String packagetype) {
        this.packagetype = packagetype;
    }

    public Double getPdstorenum() {
        return pdstorenum;
    }

    public void setPdstorenum(Double pdstorenum) {
        this.pdstorenum = pdstorenum;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getStoreunit() {
        return storeunit;
    }

    public void setStoreunit(String storeunit) {
        this.storeunit = storeunit;
    }

    public Double getProdprice() {
        return prodprice;
    }

    public void setProdprice(Double prodprice) {
        this.prodprice = prodprice;
    }
}
