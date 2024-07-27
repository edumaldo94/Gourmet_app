package com.softulp.appgmaldonado.ui.inicio;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.softulp.appgmaldonado.LoginActivityViewModel;
import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.modelo.Comentario;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.modelo.Usuario;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.List;

public class CommentBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private Receta receta;
    private InicioViewModel inicioViewModel;
    private CommentAdapter comentarioAdapter;
    private Context context;
    public CommentBottomSheetDialogFragment(Context context,Receta receta) {
        this.context = context;
        this.receta = receta;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);

        inicioViewModel = new ViewModelProvider(requireActivity()).get(InicioViewModel.class);
   //    loginActivityViewModel = new ViewModelProvider(requireActivity()).get(LoginActivityViewModel.class);

        RecyclerView recyclerComentarios = view.findViewById(R.id.recyclerComentarios);
        recyclerComentarios.setLayoutManager(new LinearLayoutManager(getContext()));
        CommentAdapter comentarioAdapter = new CommentAdapter(context,receta.getComentarios(),inicioViewModel);
        recyclerComentarios.setAdapter(comentarioAdapter);

        EditText etComment = view.findViewById(R.id.etComment);
        ImageButton btnSendComment = view.findViewById(R.id.btnSendComment);

        btnSendComment.setOnClickListener(v -> {
            String nuevoComentario = etComment.getText().toString().trim();
            if (!nuevoComentario.isEmpty()) {
                Usuario usuarioActual = inicioViewModel.getUsuarioActual();
                Comentario comentario = new Comentario();
                comentario.setUsuario(usuarioActual);
                comentario.setComentario(nuevoComentario);
                int cantCM=(receta.getCantidadComentarios() + 1);
                receta.setCantidadComentarios(cantCM);
                Log.d("cantt","paso 2"+receta.getCantidadComentarios());
                receta.getComentarios().add(comentario);
                comentarioAdapter.notifyDataSetChanged();
                etComment.setText("");

                inicioViewModel.ComentarPublicacion(receta.getRecetaID(), nuevoComentario);
                inicioViewModel.agregarComentario(receta.getRecetaID(), comentario);
            }
        });

        return view;
    }

}

