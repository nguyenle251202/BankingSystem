package menu;

import infor.declare.BankAccount;
import infor.ViewAccount;
import infor.infor_menu.MenuItem;
import infor.infor_menu.MenuManager;
import infor.infor_menu.MenuSetup;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private static List<BankAccount> accountList = ViewAccount.getInitialAccountData();

    private static MenuManager menuManager = new MenuManager(MenuSetup.createSampleMenuItems());

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menuManager.displayCurrentPage();

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("n")) {
                if (!menuManager.nextPage()) {
                    System.out.println("Already on the last page.");
                }
                continue;
            }

            if (input.equals("p")) {
                if (!menuManager.previousPage()) {
                    System.out.println("Already on the first page.");
                }
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                MenuItem selectedItem = menuManager.getItemByPageChoice(choice);

                if (selectedItem != null) {
                    String actionKey = selectedItem.getAddressLayer();

                    if ("00".equals(actionKey)) {
                        System.out.println("Exiting application.");
                        break;
                    }

                    processChoice(actionKey, scanner);

                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine();

                } else {
                    System.out.println("Invalid choice number for this page. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number, 'n', or 'p'.");
            } catch (IOException e) {
                System.err.println("An error occurred during the operation: " + e.getMessage());
                System.out.println("\nPress Enter to return to the main menu...");
                scanner.nextLine();
            }
        }

        scanner.close();
        System.out.println("Application finished.");
    }

    private static void processChoice(String actionKey, Scanner scanner) throws IOException {
        switch (actionKey) {
            case "01":
                System.out.println("\n--- Add Account ---");
                MenuAdd.addAccount(scanner, accountList);
                break;
            case "02":
                System.out.println("\n--- Deposit Account ---");
                MenuDeposit.menuDeposit(scanner, accountList);
                break;
            case "03":
                System.out.println("\n--- Withdraw Account ---");
                MenuWithdraw.menuWithdraw(scanner, accountList);
                break;
            case "04":
                System.out.println("\n--- Check Interest Rate ---");
                MenuInterest.calculateInterest(scanner, accountList);
                break;
            case "05":
                System.out.println("\n--- Transfer money ---");
                MenuTransfer.menuTransfer(scanner, accountList);
                break;
            case "06":
                System.out.println("\n--- View all data ---");
                ViewAccount.displayAccountList(accountList);
                break;
            default:
                System.out.println("Unknown menu action key: " + actionKey);
                break;
        }
    }
}