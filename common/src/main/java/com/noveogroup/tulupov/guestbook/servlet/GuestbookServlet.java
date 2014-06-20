package com.noveogroup.tulupov.guestbook.servlet;

import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDao;
import com.noveogroup.tulupov.guestbook.filter.DaoServletFilter;
import com.noveogroup.tulupov.guestbook.listener.SessionListener;
import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;
import com.noveogroup.tulupov.guestbook.model.Page;
import com.noveogroup.tulupov.guestbook.util.Config;
import com.noveogroup.tulupov.guestbook.util.Const;
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


public class GuestbookServlet extends BaseServlet {
    private static final Logger LOGGER = Logger.getLogger(GuestbookServlet.class);

    private long getPageParam(HttpServletRequest request) {
        String param = request.getParameter("page");

        if (param != null) {
            try {
                long value = Long.valueOf(param);
                if (value < 0) {
                    throw new RuntimeException(getString(request, "page_invalid"));
                }
                return value;
            } catch (NumberFormatException e) {
                throw new RuntimeException(getString(request, "page_invalid"));
            }
        }

        return 0;
    }


    private void fillForm(HttpServletRequest request) {
        String[] params = {"first_name", "last_name", "email", "message" };
        HttpSession session = request.getSession(true);
        for (String paramName : params) {
            request.setAttribute(paramName, session.getAttribute(paramName));
            session.removeAttribute(paramName);
        }

        for (String paramName : params) {
            String value = (String) request.getAttribute(paramName);
            if (value == null) {
                request.setAttribute(paramName, "");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        fillForm(request);
        restoreAttributes(request);
        GuestbookEntryDao guestbookEntryDao = (GuestbookEntryDao) request.getAttribute(DaoServletFilter.GUESTBOOK_ENTRY_DAO);
        try {

            long total = guestbookEntryDao.getCount();
            long page = 0;
            try {

                page = getPageParam(request);
            } catch (Exception e) {
                addErrorMessage(request, e.getMessage());
            }

            Config config = Config.getInstance();

            long offset = config.getPageLimit() * page;
            List<Page> pages = PaginationUtils.paginize(total, config.getPageLimit(), page);

            List<GuestbookEntry> entries = guestbookEntryDao.getEntries(offset, Const.ENTRIES_PER_PAGE);

            request.setAttribute("rootUrl", request.getContextPath());
            request.setAttribute("entries", entries);
            request.setAttribute("pages", pages);
            request.setAttribute("sessions", SessionListener.getActiveSessions());


            RequestDispatcher requestDispatcher = request.getRequestDispatcher(uiControllerName);
            requestDispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
