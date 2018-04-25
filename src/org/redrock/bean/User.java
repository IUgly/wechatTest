package org.redrock.bean;

/**
 * @author ugly
 */
public class User {
    String country;
    String province;
    String city;
    String sex;
    String imgurl;
    String openid;
    String language;
    String privilege;
    int score;
    String share;
    int count;
    String nickname;
    Long rank;
    Long rowNo;

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setRowNo(Long rowNo) {
        this.rowNo = rowNo;
    }

    public Long getRowNo() {
        return rowNo;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getRank() {
        return rank;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getSex() {
        return sex;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getOpenid() {
        return openid;
    }

    public String getLanguage() {
        return language;
    }

    public String getPrivilege() {
        return privilege;
    }

    public String getShare() {
        return share;
    }

    public int getCount() {
        return count;
    }

    public String getNickname() {
        return nickname;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
