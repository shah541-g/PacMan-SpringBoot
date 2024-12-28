package com.example.pacmazeAdventures.DTO.MultiplayerGameDTO;


public class PlayerAssignmentDTO {
    private int playerId;
    private String pacmanColor;

    // Default constructor
    public PlayerAssignmentDTO() {
    }

    // Parameterized constructor
    public PlayerAssignmentDTO(int playerId, String pacmanColor) {
        this.playerId = playerId;
        this.pacmanColor = pacmanColor;
    }

    // Getters and Setters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPacmanColor() {
        return pacmanColor;
    }

    public void setPacmanColor(String pacmanColor) {
        this.pacmanColor = pacmanColor;
    }
}
