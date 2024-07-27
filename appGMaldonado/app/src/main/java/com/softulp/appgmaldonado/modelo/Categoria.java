package com.softulp.appgmaldonado.modelo;

public class Categoria {
    private String nombre;
    private int imagen;

    public Categoria(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }
}
