package Game.SinglePlayerGame;
public class SinglePlayerGameStatus {

    private static boolean gameOver = false;

    public static boolean isGameOver(){
        return gameOver;
    }
    public static void updateGameStatus(boolean newStatus){
        gameOver = newStatus;
    }

}
