package game.pacmanGames.entity;

import java.awt.Image;

import game.pacmanGames.entity.Enums.*;

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
