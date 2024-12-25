package game.pacmanGames.Game.MultiPlayerGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import game.pacmanGames.entity.Enums.Directions;
import game.pacmanGames.imagesLoader.ImagesLoader;
import game.pacmanGames.movementEngine.MovementManager;

public class MultiPlayerGameController implements ActionListener,KeyListener{

    private MultiPlayerGame game;
    private Timer gameloop;

    
    public MultiPlayerGameController(){

        gameloop = new Timer(50,this);
        
    }

    public void init(MultiPlayerGame game){

        this.game = game;
        game.initGhostsMovement();

    }

    public void startGame(){
        gameloop.start();
    }

    private void moveYellowPacman(KeyEvent e){

        
        if(e.getKeyCode() == KeyEvent.VK_UP){
            MovementManager.updateDirection(Directions.UP.getSymbol(), game.getWalls(), game.getYellowPacman(),'M');
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            MovementManager.updateDirection(Directions.DOWN.getSymbol(), game.getWalls(), game.getYellowPacman(),'M');
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            MovementManager.updateDirection(Directions.LEFT.getSymbol(), game.getWalls(), game.getYellowPacman(),'M');
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            MovementManager.updateDirection(Directions.RIGHT.getSymbol(), game.getWalls(), game.getYellowPacman(),'M');
        }

    }

    private void moveRedPacman(KeyEvent e){

        
        if(e.getKeyCode() == KeyEvent.VK_W){
            MovementManager.updateDirection(Directions.UP.getSymbol(), game.getWalls(), game.getRedPacman(),'M');
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            MovementManager.updateDirection(Directions.DOWN.getSymbol(), game.getWalls(), game.getRedPacman(),'M');
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            MovementManager.updateDirection(Directions.LEFT.getSymbol(), game.getWalls(), game.getRedPacman(),'M');
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            MovementManager.updateDirection(Directions.RIGHT.getSymbol(), game.getWalls(), game.getRedPacman(),'M');
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

    private void changeRedPacmanImage(){
        
        if(game.getRedPacman().getDirection() == Directions.UP.getSymbol()){
            game.getRedPacman().setImage(ImagesLoader.loadPacMan2UpImage());
        }
        else if(game.getRedPacman().getDirection() == Directions.DOWN.getSymbol()){
            game.getRedPacman().setImage(ImagesLoader.loadPacMan2DownImage());
        }
        else if(game.getRedPacman().getDirection() == Directions.LEFT.getSymbol()){
            game.getRedPacman().setImage(ImagesLoader.loadPacMan2LeftImage());
        }
        else if(game.getRedPacman().getDirection() == Directions.RIGHT.getSymbol()){
            game.getRedPacman().setImage(ImagesLoader.loadPacMan2RightImage());
        }
    }

    private void restartingGame(){

        game.init(this);
        game.reset();

        game.getRedPacman().setLives(3);
        game.getYellowPacman().setLives(3);
        game.getRedPacman().setScore(0);
        game.getYellowPacman().setScore(0);
        
        MultiPlayerGameStatus.updateGameStatus(false);
        gameloop.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        game.moveEntity(this);
        game.getGamePanel().repaint();
        // System.out.println("Repainting");
        if(MultiPlayerGameStatus.isGameOver()){
            gameloop.stop();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyPressed(KeyEvent e) {}


    @Override
    public void keyReleased(KeyEvent e) {


        if (MultiPlayerGameStatus.isGameOver()){
           
            restartingGame();
        }

        moveYellowPacman(e);
        moveRedPacman(e);

        changeYellowPacmanImage();
        changeRedPacmanImage();

    }




}
