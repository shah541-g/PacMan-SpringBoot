package com.example.pacmazeAdventures.controller;



import com.example.pacmazeAdventures.DTO.SinglePlayerGameDTO.SinglePlayerGameDTO;
import com.example.pacmazeAdventures.entity.SinglePlayerGame;
import com.example.pacmazeAdventures.service.SinglePlayerGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/single-player-games")
public class SinglePlayerGameController {

    @Autowired
    private SinglePlayerGameService service;

    @PostMapping
    public ResponseEntity<SinglePlayerGame> saveGame(@RequestBody SinglePlayerGameDTO gameDTO) {
        SinglePlayerGame savedGame = service.saveGame(gameDTO);
        return ResponseEntity.ok(savedGame);
    }

    @GetMapping("/player/{player_email}")
    public ResponseEntity<List<SinglePlayerGame>> getGamesByPlayerEmail(@PathVariable String player_email) {
        List<SinglePlayerGame> games = service.getGamesByPlayerEmail(player_email);
        return ResponseEntity.ok(games);
    }


    @GetMapping
    public ResponseEntity<List<SinglePlayerGame>> getAllGames() {
        List<SinglePlayerGame> games = service.getAllGames();
        return ResponseEntity.ok(games);
    }
}
