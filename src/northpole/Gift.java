package northpole;

import enums.Category;

public class Gift {
    private final String productName;
    private final double price;
    private final String category;

    public Gift(String productName, int price, String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
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
