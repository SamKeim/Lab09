import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class GroceryShopper {
	public static void averageCost(ArrayList<String> cart, ArrayList<Integer> quantity, ArrayList<Double> price) {
		// Calculates average cost and prints to the console
		double sum = 0.0;
		for (int i = 0; i < quantity.size(); i++) {
			sum += (quantity.get(i) * price.get(i));
		}
		double average = sum / quantity.size();
		System.out.printf("The average value of the items in your cart is : $%.2f\n", average);
	}

	public static void highestCost(ArrayList<String> cart, ArrayList<Integer> quantity, ArrayList<Double> price) {
		// Finds highest value item in the cart and prints it to the console
		double max = Collections.max(price);
		int index = price.indexOf(max);
		System.out.printf("The highest value item in your cart : " + cart.get(index) + " for $%.2f\n", price.get(index));
	}

	public static void lowestCost(ArrayList<String> cart, ArrayList<Integer> quantity, ArrayList<Double> price) {
		// Finds lowest value item in the cart and prints it to the console
		double min = Collections.min(price);
		int index = price.indexOf(min);
		System.out.printf("The lowest value item in your cart : " + cart.get(index) + " for $%.2f\n", price.get(index));
	}

	public static void printMenu(TreeMap<String, Double> store) {
		// Prints out the menu on request
		System.out.printf("\n%-15s%-15s\n==============================\n", "Item", "Price");
		Set<String> setOfKeys = store.keySet();
		int i = 1;
		for (String item : setOfKeys) {
			System.out.printf("%-5d%-15s$%-15.2f\n", i++, item, store.get(item));
		}
	}

	public static void addItem(int item, ArrayList<String> cart, ArrayList<Integer> quantity,
			ArrayList<Double> price, TreeMap<String, Double> prices, TreeMap<Integer, String> stock) {
		// Tries to add an item to the cart using try/catch
		try {
			System.out.printf("Adding " + stock.get(item) + " to your cart at $%.2f\n", prices.get(stock.get(item)));
			if (cart.indexOf(stock.get(item)) == -1) { // If the item is not in the cart, will return -1
				cart.add(stock.get(item));
				quantity.add(1);
				price.add(prices.get(stock.get(item)));
			} else { // Adds one to quantity in correct location of ArrayList
				quantity.set(cart.indexOf(stock.get(item)), quantity.get(cart.indexOf(stock.get(item))) + 1);
			}
		} catch(NullPointerException e){ 
			System.out.println("Sorry, we don't have those.");
		}
	}

	public static boolean yesOrNo(String userChoice, Scanner scnr) {
		do {
			if (userChoice.toUpperCase().startsWith("Y")) {
				return true;
			} else if (userChoice.toUpperCase().startsWith("N")) {
				return false;
			} else {
				System.out.println("Please enter Y or N.");
				userChoice = scnr.nextLine();
			}
		} while (true);
	}

	public static TreeMap<Integer,String> stockStore() {
		// Stocks index TreeMap, code put in module to keep clean
		TreeMap<Integer, String> index = new TreeMap<>();
		index.put(1, "Apple");
		index.put(2, "Banana");
		index.put(3, "Cauliflower");
		index.put(4, "Dragonfruit");
		index.put(5, "Elderberry");
		index.put(6, "Figs");
		index.put(7, "Grapefruit");
		index.put(8, "Honeydew");
		return index;
	}
	public static TreeMap<String, Double> priceItems(){
		// Stocks store TreeMap, code put in module to keep clean
		TreeMap<String, Double> store = new TreeMap<>();
		store.put("Apple", 0.99);
		store.put("Banana", 0.59);
		store.put("Cauliflower", 1.59);
		store.put("Dragonfruit", 2.19);
		store.put("Elderberry", 1.79);
		store.put("Figs", 2.09);
		store.put("Grapefruit", 1.99);
		store.put("Honeydew", 3.49);
		return store;
	}
	
	public static void checkout(ArrayList<String> cart, ArrayList<Integer> quantity, ArrayList<Double> price) {
		// Called at end to print final list of items in cart
		System.out.println("Thank you for your order!");
		System.out.println("Here's what is in your cart:");
		for (int i = 0; i < cart.size(); i++) {
			System.out.printf("%-15s%3d at $%-5.2fea. for $%-5.2f\n", cart.get(i), quantity.get(i), price.get(i),(price.get(i) * quantity.get(i)));
		}
		// Calls Average Cost, Highest Cost, and Lowest Cost methods
		averageCost(cart,quantity,price);
		highestCost(cart,quantity,price);
		lowestCost(cart,quantity,price);
	}

	public static void main(String[] args) {
		
		// Stock Store
		TreeMap<Integer, String> stock = stockStore();
		TreeMap<String, Double> prices = priceItems();
		
		// Declare variables
		boolean userChoice = false;
		int userItem = -1;
		ArrayList<String> cart = new ArrayList<>();
		ArrayList<Integer> quantity = new ArrayList<>();
		ArrayList<Double> price = new ArrayList<>();
		Scanner scnr = new Scanner(System.in);
		String userSelection = "Y";

		// Welcome user
		System.out.println("Welcome to Guenther's Market!");
		
		// Start Shopping
		printMenu(prices);
		do {
			System.out.println("What item number would you like to order?");
			userItem = scnr.nextInt();
			scnr.nextLine(); // collector
			addItem(userItem, cart, quantity, price, prices, stock);
			System.out.println("Would you like to order anything else? (Y/N) Enter \"Menu\" to display the menu.");
			userSelection = scnr.nextLine();
			if (userSelection.equalsIgnoreCase("Menu")) {
				printMenu(prices);
				System.out.println("Would you like to order something else? (Y/N)");
				userChoice = yesOrNo(scnr.nextLine(),scnr);
			} else {
				userChoice = yesOrNo(userSelection, scnr);
			}
		} while (userChoice);
		checkout(cart, quantity, price);
	}
}
