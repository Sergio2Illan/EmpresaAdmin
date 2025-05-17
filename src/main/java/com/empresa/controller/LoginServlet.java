/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.empresa.controller;


import com.empresa.util.Conexion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Conexion.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String usuario = req.getParameter("username");
        String password = req.getParameter("password");

        if ("admin".equals(usuario) && "admin".equals(password)) {
            logger.info("Usuario admin");
            HttpSession sesion = req.getSession(true);
            sesion.setAttribute("usuario", usuario);
            resp.sendRedirect("empleado.jsp");
        } else {
            logger.error("Usuario o password incorrecto");
            resp.sendRedirect("index.jsp?error=1");
        }
    }
}
