package com.example.demo.me;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@RestController
public class Test2 {

    public static final Logger log = LoggerFactory.getLogger(Test2.class);
    @Autowired
    Componet1 componet1;

    @GetMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        printDetil(request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        session.setAttribute("data", "孤傲苍狼");
        //获取session的Id
        String sessionId = session.getId();
        //判断session是不是新创建的
        if (session.isNew()) {
            response.getWriter().print("session创建成功，session的id是：" + sessionId);
        } else {
            response.getWriter().print("服务器已经存在该session了，session的id是：" + sessionId);
        }
//        return response;
    }

    private void printDetil(HttpServletRequest request) {
        System.out.println("getRequestedSessionId " + request.getRequestedSessionId());
        System.out.println("getAuthType " + request.getAuthType());
        System.out.println("getContextPath " + request.getContextPath());
        System.out.println("getServletContext " + request.getServletContext());
        System.out.println("getCookies " + request.getCookies());
        System.out.println("getSession " + request.getSession());
        System.out.println("getHeaderNames " + request.getHeaderNames());
        System.out.println("getMethod " + request.getMethod());
//        System.out.println("getParts " + request.getParts());
        System.out.println("getPathInfo " + request.getPathInfo());
        System.out.println("getPathTranslated " + request.getPathTranslated());
        System.out.println("getQueryString " + request.getQueryString());
        System.out.println("getRequestURI " + request.getRequestURI());
        System.out.println("getRequestURL " + request.getRequestURL());
        System.out.println("getServletPath " + request.getServletPath());
        System.out.println("getUserPrincipal " + request.getUserPrincipal());
        System.out.println("isRequestedSessionIdFromCookie " + request.isRequestedSessionIdFromCookie());
        System.out.println("isRequestedSessionIdFromCookie " + request.isRequestedSessionIdFromCookie());
        System.out.println("isRequestedSessionIdValid " + request.isRequestedSessionIdValid());
        System.out.println("getRemoteUser " + request.getRemoteUser());
        System.out.println("getRemoteAddr: " + request.getRemoteAddr());
        System.out.println("getRemoteHost: " + request.getRemoteHost());
        System.out.println("getRemotePort: " + request.getRemotePort());
        System.out.println("getLocalAddr: " + request.getLocalAddr());
        System.out.println("getLocalName: " + request.getLocalName());
        System.out.println("getLocalPort: " + request.getLocalPort());
        System.out.println();
    }

    @PostMapping("/test")
    public void test1(HttpServletRequest req, HttpServletResponse res, @RequestBody JSONObject jsonObject) throws IOException {
//        ServletInputStream inputStream = req.getInputStream();
//        int contentLength = req.getContentLength();
//        byte[] bytes = new byte[contentLength];
//        inputStream.read(bytes, 0, contentLength);
//        System.out.println(new String(bytes));

        System.out.println(jsonObject);
        BufferedReader reader = req.getReader();
        for (String str = ""; (str = reader.readLine()) != null; ) {
            System.out.print(str);
        }

        String name = req.getParameter("name");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().println("{\"dong\":\"dong\"}");
    }

    @GetMapping("/test2")
    public void test2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        //将数据存储到session中
        session.setAttribute("data", "孤傲苍狼");
        //获取session的Id
        String sessionId = session.getId();
//        Cookie cookie = new Cookie("JSESSIONID", "zzzzzzzzzzzzzzzzzzzzzzzzzzz");
////设置cookie的有效路径
//        cookie.setPath(request.getContextPath());
//        response.addCookie(cookie);
        //判断session是不是新创建的
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if (session.isNew()) {
            response.getWriter().print("session创建成功，session的id是：" + sessionId);
        } else {
            response.getWriter().print("服务器已经存在该session了，session的id是：" + sessionId);
        }
    }


    @GetMapping("/test3")
    public void test3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        ArrayList<Cookie> arrayList = new ArrayList();
        Collections.addAll(arrayList,request.getCookies());

        arrayList.forEach(cookie -> {
            System.out.println(cookie.getName() + ":" + cookie.getValue());
        });
        Cookie cookie = new Cookie("dong", "de");
        response.addCookie(cookie);
    }


}
