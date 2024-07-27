package com.softulp.appgmaldonado.ui.buscar;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.databinding.FragmentBuscarBinding;
import com.softulp.appgmaldonado.databinding.FragmentResultadosCategoriaBinding;
import com.softulp.appgmaldonado.modelo.Categoria;

import java.util.ArrayList;
import java.util.List;


// BuscarFragment.java
public class BuscarFragment extends Fragment {
    private BuscarViewModel vm;
    private RecyclerView rvCategorias;
    private CategoriaAdapter categoriaAdapter;
    private RecetaAdapter recetaAdapter;
    private EditText etBuscar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);

        vm = new ViewModelProvider(requireActivity()).get(BuscarViewModel.class);
        rvCategorias = view.findViewById(R.id.rvCategorias);
        etBuscar = view.findViewById(R.id.etBuscar);

        // Inicializar los adaptadores
        categoriaAdapter = new CategoriaAdapter(new CategoriaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Categoria categoria) {
                // Navegar a ResultadosFragment con la categoría seleccionada
                Bundle bundle = new Bundle();
                bundle.putString("categoria", categoria.getNombre());
                NavController navController = NavHostFragment.findNavController(BuscarFragment.this);
                navController.navigate(R.id.action_page_buscar_to_resultadosFragment, bundle);
            }
        });

        recetaAdapter = new RecetaAdapter(getActivity(), new ArrayList<>(), getLayoutInflater(),"page_buscar");

        rvCategorias.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCategorias.setAdapter(categoriaAdapter); // Mostrar categorías inicialmente

        // Observar cambios en las categorías
        vm.getCategorias().observe(getViewLifecycleOwner(), new Observer<List<Categoria>>() {
            @Override
            public void onChanged(List<Categoria> categorias) {
                categoriaAdapter.setCategorias(categorias);
            }
        });

        // Observar cambios en las recetas filtradas
        vm.getRecetasFiltradas().observe(getViewLifecycleOwner(), recetas -> {
            if (recetaAdapter != null) {
                recetaAdapter.setRecetas(recetas);
                // Cambiar el adaptador del RecyclerView a recetaAdapter
                if (!etBuscar.getText().toString().isEmpty()) {
                    // Cambiar el adaptador del RecyclerView a recetaAdapter solo si hay una búsqueda
                    rvCategorias.setAdapter(recetaAdapter);
                }
            } else {
                Log.e("BuscarFragment", "recetaAdapter es null");
            }
        });

        // Configurar el TextWatcher para la búsqueda
        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No se necesita implementar nada aquí
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Llama al método filtrarRecetas() con el texto de búsqueda
                if (s.toString().isEmpty()) {
                    // Si el campo de búsqueda está vacío, mostrar las categorías
                    Log.e("BuscarFragment", "campo vacio");
                    rvCategorias.setLayoutManager(new LinearLayoutManager(getContext()));
                    rvCategorias.setAdapter(categoriaAdapter);
                } else {
                    // De lo contrario, mostrar las recetas filtradas
                    vm.filtrarRecetas(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                // No se necesita implementar nada aquí
            }
        });
        // Restablecer la vista cuando se vuelve al fragmento
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (!etBuscar.getText().toString().isEmpty()) {
                    // Limpiar el campo de búsqueda y mostrar las categorías
                    etBuscar.setText("");
                    rvCategorias.setAdapter(categoriaAdapter);
                } else {
                    // Realizar la acción de retroceso normal
                    setEnabled(false);
                    requireActivity().onBackPressed();
                }
            }
        });
        return view;
    }
}
