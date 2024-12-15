package PacMan_Games.Entity;
import java.awt.Image;
import PacMan_Games.Entity.Enums.*;


public class Ghost extends MoveableEntity{


    public Ghost(ImageDimension dimensions, int startX, int startY,
    EntityRepresentation entityRepresentation, Image image, int x, int y, char direction, int velocityX, int velocityY) {
        super(dimensions, startX, startY,
        entityRepresentation, image, x, y, direction , velocityX, velocityY);
    }
    
    @Override
    boolean isAutomatic() {
        automatic = true;
        return automatic;
    }
   
}
