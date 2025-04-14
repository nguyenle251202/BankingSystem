package menu;

import infor.declare.BankAccount;
import infor.export_log;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MenuTransfer {

    public static void menuTransfer(Scanner scanner, List<BankAccount> accountList) throws IOException {

        System.out.println("\n--- Transfer ---");

        if (accountList.isEmpty()) {
            System.out.println("No accounts found in the system. Cannot withdraw.");
            return;
        }

        BankAccount accountFrom = findAccByAccountNumberFrom(scanner, accountList);
        BankAccount accountTo = findAccByAccountNumberTo(scanner, accountList);

        if (accountFrom == null || accountTo == null) {
            System.out.println("Account ID not found or invalid ID entered. ");
            return;
        }

        System.out.println("\nAccountFrom or AccountTo Found: ");

        boolean continueTransferActions = true;
        while (continueTransferActions) {
            displayTransferOptions(accountFrom.getAccountNumber(), accountTo.getAccountNumber());
            int choice = selectTransferOpstion(scanner);

            switch (choice) {
                case 1:
                    transferAmount(scanner, accountFrom, accountTo);
                    continueTransferActions = false;
                    System.out.println("Finished transfer actions from " + accountFrom.getAccountNumber() + " to " + accountTo.getAccountNumber());
                    break;
                case 0:
                   System.out.println("Transfer cancelled from " + accountFrom.getAccountNumber() + " to " + accountTo.getAccountNumber());
                   continueTransferActions = false;
                   break;
                default:
                    System.out.println("Invalid transfer option. Please enter a valid option.");
                    break;
            }
        }
    }

    private static void transferAmount(Scanner scanner, BankAccount accountFrom, BankAccount accountTo) throws IOException {
        System.out.println("\nEnter the amount  to transfer from: " + accountFrom.getAccountNumber());
        double amount = -1;

        while (amount <= 0) {
            System.out.print("Enter the amount must be transfer (must be positive): ");
            String input = scanner.nextLine().trim();

            try {
                amount = Double.parseDouble(input);
                if (amount <= 0) {
                    System.out.println("Amount must be transfer");
                    export_log.logTransaction("Amount must be transfer");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                export_log.logTransaction("Invalid input. Please enter a valid number.");
                amount = -1;
            }
        }

        try {
            accountFrom.withdraw(amount);
            accountTo.deposit(amount);
            System.out.printf("Transfer successful %.2f withdrawn from " + accountFrom.getAccountNumber() + " to " + accountTo.getAccountNumber(), amount);
            export_log.logTransaction( "Transfer successful " + amount + " from " + accountFrom.getAccountNumber() + " to " + accountTo.getAccountNumber());
        } catch (IllegalArgumentException e) {
            System.out.println("Transfer failed: " + e.getMessage());
            export_log.logTransaction("Transfer failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during transfer" + e.getMessage());
            export_log.logTransaction("An unexpected error occurred during transfer" + e.getMessage());
        }
    }


    private static int selectTransferOpstion(Scanner scanner) {
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void displayTransferOptions(String accountNumberFrom, String accountNumberTo) {
        System.out.println("\nTransfer Options for accountFrom: " + accountNumberFrom + " to " + accountNumberTo);
        System.out.println("[1] - Enter transfer amount");
        System.out.println("[0] - Cancel transfer");
        System.out.print("Enter your choice: ");
    }

    private static BankAccount findAccByAccountNumberFrom(Scanner scanner, List<BankAccount> accountList) {
        System.out.print("Enter the AccountNumber to withdraw into (e.g., ex2-00x): ");
        String idToFind = scanner.nextLine().trim();
        if (idToFind.isEmpty()) {
            System.out.println("Account ID cannot be empty.");
            return null;
        }
        for (BankAccount accountFrom : accountList) {
            if (accountFrom.getId().equalsIgnoreCase(idToFind)) {
                return accountFrom;
            }
        }
        return null;
    }

    private static BankAccount findAccByAccountNumberTo(Scanner scanner, List<BankAccount> accountList) {
        System.out.print("Enter the AccountNumber to deposit into (e.g., ex2-00x): ");
        String idToFind = scanner.nextLine().trim();
        if (idToFind.isEmpty()) {
            System.out.println("Account ID cannot be empty.");
            return null;
        }

        for (BankAccount accountTo : accountList) {
            if (accountTo.getId().equalsIgnoreCase(idToFind)) {
                return accountTo;
            }
        }
        return null;
    }
}
