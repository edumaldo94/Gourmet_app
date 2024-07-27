package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ApiResponse implements Serializable {
    @SerializedName("recetas")
    private Wrapper<Receta> recetas;

    public Wrapper<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(Wrapper<Receta> recetas) {
        this.recetas = recetas;
    }
}