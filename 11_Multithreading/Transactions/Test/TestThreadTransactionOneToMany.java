import Errors.ErrorThisAccount;

import java.util.ArrayList;

public class TestThreadTransactionOneToMany extends Thread {
    private Account accountFROM;
    private ArrayList<Account> accountsTO;
    private Bank bank;

    TestThreadTransactionOneToMany(Account accountFROM, ArrayList<Account> accountsTO, Bank bank) {
        this.accountFROM = accountFROM;
        this.accountsTO = accountsTO;
        this.bank = bank;
    }

    @Override
    public void run() {
        for (Account accountTo : accountsTO) {
            try {
                bank.transfer(accountFROM.getAccNumber(), accountTo.getAccNumber(), 1);
            } catch (ErrorThisAccount errorThisAccount) {
                errorThisAccount.printStackTrace();
            }
        }
    }
}
