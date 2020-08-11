import Errors.ErrorThisAccount;


public class TestThreadTransactionOneToOne extends Thread {
    private Account accountFROM;
    private Account accountTO;
    private Bank bank;

    TestThreadTransactionOneToOne(Account accountFROM, Account accountTO, Bank bank) {
        this.accountFROM = accountFROM;
        this.accountTO = accountTO;
        this.bank = bank;
    }

    @Override
    public void run() {
        try {
            bank.transfer(accountFROM.getAccNumber(), accountTO.getAccNumber(), 100);
        } catch (ErrorThisAccount errorThisAccount) {
            errorThisAccount.printStackTrace();
        }
    }
}
