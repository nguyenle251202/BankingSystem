package menu;

import infor.BankAccount;
import infor.View;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private static List<BankAccount> accountList = View.getInitialAccountData();
    private static BankAccount selectedAccount;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = selectChoice(scanner);

            if (choice == 0) {
                System.out.println("Thoat app.");
                scanner.close();
                break;
            }

            processChoice(choice, scanner);
        }
        System.out.println("Application finished.");
    }

    public static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("[1] - Add Account");
        System.out.println("[2] - Deposit account");
        System.out.println("[3] - Withdraw account");
        System.out.println("[4] - Check Interest Rate");
        System.out.println("[5] - Check amount");
        System.out.println("[0] - Exit Application");
        System.out.print("Chon chuc nang: ");
    }

    private static int selectChoice(Scanner scanner) {
        int choice = -1;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 0 && choice <= 5) {
                    return choice;
                } else {
                    System.out.println("Lua chon khong hop le. Vui long chon tu 0 den 6.");
                    System.out.print("Chon chuc nang: ");
                }
            } else {
                System.out.println("Nhap sai dinh dang. Vui long nhap mot so.");
                scanner.next();
                System.out.print("Chon chuc nang: ");
            }
        }
    }

    private static void processChoice(int choice, Scanner scanner) {
        switch (choice) {

            //Complete Add
            case 1:
                System.out.println("\n--- Add Account ---");
                MenuAdd.addAccount(scanner, accountList);
                break;

            // Complete Deposit
            case 2:
                System.out.println("\n--- Deposit Account ---");
                MenuDeposit.menuDeposit(scanner, accountList);
                break;

            // Complete Withdraw
            case 3:
                System.out.println("\n--- Withdraw Account ---");
                MenuWithdraw.menuWithdraw(scanner, accountList);
                break;

            // Complete Interest
            case 4:
                System.out.println("\n--- Check Interest Rate ---");
                MenuInterest.calculateInterest(scanner, accountList);
                break;

            case 5:
                System.out.println("\n--- Transfer money ---");
                break;

            // Complete View
            case 6:
                System.out.println("\n--- View all data ---");
                View.displayAccountList(accountList);
                break;
            case 0:
                break;
            default:
                System.out.println("Lua chon khong hop le.");
                break;
        }
    }

    public static BankAccount getSelectedAccount() {
        return selectedAccount;
    }

    public static void setSelectedAccount(BankAccount selectedAccount) {
        MainMenu.selectedAccount = selectedAccount;
    }
}