package com.hannakypet.servlet;

import java.io.IOException;
import java.util.List;

import com.hannakypet.dao.MascotaDAO;
import com.hannakypet.modelo.Mascota;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet controlador para el módulo de Mascotas.
 * Gestiona las peticiones GET (listar) y POST (registrar/eliminar).
 */
@WebServlet("/mascotas")
public class MascotasServlet extends HttpServlet {
    private final MascotaDAO mascotaDAO = new MascotaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configura la codificación para evitar problemas con tildes y eñes
        request.setCharacterEncoding("UTF-8");
        
        // Consulta todas las mascotas de la base de datos
        List<Mascota> mascotas = mascotaDAO.consultarTodasLasMascotas();
        
        // Envía la lista a la vista JSP
        request.setAttribute("mascotas", mascotas);
        request.setAttribute("total", mascotas.size());
        
        // Redirige a la vista
        request.getRequestDispatcher("/WEB-INF/vistas/mascotas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String accion = request.getParameter("accion");
        String destino = request.getContextPath() + "/mascotas";

        // Si la acción es eliminar, procesa la eliminación y redirige
        if ("eliminar".equals(accion)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                mascotaDAO.eliminarMascota(id);
            } catch (NumberFormatException e) {
                System.err.println("Error al eliminar: ID no válido.");
            }
            response.sendRedirect(destino);
            return;
        }

        // Si la acción es registrar, captura los datos del formulario
        try {
            String nombre = request.getParameter("nombreMascota");
            String especie = request.getParameter("especie");
            String raza = request.getParameter("raza");
            String edadStr = request.getParameter("edad");

            // Validación básica: los campos obligatorios no deben ser nulos o vacíos
            if (nombre != null && !nombre.trim().isEmpty() && 
                especie != null && !especie.trim().isEmpty() && 
                edadStr != null && !edadStr.trim().isEmpty()) {
                
                int edad = Integer.parseInt(edadStr);
                
                // Creamos el objeto Mascota y lo enviamos al DAO
                Mascota m = new Mascota(nombre.trim(), especie.trim(), 
                                        (raza != null) ? raza.trim() : "", edad);
                mascotaDAO.registrarMascota(m);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al registrar: La edad debe ser un número.");
        }

        // Redirige de vuelta al listado para que se vea la nueva mascota
        response.sendRedirect(destino);
    }
}