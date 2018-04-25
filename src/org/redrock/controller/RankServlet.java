package org.redrock.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.redrock.bean.User;
import org.redrock.service.UserService;
import org.redrock.utils.JsonUtil;
import org.redrock.utils.StreamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "rank", urlPatterns = "/rank")
public class RankServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        String openid = (String) req.getSession().getAttribute("sessionId");
        User userRank = userService.getSimpleRank(openid);
        User user = userService.getUser(openid);

        com.alibaba.fastjson.JSONObject SimpleUser = new com.alibaba.fastjson.JSONObject();
        SimpleUser.put("nickname", user.getNickname());
        SimpleUser.put("imgurl", user.getImgurl());
        SimpleUser.put("rank", userRank.getRowNo());

        List<User> UserRank = userService.getRankList();
        String[] filterStrings = new String[]{"count","number","country","city","province",
                "sex","openid","privilege","share","language","rowNo"};
        JSONArray jsonArray = JsonUtil.BeanToJson(UserRank, filterStrings);
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        Iterator it = jsonArray.iterator();
        int i = 0;
        while (it.hasNext()){
            jsonObject.put(Integer.toString(i+1), it.next());
            i++;
        }
        jsonObject.put("my", SimpleUser);

        com.alibaba.fastjson.JSONObject data = new com.alibaba.fastjson.JSONObject();
        data.put("data",jsonObject);

        JSONObject response = new JSONObject();
        response.put("status",200);
        response.put("msg","success");
        response.put("data",data);

        try {
            StreamUtil.writeStream(resp.getOutputStream(), String.valueOf(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(
//                            resp.getOutputStream(), "UTF-8"
//                    )
//            );
//            writer.write(String.valueOf(response));
//            writer.flush();
//            writer.close();
//            System.out.println(response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException
            ,IOException{
        doGet(request,response);
    }
}
