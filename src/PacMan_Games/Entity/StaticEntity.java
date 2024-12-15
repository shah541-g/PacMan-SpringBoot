package PacMan_Games.Entity;
import java.awt.*;
import java.util.HashSet;

import PacMan_Games.Entity.Enums.*;

public abstract class StaticEntity extends Entity{

    protected boolean eatable;
    public StaticEntity() {
        super();
    }

    public StaticEntity(ImageDimension dimensions, int startX, int startY,
    EntityRepresentation entityRepresentation, Image image) {
        super(dimensions, startX, startY,
        entityRepresentation, image);
    }

    @Override
    void update(char direction, HashSet<Wall> walls, char game) {
        
    }
    
    abstract boolean isEatable();
}
