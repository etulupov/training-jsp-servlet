package com.noveogroup.tulupov.guestbook.servlet;

import com.noveogroup.tulupov.guestbook.database.dao.GuestbookEntryDao;
import com.noveogroup.tulupov.guestbook.filter.DaoServletFilter;
import com.noveogroup.tulupov.guestbook.model.GuestbookEntry;
import com.noveogroup.tulupov.guestbook.util.StringUtils;
import org.apache.commons.lang.StringEscapeUtils;
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
import java.nio.charset.Charset;
import java.util.Set;

@WebServlet(value = "/add", name = "AddEntryServlet")
public class AddEntryServlet extends BaseServlet {
    private static final Logger LOGGER = Logger.getLogger(AddEntryServlet.class);

    private String getParam(HttpServletRequest request, String paramName) throws Exception {
        String param = request.getParameter(paramName);

        return StringUtils.convertLineBreaksToHtml(StringUtils.escapeHtml(param));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void saveParameters(HttpServletRequest request) {
        String[] params = {"first_name", "last_name", "email", "message" };
        HttpSession session = request.getSession(true);
        for (String paramName : params) {
            session.setAttribute(paramName, request.getParameter(paramName));
        }
    }

    private boolean parseParameters(HttpServletRequest request) throws Exception {
        GuestbookEntry entry = new GuestbookEntry();

        entry.setFirstName(getParam(request, "first_name"));
        entry.setLastName(getParam(request, "last_name"));
        entry.setEmail(getParam(request, "email"));
        entry.setMessage(getParam(request, "message"));
        entry.setUserAgent(request.getHeader("User-Agent"));


        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        Set<ConstraintViolation<GuestbookEntry>> constraintViolations = validator
                .validate(entry);

        for (ConstraintViolation<GuestbookEntry> cv : constraintViolations) {
            LOGGER.error("Validation error: " + cv.getMessage());
            addErrorMessage(request, getString(request, cv.getMessage()));
        }

        if (constraintViolations.isEmpty()) {
            GuestbookEntryDao guestbookEntryDao = (GuestbookEntryDao) request.getAttribute(DaoServletFilter.GUESTBOOK_ENTRY_DAO);
            guestbookEntryDao.create(entry);
            return true;
        }

        return false;
    }


}
