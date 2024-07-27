package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ComentarioDto implements Serializable {

    @SerializedName("comentarioID")
    private int comentarioID;

    @SerializedName("comentario")
    private String comentario;

    @SerializedName("fechaHora")
    private String fechaHora; // Consider using Date type and appropriate type adapters if needed

    @SerializedName("usuario")
    private UsuarioDto usuario;

    // Getters and Setters

    public int getComentarioID() {
        return comentarioID;
    }

    public void setComentarioID(int comentarioID) {
        this.comentarioID = comentarioID;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }
}
