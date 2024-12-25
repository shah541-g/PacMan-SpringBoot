package game.pacmanGames.entity;

import java.awt.Image;

import game.pacmanGames.entity.Enums.*;

public class Pacman extends MoveableEntity{

    private int lives;
    private int score;
    private boolean dead;


    public Pacman(ImageDimension dimensions, int startX, int startY,
    EntityRepresentation entityRepresentation, Image image, int x, int y, char direction, int velocityX, int velocityY) {
        super(dimensions, startX, startY,
        entityRepresentation, image, x, y, direction, velocityX, velocityY);
        lives = 3;
        score = 0;
        dead = false;
    }
    
    @Override
    boolean isAutomatic() { 
        automatic = false;
        return automatic;
    }

    public int getLives() {
        return lives;
    }
    
    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        this.score += 10;
    }

    public boolean isPacmanDying() {
        return dead;
    }

    public void setDeathStatus(boolean dead) {
        this.dead = dead;
    }
     
}
