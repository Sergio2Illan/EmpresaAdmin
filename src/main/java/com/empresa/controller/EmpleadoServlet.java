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
        int ok = 0;
        int id = 0;
        switch(action){

            case "alta":
                String nombre = request.getParameter("nombre");
                String email = request.getParameter("email");
                String puesto = request.getParameter("puesto");
                double salario = Double.parseDouble(request.getParameter("salario"));
                ok = altaEmpleado(nombre, email, puesto, salario);
                response.sendRedirect("empleado.jsp?success="+ok);
                break;
            case "baja":
                id= Integer.parseInt(request.getParameter("id"));
                ok = DAOFactory.getEmpleado().eliminar(id);
                setVariables(id, ok);
                response.sendRedirect("empleado.jsp?success="+ok);
                break;
            case "modificar":

                break;
            case "buscarPorId":
                id = Integer.parseInt(request.getParameter("id"));
                Empleado emple = DAOFactory.getEmpleado().obtenerPorId(id);
                setVariables(id, ok);
                request.setAttribute("empleado", emple);
                response.sendRedirect("empleado.jsp");
                break;
            case "buscarTodos":
                List<Empleado> lista = DAOFactory.getEmpleado().listar();
                logger.info("Número de empleados listados: "+lista.stream().count());
                request.setAttribute("listaEmpleados", lista);
                response.sendRedirect("empleado.jsp");
                break;
            default:
                response.sendRedirect("empleado.jsp?error=1");
                break;
        }

    }

    private static void setVariables(int id, int ok) {
        id = 0;
        ok = 0;
    }

    private int altaEmpleado(String nombre, String email, String puesto, double salario) {
        Empleado emple = new Empleado();
        emple.setEmail(email);
        emple.setNombre(nombre);
        emple.setPuesto(puesto);
        emple.setSalario(salario);
        EmpleadosDAO empleDao = DAOFactory.getEmpleado();
        return empleDao.agregar(emple);
    }
}
