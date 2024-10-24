package com.example.saluddiaria.model;

public class Medicamento {
    String nombre, tipo, intensidad, frecuencia, hora_am, hora_pm;
    public Medicamento(){}

    public Medicamento(String nombre, String tipo, String intensidad, String frecuencia, String hora_am, String hora_pm) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.intensidad = intensidad;
        this.frecuencia = frecuencia;
        this.hora_am = hora_am;
        this.hora_pm = hora_pm;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(String intensidad) {
        this.intensidad = intensidad;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getHora_am() {
        return hora_am;
    }

    public void setHora_am(String hora_am) {
        this.hora_am = hora_am;
    }

    public String getHora_pm() {
        return hora_pm;
    }

    public void setHora_pm(String hora_pm) {
        this.hora_pm = hora_pm;
    }
}
