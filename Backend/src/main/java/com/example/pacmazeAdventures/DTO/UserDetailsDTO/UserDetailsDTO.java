package com.example.pacmazeAdventures.DTO.UserDetailsDTO;

public class UserDetailsDTO {

    private String email;
    private String username;

    // Constructor
    public UserDetailsDTO(String email, String username) {
        this.email = email;
        this.username = username;

    }

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

}
