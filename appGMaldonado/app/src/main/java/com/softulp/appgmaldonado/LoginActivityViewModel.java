package com.softulp.appgmaldonado;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.appgmaldonado.modelo.Usuario;
import com.softulp.appgmaldonado.request.ApiService;
import com.softulp.appgmaldonado.ui.inicio.InicioViewModel;
import com.softulp.appgmaldonado.ui.perfil.RecuperarActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mError;
    private MutableLiveData<Usuario> usuarioLogueado = new MutableLiveData<>();

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        mError = new MutableLiveData<>();
    }

    public LiveData<String> getMerror() {
        return mError;
    }

    public LiveData<Usuario> getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void login(String mail, String password) {
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        if (!mail.contains("@")) {
            mError.setValue("Ingrese un Correo Válido");
        } else {
            if (password.isEmpty()) {
                mError.setValue("Debe ingresar una contraseña");
            } else {
                Call<String> llamada = apiService.login(mail, password);
                llamada.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String token = "Bearer " + response.body();
                            ApiService.guardarToken(context, token);

                            // Obtener información del usuario
                            Call<Usuario> userCall = apiService.getUserInfo(token);
                            userCall.enqueue(new Callback<Usuario>() {
                                @Override
                                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                    if (response.isSuccessful()) {
                                        Usuario usuario = response.body();
                                        if (usuario != null) {
                                            usuarioLogueado.setValue(usuario);
                                            saveLoggedUser(usuario);  // Guardar el usuario logueado
                                            Log.d("namelogi", usuario.getNombre());
                                            Intent intent = new Intent(context, MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(intent);
                                        } else {
                                            mError.setValue("Error al obtener la información del usuario");
                                        }
                                    } else {
                                        mError.setValue("Error al obtener la información del usuario");
                                    }
                                }

                                @Override
                                public void onFailure(Call<Usuario> call, Throwable t) {
                                    mError.setValue("Error al obtener la información del usuario");
                                }
                            });
                        } else {
                            mError.setValue("Correo y/o Contraseña Incorrecta");
                            Log.d("salida", response.raw().message());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("salida", t.getMessage());
                    }
                });
            }
        }
    }

    // Método para guardar el usuario logueado en SharedPreferences
    private void saveLoggedUser(Usuario usuario) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("usuarioID", usuario.getUsuarioID()); // Usa "usuarioID" en lugar de "UsuarioID"
        editor.putString("Nombre", usuario.getNombre());
        editor.putString("Apellido", usuario.getApellido());
        editor.putString("Correo", usuario.getCorreo());
        editor.putString("Foto", usuario.getFoto());
        editor.apply();
    }
    public void iniciarRecupero(){
        Intent intent=new Intent(context, RecuperarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}


/*
    public void iniciarRecupero(){
        Intent intent=new Intent(context, RecuperarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

*/


