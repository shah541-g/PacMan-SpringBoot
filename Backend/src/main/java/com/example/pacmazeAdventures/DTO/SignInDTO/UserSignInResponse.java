package com.example.pacmazeAdventures.DTO.SignInDTO;


public class UserSignInResponse {
    private String message;

    public UserSignInResponse() {
    }

    public UserSignInResponse(String message) {
        this.message = message;
    }

    public String getResponse() {
        return message;
    }

    public void setResponse(String message) {
        this.message = message;
    }
}
