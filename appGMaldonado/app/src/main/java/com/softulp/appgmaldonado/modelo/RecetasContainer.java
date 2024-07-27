package com.softulp.appgmaldonado.modelo;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecetasContainer {
    @SerializedName("$values")
    private List<Receta> recetas;

    public RecetasContainer(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }
    // Getter y setter
}
