package com.softulp.appgmaldonado.ui.perfil;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.appgmaldonado.modelo.ActualizarFotoDto;
import com.softulp.appgmaldonado.modelo.CambiarClaveModel;
import com.softulp.appgmaldonado.modelo.Usuario;
import com.softulp.appgmaldonado.modelo.UsuarioDto;
import com.softulp.appgmaldonado.modelo.UsuarioPerfilDto;
import com.softulp.appgmaldonado.request.ApiService;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> mUsuario;
    private MutableLiveData<String> editar;
    private MutableLiveData<Boolean> activar;
    private MutableLiveData<Boolean> datosCompletos = new MutableLiveData<>();
    private Context context;
    public PerfilViewModel(@NonNull Application application) {
        super(application);
        mUsuario = new MutableLiveData<>();
        context=getApplication();// Inicialización aquí
        llenarPerfil();
    }

    public LiveData<Usuario> mUsuario() {
        if (mUsuario == null) {
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }
    public LiveData<String> getMeditar() {
        if (editar == null) {
            editar = new MutableLiveData<>();
        }
        return editar;
    }
    public LiveData<Boolean> getMactivar() {
        if (activar == null) {
            activar = new MutableLiveData<>();
        }
        return activar;
    }
    public LiveData<Boolean> getDatosCompletos() {
        return datosCompletos;
    }
    public void actualizarDatosPersonales(Usuario usuario) {
        String token = ApiService.leerToken(context);
        ApiService.init(getApplication());
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        UsuarioPerfilDto usuarioDto=new UsuarioPerfilDto(usuario.getUsuarioID(),usuario.getNombre(),
        usuario.getApellido(),usuario.getCorreo());
        Call<Usuario> call = apiService.Editar(token, usuarioDto);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    mUsuario.postValue(response.body());
                    datosCompletos.setValue(true);
                    Toast.makeText(getApplication(), "Datos personales actualizados correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "Error al actualizar los datos personales", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable throwable) {
                Toast.makeText(getApplication(), "Error en la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void llenarPerfil() {
        ApiService.init(getApplication());
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        String token= ApiService.leerToken(context);
        Log.d("salidasas", token);
        Call<Usuario> call = apiService.obtenerusuarioB(token);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    mUsuario.postValue(response.body());
                    Log.d("salidaC",response.body().getUsuarioID()+"");

                } else {
                    Toast.makeText(getApplication(), "Usuario No encontrado", Toast.LENGTH_SHORT).show();
                    Log.d("salidaB", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable throwable) {
                Toast.makeText(getApplication(), "Error en la solicitud", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void cambiarClave(String ClaveAntigua, String ClaveNueva, String ConfirmarClaveNueva) {
        ApiService.init(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
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

    public void actualizarFotoPerfil(Uri imageUri) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();

        // Convierte la imagen a Base64
        String base64Image = convertirImgBase64(imageUri);

        // Crea el DTO con la foto
        ActualizarFotoDto actualizarFotoDto = new ActualizarFotoDto(base64Image);
        actualizarFotoDto.setFoto(base64Image);

        Usuario usuarioActual = mUsuario.getValue(); // Guardar el estado actual del usuario

        Call<Usuario> call = apiService.actualizarFoto(token, actualizarFotoDto);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && usuarioActual != null) {
                    Usuario usuarioActualizado = response.body();
                    // Asegurar que otros campos no se borren al actualizar la foto
                    usuarioActual.setFoto(usuarioActualizado.getFoto());

                    // Vuelve a postear el usuario con la nueva foto
                    mUsuario.postValue(usuarioActual);
                    Toast.makeText(context, "Foto de perfil actualizada correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error al actualizar la foto de perfil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(context, "Error en la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String convertirImgBase64(Uri uri) {
        try {
            ContentResolver contentResolver = getApplication().getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(uri);

            if (inputStream != null) {
                // Decodifica la imagen en un objeto Bitmap
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2; // Puedes ajustar este valor para comprimir más o menos
                Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream, null, options);

                // Comprime la imagen (ajusta la calidad según tus necesidades)
                int quality = 50; // Rango de 0 (más comprimido) a 100 (menos comprimido)
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                originalBitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);

                // Convierte la imagen comprimida a Base64
                byte[] imageBytes = byteArrayOutputStream.toByteArray();
                return Base64.encodeToString(imageBytes, Base64.DEFAULT).replace("\n", ""); // Eliminar caracteres de nueva línea
            } else {
                Log.d("salidaI12", "Error al cargar la imagen");
                return null;
            }
        } catch (IOException e) {
            // Handle error: Ocurrió un error al leer la imagen.
            e.printStackTrace();
            return null;
        }
    }


    public void activarEdicion(boolean habilitar) {
        activar.setValue(habilitar);
    }

}