package discounts;

import java.util.LinkedList;

public class Card {
    private int id;
    private String clientName;
    private LinkedList<Purchase> purchases;


    public Card(int id, String clientName) {
        this.id = id;
        this.clientName = clientName;
        this.purchases = new LinkedList<>();
    }

    public int getId() {
        return id;
    }
    public String getClientName() {
        return clientName;
    }

    public LinkedList<Purchase> getPurchases() {
        return purchases;
    }

    public void addPurchase(Purchase p) {
        this.purchases.add(p);
    }

    public double totalSpent() {
        double total = 0;
        for (Purchase p : this.purchases) {
            LinkedList<Item> it = p.getItemsPurchased();
            for (Item i : it) {
                total = total + i.getProduct().getDiscountedPrice()*i.getQty();
            }
        }
        return total;
    }
    
}
