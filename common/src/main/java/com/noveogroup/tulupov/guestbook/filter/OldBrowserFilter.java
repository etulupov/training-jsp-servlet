package com.noveogroup.tulupov.guestbook.filter;


import com.j256.ormlite.support.ConnectionSource;
import com.noveogroup.tulupov.guestbook.database.dao.impl.GuestbookEntryDaoImpl;
import com.noveogroup.tulupov.guestbook.listener.ContextListener;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "OldBrowserFilter", urlPatterns = "*")
public class OldBrowserFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String userAgent = httpServletRequest.getHeader("User-Agent");

        if (userAgent != null && userAgent.contains("MSIE")) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect("old.html");
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
