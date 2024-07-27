package com.softulp.appgmaldonado.ui.agregar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.databinding.FragmentCrearRecetaBinding;
import com.softulp.appgmaldonado.modelo.Receta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecetaAgregarFragment extends Fragment {

    private RecetaAgregarViewModel vm;
    private FragmentCrearRecetaBinding binding;
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private Uri imageUris;
    private ArrayAdapter<String> dificultadAdapter;
    private ArrayAdapter<String> tipoCocinaAdapter;
    private Uri photoURI;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

            super.onViewCreated(container, savedInstanceState);
        binding = FragmentCrearRecetaBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(RecetaAgregarViewModel.class);

        imageUris = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.drawable.imagen_default);
        binding.fotoReceta.setImageResource(R.drawable.custom_fab_background);

        // Configurar NumberPickers
        binding.etTiempo.setMinValue(0);
        binding.etTiempo.setMaxValue(100);
        binding.etTiempoMinutos.setMinValue(0);
        binding.etTiempoMinutos.setMaxValue(59);

        // Configurar Spinners
        dificultadAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, new String[]{"Dificultad", "Fácil", "Medio", "Difícil"});
        dificultadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.etDificultad.setAdapter(dificultadAdapter);

        tipoCocinaAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, new String[]{"Categoría", "Verdura", "Carne Roja", "Carne Blanca", "Pescado", "Pastas"});
        tipoCocinaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.etTipoCocina.setAdapter(tipoCocinaAdapter);

        binding.etDificultad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // Evitar que la primera opción "Dificultad" sea seleccionada
                    if (view != null && view instanceof TextView) {
                        ((TextView) view).setTextColor(Color.rgb(1, 135, 134));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.etTipoCocina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // Evitar que la primera opción "Categoría" sea seleccionada
                    if (view != null && view instanceof TextView) {
                        ((TextView) view).setTextColor(Color.rgb(1, 135, 134));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.btnSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePickerLauncher.launch(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
            }
        });

        binding.btnSacarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, 1);
                }
            }
        });



        binding.btnCrearReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areFieldsValid()) {
                String titulo = binding.etTitulo.getText().toString();
                String descripcion = binding.etDescripcion.getText().toString();
                String ingredientes = binding.etIngredientes.getText().toString();
                String pasos = binding.etPasos.getText().toString();
                String porciones = binding.etPorciones.getText().toString();
                String tiempoPreparacion = binding.etTiempo.getValue() + ":" + binding.etTiempoMinutos.getValue() + " Hs";
                String dificultad = binding.etDificultad.getSelectedItem().toString();
                String tipoCocina = binding.etTipoCocina.getSelectedItem().toString();

                Receta receta = new Receta(titulo, descripcion, ingredientes, pasos, porciones, tiempoPreparacion, dificultad, tipoCocina, "");

                vm.crearReceta(imageUris, receta);
                vm.resetRecetatrue();
                vm.getRecetaAgregada().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean agregado) {
                        if (agregado) {

                            Toast.makeText(getContext(), "Receta creada con éxito", Toast.LENGTH_SHORT).show();
                            binding.etTitulo.setText("");
                            binding.etDescripcion.setText("");
                            binding.etIngredientes.setText("");
                            binding.etPasos.setText("");
                            binding.etPorciones.setText("");
                            binding.etTiempo.setValue(0) ;
                            binding.etTiempoMinutos.setValue(0);
                            binding.etDificultad.setSelection(0);
                            binding.etTipoCocina.setSelection(0);

                        } else {
                            Toast.makeText(getContext(), "Error al crear la receta", Toast.LENGTH_SHORT).show();
                        }

                    }

                });
                } else {
                    Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }
    private boolean areFieldsValid() {
        return !binding.etTitulo.getText().toString().isEmpty() &&
                !binding.etDescripcion.getText().toString().isEmpty() &&
                !binding.etIngredientes.getText().toString().isEmpty() &&
                !binding.etPasos.getText().toString().isEmpty() &&
                !binding.etPorciones.getText().toString().isEmpty() &&
                binding.etDificultad.getSelectedItemPosition() != 0 &&
                binding.etTipoCocina.getSelectedItemPosition() != 0;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            binding.fotoReceta.setImageBitmap(imageBitmap);


            // Guardar la imagen en el almacenamiento externo
            try {
                File photoFile = createImageFile();
                FileOutputStream fos = new FileOutputStream(photoFile);
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 1; // Cambia este valor para ajustar la resolución.
                Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getPath(), options);
                fos.close();
                imageUris = Uri.fromFile(photoFile);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        photoURI = Uri.fromFile(image);
        return image;
    }

}
