package discounts;

import java.util.LinkedList;

public class Purchase {
    private int id;
    private int cardId;
    private LinkedList<Item> itemsPurchased;

    public Purchase(int id, LinkedList<Item> itemsPurchased) {
        this.id = id;
        this.itemsPurchased = itemsPurchased;
        this.cardId = 0;
    }

    public int getCardId() {
        return cardId;
    }
    public LinkedList<Item> getItemsPurchased() {
        return itemsPurchased;
    }

    public void setCardId(int id) {
        this.cardId = id;
    }

    public int getId() {
        return id;
    }
}
