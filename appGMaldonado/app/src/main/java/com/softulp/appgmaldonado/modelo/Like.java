package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Like implements Serializable {

    private int LikeID;
    @SerializedName("usuarioID")
    private int UsuarioID;

    @SerializedName("recetaID")
    private int RecetaID;

    private Usuario usuario;
    @SerializedName("cantidad")
    private int cantidad;
    public Like() {
    }

    public Like(int usuarioID, int recetaID) {
        UsuarioID = usuarioID;
        RecetaID = recetaID;
    }

    public Like(int usuarioID, int recetaID, Usuario usuario, int cantidad) {
        UsuarioID = usuarioID;
        RecetaID = recetaID;
        this.usuario = usuario;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getLikeID() {
        return LikeID;
    }

    public void setLikeID(int likeID) {
        LikeID = likeID;
    }

    public int getUsuarioID() {
        return UsuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        UsuarioID = usuarioID;
    }

    public int getRecetaID() {
        return RecetaID;
    }

    public void setRecetaID(int recetaID) {
        RecetaID = recetaID;
    }
}
