package game.pacmanGames.Game.SinglePlayerGame;
import game.pacmanGames.entity.*;
import game.pacmanGames.entity.Enums.*;
import game.pacmanGames.imagesLoader.ImagesLoader;
import game.pacmanGames.map.*;

public class SinglePlayerGameInitializer {


    public static void initializeGame(SinglePlayerGame gameBoard){
        
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

        SinglePlayerGameBoard.setRows(rows);
        SinglePlayerGameBoard.setColumns(columns);
    }

    private static void MapInitializer(Map map){
        
        Maps maps = new Maps();
        MapsInitializer.initializeSinglePlayerMaps(maps);
        map.setMap(MapSelector.selectRandomMap(maps).getMap());

    }

    private static void LoadEntities(SinglePlayerGame game){


        for (int r = 0; r<SinglePlayerGameBoard.getRowCount(); r++){
            for(int c=0; c<SinglePlayerGameBoard.getColumnCount(); c++){

                String row = game.getMap().getMap()[r];
                char tileMapChar = row.charAt(c);

                int x = c*SinglePlayerGameBoard.getTileSize();
                int y = r*SinglePlayerGameBoard.getTileSize();

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
                else if(tileMapChar == ' '){ // food

                    FoodPellet food = new FoodPellet(ImageDimension.FOOD, x + 14 ,y + 14,EntityRepresentation.food,null);

                    game.getFoods().add(food);

                 }
                
            }
        }


        


    }

    

}
