package com.softulp.appgmaldonado.ui.inicio;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softulp.appgmaldonado.R;
import com.softulp.appgmaldonado.modelo.Comentario;
import com.softulp.appgmaldonado.modelo.Receta;
import com.softulp.appgmaldonado.modelo.Usuario;

import java.util.List;

public class CommentActivity extends AppCompatActivity {/*
    private RecyclerView recyclerComentarios;
    private EditText etComment;
    private ImageButton btnSendComment;
    private CommentAdapter comentarioAdapter;
    private InicioViewModel viewModel;
    private Receta receta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_comment);

        recyclerComentarios = findViewById(R.id.recyclerComentarios);
        etComment = findViewById(R.id.etComment);
        btnSendComment = findViewById(R.id.btnSendComment);

        // Obtén el objeto Receta de los extras del intent
        receta = (Receta) getIntent().getSerializableExtra("receta");

        // Inicializa el ViewModel
        viewModel = new ViewModelProvider(this).get(InicioViewModel.class);

        // Configura el RecyclerView
        recyclerComentarios.setLayoutManager(new LinearLayoutManager(this));
        comentarioAdapter = new CommentAdapter(receta.getComentarios());
        recyclerComentarios.setAdapter(comentarioAdapter);
        Log.d("cantt","paso 4"+receta.getCantidadComentarios());
        btnSendComment.setOnClickListener(v -> {
            String nuevoComentario = etComment.getText().toString().trim();
            Log.d("CommentActivity", "Usuario actual: " + nuevoComentario);
            if (!nuevoComentario.isEmpty()) {
                // Obtener el usuario actual logueado del ViewModel
                Usuario usuarioActual = viewModel.getUsuarioActual();
                Log.d("CommentActivity1", "Usuario actual: " + usuarioActual.getNombre());
                receta.setCantidadComentarios(receta.getCantidadComentarios() + 1);
                Log.d("cantt","paso 4"+receta.getCantidadComentarios());
                // Crear el nuevo comentario
                Comentario comentario = new Comentario();
                comentario.setUsuario(usuarioActual); // Establece el usuario actual logueado
                comentario.setComentario(nuevoComentario);

                // Agregar el comentario a la lista localmente para actualizar la UI
                receta.getComentarios().add(comentario);
                comentarioAdapter.notifyDataSetChanged();
                etComment.setText("");

                // Llamar a la función para enviar el comentario al ViewModel
                viewModel.ComentarPublicacion(receta.getRecetaID(), nuevoComentario);
            }
        });
    }*/
}
