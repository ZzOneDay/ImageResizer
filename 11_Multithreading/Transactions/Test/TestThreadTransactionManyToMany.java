import Errors.ErrorThisAccount;

import java.util.ArrayList;


public class TestThreadTransactionManyToMany extends Thread {
    private ArrayList<Account> accounts;
    private Bank bank;
    private long amount;


    public TestThreadTransactionManyToMany(ArrayList<Account> accounts, Bank bank, long amount) {
        this.accounts = accounts;
        this.bank = bank;
        this.amount = amount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int random1 = (int) (Math.random() *  accounts.size());
            int random2 = (int) (Math.random() *  accounts.size());
            while (random1 == random2) {
                random2 = (int) (Math.random() *  accounts.size());
            }

            Account account1 = accounts.get(random1);
            Account account2 = accounts.get(random2);

            try {
                bank.transfer(account1.getAccNumber(),account2.getAccNumber(),amount);
            } catch (ErrorThisAccount errorThisAccount) {

            }

        }
    }
}