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


public abstract class BaseServlet extends HttpServlet {
    public static final String LANGUAGE = "language";
    public static final String LANGUAGE_MAP = "lang";
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    public static final String DEFAULT_LANGUAGE = "en";

    protected String uiControllerName;

    public void init(ServletConfig servletConfig) throws ServletException {
        this.uiControllerName = servletConfig.getInitParameter("ui");
    }

    protected void addErrorMessage(HttpServletRequest request, String message) {
        List<String> errors = (List<String>) request.getAttribute(ERROR);
        if (errors == null) {
            errors = new ArrayList<>();
            request.setAttribute(ERROR, errors);
        }
        errors.add(message);
    }

    private void initLocalization(HttpServletRequest request) {
        ResourceBundle langBundle = getResourceBundle(request);
        request.setAttribute(LANGUAGE_MAP, langBundle);
    }

    protected void saveAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String[] names = {ERROR, SUCCESS};
        for (String name : names) {
            session.setAttribute(name, request.getAttribute(name));
        }
    }

    protected void restoreAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        String[] names = {ERROR, SUCCESS};
        for (String name : names) {
            request.setAttribute(name, session.getAttribute(name));
            session.removeAttribute(name);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initLocalization(request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initLocalization(request);
    }

    protected void setSuccess(HttpServletRequest request, String message) {
        request.setAttribute(SUCCESS, message);
    }

    private String getLanguage(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        String language = request.getParameter("language");

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


    protected ResourceBundle getResourceBundle(HttpServletRequest request) {
        return ResourceBundle.getBundle("locale/strings", new Locale(getLanguage(request)), new UTF8Control());
    }

    protected String getString(HttpServletRequest request, String key) {
        return getResourceBundle(request).getString(key);
    }


}
