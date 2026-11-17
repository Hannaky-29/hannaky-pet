package com.hannakypet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.hannakypet.conexion.ConexionBD;
import com.hannakypet.modelo.Vacuna;

/**
 * DAO para la gestión de persistencia de Vacunas.
 * @author María Araceli Maluche Páez
 */
public class VacunaDAO {

    /** Consulta todas las vacunas registradas para mostrarlas en la interfaz web. */
    public List<Vacuna> consultarTodasLasVacunas() {
        List<Vacuna> lista = new ArrayList<>();
        String sql = "SELECT id_vacuna, id_mascota, nombre_vacuna, fecha_aplicacion, fecha_proxima_dosis "
                   + "FROM vacunas ORDER BY fecha_proxima_dosis ASC, id_vacuna DESC";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                Vacuna v = new Vacuna();
                v.setIdVacuna(rs.getInt("id_vacuna"));
                v.setIdMascota(rs.getInt("id_mascota"));
                v.setNombreVacuna(rs.getString("nombre_vacuna"));
                v.setFechaAplicacion(rs.getDate("fecha_aplicacion"));
                v.setFechaProximaDosis(rs.getDate("fecha_proxima_dosis"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar todas las vacunas: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public boolean registrarVacuna(Vacuna vacuna) {
        String sql = "INSERT INTO vacunas (id_mascota, nombre_vacuna, fecha_aplicacion, fecha_proxima_dosis) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vacuna.getIdMascota());
            stmt.setString(2, vacuna.getNombreVacuna());
            stmt.setDate(3, vacuna.getFechaAplicacion());
            
            // Si la fecha es nula, se envía null a la base de datos (la columna lo permite)
            if (vacuna.getFechaProximaDosis() != null) {
                stmt.setDate(4, vacuna.getFechaProximaDosis());
            } else {
                stmt.setNull(4, Types.DATE);
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar vacuna: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Vacuna> consultarVacunasPorMascota(int idMascota) {
        List<Vacuna> lista = new ArrayList<>();
        String sql = "SELECT * FROM vacunas WHERE id_mascota = ? ORDER BY fecha_proxima_dosis ASC";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMascota);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vacuna v = new Vacuna();
                v.setIdVacuna(rs.getInt("id_vacuna"));
                v.setIdMascota(rs.getInt("id_mascota"));
                v.setNombreVacuna(rs.getString("nombre_vacuna"));
                v.setFechaAplicacion(rs.getDate("fecha_aplicacion"));
                v.setFechaProximaDosis(rs.getDate("fecha_proxima_dosis"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar vacunas por mascota: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizarVacuna(Vacuna vacuna) {
        String sql = "UPDATE vacunas SET nombre_vacuna = ?, fecha_aplicacion = ?, fecha_proxima_dosis = ? WHERE id_vacuna = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vacuna.getNombreVacuna());
            stmt.setDate(2, vacuna.getFechaAplicacion());
            
            if (vacuna.getFechaProximaDosis() != null) {
                stmt.setDate(3, vacuna.getFechaProximaDosis());
            } else {
                stmt.setNull(3, Types.DATE);
            }
            
            stmt.setInt(4, vacuna.getIdVacuna());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar vacuna: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarVacuna(int idVacuna) {
        String sql = "DELETE FROM vacunas WHERE id_vacuna = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVacuna);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar vacuna: " + e.getMessage());
            return false;
        }
    }
}