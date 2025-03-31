package iut.dam.projet_dev_mobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Mon Profil");
        }

        TextView txtNom = findViewById(R.id.txt_nom);
        TextView txtPrenom = findViewById(R.id.txt_prenom);
        TextView txtEmail = findViewById(R.id.txt_email);
        TextView txtDateNaissance = findViewById(R.id.txt_date_naissance);
        TextView txtTel = findViewById(R.id.txt_tel);
        Button btnModifier = findViewById(R.id.btn_modifier);

        txtNom.setText("Nom : Chris");
        txtPrenom.setText("Prénom : Paul");
        txtEmail.setText("Email : paul@gmail.com");
        txtDateNaissance.setText("Date de naissance : 01/01/2000");
        txtTel.setText("Téléphone : 06 68 87 19 45");

        btnModifier.setOnClickListener(v -> {
            Toast.makeText(ProfileActivity.this, "Fonction Modifier à venir...", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
