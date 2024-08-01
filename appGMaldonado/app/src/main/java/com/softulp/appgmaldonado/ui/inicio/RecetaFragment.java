package com.softulp.appgmaldonado.ui.inicio;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.databinding.FragmentRecetaDetalleBinding;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.modelo.RecetaSubirDto;
import com.softulp.appgmaldonado.modelo.Usuario;
import com.softulp.appgmaldonado.request.ApiService;
import com.softulp.appgmaldonado.ui.buscar.ResultadosFragment;

import java.io.ByteArrayOutputStream;

// RecetaFragment.java
public class RecetaFragment extends Fragment {
    private RecetaViewModel recetaViewModel;
    int recetaID;
    private FragmentRecetaDetalleBinding binding;
    private InicioViewModel incvm;
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private Uri imageUris;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecetaDetalleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.progressBar.setVisibility(View.VISIBLE);
        recetaViewModel = new ViewModelProvider(this).get(RecetaViewModel.class);
        incvm = new ViewModelProvider(this).get(InicioViewModel.class);
        Toolbar toolbar = binding.toolbar;
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back); // Cambia a tu ícono de flecha si es necesario
                activity.getSupportActionBar().setTitle("Gourmet");

            }
        }

        // Configurar NumberPickers
        binding.etTiempo.setMinValue(0);
        binding.etTiempo.setMaxValue(100);
        binding.etTiempoMinutos.setMinValue(0);
        binding.etTiempoMinutos.setMaxValue(59);

        // Configurar Spinners
        ArrayAdapter<CharSequence> dificultadAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.dificultad_array, R.layout.spinner_item);
        dificultadAdapter.setDropDownViewResource(R.layout.spinner_item);

        binding.etDificultad.setAdapter(dificultadAdapter);

        ArrayAdapter<CharSequence> tipoCocinaAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.tipo_cocina_array, R.layout.spinner_item);
        tipoCocinaAdapter.setDropDownViewResource(R.layout.spinner_item);
        binding.etTipoCocina.setAdapter(tipoCocinaAdapter);
        if (getArguments() != null) {
            recetaID = getArguments().getInt("recetaID");
            recetaViewModel.getRecetaById(recetaID);
        }

        if (getArguments() != null) {
             recetaID = getArguments().getInt("recetaID");
            recetaViewModel.getRecetaById(recetaID);

        }
