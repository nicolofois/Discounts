package discounts;

public class Product {
    String categoryId;
    String productId;
    double price;
    int discount;
    
    public Product(String categoryId2, String productId2, double price) {
        this.categoryId = categoryId2;
        this.productId = productId2;
        this.price = price;
        this.discount = 0;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
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
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

}
