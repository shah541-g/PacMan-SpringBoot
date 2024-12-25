package game.pacmanGames.entity;
import java.awt.*;
import java.util.HashSet;

import game.pacmanGames.entity.Enums.*;


public abstract class Entity {

    private ImageDimension dimensions;
    private int startX;
    private int startY;
    private EntityRepresentation entityRepresentation;
    private Image image;

    // Default constructor
    public Entity() {
    }

    // Parameterized constructor
    public Entity(ImageDimension dimensions, int startX, int startY,
                  EntityRepresentation entityRepresentation, Image image) {
        this.dimensions = dimensions;
        this.startX = startX;
        this.startY = startY;
        this.entityRepresentation = entityRepresentation;
        this.image = image;
    }

    // Getters and setters

    public ImageDimension getDimensions() {
        return dimensions;
    }

    public void setDimensions(ImageDimension dimensions) {
        this.dimensions = dimensions;
    }


    public EntityRepresentation getEntityRepresentation() {
        return entityRepresentation;
    }

    public void setEntityRepresentation(EntityRepresentation entityRepresentation) {
        this.entityRepresentation = entityRepresentation;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getStartX() {
        return startX;
    }
    
    public void setStartX(int startX) {
        this.startX = startX;
    }
    
    public int getStartY() {
        return startY;
    }
    
    public void setStartY(int startY) {
        this.startY = startY;
    }
    
    
    abstract void update(char direction, HashSet<Wall> walls,char game);
}
