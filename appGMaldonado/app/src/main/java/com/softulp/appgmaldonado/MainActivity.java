package com.softulp.appgmaldonado;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.softulp.appgmaldonado.databinding.ActivityMainBinding;

// MainActivity.java
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupNavegacion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bottom, menu);
        return true;
    }

    private void setupNavegacion() {
        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_hostfragment);
        NavController navController = navHostFragment != null ? navHostFragment.getNavController() : null;

        if (navController != null) {
            NavigationUI.setupWithNavController(bottomNavigationView, navController);

            bottomNavigationView.setOnItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.page_inicio) {
                    // Verifica si el fragmento actual es el InicioFragment
                    if (navController.getCurrentDestination().getId() == R.id.page_inicio) {
                        // Navega de nuevo al InicioFragment para recargarlo
                        navController.popBackStack(R.id.page_inicio, true);
                        navController.navigate(R.id.page_inicio);
                    } else {
                        navController.navigate(R.id.page_inicio);
                    }
                    return true;
                } else if (id == R.id.page_agregar) {
                    navController.navigate(R.id.page_agregar);
                    return true;
                } else if (id == R.id.page_buscar) {
                    navController.navigate(R.id.page_buscar);
                    return true;
                } else if (id == R.id.page_perfil) {
                    navController.navigate(R.id.page_perfil);
                    return true;
                } else if (id == R.id.page_salir) {
                    navController.navigate(R.id.page_salir);
                    return true;
                }
                return false;
            });
        }
    }
}
