package widgetFactories;

import javax.swing.*;
import java.awt.*;

import static behaviors.TextFieldPlaceHolderBehavior.setPlaceholderBehavior;

public class FieldFactory {

    public static void addPasswordField(JPanel formPanel, String placeholder, JPasswordField passwordField) {
        passwordField.setText(placeholder);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        passwordField.setForeground(Color.GRAY);
        setPlaceholderBehavior(passwordField, placeholder);
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20));
    }

    public static void addTextFieldWithPlaceholder(JPanel formPanel, String label, String placeholder, JTextField textField) {
        textField.setText(placeholder);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        setPlaceholderBehavior(textField, placeholder);
        formPanel.add(textField);
        formPanel.add(Box.createVerticalStrut(20));
    }


    public static void addSideBySideTextFieldWithLabel(JPanel formPanel,JLabel label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.add(label, BorderLayout.WEST);
        panel.add(field, BorderLayout.EAST);
        formPanel.add(panel);
    }

    public static void addUpsideDownTextFieldWithLabel(JPanel formPanel,JLabel label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.SOUTH);
        formPanel.add(panel);
    }

}
