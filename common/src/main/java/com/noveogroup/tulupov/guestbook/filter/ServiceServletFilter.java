package com.noveogroup.tulupov.guestbook.filter;


import com.j256.ormlite.support.ConnectionSource;
import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDao;
import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDaoFactory;
import com.noveogroup.tulupov.guestbook.database.service.GuestbookEntryService;
import com.noveogroup.tulupov.guestbook.database.service.GuestbookEntryServiceFactory;
import com.noveogroup.tulupov.guestbook.listener.ContextListener;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Inits servlet context.
 */
@WebFilter(filterName = "ServiceServletFilter", urlPatterns = "*")
public class ServiceServletFilter implements Filter {
    public static final String GUESTBOOK_ENTRY_SERVICE = "guestbook_entry_service";

    private static final Logger LOGGER = Logger.getLogger(ServiceServletFilter.class);

    public void destroy() {

    }

    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain) throws
            ServletException, IOException {
        final ServletContext context = req.getServletContext();
        final ConnectionSource connectionSource =
                (ConnectionSource) context.getAttribute(ContextListener.CONNECTION_SOURCE);

        final GuestbookEntryDao guestbookEntryDao = GuestbookEntryDaoFactory.getInstance().get(connectionSource);
        final GuestbookEntryService guestbookEntryService = GuestbookEntryServiceFactory.getInstance().get(guestbookEntryDao);

        req.setAttribute(GUESTBOOK_ENTRY_SERVICE, guestbookEntryService);

        chain.doFilter(req, resp);
    }

    public void init(final FilterConfig config) throws ServletException {

    }

}
