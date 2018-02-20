package com.example.empresasypracticas;

/**
 * Created by Vicky on 01/02/2018.
 */

public class Empresa {
    private String nombre;
    private String tipo;
    private String telefono;
    private String correoElectronico;
    private String webpage;

    public Empresa(String nombre, String tipo, String telefono, String correoElectronico, String webpage) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.webpage = webpage;
    }

    public Empresa(){

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
