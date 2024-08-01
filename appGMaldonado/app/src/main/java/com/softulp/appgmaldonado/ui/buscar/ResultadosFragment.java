package com.softulp.appgmaldonado.ui.buscar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softulp.appgmaldonado.LoginActivityViewModel;
import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.databinding.FragmentInicioBinding;

import com.softulp.appgmaldonado.databinding.FragmentResultadosCategoriaBinding;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.ui.inicio.InicioAdapter;
import com.softulp.appgmaldonado.ui.inicio.InicioViewModel;
import com.softulp.appgmaldonado.ui.inicio.RecetaViewModel;
import com.softulp.appgmaldonado.ui.perfil.PerfilViewModel;

import java.util.ArrayList;
import java.util.List;

// ResultadosFragment.java
public class ResultadosFragment extends Fragment {
    private BuscarViewModel buscarViewModel;
    private RecetaViewModel recetaViewModel;
    private FragmentResultadosCategoriaBinding binding;
    private InicioViewModel perfilViewModel;
    private RecetaAdapter adapter;
    private RecetaAdapter adapterFvt;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentResultadosCategoriaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buscarViewModel = new ViewModelProvider(requireActivity()).get(BuscarViewModel.class);
        recetaViewModel = new ViewModelProvider(requireActivity()).get(RecetaViewModel.class);
        perfilViewModel = new ViewModelProvider(requireActivity()).get(InicioViewModel.class);

        Toolbar toolbar = binding.toolbar;
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
                activity.getSupportActionBar().setTitle("Gourmet");
            }
        }

        // Configurar RecyclerView con GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        binding.recyclerVCat.setLayoutManager(gridLayoutManager);
        adapter = new RecetaAdapter(getActivity(), new ArrayList<>(), getLayoutInflater(), "resultadosFragment");
        binding.recyclerVCat.setAdapter(adapter);
/*
        LinearLayoutManager linearLayoutManagerFvt = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerRFVTas.setLayoutManager(linearLayoutManagerFvt);
        adapterFvt = new RecetaAdapter(getActivity(), new ArrayList<>(), getLayoutInflater(), "resultadosFragment");
        binding.recyclerRFVTas.setAdapter(adapterFvt);
*/

        // Obtener la categoría seleccionada del bundle
        String categoria = getArguments().getString("categoria");
        int usuarioID = getArguments().getInt("usuarioID", -1);
        Log.d("usuarioid","1 "+usuarioID);
        if (categoria != null) {
            // Buscar recetas por categoría
            buscarViewModel.buscarPorCategoria(categoria);
        } else if (usuarioID != -1) {
           Log.d("usuarioid","2 "+usuarioID);
            recetaViewModel.cargarRecetasUsuario(usuarioID);
            recetaViewModel.cargarRecetasFvt();
            if (esUsuarioLogueado(usuarioID)) {
                binding.rctfav.setVisibility(View.VISIBLE);
                recetaViewModel.cargarRecetasFvt();
                LinearLayoutManager linearLayoutManagerFvt = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                binding.recyclerRFVTas.setLayoutManager(linearLayoutManagerFvt);
                adapterFvt = new RecetaAdapter(getActivity(), new ArrayList<>(), getLayoutInflater(), "resultadosFragment");
                binding.recyclerRFVTas.setAdapter(adapterFvt);
            }
        }


        recetaViewModel.getRecetasPorUsuario().observe(getViewLifecycleOwner(), recetas -> {
            Log.d("ResultadosFragment", "qweqwe qweqwewq: " + recetas.size());
            adapter.setRecetas(recetas);
        });
        // Observar cambios en recetasPorCategoria para búsqueda por categoría
        buscarViewModel.getRecetasPorCategoria().observe(getViewLifecycleOwner(), recetas -> {
            Log.d("ResultadosFragment", "Recetas observadas: " + recetas.size());
            adapter.setRecetas(recetas);
        });

recetaViewModel.getRecetasFvt().observe(getViewLifecycleOwner(), new Observer<List<Receta>>() {
    @Override
    public void onChanged(List<Receta> recetas) {
        if (adapterFvt != null) {
            adapterFvt.setRecetas(recetas);
        }
    }
});
        toolbar.setNavigationOnClickListener(v -> NavHostFragment.findNavController(ResultadosFragment.this).navigateUp());

        return root;
    }

    private boolean esUsuarioLogueado(int usuarioID) {
        int bb=perfilViewModel.getUsuarioActual().getUsuarioID();
        Log.d("terremoto","--> "+bb);
        if (bb == usuarioID){
            return true;
        }

        return false;
    }

}

