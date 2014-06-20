package com.noveogroup.tulupov.guestbook.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LocalizationServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(uiControllerName);
        requestDispatcher.forward(request, response);
    }
}
