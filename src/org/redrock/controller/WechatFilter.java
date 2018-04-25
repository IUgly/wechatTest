package org.redrock.controller;


import com.alibaba.fastjson.JSONObject;
import org.redrock.service.UserService;
import org.redrock.utils.WechatUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "Filter", urlPatterns = {"/index.jsp"})
public class WechatFilter implements javax.servlet.Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    private UserService userService;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String code = request.getParameter("code");
        if (session.getAttribute("access_token")==null){
            JSONObject AccessAndOpenIdJson = WechatUtil.getAcess_TokenAndOpenId(code);
            String access_token = AccessAndOpenIdJson.getString("access_token");
            String openid = AccessAndOpenIdJson.getString("openid");

            session.setAttribute("sessionId",openid);
            session.setAttribute("access_token",access_token);
            String url_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
            JSONObject userInfo = WechatUtil.getInfo(url_userinfo);

            Map<String, Object> beanMap = WechatUtil.createUser(userInfo);
            userService.createUser(beanMap);
        }else {
            String openid = (String) session.getAttribute("sessionId");
            String access_token = (String)session.getAttribute("access_token");

            JSONObject json = WechatUtil.getUserInfo(openid, access_token);
            Map<String, Object> beanMap = WechatUtil.createUser(json);
            userService.createUser(beanMap);
        }
            System.out.println(session.getAttribute("sessionId"));
            filterChain.doFilter(request, response);
            request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
