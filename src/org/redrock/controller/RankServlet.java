package org.redrock.controller;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.redrock.dao.RankDao;
import org.redrock.utils.WechatUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

@WebServlet(name = "rank", urlPatterns = "/rank")
public class RankServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        String openid = (String) req.getSession().getAttribute("sessionId");
        net.sf.json.JSONObject Info = WechatUtil.referUserInfo(openid);
        String rank = WechatUtil.getRank(openid);
        JSONObject my = WechatUtil.referUserInfo(openid);
        JSONObject myInfo = new JSONObject();
        myInfo.put("nickname",my.getString("nickname"));
        myInfo.put("imgurl",my.getString("imgurl"));
        myInfo.put("rank", new Integer(rank.substring(0,1)));

        List<Map<String, Object>> list = RankDao.rankList();
        System.out.println(list);
        JSONObject rankList = new JSONObject();

        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()){
            rankList.put(Integer.toString(i+1), it.next());
            i++;
        }
        rankList.put("my", myInfo);

        Gson gson = new Gson();
        String data = gson.toJson(rankList);
        System.out.println(data);

        JSONObject response = new JSONObject();
        response.put("status",200);
        response.put("msg","success");
        response.put("data",data);

        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            resp.getOutputStream(), "UTF-8"
                    )
            );
            writer.write(String.valueOf(response));
            writer.flush();
            writer.close();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException
            ,IOException{
        doGet(request,response);
    }
}
