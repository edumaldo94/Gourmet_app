package com.softulp.appgmaldonado.ui.inicio;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.modelo.Comentario;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.modelo.Respuesta;
import com.softulp.appgmaldonado.modelo.Usuario;
import com.softulp.appgmaldonado.request.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// CommentAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.modelo.Comentario;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Comentario> comentarios;
    private InicioViewModel inicioViewModel;
    private Context context;
    public CommentAdapter(Context context,List<Comentario> comentarios, InicioViewModel inicioViewModel) {
        this.context = context;
        this.comentarios = comentarios;
        this.inicioViewModel = inicioViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comentario comentario = comentarios.get(position);
        holder.textViewUsuario.setText(comentario.getUsuario().getNombre());
        holder.textViewComentario.setText(comentario.getComentario());
        Glide.with(context)
                .load(ApiService.URL_BASE+comentario.getUsuario().getFoto())
                .placeholder(R.drawable.imagen_default)
                .into(holder.imgperfil);
        Usuario usuarioActual = inicioViewModel.getUsuarioActual();
        int comentRecetaId = comentario.getRecetaID();

        if (usuarioActual != null) {
            if (usuarioActual.getUsuarioID() == comentario.getUsuario().getUsuarioID() || usuarioActual.getUsuarioID() == comentRecetaId) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(v -> {
                    if (position < comentarios.size()) {

                        inicioViewModel.eliminarComentario(comentario.getComentarioID());
                        comentarios.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, comentarios.size());
                    }
                });
            } else {
                holder.btnDelete.setVisibility(View.GONE);
            }
        } else {
            holder.btnDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewUsuario;
        public TextView textViewComentario;
        ImageView btnDelete, imgperfil;

        public ViewHolder(View view) {
            super(view);
            textViewUsuario = view.findViewById(R.id.textViewUsuario);
            textViewComentario = view.findViewById(R.id.textViewComentario);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            imgperfil = itemView.findViewById(R.id.imageViewUsuario);
        }
    }
}
