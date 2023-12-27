package DAO;
import java.util.ArrayList;
import java.util.List;
import Model.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class BankTransactionDAO implements IBankTransacionDAO {
    DBConnection dbConnection = new DBConnection();
    PersonDAO daoP = new PersonDAO(); 
    AccountDAO daoA= new AccountDAO();  
    @Override
    public void createBankTransaction(Model.BankTransaction transaction) {
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            if(transaction.getType()==TransactionType.DEPOT){
                dbConnection.getSt().executeUpdate("INSERT INTO banktransaction (DATE, TYPE, AMOUNT, SOURCEACCOUNTNUMBER) VALUES ('" + new SimpleDateFormat("YYYY-MM-dd").format(transaction.getDate()) + "', 'DEPOT', " + transaction.getAmount() + " , "+ transaction.getSourceAccountNumber()+")");
                dbConnection.getSt().executeUpdate("UPDATE account SET BALANCE = "+(transaction.getAmount() + daoA.getAccountById(transaction.getSourceAccountNumber()).getBalance())+" WHERE ACCOUNTNUMBER = "+transaction.getSourceAccountNumber());
            } else if (transaction.getType()==TransactionType.RETRAIT){
                if(daoA.getAccountById(transaction.getSourceAccountNumber()).getBalance()>=transaction.getAmount()){
                    dbConnection.getSt().executeUpdate("INSERT INTO banktransaction (DATE, TYPE, AMOUNT, SOURCEACCOUNTNUMBER) VALUES ('" + new SimpleDateFormat("YYYY-MM-dd").format(transaction.getDate()) + "', 'RETRAIT', " + transaction.getAmount() + " , "+ transaction.getSourceAccountNumber()+")");
                    dbConnection.getSt().executeUpdate("UPDATE account SET BALANCE = "+ (daoA.getAccountById(transaction.getSourceAccountNumber()).getBalance()-transaction.getAmount() )+" WHERE ACCOUNTNUMBER = "+transaction.getSourceAccountNumber());
                }
            } else if (transaction.getType()==TransactionType.TRANSFERT){
                if (daoA.getAccountById(transaction.getSourceAccountNumber()).getBalance()>=transaction.getAmount()) {
                    dbConnection.getSt().executeUpdate("INSERT INTO banktransaction (DATE, TYPE, AMOUNT, SOURCEACCOUNTNUMBER, TARGETACCOUNTNUMBER) VALUES ('" + new SimpleDateFormat("YYYY-MM-dd").format(transaction.getDate()) + "', 'TRANSFERT', " + transaction.getAmount() + " , "+ transaction.getSourceAccountNumber()+","+transaction.getTargetAccountNumber()+")");
                    dbConnection.getSt().executeUpdate("UPDATE account SET BALANCE = "+(transaction.getAmount() + daoA.getAccountById(transaction.getTargetAccountNumber()).getBalance())+" WHERE ACCOUNTNUMBER = "+transaction.getTargetAccountNumber());
                    dbConnection.getSt().executeUpdate("UPDATE account SET BALANCE = "+ (daoA.getAccountById(transaction.getSourceAccountNumber()).getBalance()-transaction.getAmount() )+" WHERE ACCOUNTNUMBER = "+transaction.getSourceAccountNumber());
                }
            }
            System.out.println("la transaction a ete effectue avec succes");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("you have an error in transaction");
        }   
    }
    public BankTransaction getTransactionById(int id){
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("select * from banktransaction where ID_TRANSACTION = "+id+";");
            if(res.next()){
                TransactionType transactionType;
                if (res.getString(5).equals("DEPOT")) {
                    transactionType = TransactionType.DEPOT;
                } else if (res.getString(5).equals("RETRAIT")) {
                    transactionType = TransactionType.RETRAIT;
                } else if (res.getString(5).equals("TRANSFERT")) {
                    transactionType = TransactionType.TRANSFERT;
                }else{
                    return null;
                }
                return new BankTransaction(res.getInt(6),res.getDate(4),transactionType,res.getInt(3), res.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("A problem occured with the account creation");
        }
        return null;
    }
    @Override
    public List<String> displayTransactions(Account account){
        List<String> list = new ArrayList<>();
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("select * from banktransaction where SOURCEACCOUNTNUMBER = "+account.getAccountNumber()+" or TARGETACCOUNTNUMBER = "+ account.getAccountNumber() +";");
            if (!res.isBeforeFirst()) {
                System.out.println("you have 0 transactions;");
            } else {
                while(res.next()){
                    list.add(getTransactionById(res.getInt(1)).toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("A problem occured with the account creation");
        }
        return list;
    }
}
