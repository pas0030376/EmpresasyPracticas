package com.example.empresasypracticas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vicky on 01/02/2018.
 */

public class Empresa implements Serializable {
    //private int idEmpresa;
    private String nombre;
    private String tipo;
    private String telefono;
    private String personaDeContacto;
    private String correoElectronico;
    private String webpage;
   // private List<Llamada>Llamadas=new ArrayList<Llamada>();


    /*public Empresa(String nombre, String tipo, String telefono, String personaDeContacto, String correoElectronico, String webpage,List<Llamada>llamadas) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.telefono = telefono;
        this.personaDeContacto = personaDeContacto;
        this.correoElectronico = correoElectronico;
        this.webpage = webpage;
        Llamadas = llamadas;
    }*/

    public Empresa(String nombre, String tipo, String telefono, String personaDeContacto, String correoElectronico, String webpage) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.telefono = telefono;
        this.personaDeContacto = personaDeContacto;
        this.correoElectronico = correoElectronico;
        this.webpage = webpage;
    }

    public Empresa(){

    }

    /*public List<Llamada> getLlamadas() {
        return Llamadas;
    }

    public void setLlamadas(List<Llamada> llamadas) {
        Llamadas = llamadas;
    }*/

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
