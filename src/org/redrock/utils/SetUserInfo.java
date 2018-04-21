package org.redrock.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.redrock.pojo.SNSUserInfo;

import java.util.List;

public class SetUserInfo {


    public SetUserInfo(JSONObject jsonObject) {
    }

    public static void setUserInfo(com.alibaba.fastjson.JSONObject jsonObject){
        if (null != jsonObject) {
            try {
                SNSUserInfo snsUserInfo = new org.redrock.pojo.SNSUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(Integer.parseInt(jsonObject.getString("sex")));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                snsUserInfo.setPrivilegeList(String.valueOf(JSONArray.toList(JSONArray.fromObject(jsonObject.getJSONArray("privilege")), List.class)));
            } catch (Exception e) {
                int errorCode = Integer.parseInt(jsonObject.getString("errcode"));
                String errorMsg = jsonObject.getString("errmsg");
            }
    }
   }
}
