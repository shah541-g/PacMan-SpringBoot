package widgetFactories;

import javax.swing.*;
import java.awt.*;

public class ButtonFactory {

    public static JButton createActionButton(String buttonText) {
        JButton actionButton = new JButton(buttonText);
        actionButton.setFont(new Font("Arial", Font.BOLD, 18));
        actionButton.setBackground(Color.YELLOW);
        actionButton.setForeground(Color.BLACK);
        actionButton.setFocusPainted(false);
        actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        actionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return actionButton;
    }

    public static Box createCenteredButtonBox(JButton button) {
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(button);
        buttonBox.add(Box.createHorizontalGlue());
        return buttonBox;
    }


}
