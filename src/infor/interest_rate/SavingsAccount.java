package infor.interest_rate;

import infor.BankAccount;

public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String id ,String accountNumber, String ownerName, double initialBalance, double interestRate) {
        // Call the UPDATED BankAccount constructor with 3 arguments
        super(id, accountNumber, ownerName, initialBalance);
        this.interestRate = interestRate;
    }
}
