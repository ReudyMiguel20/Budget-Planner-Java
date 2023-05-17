package budget;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private final ItemList itemList;
    private final Scanner scanner;

    public UserInterface(ItemList itemList) {
        this.itemList = itemList;
        this.scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        boolean userWantToExit = false;

        while (!userWantToExit) {
            menuCommands();

            switch (scanner.nextLine()) {
                case "1" -> {
                    this.itemList.setBalance();
                }
                case "2" -> {
                    this.itemList.purchaseItemMenu();
                }
                case "3" -> {
                    this.itemList.showPurchasesMenu();
                }
                case "4" -> {
                    this.itemList.getBalance();
                }
                case "5" -> {
                    this.itemList.exportFile();
                }
                case "6" -> {
                    this.itemList.importFile();
                }
                case "7" -> {
                    this.itemList.sortItemMenu();
                }
                case "0" -> {
                    userWantToExit = true;
                    System.out.println("\nBye!");
                }
            }
        }
    }

    public void menuCommands() {
        System.out.println("""
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                5) Save
                6) Load
                7) Analyze (Sort)
                0) Exit""");
    }

}
