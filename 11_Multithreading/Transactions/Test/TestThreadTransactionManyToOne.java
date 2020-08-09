import Errors.ErrorThisAccount;

import java.util.ArrayList;

public class TestThreadTransactionManyToOne extends Thread {
    private Account accountTO;
    private ArrayList<Account> accountsFROM;
    private Bank bank;

    TestThreadTransactionManyToOne(Account accountTO, ArrayList<Account> accountsFROM, Bank bank) {
        this.accountTO = accountTO;
        this.accountsFROM = accountsFROM;
        this.bank = bank;
    }

    @Override
    public void run() {
        for (Account accountFROM : accountsFROM) {
            try {
                bank.transfer(accountFROM.getAccNumber(), accountTO.getAccNumber(), 1);
            } catch (ErrorThisAccount e) {
                e.printStackTrace();
            }
        }
    }
}
