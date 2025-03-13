package iut.dam.projet_dev_mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import iut.dam.projet_dev_mobile.models.LoginRequest;
import iut.dam.projet_dev_mobile.models.LoginResponse;
import iut.dam.projet_dev_mobile.repository.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class    LoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton;
    private ProgressBar progressBar;
    private AuthService authService;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        authService = RetrofitClient.getInstance().create(AuthService.class);
        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);

        // Vérifier si l'utilisateur est déjà connecté
        if (sharedPreferences.contains("TOKEN")) {
            goToHome();
        }

        loginButton.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        loginButton.setEnabled(false);

        LoginRequest request = new LoginRequest(email, password);
        Call<LoginResponse> call = authService.login(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressBar.setVisibility(View.GONE);
                loginButton.setEnabled(true);

                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();

                    // Sauvegarde du token dans SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("TOKEN", loginResponse.getToken());
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "Bienvenue " + loginResponse.getUser().getName(), Toast.LENGTH_SHORT).show();
                    goToHome();
                } else {
                    Toast.makeText(LoginActivity.this, "Échec de connexion", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                loginButton.setEnabled(true);
                Toast.makeText(LoginActivity.this, "Erreur : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("LOGIN_ERROR", t.getMessage());
            }
        });
    }

    private void goToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
