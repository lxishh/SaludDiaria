package com.example.saluddiaria;

public class Cita {
    private String id; // Añadir el campo id
    private String lugar;
    private String nombreDoc;
    private String especialidad;
    private String fecha;
    private String hora;

    // Constructor con id
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

    // Setter para id
    public void setId(String id) {
        this.id = id;
    }
}
