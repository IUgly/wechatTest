package org.redrock.test;



import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.redrock.bean.Rank;
import org.redrock.bean.User;
import org.redrock.service.UserService;
import org.redrock.bean.User;
import org.redrock.utils.JsonUtil;

import javax.enterprise.inject.Model;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RankTest {

    private UserService userService;

    public static void main(String[] args) {
        UserService userService = new UserService();
        String openid = "ov33805O1uXHKO2IiqmDwv0PVbp0";
        User user = userService.getUser(openid);
        Map<String, Object> scoreField = new HashMap<>();
        scoreField.put("score", 500);
        userService.updateScore(openid, scoreField);

    }
}
