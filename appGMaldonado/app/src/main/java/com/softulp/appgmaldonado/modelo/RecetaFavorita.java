package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RecetaFavorita implements Serializable {
    private int UsuarioID;
    private int RecetaID;

    public RecetaFavorita() {
    }

    public RecetaFavorita(int usuarioID, int recetaID) {
        UsuarioID = usuarioID;
        RecetaID = recetaID;
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
