package org.redrock.controller;

import net.sf.json.JSONObject;
import org.redrock.dao.InsertScore;
import org.redrock.utils.WechatUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "over", urlPatterns = "/over")
public class OverServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException
    , ServletException{
        String score = req.getParameter("score");
        String openid = (String) req.getSession().getAttribute("sessionId");
        List<Object> params = new ArrayList<>();
        params.add(score);
        params.add(openid);
        InsertScore is = new InsertScore();
        int status =is.InsertScore(params);
        JSONObject InfoJson = WechatUtil.referUserInfo(openid);

        if (status==200){
            JSONObject data = new JSONObject();
            data.put("nickname", InfoJson.getString("nickname"));
            data.put("rank", WechatUtil.getRank(openid));
            data.put("count", InfoJson.getString("count"));
            data.put("imgurl",InfoJson.getString("imgurl"));

            JSONObject response = new JSONObject();

            response.put("status",200);
            response.put("msg","success");
            response.put("data",data);
            System.out.println(response);

            resp.getOutputStream().write(resp.toString().getBytes());
            resp.getOutputStream().flush();
        }else {
            JSONObject response = new JSONObject();

            response.put("status",500);
            response.put("msg","success");

            resp.getOutputStream().write(resp.toString().getBytes());
            resp.getOutputStream().flush();
        }
    }
}
