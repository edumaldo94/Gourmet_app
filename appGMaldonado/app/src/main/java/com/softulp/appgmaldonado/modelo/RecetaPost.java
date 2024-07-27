package com.softulp.appgmaldonado.modelo;

import java.util.List;

public class RecetaPost {
    private Usuario usuario;
    private String fotoPortada;
    private String descripcion;
    private List<Comentario> comentarios;
    private int likes;

    public RecetaPost() {
    }

    public RecetaPost(Usuario usuario, String fotoPortada, String descripcion, List<Comentario> comentarios, int likes) {
        this.usuario = usuario;
        this.fotoPortada = fotoPortada;
        this.descripcion = descripcion;
        this.comentarios = comentarios;
        this.likes = likes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFotoPortada() {
        return fotoPortada;
    }

    public void setFotoPortada(String fotoPortada) {
        this.fotoPortada = fotoPortada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
