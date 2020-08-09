import Errors.NotEnoughMoney;

public class Account
{
    private volatile long money;
    private final String ACCNUMBER;

    private volatile boolean isBlock = false;

    public Account(long money, String ACCNUMBER) {
        this.money = money;
        this.ACCNUMBER = ACCNUMBER;
    }

    public Account(long money, String accNumber, boolean isBlock) {
        this.money = money;
        this.ACCNUMBER = accNumber;
        this.isBlock = isBlock;
    }

    public String getAccNumber() {
        return ACCNUMBER;
    }

    void blockAccount () {
        isBlock = true;
    }

    void unlockAccount() {
        isBlock = false;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public long getMoney() {
        synchronized (this) {
            return money;
        }
    }

    public synchronized void addMoney (Long amount) {
        money = money + amount;
    }

    public synchronized void deductMoney (Long amount) throws NotEnoughMoney {
        if (money >= amount) {
            money = money - amount;
        }
        else {
            throw new NotEnoughMoney();
        }
    }



}
