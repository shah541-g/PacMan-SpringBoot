package pages;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import org.json.JSONObject;
import player.Player;
import utils.requestUtilities.HttpUtil;
import widgetFactories.ButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static game.gameRunner.MultiPlayerRunner.MultiPlayerRunner.startMultiplayerGame;
import static widgetFactories.FrameFactory.getColoredFrame;

public class RequestKeyPage {
    public RequestKeyPage() {
        // Create the main frame
        JFrame frame = getColoredFrame("Request Key Page", 800, 600, Color.BLACK);

        frame.setLayout(new BorderLayout());
        // Create buttons using ButtonFactory
        JButton requestKeyButton = ButtonFactory.createActionButton("Request Key");
        JButton joinRoomButton = ButtonFactory.createActionButton("Join Room");

        // Add functionality to Request Key Button
        requestKeyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Request key by sending a POST request
                requestKey(frame);
            }
        });

        joinRoomButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String roomKey = JOptionPane.showInputDialog(frame, "Enter Room Key:");
                String playerEmail = Player.getEmail(); // Replace with actual email of the player

                if (roomKey != null && !roomKey.isEmpty() && playerEmail != null) {
                    // Create JSON for joining the room
                    JSONObject jsonRequestBody = new JSONObject();
                    jsonRequestBody.put("roomKey", roomKey);
                    jsonRequestBody.put("email", playerEmail);

                    // Get the player's local IP address
                    String playerIp = getLocalIpAddress();
                    jsonRequestBody.put("playerIp", playerIp); // Add IP address for the player

                    String url = "http://localhost:8080/api/multiplayer-games/join-room";
                    JSONObject response = HttpUtil.sendPostRequestWithResponse(url, jsonRequestBody.toString());

                    if (response != null) {
                        int playerId = response.getInt("playerId");
                        String pacmanColor = response.getString("pacmanColor");
                        JOptionPane.showMessageDialog(frame, "Joined as Player " + playerId + " (" + pacmanColor + ")");

                        // Show loading page while checking for the second player
                        showLoadingPage(frame);

                        // Start checking for second player after successful join
                        checkForSecondPlayer(frame, roomKey, playerId, pacmanColor);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to join the room!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Room Key and Email are required.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Center the buttons vertically using a BoxLayout
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalGlue()); // Add vertical spacing
        verticalBox.add(ButtonFactory.createCenteredButtonBox(requestKeyButton));
        verticalBox.add(Box.createVerticalStrut(20)); // Add spacing between buttons
        verticalBox.add(ButtonFactory.createCenteredButtonBox(joinRoomButton));
        verticalBox.add(Box.createVerticalGlue()); // Add vertical spacing

        // Add the vertical box to the center of the frame
        frame.add(verticalBox, BorderLayout.CENTER);

        // Set the frame visible
        frame.setVisible(true);
    }

    private void requestKey(JFrame frame) {
        // Prompt user for opponent's email
        String opponentEmail = JOptionPane.showInputDialog(frame, "Enter Opponent's Email:");
        if (opponentEmail != null && !opponentEmail.isEmpty() && !opponentEmail.equals(Player.getEmail())) {
            // Get the player's local IP address
            String playerIp = getLocalIpAddress();

            // Create the JSON request body with the player's email, opponent's email, and IP address
            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("player1Email", Player.getEmail()); // Replace with actual player1 email
            jsonRequestBody.put("player2Email", opponentEmail); // Use the entered opponent's email
            jsonRequestBody.put("playerIp", playerIp); // Add player IP address

            String requestBody = jsonRequestBody.toString();
            String url = "http://localhost:8080/api/multiplayer-games/create"; // Change to your server URL

            // Send POST request and get the response
            JSONObject response = HttpUtil.sendPostRequestWithResponse(url, requestBody);

            if (response != null) {
                // Handle the response (e.g., extracting the room key or displaying a message)
                String roomKey = response.getString("roomKey");


                // Create a panel to display the room key and a copy button
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

                // Add a text field to display the room key
                JTextField roomKeyField = new JTextField(roomKey);
                roomKeyField.setEditable(false);
                panel.add(roomKeyField);

                // Add a button with a copy icon
                JButton copyButton = new JButton("Copy");
                copyButton.addActionListener(e -> {
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                            new StringSelection(roomKey), null);
                    JOptionPane.showMessageDialog(frame, "Room key copied to clipboard!");
                });
                panel.add(copyButton);

                // Show the panel in a dialog
                JOptionPane.showMessageDialog(frame, panel, "Room Key", JOptionPane.INFORMATION_MESSAGE);

                // Show waiting page
                showLoadingPage(frame);

                // Check for the second player to join
                checkForSecondPlayer(frame, roomKey, -1, null);

            } else {
                JOptionPane.showMessageDialog(frame, "Error occurred while requesting room key.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Opponent's email is Wrong.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void showLoadingPage(JFrame frame) {
        // Create a simple loading/waiting message
        JLabel loadingLabel = new JLabel("Waiting for second player to join...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.getContentPane().removeAll();
        frame.getContentPane().add(loadingLabel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void checkForSecondPlayer(JFrame frame, String roomKey, int playerId, String pacmanColor) {
        // Create a timer to check periodically
        Timer timer = new Timer(5000, null); // Timer with a delay of 5 seconds

        // Timer Action
        timer.addActionListener(e -> {
            String url = "http://localhost:8080/api/multiplayer-games/check-room/" + roomKey;
            String responseString = HttpUtil.sendGetRequest(url);

            if (responseString != null) {
                JSONObject response = new JSONObject(responseString);

                // Check if the second player has joined
                if (response.getJSONArray("players").length() == 2) {
                    timer.stop(); // Stop the timer to avoid repeated calls

                    // Dispose of the waiting page
                    SwingUtilities.invokeLater(() -> {
                        frame.dispose(); // Close the waiting page
                        startMultiplayerGame(playerId, pacmanColor); // Start the multiplayer game
                    });
                }
            }
        });

        timer.start(); // Start the timer
    }


    private String getLocalIpAddress() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostAddress(); // Get the local IP address
        } catch (UnknownHostException e) {
            return "Unknown IP"; // Handle the error gracefully
        }
    }
}
