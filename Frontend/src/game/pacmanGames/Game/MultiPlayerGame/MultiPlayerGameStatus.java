package game.pacmanGames.Game.MultiPlayerGame;
public class MultiPlayerGameStatus {

    private static boolean gameOver = false;

    public static boolean isGameOver(){
        return gameOver;
    }
    public static void updateGameStatus(boolean newStatus){
        gameOver = newStatus;
    }

}
