package Model;
public class Account{
    private int accountNumber;
    private Person accountHolder;
    private String label;
    private int balance = 0;
    
    public Account(int accountNumber, Person accountHolder, String label, int balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.label = label;
        this.balance = balance;
    }
    public Account(Person accountHolder, String label){
        this.label = label;
        this.accountHolder = accountHolder;
    }
    public Person getAccountHolder() {
        return accountHolder;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setAccountHolder(Person accountHolder) {
        this.accountHolder = accountHolder;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public int getBalance() {
        return balance;
    }
    @Override
    public String toString() {
        return "id = "+ accountNumber +", label = " + label +", balance = " + balance + ", person: " + accountHolder.toString();
    }
    public boolean equals(Account account){
        if (account == null) {
            return false;
        }
        return this.label.equals(account.getLabel()) && this.accountHolder.equals(account.getAccountHolder());
    }
}