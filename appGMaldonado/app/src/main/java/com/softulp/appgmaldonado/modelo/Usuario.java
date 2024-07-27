package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Serializable {
    @SerializedName("usuarioID")
    private int UsuarioID;
    @SerializedName("nombre")
    private String Nombre;
    @SerializedName("apellido")
    private String Apellido;
    @SerializedName("clave")
    private String Clave;
    @SerializedName("correo")
    private String Correo;
    @SerializedName("foto")
    private String Foto;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String Clave, String correo, String foto) {
        Nombre = nombre;
        Apellido = apellido;
        Clave = Clave;
        Correo = correo;
        Foto = foto;
    }

    public Usuario(int usuarioID, String nombre, String apellido, String Clave, String correo, String foto) {
        UsuarioID = usuarioID;
        Nombre = nombre;
        Apellido = apellido;
        Clave = Clave;
        Correo = correo;
        Foto = foto;
    }

    public Usuario(int usuarioID, String nombre, String apellido, String correo) {
        UsuarioID = usuarioID;
        Nombre = nombre;
        Apellido = apellido;
        Correo = correo;
    }

    public int getUsuarioID() {
        return UsuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        UsuarioID = usuarioID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        Clave = Clave;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }
    public String getNombreCompleto() {
        return this.Nombre + " " + this.Apellido;
    }
}