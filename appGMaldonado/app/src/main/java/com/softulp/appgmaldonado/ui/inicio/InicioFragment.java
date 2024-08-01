package com.softulp.appgmaldonado.ui.inicio;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.softulp.appgmaldonado.LoginActivityViewModel;
import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.databinding.FragmentInicioBinding;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.modelo.Usuario;
import com.softulp.appgmaldonado.request.ApiService;
import com.softulp.appgmaldonado.ui.perfil.PerfilViewModel;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;
    private InicioViewModel inicioViewModel;
    private PerfilViewModel pvm;

private LoginActivityViewModel lgvm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        pvm = new ViewModelProvider(this).get(PerfilViewModel.class);
        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);
        lgvm = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        binding.recyclerInicio.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.progressBar.setVisibility(View.VISIBLE);

        inicioViewModel.cargarDatos();
        inicioViewModel.cargarRecetasFvt();
      inicioViewModel.obtenerLikesUsuario();
        binding.imgPerfil.setOnClickListener(v -> {
            // Get the NavController
            NavController navController = Navigation.findNavController((Activity) getActivity(), R.id.nav_hostfragment);

            // Pass the necessary data to the RecetaFragment
            Bundle bundle = new Bundle();
            Log.d("teresa","id "+inicioViewModel.getUsuarioActual().getUsuarioID());
            bundle.putInt("usuarioID", inicioViewModel.getUsuarioActual().getUsuarioID());

            // Navigate to RecetaFragment
            navController.navigate(R.id.action_page_inicio_to_resultadosFragment, bundle);
        });


pvm.mUsuario().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
    @Override
    public void onChanged(Usuario usuario) {
        Log.d("foto","sad "+usuario.getFoto()+" "+usuario.getApellido());
        Glide.with(requireContext())
                .load(ApiService.URL_BASE + usuario.getFoto())
                .placeholder(R.drawable.imagen_default)
                .into(binding.imgPerfil);

    }
});

        inicioViewModel.getRecetas().observe(getViewLifecycleOwner(), recetas -> {
            Log.d("vacio", "inicioViewModel");
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
            binding.recyclerInicio.setLayoutManager(gridLayoutManager);
            InicioAdapter adapter = new InicioAdapter(getActivity(), recetas,getLayoutInflater(), inicioViewModel);
            binding.recyclerInicio.setAdapter(adapter);
            binding.progressBar.setVisibility(View.GONE);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

