package com.example.pacmazeAdventures.repository;

import com.example.pacmazeAdventures.entity.Room;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RoomRepository {
    private final Map<String, Room> rooms = new HashMap<>();

    // Create a new room
    public void saveRoom(Room room) {
        rooms.put(room.getRoomKey(), room);
    }

    // Find room by key
    public Room findRoomByKey(String roomKey) {
        return rooms.get(roomKey);
    }

    // Add player to a room
    public int addPlayerToRoom(String roomKey, String email, String playerIp) {
        Room room = rooms.get(roomKey); // Find the room by its key
        if (room != null) {
            return room.addPlayer(email, playerIp); // Add player with IP address and return the player ID
        }
        return -1; // Room not found
    }
}
