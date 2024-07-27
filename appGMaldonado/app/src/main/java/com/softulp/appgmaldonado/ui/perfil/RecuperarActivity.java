package com.softulp.appgmaldonado.ui.perfil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.appgmaldonado.LoginActivity;
import com.softulp.appgmaldonado.databinding.ActivityRecuperarBinding;


public class RecuperarActivity extends AppCompatActivity {
    private RecuperarActivityViewModel vm;
    private ActivityRecuperarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RecuperarActivityViewModel.class);
        binding = ActivityRecuperarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRecuperar.setOnClickListener(v -> {
            String email = binding.etCorreoRecupero.getText().toString();
            vm.pedidoRecuperoClave(email);

            // Crear y mostrar el diálogo
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Correo Enviado")
                    .setMessage("Se ha enviado un correo electrónico para recuperar su clave.")
                    .setPositiveButton("Aceptar", (dialog, which) -> {
                        // Cerrar el diálogo
                        dialog.dismiss();

                        // Redirigir al LoginActivity
                        Intent intent = new Intent(v.getContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(intent);
                        if (v.getContext() instanceof Activity) {
                            ((Activity) v.getContext()).finish(); // Cierra la actividad actual si el contexto es una actividad
                        }
                    })
                    .show();
        });

    }
}/**/