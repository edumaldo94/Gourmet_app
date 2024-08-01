package com.softulp.appgmaldonado.request;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softulp.appgmaldonado.modelo.ActualizarFotoDto;
import com.softulp.appgmaldonado.modelo.ApiResponse;
import com.softulp.appgmaldonado.modelo.CambiarClaveModel;
import com.softulp.appgmaldonado.modelo.Comentario;
import com.softulp.appgmaldonado.modelo.ComentarioDto;
import com.softulp.appgmaldonado.modelo.CrearComentarioDto;
import com.softulp.appgmaldonado.modelo.Like;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.modelo.RecetaPost;
import com.softulp.appgmaldonado.modelo.RecetaResponse;
import com.softulp.appgmaldonado.modelo.RecetaSubirDto;
import com.softulp.appgmaldonado.modelo.Respuesta;
import com.softulp.appgmaldonado.modelo.Usuario;
import com.softulp.appgmaldonado.modelo.UsuarioDto;
import com.softulp.appgmaldonado.modelo.UsuarioPerfilDto;
import com.softulp.appgmaldonado.modelo.Wrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiService {

    public static final String URL_BASE = "http://192.168.0.108:5000/";
    private static SharedPreferences sharedPreferences;
    private static ApiInterface apiInterface;

    public static ApiInterface getApiInterface() {
        if (apiInterface == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            apiInterface = retrofit.create(ApiInterface.class);
        }
        return apiInterface;
    }
    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        }
    }
    public static void guardarToken(Context context, String token){
        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("token",token);
        editor.commit();

    }
    public static String leerToken(Context context){
        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        return sp.getString("token","");
    }
    public interface ApiInterface {
        /////////////////////////////// U S U A R I O ///////////////////////////////////////////////

        @FormUrlEncoded
        @POST("api/Usuario/login")
        Call<String> login(@Field("Usuario") String usuario, @Field("Clave") String clave);


        @GET("api/Usuario/obtenerusuario")
        Call<Usuario> obtenerusuarioB(@Header("Authorization") String token);

        @PUT("api/Usuario/editar")
        Call<Usuario> Editar(@Header("Authorization") String token, @Body UsuarioPerfilDto usuarioDto);
        @PUT("api/Usuario/editClave")
        Call<Void> clave(@Header("Authorization") String token, @Body CambiarClaveModel model);
        @PUT("api/Usuario/actualizarFoto")
        Call<Usuario> actualizarFoto(@Header("Authorization") String token, @Body ActualizarFotoDto actualizarFotoDto);
        @GET("api/Usuario/user")
        Call<Usuario> getUserInfo(@Header("Authorization") String token);

        @FormUrlEncoded
        @POST("api/Usuario/email")
        Call<Usuario> inciarRecupero(@Field("correo") String correo);

            @POST("api/Usuario/crear")
            Call<UsuarioDto> crearUsuario(@Body UsuarioDto usuarioDto);




        /////////////////////////////// I N I C I O - F R A G M E N T ///////////////////////////////////////////////
        @POST("api/Receta/crear")
        Call<Receta> crearReceta(@Header("Authorization") String token,@Body Receta receta);

        @GET("api/Receta/nueva")
        Call<ApiResponse> ObtenerPostReceta(@Header("Authorization") String token);
        @POST("api/Receta/{id}/like")
        Call<Void> darLike(@Path("id") int id, @Body Like like,@Header("Authorization") String token);
        @DELETE("api/Receta/{id}/deletlike")
        Call<Void> QuitarLike(@Path("id") int recetaId,@Header("Authorization") String token);
        @GET("api/Receta/obtenerLikes")
        Call<Wrapper<Like>> obtenerLikesUsuario(@Header("Authorization") String token);
        @GET("api/Receta/receta/{id}")
        Call<List<Comentario>> ObtenerComentarios(@Path("id") int recetaId,@Header("Authorization") String token);
        @GET("api/Receta/Obtenerxid/{id}")
        Call<Receta> ObtenerRecetaXid(@Path("id") int recetaId,@Header("Authorization") String token);
        @PUT("api/Receta/Actualizar/{recetaID}")
        Call<RecetaSubirDto> ActualizarReceta(@Path("recetaID") int recetaID, @Body RecetaSubirDto recetaDto, @Header("Authorization") String token);
        @DELETE("api/Receta/Eliminar/{recetaID}")
        Call<Void> EliminarReceta(@Path("recetaID") int recetaID, @Header("Authorization") String token);

        @GET("api/Receta/buscarPorTipoCocina/{tipoCocina}")
        Call<ApiResponse> BuscarPorCategoria(@Path("tipoCocina") String tipoCocina, @Header("Authorization") String token);
        @GET("api/Receta/buscarRecetas/{query}")
        Call<ApiResponse> BuscarPorQuery(@Path("query") String query, @Header("Authorization") String token);
        @GET("api/Receta/ObtenerRecetasPorUsuario/{usuarioID}")
        Call<Wrapper<Receta>> ObtenerRecetaXUsuario(@Path("usuarioID") int usuarioID,@Header("Authorization") String token);
        @POST("api/Comentario/{id}/comentar")
        Call<Void> ComentarPosteo(@Path("id") int recetaId, @Header("Authorization") String token, @Body CrearComentarioDto comentarioDto);

        @DELETE("api/Comentario/{id}")
        Call<Void> borrarComentario(@Path("id") int comentarioId,@Header("Authorization") String token);
        @POST("api/RecetasFavoritas/AgregarAFavoritos/{recetaId}")
        Call<Void> saveRecetasFavoritas(@Path("recetaId") int recetaId, @Header("Authorization") String token);

        @DELETE("api/RecetasFavoritas/EliminarDeFavoritos/{recetaId}")
        Call<Void> deleteRecetasFavoritas(@Path("recetaId") int recetaId, @Header("Authorization") String token);

        @GET("api/RecetasFavoritas/ObtenerFavoritas")
        Call<ApiResponse> ObtenerRecetasFvt(@Header("Authorization") String token);

    }

}
