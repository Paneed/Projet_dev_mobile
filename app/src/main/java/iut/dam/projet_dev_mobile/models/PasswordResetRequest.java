package iut.dam.projet_dev_mobile.models;

public class PasswordResetRequest {
    private String email;

    public PasswordResetRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
