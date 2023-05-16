package budget;

import java.util.ArrayList;

public class ItemList {
    private ArrayList<PurchasedItems> items;
    public ItemList() {
        this.items = new ArrayList<>();
    }

    public void addItem(PurchasedItems item) {
        this.items.add(item);
    }

    public double priceTotalItems() {
        double totalAmount = 0;
        for (PurchasedItems item : this.items) {
            totalAmount += item.getPrice();
        }
        return totalAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (PurchasedItems item : this.items) {
            String priceString = String.format("%.2f", item.getPrice());
            sb.append(item.getName()).append(" $").append(priceString).append("\n");
        }
        return sb.toString();
    }


}
