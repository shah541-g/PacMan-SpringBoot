package pages;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.swing.*;

import com.sun.tools.javac.Main;
import org.json.JSONObject;
import player.Player;
import utils.requestUtilities.HttpUtil;

import static pages.PersonalDetailsPage.showPersonalDetails;
import static pages.SinglePlayerGameDetailsPage.showSinglePlayerGameDetailsPage;
import static widgetFactories.CardFactory.getMultiplayerCard;
import static widgetFactories.CardFactory.getSinglePlayerCard;
import static widgetFactories.FrameFactory.getColoredFrame;

public class MainPage {

    static JFrame frame;

    public MainPage(){

        showHomePage();
    }

    private void showHomePage(){

        getDetails();

        // get frame
        frame = getColoredFrame("Game Menu", 800, 600, Color.BLACK);

        // Create a panel for the top left buttons
        JPanel buttonPanel = getButtonPanel();

        // Add the button panel to the frame
        frame.add(buttonPanel);

        // Create a panel for the "Single game.Player Game" card
        JPanel singlePlayerCard = getSinglePlayerCard(frame);

        // Create a panel for the "Multiplayer Game" card
        JPanel multiplayerCard = getMultiplayerCard(frame);

        // Add the cards to the frame
        frame.add(singlePlayerCard);
        frame.add(multiplayerCard);


        // Make the frame visible
        frame.setVisible(true);
    }

    private static JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBounds(0, 0, 800, 35); // Position and size

        // Create buttons
        JButton button1 = new JButton("View Personal Details");
        JButton button3 = new JButton("Score");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               showPersonalDetails(frame);
            }
        });


        JComboBox<String> gameHistoryComboBox = getGameHistoryComboBox();

        // Add buttons to the panel
        buttonPanel.add(button1);
        buttonPanel.add(gameHistoryComboBox);
        buttonPanel.add(button3);
        return buttonPanel;
    }

    private static void fetchSinglePlayerGameDetails() {
        // Get the email from the Player object
        String email = Player.getEmail();

        // Construct the URL to fetch single-player game details by email
        String urlString = "http://localhost:8080/api/single-player-games/player/" + URLEncoder.encode(email, StandardCharsets.UTF_8);

        // Send GET request
        String jsonResponse = HttpUtil.sendGetRequest(urlString);

        // Check if the response is not null or empty and display the results
        if (jsonResponse != null && !jsonResponse.isEmpty()) {
            showSinglePlayerGameDetailsPage(jsonResponse);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to fetch Single Player Game details", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static JComboBox<String> getGameHistoryComboBox() {
        String[] gameHistoryOptions = {"Games History", "Single Player Game", "Multiplayer Game"};
        JComboBox<String> gameHistoryComboBox = new JComboBox<>(gameHistoryOptions);
        gameHistoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) gameHistoryComboBox.getSelectedItem();
                if ("Single Player Game".equals(selectedOption)) {
                    fetchSinglePlayerGameDetails();
                } else if ("Multiplayer Game".equals(selectedOption)) {
                    fetchMultiplayerGameDetails();
                }
            }
        });
        return gameHistoryComboBox;
    }

    private static void fetchMultiplayerGameDetails() {
        String email = Player.getEmail();
        String urlString = "http://localhost:8080/api/multiplayer-game/player/" + URLEncoder.encode(email, StandardCharsets.UTF_8);
        String jsonResponse = HttpUtil.sendGetRequest(urlString);

        if (jsonResponse != null) {
            JOptionPane.showMessageDialog(null, "Multiplayer Game Details: " + jsonResponse);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to fetch Multiplayer Game details", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void getDetails(){


        String email = Player.getEmail();
        String urlString = "http://localhost:8080/api/users/my-details?email=" + URLEncoder.encode(email, StandardCharsets.UTF_8);
        String jsonResponse = HttpUtil.sendGetRequest(urlString);
        boolean success = parsePlayerDetails(jsonResponse);

        if (!success){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }




    private static boolean parsePlayerDetails(String jsonResponse) {
        try {
            if (jsonResponse != null && !jsonResponse.isEmpty()) {
                // Create a JSONObject from the response string
                JSONObject jsonObject = new JSONObject(jsonResponse);

                // Extract player details
                String username = jsonObject.getString("username");
                String email = jsonObject.getString("email");

                // Set player details
                Player.setUsername(username);
                Player.setEmail(email);

                return true;
            } else {
                return false; // Invalid response
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Error during parsing
        }
    }


}
