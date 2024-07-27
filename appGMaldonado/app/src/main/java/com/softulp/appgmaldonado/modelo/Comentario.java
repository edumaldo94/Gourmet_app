package com.softulp.appgmaldonado.modelo;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

    public class Comentario implements Serializable {
        @SerializedName("comentarioID")
        private int ComentarioID;
        private int UsuarioID;

        private Usuario usuario;

        private String comentario;

        private String fechaHora;
        @SerializedName("recetaId")
        private int RecetaID;

        private String Coment;
        private String FechaHora;
        private boolean Eliminado;

        public Comentario() {
        }

        public Comentario(int comentarioID, int usuarioID, int recetaID, String coment, String fechaHora, boolean eliminado) {
            ComentarioID = comentarioID;
            UsuarioID = usuarioID;
            RecetaID = recetaID;
            Coment = coment;
            FechaHora = fechaHora;
            Eliminado = eliminado;
        }

        public Comentario(int usuarioID, Usuario usuario, String comentario, String fechaHora, int recetaID, String coment, String fechaHora1, boolean eliminado) {
            UsuarioID = usuarioID;
            this.usuario = usuario;
            this.comentario = comentario;
            this.fechaHora = fechaHora;
            RecetaID = recetaID;
            Coment = coment;
            FechaHora = fechaHora1;
            Eliminado = eliminado;
        }

        public String getComentario() {
            return comentario;
        }

        public void setComentario(String comentario) {
            this.comentario = comentario;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
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

        public int getRecetaID() {
            return RecetaID;
        }

        public void setRecetaID(int recetaID) {
            RecetaID = recetaID;
        }

        public String getComent() {
            return Coment;
        }

        public void setComent(String coment) {
            Coment = coment;
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

