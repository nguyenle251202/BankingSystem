package infor.overdraft;

import infor.BankAccount;

public class CheckingAccount extends BankAccount {
    public double overdraftLimit = 2000;

    public CheckingAccount() {
        super();
    }

    public CheckingAccount(String accountNumber, String ownerName, double balance , double overdraftLimit) {
        super(accountNumber, ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String toString() {
        return super.toString() + ", Type: Checking, Overdraft Limit: " + overdraftLimit;
    }
}
