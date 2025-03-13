package iut.dam.projet_dev_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import iut.dam.projet_dev_mobile.models.PasswordResetRequest;
import iut.dam.projet_dev_mobile.models.PasswordResetResponse;
import iut.dam.projet_dev_mobile.repository.AuthService;
import iut.dam.projet_dev_mobile.repository.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailInput;
    private Button resetPasswordButton;
    private TextView backToLogin;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailInput = findViewById(R.id.email_forgot);
        resetPasswordButton = findViewById(R.id.btn_reset_password);
        backToLogin = findViewById(R.id.txt_back_to_login);

        authService = RetrofitClient.getInstance().create(AuthService.class);

        resetPasswordButton.setOnClickListener(v -> resetPassword());
        backToLogin.setOnClickListener(v -> {
            startActivity(new Intent(ForgotPasswordActivity.this, MainActivity.class));
            finish();
        });
    }

    private void resetPassword() {
        String email = emailInput.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer votre email", Toast.LENGTH_SHORT).show();
            return;
        }

        PasswordResetRequest request = new PasswordResetRequest(email);
        Call<PasswordResetResponse> call = authService.resetPassword(request);

        call.enqueue(new Callback<PasswordResetResponse>() {
            @Override
            public void onResponse(Call<PasswordResetResponse> call, Response<PasswordResetResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(ForgotPasswordActivity.this, "Email envoyé ! Vérifiez votre boîte de réception.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ForgotPasswordActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Erreur, essayez encore", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PasswordResetResponse> call, Throwable t) {
                Toast.makeText(ForgotPasswordActivity.this, "Erreur réseau : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("RESET_PASSWORD_ERROR", t.getMessage());
            }
        });
    }
}
