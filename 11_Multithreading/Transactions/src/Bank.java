import Errors.AccountIsBlock;
import Errors.ErrorThisAccount;

import java.util.HashMap;
import java.util.Random;

public class Bank {
    private HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws ErrorThisAccount {
        Account accountFrom = accounts.get(fromAccountNum);
        Account accountTo = accounts.get(toAccountNum);
        if (accountFrom == null || accountTo == null) {
            throw new ErrorThisAccount();
        }

        synchronized (accounts.get(fromAccountNum)) {
            if (!accountFrom.isBlock()) {
                accountFrom.deductMoney(amount);
            } else {
                throw new AccountIsBlock();
            }
        }

        synchronized (accounts.get(toAccountNum)) {
            if (!accountTo.isBlock()) {
                accountTo.addMoney(amount);
            } else {
                accountFrom.addMoney(amount);
                throw new AccountIsBlock();
            }
        }
        //System.out.println("Транзакция прошла успешно");

        if (amount > 50000) {
            System.out.println("Операция вызывает сомнения, счета заблокированы");
            accountFrom.blockAccount();
            accountTo.blockAccount();
            try {
                boolean transactionIsClear = isFraud(fromAccountNum, toAccountNum, amount);
                if (transactionIsClear) {
                    accountFrom.unlockAccount();
                    accountTo.unlockAccount();
                } else {
                    System.out.println("Проверка операции прошла успешно, счета разблокированы");
                }
            } catch (InterruptedException e) {
                System.out.println("Ошибка проверка транзации." +
                        "\nОбратитесь в техническую поддержку для разблокировки счетов");
                e.printStackTrace();
            }

        }

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }
}
