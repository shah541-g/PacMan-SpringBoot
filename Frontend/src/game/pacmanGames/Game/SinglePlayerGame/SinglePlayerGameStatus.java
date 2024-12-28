package game.pacmanGames.Game.SinglePlayerGame;


import java.sql.Timestamp;

public class SinglePlayerGameStatus {

    private static boolean gameOver = false;
    private static String gameOutcome;
    private static int score;
    private static Timestamp gameStartingTime;
    private static boolean isGameRunning = false;

    // Getter for gameOver
    public static boolean isGameOver() {
        return gameOver;
    }

    // Setter for gameOver
    public static void updateGameStatus(boolean newStatus) {
        gameOver = newStatus;
    }

    // Getter for gameOutcome
    public static String getGameOutcome() {
        return gameOutcome;
    }

    // Setter for gameOutcome
    public static void setGameOutcome(String outcome) {
        gameOutcome = outcome;
    }

    // Getter for score
    public static int getScore() {
        return score;
    }

    // Setter for score
    public static void setScore(int newScore) {
        score = newScore;
    }

    // Getter for currentTimestamp
    public static Timestamp getGameStartingTime() {
        return gameStartingTime;
    }

    // Setter for currentTimestamp
    public static void setGameStartingTime(Timestamp timestamp) {
        gameStartingTime = timestamp;
    }

    public static boolean isIsGameRunning() {
        return isGameRunning;
    }

    public static void setIsGameRunning(boolean isGameRunning) {
        SinglePlayerGameStatus.isGameRunning = isGameRunning;
    }
}


