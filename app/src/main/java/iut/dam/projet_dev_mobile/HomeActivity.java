package iut.dam.projet_dev_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Configuration de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configuration du DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Gestion de la navigation
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    // Déjà sur la page d'accueil
                } else if (id == R.id.nav_consumption) {
                    startActivity(new Intent(HomeActivity.this, ConsumptionActivity.class));
                } else if (id == R.id.nav_profile) {
                    startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                } else if (id == R.id.nav_settings) {
                    startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                } else if (id == R.id.nav_logout) {
                    // Déconnexion de l'utilisateur
                    SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", false);
                    editor.apply();

                    // Retourner à la page d'accueil (MainActivity)
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                    finish();
                }

                // Fermer le menu après un clic
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
}
