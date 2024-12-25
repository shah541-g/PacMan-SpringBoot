package game.pacmanGames.movementEngine;
import java.util.HashSet;
import java.util.Random;

import game.pacmanGames.collisionReseter.CollisionChecker;
import game.pacmanGames.entity.*;
import game.pacmanGames.entity.Enums.Directions;
import game.pacmanGames.Game.MultiPlayerGame.MultiPlayerGameBoard;
import game.pacmanGames.Game.SinglePlayerGame.SinglePlayerGameBoard;

public class MovementManager {

    public static void updateDirection(char direction, HashSet<Wall> walls, MoveableEntity entity, char game){
        
        char prevDirection = entity.getDirection();
        entity.setDirection(direction);

        if(game=='M')
        updateMultiplayerVelocity(entity);
        else if (game=='S')
        updateSinglePlayerVelocity(entity);


        forwardMove(entity);

        for(Wall wall:walls){
            if(CollisionChecker.collisionWithStaticEntity(entity,wall)){
                
                reverseMove(entity);

                entity.setDirection(prevDirection); 
                
                if(game=='M')
                updateMultiplayerVelocity(entity);
                else if (game=='S')
                updateSinglePlayerVelocity(entity);
            }
        }
    }

    private static void velocityUpdation(MoveableEntity entity, int velocity){

        if(entity.getDirection() == Directions.UP.getSymbol()){
            entity.setVelocityX(0);
            entity.setVelocityY(-velocity);
        }

        else if(entity.getDirection()  == Directions.DOWN.getSymbol()){
            entity.setVelocityX(0);
            entity.setVelocityY(velocity);
        }
        else if(entity.getDirection()  == Directions.LEFT.getSymbol()){
            entity.setVelocityX(-(velocity));
            entity.setVelocityY(0);
        }
        else if(entity.getDirection()  == Directions.RIGHT.getSymbol()){
            entity.setVelocityX(velocity);
            entity.setVelocityY(0);
        }
    }


    public static void updateMultiplayerVelocity(MoveableEntity entity){

        int velocity = (MultiPlayerGameBoard.getTileSize()/4);

       velocityUpdation(entity, velocity);
    }

    
    public static void updateSinglePlayerVelocity(MoveableEntity entity){

        int velocity = (SinglePlayerGameBoard.getTileSize()/4);

       velocityUpdation(entity, velocity);
    }

    public static void startGhostsMovement(HashSet<Ghost> ghosts, HashSet<Wall> walls, char game){
       
        
        for(Ghost ghost: ghosts){

            renewGhostMovement(ghost,walls, game);
        }
    }

    public static void renewGhostMovement(Ghost ghost, HashSet<Wall> walls, char game){

        Random random = new Random(System.currentTimeMillis());
        Directions[] directions = Directions.values(); 

        Directions randomDirection = directions[random.nextInt(directions.length)]; 

        char directionSymbol = randomDirection.getSymbol(); 
        updateDirection(directionSymbol, walls, ghost, game);

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
