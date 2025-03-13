package iut.dam.projet_dev_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        LottieAnimationView animationView = findViewById(R.id.lottieAnimationView);
        animationView.playAnimation();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Vérifier si l'utilisateur est connecté

            SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
            boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

            if (isLoggedIn) {
                startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
            } else {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }

            finish();
        }, 4000); // Attente de 4 secondes avant la redirection
    }
}
