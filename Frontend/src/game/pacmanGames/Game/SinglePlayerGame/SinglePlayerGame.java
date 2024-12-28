package game.pacmanGames.Game.SinglePlayerGame;

import java.awt.*;
import java.util.HashSet;
import game.pacmanGames.movementEngine.MovementManager;

import javax.swing.*;

import customExceptions.gameExceptions.AnimationFrameException;
import customExceptions.gameExceptions.InvalidStateException;
import game.pacmanGames.map.Map;
import game.pacmanGames.collisionReseter.CollisionChecker;
import game.pacmanGames.entity.*;
import game.pacmanGames.entity.Enums.Directions;
import game.pacmanGames.gameResetingEngine.GameReseter;
import game.pacmanGames.animationEngine.SinglePlayerAnimationBlinker;
import game.pacmanGames.animationEngine.DeathFramesHandler;
import pages.GameOutcomePage;

public class SinglePlayerGame {
    private HashSet<Wall> walls;
    private HashSet<FoodPellet> foods;
    private HashSet<Ghost> ghosts;
    
    
    private Pacman yellowPacman;
    
    private Map map;
    
    private JPanel gamePanel;
    

    public SinglePlayerGame(SinglePayerGameController gameController) {

        init(gameController);
        
    }

    public void init(SinglePayerGameController gameController){

        initPanel();
        configurePanel();
        gamePanel.addKeyListener(gameController);
        gamePanel.setFocusable(true);
        initAttributes();
        
        initBoard();
    }


    private void movePacmans(){


        MovementManager.forwardMove(yellowPacman);
        MovementManager.teleportX(yellowPacman,SinglePlayerGameBoard.getBoardWidth());


        // check yellowPacman collisoins
        for (Wall wall: walls){
            if(CollisionChecker.collisionWithStaticEntity(yellowPacman, wall)){
                MovementManager.reverseMove(yellowPacman);
                break;
            }
        }

    }

    private void moveGhosts() throws AnimationFrameException, InvalidStateException{

        for (Ghost ghost: ghosts){

            if (CollisionChecker.collisionWithGhost(ghost, yellowPacman)) {
                SinglePlayerAnimationBlinker.triggerDyingAnimationForSinglePlayerGame(yellowPacman, gamePanel);
            }


            if( ghost.getY() == SinglePlayerGameBoard.getTileSize()*9 && ghost.getDirection() != Directions.LEFT.getSymbol() && ghost.getDirection() != Directions.RIGHT.getSymbol()){
              
                MovementManager.updateDirection(Directions.UP.getSymbol(),walls,ghost,'S');
            }
           
            MovementManager.forwardMove(ghost);



            // check all collisoins
            for (Wall wall: walls){

                

                if(CollisionChecker.collisionWithStaticEntity(ghost, wall)){
                    MovementManager.reverseMove(ghost);
                    MovementManager.renewGhostMovement(ghost, walls,'S');
                }

            }
            MovementManager.teleportX(ghost, SinglePlayerGameBoard.getBoardWidth());
        }
        
    }

    private void foodEating(){

        
        // collision with food
        FoodPellet foodEaten = null;
        for(FoodPellet food : foods){
            if(CollisionChecker.collisionWithStaticEntity(yellowPacman, food)){
                foodEaten = food;
                
                yellowPacman.incrementScore();
            }
        }
        foods.remove(foodEaten);


        
    }
    public void moveEntity(SinglePayerGameController gameController) throws AnimationFrameException, InvalidStateException{

        
        movePacmans();
        moveGhosts();
        foodEating();
        
//        if(foods.isEmpty()){
////            init(gameController);
//           SinglePlayerGameStatus.setGameOutcome("Win");
//            SwingUtilities.invokeLater(() -> {
//                new GameOutcomePage("win");
//            });
//        }
        
    }

    public void reset(){
        GameReseter.resetPacmanPositions(yellowPacman);
        GameReseter.resetSinglePlayerGhostsPositions(ghosts);
    }

    public void draw(Graphics g){

        
        if (yellowPacman.isPacmanDying()) {

            if ( SinglePlayerAnimationBlinker.getCurrentFrame() < DeathFramesHandler.getTotalFramesCount()) {
                g.drawImage(DeathFramesHandler.getDyingFrameForRed()[SinglePlayerAnimationBlinker.getCurrentFrame()], yellowPacman.getX(), yellowPacman.getY(), yellowPacman.getDimensions().getWidth(), yellowPacman.getDimensions().getHeight(), null);
            }
        } 

        
       
            g.drawImage(yellowPacman.getImage(), yellowPacman.getX(), yellowPacman.getY(), yellowPacman.getDimensions().getWidth(), yellowPacman.getDimensions().getHeight(), null);

           
            for (Ghost ghost: ghosts){
                g.drawImage(ghost.getImage(), ghost.getX(), ghost.getY(), ghost.getDimensions().getWidth(), ghost.getDimensions().getHeight(), null);
            }

            for (Wall wall : walls ){
                g.drawImage(wall.getImage(), wall.getStartX(), wall.getStartY(), wall.getDimensions().getWidth(), wall.getDimensions().getHeight(), null);
            }

            g.setColor(Color.yellow);
            for (FoodPellet food: foods){
                g.fillRect(food.getStartX(), food.getStartY(), food.getDimensions().getWidth(), food.getDimensions().getHeight());
            }

            g.setFont(new Font("Arial", Font.PLAIN, 18));
            if(SinglePlayerGameStatus.isGameOver()){
                g.drawString("Game Over: " + String.valueOf(yellowPacman.getScore()), SinglePlayerGameBoard.getTileSize()/2, SinglePlayerGameBoard.getTileSize()/2);

            }
            else{
                g.drawString("Yellow Lives: " + String.valueOf(yellowPacman.getLives()) + "  Score: " +  String.valueOf(yellowPacman.getScore()), SinglePlayerGameBoard.getTileSize()/2, SinglePlayerGameBoard.getTileSize()/2);
               
            
        }
    }

    public void initGhostsMovement(){

        MovementManager.startGhostsMovement(ghosts,walls,'S');
    }

    private void configurePanel(){

        gamePanel.setPreferredSize(new Dimension(SinglePlayerGameBoard.getBoardWidth(),SinglePlayerGameBoard.getBoardHeight()));
        gamePanel.setBackground(Color.BLACK);
    }

    private void initPanel() {
        gamePanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Clear the panel
                draw(g); // Delegate drawing to the GameBoard's draw method
            }
        };
    }

    private void initAttributes() {
        walls = new HashSet<Wall>();
        foods = new HashSet<FoodPellet>();
        ghosts = new HashSet<Ghost>();
        map = new Map();
    }

    private void initBoard() {
        
        SinglePlayerGameInitializer.initializeGame(this);
    }

    // Getters

    public HashSet<Wall> getWalls() {
        return walls;
    }

    public HashSet<FoodPellet> getFoods() {
        return foods;
    }

    public HashSet<Ghost> getGhosts() {
        return ghosts;
    }

    public Pacman getYellowPacman() {
        return yellowPacman;
    }


    public Map getMap() {
        return map;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    // Setters
    public void setWalls(HashSet<Wall> walls) {
        this.walls = walls;
    }

    public void setFoods(HashSet<FoodPellet> foods) {
        this.foods = foods;
    }

    public void setGhosts(HashSet<Ghost> ghosts) {
        this.ghosts = ghosts;
    }

    public void setYellowPacman(Pacman pacman) {
        this.yellowPacman = pacman;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
