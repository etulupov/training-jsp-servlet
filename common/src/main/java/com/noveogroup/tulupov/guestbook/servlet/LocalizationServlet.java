package com.noveogroup.tulupov.guestbook.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Localize servlet.
 */
public class LocalizationServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws
            ServletException, IOException {
        super.doGet(request, response);

        final RequestDispatcher requestDispatcher = request.getRequestDispatcher(uiControllerName);
        requestDispatcher.forward(request, response);
    }
}
