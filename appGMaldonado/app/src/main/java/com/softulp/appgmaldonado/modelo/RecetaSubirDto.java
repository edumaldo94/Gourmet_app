package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RecetaSubirDto implements Serializable {
    private static final long serialVersionUID = 1L;


    @SerializedName("titulo")
    private String Titulo;

    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("ingredientes")
    private String Ingredientes;
    @SerializedName("pasos")
    private String Pasos;
    @SerializedName("porciones")
    private String Porciones;
    @SerializedName("tiempoPreparacion")
    private String TiempoPreparacion;
    @SerializedName("dificultad")
    private String Dificultad;
    @SerializedName("tipoCocina")
    private String TipoCocina;
    @SerializedName("fotoPortada")
    private String fotoPortada;

    public RecetaSubirDto(String titulo, String descripcion, String ingredientes, String pasos, String porciones, String tiempoPreparacion, String dificultad, String tipoCocina, String fotoPortada) {
        Titulo = titulo;
        this.descripcion = descripcion;
        Ingredientes = ingredientes;
        Pasos = pasos;
        Porciones = porciones;
        TiempoPreparacion = tiempoPreparacion;
        Dificultad = dificultad;
        TipoCocina = tipoCocina;
        this.fotoPortada = fotoPortada;
    }



    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientes() {
        return Ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        Ingredientes = ingredientes;
    }

    public String getPasos() {
        return Pasos;
    }

    public void setPasos(String pasos) {
        Pasos = pasos;
    }

    public String getPorciones() {
        return Porciones;
    }

    public void setPorciones(String porciones) {
        Porciones = porciones;
    }

    public String getTiempoPreparacion() {
        return TiempoPreparacion;
    }

    public void setTiempoPreparacion(String tiempoPreparacion) {
        TiempoPreparacion = tiempoPreparacion;
    }

    public String getDificultad() {
        return Dificultad;
    }

    public void setDificultad(String dificultad) {
        Dificultad = dificultad;
    }

    public String getTipoCocina() {
        return TipoCocina;
    }

    public void setTipoCocina(String tipoCocina) {
        TipoCocina = tipoCocina;
    }

    public String getFotoPortada() {
        return fotoPortada;
    }

    public void setFotoPortada(String fotoPortada) {
        this.fotoPortada = fotoPortada;
    }
}