package com.softulp.appgmaldonado.ui.agregar;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.request.ApiService;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecetaAgregarViewModel extends AndroidViewModel {
    private MutableLiveData<Uri> mImgUri;
    private Context context;
    private MutableLiveData<Receta> mReceta;
    private MutableLiveData<Boolean> recetaAgregada ;
    private MutableLiveData<Bitmap> fotoReceta;
    public RecetaAgregarViewModel(@NonNull Application application) {
        super(application);
        mImgUri = new MutableLiveData<>();
        mReceta = new MutableLiveData<>();
        recetaAgregada= new MutableLiveData<>();
        fotoReceta = new MutableLiveData<>();
        context = application.getApplicationContext();
    }
    public LiveData<Boolean> getRecetaAgregada() {
        return recetaAgregada;
    }

    public LiveData<Uri> getmImgUri() {
        return mImgUri;
    }

    public LiveData<Receta> getmReceta() {
        return mReceta;
    }
    public LiveData<Bitmap> getFotoReceta() {
        return fotoReceta;
    }

    public void crearReceta(Uri uris, Receta receta) {
        String token = ApiService.leerToken(getApplication());
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        Receta receta1 = receta;


        receta1.setFotoPortada(convertirImgBase64(uris)); // Asumiendo que tu clase Receta tiene un campo para múltiples fotos

        Call<Receta> llamada = apiService.crearReceta(token, receta1);

        llamada.enqueue(new Callback<Receta>() {
            @Override
            public void onResponse(Call<Receta> call, Response<Receta> response) {
                if (response.isSuccessful()) {
                    mReceta.postValue(response.body());
                    // Cambiar esto según el resultado real de la creación de la receta


                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), uris);
                        fotoReceta.setValue(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Log.d("salidaI13", response.body().toString());
                } else {
                    Log.d("salidaI14", "ELSE " + response.raw());

                }
            }

            @Override
            public void onFailure(Call<Receta> call, Throwable t) {
                Log.d("salidaI15", "ERROR " + t.getMessage());
            }
        });
    }
    public void resetRecetaAgregada() {
        recetaAgregada.setValue(false);

    }
    public void resetRecetatrue() {

        recetaAgregada.setValue(true);
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

}
