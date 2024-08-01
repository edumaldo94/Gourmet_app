package com.softulp.appgmaldonado.ui.inicio;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.appgmaldonado.modelo.ApiResponse;
import com.softulp.appgmaldonado.modelo.Comentario;
import com.softulp.appgmaldonado.modelo.ComentarioDto;
import com.softulp.appgmaldonado.modelo.CrearComentarioDto;
import com.softulp.appgmaldonado.modelo.Like;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.modelo.RecetaFavorita;
import com.softulp.appgmaldonado.modelo.Usuario;
import com.softulp.appgmaldonado.modelo.Wrapper;
import com.softulp.appgmaldonado.request.ApiService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioViewModel extends AndroidViewModel {
    private List<Like> userLikes; // Lista de likes del usuario
    private MutableLiveData<List<Receta>> recetas;
    private MutableLiveData<List<Receta>> recetasFvt;
    private MutableLiveData<Usuario> userActual;
    private MutableLiveData<Boolean> isDataLoaded;
    private Context context;

    public InicioViewModel(@NonNull Application application) {
        super(application);
        context = application;
        recetas = new MutableLiveData<>();
        userActual = new MutableLiveData<>();
        recetasFvt= new MutableLiveData<>();
        isDataLoaded = new MutableLiveData<>();
        isDataLoaded.setValue(false);
        cargarDatos();
        cargarRecetasFvt();
    }

    public LiveData<List<Receta>> getRecetas() {
        return recetas;
    }
    // Método para guardar el usuario logueado
    public LiveData<Boolean> getIsDataLoaded() {
        return isDataLoaded;
    }

    public MutableLiveData<List<Receta>> getRecetasFvt() {
        return recetasFvt;
    }

    public LiveData<Usuario> getUserActual() {
        return userActual;
    }

    // Método para obtener el usuario logueado
    private Usuario getLoggedUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        Usuario usuario = new Usuario();


        usuario.setUsuarioID(sharedPreferences.getInt("usuarioID", 0)); // Usa "usuarioID" en lugar de "UsuarioID"
        usuario.setNombre(sharedPreferences.getString("Nombre", null));
        usuario.setApellido(sharedPreferences.getString("Apellido", null));
        usuario.setCorreo(sharedPreferences.getString("Correo", null));
        usuario.setFoto(sharedPreferences.getString("Foto", null));

        return usuario;
    }

    // Método público para obtener el usuario actual
    public Usuario getUsuarioActual() {
        return getLoggedUser();
    }


    public void cargarDatos() {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        Call<ApiResponse> llamada = apiService.ObtenerPostReceta(token);

        llamada.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getRecetas() != null) {
                        List<Receta> listaRecetas = apiResponse.getRecetas().getValues();
                        recetas.postValue(listaRecetas);


                      Log.d("bebe", "Eweqweq:" + listaRecetas.size());
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
    public void cargarRecetasFvt() {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        Call<ApiResponse> llamada = apiService.ObtenerRecetasFvt(token);

        llamada.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getRecetas() != null) {
                        List<Receta> listaRecetasB = apiResponse.getRecetas().getValues();
                        recetasFvt.postValue(listaRecetasB);


                        //  Log.d("bebe", "E:" + listaRecetas.get(1).getRecetaID());
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
    public void ComentarPublicacion(int recetaId, String comentarioTexto) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiInterface = ApiService.getApiInterface();

        CrearComentarioDto comentarioDto = new CrearComentarioDto(comentarioTexto);

        Call<Void> llamada = apiInterface.ComentarPosteo(recetaId, token, comentarioDto);
        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {

                    Log.d("be2", "Comentario enviado con éxito");
                } else {
                    Log.d("be3", "Error al enviar Comentario: " + response.message() + " Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("be4", "Error en la llamada a la API: " + t.getMessage());
            }
        });
    }


    public void eliminarComentario(int comentarioId) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiInterface = ApiService.getApiInterface();

        Call<Void> llamada = apiInterface.borrarComentario(comentarioId, token);
        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("TAG", "Comentario eliminado con éxito");
                } else {
                    Log.d("TAG", "Error al eliminar el comentario: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("TAG", "Error en la llamada a la API: " + t.getMessage());
            }
        });
    }
    public void agregarComentario(int recetaId, Comentario nuevoComentario) {
        List<Receta> listaRecetas = recetas.getValue();
        if (listaRecetas != null) {
            for (Receta receta : listaRecetas) {
                if (receta.getRecetaID() == recetaId) {
                    receta.getComentarios().add(nuevoComentario);
                    recetas.setValue(listaRecetas); // Notifica a los observadores que la lista ha cambiado
                    break;
                }
            }
        }
    }
    public void enviarLike(int recetaId, Like like) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        Log.d("be1", "Enviando like: RecetaID=" + recetaId + ", UsuarioID=" + like.getUsuarioID());

        Call<Void> llamada = apiService.darLike(recetaId, like,token);
        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("be2", "Like enviado con éxito");
                } else {
                    Log.d("be3", "Error al enviar like: " + response.message() + " Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("be4", "Error en la llamada a la API: " + t.getMessage());
            }
        });
    }

    public void borrarLike(int recetaId) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();
        Log.d("be11", "Enviando solicitud para eliminar like de RecetaID=" + recetaId + " para UsuarioID=");

        Call<Void> llamada = apiService.QuitarLike(recetaId,token);
        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("be22", "Like eliminado con éxito");
                } else {
                    Log.d("be33", "Error al eliminar like: " + response.message() + " Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("be44", "Error en la llamada a la API: " + t.getMessage());
            }
        });
    }



        public void obtenerLikesUsuario() {
            String token = ApiService.leerToken(context);
            ApiService.ApiInterface apiService = ApiService.getApiInterface();

            Call<Wrapper<Like>> llamada = apiService.obtenerLikesUsuario(token);
            llamada.enqueue(new Callback<Wrapper<Like>>() {
                @Override
                public void onResponse(Call<Wrapper<Like>> call, Response<Wrapper<Like>> response) {
                    if (response.isSuccessful()) {
                        Wrapper<Like> wrapper = response.body();
                        if (wrapper != null && wrapper.getValues() != null) {
                            userLikes = wrapper.getValues();
                         //   Log.d("TAG", "likes nula"+wrapper.getValues().get(1).getRecetaID());
                            // Notify that the data has changed
                            // You can use LiveData or any other mechanism to notify the adapter
                        } else {
                            Log.d("TAG", "Lista de likes vacía o nula");
                        }
                    } else {
                        Log.d("TAG", "Error al obtener los likes del usuario: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Wrapper<Like>> call, Throwable t) {
                    Log.d("TAG", "Error en la llamada a la API para obtener likes del usuario: " + t.getMessage());
                }
            });
        }
    public void guardarRecetasFav(int recetaId) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();

        Call<Void> llamada = apiService.saveRecetasFavoritas(recetaId,token);
        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("be22", "Like eliminado con éxito");
                } else {
                    Log.d("be33", "Error al eliminar like: " + response.message() + " Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("be44", "Error en la llamada a la API: " + t.getMessage());
            }
        });
    }
    public void borrarRecetasFav(int recetaId) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInterface();

        Call<Void> llamada = apiService.deleteRecetasFavoritas(recetaId,token);
        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("be22", "Like eliminado con éxito");
                } else {
                    Log.d("be33", "Error al eliminar like: " + response.message() + " Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("be44", "Error en la llamada a la API: " + t.getMessage());
            }
        });
    }
        public boolean hasLiked(int recetaId) {
            if (userLikes != null) {
                for (Like like : userLikes) {
                    if (like.getRecetaID() == recetaId) {
                        return true;
                    }
                }
            }
            return false;
        }
    public void addLikeToUserLikes(Like like) {
        userLikes.add(like);
    }

    public void removeLikeFromUserLikes(int recetaId) {
        for (Iterator<Like> iterator = userLikes.iterator(); iterator.hasNext();) {
            Like like = iterator.next();
            if (like.getRecetaID() == recetaId) {
                iterator.remove();
                break;
            }
        }
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

                    isDataLoaded.setValue(true);

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


