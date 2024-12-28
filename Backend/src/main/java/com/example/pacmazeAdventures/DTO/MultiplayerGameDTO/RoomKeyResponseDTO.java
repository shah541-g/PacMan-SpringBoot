package com.example.pacmazeAdventures.DTO.MultiplayerGameDTO;

public class RoomKeyResponseDTO {
    private String roomKey;
    private String message;

    public RoomKeyResponseDTO(String roomKey, String message) {
        this.roomKey = roomKey;
        this.message = message;
    }

    public String getRoomKey() {
        return roomKey;
    }

    public void setRoomKey(String roomKey) {
        this.roomKey = roomKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
