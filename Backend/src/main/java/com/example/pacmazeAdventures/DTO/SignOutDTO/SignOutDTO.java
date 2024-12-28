package com.example.pacmazeAdventures.DTO.SignOutDTO;


public class SignOutDTO {
    private String email;


    public SignOutDTO(String email) {
        this.email = email;
    }

    public SignOutDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
