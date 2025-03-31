package iut.dam.projet_dev_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Paramètres");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView itemPref = findViewById(R.id.item_preferences);
        itemPref.setOnClickListener(v -> {
            startActivity(new Intent(SettingsActivity.this, PreferencesActivity.class));
        });

        TextView itemNotif = findViewById(R.id.item_notifications);
        itemNotif.setOnClickListener(v -> {
            startActivity(new Intent(SettingsActivity.this, NotificationActivity.class));
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
