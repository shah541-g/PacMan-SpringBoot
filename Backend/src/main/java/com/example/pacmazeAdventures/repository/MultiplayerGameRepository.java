package com.example.pacmazeAdventures.repository;

import com.example.pacmazeAdventures.entity.MultiplayerGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MultiplayerGameRepository extends JpaRepository<MultiplayerGame, Long> {
    Optional<MultiplayerGame> findByRoomKey(String roomKey);
}
