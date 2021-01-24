package com.example.GachiGram.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String uri = ((HttpServletRequest)servletRequest).getRequestURL().toString();
        String pageName = uri.substring(uri.lastIndexOf("/")+1);
        System.out.println(pageName);

        String middle = uri.substring(uri.lastIndexOf(".")+1);
        if (middle.equals("css") || middle.equals("png") || middle.equals("js") || middle.equals("jpg")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = ((HttpServletRequest)servletRequest).getSession(false);
        if (session == null ||  session.getAttribute("userId") == null) {
            if (pageName.equals("index.jsp") || pageName.equals("register.jsp") || pageName.equals("logServlet")) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            servletRequest.getRequestDispatcher("LogServlet").forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}