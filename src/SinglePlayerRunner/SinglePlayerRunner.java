package SinglePlayerRunner;

import javax.swing.JFrame;
import java.util.logging.Logger;
import java.util.logging.Level;

import PacMan_Games.Game.SinglePlayerGame.SinglePayerGameController;
import PacMan_Games.Game.SinglePlayerGame.SinglePlayerGame;
import PacMan_Games.Game.SinglePlayerGame.SinglePlayerGameBoard;
import Utils.LoggerUtility;

public class SinglePlayerRunner {

    // Logger instance for logging events
    private static final Logger LOGGER = LoggerUtility.getLogger("SinglePlayerRunner", "SinglePlayerRunner.log");

    public SinglePlayerRunner() {
        try {
            LOGGER.info("Launching Single Player Pac-Man game...");
            launchSinglePlayerGame();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while launching the game", e);
        }
    }

    private static void launchSinglePlayerGame() {
        
        LOGGER.info("Initializing game frame...");
        JFrame frame = initializeGameFrame();

        LOGGER.info("Setting up game controller and game...");
        SinglePayerGameController gameController = new SinglePayerGameController();
        SinglePlayerGame game = new SinglePlayerGame(gameController);

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
        
        JFrame frame = new JFrame("Pac-Man: Single Player");
        frame.setSize(SinglePlayerGameBoard.getBoardWidth(), SinglePlayerGameBoard.getBoardHeight());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LOGGER.info("Game frame initialized with size: " +
                SinglePlayerGameBoard.getBoardWidth() + "x" +
                SinglePlayerGameBoard.getBoardHeight());

        return frame;
    }
}
