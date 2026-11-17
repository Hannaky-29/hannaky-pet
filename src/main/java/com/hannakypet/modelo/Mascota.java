package com.hannakypet.modelo;

/**
 * Clase modelo (POJO) que representa la entidad Mascota en Hannaky Pet.
 * @author María Araceli Maluche Páez
 * @version 1.0
 */
public class Mascota {
    private int idMascota;
    private String nombreMascota;
    private String especie;
    private String raza;
    private int edad;

    public Mascota() {}

    public Mascota(String nombreMascota, String especie, String raza, int edad) {
        this.nombreMascota = nombreMascota;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
    }

    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }

    public String getNombreMascota() { return nombreMascota; }
    public void setNombreMascota(String nombreMascota) { this.nombreMascota = nombreMascota; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
}