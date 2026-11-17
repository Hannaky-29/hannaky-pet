import java.io.IOException;

import com.hannakypet.modelo.Vacuna;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        // Captura de datos
        int idMascota = Integer.parseInt(request.getParameter("idMascota"));
        String nombre = request.getParameter("nombreVacuna");
        String fechaAplicacionTexto = request.getParameter("fechaAplicacion");
        String fechaProximaTexto = request.getParameter("fechaProximaDosis");

        // Validaciones
        if (idMascota <= 0 || nombre == null || nombre.isBlank()
                || fechaAplicacionTexto == null || fechaAplicacionTexto.isBlank()) {
            response.sendRedirect(destino + "?error=campos");
            return;
        }
        if (nombre.trim().length() > 120) {
            response.sendRedirect(destino + "?error=nombre");
            return;
        }

        // Conversión de fechas
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
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect(destino + "?error=guardar");
    }
}