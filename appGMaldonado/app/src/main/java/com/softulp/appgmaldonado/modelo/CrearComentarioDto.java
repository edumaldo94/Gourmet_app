package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CrearComentarioDto implements Serializable {
    @SerializedName("coment")
    private String comentario;

    public CrearComentarioDto(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
