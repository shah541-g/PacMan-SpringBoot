package com.example.pacmazeAdventures.DTO.SinglePlayerGameDTO;

import java.sql.Timestamp;

public class SinglePlayerGameDTO {
    private String playerEmail;
    private String status;
    private int score;
    private Timestamp timestamp; // Add timestamp field

    // Getters and Setters
    public String getPlayerEmail() {
        return playerEmail;
    }

    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
