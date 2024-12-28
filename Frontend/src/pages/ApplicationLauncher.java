package pages;

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


import static game.pacmanGames.imagesLoader.ImagesLoader.loadLogo;
import static widgetFactories.FrameFactory.getSplashScreenFrame;

public class ApplicationLauncher {

    public ApplicationLauncher(){

    }

    public void launchApplication(){

        // create splash frame
       JFrame frame = getSplashScreenFrame();

       // create logo image
       Image scaledImage = loadLogo().getScaledInstance(frame.getWidth(), 500, Image.SCALE_SMOOTH);
       JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
       imageLabel.setBounds(0, 0, frame.getWidth(), 500); // Place image at the top
       frame.add(imageLabel);

        // Create a progress bar
        JProgressBar progressBar = getProgressbar(frame);
        uiSetupForProgressBar(progressBar);
        frame.add(progressBar);

        // Make the frame visible
        frame.setVisible(true);

        // Simulate progress bar filling
        simulateProgressBarAndLaunchApplication(progressBar,frame);

    }


    private JProgressBar getProgressbar(JFrame frame){
        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds((frame.getWidth() - 600) / 2, 520, 600, 30); // Center the progress bar
        progressBar.setForeground(Color.YELLOW); // Set fill color
        progressBar.setBackground(Color.WHITE); // Set background color
        progressBar.setValue(0);
        progressBar.setStringPainted(true); // Show progress percentage

        return progressBar;
    }

    private void uiSetupForProgressBar(JProgressBar progressBar){
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
    }

    private void simulateProgressBarAndLaunchApplication(JProgressBar progressBar, JFrame frame){

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
                startApplication();
            });
        }).start();
    }

    private void startApplication(){
        new SignUpPage(); // Start the new app
    }
}
