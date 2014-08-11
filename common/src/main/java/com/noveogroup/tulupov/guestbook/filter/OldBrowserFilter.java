package com.noveogroup.tulupov.guestbook.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Old browsers filter.
 */
public class OldBrowserFilter implements Filter {
    private static final String OLD_PAGE = "old.html";
    private String regexp;

    public void init(final FilterConfig config) throws ServletException {
        regexp = config.getInitParameter("regexp");
    }

    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws
            ServletException, IOException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final String userAgent = httpServletRequest.getHeader("User-Agent");
        final String path = httpServletRequest.getRequestURI();

        if (!path.contains(OLD_PAGE) && userAgent != null && userAgent.matches(regexp)) {
            final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect(OLD_PAGE);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }

}
