import Errors.AccountIsBlock;
import Errors.ErrorThisAccount;
import Errors.NotEnoughMoney;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class BankTest {
    @Test
    public void testTransferTestManyToOne() {
        Bank bank = new Bank();
        HashMap<String, Account> accounts = bank.getAccounts();

        Account accountTO = new Account(0, "TO");
        accounts.put(accountTO.getAccNumber(), accountTO);

        ArrayList<Account> accountsFROM = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Account account = new Account(100, "FROM" + i);
            accountsFROM.add(account);
            accounts.put(account.getAccNumber(), account);
        }

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestThreadTransactionManyToOne testThreadTransaction = new TestThreadTransactionManyToOne(accountTO, accountsFROM, bank);
            threads.add(testThreadTransaction);
        }
        threads.forEach(Thread::start);

        for (Thread testThreadTransaction : threads) {
            try {
                testThreadTransaction.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assert.assertEquals(accountTO.getMoney(), 10000);
    }

    @Test
    public void testTransferTakeOneToMany() {
        Bank bank = new Bank();
        HashMap<String, Account> accounts = bank.getAccounts();

        Account accountFROM = new Account(10001, "FROM");
        accounts.put(accountFROM.getAccNumber(), accountFROM);

        ArrayList<Account> accountsTO = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Account account = new Account(0, "TO" + i);
            accountsTO.add(account);
            accounts.put(account.getAccNumber(), account);
        }

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestThreadTransactionOneToMany testThreadTransaction = new TestThreadTransactionOneToMany(accountFROM, accountsTO, bank);
            threads.add(testThreadTransaction);
        }
        threads.forEach(Thread::start);

        for (Thread testThreadTransaction : threads) {
            try {
                testThreadTransaction.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assert.assertEquals(accountFROM.getMoney(), 1);
    }


    @Test(expected = NotEnoughMoney.class)
    public void testTransferCatchErrorNotEnoughMoney() throws ErrorThisAccount {
        Bank bank = new Bank();
        HashMap<String, Account> accounts = bank.getAccounts();

        Account accountFROM = new Account(100, "FROM");
        accounts.put(accountFROM.getAccNumber(), accountFROM);

        Account accountTO1 = new Account(100, "TO1");
        accounts.put(accountTO1.getAccNumber(), accountTO1);

        Account accountTO2 = new Account(100, "TO2");
        accounts.put(accountTO2.getAccNumber(), accountTO2);

        bank.transfer(accountFROM.getAccNumber(), accountTO1.getAccNumber(), 50);
        bank.transfer(accountFROM.getAccNumber(), accountTO2.getAccNumber(), 51);
    }

    @Test(expected = AccountIsBlock.class)
    public void testTransferCatchErrorAccountIsBlockAccountFROM() throws ErrorThisAccount {
        Bank bank = new Bank();
        HashMap<String, Account> accounts = bank.getAccounts();

        Account accountFROM = new Account(100, "FROM");
        accounts.put(accountFROM.getAccNumber(), accountFROM);

        accountFROM.blockAccount();

        Account accountTO1 = new Account(100, "TO1");
        accounts.put(accountTO1.getAccNumber(), accountTO1);

        Account accountTO2 = new Account(100, "TO2");
        accounts.put(accountTO2.getAccNumber(), accountTO2);

        bank.transfer(accountFROM.getAccNumber(), accountTO1.getAccNumber(), 50);
        bank.transfer(accountFROM.getAccNumber(), accountTO2.getAccNumber(), 51);
    }

    @Test(expected = AccountIsBlock.class)
    public void testTransferCatchErrorAccountIsBlockAccountTO() throws ErrorThisAccount {
        Bank bank = new Bank();
        HashMap<String, Account> accounts = bank.getAccounts();

        Account accountFROM = new Account(100, "FROM");
        accounts.put(accountFROM.getAccNumber(), accountFROM);

        Account accountTO1 = new Account(100, "TO1");
        accounts.put(accountTO1.getAccNumber(), accountTO1);

        accountTO1.blockAccount();

        Account accountTO2 = new Account(100, "TO2");
        accounts.put(accountTO2.getAccNumber(), accountTO2);

        bank.transfer(accountFROM.getAccNumber(), accountTO1.getAccNumber(), 50);
        bank.transfer(accountFROM.getAccNumber(), accountTO2.getAccNumber(), 51);
    }

    @Test
    public void testTransferMoneyNotLost() {
        Bank bank = new Bank();
        HashMap<String, Account> accounts = bank.getAccounts();

        int startMoney = 1000000;

        Account accountFROM = new Account(startMoney, "FROM");
        accounts.put(accountFROM.getAccNumber(), accountFROM);

        Account accountTO1 = new Account(0, "TO1");
        accounts.put(accountTO1.getAccNumber(), accountTO1);


        Account accountTO2 = new Account(0, "TO2");
        accounts.put(accountTO2.getAccNumber(), accountTO2);

        for (int i = 0; i < 10; i++) {
            try {
                bank.transfer(accountFROM.getAccNumber(), accountTO1.getAccNumber(), 50001);
                bank.transfer(accountFROM.getAccNumber(), accountTO2.getAccNumber(), 50001);
            } catch (ErrorThisAccount errorThisAccount) {
                Assert.assertEquals(accountFROM.getMoney() + accountTO1.getMoney() + accountTO2.getMoney(), startMoney);
            }
        }
    }

    @Test
    public void superTestTransferForTask() {
        Bank bank = new Bank();
        HashMap<String, Account> accountsMap = bank.getAccounts();
        ArrayList<Account> accountsList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Account account = new Account(10000, "ACCOUNT" + i);
            accountsList.add(account);
            accountsMap.put(account.getAccNumber(), account);
        }

        int sum1 = 0;
        for (Account account : accountsList) {
            sum1 += account.getMoney();
        }


        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new TestThreadTransactionManyToMany(accountsList, bank, 1));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int sum2 = 0;
        for (Account account : accountsList) {
            sum2 += account.getMoney();
        }

        Assert.assertEquals(sum1, sum2);
    }

    @Test()
    public void superTest2TransferForTask() {
        //Слишком медленный тест
        Bank bank = new Bank();
        HashMap<String, Account> accountsMap = bank.getAccounts();
        ArrayList<Account> accountsList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Account account = new Account(1000000, "ACCOUNT" + i);
            accountsList.add(account);
            accountsMap.put(account.getAccNumber(), account);
        }

        int sum1 = 0;
        for (Account account : accountsList) {
            sum1 += account.getMoney();
        }


        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new TestThreadTransactionManyToMany(accountsList, bank, 50001));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int sum2 = 0;
        for (Account account : accountsList) {
            sum2 += account.getMoney();
        }

        Assert.assertEquals(sum1, sum2);
    }
}
