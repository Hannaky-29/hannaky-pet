package com.hannakypet.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria para gestionar la conexión a la base de datos MariaDB.
 * Proyecto: Hannaky Pet - Sistema de Gestión Veterinaria.
 * Base de datos: hannaky_pet_db | Usuario: hannaky
 *
 * @author María Araceli Maluche Páez
 */
public class ConexionBD {

    // URL de conexión JDBC para MariaDB local
    private static final String URL = "jdbc:mariadb://localhost:3306/hannaky_pet_db";
    private static final String USUARIO = "hannaky";
    private static final String CONTRASENA = "172702";

    // Carga el driver una sola vez al inicializar la clase
    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MariaDB.");
            e.printStackTrace();
        }
    }

    /**
     * Obtiene una conexión activa a la base de datos.
     * @return Connection objeto de conexión JDBC.
     * @throws SQLException si falla la conexión.
     */
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    /**
     * Cierra una conexión de forma segura.
     * @param conn Conexión a cerrar.
     */
    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}