// Cita.java
package com.example.saluddiaria;

public class Cita {
    private String lugar;
    private String nombreDoc;
    private String especialidad;
    private String fecha;
    private String hora;

    public Cita(String lugar, String nombreDoc, String especialidad, String fecha, String hora) {
        this.lugar = lugar;
        this.nombreDoc = nombreDoc;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public String getNombreDoc() {
        return nombreDoc;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }
}
