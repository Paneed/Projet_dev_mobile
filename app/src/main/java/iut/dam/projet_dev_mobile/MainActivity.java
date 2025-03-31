package iut.dam.projet_dev_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;

import iut.dam.projet_dev_mobile.models.LoginRequest;
import iut.dam.projet_dev_mobile.models.LoginResponse;
import iut.dam.projet_dev_mobile.repository.AuthService;
import iut.dam.projet_dev_mobile.repository.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton;
    private TextView forgotPasswordText, createAccountText;
    private LottieAnimationView logoAnimation;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (id == R.id.nav_profile) {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                }else if (id == R.id.nav_habitats) {
                    startActivity(new Intent(MainActivity.this, HabitatActivity.class));
                } else if (id == R.id.nav_settings) {
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                } else if (id == R.id.nav_my_habitat) {
                    startActivity(new Intent(MainActivity.this, MyHabitatActivity.class));
                }else if (id == R.id.nav_ecocitizen) {
                    startActivity(new Intent(MainActivity.this, EcoCitizenActivity.class));
                }else if (id == R.id.nav_about) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("A propos")
                            .setMessage("🏠 Application : PowerHome\n🔢 Version : 1.0\n👤 Développés par : Groupe 8 - Phuong, Shihong, Eléa\n📅 Année : 2025")
                            .setPositiveButton("OK", null)
                            .show();
                }else if (id == R.id.nav_logout) {
                    logoutUser();
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.btn_login);
        forgotPasswordText = findViewById(R.id.txt_forgot_password);
        createAccountText = findViewById(R.id.txt_create_account);
        logoAnimation = findViewById(R.id.logoAnimation);

        authService = RetrofitClient.getInstance().create(AuthService.class);

        logoAnimation.playAnimation();

        loginButton.setOnClickListener(v -> loginUser());

        forgotPasswordText.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ForgotPasswordActivity.class)));

        createAccountText.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
    }

    private void loginUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest request = new LoginRequest(email, password);
        Call<LoginResponse> call = authService.login(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    Toast.makeText(MainActivity.this, "Bienvenue " + loginResponse.getUser().getName(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("TOKEN", loginResponse.getToken());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Échec de connexion. Vérifiez vos identifiants.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erreur réseau : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("LOGIN_ERROR", t.getMessage());
            }
        });
    }

    private void logoutUser() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();
    }
}
