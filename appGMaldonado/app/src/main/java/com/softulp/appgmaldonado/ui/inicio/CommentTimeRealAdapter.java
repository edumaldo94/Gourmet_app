package com.softulp.appgmaldonado.ui.inicio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.modelo.Comentario;
import com.softulp.appgmaldonado.modelo.ComentarioDto;

import java.util.ArrayList;
import java.util.List;
/*
public class CommentTimeRealAdapter extends RecyclerView.Adapter<CommentTimeRealAdapter.CommentViewHolder> {
   private Context context;
    private List<ComentarioDto> comentarios;

    public CommentTimeRealAdapter(Context context, List<ComentarioDto> comentarios) {
        this.context = context;
        this.comentarios = comentarios != null ? comentarios : new ArrayList<>();
    }

    @NonNull
    @Override
    public CommentTimeRealAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentTimeRealAdapter.CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentTimeRealAdapter.CommentViewHolder holder, int position) {
        ComentarioDto comentario = comentarios.get(position);

        String nombreUsuario = comentario.getUsuario().getNombre();
        holder.txtUsuario.setText(nombreUsuario);
        holder.txtComentario.setText(comentario.getComentario());
        holder.btnDelete.setOnClickListener(v -> {
            comentarios.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView txtUsuario, txtComentario;
        ImageView btnDelete;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsuario = itemView.findViewById(R.id.txtUsuario);
            txtComentario = itemView.findViewById(R.id.txtComentario);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}*/

