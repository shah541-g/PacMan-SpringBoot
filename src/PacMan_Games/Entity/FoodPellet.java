package PacMan_Games.Entity;

import java.awt.Image;

import PacMan_Games.Entity.Enums.*;

public class FoodPellet extends StaticEntity{

    public FoodPellet(ImageDimension dimensions, int startX, int startY,
    EntityRepresentation entityRepresentation, Image image) {
        super(dimensions, startX, startY,
        entityRepresentation, image);
    }

    @Override
    boolean isEatable() {
        eatable = true;
        return eatable;
    }


}
