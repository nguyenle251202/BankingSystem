package menu;

import infor.declare.BankAccount;
import infor.export_log;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MenuDeposit {

    public static void menuDeposit(Scanner scanner, List<BankAccount> accountList) throws IOException {
        System.out.println("\n--- Deposit ---");

        // InvalidAmountException
        if (accountList.isEmpty()) {
            System.out.println("No accounts found in the system. Cannot deposit.");
            return;
        }

        BankAccount accountToDeposit = findAccByAccountNumber(scanner, accountList);

        if (accountToDeposit == null) {
            System.out.println("Account ID not found or invalid ID entered.");
            return;
        }

        System.out.println("\nAccount Found: " + accountToDeposit.getAccountNumber() + ". Proceeding with deposit options.");

        boolean continueDepositActions = true;
        while (continueDepositActions) {
            displayDepositOptions(accountToDeposit.getAccountNumber());
            int choice = selectDepositOption(scanner);

            switch (choice) {
                case 1:
                    depositAmount(scanner, accountToDeposit);
                    continueDepositActions = false;
                    System.out.println("Finished deposit actions for Account Number: " + accountToDeposit.getAccountNumber());
                    break;
                case 0:
                    System.out.println("Deposit cancelled for Account Number: " + accountToDeposit.getAccountNumber());
                    continueDepositActions = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 0 or 1.");
                    break;
            }
        }
    }

    private static BankAccount findAccByAccountNumber(Scanner scanner, List<BankAccount> accountList) {
        System.out.print("Enter the Account ID to deposit into (e.g., ex2-00x): ");
        String idToFind = scanner.nextLine().trim();
        if (idToFind.isEmpty()) {
            System.out.println("Account ID cannot be empty.");
            return null;
        }
        for (BankAccount account : accountList) {
            if (account.getId().equalsIgnoreCase(idToFind)) {
                return account;
            }
        }
        return null;
    }

    private static void displayDepositOptions(String accountNumber) {
        System.out.println("\nDeposit Options for Account: " + accountNumber);
        System.out.println("[1] - Enter deposit amount");
        System.out.println("[0] - Cancel Deposit");
        System.out.print("Enter your choice: ");
    }

    private static void depositAmount(Scanner scanner, BankAccount account) throws IOException {
        double amount = -1;
        while (amount <= 0) {
            System.out.print("Enter the amount to deposit (must be positive): ");
            String input = scanner.nextLine().trim(); // Read and trim input

            try {
                amount = Double.parseDouble(input);
                if (amount <= 0) {
                    System.out.println("Deposit amount must be positive. Try again.");
                    export_log.logTransaction("Deposit amount must be positive. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value for the amount.");
                export_log.logTransaction("Invalid input. Please enter a numeric value for the amount.");
                amount = -1;
            }
        }

        try {
            account.deposit(amount);
            System.out.printf("Successfully deposited %.2f. New balance: %.2f%n",
                    amount, account.getBalance());
            export_log.logTransaction("Deposit " + amount + " to account " + account.getAccountNumber());
        } catch (IllegalArgumentException e) {
            System.out.println("Deposit failed: " + e.getMessage());
            export_log.logTransaction("Deposit failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during deposit: " + e.getMessage());
            export_log.logTransaction("An unexpected error occurred during deposit: " + e.getMessage());;
        }
    }

    private static int selectDepositOption(Scanner scanner) {
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}