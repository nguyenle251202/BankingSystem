package menu;

import infor.BankAccount;

import java.util.List;
import java.util.Scanner;

public class MenuWithdraw {

    public static void menuWithdraw(Scanner scanner, List<BankAccount> accountList) {
        System.out.print("\n--- Withdraw ---");

        if (accountList.isEmpty()) {
            System.out.println("No accounts found in the system. Cannot withdraw.");
            return;
        }

        BankAccount accountToWithdraw = findAccByAccountNumber(scanner, accountList);

        if (accountToWithdraw == null) {
            System.out.println("Account ID not found or invalid ID entered. ");
            return;
        }

        System.out.println("\nAccount Found: " + accountToWithdraw.getAccountNumber() + ". Proceeding with withdraw options");

        boolean continueWithdrawActions = true;
        while (continueWithdrawActions) {
            displayWithdrawOptions(accountToWithdraw.getAccountNumber());
            int choice = selectWithdrawOption(scanner);

            switch (choice) {
                case 1:
                    withdrawAmount(scanner, accountToWithdraw);
                    continueWithdrawActions = false;
                    System.out.println("Finished withdraw actions for Account Number: " + accountToWithdraw.getAccountNumber());
                    break;
                case 0:
                    System.out.println("Withdraw cancelled for Account Number: " + accountToWithdraw.getAccountNumber());
                    continueWithdrawActions = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 0 or 1.");
                    break;
            }
        }
    }

    private static BankAccount findAccByAccountNumber(Scanner scanner, List<BankAccount> accountList) {
        System.out.print("Enter the Account ID to withdraw from (e.g., EX00x): ");
        String idToFind = scanner.nextLine().trim();
        if (idToFind.isEmpty()) {
            System.out.println("Account ID cannot be empty.");
            return null;
        }
        for (BankAccount account : accountList) {
            if ( account.getId().equalsIgnoreCase(idToFind)) {
                return account;
            }
        }
        return null;
    }

    private static void displayWithdrawOptions(String accountNumber) {
        System.out.println("\nWithdraw Options for Account: " + accountNumber);
        System.out.println("[1] - Enter withdraw amount");
        System.out.println("[0] - Cancel Withdraw");
        System.out.print("Enter your choice: ");
    }

    private static void withdrawAmount(Scanner scanner, BankAccount account) {
        System.out.print("Enter the Amount to withdraw from (e.g., EX00x): ");
        double amount = -1;
        while (amount <= 0) {
            System.out.print("Enter the amount must be withdraw (must be positive): ");
            String input = scanner.nextLine().trim();

            try {
                amount = Double.parseDouble(input);
                if (amount <= 0) {
                    System.out.print("Withdraw amount must be withdraw. Try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value for the amount.");
                amount = -1;
            }
        }

        try {
            account.withdraw(amount);
            System.out.printf("Successfully withdraw %.2f. New balance: %.2f%n", amount, account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Withdraw failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during withdraw: " + e.getMessage());
        }
    }

    private static int selectWithdrawOption(Scanner scanner) {
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
