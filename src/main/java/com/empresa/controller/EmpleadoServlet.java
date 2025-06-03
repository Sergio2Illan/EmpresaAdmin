package com.empresa.controller;

import com.empresa.dao.DAOFactory;
import com.empresa.dao.EmpleadosDAO;
import com.empresa.dao.EmpleadosDAOImpl;
import com.empresa.model.Empleado;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


public class EmpleadoServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(EmpleadoServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("accion");
        switch(action){
            case "alta":
                break;
            case "buscarPorId":
                // request.setAttribute("empleado", encontrado);
                break;
            case "buscarTodos":
                // request.setAttribute("listaEmpleados", lista);
                break;
            default:
                response.sendRedirect("empleado.jsp?error=1");
                break;
        }

        request.getRequestDispatcher("empleado.jsp").forward(request, response);


    }
}
