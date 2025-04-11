package infor.interest_rate;

import infor.BankAccount;

public class SavingsAccount extends BankAccount {
    public double interestRate = 0.05 ;

    public SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
        super(accountNumber, ownerName, balance);
        this.interestRate = interestRate;
    }
}
