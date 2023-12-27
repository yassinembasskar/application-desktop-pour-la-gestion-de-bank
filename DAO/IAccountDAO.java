package DAO;
import Model.*;
public interface IAccountDAO {
    void createAccount(Account account);
    Account getAccountById(int accountId);
    void updateAccount(Account account);
    void deleteAccount(Account account);
    boolean showAccounts(Person person);
}
