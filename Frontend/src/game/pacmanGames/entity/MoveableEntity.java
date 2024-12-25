package game.pacmanGames.entity;

import java.awt.*;
import java.util.HashSet;

import game.pacmanGames.entity.Enums.*;
import game.pacmanGames.movementEngine.MovementManager;

public abstract class MoveableEntity extends Entity{

    private int x;
    private int y;
    private char direction;
    private int velocityX;
    private int velocityY;
    protected boolean automatic;

    // Default constructor
    public MoveableEntity() {
        super();
        this.x = 0;
        this.y = 0;
        this.direction = 'U';
        this.velocityX = 0;
        this.velocityY = 0;
    }

    // Constructor with parameters
    public MoveableEntity(ImageDimension dimensions, int startX, int startY,
    EntityRepresentation entityRepresentation, Image image,
                          int x, int y, char direction, int velocityX, int velocityY) {
        super(dimensions, startX, startY,
        entityRepresentation, image);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void reset(){
        this.x = this.getStartX();
        this.y = this.getStartY();
    }


    // Update method for moveable entities
    @Override
    public void update(char direction, HashSet<Wall> walls, char game) {
        MovementManager.updateDirection(direction,walls,this, game);
    }

    

    abstract boolean isAutomatic();
}
