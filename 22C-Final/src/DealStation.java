
/**
 * main.java
 * Just for Testing
 * CIS 22C, Final Project
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DealStation {
	private final int NUM_COSETICS = 25;
	Hash<Cosmetic> ht = new Hash<>(NUM_COSETICS * 2);
	BST<Cosmetic> bst1 = new BST<>();
	BSTSecondary<Cosmetic> bst2 = new BSTSecondary<>();
	Hash<user> userHash = new Hash<>(100);
	Hash<user> managerHash = new Hash<>(100);

	public static void main(String[] args) throws IOException {
		DealStation C = new DealStation();

		File file = new File("Cosmetics.txt");
		Scanner input = new Scanner(file);
		C.populateData(input);
		file = new File("user.txt");
		input = new Scanner(file);
		C.populateUser(input);
		file = new File("manager.txt");
		input = new Scanner(file);
		C.populateManager(input);

		input = new Scanner(System.in);
		String choice = "";
		while (!choice.equalsIgnoreCase("1")) {
			String id = C.loginInterface(input);
			if (id.contains("Manager")) {
				C.managerInterface(id);
				System.out.print("\nPress any key to return to the Login page or press 1 to exit: ");
				choice = input.nextLine();
			} else if (id.contains("User") || id.equals("Guest")) {
				C.userInterface(id);
				System.out.print("\nPress any key to return to the Login page or press 1 to exit: ");
				choice = input.nextLine();
			}
		}
		System.out.println("\nSee you next time and goodbye!");
		C.wirteToFile();
		input.close();

	}

	public void populateData(Scanner input) throws IOException {
		String category = "";
		String brand = "";
		String name = "";
		double price = 0.0;
		String color = "";
		String link = "";
		String review = "";
		int occNum = 0;
		ArrayList<String> occ;
		Cosmetic c;
		while (input.hasNextLine()) {
			category = input.nextLine();
			brand = input.nextLine();
			name = input.nextLine();
			price = input.nextDouble();
			input.nextLine();
			color = input.nextLine();
			occNum = input.nextInt();
			input.nextLine();
			occ = new ArrayList<>();
			for (int i = 0; i < occNum; i++) {
				occ.add(input.nextLine().toLowerCase());
			}
			review = input.nextLine();
			link = input.nextLine();
			if (input.hasNextLine()) {
				input.nextLine();
			}

			c = new Cosmetic(category, brand, name, price, color, link, occ, review);
			ht.insert(c);
			bst1.insert(c);
			bst2.insert(c);
		}
	}

	public void populateUser(Scanner input) {
		user u;

		while (input.hasNextLine()) {
			String username = input.nextLine();
			String password = input.nextLine();
			if (input.hasNextLine()) {
				input.nextLine();
			}

			u = new user(username, password);
			userHash.insert(u);
		}

	}

	public void populateManager(Scanner input) {
		user u;

		while (input.hasNextLine()) {
			String username = input.nextLine();
			String password = input.nextLine();
			if (input.hasNextLine()) {
				input.nextLine();
			}
			u = new user(username, password);
			managerHash.insert(u);
		}

	}

	public void managerInterface(String id) {
		Scanner input = new Scanner(System.in);
		String choice = "";
		System.out.println("\n*** Deal Station Management System*** \n" + "\nWelcome! " + id);

		while (!choice.equals("6")) {
			System.out.println("\nPlease select from the menu: ");
			System.out.println("1. Review current products" + "\n2. Add a new product" + "\n3. Delete a product"
					+ "\n4. Edit a product" + "\n5. Add a new manager" + "\n6. Logout");
			System.out.print("\nEnter you choice: ");
			choice = input.nextLine();
			if (choice.equals("1")) {
				displaying(input);
			} else if (choice.equals("2")) {
				adding(input);
			} else if (choice.equals("3")) {
				delete(input);
			} else if (choice.equals("4")) {
				editing(input);
			} else if (choice.equals("5")) {
				user u = registeredInterface(input, 2);
				managerHash.insert(u);
				System.out.println("\nManager " + u.getUserName() + " has Added!");
			}

			else if (choice.equals("6")) {
				System.out.println("\nThank you and see you next time!");
			} else {
				System.out.println("\nInvalid Selection!\n");
			}
		}
	}

	public void userInterface(String id) {
		Scanner input = new Scanner(System.in);
		String choice = "";
		System.out.println("\nHi " + id.substring(5) + "! Welcome to Cosmetics Deal Station!\n");
		while (!choice.equals("4")) {
			System.out.println("Please select from the menu: ");
			System.out.println(
					"1. Review products" + "\n2. Search a product" + "\n3. Get the Promotion Code" + "\n4. Logout");
			System.out.print("\nEnter you choice: ");
			choice = input.nextLine();
			if (choice.equals("1")) {
				displaying(input);
			} else if (choice.equals("2")) {
				searchInterface(input);
			} else if (choice.equals("3")) {
				getPromoCode();
			} else if (choice.equals("4")) {
				System.out.println("\nThank you for your visit!");
			} else {
				System.out.println("\nWrong input! Only digit 1, 2, 3 or 4 to exit.");
			}
		}

	}

	public void searchInterface(Scanner input) {
		String choice = "";
		System.out.println("\nStart to search for a Cosmetic!");

		while (!choice.equals("3")) {
			System.out.println("\nPlease select from the menu: ");
			System.out.println("1. Search by brand and name\n2. Smart Search\n3. Exit");
			System.out.print("\nEnter you choice: ");
			choice = input.nextLine();
			if (choice.equals("1")) {
				searching(input);
			} else if (choice.equals("2")) {
				userSmartSearchInterface(input);
			} else if (choice.equals("3")) {

			} else {
				System.out.println("\nWrong input! Only digit 1, 2 or 3 to exit\n");
			}
		}
	}

	public void userSmartSearchInterface(Scanner input) {
		String choice = "";
		while (!choice.equalsIgnoreCase("x")) {
			System.out.println("\nWelcome to our Smart Search Program!\n"
					+ "\nMultiple keywords search. You can enter: \n*category + brand(split by a comma\",\")\n*category + occasion(split by a comma\",\")\n"
					+ "*X to Exit");
			System.out.print("\nEnter you choice: ");
			choice = input.nextLine();
			if (!choice.equalsIgnoreCase("x")) {
				String[] choiceArray = choice.split(",");
				BST<Cosmetic> ss = bst1;
				for (int i = 0; i < choiceArray.length; i++) {
					ss = ss.Contain(choiceArray[i]);
				}
				if (ss == null || ss.isEmpty()) {
					System.out.println("\nSorry, the product you want is not in our database.\n");
				} else {
					ss.inOrderPrint();
				}
			}

			System.out.print("Press any key to keep searching or X to exit: ");
			choice = input.nextLine();

		}
	}

	public String center(String s, int i) {
		i = (i - s.length()) / 2;
		String s1 = "";
		for (int j = 0; j < i; j++) {
			s1 += " ";
		}
		return s1 + s + s1;
	}

	public String loginInterface(Scanner input) {
		int pass = -1;
		String choice = "";
		while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
			for (int i = 0; i < 6; i++) {
				System.out.print("❤");
				for (int j = 0; j < 60; j++) {
					if (i > 0 && i < 5) {
						System.out.print(" ");
					} else {
						System.out.print("☆");
					}
				}
				if (i == 2) {
					System.out.print("❤");
					System.out.println("\n❤" + center("Welcome to Cosmetics Deal Station!", 60) + "❤");
				} else {
					System.out.print("❤\n");
				}
			}
			System.out.println("\n1. Login\n2. Register\n3. Continue as a guest");
			System.out.print("\nEnter your choice: ");
			choice = input.nextLine();
			if (choice.equals("1")) {
				while (pass < 0) {
					System.out.println("\nLogin...");
					System.out.print("\nEnter the Member account(or Manager account): ");
					String username = input.nextLine();
					System.out.print("Enter the Password: ");
					String passwd = input.nextLine();
					user u = new user(username, passwd);
					pass = checkuser(u);
					if (pass == 1) {
						return "User " + u.getUserName();
					} else if (pass == 2) {
						return "Manager " + u.getUserName();
					} else {
						System.out.print(
								"\nAccount or Password is incorrect! \nPlease enter any key to try it again or enter \"r\" to regiter a new account: ");
						String tryagain = input.nextLine();
						if (tryagain.equalsIgnoreCase("r")) {
							user u1 = registeredInterface(input, 1);
							userHash.insert(u1);
							pass = 1;
							return "User " + u1.getUserName();
						}
					}
				}

			} else if (choice.equals("2")) {
				user u = registeredInterface(input, 1);
				userHash.insert(u);
				return "User " + u.getUserName();
			} else if (choice.equals("3")) {
				return "Guest";
			} else {
				System.out.println("Wrong input! Only digit 1, 2 or 3");
			}

		}
		return "";
	}

	public int checkuser(user u) {
		if (userHash.check(u)) {
			return 1;
		} else if (managerHash.check(u)) {
			return 2;
		} else {
			return -1;
		}
	}

	public user registeredInterface(Scanner input, int i) {
		boolean pass = false;
		String username = "";
		String password = "";
		String password2;
		if (i == 1) {
			System.out.println("\nWelcome to Deal Station!");
		} else {
			System.out.println("\nWelcome to Deal Station! Now you can add a manager account!");
		}

		while (!pass) {
			System.out.print("\nEnter your new username: ");
			username = input.nextLine();
			System.out.print("Create your password: ");
			password = input.nextLine();
			System.out.print("Re-enter the password: ");
			password2 = input.nextLine();
			if (password.equals("") || username.equals("")) {
				System.out.println("\nPassword cannot be empty!\n");
			} else if (password.equals(password2)) {
				pass = true;
			} else {
				System.out.println("\nThe two passwords are different, please try it again!");
			}
		}

		user u = new user(username, password);
		return u;

	}

	public void adding(Scanner input) {
		String link = "";
		String review = "";
		System.out.println("\nAdding a cosmetic!\n");
		System.out.print("Enter the category: ");
		String category = input.nextLine();
		System.out.print("Enter the brand: ");
		String brand = input.nextLine();
		System.out.print("Enter the name: ");
		String name = input.nextLine();
		System.out.print("Enter the price: $");
		double price = input.nextDouble();
		input.nextLine();
		System.out.print("Enter the color");
		String color = input.nextLine();
		ArrayList<String> occ = new ArrayList<>();
		Cosmetic add = new Cosmetic(category, brand, name, price, color, link, occ, review);
		bst1.insert(add);
		bst2.insert(add);
		ht.insert(add);
		System.out.println("\n" + brand + "\'s " + name + " was added!");

	}

	public void displaying(Scanner input) {
		System.out.print("\n" + "Please select one of the following options:\n" + "\nS. Sorted by brands\n"
				+ "P. Sorted by price\n" + "U. Unsorted\n");
		System.out.print("\nEnter your choice: ");
		String choice = input.nextLine();
		System.out.println("\nDisplaying the Cosmetics list:");
		if (choice.equalsIgnoreCase("S")) {
			bst1.inOrderPrint();
			System.out.println();
		}

		if (choice.equalsIgnoreCase("P")) {
			bst2.inOrderPrint();
		}
		if (choice.equalsIgnoreCase("U")) {
			System.out.println(ht);

		}
	}

	public void removing(Scanner input) {
		String brand = "";
		String name = "";

		System.out.println("\nRemoving a Cosmetic!\n");
		System.out.print("Enter the brand: ");
		brand = input.nextLine();
		System.out.print("Enter the name: ");
		name = input.nextLine();
		Cosmetic remove = new Cosmetic(brand, name);
		if (bst1.search(remove) == true) {
			bst1.remove(remove);
			bst2.remove(remove);
			ht.remove(remove);
			System.out.println("\n" + brand + "\'s " + name + " was removed!");
		} else {
			System.out.println("\nI cannot find " + brand + "\'s " + name + " in the database.");

		}
	}

	public void editing(Scanner input) {
		String category = "";
		String brand = "";
		String name = "";
		double price = 0.0;
		String color = "";
		String link = "";
		String review = "";
		String choice = "";

		System.out.println("\nEdit a Cosmetic from the current database: \n");
		System.out.print("Enter the brand to edit: ");
		brand = input.nextLine();
		System.out.print("Enter the name to edit: ");
		name = input.nextLine();
		Cosmetic edit = new Cosmetic(brand, name);
		Cosmetic c = ht.searchAndGet(edit);
		if (c != null) {
			bst1.remove(c);
			bst2.remove(c);
			ht.remove(c);
			System.out.println(c);

			while (!choice.equalsIgnoreCase("x")) {
				System.out.print("Which part do you want to edit(e.g. name; enter x to Exit): ");
				choice = input.nextLine();
				if (choice.equalsIgnoreCase("category")) {
					System.out.print("Enter the new category: ");
					category = input.nextLine();
					System.out.print("Confirm change the category from " + c.getCategory() + " to " + category
							+ ". (yes or no): ");
					choice = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						c.setCategory(category);
					}
				} else if (choice.equalsIgnoreCase("brand")) {
					System.out.print("Enter the new brand: ");
					brand = input.nextLine();
					System.out.print(
							"Confirm change the brand from " + c.getBrand() + " to " + brand + ". (yes or no): ");
					choice = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						c.setBrand(brand);
					}
				} else if (choice.equalsIgnoreCase("name")) {
					System.out.print("Enter the new name: ");
					name = input.nextLine();
					System.out.print("Confirm change the name from " + c.getName() + " to " + name + ". (yes or no): ");
					choice = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						c.setName(name);
					}
				} else if (choice.equalsIgnoreCase("price")) {
					System.out.print("Enter the new price: ");
					price = input.nextDouble();
					input.nextLine();
					System.out.print(
							"Confirm change the price from " + c.getPrice() + " to " + price + ". (yes or no): ");
					choice = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						c.setPrice(price);
					}
				} else if (choice.equalsIgnoreCase("color")) {
					System.out.print("Enter the new color: ");
					color = input.nextLine();
					System.out.print(
							"Confirm change the color from " + c.getColor() + " to " + color + ". (yes or no): ");
					color = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						c.setColor(color);
					}
				} else if (choice.equalsIgnoreCase("link")) {
					System.out.print("Enter the new link: ");
					link = input.nextLine();
					System.out.print(
							"Confirm change the link from \n" + c.getLink() + "\nto: " + link + ". (yes or no): ");
					link = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						c.setLink(link);
					}
				} else if (choice.equalsIgnoreCase("review")) {
					System.out.print("Enter the new link: ");
					review = input.nextLine();
					System.out.print(
							"Confirm change the review from " + c.getReview() + "\nto\n" + review + ". (yes or no): ");
					review = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						c.setReview(review);
					}
				} else {
					System.out.println("\nError! Please enter again.");
				}
			}
			bst1.insert(c);
			bst2.insert(c);
			ht.insert(c);
		} else {
			System.out.println("\n" + brand + "\'s " + name + " is not in the database.");
		}

	}

	public void delete(Scanner input) {
		String brand = "";
		String name = "";

		System.out.println("\nSearching for a Cosmetic!\n");
		System.out.print("Enter the brand: ");
		brand = input.nextLine();
		System.out.print("Enter the name: ");
		name = input.nextLine();
		Cosmetic search = new Cosmetic(brand, name);
		if (bst1.search(search) == false) {
			System.out.println("\n" + brand + "\'s " + name + " is not in the database.");

		} else {
			bst1.remove(search);
			bst2.remove(search);
			ht.remove(search);
			System.out.println("\n" + brand + "\'s " + name + " has removed from the database.");
		}

	}

	public void searching(Scanner input) {
		String brand = "";
		String name = "";

		System.out.println("\nSearching for a Cosmetic by brand and name!\n");
		System.out.print("Enter the brand: ");
		brand = input.nextLine();
		System.out.print("Enter the name: ");
		name = input.nextLine();
		Cosmetic search = new Cosmetic(brand, name);
		if (ht.search(search) < 0) {
			System.out.println("\n" + brand + "\'s " + name + " is not in the database.");

		} else {
			System.out.println(ht.searchAndGet(search));

		}
	}

	public void getPromoCode() {
		String[] code = { "BEAUTY25", "SPRING20", "LUCKY15", "AWESOME10" };
		Random r = new Random();
		int i = r.nextInt(code.length);
		System.out.println("\nCongradulations! You got your personal Promotion Code:\n\n" + code[i]
				+ "\n\nYou can use the code when you buying your love products on the official website and get "
				+ code[i].substring(code[i].length() - 2) + "% off! Enjoy!\n");

	}

	public void wirteToFile() {
		File file = new File("cosmetics.txt");
		PrintWriter p;
		try {
			p = new PrintWriter(file);
			bst1.writeToFile(p);
			p.close();
			file = new File("user.txt");
			p = new PrintWriter(file);
			userHash.writeToFile(p);
			p.close();
			file = new File("manager.txt");
			p = new PrintWriter(file);
			managerHash.writeToFile(p);
			p.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
