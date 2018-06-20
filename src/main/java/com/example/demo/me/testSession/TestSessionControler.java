package com.example.demo.me.testSession;


import com.example.demo.me.Componet1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/servlet")
public class TestSessionControler {

    public static final Logger log = LoggerFactory.getLogger(TestSessionControler.class);
    @Autowired
    Componet1 componet1;

    @GetMapping("/IndexServlet")
    public void test2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //创建Session
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        out.write("本网站有如下书：<br/>");
        Map<String, Book> all = DB.getAll();
        all.forEach((s, book) -> {
            String url = request.getContextPath() + "/servlet/BuyServlet?id=" + book.getId();
            System.out.println("old url: " + url);
            //response. encodeURL(java.lang.String url)用于对表单action和超链接的url地址进行重写
            url = response.encodeURL(url);//将超链接的url地址进行重写
            System.out.println("new url: " + url);
            out.println(book.getName() + "   <a href='" + url + "'>购买</a><br/>");
        });
    }

    @GetMapping("/BuyServlet")
    public void test3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Book book = DB.getAll().get(id);  //得到用户想买的书
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        List<Book> list = (List) session.getAttribute("list");  //得到用户用于保存所有书的容器
        if (list == null) {
            list = new ArrayList<Book>();
            session.setAttribute("list", list);
        }
        list.add(book);
        //response. encodeRedirectURL(java.lang.String url)用于对sendRedirect方法后的url地址进行重写
        String url = response.encodeRedirectURL(request.getContextPath() + "/servlet/ListCartServlet");
        System.out.println(url);
        response.sendRedirect(url);
    }

    @GetMapping("/ListCartServlet")
    public void test4(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        List<Book> list = (List) session.getAttribute("list");
        if (list == null || list.size() == 0) {
            out.write("对不起，您还没有购买任何商品!!");
            return;
        }

        //显示用户买过的商品
        out.write("您买过如下商品:<br>");
        for (Book book : list) {
            out.write(book.getName() + "<br/>");
        }
    }

}
