package com.empresa.dao;

import com.empresa.model.Empleado;
import com.empresa.util.Conexion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadosDAOImpl implements EmpleadosDAO {

    private static final Logger logger = LogManager.getLogger(EmpleadosDAOImpl.class);


     //private static EmpleadosDAOImpl instance;

    public EmpleadosDAOImpl() {
        // Constructor privado para evitar instanciación externa
    }

    /*public static EmpleadosDAOImpl getInstance() {
        if (instance == null) {
            instance = new EmpleadosDAOImpl();
        }
        return instance;
    }*/

    @Override
    public int agregar(Empleado e) {
        String sql = "INSERT INTO empleado (nombre, correo, departamento, salario) VALUES (?, ?, ?, ?)";
        int isOK = 0;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            logger.info("Ejecutamos la siguiente Query: " + sql);
            pstmt.setString(1, e.getNombre());
            pstmt.setString(2, e.getEmail());
            pstmt.setString(3, e.getPuesto());
            pstmt.setDouble(4, e.getSalario());
            isOK = pstmt.executeUpdate();

        } catch (SQLException ex) {
            logger.error(">------>"+ex.getLocalizedMessage());

        }
        return isOK;
    }

    @Override
    public List<Empleado> listar() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            logger.info("Ejecutamos la siguiente Query: " + sql);
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setEmail(rs.getString("correo"));
                e.setPuesto(rs.getString("departamento"));
                e.setSalario(rs.getDouble("salario"));
                empleados.add(e);
            }
        } catch (Exception e) {
            logger.error(">------>"+e.getLocalizedMessage());
        }
        return empleados;
    }

    @Override
    public Empleado obtenerPorId(int id) {
        Empleado e = null;
        String sql = "SELECT * FROM empleado WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            logger.info("Ejecutamos la siguiente Query: " + sql);
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    e = new Empleado();
                    e.setId(rs.getInt("id"));
                    e.setNombre(rs.getString("nombre"));
                    e.setEmail(rs.getString("correo"));
                    e.setPuesto(rs.getString("departamento"));
                    e.setSalario(rs.getDouble("salario"));
                }
            }
        } catch (Exception ex) {
            logger.error(">------>"+ex.getLocalizedMessage());
        }
        return e;
    }

    @Override
    public void actualizar(Empleado e) {
        String sql = "UPDATE empleado SET nombre=?, correo=?, departamento=?, salario=? WHERE id=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            logger.info("Ejecutamos la siguiente Query: " + sql);
            stmt.setString(1, e.getNombre());
            stmt.setString(2, e.getEmail());
            stmt.setString(3, e.getPuesto());
            stmt.setDouble(3, e.getSalario());
            stmt.setInt(5, e.getId());
            stmt.executeUpdate();

        } catch (Exception ex) {
            logger.error(">------>"+ex.getLocalizedMessage());
        }
    }

    @Override
    public int eliminar(int id) {
        int ok = 0;
        String sql = "DELETE FROM empleado WHERE id=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            logger.info("Ejecutamos la siguiente Query: " + sql);
            stmt.setInt(1, id);
            ok = stmt.executeUpdate();

        } catch (Exception ex) {
            logger.error(">------>"+ex.getLocalizedMessage());
        }
        return ok;
    }
}
