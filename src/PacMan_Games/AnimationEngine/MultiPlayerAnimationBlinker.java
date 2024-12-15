package PacMan_Games.AnimationEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import PacMan_Games.Entity.Pacman;
import PacMan_Games.Game.MultiPlayerGame.MultiPlayerGameStatus;
import PacMan_Games.GameResetingEngine.GameReseter;
import PacMan_Games.ImagesLoader.ImagesLoader;


import Utils.LoggerUtility;
import java.util.logging.Logger;

public class MultiPlayerAnimationBlinker {

    private static final Logger logger = LoggerUtility.getLogger("MultiPlayerAnimation", "logs/MultiPlayerAnimation.log");

    private static int currentFrame = 0;

    public static int getCurrentFrame() {
        return currentFrame;
    }

    private static void animationofDeathForMultiPlayerGame(Pacman pacman, int playerNumber, JPanel gamePanel) {


        Timer animationTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (currentFrame < DeathFramesHandler.getTotalFramesCount()) {
                        // Load the correct death frame for the player
                        if (playerNumber == 1) {
                            pacman.setImage(DeathFramesHandler.getDyingFrameForYellow()[currentFrame]);
                        } else {
                            pacman.setImage(DeathFramesHandler.getDyingFrameForRed()[currentFrame]);
                        }

                        currentFrame++;
                        gamePanel.repaint();
                    } else {
                        // End animation, reset position, and decrement lives
                        ((Timer) e.getSource()).stop();
                        currentFrame = 0;

                        pacman.setDeathStatus(false);
                        int lives = pacman.getLives();
                        lives -= 1;
                        pacman.setLives(lives);

                        if (pacman.getLives() == 0) {
                            MultiPlayerGameStatus.updateGameStatus(true);
                            return;
                        }

                        if (playerNumber == 1) {
                            try {
                                pacman.setImage(ImagesLoader.loadPacMan1LeftImage());
                            } catch (Exception ex) {
                                logger.severe("Error loading PacMan1 left image: " + ex.getMessage());
                                pacman.setImage(null); 
                            }
                        } else {
                            try {
                                pacman.setImage(ImagesLoader.loadPacMan2LeftImage());
                            } catch (Exception ex) {
                                logger.severe("Error loading PacMan2 left image: " + ex.getMessage());
                                pacman.setImage(null); 
                            }
                        }

                        try {
                            GameReseter.resetPacmanPositions(pacman);
                        } catch (Exception ex) {
                            logger.severe("Error resetting Pacman positions: " + ex.getMessage());
                        }
                    }
                } catch (Exception ex) {
                    logger.severe("Unexpected error during animation: " + ex.getMessage());
                }
            }
        });

        animationTimer.start();
    }

    public static void triggerDyingAnimationForMultiPlayerGame(Pacman pacman, int playerNumber, JPanel gamePanel) {
        try {
            if (!pacman.isPacmanDying() && playerNumber == 2) {
                pacman.setDeathStatus(true);
                animationofDeathForMultiPlayerGame(pacman, playerNumber, gamePanel);
            }
            if (!pacman.isPacmanDying() && playerNumber == 1) {
                pacman.setDeathStatus(true);
                animationofDeathForMultiPlayerGame(pacman, playerNumber, gamePanel);
            }
        } catch (Exception ex) {
            logger.severe("Error triggering dying animation for player " + playerNumber + ": " + ex.getMessage());
        }
    }

}
