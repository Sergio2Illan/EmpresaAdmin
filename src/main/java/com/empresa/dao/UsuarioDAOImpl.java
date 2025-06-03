package com.empresa.dao;

import com.empresa.model.Usuario;
import com.empresa.util.Conexion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAOImpl implements UsuariosDAO{
    private static final Logger logger = LogManager.getLogger(UsuarioDAOImpl.class);


    public UsuarioDAOImpl(){

    }

    @Override
    public Usuario getUsuario(int id) {
        Usuario user = null;
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try(Connection con = Conexion.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)){
            logger.info("Ejecutamos la siguiente Query: " + sql);
            pst.setInt(1,id);
            try(ResultSet rs = pst.executeQuery()){
                if (rs.next()){
                    user = new Usuario();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                }else {
                    logger.error("No hay usuarios en la BBDD.");
                }
            }catch(Exception e){
                logger.error(">------> ResultSet: "+e.getLocalizedMessage());
            }

        } catch (Exception e) {
            logger.error(">------> Connection, PreparedStatement: "+e.getLocalizedMessage());
        }
        return user;
    }
}
