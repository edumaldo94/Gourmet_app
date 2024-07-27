package com.softulp.appgmaldonado.modelo;

import java.io.Serializable;

public class UsuarioActual implements Serializable {
    private static UsuarioActual instance;
    private Usuario usuario;

    private UsuarioActual() {
        // Constructor privado para evitar instanciación
    }

    public static UsuarioActual getInstance() {
        if (instance == null) {
            instance = new UsuarioActual();
        }
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
