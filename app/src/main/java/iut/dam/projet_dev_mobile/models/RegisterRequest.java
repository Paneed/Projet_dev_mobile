package iut.dam.projet_dev_mobile.models;

public class RegisterRequest {
    private String nom;
    private String email;
    private String password;

    public RegisterRequest(String nom, String email, String password) {
        this.nom = nom;
        this.email = email;
        this.password = password;
    }

    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
