package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Respuesta {
    private int RespuestaID;
    private int ComentarioID;
    private int UsuarioID;
    private String RespuestasAtr;
    private String FechaHora;
    private boolean Eliminado;

    public Respuesta() {
    }

    public Respuesta(int respuestaID, int comentarioID, int usuarioID, String respuestasAtr, String fechaHora, boolean eliminado) {
        RespuestaID = respuestaID;
        ComentarioID = comentarioID;
        UsuarioID = usuarioID;
        RespuestasAtr = respuestasAtr;
        FechaHora = fechaHora;
        Eliminado = eliminado;
    }

    public int getRespuestaID() {
        return RespuestaID;
    }

    public void setRespuestaID(int respuestaID) {
        RespuestaID = respuestaID;
    }

    public int getComentarioID() {
        return ComentarioID;
    }

    public void setComentarioID(int comentarioID) {
        ComentarioID = comentarioID;
    }

    public int getUsuarioID() {
        return UsuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        UsuarioID = usuarioID;
    }

    public String getRespuestasAtr() {
        return RespuestasAtr;
    }

    public void setRespuestasAtr(String respuestasAtr) {
        RespuestasAtr = respuestasAtr;
    }

    public String getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(String fechaHora) {
        FechaHora = fechaHora;
    }

    public boolean isEliminado() {
        return Eliminado;
    }

    public void setEliminado(boolean eliminado) {
        Eliminado = eliminado;
    }
}