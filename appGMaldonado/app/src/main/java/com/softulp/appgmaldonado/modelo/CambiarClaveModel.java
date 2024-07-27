package com.softulp.appgmaldonado.modelo;


import java.io.Serializable;

public class CambiarClaveModel implements Serializable {
    private String ClaveAntigua;
    private String ClaveNueva;
    private String ConfirmarClaveNueva;

    public CambiarClaveModel(String claveAntigua, String claveNueva, String confirmarClaveNueva) {
        ClaveAntigua = claveAntigua;
        ClaveNueva = claveNueva;
        ConfirmarClaveNueva = confirmarClaveNueva;
    }

    public String getClaveAntigua() {
        return ClaveAntigua;
    }

    public void setClaveAntigua(String claveAntigua) {
        ClaveAntigua = claveAntigua;
    }

    public String getClaveNueva() {
        return ClaveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        ClaveNueva = claveNueva;
    }

    public String getConfirmarClaveNueva() {
        return ConfirmarClaveNueva;
    }

    public void setConfirmarClaveNueva(String confirmarClaveNueva) {
        ConfirmarClaveNueva = confirmarClaveNueva;
    }
// Getters y Setters
}
