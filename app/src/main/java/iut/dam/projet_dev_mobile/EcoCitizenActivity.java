package iut.dam.projet_dev_mobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EcoCitizenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_citizen);
        Toolbar toolbar = findViewById(R.id.toolbar_eco);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Éco-citoyen");
        }

        Button btn1 = findViewById(R.id.btn_creneau1);
        Button btn2 = findViewById(R.id.btn_creneau2);
        Button btn3 = findViewById(R.id.btn_creneau3);

        btn1.setOnClickListener(v ->
                Toast.makeText(this, "Réservé sur le créneau 8h - 10h 🟢", Toast.LENGTH_SHORT).show());

        btn2.setOnClickListener(v ->
                Toast.makeText(this, "Réservé sur le créneau 14h - 16h 🟠", Toast.LENGTH_SHORT).show());

        btn3.setOnClickListener(v ->
                Toast.makeText(this, "Réservé sur le créneau 18h - 20h 🔴", Toast.LENGTH_SHORT).show());
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
