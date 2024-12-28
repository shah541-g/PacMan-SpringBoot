package widgetFactories;

import javax.swing.*;
import java.awt.*;

public class LabelFactory {

    public static JLabel createHyperlinkLabel(String text) {
        JLabel label = new JLabel("<html><u>" + text + "</u></html>");
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(Color.BLUE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand
        return label;
    }

    public static Box createCenteredLabelBox(JLabel label) {
        Box labelBox = Box.createHorizontalBox();
        labelBox.add(Box.createHorizontalGlue()); // Add flexible space on the left
        labelBox.add(label); // Add the label
        labelBox.add(Box.createHorizontalGlue()); // Add flexible space on the right
        return labelBox;
    }

    public static JLabel getCustomLabel(String message, Color color, int fontSize){

        JLabel customLabel = new JLabel();
        customLabel.setText(message);
        customLabel.setFont(new Font("Arial", Font.BOLD, fontSize));
        customLabel.setForeground(color);
        customLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return customLabel;
    }


}
