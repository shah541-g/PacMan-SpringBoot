package CollisionReseter;
import Entity.*;

public class CollisionChecker {

    public static boolean collisionWithStaticEntity(MoveableEntity moveableEntity, StaticEntity staticEntity){
        return  moveableEntity.getX() < staticEntity.getStartX() + staticEntity.getDimensions().getWidth() &&
                moveableEntity.getX()  + moveableEntity.getDimensions().getWidth() > staticEntity.getStartX() &&
                moveableEntity.getY() < staticEntity.getStartY() + staticEntity.getDimensions().getHeight() &&
                moveableEntity.getY() + moveableEntity.getDimensions().getHeight() > staticEntity.getStartY();
    }

    public static boolean collisionWithGhost(Ghost ghost, Pacman pacman){
        return  ghost.getX() < pacman.getX() + pacman.getDimensions().getWidth() &&
        ghost.getX()  + ghost.getDimensions().getWidth() > pacman.getX() &&
        ghost.getY() < pacman.getY() + pacman.getDimensions().getHeight() &&
        ghost.getY() + ghost.getDimensions().getHeight() > pacman.getY();
    }



}
