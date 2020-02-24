package com.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author zyb
 * @TIME 19-12-17
 **/
public class SessionFilter implements Filter {

    private String excludedPaths;
    private String [] excludedPathArray;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedPaths = filterConfig.getInitParameter("excludedPaths");
        if (excludedPaths!="" && excludedPaths!=null){
            excludedPathArray = excludedPaths.split(",");
        }
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String servletPath = request.getServletPath();
//        String pathInfo = request.getPathInfo();
//        String contextPath = request.getContextPath();
//        String requestURI = request.getRequestURI();
        if (servletPath.contains("login.jsp") ||servletPath.contains("login.do")|| session.getAttribute("user") != null || isStaticRes(servletPath)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            response.sendRedirect("/IMS/njwb/login.jsp");
            session.setAttribute("info","请先登录才进行操作");
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isStaticRes(String path){
        for (String s : excludedPathArray) {
            if (path.endsWith(s)){
                return true;
            }
        }
        return false;
    }
}
