package com.example.pacmazeAdventures.repository;

import com.example.pacmazeAdventures.model.DesiredRoom;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DesiredRoomRepository {
    private final Map<String, DesiredRoom> desiredRooms = new HashMap<>();

    // Save a desired room
    public void saveDesiredRoom(String roomKey, DesiredRoom desiredRoom) {
        desiredRooms.put(roomKey, desiredRoom);
    }

    // Find a desired room by room key
    public DesiredRoom findDesiredRoomByKey(String roomKey) {
        return desiredRooms.get(roomKey);
    }

    // Remove a desired room by room key
    public boolean removeDesiredRoom(String roomKey) {
        if (desiredRooms.containsKey(roomKey)) {
            desiredRooms.remove(roomKey);
            return true; // Room removed successfully
        }
        return false; // Room not found
    }
}
