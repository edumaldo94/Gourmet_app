package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecetaResponse {
    @SerializedName("recetas")
    private List<Receta> recetas;

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }
}
