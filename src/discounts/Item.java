package discounts;

public class Item {
    Product product;
    int qty;
    public Item(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }
    public int getQty() {
        return qty;
    }

}
