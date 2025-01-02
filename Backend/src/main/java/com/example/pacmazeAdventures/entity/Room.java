package com.example.pacmazeAdventures.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private String roomKey;
    private Map<String, String> playersWithIp; // Map of email and their corresponding IP address

    public Room(String roomKey) {
        this.roomKey = roomKey;
        this.playersWithIp = new HashMap<>();
    }

    public String getRoomKey() {
        return roomKey;
    }

    // Get a list of player emails in the room
    public List<String> getPlayers() {
        return new ArrayList<>(playersWithIp.keySet()); // Return the list of player emails
    }

    public Map<String, String> getPlayersWithIp() {

        return playersWithIp;
    }

    public int addPlayer(String email, String ipAddress) {
        System.out.println("Players Count: " + playersWithIp.size() );
        System.out.println(playersWithIp.containsKey(email));
        if (playersWithIp.size() <= 2 && !playersWithIp.containsKey(email)) {
            playersWithIp.put(email, ipAddress);
            return playersWithIp.size(); // Return the player count (1 or 2)
        }
        return -1; // Room full or player already exists
    }

    // Remove a player from the room
    public boolean removePlayer(String email) {
        if (playersWithIp.containsKey(email)) {
            playersWithIp.remove(email);
            return true; // Player removed successfully
        }
        return false; // Player not found
    }

    public int getPlayerId(String email) {
        return playersWithIp.keySet().stream().toList().indexOf(email) + 1; // Player 1 = index 0, Player 2 = index 1
    }
}
