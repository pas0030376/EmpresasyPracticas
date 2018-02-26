package com.example.empresasypracticas;
import java.io.Serializable;
import java.util.List;

public class Estudiante implements Serializable {

    private String nom;
    private String cognom;
    private String tipo_practicas;
    private String inicio_practicas;
    private String fin_practicas;
    private String correo;
    private String curso;
    private int telefono;
    private String empresa;
    private List<String> tareas;
    private String motivosFinalizacion;
    private String comentarios;
    private String valoracion;
    private String NIE;

    public Estudiante(){

    }

    public Estudiante(String nom, String cognom, String tipo_practicas, String inicio_practicas, String fin_practicas, String correo, String curso, int telefono, String empresa,
                      List<String> tareas, String motivosFinalizacion, String comentarios, String valoracion, String NIE) {
        this.nom = nom;
        this.cognom = cognom;
        this.tipo_practicas = tipo_practicas;
        this.inicio_practicas = inicio_practicas;
        this.fin_practicas = fin_practicas;
        this.correo = correo;
        this.curso = curso;
        this.telefono = telefono;
        this.empresa = empresa;
        this.tareas = tareas;
        this.motivosFinalizacion = motivosFinalizacion;
        this.comentarios = comentarios;
        this.valoracion = valoracion;
        this.NIE = NIE;
    }

    public Estudiante(String nom, String cognom, String NIE, String tipo_practicas, String inicio_practicas, String fin_practicas, String correo, String curso, int telefono, String empresa,
                      List<String> tareas) {
        this.nom = nom;
        this.cognom = cognom;
        this.tipo_practicas = tipo_practicas;
        this.inicio_practicas = inicio_practicas;
        this.fin_practicas = fin_practicas;
        this.correo = correo;
        this.curso = curso;
        this.telefono = telefono;
        this.empresa = empresa;
        this.tareas = tareas;
        this.NIE = NIE;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getTipo_practicas() {
        return tipo_practicas;
    }

    public void setTipo_practicas(String tipo_practicas) {
        this.tipo_practicas = tipo_practicas;
    }

    public String getInicio_practicas() {
        return inicio_practicas;
    }

    public void setInicio_practicas(String inicio_practicas) {
        this.inicio_practicas = inicio_practicas;
    }

    public String getFin_practicas() {
        return fin_practicas;
    }

    public void setFin_practicas(String fin_practicas) {
        this.fin_practicas = fin_practicas;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public List<String> getTareas() {
        return tareas;
    }

    public void setTareas(List<String> tareas) {
        this.tareas = tareas;
    }

    public String getMotivosFinalizacion() {
        return motivosFinalizacion;
    }

    public void setMotivosFinalizacion(String motivosFinalizacion) {
        this.motivosFinalizacion = motivosFinalizacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public String getNIE() {
        return NIE;
    }

    public void setNIE(String NIE) {
        this.NIE = NIE;
    }
}
