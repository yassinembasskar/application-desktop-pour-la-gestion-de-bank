package DAO;

import java.sql.ResultSet;

import Model.Account;
import Model.Person;

public class AccountDAO implements IAccountDAO {
    DBConnection dbConnection = new DBConnection();
    PersonDAO daoP = new PersonDAO();   
    public AccountDAO(){

    }
    @Override
    public boolean showAccounts(Person person) {
         try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("select ACCOUNTNUMBER from account where ID_PERSON = "+daoP.findIdPerson(person)+";");
            if (!res.isBeforeFirst()) {
                System.out.println("you have 0 accounts;");
            } else {
                while(res.next()){
                    System.out.println(getAccountById(res.getInt(1)));
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("A problem occured with the account creation");
        }
        return false;
    }
    @Override
    public void createAccount(Account account) {
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("select * from account where ID_PERSON = "+daoP.findIdPerson(account.getAccountHolder())+" and label_account = '"+account.getLabel()+"';");
            if(res.next()){
                System.out.println("il existe deja un compte avec ses informations");
            } else {
                dbConnection.getSt().executeUpdate("INSERT INTO account (ID_PERSON, label_account, BALANCE) VALUES (" + daoP.findIdPerson(account.getAccountHolder()) + ", '" + account.getLabel() + "', " + 0 + ")");
                System.out.println("votre compte a ete creer avec succes");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("A problem occured with the account creation");
        }
    }

    @Override
    public Account getAccountById(int accountId) {
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("select * from account where ACCOUNTNUMBER = "+accountId+";");
            if(res.next()){
                return new Account(res.getInt(1),daoP.getPersonById(res.getInt(2)),res.getString(3),res.getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("A problem occured with the account creation");
        }
        return null;
    }

    @Override
    public void updateAccount(Account account) {
        System.out.println(account);
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("select * from account where ID_PERSON = "+daoP.findIdPerson(account.getAccountHolder())+" and label_account = '"+account.getLabel()+"';");
            if(res.next()){
                System.out.println("il existe deja un compte avec ses informations");
            } else {
                dbConnection.getSt().executeUpdate("UPDATE account SET label_account = '"+account.getLabel()+"', ID_PERSON = "+daoP.findIdPerson(account.getAccountHolder())+" WHERE ACCOUNTNUMBER = " + account.getAccountNumber());
                System.out.println("votre modification a ete execute avec succes");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("A problem occured with the account update");
        }
    }

    public boolean checkHolder(Account account, Person person){
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("select * from account where ACCOUNTNUMBER = "+ account.getAccountNumber() +" and ID_PERSON = "+daoP.findIdPerson(person)+";");
            return res.next();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("A problem occured with the account update");
        }
        return false;
    }

    public Account getAccountByLabel(String email, String label){
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("select * from account where ID_PERSON = "+daoP.findIdPersonByLabel(email)+" AND label_account = '"+ label +"';");
            if(res.next()){
                return getAccountById(res.getInt(1));
            } else {
                System.out.println("The account is not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("A problem occured with the finding the account");
        }
        return null;
    }

    @Override
    public void deleteAccount(Account account) {
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            dbConnection.getSt().executeUpdate(" delete from account where ACCOUNTNUMBER = "+account.getAccountNumber());
            System.out.println("your account is deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("A problem occured with the account delete");
        }
    }
}
