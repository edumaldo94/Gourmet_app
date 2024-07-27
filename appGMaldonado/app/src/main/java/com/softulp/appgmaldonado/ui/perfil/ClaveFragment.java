package com.softulp.appgmaldonado.ui.perfil;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;



public class ClaveFragment extends Fragment {/*
    private ClaveFragmentViewModel claveFragmentViewModel;

    private EditText etClaveAntigua;
    private EditText etClaveNueva;
    private EditText etConfirmarClaveNueva;
    private Button btnCambiarClave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clave, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etClaveAntigua = view.findViewById(R.id.etClaveAntigua);
        etClaveNueva = view.findViewById(R.id.etClaveNueva);
        etConfirmarClaveNueva = view.findViewById(R.id.etConfirmarClaveNueva);
        btnCambiarClave = view.findViewById(R.id.btnCambiarClave);

        claveFragmentViewModel = new ViewModelProvider(this).get(ClaveFragmentViewModel.class);

        btnCambiarClave.setOnClickListener(v -> cambiarClave());
    }

    private void cambiarClave() {

        String ClaveAntigua = etClaveAntigua.getText().toString();
        String ClaveNueva = etClaveNueva.getText().toString();
        String ConfirmarClaveNueva = etConfirmarClaveNueva.getText().toString();
        Log.d("clave",ClaveNueva);
        if (!ClaveNueva.equals(ConfirmarClaveNueva)) {
            Toast.makeText(getContext(), "Las nuevas claves no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        claveFragmentViewModel.cambiarClave(ClaveAntigua, ClaveNueva, ConfirmarClaveNueva);
    }*/
}
