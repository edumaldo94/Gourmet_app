package com.softulp.appgmaldonado.ui.agregar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.databinding.FragmentRecetaBinding;
import com.softulp.appgmaldonado.modelo.Receta;
/*
public class CrearRecetaFragment extends Fragment {
    private RecetaViewModel viewModel;
    private FragmentRecetaBinding binding;
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private Uri imgUri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecetaBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(RecetaViewModel.class);

        int recetaId = getArguments().getInt("recetaId");
        viewModel.cargarReceta(recetaId);

        viewModel.getReceta().observe(getViewLifecycleOwner(), new Observer<Receta>() {
            @Override
            public void onChanged(Receta receta) {
                binding.tvTitulo.setText(receta.getTitulo());
                binding.tvIngredientes.setText(receta.getIngredientes());
                binding.tvPasos.setText(receta.getPasos());
                Glide.with(requireContext())
                        .load(ApiService.URL_BASE + receta.getFoto())
                        .placeholder(R.drawable.imagen_default)
                        .into(binding.imgReceta);
            }
        });

        binding.btnSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });

        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                viewModel.cargarImg(result);
            }
        });

        viewModel.getImgUri().observe(getViewLifecycleOwner(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                imgUri = uri;
                binding.imgReceta.setImageURI(uri);
            }
        });

        return binding.getRoot();
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }
}
        */