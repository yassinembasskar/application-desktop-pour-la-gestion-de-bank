package Model;
import java.util.Date;
public class BankTransaction {
    private int amount;
    private Date date;
    private TransactionType type;
    private int sourceAccountNumber;
    private int targetAccountNumber;
    public BankTransaction(int amount, Date date, TransactionType type,int sourceAccountNumber) {
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.sourceAccountNumber = sourceAccountNumber;
    }
    public BankTransaction(int amount, Date date, TransactionType type, int sourceAccountNumber, int targetAccountNumber) {
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setSourceAccountNumber(int sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }
    public void setTargetAccountNumber(int targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }
    
    public double getAmount() {
        return amount;
    }
    public Date getDate() {
        return date;
    }
    public TransactionType getType() {
        return type;
    }
    public int getSourceAccountNumber() {
        return sourceAccountNumber;
    }
    public int getTargetAccountNumber() {
        return targetAccountNumber;
    }
    @Override
    public String toString() {
        return "Date: "+ date + ", Transaction type: " + type + ", Amount: " + amount;
    }
}
