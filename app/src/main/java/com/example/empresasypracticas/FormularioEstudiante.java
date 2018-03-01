package com.example.empresasypracticas;

/**
 * Created by Vicky on 01/03/2018.
 * Preguntas a hacer a las empresas sobre los estudiantes
 */

public class FormularioEstudiante {
    private String Tutornom;
    private String Tutorcognom;
    private String EmpresaA;
    private String TutorEmail;
    private String EstudentNIE;
    private String EstudenEmail;
    private String EstudentFullname;
    private String FormacionInicial; //Satisfaccion con la formacion inicial del alumno
    private String ValoracionGlobal; //Como califacaria la experiencia global;
    private String ValoracionEstudiante; //Como valoraria al estudiante;
    private String RepitirColaboracion; //¿Repitiria la colaboracion con este estudiante;
    private String Comentarios; //Comentarios extras;
    private String MotivoFin; //¿Porque terminaron las practicas?  Despido, fin convenio, otros;

    public String getMotivoFin() {
        return MotivoFin;
    }

    public void setMotivoFin(String motivoFin) {
        MotivoFin = motivoFin;
    }

    public String getTutornom() {
        return Tutornom;
    }

    public void setTutornom(String tutornom) {
        Tutornom = tutornom;
    }

    public String getTutorcognom() {
        return Tutorcognom;
    }

    public void setTutorcognom(String tutorcognom) {
        Tutorcognom = tutorcognom;
    }

    public String getEmpresaA() {
        return EmpresaA;
    }

    public void setEmpresaA(String empresaA) {
        EmpresaA = empresaA;
    }

    public String getTutorEmail() {
        return TutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        TutorEmail = tutorEmail;
    }

    public String getEstudentNIE() {
        return EstudentNIE;
    }

    public void setEstudentNIE(String estudentNIE) {
        EstudentNIE = estudentNIE;
    }

    public String getEstudenEmail() {
        return EstudenEmail;
    }

    public void setEstudenEmail(String estudenEmail) {
        EstudenEmail = estudenEmail;
    }

    public String getEstudentFullname() {
        return EstudentFullname;
    }

    public void setEstudentFullname(String estudentFullname) {
        EstudentFullname = estudentFullname;
    }

    public String getFormacionInicial() {
        return FormacionInicial;
    }

    public void setFormacionInicial(String formacionInicial) {
        FormacionInicial = formacionInicial;
    }

    public String getValoracionGlobal() {
        return ValoracionGlobal;
    }

    public void setValoracionGlobal(String valoracionGlobal) {
        ValoracionGlobal = valoracionGlobal;
    }

    public String getValoracionEstudiante() {
        return ValoracionEstudiante;
    }

    public void setValoracionEstudiante(String valoracionEstudiante) {
        ValoracionEstudiante = valoracionEstudiante;
    }

    public String getRepitirColaboracion() {
        return RepitirColaboracion;
    }

    public void setRepitirColaboracion(String repitirColaboracion) {
        RepitirColaboracion = repitirColaboracion;
    }

    public String getComentarios() {
        return Comentarios;
    }

    public void setComentarios(String comentarios) {
        Comentarios = comentarios;
    }
}
