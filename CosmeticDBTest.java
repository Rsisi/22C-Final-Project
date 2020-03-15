
/**
 * MovieDatabase.java
 * @author Renmei Gao
 * @author Alyssa Reyes
 * CIS 22C, Lab 7
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class CosmeticDBTest {
	private final int NUM_COSETICS = 25;
	Hash<Cosmetic> ht = new Hash<>(NUM_COSETICS * 2);
	BST1<Cosmetic> bst = new BST1<>();

	public static void main(String[] args) throws IOException {
		CosmeticDBTest C = new CosmeticDBTest();

		File file = new File("cosmetics.txt");
		Scanner input = new Scanner(file);
		C.populateData(input);

		input = new Scanner(System.in);
		String choice;
		System.out.println("Welcome to Cosmetics Deal Space Station!");
		System.out.println("\nPlease select from one of the following options:\n" + "\n" + "A. Add a Cosmetic\n"
				+ "D. Display all Cosmetics\n" + "R. Remove a Cosmetic\n" + "S. Search for a Cosmetic\n" + "X. Exit");
		System.out.print("\nEnter your choice: ");
		choice = input.nextLine();
		while (!choice.equalsIgnoreCase("X")) {

			if (choice.equalsIgnoreCase("A")) {
				C.adding(input);

			} else if (choice.equalsIgnoreCase("D")) {
				C.displaying(input);

			} else if (choice.equalsIgnoreCase("R")) {
				C.removing(input);

			} else if (choice.equalsIgnoreCase("S")) {
				C.searching(input);

			} else {
				System.out.println("\nInvalid Selection!");
			}

			System.out.println("\nPlease select from one of the following options:\n" + "\n" + "A. Add a Cosmetic\n"
					+ "D. Display all Cosmetics\n" + "R. Remove a Cosmetic\n" + "S. Search for a Cosmetic\n"
					+ "X. Exit");
			System.out.print("\nEnter your choice: ");
			choice = input.nextLine();
		}
		System.out.println("\nGoodbye!");
		input.close();
	}

	public void populateData(Scanner input) throws IOException {
		String category = "";
		String brand = "";
		String name = "";
		double price = 0.0;
		String color = "";
		Cosmetic c;
		while (input.hasNextLine()) {

			category = input.nextLine();
			brand = input.nextLine();
			name = input.nextLine();
			price = input.nextDouble();
			input.nextLine();
			color = input.nextLine();

			c = new Cosmetic(category, brand, name, price, color);
			ht.insert(c);
			bst.insert(c);
		}
	}

	public void adding(Scanner input) {
		System.out.println("\nAdding a cosmetic!\n");
		System.out.print("Enter the category: ");
		String category = input.nextLine();
		System.out.print("Enter the brand: ");
		String brand = input.nextLine();
		System.out.print("Enter the name: ");
		String name = input.nextLine();
		System.out.print("Enter the price: $");
		double price = input.nextDouble();
		System.out.print("Enter the color");
		String color = input.nextLine();
		Cosmetic add = new Cosmetic(category, brand, name, price, color);
		bst.insert(add);
		ht.insert(add);
		System.out.println("\n" + brand + "\'s " + name + " was added!");

	}

	public void displaying(Scanner input) {
		System.out
				.print("\n" + "Please select one of the following options:\n" + "\n" + "S. Sorted\n" + "U. Unsorted\n");
		System.out.print("\nEnter your choice: ");
		String choice = input.nextLine();
		System.out.println("\nDisplaying Cosmetics list:");
		if (choice.equalsIgnoreCase("S")) {
			bst.inOrderPrint();
			System.out.println();
		}
		if (choice.equalsIgnoreCase("U")) {
			System.out.println(ht);

		}
	}

	public void removing(Scanner input) {
		String category = "";
		String brand = "";
		String name = "";
		double price = 0.0;
		String color = "";

		System.out.println("\nRemoving a Cosmetic!\n");
		System.out.print("Enter the brand: ");
		brand = input.nextLine();
		System.out.print("Enter the name: ");
		name = input.nextLine();
		Cosmetic remove = new Cosmetic(category, brand, name, price, color);
		if (ht.search(remove) != -1) {
			bst.remove(remove);
			ht.remove(remove);
			System.out.println("\n" + brand + "\'s " + name + " was removed!");
		} else {
			System.out.println("\nI cannot find " + brand + "\'s " + name + " in the database.");

		}
	}

	public void searching(Scanner input) {
		String category = "";
		String brand = "";
		String name = "";
		double price = 0.0;
		String color = "";

		System.out.println("\nSearching for a Cosmetic!\n");
		System.out.print("Enter the brand: ");
		brand = input.nextLine();
		System.out.print("Enter the name: ");
		name = input.nextLine();
		Cosmetic search = new Cosmetic(category, brand, name, price, color);
		if (bst.search(search) == false) {
			System.out.println("\n" + brand + "\'s " + name + " is not in the database.");

		} else {
			System.out.println("\n" + brand + "\'s " + name + " is in the database.");

		}
	}
}