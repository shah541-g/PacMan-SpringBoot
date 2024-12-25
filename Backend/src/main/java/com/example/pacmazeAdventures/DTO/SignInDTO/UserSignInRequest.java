package com.example.pacmazeAdventures.DTO.SignInDTO;


public class UserSignInRequest {
    private String email;
    private String password;

    public UserSignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserSignInRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
