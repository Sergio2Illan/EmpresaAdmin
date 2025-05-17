package com.empresa.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class Conexion {
    private static final Logger logger = LogManager.getLogger(Conexion.class);
    private static HikariDataSource dataSource;
    private static HikariConfig config;
    private static Connection con;

    private Conexion(){}

    public static Connection getConnection(){
        try{
            if(dataSource != null || !dataSource.getConnection().isClosed()){
                logger.info("Leyendo del dataSource.properties...");
                config = new HikariConfig("src/main/resources/db.properties");
                logger.info("Datasource cargado con exito.");
                dataSource = new HikariDataSource(config);
                con = dataSource.getConnection();
                logger.info("Conexion con la BBDD creada con exito.");
            }
        }catch (Exception e){
            logger.error("Error >---> : "+e.getLocalizedMessage());
        }

        return con;
    }

    public static void closeConnection(){
        try{
            if(dataSource != null && !dataSource.isClosed()){
                dataSource.close();
                logger.info("Conexion cerrada con exito.");
            }
        }catch (Exception e){
            logger.error("Error >---> : "+e.getLocalizedMessage());
        }
    }


}
