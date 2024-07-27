package com.softulp.appgmaldonado.ui.perfil;


import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*
public class ClaveFragmentViewModel extends AndroidViewModel {

    private Context context;
    public ClaveFragmentViewModel(@NonNull Application application) {
        super(application);
        context=getApplication();
    }

    public void cambiarClave(String ClaveAntigua, String ClaveNueva, String ConfirmarClaveNueva) {
        ApiService.init(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        String token = ApiService.leerToken(context);
        Log.d("carajo4", token);
        CambiarClaveModel model = new CambiarClaveModel(ClaveAntigua, ClaveNueva, ConfirmarClaveNueva);

        Call<Void> call = apiService.clave(token, model);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), "Clave actualizada correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "Error al actualizar la clave", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplication(), "Error en la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }
}*/
