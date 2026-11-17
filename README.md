# Hannaky Pet - Sistema de Gestión Veterinaria

Sistema web desarrollado en **Java 17 (LTS)** bajo arquitectura **MVC (Modelo - Vista - Controlador)** utilizando **Jakarta EE (Servlets / JSP)**, **Apache Maven** y persistencia en **MariaDB / MySQL**.

---

## Módulos Implementados

1. **Módulo de Mascotas (`Mascota`, `MascotaDAO`, `MascotasServlet`, `mascotas.jsp`):**
   - Registro de pacientes veterinarios (nombre, especie, raza y edad).
   - Consulta general, edición y eliminación de perfiles de mascotas.
2. **Módulo de Control de Vacunas (`Vacuna`, `VacunaDAO`, `VacunasServlet`, `vacunas.jsp`):**
   - Registro de vacunas aplicadas y programación de próximas dosis.
   - **Selección dinámica de mascotas:** El formulario carga un menú desplegable directamente desde la base de datos para asociar la vacuna al paciente correcto.
3. **Módulo Stand-Alone Consola (`MainApp.java`):**
   - Interfaz por línea de comandos para operaciones CRUD sobre el calendario de vacunas.

---

## Configuración de Base de Datos (MariaDB / MySQL)

Ejecute el script ubicado en `database/hannaky_pet_db.sql`:

```sql
CREATE DATABASE IF NOT EXISTS hannaky_pet_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hannaky_pet_db;

CREATE TABLE IF NOT EXISTS mascotas (
    id_mascota INT AUTO_INCREMENT PRIMARY KEY,
    nombre_mascota VARCHAR(100) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    raza VARCHAR(80),
    edad INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS vacunas (
    id_vacuna INT AUTO_INCREMENT PRIMARY KEY,
    id_mascota INT NOT NULL,
    nombre_vacuna VARCHAR(120) NOT NULL,
    fecha_aplicacion DATE NOT NULL,
    fecha_proxima_dosis DATE NULL,
    CONSTRAINT fk_vacuna_mascota FOREIGN KEY (id_mascota) REFERENCES mascotas(id_mascota) ON DELETE CASCADE
);
