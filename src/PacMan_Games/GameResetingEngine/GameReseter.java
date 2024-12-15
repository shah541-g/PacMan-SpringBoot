package PacMan_Games.GameResetingEngine;

import java.util.HashSet;
import java.util.logging.*;

import CustomExceptions.GameExceptions.NullEntityException;
import PacMan_Games.Entity.*;
import PacMan_Games.Game.MultiPlayerGame.MultiPlayerGameStatus;
import PacMan_Games.Game.SinglePlayerGame.SinglePlayerGameStatus;
import Utils.LoggerUtility;

public class GameReseter {

    private static final Logger logger = LoggerUtility.getLogger("GameReseter", "GameReseter.log");

    public static void resetMultiPlayerGhostsPositions(HashSet<Ghost> ghosts) {
        try {
            if (ghosts == null) {
                throw new NullEntityException("Ghost set is null for multiplayer reset.");
            }

            if (MultiPlayerGameStatus.isGameOver()) {
                for (Ghost ghost : ghosts) {
                    if (ghost == null) {
                        throw new NullEntityException("A ghost is null during multiplayer reset.");
                    }
                    ghost.reset();
                }
            } else {
                logger.warning("Multiplayer game is not over, but resetMultiPlayerGhostsPositions was called.");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error resetting multiplayer ghosts' positions: " + e.getMessage(), e);
        }
    }

    public static void resetSinglePlayerGhostsPositions(HashSet<Ghost> ghosts) {
        try {
            if (ghosts == null) {
                throw new NullEntityException("Ghost set is null for single-player reset.");
            }

            if (SinglePlayerGameStatus.isGameOver()) {
                for (Ghost ghost : ghosts) {
                    if (ghost == null) {
                        throw new NullEntityException("A ghost is null during single-player reset.");
                    }
                    ghost.reset();
                }
            } else {
                logger.warning("Single-player game is not over, but resetSinglePlayerGhostsPositions was called.");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error resetting single-player ghosts' positions: " + e.getMessage(), e);
        }
    }

    public static void resetPacmanPositions(Pacman pacman) {
        try {
            if (pacman == null) {
                throw new NullEntityException("Pacman entity is null during position reset.");
            }

            if (!pacman.isPacmanDying()) {
                pacman.reset();
                pacman.setVelocityX(0);
                pacman.setVelocityY(0);
            } else {
                logger.warning("Pacman is in dying state, but resetPacmanPositions was called.");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error resetting Pacman's position: " + e.getMessage(), e);
        }
    }
}
