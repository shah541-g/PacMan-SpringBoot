package MultiPlayerRunner;

import javax.swing.JFrame;
import java.util.logging.Logger;
import java.util.logging.Level;

import PacMan_Games.Game.MultiPlayerGame.MultiPlayerGameController;
import Utils.LoggerUtility;
import PacMan_Games.Game.MultiPlayerGame.MultiPlayerGame;
import PacMan_Games.Game.MultiPlayerGame.MultiPlayerGameBoard;

public class MultiPlayerRunner {

    private static final Logger LOGGER = LoggerUtility.getLogger("MultiPlayerRunner","MultiPlayerRunner.log");

    public MultiPlayerRunner() {
        try {
            LOGGER.info("Launching Multi Player Pac-Man game...");
            launchMultiPlayerGame();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while launching the game", e);
        }
    }

    private static void launchMultiPlayerGame() {
        
        LOGGER.info("Initializing game frame...");
        JFrame frame = initializeGameFrame();

        LOGGER.info("Setting up game controller and game...");
        MultiPlayerGameController gameController = new MultiPlayerGameController();
        MultiPlayerGame game = new MultiPlayerGame(gameController);

        gameController.init(game);
        LOGGER.info("Game initialization complete.");

        frame.add(game.getGamePanel());

        frame.pack();
        game.getGamePanel().requestFocusInWindow();
        frame.setVisible(true);

        LOGGER.info("Game window is now visible. Starting the game loop...");
        gameController.startGame();
    }

    private static JFrame initializeGameFrame() {
        
        JFrame frame = new JFrame("Pac-Man: Multi Player");
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
