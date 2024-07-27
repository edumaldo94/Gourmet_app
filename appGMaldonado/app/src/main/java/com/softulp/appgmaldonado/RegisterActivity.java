package com.softulp.appgmaldonado;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.softulp.appgmaldonado.databinding.ActivityLoginBinding;
import com.softulp.appgmaldonado.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private RegisterActivityViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegisterActivityViewModel.class);

        binding.btnRegistrar.setOnClickListener(v -> {
            String nombre = binding.etNombre.getText().toString();
            String apellido = binding.etApellido.getText().toString();
            String correo = binding.etCorreo.getText().toString();
            String clave = binding.etClave.getText().toString();

            viewModel.crearUsuario(nombre, apellido, correo, clave);
        });

        viewModel.getResultadoRegistro().observe(this, resultado -> {
            if (resultado != null && resultado.equals("success")) {
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity or navigate to login screen
            } else if (resultado != null) {
                Toast.makeText(this, "Error: " + resultado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}