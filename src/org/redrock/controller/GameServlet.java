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

@WebServlet(name = "game", urlPatterns = "/game")
public class GameServlet extends HttpServlet{
    private UserService userService;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException {
        String openid = (String) req.getSession().getAttribute("sessionId");
        System.out.println(openid);

        User user = userService.getUser(openid);

        JSONObject data = new JSONObject();
        data.put("nickname", user.getNickname());
        data.put("rank", userService.getSimpleRank(openid));
        data.put("share", user.getShare());
        data.put("count", user.getCount());
        data.put("imgurl", user.getImgurl());

        JSONObject response = new JSONObject();

        response.put("status",200);
        response.put("msg","success");
        response.put("data",data);
        System.out.println(response);

        StreamUtil.writeStream(resp.getOutputStream(), String.valueOf(response));
    }
}
