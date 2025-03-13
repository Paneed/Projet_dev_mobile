package iut.dam.projet_dev_mobile.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "residents")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "email")
    private String email;

    // Constructeur avec paramètres
    public User(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    // Constructeur par défaut (nécessaire pour certaines opérations de Room)
    public User() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
