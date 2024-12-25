package UI;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import PacMan_Games.ImagesLoader.ImagesLoader;

public class ApplicationLauncher {

    public ApplicationLauncher(){

    }

    public void launchApplication(){
        JFrame frame = new JFrame("Splash Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 656); // Match the image resolution
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Set the background color of the frame
        frame.getContentPane().setBackground(Color.BLACK);


       Image scaledImage = ImagesLoader.loadLogo().getScaledInstance(frame.getWidth(), 500, Image.SCALE_SMOOTH);
       JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
       imageLabel.setBounds(0, 0, frame.getWidth(), 500); // Place image at the top
       frame.add(imageLabel);

        // Create a progress bar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds((frame.getWidth() - 600) / 2, 520, 600, 30); // Center the progress bar
        progressBar.setForeground(Color.YELLOW); // Set fill color
        progressBar.setBackground(Color.WHITE); // Set background color
        progressBar.setValue(0);
        progressBar.setStringPainted(true); // Show progress percentage

        // Custom UI to change progress text color dynamically
        progressBar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI() {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                super.paintDeterminate(g, c);
                String progressText = progressBar.getString();

                // Dynamically change the color based on progress
                
                Color textColor;

                
                    textColor = Color.BLACK;  // Use black for the first half of the progress
               

                // Set the color of the progress text
                g.setColor(textColor);

                // Center the progress text
                FontMetrics metrics = g.getFontMetrics();
                int x = (progressBar.getWidth() - metrics.stringWidth(progressText)) / 2;
                int y = (progressBar.getHeight() + metrics.getAscent()) / 2;

                // Draw the text
                g.drawString(progressText, x, y);
            }
        });

        frame.add(progressBar);

        // Make the frame visible
        frame.setVisible(true);

        // Simulate progress bar filling
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(50); // Simulate time-consuming task
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setValue(i);
            }
            // After progress bar completes, run the new App
            SwingUtilities.invokeLater(() -> {
                frame.dispose(); // Close the splash screen
                new HomePage(); // Start the new app
            });
        }).start();
    }
}
