package com.empresa.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


public class LogoutServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Invalida la sesión actual
        HttpSession session = req.getSession(false); // false para evitar crear una nueva
        if (session != null) {
            logger.info("Se invalida la session activa.");
            session.invalidate();
        }

        // Redirige al login
        logger.info("Se redirige a index.jsp");
        resp.sendRedirect("index.jsp");
    }
}
