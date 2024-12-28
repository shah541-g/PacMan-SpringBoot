package com.example.pacmazeAdventures.controller;

import com.example.pacmazeAdventures.DTO.MultiplayerGameDTO.*;
import com.example.pacmazeAdventures.DTO.SignInDTO.UserSignInResponse;
import com.example.pacmazeAdventures.model.Room;
import com.example.pacmazeAdventures.service.MultiplayerGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/multiplayer-games")
public class MultiplayerGameController {

    private final MultiplayerGameService service;
    private final CopyOnWriteArrayList<String> moveQueue = new CopyOnWriteArrayList<>();

    public MultiplayerGameController(MultiplayerGameService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<RoomKeyResponseDTO> createRoom(@RequestBody GameRequestDTO request) {
        // Call your service to create the room
        String roomKey = service.createRoom(request);

        // Create a ResponseDTO to return the room key and a success message
        RoomKeyResponseDTO responseDTO = new RoomKeyResponseDTO(roomKey, "Room created successfully!");

        // Return the response wrapped in ResponseEntity
        return ResponseEntity.ok(responseDTO);
    }


    @PostMapping("/join-room")
    public ResponseEntity<PlayerAssignmentDTO> joinRoom(@RequestBody RoomKeyRequestDTO request) {
        // Validate room key
        if (!service.validateRoomKey(request)) {
            return ResponseEntity.badRequest().body(null); // Invalid room key
        }
        System.out.println("hey boy");
        // Add player with their IP address
        int playerId = service.assignPlayerRole(request.getRoomKey(), request.getEmail(), request.getPlayerIp());
        if (playerId == -1) {
            return ResponseEntity.badRequest().body(null); // Room full or invalid email
        }
        String pacmanColor = (playerId == 1) ? "yellow" : "red";

        // Return player assignment details
        PlayerAssignmentDTO assignment = new PlayerAssignmentDTO(playerId, pacmanColor);
        return ResponseEntity.ok(assignment);
    }


    @PostMapping("/send-move")
    public ResponseEntity<String> sendMove(@RequestBody MoveDTO move) {
        moveQueue.add(move.getMove());
        return ResponseEntity.ok("Move added.");
    }

    @GetMapping("/receive-move")
    public ResponseEntity<String> receiveMove() {
        if (!moveQueue.isEmpty()) {
            return ResponseEntity.ok(moveQueue.remove(0));
        }
        return ResponseEntity.ok("No moves available.");
    }


    @GetMapping("/check-room/{roomKey}")
    public ResponseEntity<?> checkRoom(@PathVariable String roomKey) {
        // Check if room exists
        Room room = service.getRoomByKey(roomKey);
        if (room == null) {
            return ResponseEntity.notFound().build(); // Room not found
        }

        // Get the list of players in the room
        List<String> players = room.getPlayers(); // Assuming getPlayers() returns a list of player emails

        // Prepare the JSON response with players' information
        Map<String, Object> response = new HashMap<>();
        response.put("players", players);

        return ResponseEntity.ok(response); // Return the list of players in the room as a JSON object
    }



}
