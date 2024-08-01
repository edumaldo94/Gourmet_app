package com.softulp.appgmaldonado.ui.inicio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.modelo.Comentario;
import com.softulp.appgmaldonado.modelo.Like;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.modelo.Usuario;
import com.softulp.appgmaldonado.request.ApiService;
import com.softulp.appgmaldonado.ui.perfil.PerfilViewModel;

import java.util.List;

public class InicioAdapter extends RecyclerView.Adapter<InicioAdapter.InicioViewHolder> {
    private Context context;
    private List<Receta> recetas;

    private LayoutInflater inflater;
    private InicioViewModel viewModel;

    public InicioAdapter(Context context, List<Receta> recetas, LayoutInflater inflater, InicioViewModel viewModel) {
        this.context = context;
        this.recetas = recetas;
        this.inflater = inflater;
        this.viewModel = viewModel;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public InicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_inicio, parent, false);
        return new InicioViewHolder(view);
    }
//maldonado19994@gmail.com
    @Override
    public void onBindViewHolder(@NonNull InicioViewHolder holder, int position) {
        Receta receta = recetas.get(position);


        holder.btnRecetaDetalle.setOnClickListener(v -> {
            // Get the NavController
            NavController navController = Navigation.findNavController((Activity) context, R.id.nav_hostfragment);

            // Pass the necessary data to the RecetaFragment
            Bundle bundle = new Bundle();
            bundle.putInt("recetaID", receta.getRecetaID());

            // Navigate to RecetaFragment
            navController.navigate(R.id.action_inicioFragment_to_recetaFragment, bundle);
        });

        Glide.with(context)
                .load(ApiService.URL_BASE+receta.getFotoPortada())
                .placeholder(R.drawable.imagen_default)
                .into(holder.imgReceta);

        Log.d("ImageURL", ApiService.URL_BASE + receta.getFotoPortada());

        Glide.with(context)
                .load(ApiService.URL_BASE+receta.getUsuario().getFoto())
                .placeholder(R.drawable.imagen_default)
                .into(holder.imgPerfil);

        Log.d("ImageURL", ApiService.URL_BASE + receta.getUsuario().getFoto());
        holder.txtDescripcion.setText(receta.getDescripcion());

        // Mostrar nombre de usuario
        String nombreCompleto = receta.getUsuario().getNombre() + " " + receta.getUsuario().getApellido();
        holder.txtNombre.setText(nombreCompleto);
        holder.txtNombre.setOnClickListener(v -> {
            // Get the NavController
            NavController navController = Navigation.findNavController((Activity) context, R.id.nav_hostfragment);

            // Pass the necessary data to the RecetaFragment
            Bundle bundle = new Bundle();
            Log.d("teresa","id "+receta.getUsuario().getUsuarioID());
            bundle.putInt("usuarioID", receta.getUsuario().getUsuarioID());

            // Navigate to RecetaFragment
            navController.navigate(R.id.action_page_inicio_to_resultadosFragment, bundle);
        });
        holder.imgPerfil.setOnClickListener(v -> {
            // Get the NavController
            NavController navController = Navigation.findNavController((Activity) context, R.id.nav_hostfragment);

            // Pass the necessary data to the RecetaFragment
            Bundle bundle = new Bundle();
            Log.d("teresa","id "+receta.getUsuario().getUsuarioID());
            bundle.putInt("usuarioID", receta.getUsuario().getUsuarioID());

            // Navigate to RecetaFragment
            navController.navigate(R.id.action_page_inicio_to_resultadosFragment, bundle);
        });
        // Mostrar cantidad de likes
        holder.likes.setText(String.valueOf(receta.getLikes().getCantidad()));


        boolean isLiked = viewModel.hasLiked(receta.getRecetaID());
        receta.setLiked(isLiked);
        if (isLiked) {
            holder.btnLike.setImageResource(R.drawable.icon_likered11);
        } else {
            holder.btnLike.setImageResource(R.drawable.icon_likeblack2);
        }

        holder.btnLike.setOnClickListener(v -> {
            boolean currentlyLiked = receta.isLiked();
            Like like = new Like();
            like.setRecetaID(receta.getRecetaID());

            if (currentlyLiked) {
                viewModel.borrarLike(receta.getRecetaID());
                viewModel.removeLikeFromUserLikes(receta.getRecetaID());
                receta.getLikes().setCantidad(receta.getLikes().getCantidad() - 1);
                receta.setLiked(false);
                holder.btnLike.setImageResource(R.drawable.icon_likeblack2);
            } else {
                viewModel.enviarLike(receta.getRecetaID(), like);
                viewModel.addLikeToUserLikes(like);
                receta.getLikes().setCantidad(receta.getLikes().getCantidad() + 1);
                receta.setLiked(true);
                holder.btnLike.setImageResource(R.drawable.icon_likered11);
            }

            holder.likes.setText(String.valueOf(receta.getLikes().getCantidad()));
            notifyItemChanged(holder.getAdapterPosition());
        });

        if (receta.isMostrarComentarios()) {
            holder.recyclerComentarios.setVisibility(View.VISIBLE);
            holder.commentSection.setVisibility(View.VISIBLE);
        } else {
            holder.recyclerComentarios.setVisibility(View.GONE);
            holder.commentSection.setVisibility(View.GONE);
        }

        holder.recyclerComentarios.setLayoutManager(new LinearLayoutManager(context));
        CommentAdapter comentarioAdapter = new CommentAdapter(context,receta.getComentarios(),viewModel);
        holder.recyclerComentarios.setAdapter(comentarioAdapter);



        holder.comentarios.setOnClickListener(v -> {

            CommentBottomSheetDialogFragment bottomSheetFragment = new CommentBottomSheetDialogFragment(context,receta);
            Log.d("cantt","paso 0"+receta.getCantidadComentarios());
            holder.cantComent.setText(String.valueOf(receta.getCantidadComentarios()));
            bottomSheetFragment.show(((FragmentActivity) context).getSupportFragmentManager(), bottomSheetFragment.getTag());

        });

        holder.btnSendComment.setOnClickListener(v -> {
            String nuevoComentario = holder.etComment.getText().toString().trim();
            if (!nuevoComentario.isEmpty()) {
                // Obtener el usuario actual logueado del ViewModel
                Usuario usuarioActual = viewModel.getUsuarioActual();
Log.d("teresa","sad "+usuarioActual.getNombre());
                // Crear el nuevo comentario
                Comentario comentario = new Comentario();
                comentario.setUsuario(usuarioActual); // Establece el usuario actual logueado
                comentario.setComentario(nuevoComentario);
                receta.setCantidadComentarios(receta.getCantidadComentarios() + 1);
                holder.cantComent.setText(String.valueOf(receta.getCantidadComentarios()));
                // Agregar el comentario a la lista localmente para actualizar la UI
                receta.getComentarios().add(comentario);
                comentarioAdapter.notifyDataSetChanged();
                holder.etComment.setText("");
                holder.cantComent.setText(String.valueOf(receta.getCantidadComentarios()));

                // Llamar a la función para enviar el comentario al ViewModel
                viewModel.ComentarPublicacion(receta.getRecetaID(), nuevoComentario);

            }
        });


        holder.btnComment.setOnClickListener(v -> {
            int visibility = holder.commentSection.getVisibility() == View.GONE ? View.VISIBLE : View.GONE;
            holder.commentSection.setVisibility(visibility);
        });
        holder.cantComent.setText(String.valueOf(receta.getCantidadComentarios()));

        // Actualizar el ícono del botón de favorito según el estado
if(receta.isEsFavorita()== true){
    holder.btnFavorito.setImageResource(R.drawable.ic_favoritored);
}
        holder.btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int recetaID=receta.getRecetaID();
                if(receta.isEsFavorita()== true){
                    viewModel.borrarRecetasFav(recetaID);
                    holder.btnFavorito.setImageResource(R.drawable.ic_favorito);
                }else {
                    viewModel.guardarRecetasFav(recetaID);
                    holder.btnFavorito.setImageResource(R.drawable.ic_favoritored);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public static class InicioViewHolder extends RecyclerView.ViewHolder {
        ImageView imgReceta, imgPerfil;
        TextView txtNombre, txtDescripcion;
        ImageView btnComment,btnRecetaDetalle,btnFavorito;
        ImageView btnLike;
        TextView likes;
        TextView cantComent;
        LinearLayout comentarios;
        RecyclerView recyclerComentarios;
        EditText etComment;
        ImageButton btnSendComment;
        LinearLayout commentSection;

        public InicioViewHolder(@NonNull View itemView) {
            super(itemView);
            imgReceta = itemView.findViewById(R.id.imgReceta);
            imgPerfil = itemView.findViewById(R.id.imgPerfil);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnComment = itemView.findViewById(R.id.btnComment);
            likes = itemView.findViewById(R.id.likes);
            cantComent = itemView.findViewById(R.id.cantidadComent);
            comentarios = itemView.findViewById(R.id.comentarios);
            recyclerComentarios = itemView.findViewById(R.id.recyclerComentarios);
            etComment = itemView.findViewById(R.id.etComment);
            btnSendComment = itemView.findViewById(R.id.btnSendComment);
            commentSection = itemView.findViewById(R.id.commentSection);
            btnRecetaDetalle=itemView.findViewById(R.id.btnRecetaDetalle);
            btnFavorito=itemView.findViewById(R.id.btnFavorito);
        }
    }
}
