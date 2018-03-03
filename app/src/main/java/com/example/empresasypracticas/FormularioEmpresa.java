package com.example.empresasypracticas;

/**
 * Created by Vicky on 01/03/2018.
 * Preguntas a hacer a los estudiantes sobre las empresas
 */

public class FormularioEmpresa {
    private String snom;
    private String scognom;
    private String emailStudent;
    private String telefono;
    private String TipoPracticas;
    private String Empresadepracticas;
    private String cicle;
    private String curso;
    private String relacioncompañeros; //Como calificarias la relacion con los compañeros de trabajo
    private String relaciontutorE; //Como calificarias la relacion con el tutor de trabajo
    private String aprendizaje; //Valoracion del aprendizaje en la empresa
    private String Repetir; // Trabajarias con esta empresa
    private String ValoracionGlobalS;
    private String Scomentarios;

    public String getCicle() {
        return cicle;
    }

    public void setCicle(String cicle) {
        this.cicle = cicle;
    }

    public String getSnom() {
        return snom;
    }

    public void setSnom(String snom) {
        this.snom = snom;
    }

    public String getScognom() {
        return scognom;
    }

    public void setScognom(String scognom) {
        this.scognom = scognom;
    }

    public String getEmailStudent() {
        return emailStudent;
    }

    public void setEmailStudent(String emailStudent) {
        this.emailStudent = emailStudent;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoPracticas() {
        return TipoPracticas;
    }

    public void setTipoPracticas(String tipoPracticas) {
        TipoPracticas = tipoPracticas;
    }

    public String getEmpresadepracticas() {
        return Empresadepracticas;
    }

    public void setEmpresadepracticas(String empresadepracticas) {
        Empresadepracticas = empresadepracticas;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getRelacioncompañeros() {
        return relacioncompañeros;
    }

    public void setRelacioncompañeros(String relacioncompañeros) {
        this.relacioncompañeros = relacioncompañeros;
    }

    public String getRelaciontutorE() {
        return relaciontutorE;
    }

    public void setRelaciontutorE(String relaciontutorE) {
        this.relaciontutorE = relaciontutorE;
    }

    public String getAprendizaje() {
        return aprendizaje;
    }

    public void setAprendizaje(String aprendizaje) {
        this.aprendizaje = aprendizaje;
    }

    public String getRepetir() {
        return Repetir;
    }

    public void setRepetir(String repetir) {
        Repetir = repetir;
    }

    public String getValoracionGlobalS() {
        return ValoracionGlobalS;
    }

    public void setValoracionGlobalS(String valoracionGlobalS) {
        ValoracionGlobalS = valoracionGlobalS;
    }

    public String getScomentarios() {
        return Scomentarios;
    }

    public void setScomentarios(String scomentarios) {
        Scomentarios = scomentarios;
    }
}
}
