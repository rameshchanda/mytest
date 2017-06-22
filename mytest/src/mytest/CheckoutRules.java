package mytest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CheckoutRules {

	private final String reduceItem;
	private final int minimumAmount;
	private final double reducePrice;

	public CheckoutRules(String item, int min, double reducePrice) {
		this.reduceItem = item;
		this.minimumAmount = min;
		this.reducePrice = reducePrice;
	}

	public double applyRule(List<String> shoppingCart, double total) {
		int count = Collections.frequency(shoppingCart, reduceItem);
		// Integer division gives the number of times the discount is applied
		double deduction = (count / minimumAmount) * reducePrice;
		return total - deduction;
	}

	public static void main(String args[]) {
		// 4 apples and 3 oranges = 2 * 25 + 2 * 60 = 1.2 + .5 = 170
		List<String> shoppingCart = Arrays.asList(Constants.APPLE, Constants.APPLE, Constants.ORANGE, Constants.APPLE,
				Constants.APPLE, Constants.ORANGE, Constants.ORANGE);
		CheckoutTotal checkOutTotal = new CheckoutTotal();
		checkOutTotal.addPrice(Constants.APPLE, 25);
		checkOutTotal.addPrice(Constants.ORANGE, 60);

		double total = checkOutTotal.calculateTotal(shoppingCart);
		CheckoutRules reductionRules = new CheckoutRules(Constants.APPLE, 2,
				checkOutTotal.getPrice(Constants.APPLE));
		total = reductionRules.applyRule(shoppingCart, total);

		reductionRules = new CheckoutRules(Constants.ORANGE, 3, checkOutTotal.getPrice(Constants.ORANGE));
		total = reductionRules.applyRule(shoppingCart, total);
		System.out.println(total);

	}
}
