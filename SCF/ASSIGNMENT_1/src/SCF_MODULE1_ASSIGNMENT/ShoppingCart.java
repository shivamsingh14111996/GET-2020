package SCF_MODULE1_ASSIGNMENT;

import java.util.*;

/**
 * @author Shivam 
 * Items class have the Items of catalogue
 */
class Items {
	Map<String, String> items;
	Map<String, Integer> itemsIdPrice;

	/**
	 * Constructor will assign values to items map and itemsIdPrice map
	 */
	Items() {
		itemsIdPrice = new HashMap<String, Integer>();
		itemsIdPrice.put("pId01", 10);
		itemsIdPrice.put("pId02", 20);
		itemsIdPrice.put("pId03", 30);
		itemsIdPrice.put("pId04", 40);
		itemsIdPrice.put("pId05", 50);
		items = new HashMap<String, String>();
		items.put("pId01", "item1");
		items.put("pId02", "item2");
		items.put("pId03", "item3");
		items.put("pId04", "item4");
		items.put("pId05", "item5");
	}
}

/**
 * @author Shivam
 *  		ShoppingCart class is created to perform cart operations
 *  		ShoppingCart extends the class Items so that it can use the Items
 */
public class ShoppingCart extends Items {
	Map<String, Integer> cartItems;

	ShoppingCart() {
		/* String for item id and Integer for quantity */
		cartItems = new HashMap<String, Integer>();
	}

	/**
	 * @Description
	 * printItems method is used to display the catalogue of shopping mall
	 */
	public void printItems() {

		System.out.println("id    Item    Price");
		for (Map.Entry<String, String> item : super.items.entrySet()) {
			System.out.println(item.getKey() + " " + item.getValue() + "  " + super.itemsIdPrice.get(item.getKey()));

		}
		System.out.println();
		inputUserChoice();
	}

	/**
	 * @Description 
	 * inputUserChoice is used to display the tasks that the Shopping cart can
	 * perform
	 */
	public void inputUserChoice() {
		int input = 0;

		Scanner userInput = null;

		try {
			System.out.println("1. add item");
			System.out.println("2. change item quantity");
			System.out.println("3. remove item");
			System.out.println("4. display your cart items");
			System.out.println("5. generate bill");
			System.out.println("6. print items in shopping mall");
			System.out.println("7. exit");
			userInput = new Scanner(System.in);
			input = userInput.nextInt();
			cartOperations(input);
		} catch (Exception e) {
			System.out.println("input should be an Integer");
			inputUserChoice();
		}

	}

	/**
	 * @Description 
	 * cartOperations method  is used to select the particular "operation" to perform
	 * @param input 
	 */
	public void cartOperations(int input) {
		try {

			switch (input) {
			case  1 :
				addItem();
				break;
			case 2:
				changeItemquantity();
				break;
			case 3:
				removeItem();
				break;
			case 4:
				displayCart();
				break;
			case 5:
				generateBill();
				break;
			case 6:
				printItems();
				break;
			case 7:
				exitFromShoppingMall();
				break;
			default:
				System.out.println("provide proper input");
				inputUserChoice();
			}
		} catch (Exception e) {

		}

	}

	/**
	 * @Description 
	 * removeItem method is used if user want to completely remove an Item from his
	 * cart
	 */
	public void removeItem() {
		Scanner scanner = null;
		try {
			if (cartItems.isEmpty()) {
				System.out.println("cart is empty please add some items");
				inputUserChoice();
			} else {
				System.out.println("if you want to go back press 'back' and 'exit' to close and then press enter");
				System.out.println("please enter the id of the item to remove...");
				System.out.println("id: ");
				scanner = new Scanner(System.in);

				String id = scanner.next();
				if ("back".equals(id)) {
					inputUserChoice();
				} else if ("exit".equals(id)) {

					exitFromShoppingMall();

				} else if (cartItems.containsKey(id)) {
					cartItems.remove(id);
					System.out.println("item removed successfully.");
					inputUserChoice();
				}
			}
		} catch (Exception e) {

		}

	}

