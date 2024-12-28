package com.example.pacmazeAdventures.service;



import com.example.pacmazeAdventures.DTO.SinglePlayerGameDTO.SinglePlayerGameDTO;
import com.example.pacmazeAdventures.entity.SinglePlayerGame;
import com.example.pacmazeAdventures.repository.SinglePlayerGameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinglePlayerGameService {

    private static final Logger logger = LoggerFactory.getLogger(SinglePlayerGameService.class);

    @Autowired
    private SinglePlayerGameRepository repository;

    public SinglePlayerGame saveGame(SinglePlayerGameDTO gameDTO) {
        SinglePlayerGame game = new SinglePlayerGame();
        game.setPlayerEmail(gameDTO.getPlayerEmail());
        game.setStatus(SinglePlayerGame.Status.valueOf(gameDTO.getStatus().toUpperCase()));
        game.setScore(gameDTO.getScore());
        game.setTimestamp(gameDTO.getTimestamp()); // Set timestamp from DTO
        return repository.save(game);
    }


    public List<SinglePlayerGame> getGamesByPlayerEmail(String player_email) {
        logger.info("Fetching games for player email: {}", player_email);
        List<SinglePlayerGame> games = repository.findByPlayerEmail(player_email);
        logger.info("Games fetched: {}", games);

        return games;
    }

    public List<SinglePlayerGame> getAllGames() {
        return repository.findAll();
    }
}
