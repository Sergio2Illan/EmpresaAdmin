/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.empresa.controller;


import com.empresa.dao.DAOFactory;
import com.empresa.dao.UsuariosDAO;
import com.empresa.model.Usuario;
import com.empresa.util.Conexion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String usuario = req.getParameter("username");
        String password = req.getParameter("password");

        Usuario user = getUserFronServer();
        if (user.getUsername().equals(usuario) && user.getPassword().equals(password)) {
            logger.info("Usuario admin");
            HttpSession sesion = req.getSession(true);
            sesion.setAttribute("usuario", usuario);
            resp.sendRedirect("empleado.jsp");
        } else {
            logger.error("Usuario o password incorrecto");
            resp.sendRedirect("index.jsp?error=1");
        }

    }

    private Usuario getUserFronServer(){
        try{
            UsuariosDAO usu = DAOFactory.getUsuario();
            Usuario user = usu.getUsuario(1);
            return user;
        } catch (Exception e) {
            logger.error("----> "+e.getLocalizedMessage());
            return null;
        }
    }
}
