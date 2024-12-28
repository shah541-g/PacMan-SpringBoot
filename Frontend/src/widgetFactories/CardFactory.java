package widgetFactories;

import game.gameRunner.MultiPlayerRunner.MultiPlayerRunner;
import game.gameRunner.SinglePlayerRunner.SinglePlayerRunner;
import game.pacmanGames.Game.SinglePlayerGame.SinglePlayerGameStatus;
import pages.RequestKeyPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.Instant;

public class CardFactory {


    public static JPanel getMultiplayerCard(JFrame frame) {

        Color multiplayerCardColor = new Color(144, 238, 144);
        int x = 450;
        int y = 200;
        String text = "Multiplayer Game";
        int width = 250;
        int height = 150;
        JPanel multiplayerCard = getCard(x,y,text,width,height,multiplayerCardColor);

        // Add a click event to the Multiplayer card
        multiplayerCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                SwingUtilities.invokeLater(RequestKeyPage::new);
            }
        });
        return multiplayerCard;
    }

    private static JPanel getCard(int x, int y, String text, int width, int height, Color color) {
        JPanel multiplayerCard = new JPanel();
        multiplayerCard.setBounds(x, y, width, height); // Position and size
        multiplayerCard.setBackground(color); // Light green color
        multiplayerCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel multiplayerLabel = new JLabel(text, SwingConstants.CENTER);
        multiplayerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        multiplayerCard.setLayout(new BorderLayout());
        multiplayerCard.add(multiplayerLabel, BorderLayout.CENTER);
        return multiplayerCard;
    }

    public static JPanel getSinglePlayerCard(JFrame frame) {


        Color singleplayerCardColor = new Color(173, 216, 230);
        int x = 100;
        int y = 200;
        String text = "Single Player Game";
        int width = 250;
        int height = 150;
        JPanel singlePlayerCard = getCard(x,y,text,width,height,singleplayerCardColor);

        // Add a click event to the Single game.Player card
        singlePlayerCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(!SinglePlayerGameStatus.isIsGameRunning()) {
                    SinglePlayerGameStatus.setGameStartingTime(Timestamp.from(Instant.now()));
                    System.out.println(SinglePlayerGameStatus.getGameStartingTime());
                    SwingUtilities.invokeLater(SinglePlayerRunner::new);
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Game is already running");
                }
            }
        });
        return singlePlayerCard;
    }

}
