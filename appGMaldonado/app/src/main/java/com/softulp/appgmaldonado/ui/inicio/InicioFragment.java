package com.softulp.appgmaldonado.ui.inicio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.softulp.appgmaldonado.LoginActivityViewModel;
import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.databinding.FragmentInicioBinding;
import com.softulp.appgmaldonado.request.ApiService;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;
    private InicioViewModel inicioViewModel;

private LoginActivityViewModel lgvm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);
        lgvm = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        binding.recyclerInicio.setLayoutManager(new LinearLayoutManager(getContext()));
       // UsuarioActual.getInstance().setUsuarioId(2);
      //  int usuarioId = UsuarioActual.getInstance().getUsuarioId();
     //   Log.d("111", "Usuario ID: " + usuarioId);

        Glide.with(requireContext())
                .load(ApiService.URL_BASE + inicioViewModel.getUsuarioActual().getFoto())
                .placeholder(R.drawable.imagen_default)
                .into(binding.imgPerfil);
      inicioViewModel.obtenerLikesUsuario();
        inicioViewModel.cargarDatos();

        inicioViewModel.getRecetas().observe(getViewLifecycleOwner(), recetas -> {
            Log.d("vacio", "inicioViewModel");
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
            binding.recyclerInicio.setLayoutManager(gridLayoutManager);
            InicioAdapter adapter = new InicioAdapter(getActivity(), recetas, getLayoutInflater(), inicioViewModel);
            binding.recyclerInicio.setAdapter(adapter);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

