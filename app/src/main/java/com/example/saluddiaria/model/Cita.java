package com.example.saluddiaria.model;

public class Cita {
    private String id; // Añadir el campo id
    private String lugar;
    private String nombreDoc;
    private String especialidad;
    private String fecha;
    private String hora;

    // Constructor vacío requerido
    public Cita() {}

    // Constructor con todos los parámetros
    public Cita(String id, String lugar, String nombreDoc, String especialidad, String fecha, String hora) {
        this.id = id;
        this.lugar = lugar;
        this.nombreDoc = nombreDoc;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters
    public String getId() {
        return id;
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

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
