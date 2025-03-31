package iut.dam.projet_dev_mobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MyHabitatActivity extends AppCompatActivity {

    private TextView tvNom, tvConso;
    private LinearLayout layoutEquipements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habitat);
        Button btnReserver = findViewById(R.id.btnReserver);
        btnReserver.setOnClickListener(v -> {
            Toast.makeText(this, "Fonction de réservation à venir...", Toast.LENGTH_SHORT).show();
        });

        Toolbar toolbar = findViewById(R.id.toolbar_habitat);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Mon habitat");
        }

        tvNom = findViewById(R.id.tvNomResident);
        tvConso = findViewById(R.id.tvConsoTotal);
        layoutEquipements = findViewById(R.id.layoutEquipements);

        Habitat habitat = (Habitat) getIntent().getSerializableExtra("habitat");

        if (habitat != null) {
            tvNom.setText(habitat.getNom());

            if (habitat.getEquipement().isEmpty()) {
                TextView noEquip = new TextView(this);
                noEquip.setText("Aucun équipement trouvé.");
                noEquip.setTextSize(16);
                layoutEquipements.addView(noEquip);
            }

            int total = 0;
            for (String equipement : habitat.getEquipement()) {
                ImageView imageView = new ImageView(this);
                int resId = getResources().getIdentifier(equipement, "drawable", getPackageName());
                if (resId != 0) {
                    imageView.setImageResource(resId);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120, 120);
                    params.setMargins(8, 8, 8, 8);
                    imageView.setLayoutParams(params);
                    layoutEquipements.addView(imageView);
                }
                total += 300;
            }

            tvConso.setText("Consommation estimée : " + total + " W");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
