package infor;

import java.util.ArrayList;
import java.util.List;

public class View {
    public static List<BankAccount> getInitialAccountData() {
        List<BankAccount> initialList = new ArrayList<>();

        initialList.add(new BankAccount(null, "ex2-001", "Nguyen Van A", 1000000));
        initialList.add(new BankAccount(null, "ex2-002", "Tran Van B", 100));
        initialList.add(new BankAccount(null, "ex2-003", "Le Thi C", 500));
        return initialList;
    }

    public static void displayAccountList(List<BankAccount> accountList) {

        if (accountList == null || accountList.isEmpty()) {
            System.out.println("No account data available.");
            return;
        }


        int idWidth = 8;
        int accountNumberWidth = 15;
        int ownerNameWidth = 25;
        int balanceWidth = 15;


        String headerFormat = "| %-" + idWidth + "s | %-" + accountNumberWidth + "s | %-" + ownerNameWidth + "s | %" + balanceWidth + "s |%n";

        String rowFormat = "| %-" + idWidth + "s | %-" + accountNumberWidth + "s | %-" + ownerNameWidth + "s | %" + balanceWidth + ".2f |%n";
        String separator = "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(accountNumberWidth + 2) + "+" + "-".repeat(ownerNameWidth + 2) + "+" + "-".repeat(balanceWidth + 2) + "+%n";


        System.out.format(separator);
        System.out.format(headerFormat, "ID", "Account Number", "Owner Name", "Balance");
        System.out.format(separator);


        for (BankAccount account : accountList) {

            String idDisplay = (account.getId() == null) ? "N/A" : account.getId().toString();

            System.out.format(rowFormat,
                    idDisplay,
                    account.getAccountNumber(),
                    account.getOwnerName(),
                    account.getBalance());
        }

        System.out.format(separator);
    }

}