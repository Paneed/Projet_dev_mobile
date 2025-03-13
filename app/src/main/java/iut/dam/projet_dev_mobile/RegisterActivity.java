package iut.dam.projet_dev_mobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import iut.dam.projet_dev_mobile.models.RegisterRequest;
import iut.dam.projet_dev_mobile.models.RegisterResponse;
import iut.dam.projet_dev_mobile.repository.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameInput, emailInput, passwordInput;
    private Button registerButton, goToLoginButton;
    private ProgressBar progressBar;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInput = findViewById(R.id.name);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        registerButton = findViewById(R.id.btn_register);
        goToLoginButton = findViewById(R.id.btn_go_to_login);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        authService = RetrofitClient.getInstance().create(AuthService.class);

        registerButton.setOnClickListener(v -> registerUser());
        goToLoginButton.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void registerUser() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Tous les champs sont requis", Toast.LENGTH_SHORT).show();
            return;
        }

        // LOG POUR DEBUG
        Log.d("REGISTER_DEBUG", "Nom: " + name + ", Email: " + email + ", Password: " + password);

        progressBar.setVisibility(View.VISIBLE);
        registerButton.setEnabled(false);

        RegisterRequest request = new RegisterRequest(name, email, password);
        Call<RegisterResponse> call = authService.register(request);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                progressBar.setVisibility(View.GONE);
                registerButton.setEnabled(true);

                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RegisterActivity.this, "Inscription réussie !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Échec de l'inscription", Toast.LENGTH_SHORT).show();
                    Log.e("REGISTER_DEBUG", "Réponse serveur : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                registerButton.setEnabled(true);
                Toast.makeText(RegisterActivity.this, "Erreur : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("REGISTER_ERROR", "Échec de la requête", t);
            }
        });
    }

}
