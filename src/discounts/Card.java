package discounts;

public class Card {
    int id;
    String clientname;

    public Card(int id, String clientname) {
        this.id = id;
        this.clientname = clientname;
    }

    public int getId() {
        return id;
    }
    public String getClientName() {
        return clientname;
    }
    
}
