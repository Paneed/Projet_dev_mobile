package iut.dam.projet_dev_mobile;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); // Assure-toi que ce nom correspond bien au fichier XML

        // Récupération de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    // Gérer le bouton retour
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Ferme l'activité et retourne à la précédente
        return true;
    }
}
