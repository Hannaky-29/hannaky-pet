
package com.hannakypet.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = obtenerVariable(
        "DB_URL",
        "jdbc:mysql://localhost:3306/hannaky_pet_db?serverTimezone=UTC"
    );

    private static final String USUARIO = obtenerVariable("DB_USER", null);
    private static final String CONTRASENA = obtenerVariable("DB_PASSWORD", null);

    private static String obtenerVariable(String nombre, String valorPorDefecto) {
        String valor = System.getenv(nombre);

        if (valor == null || valor.isBlank()) {
            if (valorPorDefecto != null) {
                return valorPorDefecto;
            }

            throw new IllegalStateException(
                "Falta configurar la variable de entorno " + nombre
            );
        }

        return valor;
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
