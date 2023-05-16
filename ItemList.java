package budget;

import java.util.ArrayList;
import java.util.Scanner;

public class ItemList {
    private ArrayList<PurchasedItems> items;
    private double balance;
    private Scanner scanner;

    public ItemList() {
        this.items = new ArrayList<>();
        this.balance = 0;
        this.scanner = new Scanner(System.in);
    }

    public void addItem(PurchasedItems item) {
        if (this.balance <= 0) {
            return;
        } else {
            this.items.add(item);
            System.out.println("Purchase was added!");
        }
    }

    public void purchaseItem() {
        System.out.println("\nEnter purchase name:");
        String item = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = Double.parseDouble(scanner.nextLine());

        this.balance -= price;
        addItem(new PurchasedItems(item, price));
    }

    public void purchaseFoodItem() {
        System.out.println("\nEnter purchase name:");
        String item = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = Double.parseDouble(scanner.nextLine());

        this.balance -= price;
        addItem(new PurchasedItems(item, price, purchaseType.FOOD));
    }

    public void purchaseClothesItem() {
        System.out.println("\nEnter purchase name:");
        String item = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = Double.parseDouble(scanner.nextLine());

        this.balance -= price;
        addItem(new PurchasedItems(item, price, purchaseType.CLOTHES));
    }

    public void purchaseEntertainmentItem() {
        System.out.println("\nEnter purchase name:");
        String item = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = Double.parseDouble(scanner.nextLine());

        this.balance -= price;
        addItem(new PurchasedItems(item, price, purchaseType.ENTERTAINMENT));
    }

    public void purchaseOtherItem() {
        System.out.println("\nEnter purchase name:");
        String item = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = Double.parseDouble(scanner.nextLine());

        this.balance -= price;
        addItem(new PurchasedItems(item, price, purchaseType.OTHER));
    }

    public void purchaseItemMenu() {
        boolean exitLoop = false;

        //While loop inside a switch to determine with type of item is going to be added
        while (!exitLoop) {
            printAddMenuItem();
            switch (scanner.nextLine()) {
                case "1" -> {
                    purchaseFoodItem();
                }
                case "2" -> {
                    purchaseClothesItem();
                }
                case "3" -> {
                    purchaseEntertainmentItem();
                }
                case "4" -> {
                    purchaseOtherItem();
                }
                case "5" -> {
                    exitLoop = true;
                    System.out.println();
                }
            }
        }

    }

    public void showPurchasesMenu() {
        boolean exitLoop = false;

        if (this.items.size() == 0) {
            exitLoop = true;
            System.out.println("\nThe purchase list is empty!\n");
        }

        //While loop inside a switch to determine with type of item is going to be added
        while (!exitLoop) {
            printPurchaseMenu();
            switch (scanner.nextLine()) {
                case "1" -> {
                    showFoodPurchases();
                }
                case "2" -> {
                    showClothesPurchases();
                }
                case "3" -> {
                    showEntertainmentPurchases();
                }
                case "4" -> {
                    showOtherPurchases();
                }
                case "5" -> {
                    showPurchases();
                }
                case "6" -> {
                    exitLoop = true;
                    System.out.println();
                }
            }
        }

    }

    public double priceTotalItems() {
        double totalAmount = 0;
        for (PurchasedItems item : this.items) {
            totalAmount += item.getPrice();
        }
        return totalAmount;
    }

    public double priceSpecificTypeItem(purchaseType type) {
        double totalAmount = 0;
        for (PurchasedItems item : this.items) {
            if (item.getType().equals(type)) {
                totalAmount += item.getPrice();
            }
        }
        return totalAmount;
    }

