package com.hannakypet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hannakypet.conexion.ConexionBD;
import com.hannakypet.modelo.Mascota;

/**
 * DAO para la gestión de persistencia de Mascotas.
 * @author María Araceli Maluche Páez
 */
public class MascotaDAO {
    
    public List<Mascota> consultarTodasLasMascotas() {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT id_mascota, nombre_mascota, especie, raza, edad FROM mascotas ORDER BY id_mascota DESC";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                Mascota m = new Mascota();
                m.setIdMascota(rs.getInt("id_mascota"));
                m.setNombreMascota(rs.getString("nombre_mascota"));
                m.setEspecie(rs.getString("especie"));
                m.setRaza(rs.getString("raza"));
                m.setEdad(rs.getInt("edad"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar mascotas: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public boolean registrarMascota(Mascota m) {
        String sql = "INSERT INTO mascotas (nombre_mascota, especie, raza, edad) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, m.getNombreMascota());
            stmt.setString(2, m.getEspecie());
            stmt.setString(3, m.getRaza());
            stmt.setInt(4, m.getEdad());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar mascota en la BD: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarMascota(int id) {
        String sql = "DELETE FROM mascotas WHERE id_mascota = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar mascota: " + e.getMessage());
            return false;
        }
    }
}