package com.example.pacmazeAdventures.entity;

public class DesiredRoom {


    private String player1Email;
    private String player2Email;

    // Constructor
    public DesiredRoom(String player1Email, String player2Email) {


        this.player1Email = player1Email;
        this.player2Email = player2Email;
    }

    // Getters and Setters

    public String getPlayer1Email() {
        return player1Email;
    }

    public void setPlayer1Email(String player1Email) {
        this.player1Email = player1Email;
    }

    public String getPlayer2Email() {
        return player2Email;
    }

    public void setPlayer2Email(String player2Email) {
        this.player2Email = player2Email;
    }
}
