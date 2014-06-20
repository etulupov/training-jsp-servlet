package com.noveogroup.tulupov.guestbook.filter;


import com.j256.ormlite.support.ConnectionSource;
import com.noveogroup.tulupov.guestbook.database.dao.impl.GuestbookEntryDaoImpl;
import com.noveogroup.tulupov.guestbook.listener.ContextListener;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "DaoServletFilter", urlPatterns = "*")
public class DaoServletFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(DaoServletFilter.class);

    public static final String GUESTBOOK_ENTRY_DAO = "guestbook_entry_dao";

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        ConnectionSource connectionSource = (ConnectionSource) context.getAttribute(ContextListener.CONNECTION_SOURCE);

        try {
            req.setAttribute(GUESTBOOK_ENTRY_DAO, new GuestbookEntryDaoImpl(connectionSource));
        } catch (SQLException e) {
            LOGGER.error("Error", e);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
