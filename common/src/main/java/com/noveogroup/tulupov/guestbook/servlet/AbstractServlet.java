package com.noveogroup.tulupov.guestbook.servlet;

import com.noveogroup.tulupov.guestbook.util.UTF8Control;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Base servlet.
 */
public abstract class AbstractServlet extends HttpServlet {
    public static final String LANGUAGE = "language";
    public static final String LANGUAGE_MAP = "lang";
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    public static final String FORM = "form";
    public static final String DEFAULT_LANGUAGE = "en";

    protected String uiControllerName;

    public void init(final ServletConfig servletConfig) throws ServletException {
        this.uiControllerName = servletConfig.getInitParameter("ui");
    }

    protected void addErrorMessage(final HttpServletRequest request, final String message) {
        List<String> errors = (List<String>) request.getAttribute(ERROR);
        if (errors == null) {
            errors = new ArrayList<>();
            request.setAttribute(ERROR, errors);
        }
        errors.add(message);
    }

    private void initLocalization(final HttpServletRequest request) {
        final ResourceBundle langBundle = getResourceBundle(request);
        request.setAttribute(LANGUAGE_MAP, langBundle);
    }

    protected void saveAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(true);
        final String[] names = {ERROR, SUCCESS, FORM};
        for (String name : names) {
            session.setAttribute(name, request.getAttribute(name));
        }
    }

    protected void restoreAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(true);

        final String[] names = {ERROR, SUCCESS, FORM};
        for (String name : names) {
            request.setAttribute(name, session.getAttribute(name));
            session.removeAttribute(name);
        }

    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws
            ServletException, IOException {
        initLocalization(request);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws
            ServletException, IOException {
        initLocalization(request);
    }

    protected void setSuccess(final HttpServletRequest request, final String message) {
        request.setAttribute(SUCCESS, message);
    }

    private String getLanguage(final HttpServletRequest request) {
        final HttpSession session = request.getSession(true);

        String language = request.getParameter(LANGUAGE);

        if (language != null) {
            session.setAttribute(LANGUAGE, language);
        } else {
            language = (String) session.getAttribute(LANGUAGE);
            if (language == null) {
                language = DEFAULT_LANGUAGE;
                session.setAttribute(LANGUAGE, language);
            }
        }

        return language;
    }


    protected ResourceBundle getResourceBundle(final HttpServletRequest request) {
        return ResourceBundle.getBundle("locale/strings", new Locale(getLanguage(request)), new UTF8Control());
    }

    protected String getString(final HttpServletRequest request, final String key) {
        return getResourceBundle(request).getString(key);
    }


}
