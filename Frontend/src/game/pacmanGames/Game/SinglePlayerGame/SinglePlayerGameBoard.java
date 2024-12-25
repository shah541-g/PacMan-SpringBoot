package game.pacmanGames.Game.SinglePlayerGame;

public class SinglePlayerGameBoard {


    private static int rowCount = 21;
    private static int columnCount = 19;
    private static int tileSize = 32;
    private static int boardWidth = columnCount * tileSize;
    private static int boardHeight = rowCount * tileSize;


    public static int getRowCount() {
        return rowCount;
    }

    public static int getColumnCount() {
        return columnCount;
    }

    
    public static void setRows(int rows) {
        rowCount = rows;
        boardHeight = rowCount * tileSize;
    }

    public static void setColumns(int columns) {
        columnCount = columns;
        boardWidth = columnCount * tileSize;
    }

    public static int getBoardWidth(){
        return boardWidth;
    }

    
    public static int getBoardHeight(){
        return boardHeight;
    }

    public static int getTileSize(){
        return tileSize;
    }


}
