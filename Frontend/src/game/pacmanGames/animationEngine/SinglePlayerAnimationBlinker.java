package game.pacmanGames.animationEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InvalidObjectException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.Timer;

import customExceptions.gameExceptions.AnimationFrameException;
import customExceptions.gameExceptions.InvalidStateException;
import game.pacmanGames.entity.Pacman;
import game.pacmanGames.Game.SinglePlayerGame.SinglePlayerGameStatus;
import game.pacmanGames.gameResetingEngine.GameReseter;
import game.pacmanGames.imagesLoader.ImagesLoader;
import utils.loggingUtilities.LoggerUtility;

public class SinglePlayerAnimationBlinker {

    private static int currentFrame = 0;

    // Logger for this class
    private static final Logger logger = LoggerUtility.getLogger("SinglePlayerGame", "SinglePlayerGame.log");

    public static int getCurrentFrame() {
        return currentFrame;
    }

    private static void animationofDeathForSinglePlayerGame(Pacman pacman, JPanel gamePanel) throws InvalidObjectException {
        // Validate inputs
        if (pacman == null) {
            logger.log(Level.SEVERE, "Pacman object cannot be null.");
            throw new InvalidObjectException("Pacman object cannot be null.");
        }
        if (gamePanel == null) {
            logger.log(Level.SEVERE, "Game panel cannot be null.");
            throw new InvalidObjectException("Game panel cannot be null.");
        }

        // Timer for animation
        Timer animationTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (currentFrame < DeathFramesHandler.getTotalFramesCount()) {
                        
                        pacman.setImage(DeathFramesHandler.getDyingFrameForYellow()[currentFrame]);
                        currentFrame++;
                        gamePanel.repaint();
                    } else {
                        ((Timer) e.getSource()).stop();
                        currentFrame = 0;

                        pacman.setDeathStatus(false);

                        int lives = pacman.getLives();
                        if (lives > 0) {
                            pacman.setLives(lives - 1);
                        } else {
                            String error = "Pacman lives cannot be negative.";
                            logger.log(Level.SEVERE, error);
                            throw new InvalidStateException(error);
                        }

                        if (pacman.getLives() == 0) {
                            SinglePlayerGameStatus.updateGameStatus(true);
                            return;
                        }

                        pacman.setImage(ImagesLoader.loadPacMan1LeftImage());
                        GameReseter.resetPacmanPositions(pacman);
                    }
                } catch (Exception ex) {
                    // Log unexpected errors
                    logger.log(Level.SEVERE, "Error during Pacman animation: ", ex);
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        animationTimer.start();
    }

    public static void triggerDyingAnimationForSinglePlayerGame(Pacman pacman, JPanel gamePanel) throws AnimationFrameException, InvalidStateException {
        try {
            if (pacman == null) {
                String error = "Pacman object cannot be null.";
                logger.log(Level.SEVERE, error);
                throw new InvalidObjectException(error);
            }
            if (gamePanel == null) {
                String error = "Game panel cannot be null.";
                logger.log(Level.SEVERE, error);
                throw new InvalidObjectException(error);
            }

            if (!pacman.isPacmanDying()) {
                pacman.setDeathStatus(true);
                animationofDeathForSinglePlayerGame(pacman, gamePanel);
            }
        } catch (InvalidObjectException ex) {
            // Log specific custom exceptions
            logger.log(Level.WARNING, "Custom exception: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            // Catch and log any unexpected errors
            logger.log(Level.SEVERE, "Unexpected error: ", ex);
        }
    }
}
