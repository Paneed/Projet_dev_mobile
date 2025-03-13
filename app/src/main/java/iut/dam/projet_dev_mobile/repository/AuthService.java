package iut.dam.projet_dev_mobile.repository;

import iut.dam.projet_dev_mobile.models.LoginRequest;
import iut.dam.projet_dev_mobile.models.LoginResponse;
import iut.dam.projet_dev_mobile.models.PasswordResetRequest;
import iut.dam.projet_dev_mobile.models.PasswordResetResponse;
import iut.dam.projet_dev_mobile.models.RegisterRequest;
import iut.dam.projet_dev_mobile.models.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @Headers("Content-Type: application/json")
    @POST("/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @Headers("Content-Type: application/json")
    @POST("/reset-password")
    Call<PasswordResetResponse> resetPassword(@Body PasswordResetRequest request);
}
