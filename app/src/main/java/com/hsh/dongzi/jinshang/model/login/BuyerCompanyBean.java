package com.hsh.dongzi.jinshang.model.login;

import java.io.Serializable;

/**
 * 公司信息bean
 */
public class BuyerCompanyBean implements Serializable {
    protected String address;// (string, optional): 详细地址 ,
    protected String bankaccount;// (string, optional): 银行帐号 ,
    protected String bankname;// (string, optional): 开户银行 ,
    protected String city;// (string, optional): 市 ,
    protected String citysmall;// (string, optional): 区 ,
    protected String companyname;// (string, optional): companyname ,
    protected String companyno;// (string, optional): 单位编号 ,
    protected String createdate;// (string, optional): 创建时间 ,
    protected int id;// (integer, optional),
    protected String invoicetype;// (string, optional): 发票类型 ,
    protected String legalperson;// (string, optional): 法人代表 ,
    protected int memberid;// (integer, optional):  ,
    protected String methodsettingaccount;// (string, optional): 结算方式 ,
    protected String mobile;// (string, optional): 联系手机 ,
    protected String postaladdress;// (string, optional): 通讯地址 ,
    protected String province;// (string, optional): 省 ,
    protected String shortname;// (string, optional): shortname ,
    protected String taxregistrationcertificate;// (string, optional): 纳税号 ,
    protected String updatedate;// (string, optional): 更新时间 ,
    protected String worktelephone;// (string, optional): 单位电话 ,
    protected String zipcode;// (string, optional): 邮编
    protected String businesslicencenumberphoto;//营业执照

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitysmall() {
        return citysmall;
    }

    public void setCitysmall(String citysmall) {
        this.citysmall = citysmall;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyno() {
        return companyno;
    }

    public void setCompanyno(String companyno) {
        this.companyno = companyno;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoicetype() {
        return invoicetype;
    }

    public void setInvoicetype(String invoicetype) {
        this.invoicetype = invoicetype;
    }

    public String getLegalperson() {
        return legalperson;
    }

    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson;
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public String getMethodsettingaccount() {
        return methodsettingaccount;
    }

    public void setMethodsettingaccount(String methodsettingaccount) {
        this.methodsettingaccount = methodsettingaccount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobil) {
        this.mobile = mobil;
    }

    public String getPostaladdress() {
        return postaladdress;
    }

    public void setPostaladdress(String postaladdress) {
        this.postaladdress = postaladdress;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getTaxregistrationcertificate() {
        return taxregistrationcertificate;
    }

    public void setTaxregistrationcertificate(String taxregistrationcertificate) {
        this.taxregistrationcertificate = taxregistrationcertificate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public String getWorktelephone() {
        return worktelephone;
    }

    public void setWorktelephone(String worktelephone) {
        this.worktelephone = worktelephone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getBusinesslicencenumberphoto() {
        return businesslicencenumberphoto;
    }

    public void setBusinesslicencenumberphoto(String businesslicencenumberphoto) {
        this.businesslicencenumberphoto = businesslicencenumberphoto;
    }
}
