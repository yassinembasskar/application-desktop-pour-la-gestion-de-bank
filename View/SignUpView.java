package View;

import javax.swing.*;
import java.awt.*;

public class SignUpView extends JFrame{
    private JPanel mainPanel = new JPanel();
    private JPanel textPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JTextField firstNameTextField = new JTextField();
    private JTextField lastNameTextField = new JTextField();
    private JTextField emailTextField = new JTextField();
    private JTextField phoneNumberTextField = new JTextField();
    private JTextField motPassTextField = new JTextField();
    private JButton LoginButton = new JButton("Login");
    private JButton SignUpButton = new JButton("Sign Up");
    public SignUpView(){
        setSize(700, 240);
        setTitle("Sign Up");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(mainPanel);
        mainPanel.setLayout(new GridLayout(2,1));
        mainPanel.add(textPanel);
        mainPanel.add(buttonPanel);
        textPanel.setLayout(new GridLayout(5, 2));
        buttonPanel.setLayout(new FlowLayout());
        textPanel.add(new JLabel("First Name:"));
        textPanel.add(firstNameTextField);
        textPanel.add(new JLabel("Last Name:"));
        textPanel.add(lastNameTextField);
        textPanel.add(new JLabel("Email:"));
        textPanel.add(emailTextField);
        textPanel.add(new JLabel("Phone Number:"));
        textPanel.add(phoneNumberTextField);
        textPanel.add(new JLabel("Mot Passe:"));
        textPanel.add(motPassTextField);
        buttonPanel.add(LoginButton);
        buttonPanel.add(SignUpButton);
    }
    
    public String getFirstNameTextField() {
        return firstNameTextField.getText();
    }

    public String getLastNameTextField() {
        return lastNameTextField.getText();
    }

    public String getEmailTextField() {
        return emailTextField.getText();
    }

    public String getPhoneNumberTextField() {
        return phoneNumberTextField.getText();
    }

    public String getMotPassTextField() {
        return motPassTextField.getText();
    }

    public JButton getSignUpButton() {
        return SignUpButton;
    }
    public JButton getLoginButton() {
        return LoginButton;
    }
}

