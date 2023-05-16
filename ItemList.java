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
        }
    }

    public void purchaseItem() {
        System.out.println("\nEnter purchase name:");
        String item = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = Double.parseDouble(scanner.nextLine());

        this.balance -= price;
        addItem(new PurchasedItems(item, price));
        System.out.println("Purchase was added!\n");
    }

    public double priceTotalItems() {
        double totalAmount = 0;
        for (PurchasedItems item : this.items) {
            totalAmount += item.getPrice();
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
            System.out.println(purchaseList());
            System.out.printf("Total sum: $%.2f ", priceTotalItems());
            System.out.println("\n");
        }

    }

    public String purchaseList() {
        StringBuilder sb = new StringBuilder("\n");

        for (int i = 0; i < this.items.size(); i++) {
            String priceString = String.format("%.2f", this.items.get(i).getPrice());
            if (this.items.size() == 1) {
                sb.append(this.items.get(i).getName()).append(" $").append(priceString);
            } else if (i == this.items.size() - 1) {
                sb.append(this.items.get(i).getName()).append(" $").append(priceString);
            } else {
                sb.append(this.items.get(i).getName()).append(" $").append(priceString).append("\n");
            }
        }

        return sb.toString();
    }


}
