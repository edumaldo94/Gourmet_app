package com.softulp.appgmaldonado.ui.inicio;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.appgmaldonado.modelo.ApiResponse;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.modelo.RecetaSubirDto;
import com.softulp.appgmaldonado.modelo.Usuario;
import com.softulp.appgmaldonado.modelo.Wrapper;
import com.softulp.appgmaldonado.request.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// viewmodels/RecetaViewModel.java
public class RecetaViewModel extends AndroidViewModel {
    private MutableLiveData<Receta> receta;
    private MutableLiveData<String> labelSwitch;
    private MutableLiveData<List<Receta>> recetasPorUsuario;
    private Context context;
    private MutableLiveData<Boolean> deleteResult = new MutableLiveData<>();

    public RecetaViewModel(@NonNull Application application) {
        super(application);
        context = application;
        receta = new MutableLiveData<>();
        labelSwitch = new MutableLiveData<>();
        recetasPorUsuario= new MutableLiveData<>();
    }
    public LiveData<List<Receta>> getRecetasPorUsuario() {
        return recetasPorUsuario;
    }
    public LiveData<Receta> getReceta() {
        return receta;
    }

    public LiveData<String> getLabelSwitch() {
        return labelSwitch;
    }
    public LiveData<Boolean> getDeleteResult() {
        return deleteResult;
    }
    public void getRecetaById(int recetaID) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        Call<Receta> llamada = apiService.ObtenerRecetaXid(recetaID, token);

        llamada.enqueue(new Callback<Receta>() {
            @Override
            public void onResponse(Call<Receta> call, Response<Receta> response) {
                if (response.isSuccessful()) {
                    receta.postValue(response.body());

                } else {
                    Log.d("RecetaViewModel", "Error: " + response.raw());
                }
            }

            @Override
            public void onFailure(Call<Receta> call, Throwable t) {
                Log.d("RecetaViewModel", "Failure: " + t.getMessage());
            }
        });
    }
    public void updateReceta(int recetaID, RecetaSubirDto recetaDto) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        Call<RecetaSubirDto> llamada = apiService.ActualizarReceta(recetaID,recetaDto, token);

        llamada.enqueue(new Callback<RecetaSubirDto>() {
            @Override
            public void onResponse(Call<RecetaSubirDto> call, Response<RecetaSubirDto> response) {
                if (response.isSuccessful()) {
                    Log.d("RecetaViewModel", "Enviado Correctamente!!!");

                } else {
                    Log.d("RecetaViewModel", "Error: " + response.raw());
                }
            }

            @Override
            public void onFailure(Call<RecetaSubirDto> call, Throwable t) {
                Log.d("RecetaViewModel", "Failure: " + t.getMessage());
            }
        });
    }
    public void deleteReceta(int recetaID) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        Call<Void> llamada = apiService.EliminarReceta(recetaID, token);

        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    deleteResult.postValue(true);
                    Log.d("RecetaViewModel", "Eliminado Correctamente!!!");
                } else {
                    deleteResult.postValue(false);
                    Log.d("RecetaViewModel", "Error: " + response.raw());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                deleteResult.postValue(false);
                Log.d("RecetaViewModel", "Failure: " + t.getMessage());
            }
        });
    }
    public void cargarRecetasUsuario(int usuarioId) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        Call<Wrapper<Receta>> llamada = apiService.ObtenerRecetaXUsuario(usuarioId, token);

        llamada.enqueue(new Callback<Wrapper<Receta>>() {
            @Override
            public void onResponse(Call<Wrapper<Receta>> call, Response<Wrapper<Receta>> response) {
                if (response.isSuccessful()) {
                   // Receta wrapper = response.body();
                    Wrapper<Receta> recetas = response.body();
                    recetasPorUsuario.setValue(recetas.getValues());
                    Log.d("BuscarViewModelCerte", "asdasdasd: " + response.raw());


                } else {
                    Log.d("BuscarViewModelCerte", "Error: " + response.raw());

                }
            }

            @Override
            public void onFailure(Call<Wrapper<Receta>> call, Throwable t) {
                Log.d("BuscarViewModelCerte", "Failure: " + t.getMessage());

            }
        });
    }
}
