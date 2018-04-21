package org.redrock.controller;


import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.redrock.dao.StartDao;
import org.redrock.utils.WechatUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "game", urlPatterns = "/game")
public class GameServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException {
        String openid = (String) req.getSession().getAttribute("sessionId");
        System.out.println(openid);

        JSONObject InfoJson = WechatUtil.referUserInfo(openid);

        JSONObject data = new JSONObject();
        data.put("nickname", InfoJson.getString("nickname"));
        data.put("rank", WechatUtil.getRank(openid));
        data.put("share", InfoJson.getString("share"));
        data.put("count", InfoJson.getString("count"));
        data.put("imgurl",InfoJson.getString("imgurl"));

        JSONObject response = new JSONObject();

        response.put("status",200);
        response.put("msg","success");
        response.put("data",data);
        System.out.println(response);

        resp.getOutputStream().write(resp.toString().getBytes());
        resp.getOutputStream().flush();
    }
}
