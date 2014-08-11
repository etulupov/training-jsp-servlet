package com.noveogroup.tulupov.guestbook.servlet;

import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDao;
import com.noveogroup.tulupov.guestbook.database.service.GuestbookEntryService;
import com.noveogroup.tulupov.guestbook.database.service.ServiceException;
import com.noveogroup.tulupov.guestbook.filter.ServiceServletFilter;
import com.noveogroup.tulupov.guestbook.listener.SessionListener;
import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;
import com.noveogroup.tulupov.guestbook.model.Page;
import com.noveogroup.tulupov.guestbook.util.Config;
import com.noveogroup.tulupov.guestbook.util.PaginationUtils;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Main page.
 */
public class GuestbookServlet extends AbstractServlet {
    private static final Logger LOGGER = Logger.getLogger(GuestbookServlet.class);
    private static final String PARAM_FIRST_NAME = "first_name";
    private static final String PARAM_LAST_NAME = "last_name";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_MESSAGE = "message";
    private static final String PARAM_PAGE_INVALID = "page_invalid";

    private long getPageParam(final HttpServletRequest request) {
        final String param = request.getParameter("page");

        if (param != null) {
            try {
                final long value = Long.valueOf(param);
                if (value < 0) {
                    throw new RuntimeException(getString(request, PARAM_PAGE_INVALID));
                }
                return value;
            } catch (NumberFormatException e) {
                throw new RuntimeException(getString(request, PARAM_PAGE_INVALID));
            }
        }

        return 0;
    }


    private void fillForm(final HttpServletRequest request) {
        final String[] params = {PARAM_FIRST_NAME, PARAM_LAST_NAME, PARAM_EMAIL, PARAM_MESSAGE};
        final HttpSession session = request.getSession();
        for (String paramName : params) {
            request.setAttribute(paramName, session.getAttribute(paramName));
            session.removeAttribute(paramName);
        }

        for (String paramName : params) {
            final String value = (String) request.getAttribute(paramName);
            if (value == null) {
                request.setAttribute(paramName, "");
            }
        }
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws
            ServletException, IOException {
        super.doGet(request, response);
        fillForm(request);
        restoreAttributes(request);
        final GuestbookEntryService guestbookEntryService =
                (GuestbookEntryService) request.getAttribute(ServiceServletFilter.GUESTBOOK_ENTRY_SERVICE);
        try {
            final long total = guestbookEntryService.getCount();
            long page = 0;
            try {

                page = getPageParam(request);
            } catch (Exception e) {
                addErrorMessage(request, e.getMessage());
            }

            final Config config = Config.getInstance();

            final long offset = config.getPageLimit() * page;
            final List<Page> pages = PaginationUtils.paginize(total, config.getPageLimit(), page);

            final List<GuestbookEntry> entries = guestbookEntryService.getEntries(offset, config.getPageLimit());

            request.setAttribute("rootUrl", request.getContextPath());
            request.setAttribute("entries", entries);
            request.setAttribute("pages", pages);
            request.setAttribute("sessions", SessionListener.getActiveSessions());


            final RequestDispatcher requestDispatcher = request.getRequestDispatcher(uiControllerName);
            requestDispatcher.forward(request, response);

        } catch (ServiceException e) {
            addErrorMessage(request, e.getMessage());
        }
    }
}
