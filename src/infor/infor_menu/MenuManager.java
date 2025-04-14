package infor.infor_menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuManager {

    private List<MenuItem> menuItems;
    private int pageSize = 5;
    private int currentPage = 1;
    private int totalPages;

    public MenuManager(List<MenuItem> menuItems) {
        this.menuItems = menuItems.stream()
                .filter(item -> item.getAddressLayer() != null && !item.getAddressLayer().contains("-"))
                .collect(Collectors.toList());

        updateTotalPages();
    }

    private void updateTotalPages() {
        if (this.menuItems == null || this.menuItems.isEmpty()) {
            this.totalPages = 0;
            this.currentPage = 0;
        } else {
            this.totalPages = (int) Math.ceil((double) this.menuItems.size() / pageSize);
            if (this.currentPage > this.totalPages) {
                this.currentPage = this.totalPages;
            }
            if (this.currentPage < 1 && this.totalPages > 0) {
                this.currentPage = 1;
            }
            if (this.totalPages == 0) {
                this.currentPage = 0;
            }
        }
    }

    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(menuItems);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems.stream()
                .filter(item -> item.getAddressLayer() != null && !item.getAddressLayer().contains("-"))
                .collect(Collectors.toList());
        updateTotalPages();
        this.currentPage = (this.totalPages > 0) ? 1 : 0;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
            updateTotalPages();
            if (this.currentPage > this.totalPages) {
                this.currentPage = this.totalPages;
            }
            if (this.currentPage < 1 && this.totalPages > 0) {
                this.currentPage = 1;
            }
        }
    }

    public void displayCurrentPage() {
        if (totalPages == 0) {
            System.out.println("No menu items to display.");
            return;
        }

        System.out.println("\n--- Main Menu (Page " + currentPage + "/" + totalPages + ") ---");

        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, menuItems.size());

        for (int i = startIndex; i < endIndex; i++) {
            MenuItem item = menuItems.get(i);
            int displayIndex = i - startIndex + 1;
            System.out.println("[" + displayIndex + "] - " + item.getFunctionName());
        }

        System.out.println("---------------------------------------");
        String navOptions = "";
        if (currentPage > 1) {
            navOptions += "[p] Previous ";
        }
        if (currentPage < totalPages) {
            if (!navOptions.isEmpty()) navOptions += "| ";
            navOptions += "[n] Next ";
        }

        if (!navOptions.isEmpty()) {
            System.out.println(navOptions);
        }
        System.out.print("Enter choice (1-" + (endIndex - startIndex) + "), 'p', or 'n': ");
    }

    public boolean nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            return true;
        }
        return false;
    }

    public boolean previousPage() {
        if (currentPage > 1) {
            currentPage--;
            return true;
        }
        return false;
    }

    public MenuItem getItemByPageChoice(int pageChoice) {
        int actualIndex = (currentPage - 1) * pageSize + pageChoice - 1;
        int itemsOnCurrentPage = getItemsOnCurrentPage();

        if (pageChoice > 0 && pageChoice <= itemsOnCurrentPage && actualIndex >= 0 && actualIndex < menuItems.size()) {
            return menuItems.get(actualIndex);
        }
        return null;
    }

    public int getItemsOnCurrentPage() {
        if (totalPages == 0) return 0;
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, menuItems.size());
        return endIndex - startIndex;
    }
}