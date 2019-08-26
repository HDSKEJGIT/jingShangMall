package com.hsh.dongzi.jinshang.model.login;

import java.io.Serializable;

/**
 * 用户信息bean
 */
public class UserBean implements Serializable {
    protected boolean logined;
    protected String address;// (string, optional): 地址 ,
    protected String alipay;// (string, optional): 支付宝账户 ,
    protected String availablelimit;// (number, optional): 买家可用授信额度 ,
    protected String balance;// (number, optional): 买家余额 ,
    protected String begainlastlogindate;// (string, optional),
    protected int billday;// (integer, optional): 卖家账单日 ,
    protected String billmoney;// (number, optional): 开票金额 ,
    protected BuyerCompanyBean buyerCompanyInfo;// (BuyerCompanyInfo, optional): 买家公司信息 ,
    protected String clerkname;// (string, optional): 业务员 ,
    protected boolean company;// (boolean, optional): 是否公司 ,
    protected String createdate;// (string, optional): 创建时间 ,
    protected String creditaccountday;// (string, optional): 授信账单日 ,
    protected double creditlimit;// (number, optional): 买家授信额度 ,
    protected String creditstarttoend;// (string, optional): 结算间隔 ,
    protected String deliveryaddress;// (string, optional): 地址 ,
    protected String deliveryregionid;// (string, optional),
    protected boolean disabled;// (boolean, optional): 是否停用 ,
    protected String email;// (string, optional): 邮箱 ,
    protected String endtime;// (string, optional),
    protected String favicon;// (string, optional): 头像照片地址 ,
    protected String faxes;//(string, optional): 传真 ,
    protected boolean flag;//(boolean, optional): 是否主账户 ,
    protected String from;//(string, optional): 登录地址源 seller：卖家 buyer:买家 ,
    protected int gradleid;//(integer, optional): 会员等级 ,
    protected String hobby;//(string, optional): 爱好 ,
    protected int id;//(integer, optional),
    protected int integrals;//(integer, optional): 会员积分 ,
    protected String invitecode;//(string, optional): 邀请码 ,
    protected String labelid;//(integer, optional): 会员标签 ,
    protected int lastday;//(integer, optional): 买家最后还款日 ,
    protected String lastlogindate;//(string, optional): 最后登陆时间 ,
    protected String loginType;//(string, optional): 登录方式 main:主帐号登录，sub:子帐号登录 ,
    protected int membersettingstate;//(integer, optional): 卖家减价率状态 ,
    protected String[] menu;//(object, optional): 权限 ,
    protected String mobile;//(string, optional): 手机号 ,
    protected String nick;//(string, optional): 昵称 ,
    protected String overlastlogindate;//(string, optional),
    protected int parentid;//(integer, optional): 父ID ,
    protected String parentname;//(string, optional): 主账户名 ,
    protected String paypassword;//(string, optional),
    protected String paypasswordsalt;//(string, optional),
    protected String photo;//(string, optional): 头像 ,
    protected String postcode;//(string, optional): 邮编 ,
    protected String qq;//(string, optional),
    protected String realname;//(string, optional): 真实姓名 ,
    protected String remark;// (string, optional): 备注 ,
    protected boolean reviewed;//(boolean, optional): 是否需要审核 ,
    protected SellerCompanyBean sellerCompanyInfo;// (SellerCompanyInfo, optional): 卖家公司信息 ,
    protected String sellerbanlance;//(number, optional): 卖家账户余额 ,
    protected String sellerfreezebanlance;//(number, optional): 卖家账户冻结余额 ,
    protected String sex;//(string, optional),
    protected String starttime;//(string, optional),
    protected String telephone;//(string, optional): 固定电话 ,
    protected int type;//(integer, optional): 类型：0-买家 1-卖家 ,
    protected String usedlimit;//(number, optional): 买家已用授信额度 ,
    protected String username;//(string, optional): 用户名 ,
    protected String way;//(string, optional): 注册途径 ,
    protected String waysalesman;//(string, optional): 介绍人 ,
    protected String wxpay;//(string, optional): 微信账户
    protected String province;
    protected String city;
    protected String citysmall;
    private String rebateballance;//返利券额度

    public String getRebateballance() {
        return rebateballance;
    }

