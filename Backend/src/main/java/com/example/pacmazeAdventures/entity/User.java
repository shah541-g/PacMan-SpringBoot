package com.example.pacmazeAdventures.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "users") // Maps to the 'users' table in the database
public class User {

    @Id
    @Column(length = 25) // Sets the maximum length of the email field
    private String email; // Maps to the 'email' column (Primary Key)

    @Column(unique = true, nullable = false, length = 20) // Ensures uniqueness and NOT NULL constraint
    private String username;

    @Column(nullable = false, length = 1000) // Ensures NOT NULL constraint
    private String password;

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
