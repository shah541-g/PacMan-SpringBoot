package pages;

import com.sun.tools.javac.Main;
import org.json.JSONObject;
import player.Player;
import utils.requestUtilities.HttpUtil;
import widgetFactories.ButtonFactory;
import widgetFactories.LabelFactory;
import widgetFactories.PanelFactory;
import widgetFactories.SpaceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static validations.EmailValidator.validateEmail;
import static validations.PasswordValidator.validatePassword;
import static widgetFactories.FieldFactory.addPasswordField;
import static widgetFactories.FieldFactory.addTextFieldWithPlaceholder;
import static widgetFactories.FrameFactory.getFrame;

public class SignInPage {

    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private static final String emailPlaceholder = "Email address";
    private static final String passwordPlaceholder = "Create a password";

    public SignInPage() {



        frame = getFrame("Sign In", 400, 500, JFrame.EXIT_ON_CLOSE);


        // Create the top panel with a title
        JPanel topPanel = PanelFactory.createTopPanel("Sign In");

        // Create the form panel
        JPanel formPanel = PanelFactory.createFormPanel();


        emailField = new JTextField();
        passwordField = new JPasswordField();

        addTextFieldWithPlaceholder(formPanel, "Email", "email", emailField);
        addPasswordField(formPanel, "Create a password",passwordField);

        // create account button
        JButton createAccountButton = ButtonFactory.createActionButton("Sign In");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signIn();
            }
        });
        Box buttonBox = ButtonFactory.createCenteredButtonBox(createAccountButton);
        formPanel.add(buttonBox);

        SpaceFactory.addVerticleSpaceForPanel(formPanel,15);

        // Already have an account
        String linkText = "Have no account? Create Account.";
        JLabel signInLabel = LabelFactory.createHyperlinkLabel(linkText);
        // Add a MouseListener to handle the click
        signInLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Logic to open the sign-in page
                frame.dispose();
                new SignUpPage();
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

        return !password.equals(passwordPlaceholder) && !email.equals(emailPlaceholder);
    }

    private void signIn() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if(isValid() && validatePassword(password) && validateEmail(email)){


            // Manually construct the JSON request body as a string
            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("email", email);
            jsonRequestBody.put("password", password);

            String requestBody = jsonRequestBody.toString();

            JSONObject response = HttpUtil.sendPostRequestWithResponse(
                    "http://localhost:8080/api/users/signin",  // URL
                    requestBody  // Request body as string
            );
            if(response!=null){
                Player.setEmail(email);
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
