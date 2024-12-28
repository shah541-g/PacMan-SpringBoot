package com.example.pacmazeAdventures.DTO.MultiplayerGameDTO;

public class MoveDTO {

    private String playerEmail;
    private String move;

    // Getter for playerEmail
    public String getPlayerEmail() {
        return playerEmail;
    }

    // Setter for playerEmail
    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }

    // Getter for move
    public String getMove() {
        return move;
    }

    // Setter for move
    public void setMove(String move) {
        this.move = move;
    }
}
