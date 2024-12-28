package game.gameRunner.MultiPlayerRunner;

import javax.swing.JFrame;
import java.util.logging.Logger;
import java.util.logging.Level;

import game.pacmanGames.Game.MultiPlayerGame.MultiPlayerGameController;
import utils.loggingUtilities.LoggerUtility;
import game.pacmanGames.Game.MultiPlayerGame.MultiPlayerGame;
import game.pacmanGames.Game.MultiPlayerGame.MultiPlayerGameBoard;

public class MultiPlayerRunner {

    private static final Logger LOGGER = LoggerUtility.getLogger("MultiPlayerRunner","MultiPlayerRunner.log");

    public MultiPlayerRunner() {

    }

    public static void startMultiplayerGame(int playerId, String pacmanColor) {
        LOGGER.info("Starting multiplayer game with Player ID: " + playerId + " and Pac-Man color: " + pacmanColor);

        try {
            LOGGER.info("Initializing game frame for multiplayer...");
            JFrame frame = initializeGameFrame();

            LOGGER.info("Setting up game controller and multiplayer game...");
            MultiPlayerGameController gameController = new MultiPlayerGameController(playerId, pacmanColor);
            MultiPlayerGame game = new MultiPlayerGame(gameController);

            gameController.init(game);
            LOGGER.info("Game initialization complete for Player " + playerId);

            frame.add(game.getGamePanel());

            frame.pack();
            game.getGamePanel().requestFocusInWindow();
            frame.setVisible(true);

            LOGGER.info("Game window is now visible. Starting the multiplayer game loop...");
            gameController.startGame();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while starting the multiplayer game", e);
        }
    }


//    private static void launchMultiPlayerGame() {
//
//        LOGGER.info("Initializing game frame...");
//        JFrame frame = initializeGameFrame();
//
//        LOGGER.info("Setting up game controller and game...");
//        MultiPlayerGameController gameController = new MultiPlayerGameController();
//        MultiPlayerGame game = new MultiPlayerGame(gameController);
//
//        gameController.init(game);
//        LOGGER.info("Game initialization complete.");
//
//        frame.add(game.getGamePanel());
//
//        frame.pack();
//        game.getGamePanel().requestFocusInWindow();
//        frame.setVisible(true);
//
//        LOGGER.info("Game window is now visible. Starting the game loop...");
//        gameController.startGame();
//    }

    private static JFrame initializeGameFrame() {
        
        JFrame frame = new JFrame("Pac-Man: Multi game.Player");
        frame.setSize(MultiPlayerGameBoard.getBoardWidth(), MultiPlayerGameBoard.getBoardHeight());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LOGGER.info("Game frame initialized with size: " +
                MultiPlayerGameBoard.getBoardWidth() + "x" +
                MultiPlayerGameBoard.getBoardHeight());

        return frame;
    }
}
