package Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

import View.*;
import DAO.*;
import Model.*;
import java.awt.event.*;

public class BankController {
    private LoginView viewL;
    private SignUpView viewS;
    private BankView bankView;
    private Person p = null;
    private PersonDAO daoP = new PersonDAO();
    private AccountDAO daoA = new AccountDAO();
    private BankTransactionDAO daoT = new BankTransactionDAO();

    private static Scanner scanner = new Scanner(System.in);
    public BankController(){
        loginInterface();
    }
    public void displayMainMenu(){
        while (true) {
            System.out.println("-----Menu principal-----");
            System.out.println("1. Creer un compte");
            System.out.println("2. Update un compte");
            System.out.println("3. Supprimer un compte");
            System.out.println("4. Effectuer une transaction");
            System.out.println("5. Afficher historique des transactions");
            System.out.println("0. Quitter");
            System.out.println("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    updateAccount();
                    break;
                case 3:
                    deleteAccount();
                    break;
                case 4:
                    effectuerTransaction();
                    break;
                case 5:
                    displayTransaction();
                case 0:
                    System.out.println("Au Revoir !");
                    System.exit(0);
                default:
                    System.out.println("Option non valide. Veuillez ressayer.");
            }
        }
    }
    void loginInterface(){
        try{
            viewL = new LoginView();
            this.viewL.getLoginButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    p = daoP.login(viewL.getNomTextField(),viewL.getMotPassField());
                    if(p!=null){
                        viewL.dispose();
                        bankInterface();
                    }
                }
            });
            this.viewL.getSignUpButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    viewL.dispose();
                    signUpInterface();
                }
            });
        } catch (Exception e){
            System.out.println("error with login: " + e.getMessage());
        }
    }
    void bankInterface(){
        bankView = new BankView();
        try{
            this.bankView.getCreateAccountButton().addActionListener(e->createAccount());
            this.bankView.getUpdateAccountButton().addActionListener(e->updateAccount());
            this.bankView.getDeleteAccountButton().addActionListener(e->deleteAccount());
            this.bankView.getTransferButton().addActionListener(e->effectuerTransaction());
            this.bankView.getAfficherDeleteComptes().addActionListener(e->afficherComptes());
            this.bankView.getAfficherUpdateComptes().addActionListener(e->afficherComptes());

            this.bankView.getDisplayTransactionsButton().addActionListener(e->displayTransaction());
        } catch (Exception e) {
            System.out.println("error with bankView: " + e.getMessage());
        }
    }
    public void signUpInterface(){
        viewS = new SignUpView();
        try {
            this.viewS.getSignUpButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    p = daoP.signup(viewS.getFirstNameTextField(),viewS.getLastNameTextField(),viewS.getEmailTextField(),viewS.getPhoneNumberTextField(),viewS.getMotPassTextField());
                    if(p!=null){
                        viewS.dispose();
                        bankInterface();
                    }
                }
            });
            this.viewS.getLoginButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    viewS.dispose();
                    loginInterface();
                }
            });
        } catch (Exception e) {
            System.out.println("error with signup: " + e.getMessage());
        }
    }
    Person createClient(){
        System.out.println("entrer votre first nom: ");
        String firstName = scanner.nextLine()+scanner.nextLine();
        System.out.println("entrer votre last nom: ");
        String lastName = scanner.nextLine()+scanner.nextLine();
        System.out.println("entrer votre email: ");
        String email = scanner.nextLine()+scanner.nextLine();
        System.out.println("entrer votre phone: ");
        String phoneNumber = scanner.nextLine()+scanner.nextLine();
        System.out.println("entrer votre password");
        String password = scanner.nextLine()+scanner.nextLine();
        System.out.println("votre compte a ete creer");
        return daoP.signup(firstName, lastName, email, phoneNumber, password);
    }
    void createAccount(){
        if(bankView.getAccountLabelField()!=""){
            Account account = new Account(p,bankView.getAccountLabelField());
            daoA.createAccount(account);
        }
    }
    void afficherComptes(){
        if (daoA.showAccounts(p)) {
            System.out.println("this is the your accounts");
        } else {
            System.out.println("you have 0 accounts");
        }
    }
    void updateAccount(){
        if(daoA.showAccounts(p)){
            if (bankView.getUpdateAccountNumber()!=0) {
                Account account = daoA.getAccountById(bankView.getUpdateAccountNumber());
                if (account!=null && daoA.checkHolder(account,p)){
                    if (!bankView.getUpdateEmailHolderField().trim().isEmpty() && !bankView.getUpdatePasswordField().trim().isEmpty()) {
                        System.out.println("dkhl ->" + bankView.getUpdateEmailHolderField() + "; dkhl ->" + bankView.getUpdatePasswordField());
                        Person p1 = daoP.login(bankView.getUpdateEmailHolderField(),bankView.getUpdatePasswordField());
                        if (p1!=null) {
                            account.setAccountHolder(p1);
                        } else {
                            System.out.println("le person n'est pas modifier");
                        }
                    }
                    if (!bankView.getUpdateLabelField().trim().isEmpty()) {
                        System.out.println("dkhl  ->" + bankView.getUpdateLabelField() + ";");
                        account.setLabel(bankView.getUpdateLabelField());
                    }
                    if (!daoA.getAccountById(bankView.getUpdateAccountNumber()).equals(account)) {
                        daoA.updateAccount(account);
                    }
                } else {
                    System.out.println("le id que vous avez entrer n'existe pas");
                }
            }
        }
    }
    void deleteAccount(){
        if(daoA.showAccounts(p)){
            if (bankView.getDeleteAccountNumberField()!=0) {
                Account account = daoA.getAccountById(bankView.getDeleteAccountNumberField());
                if (account!=null && daoA.checkHolder(account,p)){
                    daoA.deleteAccount(account);
                } else {
                    System.out.println("le id que vous avez entrer n'existe pas");
                }
            }
        }
    }
    void effectuerTransaction(){
        if (daoA.showAccounts(p)) {
            if (bankView.getSourceAccountField()!=0) {
                Account account = daoA.getAccountById(bankView.getSourceAccountField());
                if (account!=null && daoA.checkHolder(account,p)){
                    switch (bankView.getTypeField()) {
                        case "DEPOT":
                            depotTrans(account);
                            break;
                        case "RETRAIT":
                            retraitTrans(account);
                            break;
                        case "TRANSFERT":
                            transfertTrans(account);
                            break;
                        default:
                            System.out.println("sorry, there is no such type");
                            break;
                    }
                } else {
                    System.out.println("le id que vous avez entrer n'existe pas");
                }
            }
        }
    }
    void depotTrans(Account account){
        if (bankView.getTransferAmountField()!=0) {
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            LocalDateTime currentDateTime = LocalDateTime.of(currentDate, currentTime);
            Date currentDateAsDate = java.sql.Timestamp.valueOf(currentDateTime);
            BankTransaction transaction = new BankTransaction(bankView.getTransferAmountField(),currentDateAsDate,TransactionType.DEPOT,account.getAccountNumber());
            daoT.createBankTransaction(transaction);
        }
    }
    void retraitTrans(Account account){
        if (bankView.getTransferAmountField()!=0) {
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            LocalDateTime currentDateTime = LocalDateTime.of(currentDate, currentTime);
            Date currentDateAsDate = java.sql.Timestamp.valueOf(currentDateTime);
            BankTransaction transaction = new BankTransaction(bankView.getTransferAmountField(),currentDateAsDate,TransactionType.RETRAIT,account.getAccountNumber());
            daoT.createBankTransaction(transaction);
        }
    }
    void transfertTrans(Account account){
        if (bankView.getTransferAmountField()!=0) {
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            LocalDateTime currentDateTime = LocalDateTime.of(currentDate, currentTime);
            Date currentDateAsDate = java.sql.Timestamp.valueOf(currentDateTime);
            Account endAccount = daoA.getAccountByLabel(bankView.getTargetEmailHolderField(),bankView.getTargetLabelAccountField());
            if (endAccount!=null) {
                BankTransaction transaction = new BankTransaction(bankView.getTransferAmountField(),currentDateAsDate,TransactionType.TRANSFERT,account.getAccountNumber(),endAccount.getAccountNumber());
                daoT.createBankTransaction(transaction);
            }
        }
    }
    void displayTransaction(){
        if (daoA.showAccounts(p)) {
            if (bankView.getDisplayTransAccountNumber()!=0) {
                Account account = daoA.getAccountById(bankView.getDisplayTransAccountNumber());
                if (account!=null && daoA.checkHolder(account,p)){
                    bankView.getDisplayTextArea().setText("");
                    for (String x : daoT.displayTransactions(account)) {
                        bankView.getDisplayTextArea().append(x + "\n");
                    }
                } else {
                    System.out.println("le id que vous avez entrer n'existe pas");
                }
            }
        }
    }
}