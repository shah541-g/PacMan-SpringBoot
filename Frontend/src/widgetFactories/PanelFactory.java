package widgetFactories;

import javax.swing.*;
import java.awt.*;

public class PanelFactory {

    public static JPanel createTopPanel(String titleText) {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.BLACK);

        // Add space above the title
        addVerticleSpacing(topPanel,30);

        // Create and add title label
        JLabel titleLabel = new JLabel(titleText);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(titleLabel);

        // Add space after the title
        addVerticleSpacing(topPanel,20);

        return topPanel;
    }

    // Method to create the form panel with custom padding and background
    public static JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        formPanel.setBackground(Color.BLACK);
//        addVerticleSpacing(formPanel,30);
        return formPanel;
    }

    public static void addVerticleSpacing(JPanel panel,int height){
        SpaceFactory.addVerticleSpaceForPanel(panel,height);
    }


}
