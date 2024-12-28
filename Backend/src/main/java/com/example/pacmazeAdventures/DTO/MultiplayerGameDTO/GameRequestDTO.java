package com.example.pacmazeAdventures.DTO.MultiplayerGameDTO;

public class GameRequestDTO {

    private String player1Email;
    private String player1Ip; // Added for Player 1 IP
    private String player2Email;

    // Getter for player1Email
    public String getPlayer1Email() {
        return player1Email;
    }

    // Setter for player1Email
    public void setPlayer1Email(String player1Email) {
        this.player1Email = player1Email;
    }

    // Getter for player1Ip
    public String getPlayer1Ip() {
        return player1Ip;
    }

    // Setter for player1Ip
    public void setPlayer1Ip(String player1Ip) {
        this.player1Ip = player1Ip;
    }

    // Getter for player2Email
    public String getPlayer2Email() {
        return player2Email;
    }

    // Setter for player2Email
    public void setPlayer2Email(String player2Email) {
        this.player2Email = player2Email;
    }


}
