package behaviors;

import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TextFieldPlaceHolderBehavior {

    public static void setPlaceholderBehavior(JTextComponent field, String placeholder) {
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText(""); // Clear placeholder
                    field.setForeground(Color.BLACK); // Change text color
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder); // Restore placeholder
                    field.setForeground(Color.GRAY); // Change text color back to placeholder style
                }
            }
        });
    }
}