    public void setRebateballance(String rebateballance) {
        this.rebateballance = rebateballance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(String lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword;
    }

    public String getPaypasswordsalt() {
        return paypasswordsalt;
    }

    public void setPaypasswordsalt(String paypasswordsalt) {
        this.paypasswordsalt = paypasswordsalt;
    }

    public boolean isCompany() {
        return company;
    }

    public void setCompany(boolean company) {
        this.company = company;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public int getGradleid() {
        return gradleid;
    }

    public void setGradleid(int gradleid) {
        this.gradleid = gradleid;
    }

    public String getDeliveryregionid() {
        return deliveryregionid;
    }

    public void setDeliveryregionid(String deliveryregionid) {
        this.deliveryregionid = deliveryregionid;
    }

    public String getDeliveryaddress() {
        return deliveryaddress;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress;
    }

    public int getIntegrals() {
        return integrals;
    }

    public void setIntegrals(int integrals) {
        this.integrals = integrals;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getWxpay() {
        return wxpay;
    }

    public void setWxpay(String wxpay) {
        this.wxpay = wxpay;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getWaysalesman() {
        return waysalesman;
    }

    public void setWaysalesman(String waysalesman) {
        this.waysalesman = waysalesman;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }

    public String getClerkname() {
        return clerkname;
    }

    public void setClerkname(String clerkname) {
        this.clerkname = clerkname;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String[] getMenu() {
        return menu;
    }

    public void setMenu(String[] menu) {
        this.menu = menu;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFaxes() {
        return faxes;
    }

    public void setFaxes(String faxes) {
        this.faxes = faxes;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public String getSellerbanlance() {
        return sellerbanlance;
    }

    public void setSellerbanlance(String sellerbanlance) {
        this.sellerbanlance = sellerbanlance;
    }

    public String getSellerfreezebanlance() {
        return sellerfreezebanlance;
    }

    public void setSellerfreezebanlance(String sellerfreezebanlance) {
        this.sellerfreezebanlance = sellerfreezebanlance;
    }

    public double getCreditlimit() {
        return creditlimit;
    }

    public void setCreditlimit(double creditlimit) {
        this.creditlimit = creditlimit;
    }

    public String getUsedlimit() {
        return usedlimit;
    }

    public void setUsedlimit(String usedlimit) {
        this.usedlimit = usedlimit;
    }

    public String getAvailablelimit() {
        return availablelimit;
    }

    public void setAvailablelimit(String availablelimit) {
        this.availablelimit = availablelimit;
    }

    public int getLastday() {
        return lastday;
    }

    public void setLastday(int lastday) {
        this.lastday = lastday;
    }

    public int getBillday() {
        return billday;
    }

    public void setBillday(int billday) {
        this.billday = billday;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public SellerCompanyBean getSellerCompanyInfo() {
        return sellerCompanyInfo;
    }

    public void setSellerCompanyInfo(SellerCompanyBean sellerCompanyInfo) {
        this.sellerCompanyInfo = sellerCompanyInfo;
    }

    public BuyerCompanyBean getBuyerCompanyInfo() {
        if (buyerCompanyInfo == null) {
            buyerCompanyInfo = new BuyerCompanyBean();
        }
        return buyerCompanyInfo;
    }

    public void setBuyerCompanyInfo(BuyerCompanyBean buyerCompanyInfo) {
        this.buyerCompanyInfo = buyerCompanyInfo;
    }

    public int getMembersettingstate() {
        return membersettingstate;
    }

    public void setMembersettingstate(int membersettingstate) {
        this.membersettingstate = membersettingstate;
    }

    public String getBillmoney() {
        return billmoney;
    }

    public void setBillmoney(String billmoney) {
        this.billmoney = billmoney;
    }

    public String getCreditaccountday() {
        return creditaccountday;
    }

    public void setCreditaccountday(String creditaccountday) {
        this.creditaccountday = creditaccountday;
    }

    public String getCreditstarttoend() {
        return creditstarttoend;
    }

    public void setCreditstarttoend(String creditstarttoend) {
        this.creditstarttoend = creditstarttoend;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getBegainlastlogindate() {
        return begainlastlogindate;
    }

    public void setBegainlastlogindate(String begainlastlogindate) {
        this.begainlastlogindate = begainlastlogindate;
    }

    public String getOverlastlogindate() {
        return overlastlogindate;
    }

    public void setOverlastlogindate(String overlastlogindate) {
        this.overlastlogindate = overlastlogindate;
    }

    public boolean isLogined() {
        return logined;
    }

    public void setLogined(boolean logined) {
        this.logined = logined;
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
}
