package menu;

import infor.BankAccount;

import java.util.List;
import java.util.Scanner;

public class MenuInterest {

    public static void calculateInterest(Scanner scanner, List<BankAccount> accountList) {
        System.out.print("\n--- Menu Interest ---");

        if (accountList.isEmpty()) {
            System.out.print("\nNo Accounts Found");
            return;
        }

        BankAccount accToCalInterest = findAccCalInterest (scanner, accountList);
        if (accToCalInterest == null) {
            System.out.print("\nNo Account Found");
            return;
        }
        boolean continueCalInterest = true;
        while (continueCalInterest) {
            displayInterestOptions(accToCalInterest.getAccountNumber());
            int choice = selectInterestOptions(scanner);
            switch (choice) {
                case 1:
                    interestAmount(scanner, accToCalInterest);
                    continueCalInterest = false;
                    System.out.print("\nFinished Calcula Interest for Account: " + accToCalInterest.getAccountNumber());
                    break;
                case 0:
                    System.out.print("\nEnter the Account ID to deposit into: ");
                    continueCalInterest = false;
                    break;
                default:
                    System.out.print("\nInvalid Option");
                    break;
            }
        }
    }

    private static BankAccount findAccCalInterest(Scanner scanner, List<BankAccount> accountList) {
        System.out.print("Enter the Account ID to Calcula Interest into (e.g., ex2-00x): ");
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

    private static int selectInterestOptions(Scanner scanner) {
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void displayInterestOptions(String accountNumber) {
        System.out.println("\n Interest Options for Account: " + accountNumber);
        System.out.println("[1] - Enter Interest amount");
        System.out.println("[0] - Cancel Interest");
        System.out.print("Enter your choice: ");
    }

    private static void interestAmount(Scanner scanner, BankAccount account) {
        int month = -1;
        double amount = -1;
        while (month <= 0) {
            System.out.print("Enter the month to Interest: ");
            String input = scanner.nextLine().trim(); // Read and trim input

            try {
                month = Integer.parseInt(input);
                if (month < 1) {
                    System.out.println("Invalid Month");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Month");
                month = -1;
            }
        }

        try {
            account.interest(month);
            System.out.println("CalInterest amount: " + amount);
        } catch (IllegalArgumentException e) {
            System.out.println("Interest amount cannot be calculated" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Interest amount cannot be calculated" + e.getMessage());
        }
    }
}