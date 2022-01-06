package northpole;

public final class Gift {
    private final String productName;
    private final double price;
    private final String category;
    private final int quantity;

    public Gift(final String productName, final int price, final String category,
                final int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
