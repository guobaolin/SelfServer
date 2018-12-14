package com.gbl.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by guobaolin on 2018/11/14.
 */
//@WebFilter(filterName="customFilter", urlPatterns = "/*")
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("调用Filter的init()方法");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("调用Filter的doFilter()方法1");

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("调用Filter的doFilter()方法2");
    }

    @Override
    public void destroy() {
        System.out.println("调用Filter的destroy()方法");
    }
}
