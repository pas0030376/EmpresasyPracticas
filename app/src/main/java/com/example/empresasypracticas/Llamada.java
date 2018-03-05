package com.example.empresasypracticas;

import java.io.Serializable;

public class Llamada implements Serializable {
    //private int id;
    private String nombreEmpresa;//new
    private String fechaLlamada;
    private String horaLLamada;
    private String motivoLlamada;
    private String personaContactada;

    /*public Llamada(String fechaLlamada, String horaLLamada, String motivoLlamada, String personaContactada) {
        this.fechaLlamada = fechaLlamada;
        this.horaLLamada = horaLLamada;
        this.motivoLlamada = motivoLlamada;
        this.personaContactada = personaContactada;
    }*/

    public Llamada(String nombreEmpresa, String fechaLlamada, String horaLLamada, String motivoLlamada, String personaContactada) {
        this.nombreEmpresa = nombreEmpresa;
        this.fechaLlamada = fechaLlamada;
        this.horaLLamada = horaLLamada;
        this.motivoLlamada = motivoLlamada;
        this.personaContactada = personaContactada;
    }

    public Llamada() {}
/*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getFechaLlamada() {
        return fechaLlamada;
    }

    public String getHoraLLamada() {
        return horaLLamada;
    }

    public String getMotivoLlamada() {
        return motivoLlamada;
    }


    public String getPersonaContactada() {
        return personaContactada;
    }


    public void setFechaLlamada(String fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }

    public void setPersonaContactada(String personaContactada) {
        this.personaContactada = personaContactada;
    }

    public void setHoraLLamada(String horaLLamada) {
        this.horaLLamada = horaLLamada;
    }

    public void setMotivoLlamada(String motivoLlamada) {
        this.motivoLlamada = motivoLlamada;
    }


}
