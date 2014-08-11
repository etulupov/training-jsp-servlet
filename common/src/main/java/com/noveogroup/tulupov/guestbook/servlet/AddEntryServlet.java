package com.noveogroup.tulupov.guestbook.servlet;

import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDao;
import com.noveogroup.tulupov.guestbook.filter.DaoServletFilter;
import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;
import com.noveogroup.tulupov.guestbook.util.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Set;

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

        try {
            if (parseParameters(request)) {
                setSuccess(request, getString(request, "success_add"));
            } else {
                saveParameters(request);
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

    private boolean parseParameters(final HttpServletRequest request) throws Exception {
        final GuestbookEntry entry = new GuestbookEntry();

        entry.setFirstName(getParam(request, PARAM_FIRST_NAME));
        entry.setLastName(getParam(request, PARAM_LAST_NAME));
        entry.setEmail(getParam(request, PARAM_EMAIL));
        entry.setMessage(getParam(request, PARAM_MESSAGE));
        entry.setUserAgent(request.getHeader("User-Agent"));


        final ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        final Validator validator = vf.getValidator();

        final Set<ConstraintViolation<GuestbookEntry>> constraintViolations = validator
                .validate(entry);

        for (ConstraintViolation<GuestbookEntry> cv : constraintViolations) {
            LOGGER.error("Validation error: " + cv.getMessage());
            addErrorMessage(request, getString(request, cv.getMessage()));
        }

        if (constraintViolations.isEmpty()) {
            final GuestbookEntryDao guestbookEntryDao =
                    (GuestbookEntryDao) request.getAttribute(DaoServletFilter.GUESTBOOK_ENTRY_DAO);
            guestbookEntryDao.create(entry);
            return true;
        }

        return false;
    }


}
