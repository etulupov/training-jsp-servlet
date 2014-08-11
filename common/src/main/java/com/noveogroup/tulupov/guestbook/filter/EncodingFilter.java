package com.noveogroup.tulupov.guestbook.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Sets input/output encodings.
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = "*")
public class EncodingFilter implements Filter {
    private static final String UTF_8 = "UTF-8";

    public void destroy() {

    }

    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain) throws
            ServletException, IOException {
        req.setCharacterEncoding(UTF_8);
        resp.setCharacterEncoding(UTF_8);

        chain.doFilter(req, resp);
    }

    public void init(final FilterConfig config) throws ServletException {

    }

}
