package View;
import javax.swing.*;
import java.awt.*;

public class BankView extends JFrame {
    private JTabbedPane tabbedPane;
    private JButton createAccountButton;
    private JButton transferButton;
    private JButton updateAccountButton;
    private JButton deleteAccountButton; 
    private JButton displayTransactionsButton;
    private JTextArea displayTextArea;
    private JTextField accountLabelField;
    private JTextField updateAccountNumber;
    private JTextField updateLabelField;
    private JTextField updateEmailHolderField;
    private JTextField updatePasswordField;
    private JTextField deleteAccountNumberField;
    private JTextField typeField;
    private JTextField sourceAccountField;
    private JTextField targetAccountLabelField;
    private JTextField targetEmailHolderField;
    private JTextField transferAmountField;
    private JButton afficherUpdateComptes;
    private JTextField displayTransAccountNumber;
    private JButton afficherDeleteComptes;
    public BankView() {

        setTitle("Application Bancaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setVisible(true);

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Creer un Compte", createAccountPanel());
        tabbedPane.addTab("Modifier le Compte", updateAccountPanel());
        tabbedPane.addTab("Supprimer le Compte", deleteAccountPanel());
        tabbedPane.addTab("Afficher les transactions", displayTransactions());
        tabbedPane.addTab("Transferer de l'Argent", transferMoneyPanel());

        add(tabbedPane);

        pack();
    }

    private JPanel createAccountPanel() {
        JPanel panel = new JPanel();
        accountLabelField = new JTextField(10);

        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Label:"));
        panel.add(accountLabelField);
        createAccountButton = new JButton("Creer");
        panel.add(createAccountButton);

        return panel;
    }

    private JPanel updateAccountPanel() {
        JPanel panel = new JPanel();
        updateAccountNumber = new JTextField(10);
        updateLabelField = new JTextField(10);
        updateEmailHolderField = new JTextField(10);
        updatePasswordField = new JTextField(10);

        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Account number of the update account:"));
        panel.add(updateAccountNumber);
        panel.add(new JLabel("Label de compte:"));
        panel.add(updateLabelField);
        panel.add(new JLabel("Email du titulaire:"));
        panel.add(updateEmailHolderField);
        panel.add(new JLabel("Password du titulaire:"));
        panel.add(updatePasswordField);

        updateAccountButton = new JButton("Modifier");
        afficherUpdateComptes = new JButton("Afficher les comptes");
        panel.add(updateAccountButton);
        panel.add(afficherUpdateComptes);
        return panel;
    }

    private JPanel deleteAccountPanel() {
        JPanel panel = new JPanel();
        deleteAccountNumberField = new JTextField(10);

        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Account Number:"));
        panel.add(deleteAccountNumberField);

        deleteAccountButton = new JButton("Supprimer");
        afficherDeleteComptes = new JButton("Afficher les comptes");
        panel.add(deleteAccountButton);        
        panel.add(afficherDeleteComptes);


        return panel;
    }

    private JPanel displayTransactions() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        displayTextArea = new JTextArea(10, 30);
        displayTextArea.setEditable(false);
        displayTransAccountNumber = new JTextField(10);
        JScrollPane scrollPane = new JScrollPane(displayTextArea);
        panel.add(displayTransAccountNumber);
        panel.add(scrollPane);

        displayTransactionsButton = new JButton("Afficher");
        panel.add(displayTransactionsButton);

        return panel;
    }

    private JPanel transferMoneyPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        targetEmailHolderField = new JTextField();
        targetAccountLabelField = new JTextField();
        sourceAccountField = new JTextField();
        transferAmountField = new JTextField();
        typeField = new JTextField();
        JLabel typeLabel = new JLabel("Type (DEPOT/RETRAIT/TRANSFERT)");
        JLabel amountLabel = new JLabel("Montant:");
        JLabel targetEmailLabel = new JLabel("Email Target:");
        JLabel targetLabel = new JLabel("Label account Target:");
        JLabel sourceLabel = new JLabel("Source Account Number");
        transferButton = new JButton("Transferer");

        panel.add(sourceLabel);
        panel.add(sourceAccountField);
        panel.add(typeLabel);
        panel.add(typeField);
        panel.add(targetEmailLabel);
        panel.add(targetEmailHolderField);
        panel.add(targetLabel);
        panel.add(targetAccountLabelField);
        panel.add(amountLabel);
        panel.add(transferAmountField);
        panel.add(transferButton);

        return panel;
    }
    public JButton getCreateAccountButton() {
        return createAccountButton;
    }
    public String getAccountLabelField() {
        return accountLabelField.getText();
    }
    public JButton getUpdateAccountButton() {
        return updateAccountButton;
    }
    public String getUpdateLabelField() {
        return updateLabelField.getText();
    }
    public int getUpdateAccountNumber() {
        try {
            return Integer.parseInt(updateAccountNumber.getText());
        } catch (Exception e) {
            System.out.println(deleteAccountNumberField.getText());
            System.out.println("jjjjjjjjjjj");
            System.out.println("vous n'avez entrer pas un nombre");
        }
        return 0;
    }
    public String getUpdateEmailHolderField() {
        return updateEmailHolderField.getText();
    }
    public String getUpdatePasswordField() {
        return updatePasswordField.getText();
    }
    public int getDeleteAccountNumberField() {
        try {
            return Integer.parseInt(deleteAccountNumberField.getText());
        } catch (Exception e) {
            System.out.println(deleteAccountNumberField.getText());
            System.out.println("hhhhhhhhhhhh");
            System.out.println("vous n'avez entrer pas un nombre");
        }
        return 0;
    }
    public JButton getDeleteAccountButton() {
        return deleteAccountButton;
    }
    public int getSourceAccountField() {
        try {
            return Integer.parseInt(sourceAccountField.getText());
        } catch (Exception e) {
            System.out.println(deleteAccountNumberField.getText());
            System.out.println("ccccccccccc");
            System.out.println("vous n'avez entrer pas un nombre");
        }
        return 0;
    }
    public String getTargetLabelAccountField() {
        return targetAccountLabelField.getText();
    }
    public String getTargetEmailHolderField() {
        return targetEmailHolderField.getText();
    }
    public String getTypeField() {
        return typeField.getText();
    }
    public int getTransferAmountField() {
        try {
            return Integer.parseInt(transferAmountField.getText());
        } catch (Exception e) {
            System.out.println(deleteAccountNumberField.getText());
            System.out.println("ssssssssssss");
            System.out.println("vous n'avez entrer pas un nombre");
        }
        return 0;
    }
    public JButton getTransferButton() {
        return transferButton;
    }
    public JButton getDisplayTransactionsButton() {
        return displayTransactionsButton;
    }
    public JTextArea getDisplayTextArea() {
        return displayTextArea;
    }
    public JButton getAfficherDeleteComptes() {
        return afficherDeleteComptes;
    }
    public JButton getAfficherUpdateComptes() {
        return afficherUpdateComptes;
    }
    public int getDisplayTransAccountNumber() {
        try {
            return Integer.parseInt(displayTransAccountNumber.getText());
        } catch (Exception e) {
            System.out.println("vous n'avez entrer pas un nombre");
        }
        return 0;
    }

}