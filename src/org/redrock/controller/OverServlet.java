package org.redrock.controller;

import net.sf.json.JSONObject;
import org.redrock.bean.User;
import org.redrock.service.UserService;
import org.redrock.utils.StreamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "over", urlPatterns = "/over")
public class OverServlet extends HttpServlet {
    private final UserService userService;

    public OverServlet() {
        userService = new UserService();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException
    , ServletException{
//                UserService userService = new UserService();
                String score = req.getParameter("score");
                String openid = (String) req.getSession().getAttribute("openid");
                User user = this.userService.getUser(openid);

                Map<String, Object> scoreField = new HashMap<>();
                scoreField.put("score", score);
                userService.updateScore(openid, scoreField);
                JSONObject data = new JSONObject();
                data.put("nickname", user.getNickname());
                data.put("rank", userService.getSimpleRank(openid));
                data.put("count", user.getCount());
                data.put("imgurl",user.getImgurl());

                JSONObject response = new JSONObject();

                response.put("status",200);
                response.put("msg","success");
                response.put("data",data);
                System.out.println(response);

        StreamUtil.writeStream(resp.getOutputStream(), String.valueOf(response));

//            JSONObject response = new JSONObject();
//
//            response.put("status",500);
//            response.put("msg","success");
//
//            resp.getOutputStream().write(resp.toString().getBytes());
//            resp.getOutputStream().flush();
    }
}
