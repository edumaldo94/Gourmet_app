package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioPerfilDto implements Serializable {

    @SerializedName("usuarioId")
    private int usuarioId;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("apellido")
    private String apellido;
    @SerializedName("correo")
    private String correo;



    public UsuarioPerfilDto(int usuarioId, String nombre, String apellido, String correo) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;

    }

// Getters and Setters


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
