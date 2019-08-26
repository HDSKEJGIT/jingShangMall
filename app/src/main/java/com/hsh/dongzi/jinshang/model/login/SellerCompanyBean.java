package com.hsh.dongzi.jinshang.model.login;

import java.io.Serializable;

/**
 * 公司信息bean
 */
public class SellerCompanyBean implements Serializable {
    protected String address;// (string, optional): 公司详细地址 ,
    protected String alipayname;// (string, optional): 支付宝姓名 ,
    protected String alipayno;// (string, optional): 支付宝帐号 ,
    protected String bankaccount;// (string, optional): 公司银行帐号 ,
    protected String bankbrachaccount;// (string, optional): 支行联行号 ,
    protected String bankbrachname;// (string, optional): 开户行支行名称 ,
    protected String bankcity;// (string, optional): 开会行所在地(市) ,
    protected String bankcitysmall;// (string, optional): 开会行所在地(区) ,
    protected String bankname;// (string, optional): 银行开户行 ,
    protected String bankorgnumpic;// (string, optional): 开户银行许可证图片 ,
    protected String bankprovince;// (string, optional): 开会行所在地（省） ,
    protected String[] businesscategory;// (object, optional): 经营分类 ,
    protected String businesslicenceend;// (string, optional): 营业执照结束时间 ,
    protected String businesslicencenumber;// (string, optional): 营业执照 ,
    protected String businesslicencenumberphoto;// (string, optional): 营业执照图片地址 ,
    protected String businesslicencestart;// (string, optional): 营业执照开始时间 ,
    protected String businessscope;// (string, optional): 经营范围 ,
    protected String city;// (string, optional): 市 ,
    protected String citysmall;// (string, optional): 区 ,
    protected String companyname;// (string, optional): companyname ,
    protected String companytel;// (string, optional): 公司电话 ,
    protected String createdate;// (string, optional): 创建时间 ,
    protected String email;// (string, optional): mail ,
    protected int employeenum;// (integer, optional): 员工人数 ,
    protected int id;// (integer, optional),
    protected int isrecommend;// (integer, optional): 是否推荐0=推存1=不推存 ,
    protected String linkman;// (string, optional): 联系人 ,
    protected String linkmantel;// (string, optional): 联系人电话 ,
    protected int memberid;// (integer, optional),
    protected String province;// (string, optional): 省 ,
    protected int regfound;// (integer, optional): 注册资本（万元） ,
    protected String shopgrade;// (string, optional): 店铺等级 ,
    protected int shopgradeid;// (integer, optional): 商铺等级关联id ,
    protected String shopname;// (string, optional): 店铺名称 ,
    protected String shopperiod;// (string, optional): 店铺有效期 ,
    protected int shopstate;// (integer, optional): 店铺状态0=开店1=关店 ,
    protected String taxregistrationcertificate;// (string, optional): 纳税人识别号 ,
    protected String taxregistrationno;// (string, optional): 税务登记证 ,
    protected String taxregistrationnopic;// (string, optional): 税务登记证电子图片 ,
    protected String updatedate;// (string, optional): 更新时间 ,
    protected int validate;// (integer, optional),
    protected String wxname;// (string, optional): 微信姓名 ,
    protected String wxno;// (string, optional): 微信帐号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanytel() {
        return companytel;
    }

    public void setCompanytel(String companytel) {
        this.companytel = companytel;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkmantel() {
        return linkmantel;
    }

    public void setLinkmantel(String linkmantel) {
        this.linkmantel = linkmantel;
    }

    public int getEmployeenum() {
        return employeenum;
    }

    public void setEmployeenum(int employeenum) {
        this.employeenum = employeenum;
    }

    public int getRegfound() {
        return regfound;
    }

    public void setRegfound(int regfound) {
        this.regfound = regfound;
    }

    public String getBusinesslicencenumber() {
        return businesslicencenumber;
    }

    public void setBusinesslicencenumber(String businesslicencenumber) {
        this.businesslicencenumber = businesslicencenumber;
    }

    public String getBusinesslicencenumberphoto() {
        return businesslicencenumberphoto;
    }

    public void setBusinesslicencenumberphoto(String businesslicencenumberphoto) {
        this.businesslicencenumberphoto = businesslicencenumberphoto;
    }

    public String getBusinesslicencestart() {
        return businesslicencestart;
    }

    public void setBusinesslicencestart(String businesslicencestart) {
        this.businesslicencestart = businesslicencestart;
    }

    public String getBusinesslicenceend() {
        return businesslicenceend;
    }

    public void setBusinesslicenceend(String businesslicenceend) {
        this.businesslicenceend = businesslicenceend;
    }

    public String getBusinessscope() {
        return businessscope;
    }

    public void setBusinessscope(String businessscope) {
        this.businessscope = businessscope;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getBankbrachname() {
        return bankbrachname;
    }

    public void setBankbrachname(String bankbrachname) {
        this.bankbrachname = bankbrachname;
    }

    public String getBankbrachaccount() {
        return bankbrachaccount;
    }

    public void setBankbrachaccount(String bankbrachaccount) {
        this.bankbrachaccount = bankbrachaccount;
    }

    public String getBankprovince() {
        return bankprovince;
    }

    public void setBankprovince(String bankprovince) {
        this.bankprovince = bankprovince;
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity;
    }

    public String getBankcitysmall() {
        return bankcitysmall;
    }

    public void setBankcitysmall(String bankcitysmall) {
        this.bankcitysmall = bankcitysmall;
    }

    public String getBankorgnumpic() {
        return bankorgnumpic;
    }

    public void setBankorgnumpic(String bankorgnumpic) {
        this.bankorgnumpic = bankorgnumpic;
    }

    public String getAlipayname() {
        return alipayname;
    }

    public void setAlipayname(String alipayname) {
        this.alipayname = alipayname;
    }

    public String getAlipayno() {
        return alipayno;
    }

    public void setAlipayno(String alipayno) {
        this.alipayno = alipayno;
    }

    public String getWxname() {
        return wxname;
    }

    public void setWxname(String wxname) {
        this.wxname = wxname;
    }

    public String getWxno() {
        return wxno;
    }

    public void setWxno(String wxno) {
        this.wxno = wxno;
    }

    public String getTaxregistrationno() {
        return taxregistrationno;
    }

    public void setTaxregistrationno(String taxregistrationno) {
        this.taxregistrationno = taxregistrationno;
    }

    public String getTaxregistrationcertificate() {
        return taxregistrationcertificate;
    }

    public void setTaxregistrationcertificate(String taxregistrationcertificate) {
        this.taxregistrationcertificate = taxregistrationcertificate;
    }

    public String getTaxregistrationnopic() {
        return taxregistrationnopic;
    }

    public void setTaxregistrationnopic(String taxregistrationnopic) {
        this.taxregistrationnopic = taxregistrationnopic;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public int getValidate() {
        return validate;
    }

    public void setValidate(int validate) {
        this.validate = validate;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String[] getBusinesscategory() {
        return businesscategory;
    }

    public void setBusinesscategory(String[] businesscategory) {
        this.businesscategory = businesscategory;
    }

    public int getShopgradeid() {
        return shopgradeid;
    }

    public void setShopgradeid(int shopgradeid) {
        this.shopgradeid = shopgradeid;
    }

    public String getShopperiod() {
        return shopperiod;
    }

    public void setShopperiod(String shopperiod) {
        this.shopperiod = shopperiod;
    }

    public int getShopstate() {
        return shopstate;
    }

    public void setShopstate(int shopstate) {
        this.shopstate = shopstate;
    }

    public String getShopgrade() {
        return shopgrade;
    }

    public void setShopgrade(String shopgrade) {
        this.shopgrade = shopgrade;
    }

    public int getIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(int isrecommend) {
        this.isrecommend = isrecommend;
    }
}
