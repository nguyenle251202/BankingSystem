package infor.overdraft;

import infor.BankAccount;

public class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String id ,String accountNumber, String ownerName, double balance, double overdraftLimit) {
        super(id, accountNumber, ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

