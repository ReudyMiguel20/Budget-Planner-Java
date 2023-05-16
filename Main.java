package budget;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        ItemList itemList = new ItemList();

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            String[] splitter = userInput.split(" \\$");
            String item = splitter[0];
            double price = Double.parseDouble(splitter[1]);

            itemList.addItem(new PurchasedItems(item, price));
        }

        System.out.println(itemList.toString());
        System.out.printf("Total: $%.2f", itemList.priceTotalItems());
    }
}
