package game.pacmanGames.collisionReseter;

import game.pacmanGames.entity.*;
import utils.loggingUtilities.LoggerUtility;
import java.util.logging.*;

import customExceptions.gameExceptions.InvalidEntityDimensionsException;
import customExceptions.gameExceptions.NullEntityException;

public class CollisionChecker {

    private static final Logger logger = LoggerUtility.getLogger("CollisionChecker", "CollisionChecker.log");

    public static boolean collisionWithStaticEntity(MoveableEntity moveableEntity, StaticEntity staticEntity) {
        try {
            if (moveableEntity == null || staticEntity == null) {
                throw new NullEntityException("One or both entities are null during collision check.");
            }

            if (moveableEntity.getDimensions() == null || staticEntity.getDimensions() == null) {
                throw new InvalidEntityDimensionsException("One or both entities have invalid dimensions.");
            }

            return moveableEntity.getX() < staticEntity.getStartX() + staticEntity.getDimensions().getWidth() &&
                    moveableEntity.getX() + moveableEntity.getDimensions().getWidth() > staticEntity.getStartX() &&
                    moveableEntity.getY() < staticEntity.getStartY() + staticEntity.getDimensions().getHeight() &&
                    moveableEntity.getY() + moveableEntity.getDimensions().getHeight() > staticEntity.getStartY();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during collisionWithStaticEntity: " + e.getMessage(), e);
            return false; 
        }
    }

    public static boolean collisionWithGhost(Ghost ghost, Pacman pacman) {
        try {
            if (ghost == null || pacman == null) {
                throw new NullEntityException("Ghost or Pacman is null during collision check.");
            }

            if (ghost.getDimensions() == null || pacman.getDimensions() == null) {
                throw new InvalidEntityDimensionsException("Ghost or Pacman has invalid dimensions.");
            }

            return ghost.getX() < pacman.getX() + pacman.getDimensions().getWidth() &&
                    ghost.getX() + ghost.getDimensions().getWidth() > pacman.getX() &&
                    ghost.getY() < pacman.getY() + pacman.getDimensions().getHeight() &&
                    ghost.getY() + ghost.getDimensions().getHeight() > pacman.getY();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during collisionWithGhost: " + e.getMessage(), e);
            return false; 
        }
    }
}
