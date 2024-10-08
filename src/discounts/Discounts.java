package discounts;
import java.util.*;

public class Discounts {
	TreeMap<Integer,Card> cards = new TreeMap<>();
	TreeMap<String,Product> products = new TreeMap<>();
	TreeMap<String,LinkedList<Item>> Purchases = new TreeMap<>();
	int currentId = 1;

	
	//R1
	public int issueCard(String name) {
		Card c = new Card(currentId, name);
		cards.put(currentId, c);
	    return currentId++;
	}
	
    public String cardHolder(int cardN) {
        return cards.get(cardN).getClientName();
    }
    

	public int nOfCards() {
	       return cards.keySet().size();

	}
	
	//R2
	public void addProduct(String categoryId, String productId, double price) throws DiscountsException {
			if (products.containsKey(productId)) {
				throw new DiscountsException("Prodotto già presente");
			}
			Product p = new Product(categoryId, productId, price);
			products.put(productId, p);
	}
	
	public double getPrice(String productId) throws DiscountsException {
		if (!products.containsKey(productId)) {
			throw new DiscountsException("Prodotto non trovato");
		}
        return products.get(productId).getPrice();
	}

	public int getAveragePrice(String categoryId) throws DiscountsException {
		double total = 0;
		int count = 0;
		List<Double> prices = products.values().stream().filter(p -> p.getCategoryId() == categoryId).map(p -> p.getPrice()).toList();
		for (Double p : prices) {
			total = total + p;
			count++;
		}
        return (int)Math.round(total/count);
	}
	
	//R3
	public void setDiscount(String categoryId, int percentage) throws DiscountsException {
		if (percentage < 0 || percentage >50) {
			throw new DiscountsException("Sconto non valido");
		}
		for (Product p : products.values()) {
			if (p.getCategoryId() == categoryId) {
				p.setDiscount(percentage);
			}
		}
	}

	public int getDiscount(String categoryId) {
		int discount = 0;
		for (Product p : products.values()) {
			if (p.getCategoryId() == categoryId) {
				discount = p.getDiscount();
				break;
			}
		}
        return discount;
	}

	//R4
	public int addPurchase(int cardId, String... items) throws DiscountsException {
        return -1;
	}

	public int addPurchase(String... items) throws DiscountsException {
        return -1;
	}
	
	public double getAmount(int purchaseCode) {
        return -1.0;
	}
	
	public double getDiscount(int purchaseCode)  {
        return -1.0;
	}
	
	public int getNofUnits(int purchaseCode) {
        return -1;
	}
	
	//R5
	public SortedMap<Integer, List<String>> productIdsPerNofUnits() {
        return null;
	}
	
	public SortedMap<Integer, Double> totalPurchasePerCard() {
        return null;
	}
	
	public int totalPurchaseWithoutCard() {
        return -1;
	}
	
	public SortedMap<Integer, Double> totalDiscountPerCard() {
        return null;
	}


}
