package discounts;
import java.util.*;

public class Discounts {
	TreeMap<Integer,Card> cards = new TreeMap<>();
	TreeMap<String,Product> products = new TreeMap<>();
	TreeMap<Integer, Purchase> purchases = new TreeMap<>();
	LinkedList<Item> itemsList = new LinkedList<>();
	int currentId = 1, purchaseId = 1;
	double totalSpentNoCard = 0;

	
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
	       return cards.size();
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
		return (int) Math.round(products.values().stream().filter(p -> p.getCategoryId() == categoryId).mapToDouble(p -> p.getPrice()).average().orElse(0.0));
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
		for (String i : items) {
			String e[] = i.split(":");
			if (!products.containsKey(e[0])) {
				throw new DiscountsException();
			}
		}
		LinkedList<Item> itList = new LinkedList<>();
		for (String it : items) {
			String e[] = it.split(":");
			Product p = products.get(e[0]);
			Item item = new Item(p, Integer.valueOf(e[1]));
			itemsList.add(item);
			itList.add(item);
		}
		Purchase p = new Purchase(purchaseId, itList);
		Card c = cards.get(cardId);
		p.setCardId(cardId);
		c.addPurchase(p);
		purchases.put(purchaseId, p);
        return purchaseId++;
	}

	public int addPurchase(String... items) throws DiscountsException {
        for (String i : items) {
			String e[] = i.split(":");
			if (!products.containsKey(e[0])) {
				throw new DiscountsException();
			}
		}
		LinkedList<Item> itList = new LinkedList<>();
		for (String it : items) {
			String e[] = it.split(":");
			Product p = products.get(e[0]);
			Item item = new Item(p, Integer.valueOf(e[1]));
			itemsList.add(item);
			itList.add(item);
		}
		Purchase p = new Purchase(purchaseId, itList);
		purchases.put(purchaseId, p);
		totalSpentNoCard = totalSpentNoCard + getAmount(purchaseId);
        return purchaseId++;
	}
	
	public double getAmount(int purchaseCode) {
		double total = 0;
		Purchase p = purchases.get(purchaseCode);
		if (p.getCardId() != 0) {
			for (Item i : p.getItemsPurchased()) {
				total = total + i.getQty() * i.getProduct().getDiscountedPrice();
			}
			return total;
		}
		else {
			for (Item i : p.getItemsPurchased()) {
				total = total + i.getQty() * i.getProduct().getPrice();
			}
			return total;
		}
	}
	
	public double getDiscount(int purchaseCode)  {
		Purchase p = purchases.get(purchaseCode);
		double effective = 0, discounted = 0;
		if (p.getCardId() == 0) {
			return 0;
		}
		else {
			discounted = getAmount(purchaseCode);
			for (Item i : p.getItemsPurchased()) {
				effective = effective + i.getQty() * i.getProduct().getPrice();
			}

		}
        return effective-discounted;
	}
	
	public int getNofUnits(int purchaseCode) {
		int total = 0;
		List<Integer> qtyList = purchases.get(purchaseCode).getItemsPurchased().stream().map(i -> i.getQty()).toList();
		for (int i : qtyList) {
			total = total + i;
		}
        return total;
	}
	
	//R5
	public SortedMap<Integer, List<String>> productIdsPerNofUnits() {
		TreeMap<Integer, List<String>> result = new TreeMap<>();
		Map<String, Integer> productQuantities = new HashMap<>();
		
		// Somma le quantità per ogni prodotto
		for (Item item : itemsList) {
			String productId = item.getProduct().getProductId();
			productQuantities.merge(productId, item.getQty(), Integer::sum);
		}
		
		// Raggruppa per quantità
		for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
			result.computeIfAbsent(entry.getValue(), k -> new ArrayList<>())
				  .add(entry.getKey());
		}
		
		// Ordina le liste
		result.values().forEach(list -> Collections.sort(list));
		
		return result;
	}
	
	public SortedMap<Integer, Double> totalPurchasePerCard() {
		SortedMap<Integer,Double> purchasesMap = new TreeMap<>();
		for (Card c : cards.values()) {
			if (!c.getPurchases().isEmpty()) {
				purchasesMap.put(c.getId(), Math.round(c.totalSpent() * 100.0) / 100.0);
			}
		}
        return purchasesMap;
	}
	
	public double totalPurchaseWithoutCard() {
        return totalSpentNoCard;
	}
	
	public SortedMap<Integer, Double> totalDiscountPerCard() {
		TreeMap<Integer, Double> discountMap = new TreeMap<>();
		
		for (Card card : cards.values()) {
			double totalDiscount = card.getPurchases().stream()
				.mapToDouble(p -> getDiscount(p.getId()))
				.sum();
			
			if (totalDiscount > 0) {
				discountMap.put(card.getId(), Math.round(totalDiscount * 100.0) / 100.0);
			}
		}
		
		return discountMap;
	}


}
