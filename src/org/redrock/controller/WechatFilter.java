package org.redrock.controller;


import com.alibaba.fastjson.JSONObject;
import org.redrock.bean.User;
import org.redrock.service.UserService;
import org.redrock.utils.WechatUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@WebFilter(filterName = "Filter", urlPatterns = {"/*"})
public class WechatFilter implements javax.servlet.Filter{
    public UserService userService ;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String code = request.getParameter("code");
        Enumeration<String> set = request.getParameterNames();

        if (session.getAttribute("access_token")==null){
            JSONObject AccessAndOpenIdJson = WechatUtil.getAcess_TokenAndOpenId(code);
            String access_token = AccessAndOpenIdJson.getString("access_token");
            String openid = AccessAndOpenIdJson.getString("openid");

            session.setAttribute("openid",openid);
            session.setAttribute("access_token",access_token);
//            String url_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
//            JSONObject userInfo = WechatUtil.getInfo(url_userinfo);
            session.setAttribute("msg","未登录");
//            Map<String, Object> beanMap = WechatUtil.createUser(userInfo);

//            System.out.println(userInfo.getString("openid"));
//            User user = userService.getUser(userInfo.getString("openid"));
//            if (user == null) {
//                userService.createUser(beanMap);
//            }
        }else {
//            String openid = (String) session.getAttribute("openid");
//            String access_token = (String)session.getAttribute("access_token");
            session.setAttribute("msg", "已登陆");
//            JSONObject json = WechatUtil.getUserInfo(openid, access_token);
        }
             filterChain.doFilter(request, response);
//            request.getRequestDispatcher("/views/start.jsp").forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
