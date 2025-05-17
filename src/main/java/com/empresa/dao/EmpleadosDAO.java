package com.empresa.dao;

import com.empresa.model.Empleado;

import java.util.List;

public interface EmpleadosDAO {

    void agregar(Empleado e);
    List<Empleado> listar();
    Empleado obtenerPorId(int id);
    void actualizar(Empleado e);
    void eliminar(int id);
}
