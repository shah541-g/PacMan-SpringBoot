package game.pacmanGames.Game.SinglePlayerGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import customExceptions.gameExceptions.AnimationFrameException;
import customExceptions.gameExceptions.InvalidStateException;
import game.pacmanGames.entity.Enums.Directions;
import game.pacmanGames.imagesLoader.ImagesLoader;
import game.pacmanGames.movementEngine.MovementManager;

public class SinglePayerGameController implements ActionListener,KeyListener{

    private SinglePlayerGame game;
    private Timer gameloop;

    
    public SinglePayerGameController(){

        gameloop = new Timer(50,this);
        
    }

    public void init(SinglePlayerGame game){

        this.game = game;
        game.initGhostsMovement();

    }

    public void startGame(){
        gameloop.start();
    }

    private void moveYellowPacman(KeyEvent e){

        
        if(e.getKeyCode() == KeyEvent.VK_UP){
            MovementManager.updateDirection(Directions.UP.getSymbol(), game.getWalls(), game.getYellowPacman(),'S');
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            MovementManager.updateDirection(Directions.DOWN.getSymbol(), game.getWalls(), game.getYellowPacman(),'S');
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            MovementManager.updateDirection(Directions.LEFT.getSymbol(), game.getWalls(), game.getYellowPacman(),'S');
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            MovementManager.updateDirection(Directions.RIGHT.getSymbol(), game.getWalls(), game.getYellowPacman(),'S');
        }

    }

  
    

    private void changeYellowPacmanImage(){

        
        if(game.getYellowPacman().getDirection() == Directions.UP.getSymbol()){
            game.getYellowPacman().setImage(ImagesLoader.loadPacMan1UpImage());
        }
        else if(game.getYellowPacman().getDirection() == Directions.DOWN.getSymbol()){
            game.getYellowPacman().setImage(ImagesLoader.loadPacMan1DownImage());
        }
        else if(game.getYellowPacman().getDirection() == Directions.LEFT.getSymbol()){
            game.getYellowPacman().setImage(ImagesLoader.loadPacMan1LeftImage());
        }
        else if(game.getYellowPacman().getDirection() == Directions.RIGHT.getSymbol()){
            game.getYellowPacman().setImage(ImagesLoader.loadPacMan1RightImage());
        }

    }


    private void restartingGame(){

        game.init(this);
        game.reset();


        game.getYellowPacman().setLives(3);
        game.getYellowPacman().setScore(0);
        
        SinglePlayerGameStatus.updateGameStatus(false);
        gameloop.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            game.moveEntity(this);
        } catch (AnimationFrameException | InvalidStateException e1) {
            e1.printStackTrace();
        }
        game.getGamePanel().repaint();
        if(SinglePlayerGameStatus.isGameOver()){
            gameloop.stop();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyPressed(KeyEvent e) {}


    @Override
    public void keyReleased(KeyEvent e) {


        if (SinglePlayerGameStatus.isGameOver()){
           
            restartingGame();
        }

        moveYellowPacman(e);
       

        changeYellowPacmanImage();
       

    }




}
