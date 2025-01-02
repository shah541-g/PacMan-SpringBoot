package com.example.pacmazeAdventures.DTO.SignUpDTO;


public class UserSignUpResponse {
    private String message;

    public UserSignUpResponse() {
        this.message = "message";
    }
    public UserSignUpResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
