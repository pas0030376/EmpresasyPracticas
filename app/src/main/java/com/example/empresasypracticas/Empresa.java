package com.example.empresasypracticas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vicky on 01/02/2018.
 */

public class Empresa implements Serializable {
    private String nombre;
    private String cif;
    private String adreca;
    private String municipi;
    private String cp;
    private String telefon;
    private String nombrepersonaDeContacto;
    private String dnipersonaDeContacto;
    private String cargopersonaDeContacto;
    private String telefonPersonaDeContacto;
    private String emailPersonaDeContacto;
    private String nombreTutor;
    private String dniTutor;
    private String cargoTutor;
    private String telefonTutor;
    private String emailTutor;
    private String SectorEscolar;
    private String homologada;

    public Empresa(){}

    public Empresa(String nombre, String cif, String adreca, String municipi, String cp, String telefon, String nombrepersonaDeContacto, String dnipersonaDeContacto, String cargopersonaDeContacto, String telefonPersonaDeContacto, String emailPersonaDeContacto, String nombreTutor, String dniTutor, String cargoTutor, String telefonTutor, String emailTutor, String sectorEscolar, String homologada) {
        this.nombre = nombre;
        this.cif = cif;
        this.adreca = adreca;
        this.municipi = municipi;
        this.cp = cp;
        this.telefon = telefon;
        this.nombrepersonaDeContacto = nombrepersonaDeContacto;
        this.dnipersonaDeContacto = dnipersonaDeContacto;
        this.cargopersonaDeContacto = cargopersonaDeContacto;
        this.telefonPersonaDeContacto = telefonPersonaDeContacto;
        this.emailPersonaDeContacto = emailPersonaDeContacto;
        this.nombreTutor = nombreTutor;
        this.dniTutor = dniTutor;
        this.cargoTutor = cargoTutor;
        this.telefonTutor = telefonTutor;
        this.emailTutor = emailTutor;
        SectorEscolar = sectorEscolar;
        this.homologada = homologada;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCif() {
        return cif;
    }

    public String getAdreca() {
        return adreca;
    }

    public String getMunicipi() {
        return municipi;
    }

    public String getCp() {
        return cp;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getNombrepersonaDeContacto() {
        return nombrepersonaDeContacto;
    }

    public String getDnipersonaDeContacto() {
        return dnipersonaDeContacto;
    }

    public String getCargopersonaDeContacto() {
        return cargopersonaDeContacto;
    }

    public String getTelefonPersonaDeContacto() {
        return telefonPersonaDeContacto;
    }

    public String getEmailPersonaDeContacto() {
        return emailPersonaDeContacto;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public String getDniTutor() {
        return dniTutor;
    }

    public String getCargoTutor() {
        return cargoTutor;
    }

    public String getTelefonTutor() {
        return telefonTutor;
    }

    public String getEmailTutor() {
        return emailTutor;
    }

    public String getSectorEscolar() {
        return SectorEscolar;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public void setMunicipi(String municipi) {
        this.municipi = municipi;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setNombrepersonaDeContacto(String nombrepersonaDeContacto) {
        this.nombrepersonaDeContacto = nombrepersonaDeContacto;
    }

    public void setDnipersonaDeContacto(String dnipersonaDeContacto) {
        this.dnipersonaDeContacto = dnipersonaDeContacto;
    }

    public void setCargopersonaDeContacto(String cargopersonaDeContacto) {
        this.cargopersonaDeContacto = cargopersonaDeContacto;
    }

    public void setTelefonPersonaDeContacto(String telefonPersonaDeContacto) {
        this.telefonPersonaDeContacto = telefonPersonaDeContacto;
    }

    public void setEmailPersonaDeContacto(String emailPersonaDeContacto) {
        this.emailPersonaDeContacto = emailPersonaDeContacto;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public void setDniTutor(String dniTutor) {
        this.dniTutor = dniTutor;
    }

    public void setCargoTutor(String cargoTutor) {
        this.cargoTutor = cargoTutor;
    }

    public void setTelefonTutor(String telefonTutor) {
        this.telefonTutor = telefonTutor;
    }

    public void setEmailTutor(String emailTutor) {
        this.emailTutor = emailTutor;
    }

    public void setSectorEscolar(String sectorEscolar) {
        SectorEscolar = sectorEscolar;
    }

    public String getHomologada() {
        return homologada;
    }

    public void setHomologada(String homologada) {
        this.homologada = homologada;
    }
}


