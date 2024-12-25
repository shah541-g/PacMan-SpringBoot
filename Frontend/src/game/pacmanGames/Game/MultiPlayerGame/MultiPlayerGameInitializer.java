package game.pacmanGames.Game.MultiPlayerGame;
import game.pacmanGames.entity.*;
import game.pacmanGames.entity.Enums.*;
import game.pacmanGames.imagesLoader.ImagesLoader;
import game.pacmanGames.map.*;

public class MultiPlayerGameInitializer {


    public static void initializeGame(MultiPlayerGame gameBoard){
        
        MapInitializer(gameBoard.getMap());
        BoardRowsAndColumnsSetter(gameBoard.getMap());
        LoadEntities(gameBoard);
    }

    private static void BoardRowsAndColumnsSetter(Map map){
        if (map == null) {
            throw new IllegalArgumentException("Map cannot be null");
        }
        int rows = map.getMap().length;
        int columns = map.getMap()[0].length();

        MultiPlayerGameBoard.setRows(rows);
        MultiPlayerGameBoard.setColumns(columns);
    }

    private static void MapInitializer(Map map){
        
        Maps maps = new Maps();
        MapsInitializer.initializeMultiplayerMaps(maps);
        map.setMap(MapSelector.selectRandomMap(maps).getMap());

    }

    private static void LoadEntities(MultiPlayerGame game){


        for (int r = 0; r<MultiPlayerGameBoard.getRowCount(); r++){
            for(int c=0; c<MultiPlayerGameBoard.getColumnCount(); c++){

                String row = game.getMap().getMap()[r];
                char tileMapChar = row.charAt(c);

                int x = c*MultiPlayerGameBoard.getTileSize();
                int y = r*MultiPlayerGameBoard.getTileSize();

                if (tileMapChar == 'X'){ // blue wall
                    Wall wall = new Wall(ImageDimension.WALL, x,y,EntityRepresentation.wall,ImagesLoader.loadWallImage());

                    
                    game.getWalls().add(wall);
                }
                else if(tileMapChar == 'b'){ // blue ghost
                    Ghost ghost = new Ghost(ImageDimension.GHOST, x,y,EntityRepresentation.blueGhost,ImagesLoader.loadBlueGhostImage(),x,y, Directions.LEFT.getSymbol(), 0,0);
                    game.getGhosts().add(ghost);
                }
                else if(tileMapChar == 'o'){ // orange ghost
                    Ghost ghost = new Ghost(ImageDimension.GHOST, x,y,EntityRepresentation.orangeGhost,ImagesLoader.loadOrangeGhostImage(),x,y, Directions.LEFT.getSymbol(), 0,0);
                    game.getGhosts().add(ghost);
                }
                else if(tileMapChar == 'r'){ // red ghost
                    Ghost ghost = new Ghost(ImageDimension.GHOST, x,y,EntityRepresentation.redGhost,ImagesLoader.loadRedGhostImage(),x,y, Directions.LEFT.getSymbol(), 0,0);
                    game.getGhosts().add(ghost);
                }
                else if(tileMapChar == 'p'){ // pink ghost
                    Ghost ghost = new Ghost(ImageDimension.GHOST, x,y,EntityRepresentation.pinkGhost,ImagesLoader.loadPinkGhostImage(),x,y, Directions.LEFT.getSymbol(), 0,0);
                    game.getGhosts().add(ghost);
                }
                else if(tileMapChar == 'P'){ // PacMan 1
                    Pacman pacman = new Pacman(ImageDimension.PACMAN, x,y,EntityRepresentation.yellowPacman,ImagesLoader.loadPacMan1LeftImage(),x,y, Directions.LEFT.getSymbol(), 0,0);
                    
                    game.setYellowPacman(pacman);
                }
                else if(tileMapChar == 'Q'){ // PacMan 2
                    
                    Pacman pacman = new Pacman(ImageDimension.PACMAN, x,y,EntityRepresentation.redPacman,ImagesLoader.loadPacMan2LeftImage(),x,y, Directions.LEFT.getSymbol(), 0,0);
                    
                    game.setRedPacman(pacman);
                    
                }
                else if(tileMapChar == ' '){ // food

                    FoodPellet food = new FoodPellet(ImageDimension.FOOD, x + 14 ,y + 14,EntityRepresentation.food,null);

                    game.getFoods().add(food);

                 }
                
            }
        }


        


    }

    

}
