package com.empresa.dao;

public class DAOFactory {

    public static EmpleadosDAO getEmpleado(){
        return new EmpleadosDAOImpl();
    }

    public static UsuariosDAO getUsuario(){
        return new UsuarioDAOImpl();
    }
}