    public void getBalance() {
        System.out.println("\nBalance: $" + this.balance + "\n");
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBalance() {
        System.out.println("\nEnter income:");
        double balance = Double.parseDouble(scanner.nextLine());
        this.balance += balance;
        System.out.println("Income was added!\n");
    }

    public void showPurchases() {
        if (this.items.size() == 0) {
            System.out.println("\nThe purchase list is empty\n");
        } else {
            System.out.println("\nAll:");
            System.out.println(purchaseList());
            System.out.printf("Total sum: $%.2f ", priceTotalItems());
            System.out.println();
        }
    }

    public String purchaseList() {
        //I can refactor all this code similar to getFoodItems but for all the items
        StringBuilder sb = new StringBuilder();

        //Looking only for ALL items
        for (PurchasedItems x : this.items) {
            sb.append(x).append("\n");
        }

        //Deleting the last new line before returning the string
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String getFoodItems() {
        StringBuilder sb = new StringBuilder();

        //Looking only for FOOD items
        for (PurchasedItems x : this.items) {
            if (x.getType() == purchaseType.FOOD) {
                sb.append(x).append("\n");
            }
        }
        //Deleting the last new line before returning the string
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void showFoodPurchases() {
        //Checking if there's any Food item type on the list.
        boolean isThereFoodItems = false;
        for (PurchasedItems x : this.items) {
            if (x.getType().equals(purchaseType.FOOD)) {
                isThereFoodItems = true;
                break;
            }
        }

        if (this.items.size() == 0 || !isThereFoodItems) {
            System.out.println("\nFood:");
            System.out.println("The purchase list is empty!");
        } else {
            System.out.println("\nFood:");
            System.out.println(getFoodItems());
            System.out.printf("Total sum: $%.2f ", priceSpecificTypeItem(purchaseType.FOOD));
            System.out.println();
        }
    }

    public String getClothesItems() {
        StringBuilder sb = new StringBuilder("");

        //Looking only for CLOTHES items
        for (PurchasedItems x : this.items) {
            if (x.getType() == purchaseType.CLOTHES) {
                sb.append(x).append("\n");
            }
        }
        //Deleting the last new line before returning the string
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void showClothesPurchases() {
        //Checking if there's any Clothes type ITEM on the list.
        boolean isThereClothesItems = false;
        for (PurchasedItems x : this.items) {
            if (x.getType().equals(purchaseType.CLOTHES)) {
                isThereClothesItems = true;
                break;
            }
        }

        if (this.items.size() == 0 || !isThereClothesItems) {
            System.out.println("\nClothes:");
            System.out.println("The purchase list is empty!");
        } else {
            System.out.println("\nClothes:");
            System.out.println(getClothesItems());
            System.out.printf("Total sum: $%.2f ", priceSpecificTypeItem(purchaseType.CLOTHES));
            System.out.println();
        }
    }

    public String getEntertainmentItems() {
        StringBuilder sb = new StringBuilder();

        //Looking only for Entertainment items
        for (PurchasedItems x : this.items) {
            if (x.getType() == purchaseType.ENTERTAINMENT) {
                sb.append(x).append("\n");
            }
        }
        //Deleting the last new line before returning the string
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void showEntertainmentPurchases() {
        //Checking if there's any Entertainment type ITEM on the list.
        boolean isThereEntertainmentItems = false;
        for (PurchasedItems x : this.items) {
            if (x.getType().equals(purchaseType.ENTERTAINMENT)) {
                isThereEntertainmentItems = true;
                break;
            }
        }

        if (this.items.size() == 0 || !isThereEntertainmentItems) {
            System.out.println("\nEntertainment:");
            System.out.println("The purchase list is empty!");
        } else {
            System.out.println("\nEntertainment:");
            System.out.println(getEntertainmentItems());
            System.out.printf("Total sum: $%.2f ", priceSpecificTypeItem(purchaseType.ENTERTAINMENT));
            System.out.println();
        }
    }

    public String getOtherItems() {
        StringBuilder sb = new StringBuilder();

        //Looking only for OTHER items type
        for (PurchasedItems x : this.items) {
            if (x.getType() == purchaseType.OTHER) {
                sb.append(x).append("\n");
            }
        }
        //Deleting the last new line before returning the string
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void showOtherPurchases() {
        //Checking if there's any OTHER type ITEM on the list.
        boolean isThereOtherItems = false;
        for (PurchasedItems x : this.items) {
            if (x.getType().equals(purchaseType.OTHER)) {
                isThereOtherItems = true;
                break;
            }
        }

        if (this.items.size() == 0 || !isThereOtherItems) {
            System.out.println("\nOther:");
            System.out.println("The purchase list is empty!");
        } else {
            System.out.println("\nOther:");
            System.out.println(getOtherItems());
            System.out.printf("Total sum: $%.2f ", priceSpecificTypeItem(purchaseType.OTHER));
            System.out.println();
        }
    }

    public static void printAddMenuItem() {
        System.out.println("""
                
                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) Back""");
    }

    public static void printPurchaseMenu() {
        System.out.println("""
                
                Choose the type of purchases
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) All
                6) Back""");
    }

}
