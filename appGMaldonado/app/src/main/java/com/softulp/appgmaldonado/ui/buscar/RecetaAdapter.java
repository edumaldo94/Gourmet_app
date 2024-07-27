package com.softulp.appgmaldonado.ui.buscar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.request.ApiService;
import com.softulp.appgmaldonado.ui.inicio.InicioViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder> {
    private List<Receta> recetas;
    private LayoutInflater inflater;
    private Context context;
    private String sourceFragment;

    public RecetaAdapter(Context context, List<Receta> recetas, LayoutInflater inflater, String sourceFragment) {
        this.context = context;
        this.recetas = recetas;
        this.inflater = inflater;
        this.sourceFragment = sourceFragment;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_receta, parent, false);
        return new RecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {
        Receta receta = recetas.get(position);
        holder.txtNombre.setText(receta.getUsuario().getNombre() + " " + receta.getUsuario().getApellido());
        holder.txtTitulo.setText(receta.getTitulo());
        Log.d("RecetaAdapter", "Receta: " + receta.getTitulo());

        Glide.with(context)
                .load(ApiService.URL_BASE + receta.getUsuario().getFoto())
                .placeholder(R.drawable.imagen_default)
                .into(holder.imgPerfil);

        Glide.with(context)
                .load(ApiService.URL_BASE + receta.getFotoPortada())
                .placeholder(R.drawable.imagen_default)
                .into(holder.imgReceta);
        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("recetaID", receta.getRecetaID());
            Log.d("recetaID","--- "+receta.getRecetaID());
            if ("page_buscar".equals(sourceFragment)) {
                Navigation.findNavController(v).navigate(R.id.action_page_buscar_to_recetaFragment, bundle);
            } else if ("resultadosFragment".equals(sourceFragment)) {
                Navigation.findNavController(v).navigate(R.id.action_resultadosFragment_to_recetaFragment, bundle);
            }});

    }

    @Override
    public int getItemCount() {
        Log.d("RecetaAdapter", "Receta size: " + recetas.size());
        return recetas.size();
    }

    static class RecetaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPerfil;
        private ImageView imgReceta;
        private TextView txtNombre;
        private TextView txtTitulo;

        public RecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPerfil = itemView.findViewById(R.id.imgPerfil);
            imgReceta = itemView.findViewById(R.id.imgReceta);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
        }
    }
}
