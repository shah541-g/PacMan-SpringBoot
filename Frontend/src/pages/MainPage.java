package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import game.gameRunner.MultiPlayerRunner.MultiPlayerRunner;
import game.gameRunner.SinglePlayerRunner.SinglePlayerRunner;

public class MainPage {

    public MainPage(){

        showHomePage();
    }

    private void showHomePage(){
         // Create the main frame
        JFrame frame = new JFrame("Game Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set frame size
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(null); // Use absolute positioning

        // Set the background color of the content pane to black
        frame.getContentPane().setBackground(Color.BLACK);

        // Create a panel for the top left buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBounds(0, 0, 800, 35); // Position and size

        // Create buttons
        JButton button1 = new JButton("View Personal Details");
        JButton button2 = new JButton("Games History");
        JButton button3 = new JButton("Score");

        // Add buttons to the panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        // Add the button panel to the frame
        frame.add(buttonPanel);

        // Create a panel for the "Single game.Player Game" card
        JPanel singlePlayerCard = new JPanel();
        singlePlayerCard.setBounds(100, 200, 250, 150); // Position and size
        singlePlayerCard.setBackground(new Color(173, 216, 230)); // Light blue color
        singlePlayerCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel singlePlayerLabel = new JLabel("Single game.Player Game", SwingConstants.CENTER);
        singlePlayerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        singlePlayerCard.setLayout(new BorderLayout());
        singlePlayerCard.add(singlePlayerLabel, BorderLayout.CENTER);

        // Add a click event to the Single game.Player card
        singlePlayerCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // JOptionPane.showMessageDialog(frame, "Single game.Player Game clicked!");
                // // You can launch the single-player game here

                
                // new Thread(SinglePlayerRunner::new).start();

                 JOptionPane.showMessageDialog(frame, "Single game.Player Game clicked!");
                // Use SwingUtilities.invokeLater to safely start the background task
                SwingUtilities.invokeLater(() -> new SinglePlayerRunner());
            }
        });

        // Create a panel for the "Multiplayer Game" card
        JPanel multiplayerCard = new JPanel();
        multiplayerCard.setBounds(450, 200, 250, 150); // Position and size
        multiplayerCard.setBackground(new Color(144, 238, 144)); // Light green color
        multiplayerCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel multiplayerLabel = new JLabel("Multiplayer Game", SwingConstants.CENTER);
        multiplayerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        multiplayerCard.setLayout(new BorderLayout());
        multiplayerCard.add(multiplayerLabel, BorderLayout.CENTER);

        // Add a click event to the Multiplayer card
        multiplayerCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // JOptionPane.showMessageDialog(frame, "Multiplayer Game clicked!");
                // // You can launch the multiplayer game here
                // new Thread(MultiPlayerRunner::new).start();

                JOptionPane.showMessageDialog(frame, "MultiPlayer Game clicked!");
                // Use SwingUtilities.invokeLater to safely start the background task
                SwingUtilities.invokeLater(() -> new MultiPlayerRunner());
            }
        });

        // Add the cards to the frame
        frame.add(singlePlayerCard);
        frame.add(multiplayerCard);

        // Make the frame visible
        frame.setVisible(true);
    }
}
