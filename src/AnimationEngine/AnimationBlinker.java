package AnimationEngine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.Pacman;
// import Game.GameStatus;
import Game.MultiPlayerGame.MultiPlayerGameStatus;
import Game.SinglePlayerGame.SinglePlayerGameStatus;
import GameResetingEngine.GameReseter;
import ImagesLoader.ImagesLoader;

public class AnimationBlinker {

    private static int currentFrame = 0;

    public static int getCurrentFrame(){

        return currentFrame;
    }
    private static void animationofDeathForMultiPlayerGame(Pacman pacman,int playerNumber, JPanel gamePanel){


        // Timer for animation
        Timer animationTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFrame < DeathFramesHandler.getTotalFramesCount()) {
                    if(playerNumber == 1)
                    pacman.setImage(DeathFramesHandler.getDyingFrameForYellow()[currentFrame]);
                    else
                    pacman.setImage(DeathFramesHandler.getDyingFrameForRed()[currentFrame]);

                    currentFrame++;
                    gamePanel.repaint();
                } else {
                    // End animation, reset position, and decrement lives
                    ((Timer) e.getSource()).stop();
                    currentFrame = 0;
                    
                    pacman.setDeathStatus(false);
                    int lives = pacman.getLives();
                    lives-=1;
                    pacman.setLives(lives);

                    if (pacman.getLives() == 0) {
                        MultiPlayerGameStatus.updateGameStatus(true);
                        return;
                    }

                    if (playerNumber == 1) {
                        
                        pacman.setImage(ImagesLoader.loadPacMan1LeftImage());


                    } else {
                        pacman.setImage(ImagesLoader.loadPacMan2LeftImage());
                        
                    }
                    GameReseter.resetPacmanPositions(pacman);

                }
            }
        });

        animationTimer.start();
    }

    public static void triggerDyingAnimationForMultiPlayerGame(Pacman pacman, int playerNumber, JPanel gamPanel) {
        if (!pacman.isPacmanDying() && playerNumber == 2){
            pacman.setDeathStatus(true);
            animationofDeathForMultiPlayerGame(pacman,playerNumber, gamPanel);
        }
        if (!pacman.isPacmanDying() && playerNumber == 1) {
            pacman.setDeathStatus(true);
            animationofDeathForMultiPlayerGame(pacman,playerNumber, gamPanel);
        }
    }




    
    private static void animationofDeathForSinglePlayerGame(Pacman pacman, JPanel gamePanel){


        // Timer for animation
        Timer animationTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFrame < DeathFramesHandler.getTotalFramesCount()) {
                   
                    pacman.setImage(DeathFramesHandler.getDyingFrameForYellow()[currentFrame]);

                    currentFrame++;
                    gamePanel.repaint();
                } else {
                    // End animation, reset position, and decrement lives
                    ((Timer) e.getSource()).stop();
                    currentFrame = 0;
                    
                    pacman.setDeathStatus(false);
                    int lives = pacman.getLives();
                    lives-=1;
                    pacman.setLives(lives);

                    if (pacman.getLives() == 0) {
                        SinglePlayerGameStatus.updateGameStatus(true);
                        return;
                    }

                   
                        pacman.setImage(ImagesLoader.loadPacMan1LeftImage());
                        
                    
                    GameReseter.resetPacmanPositions(pacman);

                }
            }
        });

        animationTimer.start();
    }

    public static void triggerDyingAnimationForSinglePlayerGame(Pacman pacman, JPanel gamPanel) {
       
        if (!pacman.isPacmanDying()) {
            pacman.setDeathStatus(true);
            animationofDeathForSinglePlayerGame(pacman, gamPanel);
        }
    }


}
