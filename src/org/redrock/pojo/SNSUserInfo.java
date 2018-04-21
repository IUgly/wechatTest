package org.redrock.pojo;

import java.util.List;

public class SNSUserInfo {
    // 用户标识
    private String openId;
    // 用户昵称
    private String nickname;
    // 性别（1是男性，2是女性，0是未知）
    private int sex;
    // 国家
    private String country;
    // 省份
    private String province;
    // 城市
    private String city;
    // 用户头像链接
    private String headimgurl;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    // 用户特权信息
    private String privilege;

    private String language;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getHeadImgUrl() {
        return headimgurl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headimgurl = headImgUrl;
    }

    public String getPrivilegeList() {
        return privilege;
    }

    public void setPrivilegeList(String privilegeList) {
        this.privilege = privilegeList;
    }
}