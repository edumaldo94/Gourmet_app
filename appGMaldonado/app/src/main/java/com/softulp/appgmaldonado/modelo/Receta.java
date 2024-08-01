package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Receta implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("recetaID")
    private int recetaId;

    private Usuario usuario;
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
    @SerializedName("cantidadComentarios")
    private int  cantidadComentarios;
    private Like likes;
    @SerializedName("comentarios")
    private Wrapper<Comentario> comentarios;
    private boolean mostrarComentarios;
    private boolean liked;

    @SerializedName("esFavorita")
    private boolean esFavorita;

    public Receta() {
    }

    public Receta(Usuario usuario, String titulo, String descripcion, String ingredientes, String pasos, String porciones, String tiempoPreparacion, String dificultad, String tipoCocina, String fotoPortada, Like likes, Wrapper<Comentario> comentarios, boolean mostrarComentarios, boolean liked, boolean esFavorita) {
        this.usuario = usuario;
        Titulo = titulo;
        this.descripcion = descripcion;
        Ingredientes = ingredientes;
        Pasos = pasos;
        Porciones = porciones;
        TiempoPreparacion = tiempoPreparacion;
        Dificultad = dificultad;
        TipoCocina = tipoCocina;
        this.fotoPortada = fotoPortada;
        this.likes = likes;
        this.comentarios = comentarios;
        this.mostrarComentarios = mostrarComentarios;
        this.liked = liked;

        this.esFavorita = esFavorita;
    }

    public Receta(int recetaId, Usuario usuario, String titulo, String descripcion, String ingredientes, String pasos, String porciones, String tiempoPreparacion, String dificultad, String tipoCocina, String fotoPortada, int cantidadComentarios, Like likes, Wrapper<Comentario> comentarios, boolean mostrarComentarios, boolean liked, boolean esFavorita) {
        this.recetaId = recetaId;
        this.usuario = usuario;
        Titulo = titulo;
        this.descripcion = descripcion;
        Ingredientes = ingredientes;
        Pasos = pasos;
        Porciones = porciones;
        TiempoPreparacion = tiempoPreparacion;
        Dificultad = dificultad;
        TipoCocina = tipoCocina;
        this.fotoPortada = fotoPortada;
        this.cantidadComentarios = cantidadComentarios;
        this.likes = likes;
        this.comentarios = comentarios;
        this.mostrarComentarios = mostrarComentarios;
        this.liked = liked;
        this.esFavorita = esFavorita;
    }

    public Receta(String titulo, String descripcion, String ingredientes, String pasos, String porciones, String tiempoPreparacion, String dificultad, String tipoCocina, String fotoPortada) {
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

    public void incrementarCantidadComentarios() {
        this.cantidadComentarios++;
    }
    public int getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(int recetaId) {
        this.recetaId = recetaId;
    }

    public int getCantidadComentarios() {
        return cantidadComentarios;
    }

    public void setCantidadComentarios(int cantidadComentarios) {
        this.cantidadComentarios = cantidadComentarios;
    }

    public Like getLikes() {
        return likes;
    }

    public void setLikes(Like likes) {
        this.likes = likes;
    }

    public int getRecetaID() {
        return recetaId;
    }

    public void setRecetaID(int recetaID) {
        this.recetaId = recetaID;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public List<Comentario> getComentarios() {
        return comentarios != null ? comentarios.getValues() : null;
    }

    public void setComentarios(Wrapper<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    public boolean isMostrarComentarios() {
        return mostrarComentarios;
    }

    public void setMostrarComentarios(boolean mostrarComentarios) {
        this.mostrarComentarios = mostrarComentarios;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isEsFavorita() {
        return esFavorita;
    }

    public void setEsFavorita(boolean esFavorita) {
        this.esFavorita = esFavorita;
    }
}