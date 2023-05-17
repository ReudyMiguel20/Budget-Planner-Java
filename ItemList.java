package budget;

import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

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

    public void addItemFromFile(PurchasedItems item) {
        this.items.add(item);
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

    public void sortItemMenu() {
        boolean exitLoop = false;

        while (!exitLoop) {
            printSortMenu();

            switch (scanner.nextLine()) {
                case "1" -> {
                    sortAll();
                }
                case "2" -> {
                    sortByTypeCalculations();
                }
                case "3" -> {
                    sortCertainTypeMenu();
                }
                case "4" -> {
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

    public String csvFormat() {
        StringBuilder sb = new StringBuilder();

        for (PurchasedItems x : this.items) {
            sb.append(x.csv()).append("\n");
        }

        //Removing the last newline at the end of the string
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void exportFile() {
        try {
            FileWriter fw = new FileWriter("purchases.txt");
            fw.write(csvFormat() + ":" + balance);
            fw.close();
            System.out.println("\nPurchases were saved!\n");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("\nTheres nothing to save\n");
        }
    }

    public void importFile() {
        String line = "";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("purchases.txt"));
            while ((line = reader.readLine()) != null) {
                String[] splitter = line.split(":");
                String productName = splitter[0];
                double price = Double.parseDouble(splitter[1]);
                purchaseType typeProduct = purchaseType.valueOf(splitter[2]);


                addItemFromFile(new PurchasedItems(productName, price, typeProduct));
                if (splitter.length == 4) {
                    double balance = Double.parseDouble(splitter[3]);
                    setBalance(balance);
                }
            }
            System.out.println("\nPurchases were loaded!\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortMenu() {
        boolean exitLoop = false;

        while (!exitLoop) {
            switch (scanner.nextLine()) {
                case "1" -> sortAll();
                case "2" -> sortByTypeCalculations();
                case "3" -> sortCertainTypeMenu();
            }
        }
    }

    public void sortAll() {
        StringBuilder sb = new StringBuilder("\nAll:\n");

        if (this.items.size() == 0) {
            System.out.println("\nThe purchase list is empty!");
            return;
        }

        //Bubble sort algorithm to sort the items by Ascending order
        for (int i = 0; i < this.items.size() - 1; i++) {
            for (int j = 0; j < this.items.size() - i - 1; j++) {
                if (this.items.get(j).getPrice() < this.items.get(j + 1).getPrice()) {
                    Collections.swap(this.items, j, j + 1);
                }
            }
        }

        //Appending the items to the StringBuilder
        for (PurchasedItems x : this.items) {
            sb.append(x).append("\n");
        }

        //Removing the last newline from the StringBuilder
        sb.deleteCharAt(sb.length() - 1);

        //Printing the list
        System.out.println(sb.toString());
        System.out.println("Total: $" + priceTotalItems());
    }

    public void sortByTypeCalculations() {
        //Creating new HashMap with the name and values of each type for item
        HashMap<String, Double> typeItems = new HashMap<>();
        typeItems.put("Food", getPriceTypeItems(purchaseType.FOOD));
        typeItems.put("Clothes", getPriceTypeItems(purchaseType.CLOTHES));
        typeItems.put("Entertainment", getPriceTypeItems(purchaseType.ENTERTAINMENT));
        typeItems.put("Other", getPriceTypeItems(purchaseType.OTHER));

        //Get the entry set of the HashMap
        Set<Map.Entry<String, Double>> entrySet = typeItems.entrySet();

        //Convert the entry set to a list for sorting
        List<Map.Entry<String, Double>> list = new ArrayList<>(entrySet);

        //Sort the list based on the values in descending order
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        //Iterate over the sorted list and print the key-value pairs
        System.out.println();
        list.forEach(s -> {
            DecimalFormat df = new DecimalFormat("0.00");
            System.out.println(s.getKey() + " - $" + df.format(s.getValue()));
        });
        System.out.println("Total sum: $" + priceTotalItems());

    }

    public double getPriceTypeItems(purchaseType type) {
        DecimalFormat df = new DecimalFormat("0.00");
        double totalAmountByType = 0.00;

        //Looking only for certain type items, so it can sum all the values of this certain type and return it
        for (PurchasedItems x : this.items) {
            if (x.getType() == type) {
                totalAmountByType += x.getPrice();
            }
        }

        //Converting the DecimalFormat value to Double, then returning it as '0.00' just like the pattern
        return Double.parseDouble(df.format(totalAmountByType));
    }


    public void sortCertainType(purchaseType type) {
        StringBuilder sb = new StringBuilder();
        ArrayList<PurchasedItems> certainItems = new ArrayList<>(getCertainItems(type));

        //If list is empty return and print a message
        if (certainItems.size() == 0) {
            System.out.println("\nThe purchase list is empty!");
            return;
        }

        //Bubble sort algorithm to sort the items by Ascending order
        for (int i = 0; i < certainItems.size() - 1; i++) {
            for (int j = 0; j < certainItems.size() - i - 1; j++) {
                    if (certainItems.get(j).getPrice() < certainItems.get(j + 1).getPrice()) {
                        Collections.swap(certainItems, j, j + 1);
                    }
            }
        }

        //Then appending all the list into a StringBuilder to further printing
        for (PurchasedItems x : certainItems) {
            sb.append(x).append("\n");
        }

        //Removing the last newline
        sb.deleteCharAt(sb.length() - 1);

        //Assigning a value to a String depending on the ENUM type
        String typeItem = "";
        switch (type) {
            case FOOD -> typeItem = "Food: ";
            case CLOTHES -> typeItem = "Clothes: ";
            case ENTERTAINMENT -> typeItem = "Entertainment: ";
            case OTHER -> typeItem = "Other: ";
        }

        //Printing the list
        System.out.println("\n" + typeItem);
        System.out.println(sb.toString());
        System.out.println("Total sum: $" + getPriceTypeItems(type));
        System.out.println();

    }

    public List<PurchasedItems> getCertainItems(purchaseType type) {
        ArrayList<PurchasedItems> certainItems = new ArrayList<>();

        //Looking only for certain type items
        for (PurchasedItems x : this.items) {
            if (x.getType() == type) {
                certainItems.add(x);
            }
        }

        return certainItems;
    }

    public void sortCertainTypeMenu() {
        printCertainTypeMenu();
        switch (scanner.nextLine()) {
            case "1" -> sortCertainType(purchaseType.FOOD);
            case "2" -> sortCertainType(purchaseType.CLOTHES);
            case "3" -> sortCertainType(purchaseType.ENTERTAINMENT);
            case "4" -> sortCertainType(purchaseType.OTHER);
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

    public static void printSortMenu() {
        System.out.println("""
                                
                How do you want to sort?
                1) Sort all purchases
                2) Sort by type
                3) Sort certain type
                4) Back""");
    }

    public static void printCertainTypeMenu() {
        System.out.println("""
                                
                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other""");
    }

}
