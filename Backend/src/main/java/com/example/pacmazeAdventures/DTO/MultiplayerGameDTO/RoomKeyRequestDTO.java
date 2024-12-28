package com.example.pacmazeAdventures.DTO.MultiplayerGameDTO;

public class RoomKeyRequestDTO {
    private String roomKey;
    private String email;
    private String playerIp; // Add player IP to the DTO

    // Default constructor
    public RoomKeyRequestDTO() {
    }

    // Parameterized constructor
    public RoomKeyRequestDTO(String roomKey, String email, String playerIp) {
        this.roomKey = roomKey;
        this.email = email;
        this.playerIp = playerIp;
    }

    // Getters and Setters
    public String getRoomKey() {
        return roomKey;
    }

    public void setRoomKey(String roomKey) {
        this.roomKey = roomKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlayerIp() {
        return playerIp;
    }

    public void setPlayerIp(String playerIp) {
        this.playerIp = playerIp;
    }
}
