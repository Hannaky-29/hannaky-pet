package com.hannakypet.modelo;

import java.sql.Date;

public class Vacuna {
    private int idVacuna;
    private int idMascota;
    private String nombreVacuna;
    private Date fechaAplicacion;
    private Date fechaProximaDosis;

    public Vacuna() {}

    public Vacuna(int idMascota, String nombreVacuna, Date fechaAplicacion, Date fechaProximaDosis) {
        this.idMascota = idMascota;
        this.nombreVacuna = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.fechaProximaDosis = fechaProximaDosis;
    }

    public int getIdVacuna() { return idVacuna; }
    public void setIdVacuna(int idVacuna) { this.idVacuna = idVacuna; }

    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }

    public String getNombreVacuna() { return nombreVacuna; }
    public void setNombreVacuna(String nombreVacuna) { this.nombreVacuna = nombreVacuna; }

    public Date getFechaAplicacion() { return fechaAplicacion; }
    public void setFechaAplicacion(Date fechaAplicacion) { this.fechaAplicacion = fechaAplicacion; }

    public Date getFechaProximaDosis() { return fechaProximaDosis; }
    public void setFechaProximaDosis(Date fechaProximaDosis) { this.fechaProximaDosis = fechaProximaDosis; }

    @Override
    public String toString() {
        return "Vacuna [ID=" + idVacuna + ", Nombre=" + nombreVacuna + 
               ", Aplicada=" + fechaAplicacion + ", Próxima Dosis=" + fechaProximaDosis + "]";
    }
}