package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioDto implements Serializable {

    @SerializedName("usuarioId")
    private int usuarioId;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("apellido")
    private String apellido;
    @SerializedName("correo")
    private String correo;

    @SerializedName("clave")
    private String clave;

    @SerializedName("foto")
    private String foto;

    public UsuarioDto(String nombre, String apellido, String correo, String clave,String foto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
        this.foto = foto;
    }

    public UsuarioDto(int usuarioId, String nombre, String apellido, String correo, String clave, String foto) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
        this.foto = foto;
    }

// Getters and Setters


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
