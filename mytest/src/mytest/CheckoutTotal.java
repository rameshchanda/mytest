package mytest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CheckoutTotal implements Checkout {
	
    private final Map<String, Double> basePrice = new HashMap<>();

    public void addPrice(String item, double price) {
        basePrice.put(item, price);
    }
    
    public double getPrice(String item) {
    	return basePrice.get(item);
    }

    @Override
    public double calculateTotal(List<String> shoppingCart) {
        return shoppingCart.stream().mapToDouble(basePrice::get).sum();
    }
    
    public static void main(String args[]) {
    	CheckoutTotal total = new CheckoutTotal();
    	total.addPrice(Constants.APPLE, 60);
    	total.addPrice(Constants.ORANGE, 25);
    	
    	double tot = total.calculateTotal(Arrays.asList(Constants.APPLE, Constants.APPLE, Constants.ORANGE, Constants.APPLE));
    	System.out.println(tot);
    }
}
