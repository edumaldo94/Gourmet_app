package com.softulp.appgmaldonado.ui.buscar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.modelo.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {

    private List<Categoria> categorias;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Categoria categoria);
    }

    public CategoriaAdapter(OnItemClickListener listener) {
        this.listener = listener;
        categorias = new ArrayList<>();
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria, parent, false);
        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = categorias.get(position);
        holder.bind(categoria);
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    class CategoriaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagen;
        private TextView nombre;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.ivCategoria);
            nombre = itemView.findViewById(R.id.tvCategoria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(categorias.get(getAdapterPosition()));
                }
            });
        }

        public void bind(Categoria categoria) {
            // Aquí se puede cargar la imagen y el nombre de la categoría
            nombre.setText(categoria.getNombre());
            imagen.setImageResource(categoria.getImagen());
        }
    }
}
