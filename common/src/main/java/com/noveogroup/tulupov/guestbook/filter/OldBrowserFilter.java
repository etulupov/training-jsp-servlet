package com.noveogroup.tulupov.guestbook.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Old browsers filter.
 */
@WebFilter(filterName = "OldBrowserFilter", urlPatterns = "*")
public class OldBrowserFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws
            ServletException, IOException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final String userAgent = httpServletRequest.getHeader("User-Agent");

        if (userAgent != null && userAgent.contains("MSIE")) {
            final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect("old.html");
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(final FilterConfig config) throws ServletException {

    }

}
