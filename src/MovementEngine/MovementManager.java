package MovementEngine;
import java.util.HashSet;
import java.util.Random;

import CollisionReseter.CollisionChecker;
import Entity.*;
import Entity.Enums.Directions;
import Game.MultiPlayerGame.MultiPlayerGameBoard;

public class MovementManager {

    public static void updateDirection(char direction, HashSet<Wall> walls, MoveableEntity entity){
        char prevDirection = entity.getDirection();
        entity.setDirection(direction);

        updateVelocity(entity);


        int x = 0;
        x = entity.getX();
        x+=entity.getVelocityX();
        entity.setX(x);
        x = 0;


        int y = 0;
        y = entity.getY();
        y+=entity.getVelocityY();
        entity.setY(y);
        y = 0;

        for(Wall wall:walls){
            if(CollisionChecker.collisionWithStaticEntity(entity,wall)){
                
                x = entity.getX();
                x-=entity.getVelocityX();
                entity.setX(x);
                x = 0;


                y = entity.getY();
                y-=entity.getVelocityY();
                entity.setY(y);
                y = 0;


                entity.setDirection(prevDirection);
                updateVelocity(entity);
            }
        }
    }

    public static void updateVelocity(MoveableEntity entity){
        if(entity.getDirection() == Directions.UP.getSymbol()){
            entity.setVelocityX(0);
            entity.setVelocityY(-(MultiPlayerGameBoard.getTileSize()/4));
        }

        else if(entity.getDirection()  == Directions.DOWN.getSymbol()){
            entity.setVelocityX(0);
            entity.setVelocityY(MultiPlayerGameBoard.getTileSize()/4);
        }
        else if(entity.getDirection()  == Directions.LEFT.getSymbol()){
            entity.setVelocityX(-(MultiPlayerGameBoard.getTileSize()/4));
            entity.setVelocityY(0);
        }
        else if(entity.getDirection()  == Directions.RIGHT.getSymbol()){
            entity.setVelocityX(MultiPlayerGameBoard.getTileSize()/4);
            entity.setVelocityY(0);
        }
    }

    public static void startGhostsMovement(HashSet<Ghost> ghosts, HashSet<Wall> walls){
       
        
        for(Ghost ghost: ghosts){

            renewGhostMovement(ghost,walls);
        }
    }

    public static void renewGhostMovement(Ghost ghost, HashSet<Wall> walls){

        Random random = new Random(System.currentTimeMillis());
        Directions[] directions = Directions.values(); 

        Directions randomDirection = directions[random.nextInt(directions.length)]; 

        char directionSymbol = randomDirection.getSymbol(); 
        updateDirection(directionSymbol, walls, ghost);

    }

    
    public static void teleportY(MoveableEntity entity, int boardHeight){
        

        int y;

        if(entity.getY() <= 0){
            y = entity.getY();
            y = boardHeight - entity.getDimensions().getHeight();

            entity.setY(y);
            y = 0;
        }
        else if(entity.getY() + entity.getDimensions().getHeight() >= boardHeight){
            entity.setY(0);
        }
    }

    public static void teleportX(MoveableEntity entity, int boardWidth){
        
        int x;

        if(entity.getX() <= 0){
           
            x = boardWidth - entity.getDimensions().getWidth();
            entity.setX(x);
            x = 0;
        }
        else if(entity.getX() + entity.getDimensions().getWidth() >= boardWidth){
            entity.setX(0);
        }
    }

    
    public static void reverseMove(MoveableEntity entity){

        int xVal = entity.getX();
        int xvelocityVal = entity.getVelocityX();
        xVal -= xvelocityVal;

        entity.setX(xVal);
        xVal = 0;


        int yVal = entity.getY();
        int yvelocityVal = entity.getVelocityY();

        yVal -= yvelocityVal;
        entity.setY(yVal);
        yVal = 0;

    }

    public static void forwardMove(MoveableEntity entity){

        

        int y = 0;
        int x = 0;

        x = entity.getX();
        x += entity.getVelocityX();
        entity.setX(x);
        x = 0;


        y = entity.getY();
        y += entity.getVelocityY();
        entity.setY(y);
        y = 0;

    }

}
