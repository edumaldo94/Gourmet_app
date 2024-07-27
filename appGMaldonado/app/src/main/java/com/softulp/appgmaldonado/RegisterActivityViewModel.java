package com.softulp.appgmaldonado;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.appgmaldonado.modelo.UsuarioDto;
import com.softulp.appgmaldonado.request.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivityViewModel extends AndroidViewModel {
    private MutableLiveData<String> resultadoRegistro = new MutableLiveData<>();
    private Context context;

    public RegisterActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    public LiveData<String> getResultadoRegistro() {
        return resultadoRegistro;
    }

    public void crearUsuario(String nombre, String apellido, String correo, String clave) {
        UsuarioDto usuarioDto = new UsuarioDto(nombre, apellido, correo, clave,"");
        Log.d("berer", "E:" + usuarioDto.getApellido()+" "+usuarioDto.getNombre());
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiInterface = ApiService.getApiInterface();

        Call<UsuarioDto> call = apiInterface.crearUsuario(usuarioDto);
        call.enqueue(new Callback<UsuarioDto>() {
            @Override
            public void onResponse(Call<UsuarioDto> call, Response<UsuarioDto> response) {
                if (response.isSuccessful()) {
                    resultadoRegistro.postValue("success");
                    Log.d("berer", "E:" + response.body().toString());
                } else {
                    resultadoRegistro.postValue("Error: " + response.message());
                    Log.d("berer", "E:" + response.message());
                }
            }

            @Override
            public void onFailure(Call<UsuarioDto> call, Throwable t) {
                Log.d("berer", "E:" + t.getMessage());
                resultadoRegistro.postValue("Error: " + t.getMessage());
            }
        });
    }
}
