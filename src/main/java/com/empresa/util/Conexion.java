package com.empresa.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class Conexion {
    private static final Logger logger = LogManager.getLogger(Conexion.class);
    private static final HikariDataSource dataSource;

    static {
        logger.info("Inicializando el pool de conexiones...");
        try {
            HikariConfig config = new HikariConfig("/db.properties");
            dataSource = new HikariDataSource(config);
            logger.info("Pool de conexiones inicializado correctamente.");
        } catch (Exception e) {
            logger.error("Error al inicializar el pool de conexiones: " + e.getMessage());
            throw new RuntimeException("No se pudo inicializar el pool de conexiones.", e);
        }
    }

    private Conexion() {}

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            logger.error("Error al obtener la conexión: " + e.getMessage());
            return null;
        }
    }

    // No llames a esto salvo que cierres la aplicación por completo
    public static void closeDataSource() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            logger.info("DataSource cerrado correctamente.");
        }
    }
}
