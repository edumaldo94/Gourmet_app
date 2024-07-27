package com.softulp.appgmaldonado.ui.inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.appgmaldonado.modelo.Receta;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Receta> recetaLiveData = new MutableLiveData<>();

    public LiveData<Receta> getRecetaLiveData() {
        return recetaLiveData;
    }

    public void setReceta(Receta receta) {
        recetaLiveData.setValue(receta);
    }

    public void incrementarCantidadComentarios() {
        Receta receta = recetaLiveData.getValue();
        if (receta != null) {
            receta.setCantidadComentarios(receta.getCantidadComentarios() + 1);
            recetaLiveData.setValue(receta);  // Notifica a los observadores
        }
    }

    public void decrementarCantidadComentarios() {
        Receta receta = recetaLiveData.getValue();
        if (receta != null) {
            receta.setCantidadComentarios(receta.getCantidadComentarios() - 1);
            recetaLiveData.setValue(receta);  // Notifica a los observadores
        }
    }
}
