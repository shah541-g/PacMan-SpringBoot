package Entity;
import java.awt.*;
import java.util.HashSet;

import Entity.Enums.*;

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
    void update(char direction, HashSet<Wall> walls) {
        
    }
    
    abstract boolean isEatable();
}
