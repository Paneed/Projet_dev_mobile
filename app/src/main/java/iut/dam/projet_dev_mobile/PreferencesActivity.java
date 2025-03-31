package iut.dam.projet_dev_mobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PreferencesActivity extends AppCompatActivity {

    private Switch switchDarkMode, switchAutoUpdate;
    private RadioGroup fontSizeGroup;
    private Spinner langSpinner;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Toolbar toolbar = findViewById(R.id.toolbar_preferences);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Mes préférences");
        }

        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        switchDarkMode = findViewById(R.id.switch_dark_mode);
        switchAutoUpdate = findViewById(R.id.switch_auto_update);
        fontSizeGroup = findViewById(R.id.fontSizeGroup);
        langSpinner = findViewById(R.id.lang_spinner);

        switchDarkMode.setChecked(prefs.getBoolean("dark_mode", false));
        switchAutoUpdate.setChecked(prefs.getBoolean("auto_update", false));
        fontSizeGroup.check(prefs.getInt("font_size", R.id.fontMedium));
        langSpinner.setSelection(prefs.getInt("lang", 0));

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) ->
                prefs.edit().putBoolean("dark_mode", isChecked).apply());

        switchAutoUpdate.setOnCheckedChangeListener((buttonView, isChecked) ->
                prefs.edit().putBoolean("auto_update", isChecked).apply());

        fontSizeGroup.setOnCheckedChangeListener((group, checkedId) ->
                prefs.edit().putInt("font_size", checkedId).apply());

        langSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                prefs.edit().putInt("lang", position).apply();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