	/**
	 * @Description
	 * exitFromShoppingMall method will help the user to exit from the shopping mall
	 */
	public void exitFromShoppingMall() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);

			System.out.println("are you sure you want to exit ?");
			System.out.println("yes/no");

			String result = scanner.next();
			if ("yes".contentEquals(result)) {
				System.exit(0);
			} else if ("no".contentEquals(result)) {
				inputUserChoice();
			}
		} catch (Exception e) {

			System.out.println("provide proper input");
			exitFromShoppingMall();

		}

	}

	/**
	 * @Description
	 * generateBill method is used to generate bill of user's cart
	 */
	public void generateBill() {
		int total = 0;
		String result = "";
		Scanner scanner = null;
		if (cartItems.isEmpty()) {
			System.out.println("cart is empty please add some items");
			inputUserChoice();

		} else {
			System.out.println("id     item     quantity   price ");

			for (Map.Entry<String, Integer> cart : cartItems.entrySet()) {
				System.out.println(cart.getKey() + "  " + items.get(cart.getKey()) + "        " + cart.getValue()
						+ "       " + itemsIdPrice.get(cart.getKey()) * cart.getValue());
				total += itemsIdPrice.get(cart.getKey()) * cart.getValue();
			}
			System.out.println("------------------------------------");
			System.out.println("Total                       " + total);
			System.out.println("thanks for shopping!!!");
			System.out.println("press 'exit' to exit  or press 'back' to go back to cart management section");
			scanner = new Scanner(System.in);
			result = scanner.next();
			if ("exit".equals(result)) {

				exitFromShoppingMall();
			}
			if ("back".equals(result)) {

				inputUserChoice();
			}
		}

	}

	/**
	 * @Description
	 * displayCart is used to display cart items
	 */
	public void displayCart() {
		if (cartItems.isEmpty()) {
			System.out.println("cart is empty please add some items");

		} else {
			System.out.println("id     item     quantity   price ");

			for (Map.Entry<String, Integer> cart : cartItems.entrySet()) {
				System.out.println(cart.getKey() + "  " + items.get(cart.getKey()) + "        " + cart.getValue()
						+ "       " + itemsIdPrice.get(cart.getKey()) * cart.getValue());
			}
		}
		inputUserChoice();

	}

	/**
	 * @Description 
	 * changeItemquantity will change the quantity of an item in the shopping cart
	 */
	public void changeItemquantity() {
		Scanner scanner = null;
		try {
			if (cartItems.isEmpty()) {
				System.out.println("cart is empty please add some items");

			} else {
				System.out.println("enter id of the item for which you want to change quantity");
				scanner = new Scanner(System.in);
				String id = scanner.next();

				if (cartItems.containsKey(id)) {
					System.out.println("enter new quantity to replace with old");
					cartItems.put(id, scanner.nextInt());
					System.out.println("cart successfully updated;");

				} else {
					System.out.println("item with the id " + id + " doesn't exist");

				}

			}
			inputUserChoice();
		} catch (Exception e) {

		}

	}

	/**
	 * @Description 
	 * addItem method will add items to the user's cart
	 */
	public void addItem() {
		Scanner scanner = null;
		try {
			System.out.println("if you want to go back press 'back' and 'exit' to close and then press enter");
			System.out.println("please enter the id and quantity of the item you want to add...");
			System.out.println("id: ");
			scanner = new Scanner(System.in);

			String id = scanner.next();
			if ("back".equals(id)) {
				inputUserChoice();
			} else if ("exit".equals(id)) {

				exitFromShoppingMall();

			} else if (cartItems.containsKey(id)) {
				System.out.println("item already exists better you change its quantity.");
				inputUserChoice();
			} else if (items.containsKey(id)) {
				System.out.println("quantity: ");
				try {
					int quantity = scanner.nextInt();
					cartItems.put(id, quantity);
					System.out.println("item added successfully");
					System.out.println();
					inputUserChoice();

				} catch (Exception e) {
					System.out.println("quantity must be an Integer");
				}
			} else {
				System.out.println("incorrect input or id");

				addItem();

			}
		} catch (Exception e) {

		}

	}

	public static void main(String args[]) {
		ShoppingCart shoppingcart = new ShoppingCart();
		shoppingcart.printItems();
		// shoppingcart.inputUserChoice();

	}

}