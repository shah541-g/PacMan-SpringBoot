package pages;

import game.gameRunner.SinglePlayerRunner.SinglePlayerRunner;
import game.pacmanGames.Game.SinglePlayerGame.SinglePlayerGameStatus;
import org.json.JSONObject;
import player.Player;
import utils.requestUtilities.HttpUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import static widgetFactories.ButtonFactory.getTextButton;
import static widgetFactories.FrameFactory.getFrame;
import static widgetFactories.LabelFactory.getCustomLabel;
import static widgetFactories.SpaceFactory.addVerticleSpaceForPanel;

public class GameOutcomePage {


    private final JFrame frame;

    public GameOutcomePage(String gameOutcome,JFrame gameFrame) {

        sendGameResults();

        // Initialize the frame
        frame = getFrame("Game Outcome", 400,  300, JFrame.EXIT_ON_CLOSE);

        // Create a panel for the content
        JPanel panel = getContentPanel();

        // Add a label to show the game outcome
        String message = getOutcomeMessage(gameOutcome);
        Color color = getOutcomeColor(gameOutcome);
        int fontSize = 20;

        JLabel outcomeLabel = getCustomLabel(message,color,fontSize);
        panel.add(outcomeLabel);

        // Add a "Play Again" button

        addPlayButton(gameFrame,panel);

        // Add an "Exit" button
        addExitButton(gameFrame,panel);

        // Add panel to frame
        frame.add(panel);

        show();
    }

    private JPanel getContentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    private void addPlayButton(JFrame gameFrame, JPanel panel){

        JButton playAgainButton = getTextButton(gameFrame,"Play Again");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SinglePlayerGameStatus.setIsGameRunning(false);
                gameFrame.dispose();
//                 Code to restart the game
                startNewGame();
            }
        });
        addVerticleSpaceForPanel(panel,20);
        panel.add(playAgainButton);
    }

    private void addExitButton(JFrame gameFrame,JPanel panel){

        JButton exitButton = getTextButton(frame,"Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SinglePlayerGameStatus.setIsGameRunning(false);
                gameFrame.dispose();
                frame.dispose();
            }
        });
        addVerticleSpaceForPanel(panel,10);
        panel.add(exitButton);
    }

    private String getOutcomeMessage(String gameOutcome) {
        return switch (gameOutcome.toLowerCase()) {
            case "win" -> "Congratulations! You Won!";
            case "lose" -> "Game Over! You Lost.";
            default -> "Game Outcome Undetermined.";
        };
    }

    private Color getOutcomeColor(String gameOutcome) {
        return switch (gameOutcome.toLowerCase()) {
            case "win" -> Color.GREEN;
            case "lose" -> Color.RED;
            default -> Color.BLACK;
        };
    }

    private void startNewGame() {

        frame.dispose();
        SwingUtilities.invokeLater(SinglePlayerRunner::new);
    }

    public void show() {
        frame.setVisible(true);
    }

    private void sendGameResults() {
        int score = SinglePlayerGameStatus.getScore();
        String status = SinglePlayerGameStatus.getGameOutcome();
        Timestamp timestamp = SinglePlayerGameStatus.getGameStartingTime();
        String timestampStr = timestamp.toInstant().toString(); // ISO 8601 format
        String email = Player.getEmail();

        System.out.println("Game starting time: " + timestamp);


        // Create the JSON request body
        JSONObject jsonRequestBody = new JSONObject();
        jsonRequestBody.put("playerEmail", email);
        jsonRequestBody.put("status", status);
        jsonRequestBody.put("score", score);
        jsonRequestBody.put("timestamp", timestampStr);

        // Convert JSONObject to a string
        String requestBodyString = jsonRequestBody.toString();


        String apiUrl = "http://localhost:8080/api/single-player-games";

        // Send the POST request
        boolean success = HttpUtil.sendPostRequest(apiUrl, requestBodyString);

        // Log the result
        if (success) {
            System.out.println("Game results sent successfully.");
        } else {
            System.out.println("Failed to send game results.");
        }
    }

}
