package com.noveogroup.tulupov.guestbook.servlet;

import com.noveogroup.tulupov.guestbook.database.service.GuestbookEntryService;
import com.noveogroup.tulupov.guestbook.database.service.ServiceException;
import com.noveogroup.tulupov.guestbook.database.service.ValidationException;
import com.noveogroup.tulupov.guestbook.database.service.impl.GuestbookEntryServiceImpl;
import com.noveogroup.tulupov.guestbook.model.Form;
import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;
import com.noveogroup.tulupov.guestbook.util.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import java.io.IOException;

/**
 * Adds new guestbook entry.
 */
@WebServlet(value = "/add", name = "AddEntryServlet")
public class AddEntryServlet extends AbstractServlet {
    private static final Logger LOGGER = Logger.getLogger(AddEntryServlet.class);
    private static final String PARAM_FIRST_NAME = "first_name";
    private static final String PARAM_LAST_NAME = "last_name";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_MESSAGE = "message";

    private String getParam(final HttpServletRequest request, final String paramName) throws Exception {
        final String param = request.getParameter(paramName);

        return StringUtils.convertLineBreaksToHtml(StringUtils.escapeHtml(param));
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws
            ServletException, IOException {
        super.doPost(request, response);

        final Form form = new Form();
        request.setAttribute(FORM, form);

        try {
            final GuestbookEntry entry = parseParameters(request);

            final GuestbookEntryService guestbookEntryService = GuestbookEntryServiceImpl.getInstance();

            try {
                guestbookEntryService.create(entry);
                setSuccess(request, getString(request, "success_add"));
            } catch (ValidationException e) {
                saveParameters(request);
                for (ConstraintViolation<GuestbookEntry> cv : e.getResults()) {
                    LOGGER.error("Validation error: " + cv.getMessage());
                    form.put(cv.getPropertyPath().toString(), getString(request, cv.getMessage()));
                }
            } catch (ServiceException e) {
                saveParameters(request);
                addErrorMessage(request, getString(request, e.getMessage()));
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);

            saveParameters(request);
            addErrorMessage(request, e.getMessage());
        }

        saveAttributes(request);
        response.sendRedirect("index");
    }

    private void saveParameters(final HttpServletRequest request) {
        final String[] params = {PARAM_FIRST_NAME, PARAM_LAST_NAME, PARAM_EMAIL, PARAM_MESSAGE};
        final HttpSession session = request.getSession(true);
        for (String paramName : params) {
            session.setAttribute(paramName, request.getParameter(paramName));
        }
    }

    private GuestbookEntry parseParameters(final HttpServletRequest request) throws Exception {
        final GuestbookEntry entry = new GuestbookEntry();

        entry.setFirstName(getParam(request, PARAM_FIRST_NAME));
        entry.setLastName(getParam(request, PARAM_LAST_NAME));
        entry.setEmail(getParam(request, PARAM_EMAIL));
        entry.setMessage(getParam(request, PARAM_MESSAGE));
        entry.setUserAgent(request.getHeader("User-Agent"));

        return entry;
    }


}
