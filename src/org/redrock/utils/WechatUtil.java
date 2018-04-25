package org.redrock.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WechatUtil {
    public static JSONObject getInfo(String url){
        try {
            URL getUrl = new URL(url);
            HttpURLConnection http = (HttpURLConnection) getUrl.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type",
                    "text/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);

            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] b = new byte[size];
            is.read(b);

            String message = new String(b, "UTF-8");
            JSONObject json = JSONObject.parseObject(message);
            return json;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public static Map<String, Object> getInfoMap(JSONObject json){
//        Map<String, Object> map =new HashMap<String, Object>(9);
//        map.put("country",json.get("country"));
//        map.put("province",json.get("province"));
//        map.put("city",json.get("city"));
//        map.put("openid",json.get("openid"));
//        map.put("sex", json.get("sex"));
//        map.put("nickname", json.get("nickname"));
//        map.put("headimgurl", json.get("headimgurl"));
//        map.put("language", json.get("language"));
//        map.put("privilege", json.get("privilege"));
//        return map;
//    }
    public static Map<String, Object> createUser(JSONObject json){
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("nickname", json.get("nickname"));
        fieldMap.put("country", json.get("country"));
        fieldMap.put("province", json.get("province"));
        fieldMap.put("city", json.get("city"));
        fieldMap.put("openid", json.get("openid"));
        fieldMap.put("imgurl", json.get("headimgurl"));
        return fieldMap;
    }

    public static JSONObject getAcess_TokenAndOpenId(String code){
        String url_token = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxf999d6b9d8eca6de&secret=82b64cc7c85b497652994d28bc1257b7&code=" + code + "&grant_type=authorization_code";
        JSONObject json = WechatUtil.getInfo(url_token);
        return json;
    }
    public static String getSimpleAccess(){
        String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxf999d6b9d8eca6de&secret=82b64cc7c85b497652994d28bc1257b7";
        com.alibaba.fastjson.JSONObject json = WechatUtil.getInfo(url);
        String Access_Token = json.getString("access_token");
        return Access_Token;
    }
    public static JSONObject getUserInfo(String openid, String access_token){
        String url_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        JSONObject userInfo = WechatUtil.getInfo(url_userinfo);
        return userInfo;
    }

    public static boolean verify(HttpSession session){
        String openid = (String) session.getAttribute("sessionId");
        if (openid ==null){
            return false;
        }else {
        String access_token = WechatUtil.getSimpleAccess();
        JSONObject jsonObject = WechatUtil.getUserInfo(openid, access_token);
        if (jsonObject ==null){
            return false;
        }else {
            return true;
           }
        }
    }

}