recetaViewModel.getReceta().observe(getViewLifecycleOwner(), new Observer<Receta>() {
    @Override
    public void onChanged(Receta receta) {
        Glide.with(requireContext())
                .load(ApiService.URL_BASE + receta.getUsuario().getFoto())
                .placeholder(R.drawable.imagen_default)
                .into(binding.imgPerfil);

        //binding.txtNombre.setText(receta.getUsuario().getNombre() + " " + receta.getUsuario().getApellido());
        binding.etTitulo.setText(receta.getTitulo());

        Glide.with(requireContext())
                .load(ApiService.URL_BASE + receta.getFotoPortada())
                .placeholder(R.drawable.imagen_default)
                .into(binding.fotoReceta);

        binding.etDescripcion.setText(receta.getDescripcion());
        binding.etIngredientes.setText(receta.getIngredientes());
        binding.etPasos.setText(receta.getPasos());

        String tiempoPreparacion = receta.getTiempoPreparacion();
        String[] tiempoParts = tiempoPreparacion.split("[: Hs]+");

        if (tiempoParts.length >= 2) {
            int hora = Integer.parseInt(tiempoParts[0].trim());
            int minutos = Integer.parseInt(tiempoParts[1].trim());
            binding.etTiempo.setValue(hora);
            binding.etTiempoMinutos.setValue(minutos);
        }

        binding.etPorciones.setText(receta.getPorciones());

        if (receta.getDificultad() != null) {
            int dificultadPosition = dificultadAdapter.getPosition(receta.getDificultad());
            binding.etDificultad.setSelection(dificultadPosition);
        }

        if (receta.getTipoCocina() != null) {
            int tipoCocinaPosition = tipoCocinaAdapter.getPosition(receta.getTipoCocina());
            binding.etTipoCocina.setSelection(tipoCocinaPosition);
        }

        Usuario usuarioActual = incvm.getUsuarioActual();
        if (receta.getUsuario().getUsuarioID() != usuarioActual.getUsuarioID()) {
            disableEditTexts();
            binding.btnCrearReceta.setVisibility(View.GONE);
            binding.btnGuardar.setVisibility(View.GONE);
            binding.btnSubirImagen.setVisibility(View.GONE);
            binding.btnBorrar.setVisibility(View.GONE);
        } else {

            binding.btnCrearReceta.setVisibility(View.VISIBLE);
            binding.btnGuardar.setVisibility(View.GONE);
            binding.btnSubirImagen.setVisibility(View.GONE);
            binding.btnBorrar.setVisibility(View.GONE);
            disableEditTexts();
        }

        binding.progressBar.setVisibility(View.GONE);
    }

});
binding.btnMostrarReceta.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // Navegar a la pantalla de recetas del usuario
        Bundle bundle = new Bundle();
        Log.d("userId"," "+ recetaViewModel.getReceta().getValue().getUsuario().getUsuarioID());
        // Asumiendo que tienes una manera de obtener el ID del usuario actual
        int usuarioId = recetaViewModel.getReceta().getValue().getUsuario().getUsuarioID();
        recetaViewModel.cargarRecetasUsuario(usuarioId);
        bundle.putInt("usuarioID", usuarioId);
        Navigation.findNavController(v).navigate(R.id.action_recetaFragment_to_resultadosFragment, bundle);
    }
});


        binding.btnCrearReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visibleEditTexts();
                binding.btnCrearReceta.setVisibility(View.GONE);
                binding.btnGuardar.setVisibility(View.VISIBLE);
                binding.btnSubirImagen.setVisibility(View.VISIBLE);
                binding.btnBorrar.setVisibility(View.VISIBLE);
            }
        });
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecetaSubirDto recetaDto = recopilarDatosReceta();
                recetaViewModel.updateReceta(recetaID, recetaDto);
                Log.d("ce","btn guardar"+recetaID);
               // Log.d("cetenta","btn guardar"+recetaDto.getDescripcion()+recetaDto.getFotoPortada());

                binding.btnCrearReceta.setVisibility(View.VISIBLE);
                binding.btnGuardar.setVisibility(View.GONE);
                binding.btnSubirImagen.setVisibility(View.GONE);
                binding.btnBorrar.setVisibility(View.GONE);
                disableEditTexts();
                Toast.makeText(getContext(), "Receta modificada con éxito", Toast.LENGTH_SHORT).show();

            }
        });
        binding.btnSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePickerLauncher.launch(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
                  }
        });

        binding.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Confirmar eliminación")
                        .setMessage("¿Está seguro que desea eliminar esta receta?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recetaViewModel.deleteReceta(recetaID);
                                recetaViewModel.getDeleteResult().observe(getViewLifecycleOwner(), success -> {
                                    if (success) {
                                        // Navegar de vuelta al inicio
                                        NavController navController = NavHostFragment.findNavController(RecetaFragment.this);
                                        navController.navigate(R.id.action_recetaFragment_to_page_inicio);
                                    } else {
                                        // Mostrar un mensaje de error si la eliminación falló
                                        Toast.makeText(requireContext(), "Error al eliminar la receta", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(RecetaFragment.this);
                navController.navigateUp();
            }
        });

        return view;
    }
    private RecetaSubirDto recopilarDatosReceta() {
        String titulo = binding.etTitulo.getText().toString();
        String descripcion = binding.etDescripcion.getText().toString();
        String ingredientes = binding.etIngredientes.getText().toString();
        String pasos = binding.etPasos.getText().toString();
        String porciones = binding.etPorciones.getText().toString();
        String dificultad = binding.etDificultad.getSelectedItem().toString();
        String tipoCocina = binding.etTipoCocina.getSelectedItem().toString();

        String tiempo = binding.etTiempo.getValue() + ":" + binding.etTiempoMinutos.getValue() + " Hs";

        String fotoPort = null;
        if (binding.fotoReceta.getDrawable() != null) {
            fotoPort = imagenToBase64(binding.fotoReceta);
        }

         return new RecetaSubirDto(titulo, descripcion, ingredientes, pasos, porciones, tiempo, dificultad, tipoCocina, fotoPort);
    }


    private String imagenToBase64(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private void visibleEditTexts() {
        binding.etTitulo.setEnabled(true);
        binding.etDescripcion.setEnabled(true);
        binding.etIngredientes.setEnabled(true);
        binding.etPasos.setEnabled(true);
        binding.etPorciones.setEnabled(true);
        binding.etDificultad.setEnabled(true);
        binding.etTipoCocina.setEnabled(true);
        binding.etTiempo.setEnabled(true);
        binding.etTiempoMinutos.setEnabled(true);
    }
    private void disableEditTexts() {
        binding.etTitulo.setEnabled(false);
        binding.etDescripcion.setEnabled(false);
        binding.etIngredientes.setEnabled(false);
        binding.etPasos.setEnabled(false);
        binding.etPorciones.setEnabled(false);
        binding.etDificultad.setEnabled(false);
        binding.etTipoCocina.setEnabled(false);
        binding.etTiempo.setEnabled(false);
        binding.etTiempoMinutos.setEnabled(false);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            imageUris = result.getData().getData();
                            binding.fotoReceta.setImageURI(imageUris);
                        }
                    }
                });
    }

}
