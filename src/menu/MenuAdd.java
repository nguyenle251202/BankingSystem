package menu;

import infor.declare.BankAccount;
import infor.export_log;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuAdd {
    public static void addAccount (Scanner scanner, List<BankAccount> accountList) throws IOException {
        System.out.print("\n---Create account---");

        String accountNumber = "";
        String ownerName = "";
        double initialBalance = 0;

        while (accountNumber.isEmpty()) {
            System.out.print("\nEnter account number: ");
            accountNumber = scanner.nextLine();
            if (accountNumber.isEmpty()) {
                System.out.println("Account number cannot be empty.");
            }
        }

        while (ownerName.isEmpty()) {
            System.out.print("\nEnter owner name: ");
            ownerName = scanner.nextLine();
            if (ownerName.isEmpty()) {
                System.out.println("Owner_Name cannot be empty.");
            }
        }

        // try - catch - finally
        while (initialBalance <= 10) {
            System.out.print("\nEnter balance: ");
            try {
                initialBalance = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number for age.");
                export_log.logTransaction("Invalid input. Please enter a whole number for age.");
                scanner.next();
                initialBalance = 0;
            }
            finally {
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        }
        BankAccount newAccount = new BankAccount(null, accountNumber, ownerName, initialBalance);
        accountList.add(newAccount);
        System.out.println("Account created successfully!");
        export_log.logTransaction("Create account " + accountNumber + "-" + ownerName + " successfully!");
    }
}
