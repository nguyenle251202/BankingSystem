package infor.interest_rate;

import infor.declare.BankAccount;

public class SavingsAccount extends BankAccount {
    public double interestRate = 0.05 ;

    public SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
        super(accountNumber, ownerName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void interest(int month) {

        String ownerName = getOwnerName();

        if (month > 0) {
            setBalance(getBalance() * Math.pow(( 1 + interestRate), month));
            System.out.println("Interested " + month + " months to " + ownerName);
        }
        else {
            System.out.println("Cannot interest " + month + "months to " + ownerName);
        }
    }

}
