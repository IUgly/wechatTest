import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.redrock.bean.User;
import org.redrock.service.UserService;
import org.redrock.utils.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
//        Map<String, Object> beanMap = new HashMap<>();
//        beanMap.put("country", "china");
//        beanMap.put("province", "chongqing");
//        beanMap.put("imgurl", "www.kuangjunlin.top");
//        beanMap.put("openid", "xxxxxxx");
//        beanMap.put("nickname", "IUgly");

        UserService userService = new UserService();
        User user = userService.getUser("xxx");
        System.out.println(user);
//        userService.createUser(beanMap);
//        User user = userService.getUser("xxxxxxx");
//        List<User> list = new ArrayList<User>();
//        list.add(user);
//
//        Gson gson = new Gson();
//        String json2 = gson.toJson(list);
//
//        System.out.println("json"+json2);
//        User user2 = JsonUtil.fromJson(String.valueOf(json2), user.getClass());
//        List<User> list2 = new ArrayList<User>();
//        list2.add(user2);
//        String json3 = gson.toJson(list2);
//        System.out.println("json2"+json3);

    }
}
