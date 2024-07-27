package com.softulp.appgmaldonado.ui.inicio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.modelo.Like;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.modelo.RecetaPost;
import com.softulp.appgmaldonado.request.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// adapters/RecetaAdapter.java
/*public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.ViewHolder> {

    private List<Receta> recetas;
    private Context context;

    public RecetaAdapter(Context context, List<Receta> recetas) {
        this.context = context;
        this.recetas = recetas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_receta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Receta receta = recetas.get(position);

        holder.txtNombre.setText(receta.getUsuario().getNombre() + " " + receta.getUsuario().getApellido());
        holder.txtDescripcion.setText(receta.getDescripcion());
        // Cargar imagenes usando Glide o Picasso
        // Glide.with(context).load(receta.getFotoPortada()).into(holder.imgReceta);

        holder.btnLike.setOnClickListener(v -> {
            // Manejar el evento de dar like
        });

        holder.btnComment.setOnClickListener(v -> {
            // Manejar el evento de comentar
        });
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgReceta, imgPerfil;
        TextView txtNombre, txtDescripcion;
        ImageButton btnLike, btnComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgReceta = itemView.findViewById(R.id.imgReceta);
            imgPerfil = itemView.findViewById(R.id.imgPerfil);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnComment = itemView.findViewById(R.id.btnComment);
        }
    }
}*/
