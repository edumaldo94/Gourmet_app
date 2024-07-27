package com.softulp.appgmaldonado.ui.perfil;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.databinding.FragmentPerfilBinding;
import com.softulp.appgmaldonado.modelo.Usuario;
import com.softulp.appgmaldonado.request.ApiService;


public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private PerfilViewModel pvm;
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private static final int SELECT_IMAGE_REQUEST = 1;

    private Button btnCambiarClave;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        pvm = new ViewModelProvider(this).get(PerfilViewModel.class);

        pvm.mUsuario().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
Log.d("salidaA",usuario.getUsuarioID()+"");
binding.txtTituloName.setText(usuario.getNombre() +" "+usuario.getApellido());
                    binding.etCodigo.setText(usuario.getUsuarioID()+"");
                    binding.editNombre.setText(usuario.getNombre());
                    binding.editApellido.setText(usuario.getApellido());
                //   binding.editClave.setText(propietario.getClave());
                    binding.editCorreo.setText(usuario.getCorreo());
                binding.etClaveAntigua.setText(usuario.getClave());
              /*  Glide.with()
                        .load(ApiService.URL_BASE + usuario.getFoto())
                        .placeholder(R.drawable.imagen_default)
                        .into(binding.profileImage);*/
                Glide.with(requireContext())
                        .load(ApiService.URL_BASE + usuario.getFoto())
                        .placeholder(R.drawable.imagen_default)
                        .into(binding.profileImage);
                Glide.with(requireContext())
                        .load(ApiService.URL_BASE + usuario.getFoto())
                        .placeholder(R.drawable.imagen_default)
                        .into(binding.imagenDefa);

            }

        });
pvm.getMactivar().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
    @Override
    public void onChanged(Boolean aBoolean) {
      //  binding.etCodigo.setEnabled(aBoolean);
        binding.editNombre.setEnabled(aBoolean);
        binding.editApellido.setEnabled(aBoolean);
       // binding.editDni.setEnabled(aBoolean);
       // binding.editTelefono.setEnabled(aBoolean);

    }
});
        pvm.getMeditar().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btneditar.setText(s);
            }
        });

        pvm.llenarPerfil();
        binding.btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.btneditar.getText().toString().equals("Editar")) {
                    pvm.activarEdicion(true);  // Activa los campos para edición
                    binding.btneditar.setText("Guardar");
                    binding.btneditar.setBackgroundResource(R.drawable.btnguardar);

                } else {
                    Usuario usuario = new Usuario(
                            binding.etCodigo.getId(),
                            binding.editNombre.getText().toString(),
                            binding.editApellido.getText().toString(),
                            binding.editCorreo.getText().toString()
                    );
                    if (usuario.getFoto() != null) {
                        usuario.setFoto(pvm.mUsuario().getValue().getFoto());
                    }
                    pvm.actualizarDatosPersonales(usuario);

                }
            }
        });
        pvm.getDatosCompletos().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean completo) {
                if (completo) {
                    pvm.llenarPerfil();
                }
            }
        });

        binding.Confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String claveAntigua = binding.etClaveAntigua.getText().toString();
                String claveNueva = binding.etClaveNueva.getText().toString();
                String confirmarClaveNueva = binding.etConfirmarClaveNueva.getText().toString();
                pvm.cambiarClave(claveAntigua, claveNueva, confirmarClaveNueva);
            }
        });

// Implementar la lógica para cambiar la foto de perfil aquí
        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad para seleccionar una imagen de la galería
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECT_IMAGE_REQUEST);

            }
        });


        return root;
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            // Muestra la imagen seleccionada en la UI
            binding.profileImage.setImageURI(selectedImageUri);

            // Llama a la función para actualizar la foto de perfil
            pvm.actualizarFotoPerfil(selectedImageUri);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}