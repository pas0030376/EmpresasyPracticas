package com.example.empresasypracticas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vicky on 01/02/2018.
 */

public class Empresa implements Serializable {
    private String nombre;
    private String tipo;
    private String telefono;
    private String personaDeContacto;
    private String correoElectronico;
    private String webpage;
    private String SectorEscolar;


    public Empresa(String nombre, String tipo, String telefono, String personaDeContacto, String correoElectronico, String webpage,String SectorEscolar) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.telefono = telefono;
        this.personaDeContacto = personaDeContacto;
        this.correoElectronico = correoElectronico;
        this.webpage = webpage;
        this.SectorEscolar = SectorEscolar;
    }

    public Empresa(){

    }

    public String getSectorEscolar() {
        return SectorEscolar;
    }

    public void setSectorEscolar(String sectorEscolar) {
        SectorEscolar = sectorEscolar;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPersonaDeContacto() {
        return personaDeContacto;
    }

    public void setPersonaDeContacto(String personaDeContacto) {
        this.personaDeContacto = personaDeContacto;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }
}
