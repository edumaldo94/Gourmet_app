package com.softulp.appgmaldonado.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.softulp.appgmaldonado.modelo.Usuario;
import com.softulp.appgmaldonado.request.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecuperarActivityViewModel extends AndroidViewModel {

    Context context;
    public RecuperarActivityViewModel(@NonNull Application application) {
        super(application);
        context=application;
    }
    public void  pedidoRecuperoClave(String correo){
        String token= ApiService.leerToken(context);
        Log.d("salidaR","TOKEN: "+token);
        ApiService.ApiInterface apiInterface= ApiService.getApiInterface();
        Call<Usuario> llamada=apiInterface.inciarRecupero(correo);
        llamada.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){

                    Log.d("salidaR ",response.body().toString());

                }else {

                    Log.d("salidaR respuesta ",response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("salidaR falla ",t.getMessage());
            }
        });
    }
}
/**/