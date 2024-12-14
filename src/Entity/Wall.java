package Entity;

import java.awt.Image;

import Entity.Enums.*;

public class Wall extends StaticEntity{


    public Wall(ImageDimension dimensions, int startX, int startY,
    EntityRepresentation entityRepresentation, Image image) {
        super(dimensions, startX, startY,
        entityRepresentation, image);
    }


    @Override
    boolean isEatable() {
        eatable = false;
        return eatable;
    }

}
