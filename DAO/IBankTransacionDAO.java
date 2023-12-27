package DAO;
import java.util.List;

import Model.*;

public interface IBankTransacionDAO {
    void createBankTransaction(BankTransaction transaction);
    List<String> displayTransactions(Account account);

}
