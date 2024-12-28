package pages;

import org.json.JSONObject;
import player.Player;
import utils.requestUtilities.HttpUtil;

import javax.swing.*;
import java.awt.*;

import static widgetFactories.ButtonFactory.createActionButton;
import static widgetFactories.ButtonFactory.createCenteredButtonBox;
import static widgetFactories.FrameFactory.getFrame;
import static widgetFactories.SpaceFactory.addVerticleSpaceForPanel;

public class PersonalDetailsPage {

    private static final String BASE_URL = "http://localhost:8080/api/users"; // Adjust the base URL if necessary

    public static  void showPersonalDetails(JFrame mainFrame) {
        // Create a new window to display personal details
        JFrame detailsFrame = getFrame("Personal Details",400,300,JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to display personal details
        JPanel detailsPanel = getDetailsPanel();

        // Create font for the labels
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        // Get player details
        String username = Player.getUsername();
        String email = Player.getEmail();

        // Add the player details to the panel with styled labels
        JLabel usernameLabel = getUsernameLabel(username, labelFont);

        JLabel emailLabel = getEmailLabel(email, labelFont);

        // Add the labels to the details panel
        detailsPanel.add(usernameLabel);
        addVerticleSpaceForPanel(detailsPanel,10);
        detailsPanel.add(emailLabel);

        // Create a close button for the frame
        JButton closeButton = createActionButton("Close");
        closeButton.addActionListener(e -> detailsFrame.dispose());
        Box closeButtonCenteredBox = createCenteredButtonBox(closeButton);

        // Add the close button to the details panel
        detailsPanel.add(closeButtonCenteredBox);

        JButton logoutButton = createActionButton("Log Out");
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);

        // Add action listener for the logout button
        logoutButton.addActionListener(e -> {
            // Trigger logout when button is pressed
            boolean success = logout();
            if (success) {
                SwingUtilities.invokeLater(() -> {
                    detailsFrame.dispose(); // Close the detailsFrame
                    mainFrame.dispose();
                    new SignInPage(); // Start the new app
                });
            }
        });
        Box logoutButtonCenteredBox = createCenteredButtonBox(logoutButton);


        // Add the close button to the details panel

        addVerticleSpaceForPanel(detailsPanel,20);
        detailsPanel.add(closeButtonCenteredBox);
        addVerticleSpaceForPanel(detailsPanel,20);
        detailsPanel.add(logoutButtonCenteredBox);


        addVerticleSpaceForPanel(detailsPanel,40);

        detailsPanel.setBackground(Color.BLACK);

        // Add the details panel to the frame
        Box detailsBox = getDetailsBox(detailsPanel);

        detailsFrame.add(detailsBox);
        // Make the details frame visible
        detailsFrame.getContentPane().setBackground(Color.BLACK);
        detailsFrame.setVisible(true);
    }


    private static boolean logout(){

        // Prepare the request body with the email of the user logging out
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", Player.getEmail());

        // Send POST request to /signout endpoint
        JSONObject response = HttpUtil.sendPostRequestWithResponse(BASE_URL + "/signout", requestBody.toString());

        // If the response is successful, return true
        if (response != null && response.has("response") && response.getString("response").equals("Sign Out Successful")) {
            JOptionPane.showMessageDialog(null, "Successfully logged out.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Logout failed.");
            return false;
        }
    }

    private static Box getDetailsBox(JPanel detailsPanel) {
        Box detailsBox = Box.createHorizontalBox();
        detailsBox.add(Box.createHorizontalGlue());
        detailsBox.add(detailsPanel);
        detailsBox.add(Box.createHorizontalGlue());
        return detailsBox;
    }

    private static JLabel getEmailLabel(String email, Font labelFont) {
        JLabel emailLabel = new JLabel("Email: " + email);
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(Color.white);
        return emailLabel;
    }

    private static JLabel getUsernameLabel(String username, Font labelFont) {
        JLabel usernameLabel = new JLabel("Username: " + username);
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(Color.white);
        return usernameLabel;
    }

    private static JPanel getDetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return detailsPanel;
    }


}
