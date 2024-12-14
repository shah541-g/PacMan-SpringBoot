package GameResetingEngine;
import java.util.HashSet;

import Entity.*;
import Game.MultiPlayerGame.MultiPlayerGameStatus;

public class GameReseter {

    public static void resetGhostsPositions(HashSet<Ghost> ghosts){

        if (MultiPlayerGameStatus.isGameOver()) {

            for(Ghost ghost: ghosts){
                ghost.reset();
            }
        }
    }
    public static void resetPacmanPositions(Pacman pacman) {
        if (!pacman.isPacmanDying()) {
            pacman.reset();
            pacman.setVelocityX(0);
            pacman.setVelocityY(0);

        }
    }
}
