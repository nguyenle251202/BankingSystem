package infor.infor_menu;

import java.util.concurrent.atomic.AtomicInteger;

public class MenuItem {

    private static final AtomicInteger idCounter = new AtomicInteger(1);

    private String idMainMenu;
    private String FunctionName;
    private String AddressLayer;

    // Constructor
    public MenuItem(String idMainMenu, String functionName, String addressLayer) {
        this.idMainMenu = String.format("ID01_%03d", idCounter.getAndIncrement());
        FunctionName = functionName;
        AddressLayer = addressLayer;
    }

    // Getters
    public String getIdMainMenu() {
        return idMainMenu;
    }

    public String getFunctionName() {
        return FunctionName;
    }
    public String getAddressLayer() {
        return AddressLayer;
    }

    // Setters
    public void setFunctionName(String functionName) {
        FunctionName = functionName;
    }
    public void setAddressLayer(String addressLayer) {
        AddressLayer = addressLayer;
    }

    // ToString
    public String toString() {
        return "MainMenuList{" +
                "idMainMenu=" + idMainMenu +
                ", FunctionName='" + FunctionName + '\'' +
                ", AddressLayer='" + AddressLayer + '\'' +
                '}';
    }
}
