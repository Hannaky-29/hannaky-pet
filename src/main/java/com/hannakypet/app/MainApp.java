package com.hannakypet.app;

import com.hannakypet.dao.VacunaDAO;
import com.hannakypet.modelo.Vacuna;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VacunaDAO vacunaDAO = new VacunaDAO();

        System.out.println("=========================================");
        System.out.println("    BIENVENIDO A HANNAKY PET - MÓDULO   ");
        System.out.println("=========================================");

        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar nueva vacuna (Inserción)");
            System.out.println("2. Consultar calendario de vacunas (Consulta)");
            System.out.println("3. Actualizar registro de vacuna (Actualización)");
            System.out.println("4. Eliminar registro de vacuna (Eliminación)");
            System.out.println("5. Ver Consejos de Nutrición y Salud");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    System.out.print("ID de la mascota: ");
                    int idMascota = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nombre de la vacuna (ej. Rabia): ");
                    String nombre = scanner.nextLine();
                    System.out.print("Fecha de aplicación (YYYY-MM-DD): ");
                    Date fAplicacion = Date.valueOf(scanner.nextLine());
                    System.out.print("Próxima dosis (YYYY-MM-DD): ");
                    Date fProxima = Date.valueOf(scanner.nextLine());

                    Vacuna nuevaVacuna = new Vacuna(idMascota, nombre, fAplicacion, fProxima);
                    if (vacunaDAO.registrarVacuna(nuevaVacuna)) {
                        System.out.println("¡Vacuna registrada con éxito en Hannaky Pet!");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese ID de la mascota para consultar su calendario: ");
                    int idConsulta = Integer.parseInt(scanner.nextLine());
                    List<Vacuna> vacunas = vacunaDAO.consultarVacunasPorMascota(idConsulta);
                    System.out.println("\n--- CALENDARIO DE VACUNACIÓN ---");
                    for (Vacuna v : vacunas) {
                        System.out.println(v);
                    }
                    break;

                case 3:
                    System.out.print("ID de la vacuna a actualizar: ");
                    int idAct = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nuevo nombre de la vacuna: ");
                    String nomAct = scanner.nextLine();
                    System.out.print("Nueva fecha de aplicación (YYYY-MM-DD): ");
                    Date fAplicAct = Date.valueOf(scanner.nextLine());
                    System.out.print("Nueva fecha de próxima dosis (YYYY-MM-DD): ");
                    Date fProxAct = Date.valueOf(scanner.nextLine());

                    Vacuna vacunaAct = new Vacuna(0, nomAct, fAplicAct, fProxAct);
                    vacunaAct.setIdVacuna(idAct);
                    if (vacunaDAO.actualizarVacuna(vacunaAct)) {
                        System.out.println("¡Registro actualizado correctamente!");
                    }
                    break;

                case 4:
                    System.out.print("ID de la vacuna a eliminar: ");
                    int idElim = Integer.parseInt(scanner.nextLine());
                    if (vacunaDAO.eliminarVacuna(idElim)) {
                        System.out.println("¡Registro eliminado correctamente!");
                    }
                    break;

                case 5:
                    System.out.println("\n--- CONSEJOS: Mantén al día las vacunas y desparasitaciones de tu mascota. ---\n");
                    break;

                case 6:
                    System.out.println("¡Saliendo de Hannaky Pet!");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
        scanner.close();
    }
}