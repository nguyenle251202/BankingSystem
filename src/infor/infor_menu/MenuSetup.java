package infor.infor_menu;

import java.util.ArrayList;
import java.util.List;

public class MenuSetup {

    public static List<MenuItem> createSampleMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(new MenuItem(null, "Add Account", "01"));
        menuItems.add(new MenuItem(null, "Deposit Account", "02"));
        menuItems.add(new MenuItem(null, "Withdraw Account", "03"));
        menuItems.add(new MenuItem(null, "Check Interest Rate", "04"));
        menuItems.add(new MenuItem(null, "Transfer Money", "05"));
        menuItems.add(new MenuItem(null, "View All Data", "06"));
        menuItems.add(new MenuItem(null, "Exit Application", "00"));
//
//        // Layer02:
//        menuItems.add(new MenuItem(null, "[1] - Enter deposit amount", "02-01"));
//        menuItems.add(new MenuItem(null, "[0] - Cancel Deposit", "00"));
//
//        // Layer03:
//        menuItems.add(new MenuItem(null, "[1] - Enter withdraw amount", "03-01"));
//        menuItems.add(new MenuItem(null, "[0] - Cancel Withdraw", "00"));
//
//        // Layer04:
//        menuItems.add(new MenuItem(null, "[1] - Enter Interest amount", "04-01"));
//        menuItems.add(new MenuItem(null, "[0] - Cancel Interest", "00"));
//
//        // Layer05:
//        menuItems.add(new MenuItem(null, "[1] - Enter transfer amount", "05-01"));
//        menuItems.add(new MenuItem(null, "[0] - Cancel transfer", "00"));

        return menuItems;
    }

    public static void main(String[] args) {
        List<MenuItem> sampleList = createSampleMenuItems();

        MenuManager menuManager = new MenuManager(sampleList);

        System.out.println("Successfully created MenuManager.");
        System.out.println("Items stored in MenuManager:");

        if (menuManager.getMenuItems() != null) {
            for (MenuItem item : menuManager.getMenuItems()) {
                System.out.println(" - " + item);
            }
            System.out.println("\nTotal items: " + menuManager.getMenuItems().size());
        } else {
            System.out.println("MenuManager item list is null.");
        }

    }
}