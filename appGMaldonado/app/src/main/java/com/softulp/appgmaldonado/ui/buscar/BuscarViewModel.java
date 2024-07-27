package com.softulp.appgmaldonado.ui.buscar;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.modelo.ApiResponse;
import com.softulp.appgmaldonado.modelo.Categoria;
import com.softulp.appgmaldonado.modelo.Like;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.modelo.Wrapper;
import com.softulp.appgmaldonado.request.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarViewModel extends AndroidViewModel {
    private MutableLiveData<List<Categoria>> categorias;
    private MutableLiveData<List<Receta>> recetasPorCategoria;
    private MutableLiveData<List<Receta>> recetasFiltradas;
    private Context context;
    private MutableLiveData<List<Receta>> recetas;
    public BuscarViewModel(@NonNull Application application) {
        super(application);
        context = application;
        categorias = new MutableLiveData<>();
        recetasPorCategoria = new MutableLiveData<>();
        recetasFiltradas = new MutableLiveData<>();
        recetas = new MutableLiveData<>(new ArrayList<>());
        cargarCategorias();
      //  cargarDatos();
    }

    public LiveData<List<Categoria>> getCategorias() {
        return categorias;
    }

    public LiveData<List<Receta>> getRecetasPorCategoria() {
        return recetasPorCategoria;
    }
    public LiveData<List<Receta>> getRecetasFiltradas() {
        return recetasFiltradas;
    }
    public void filtrarRecetas(String query) {
        cargarDatos(query);
    }
    public void cargarDatos(String query) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        Call<ApiResponse> llamada = apiService.BuscarPorQuery(query,token);

        llamada.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getRecetas() != null) {
                        List<Receta> listaRecetas = apiResponse.getRecetas().getValues();
                        recetasFiltradas.postValue(listaRecetas);
                        Log.d(" ", "Buscanddo...");
                    } else {
                        Log.d("TAG", "Error: Respuesta o lista de recetas nula");
                    }
                } else {
                    Log.d("TAG", "Error en la respuesta de la API: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("API_CALL", "Error en la llamada a la API: " + t.getMessage());
                Toast.makeText(context, "Error en la llamada a la API", Toast.LENGTH_SHORT).show();
            }
        });
    }
/*
    public void filtrarRecetas(String query) {
        if (recetas.getValue() == null) return;
        Log.d("filtrar", "p1 ");
        if (query == null || query.isEmpty()) {
            recetasFiltradas.setValue(recetas.getValue());
            Log.d("filtrar", "p2 ");
        } else {
            List<Receta> listaFiltrada = new ArrayList<>();
            for (Receta receta : recetas.getValue()) {
                if (receta.getTitulo() != null && receta.getTitulo().toLowerCase().contains(query.toLowerCase())) {
                    listaFiltrada.add(receta);
                    Log.d("filtrar", "p3 ");
                }
            }
            Log.d("filtrar", "p4 ");
            recetasFiltradas.setValue(listaFiltrada);
        }
    }*/


    private void cargarCategorias() {
        List<Categoria> listaCategorias = new ArrayList<>();
        listaCategorias.add(new Categoria("Carne Blanca", R.drawable.carneblanca));
        listaCategorias.add(new Categoria("Carne Roja", R.drawable.carneroja));
        listaCategorias.add(new Categoria("Verdura", R.drawable.verduras));
        listaCategorias.add(new Categoria("Pescado", R.drawable.pescado));
        listaCategorias.add(new Categoria("Pastas", R.drawable.pasta));

        categorias.setValue(listaCategorias);
    }
  /*  public void cargarRecetasUsuario(int usuarioId) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        Call<List<Receta>> llamada = apiService.ObtenerRecetaXUsuario(usuarioId, token);

        llamada.enqueue(new Callback<List<Receta>>() {
            @Override
            public void onResponse(Call<List<Receta>> call, Response<List<Receta>> response) {
                if (response.isSuccessful()) {
                    // Receta wrapper = response.body();
                    List<Receta> recetas = response.body();
                    recetasFiltradas.setValue(recetas);



                } else {
                    Log.d("BuscarViewModel", "Error: " + response.raw());

                }
            }

            @Override
            public void onFailure(Call<List<Receta>> call, Throwable t) {
                Log.d("BuscarViewModel", "Failure: " + t.getMessage());

            }
        });
    }*/
    public void buscarPorCategoria(String tipoCocina) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        Call<ApiResponse> llamada = apiService.BuscarPorCategoria(tipoCocina, token);

        llamada.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse wrapper = response.body();
                        List<Receta> recetas = wrapper.getRecetas().getValues();
                        recetasPorCategoria.setValue(recetas);

                            Log.d("BuscarViewModel", "Receta ID: " + recetas.get(0).getTitulo());

                } else {
                    Log.d("BuscarViewModel", "Error: " + response.raw());

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("BuscarViewModel", "Failure: " + t.getMessage());

            }
        });
    }
}
