package com.example.pacmazeAdventures.service;

import com.example.pacmazeAdventures.DTO.MultiplayerGameDTO.GameRequestDTO;
import com.example.pacmazeAdventures.DTO.MultiplayerGameDTO.RoomKeyRequestDTO;
import com.example.pacmazeAdventures.entity.MultiplayerGame;
import com.example.pacmazeAdventures.entity.DesiredRoom;
import com.example.pacmazeAdventures.entity.Room;
import com.example.pacmazeAdventures.repository.DesiredRoomRepository;
import com.example.pacmazeAdventures.repository.MultiplayerGameRepository;
import com.example.pacmazeAdventures.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MultiplayerGameService {

    private final MultiplayerGameRepository repository;
    private final RoomRepository roomRepository;
    private final DesiredRoomRepository desiredRooms;

    public MultiplayerGameService(MultiplayerGameRepository repository, RoomRepository roomRepository, DesiredRoomRepository desiredRooms) {
        this.repository = repository;
        this.roomRepository = roomRepository;
        this.desiredRooms = desiredRooms;
    }

    public String createRoom(GameRequestDTO request) {
        // Create the multiplayer game entity
        MultiplayerGame game = new MultiplayerGame();
        String roomKey = UUID.randomUUID().toString();

        game.setRoomKey(roomKey);
        game.setPlayer1Email(request.getPlayer1Email());
        game.setPlayer2Email(request.getPlayer2Email());
        game.setStartingTime(LocalDateTime.now());
        game.setPlayer1Score(0);
        game.setPlayer2Score(0);
        game.setWinner("None");
        game.setLoser("None");

        // Save to multiplayer game repository
        repository.save(game);

        // Initialize a room in RoomRepository
        Room room = new Room(roomKey);
        room.addPlayer(request.getPlayer1Email(),request.getPlayer1Ip());
        roomRepository.saveRoom(room);

        desiredRooms.saveDesiredRoom(roomKey,new DesiredRoom(request.getPlayer1Email(),request.getPlayer2Email()));

        return roomKey;
    }


    // Method to validate room key
    public boolean validateRoomKey(RoomKeyRequestDTO request) {
        // Check if the room key exists in the repository
        return roomRepository.findRoomByKey(request.getRoomKey()) != null;
    }

    // Method to validate email
    public boolean validateEmail(RoomKeyRequestDTO request) {
        DesiredRoom room = desiredRooms.findDesiredRoomByKey(request.getRoomKey());

        return room.getPlayer1Email().equals(request.getEmail()) || room.getPlayer2Email().equals(request.getEmail());
    }

    public int assignPlayerRole(String roomKey, String email, String playerIp) {
        Room room = roomRepository.findRoomByKey(roomKey);

        if (validateEmail(new RoomKeyRequestDTO(roomKey,email,playerIp))) {
            // Try to add the player to the room and return the player count (1 or 2)
            System.out.println("ello");
            int playerCount = room.addPlayer(email, playerIp);

            if (playerCount == -1) {
                // Room is full or player already exists
                return -1; // Room full or player already joined
            }

            return room.getPlayerId(email); // Return the player ID (1 or 2)
        }
        return -1; // Room doesn't exist
    }

    // Get the room by its key
    public Room getRoomByKey(String roomKey) {
        return roomRepository.findRoomByKey(roomKey);
    }
}
