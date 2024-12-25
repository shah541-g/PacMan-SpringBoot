package pages;


import utils.requestUtilities.HttpUtil;
import widgetFactories.ButtonFactory;
import widgetFactories.LabelFactory;
import widgetFactories.PanelFactory;
import widgetFactories.SpaceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static validations.EmailValidator.validateEmail;
import static validations.PasswordValidator.validatePassword;
import static validations.UsernameValidator.validateUsername;
import static widgetFactories.FieldFactory.addPasswordField;
import static widgetFactories.FieldFactory.addTextFieldWithPlaceholder;

public class SignUpPage {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private static final String usernamePlaceholder = "username";
    private static final String emailPlaceholder = "Email address";
    private static final String passwordPlaceholder = "Create a password";


    public SignUpPage() {



        frame = new JFrame("Create Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);


        // Create the top panel with a title
        JPanel topPanel = PanelFactory.createTopPanel("Create Account.");

        // Create the form panel
        JPanel formPanel = PanelFactory.createFormPanel();


        usernameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();

        addTextFieldWithPlaceholder(formPanel, "Username", usernamePlaceholder,usernameField);
        addTextFieldWithPlaceholder(formPanel, "Email address", emailPlaceholder,emailField);
        addPasswordField(formPanel, passwordPlaceholder,passwordField);

        // create account button
        JButton createAccountButton = ButtonFactory.createActionButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                signUp();
            }
        });
        Box buttonBox = ButtonFactory.createCenteredButtonBox(createAccountButton);
        formPanel.add(buttonBox);

        SpaceFactory.addVerticleSpaceForPanel(formPanel,15);

        // Already have an account
        String linkText = "Already have an account? Sign in.";
        JLabel signInLabel = LabelFactory.createHyperlinkLabel(linkText);
        // Add a MouseListener to handle the click
        signInLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    frame.dispose(); // Close the splash screen
                    new SignInPage(); // Start the new app
                });
            }
        });

        // Create a Box container for centering the sign-in label
        Box signInBox = LabelFactory.createCenteredLabelBox(signInLabel);
        // Add the box containing the label to the formPanel
        formPanel.add(signInBox);

        // Add panels to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
    }



    private boolean isValid(){
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String username = usernameField.getText();
        return !password.equals(passwordPlaceholder) && !email.equals(emailPlaceholder) && !username.equals(usernamePlaceholder);
    }

    private void signUp() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();

        if(isValid() && validateUsername(username) && validatePassword(password) && validateEmail(email)){


            String jsonRequestBody = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\", \"email\": \"" + email + "\"}";

            boolean requestSent = HttpUtil.sendPostRequest(
                    "http://localhost:8080/api/users/signup",  // URL
                    jsonRequestBody  // Request body as string
            );
            if(requestSent){
                frame.dispose();
                // Start the MainPage
                SwingUtilities.invokeLater(MainPage::new);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Error: Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



}
