package budget;

public class PurchasedItems {

    private String name;
    private double price;
    private purchaseType type;

    public PurchasedItems(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public PurchasedItems(String name, double price, purchaseType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(purchaseType type) {
        this.type = type;
    }

    public purchaseType getType() {
        return this.type;
    }
}
