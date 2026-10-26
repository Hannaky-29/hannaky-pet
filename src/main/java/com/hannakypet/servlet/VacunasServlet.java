package com.hannakypet.servlet;

import com.hannakypet.dao.VacunaDAO;
import com.hannakypet.modelo.Vacuna;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/vacunas")
public class VacunasServlet extends HttpServlet {
    private final VacunaDAO vacunaDAO = new VacunaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String accion = request.getParameter("accion");
        String filtro = request.getParameter("idMascota");

        if ("editar".equals(accion) && id != null) {
            try {
                int idVacuna = Integer.parseInt(id);
                for (Vacuna vacuna : vacunaDAO.consultarTodasLasVacunas()) {
                    if (vacuna.getIdVacuna() == idVacuna) {
                        request.setAttribute("editar", vacuna);
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "El identificador de la vacuna no es válido.");
            }
        }

        List<Vacuna> vacunas = vacunaDAO.consultarTodasLasVacunas();
        if (filtro != null && !filtro.isBlank()) {
            try {
                int idMascota = Integer.parseInt(filtro.trim());
                vacunas.removeIf(v -> v.getIdMascota() != idMascota);
                request.setAttribute("filtroMascota", filtro.trim());
            } catch (NumberFormatException e) {
                request.setAttribute("error", "El ID de mascota debe ser un número entero.");
            }
        }
        request.setAttribute("vacunas", vacunas);
        request.setAttribute("total", vacunas.size());
        request.getRequestDispatcher("/WEB-INF/vistas/vacunas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        String destino = request.getContextPath() + "/vacunas";

        try {
            if ("eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                response.sendRedirect(destino + (vacunaDAO.eliminarVacuna(id)
                        ? "?mensaje=eliminada" : "?error=eliminar"));
                return;
            }

            int idMascota = Integer.parseInt(request.getParameter("idMascota"));
            String nombre = request.getParameter("nombreVacuna");
            String fechaAplicacionTexto = request.getParameter("fechaAplicacion");
            String fechaProximaTexto = request.getParameter("fechaProximaDosis");

            if (idMascota <= 0 || nombre == null || nombre.isBlank()
                    || fechaAplicacionTexto == null || fechaAplicacionTexto.isBlank()) {
                response.sendRedirect(destino + "?error=campos");
                return;
            }
            if (nombre.trim().length() > 120) {
                response.sendRedirect(destino + "?error=nombre");
                return;
            }

            Date fechaAplicacion = Date.valueOf(fechaAplicacionTexto);
            Date fechaProxima = (fechaProximaTexto == null || fechaProximaTexto.isBlank())
                    ? null : Date.valueOf(fechaProximaTexto);
            Vacuna vacuna = new Vacuna(idMascota, nombre.trim(), fechaAplicacion, fechaProxima);
            boolean correcto;

            if ("actualizar".equals(accion)) {
                vacuna.setIdVacuna(Integer.parseInt(request.getParameter("idVacuna")));
                correcto = vacunaDAO.actualizarVacuna(vacuna);
            } else {
                correcto = vacunaDAO.registrarVacuna(vacuna);
            }
            response.sendRedirect(destino + (correcto
                    ? ("actualizar".equals(accion) ? "?mensaje=actualizada" : "?mensaje=registrada")
                    : "?error=guardar"));
        } catch (IllegalArgumentException e) {
            response.sendRedirect(destino + "?error=fecha");
        }
    }
}
