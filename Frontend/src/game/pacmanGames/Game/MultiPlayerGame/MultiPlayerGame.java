package game.pacmanGames.Game.MultiPlayerGame;

import java.awt.*;
import java.util.HashSet;
import game.pacmanGames.movementEngine.MovementManager;
import javax.swing.JPanel;

import game.pacmanGames.map.Map;
import game.pacmanGames.collisionReseter.CollisionChecker;
import game.pacmanGames.entity.*;
import game.pacmanGames.gameResetingEngine.GameReseter;
import game.pacmanGames.animationEngine.MultiPlayerAnimationBlinker;
import game.pacmanGames.animationEngine.DeathFramesHandler;

public class MultiPlayerGame {
    private HashSet<Wall> walls;
    private HashSet<FoodPellet> foods;
    private HashSet<Ghost> ghosts;
    
    
    private Pacman yellowPacman;
    private Pacman redPacman;
    
    private Map map;
    
    private JPanel gamePanel;
    

    public MultiPlayerGame(MultiPlayerGameController gameController) {

        init(gameController);
        
    }

    public void init(MultiPlayerGameController gameController){

        initPanel();
        configurePanel();
        gamePanel.addKeyListener(gameController);
        gamePanel.setFocusable(true);
        initAttributes();
        
        initBoard();
        System.out.println("Board is initialized");
    }


    private void movePacmans(){


        MovementManager.forwardMove(yellowPacman);
        MovementManager.forwardMove(redPacman);
        MovementManager.teleportY(yellowPacman,MultiPlayerGameBoard.getBoardHeight());
        MovementManager.teleportY(redPacman,MultiPlayerGameBoard.getBoardHeight());


        // check yellowPacman collisoins
        for (Wall wall: walls){
            if(CollisionChecker.collisionWithStaticEntity(yellowPacman, wall)){
                MovementManager.reverseMove(yellowPacman);
                break;
            }
        }

        // check redPacman collisoins
        for (Wall wall: walls){
            if(CollisionChecker.collisionWithStaticEntity(redPacman, wall)){
                MovementManager.reverseMove(redPacman);
                break;
            }
        }   
    }

    private void moveGhosts(){

        for (Ghost ghost: ghosts){

            if (CollisionChecker.collisionWithGhost(ghost, yellowPacman)) {
                MultiPlayerAnimationBlinker.triggerDyingAnimationForMultiPlayerGame(yellowPacman, 1,gamePanel);
            }
    
            if (CollisionChecker.collisionWithGhost(ghost, redPacman)) {
                MultiPlayerAnimationBlinker.triggerDyingAnimationForMultiPlayerGame(redPacman, 2, gamePanel);
            }


            if( (ghost.getX() == MultiPlayerGameBoard.getTileSize()*6 || ghost.getX() == MultiPlayerGameBoard.getTileSize()*12 ) && ghost.getDirection() != 'L' && ghost.getDirection() != 'R'){
              
                MovementManager.updateDirection('L',walls,ghost,'M');
            }
           
            MovementManager.forwardMove(ghost);



            // check all collisoins
            for (Wall wall: walls){

                

                if(CollisionChecker.collisionWithStaticEntity(ghost, wall)){
                    MovementManager.reverseMove(ghost);
                    MovementManager.renewGhostMovement(ghost, walls,'M');
                }

            }
            MovementManager.teleportY(ghost, MultiPlayerGameBoard.getBoardHeight());
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


        // collision with food
        foodEaten = null;
        for(FoodPellet food : foods){
            if(CollisionChecker.collisionWithStaticEntity(redPacman, food)){
                foodEaten = food;
                redPacman.incrementScore();
            }
        }
        foods.remove(foodEaten);

    }
    public void moveEntity(MultiPlayerGameController gameController){

        
        movePacmans();
        moveGhosts();
        foodEating();
        
        if(foods.isEmpty()){
            init(gameController);
           
        }
        
    }

    public void reset(){
        GameReseter.resetPacmanPositions(redPacman);
        GameReseter.resetPacmanPositions(yellowPacman);
        GameReseter.resetMultiPlayerGhostsPositions(ghosts);
    }

    public void draw(Graphics g){

        
        if (yellowPacman.isPacmanDying()) {

            if ( MultiPlayerAnimationBlinker.getCurrentFrame() < DeathFramesHandler.getTotalFramesCount()) {
                g.drawImage(DeathFramesHandler.getDyingFrameForRed()[MultiPlayerAnimationBlinker.getCurrentFrame()], yellowPacman.getX(), yellowPacman.getY(), yellowPacman.getDimensions().getWidth(), yellowPacman.getDimensions().getHeight(), null);
            }
        } 

        if (redPacman.isPacmanDying()) {
            // Draw the dying frame for the PacMan in animation
            if (MultiPlayerAnimationBlinker.getCurrentFrame() < DeathFramesHandler.getTotalFramesCount()) {
                g.drawImage(DeathFramesHandler.getDyingFrameForRed()[MultiPlayerAnimationBlinker.getCurrentFrame()], redPacman.getX(), redPacman.getY(), redPacman.getDimensions().getWidth(), redPacman.getDimensions().getHeight(), null);
            }
        } 
       
            g.drawImage(yellowPacman.getImage(), yellowPacman.getX(), yellowPacman.getY(), yellowPacman.getDimensions().getWidth(), yellowPacman.getDimensions().getHeight(), null);

            g.drawImage(redPacman.getImage(), redPacman.getX(), redPacman.getY(), redPacman.getDimensions().getWidth(), redPacman.getDimensions().getHeight(), null);

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
            if(MultiPlayerGameStatus.isGameOver()){
                g.drawString("Game Over: " + String.valueOf(yellowPacman.getScore()), MultiPlayerGameBoard.getTileSize()/2, MultiPlayerGameBoard.getTileSize()/2);

            }
            else{
                g.drawString("Yellow Lives: " + String.valueOf(yellowPacman.getLives()) + "  Score: " +  String.valueOf(yellowPacman.getScore()), MultiPlayerGameBoard.getTileSize()/2, MultiPlayerGameBoard.getTileSize()/2);
                g.drawString("Red Lives: " + String.valueOf(redPacman.getLives()) + " Score: " + String.valueOf(redPacman.getScore()), MultiPlayerGameBoard.getBoardWidth() - 200, MultiPlayerGameBoard.getTileSize()/2);

            
        }
    }

    public void initGhostsMovement(){

        MovementManager.startGhostsMovement(ghosts,walls,'M');
    }

    private void configurePanel(){

        gamePanel.setPreferredSize(new Dimension(MultiPlayerGameBoard.getBoardWidth(),MultiPlayerGameBoard.getBoardHeight()));
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
        // redPacman = new Pacman();
        map = new Map();
    }

    private void initBoard() {
        
        MultiPlayerGameInitializer.initializeGame(this);
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

    public Pacman getRedPacman() {
        return redPacman;
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

    public void setRedPacman(Pacman pacman) {
        this.redPacman = pacman;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
