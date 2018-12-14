package com.gbl.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by guobaolin on 2018/11/14.
 */
//@WebServlet(name = "customServlet", loadOnStartup = 1, urlPatterns = "/aaa")
public class CustomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("你好啊！-- 调用HttpServlet的doGet()方法");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("调用HttpServlet的init()方法");
    }

    @Override
    public void destroy() {
        System.out.println("调用HttpServlet的destroy()方法");
    }
}
