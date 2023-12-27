package View;
import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame{
    private JPanel mainPanel = new JPanel();
    private JPanel textPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JTextField nomTextField = new JTextField();
    private JTextField motPassField = new JTextField();
    private JButton LoginButton = new JButton("Login");
    private JButton SignUpButton = new JButton("SignUp");
    public LoginView(){
        setSize(700, 240);
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(mainPanel);
        mainPanel.setLayout(new GridLayout(2,1));
        mainPanel.add(textPanel);
        mainPanel.add(buttonPanel);
        textPanel.setLayout(new GridLayout(2, 2));
        buttonPanel.setLayout(new FlowLayout());
        textPanel.add(new JLabel("Email:"));
        textPanel.add(nomTextField);
        textPanel.add(new JLabel("Mot Passe"));
        textPanel.add(motPassField);
        buttonPanel.add(LoginButton);
        buttonPanel.add(SignUpButton);
    }
    public String getNomTextField() {
        return nomTextField.getText();
    }
    public String getMotPassField() {
        return motPassField.getText();
    }
    public JButton getSignUpButton() {
        return SignUpButton;
    }
    public JButton getLoginButton() {
        return LoginButton;
    }
}

