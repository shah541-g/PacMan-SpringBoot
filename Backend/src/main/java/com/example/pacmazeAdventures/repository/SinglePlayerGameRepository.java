package com.example.pacmazeAdventures.repository;


import com.example.pacmazeAdventures.entity.SinglePlayerGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinglePlayerGameRepository extends JpaRepository<SinglePlayerGame, Integer> {
    List<SinglePlayerGame> findByPlayerEmail(String player_email);
}

