package iut.dam.projet_dev_mobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NotificationActivity extends AppCompatActivity {

    private Switch switchNotifications, switchAlertes, switchEmail, switchOffer;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Toolbar toolbar = findViewById(R.id.toolbar_notif);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Mes notifications");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        prefs = getSharedPreferences("prefs_notifications", MODE_PRIVATE);

        switchNotifications = findViewById(R.id.switch_notifications);
        switchAlertes = findViewById(R.id.switch_alertes);
        switchEmail = findViewById(R.id.switch_email);
        switchOffer = findViewById(R.id.switch_offer);

        switchNotifications.setChecked(prefs.getBoolean("notif_general", false));
        switchAlertes.setChecked(prefs.getBoolean("notif_alertes", false));
        switchEmail.setChecked(prefs.getBoolean("notif_email", false));
        switchOffer.setChecked(prefs.getBoolean("notif_offer", false));

        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) ->
                prefs.edit().putBoolean("notif_general", isChecked).apply());

        switchAlertes.setOnCheckedChangeListener((buttonView, isChecked) ->
                prefs.edit().putBoolean("notif_alertes", isChecked).apply());

        switchEmail.setOnCheckedChangeListener((buttonView, isChecked) ->
                prefs.edit().putBoolean("notif_email", isChecked).apply());

        switchOffer.setOnCheckedChangeListener((buttonView, isChecked) ->
                prefs.edit().putBoolean("notif_offer", isChecked).apply());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
