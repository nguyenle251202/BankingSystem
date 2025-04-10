package infor;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {

    private static final AtomicInteger idCounter = new AtomicInteger(1);

    private String id;
    private String accountNumber;
    private String ownerName;
    protected double balance;

    public BankAccount(String id ,String accountNumber, String ownerName, double initialBalance) {
        this.id = String.format("EX%03d", idCounter.getAndIncrement());
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    public BankAccount() {

    }

    public BankAccount(String accountNumber, String ownerName, double balance) {
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    //Setters
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //ToStrings
    @Override
    public String toString() {
        return "BankAccount{" +
                "id='" + id + '\'' +
                "accountNumber='" + accountNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                '}';
    }

    //deposit
    public void deposit (double amount){
        if (amount >= 0) {
            balance += amount;
            System.out.println("Deposited " + amount + " to " + ownerName);
        } else {
            System.out.println("Cannot deposit " + amount + " to " + ownerName + " because the amount is negative");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= getBalance() + 2000) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawn " + amount + " successfully.");
        } else {
            System.out.println("Amount exceeds overdraft limit or invalid. Overdraft limit: " + 2000 );
        }
    }

}
