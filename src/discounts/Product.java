package discounts;

public class Product {
    String categoryId;
    String productId;
    double price;
    int discount;
    
    public Product(String categoryId, String productId, double price) {
        this.categoryId = categoryId;
        this.productId = productId;
        this.price = price;
        this.discount = 0;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public String getProductId() {
        return this.productId;
    }

    public double getPrice() {
        return this.price;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getDiscountedPrice() {
        int p = 100 - this.discount;
        return (this.price/100)*p;
    }

}
