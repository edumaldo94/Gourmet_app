package com.softulp.appgmaldonado.ui.agregar;

import android.content.Context;
import android.os.Bundle;
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

import java.util.List;
/*
public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.ViewHolder> {
    private Context context;
    private List<Receta> recetas;
    private LayoutInflater li;

    public RecetaAdapter(List<Receta> recetas, Context context) {
        this.context = context;
        this.recetas = recetas;
        this.li = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecetaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = li.inflate(R.layout.fragment_crear_receta, parent, false);  // Asegúrate de usar el layout correcto
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaAdapter.ViewHolder holder, int position) {
        Receta receta = recetas.get(position);
        holder.titulo.setText(receta.getTitulo());
        holder.ingredientes.setText(receta.getIngredientes());
        holder.pasos.setText(receta.getPasos());
        holder.tiempo.setText(receta.getTiempoPreparacion());
        holder.porciones.setText(receta.getPorciones());
        // Aquí deberías cargar la imagen usando Glide u otra librería si tienes una imagen en Receta
        Glide.with(context).load(receta.getFotoPortada()).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView ingredientes;
        TextView pasos;
        TextView tiempo;
        TextView porciones;
         ImageView imagen; // Si tienes una imagen

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.etTitulo);
            ingredientes = itemView.findViewById(R.id.etIngredientes);
            pasos = itemView.findViewById(R.id.etPasos);
            tiempo = itemView.findViewById(R.id.etTiempo);
            porciones = itemView.findViewById(R.id.etPorciones);
             imagen = itemView.findViewById(R.id.imgReceta); // Si tienes una imagen
            
        }
    }
}
*/