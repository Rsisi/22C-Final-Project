
/**
 * main.java
 * Just for Testing
 * CIS 22C, Final Project
 */
package Station;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import DataStructurePackage.*;
import IOPackage.FileIO;
import UserPackage.User;

public class DealStation {
	private Hash<Cosmetic> ht ;
	private BST<Cosmetic> bstBrand;
	private BSTSecondary<Cosmetic> bstPrice;
	private Hash<User> customerHash;
	private Hash<User> managerHash;
	private FileIO io = new FileIO();

	public static void main(String[] args) throws IOException {
		DealStation C = new DealStation();
		C.populateData();
		Scanner input = new Scanner(System.in);
		String choice = "";
		while (!choice.equalsIgnoreCase("1")) {
			String loginValue = C.loginInterface(input);
			String id[] = loginValue.split(" ");
			if (id[0].equals("2")) {
				C.managerInterface(id[1]);
				System.out.print("\nPress any key to return to the Login page or press 1 to exit: ");
				choice = input.nextLine();
			} else if (id[0].equals("1") || id[0].equals("0")) {
				C.customerInterface(id[1]);
				System.out.print("\nPress any key to return to the Login page or press 1 to exit: ");
				choice = input.nextLine();
			}
		}
		System.out.println("\nSee you next time and goodbye!");
		C.io.wirteToFile();
		input.close();

	}
	
	

	public void populateData() {		
		Scanner input;
		try {
			File file = new File("Cosmetics.txt");
			input = new Scanner(file);
			io.populateData(input);
			bstBrand = io.getBstBrand();
			bstPrice = io.getBstPrice();
			ht = io.getHT();
			file = new File("user.txt");
			input = new Scanner(file);
			io.populateUser(input);
			customerHash = io.getCustomerHash();
			file = new File("manager.txt");
			input = new Scanner(file);
			io.populateManager(input);
			managerHash = io.getManagerHash();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	public void managerInterface(String id) {
		Scanner input = new Scanner(System.in);
		String choice = "";
		System.out.println("");
		printInTable("-", "-", " ", "Deal Station™ Management System", 60, 4);

		while (!choice.equals("6")) {
			System.out.println("\nPlease select from the menu: ");
			System.out.println("1. Review current products" + "\n2. Add a new product" + "\n3. Delete a product" + "\n4. Edit a product"+ "\n5. Add a new manager" + "\n6. Logout");
			System.out.print("\nEnter you chooise:");
			choice = input.nextLine();
			if (choice.equals("1")) {
				displaying(input);
			} else if (choice.equals("2")) {
				adding(input, id);
			} else if (choice.equals("3")) {
				delete(input, id);
			} else if (choice.equals("4")) {
				editing(input, id);
			} else if (choice.equals("5")) {
				User u = registeredInterface(input, 2);
				managerHash.insert(u);
				System.out.println("\nManager " + u.getUserName() + " has Added");
			}

			else if (choice.equals("6")) {
				System.out.println("\nThank you for your work!");
			} else {
				System.out.println("\nInvalid Selection!\n");
			}
		}
	}

	public void customerInterface(String id) {
		Scanner input = new Scanner(System.in);
		String choice = "";
		BST<Cosmetic> wishList = new BST<>();
		System.out.println("");
		printInTable("♡", "♡", " ", "Hi " + id + "! Welcome Back!", 60, 4);
		while (!choice.equals("6")) {
			System.out.println("\nPlease select from the menu: ");
			System.out.println("1. Review products" + "\n2. Search a product" + "\n3. Get the Promotion Code"  + "\n4. Check Current WishList"
					+ "\n5. Printout Current WishList" + "\n6. Eixt");
			System.out.print("\nEnter you chooise:");
			choice = input.nextLine();
			if (choice.equals("1")) {
				displaying(input);
			} else if (choice.equals("2")) {
				wishList = searchInterface(input, wishList);
			} else if (choice.equals("3")) {
				getPromoCode();
			} else if (choice.equals("4")) {
				System.out.println("Hi " + id + ", Here is your current WishList");
				wishList.inOrderPrint();
			} else if (choice.equals("5")) {
				String fileName = io.writeWishListToFile(wishList, id);
				System.out.println("\nYour WishList has already output to file " + fileName + " !");
			} else if (choice.equals("6")) {
				System.out.println("\nThank you for your visit!");
				String fileName = io.writeWishListToFile(wishList, id);
				System.out.println("\nYour WishList has already output to file " + fileName + " !");
			} else {
				System.out.println("\nWrong input! Only digit 1, 2, 3 or 4 to exit\n");
			}
		}

	}

	public BST<Cosmetic> searchInterface(Scanner input, BST<Cosmetic> wishList) {
		String choice = "";
		System.out.println("\nStart to search for a Cosmetic!");

		while (!choice.equals("3")) {
			System.out.println("Please select from the menu: ");
			System.out.println("1. Search by brand and name\n2. Smart Search\n3. Exit");
			System.out.print("\nEnter you choice: ");
			choice = input.nextLine();
			if (choice.equals("1")) {
				searching(input);
			} else if (choice.equals("2")) {
				wishList = smartSearchInterface(input, wishList);
			} else if (choice.equals("3")) {

			} else {
				System.out.println("\nWrong input! Only digit 1, 2 or 3 to exit\n");
			}
		}
		return wishList;
	}

	public BST<Cosmetic> smartSearchInterface(Scanner input, BST<Cosmetic> wishList) {
		String choice = "";

		while (!choice.equalsIgnoreCase("x")) {
			System.out.println("");
			printInTable("┇", "☞", " ", "Welcome to our Smart Search Program!", 60, 6);

			System.out.println(
					"\nMultiple keywords search. You can enter: \n*category + brand(split by a comma\",\")\n*category + occasion(split by a comma\",\")\n"
							+ "*X to Exit");
			System.out.print("\nEnter you choice: ");
			choice = input.nextLine();
			if (!choice.equalsIgnoreCase("x")) {
				String[] choiceArray = choice.split(",");
				System.out.println(choiceArray[0] + " " + choiceArray[1]);
				BST<Cosmetic> ss = bstBrand;
				for (int i = 0; i < choiceArray.length; i++) {
					ss = ss.Contain(choiceArray[i]);
				}
				if (ss == null || ss.isEmpty()) {
					System.out.println("\nSorry, The product you want is not in our database\n");
				} else {
					ss.inOrderPrint();
					System.out.print("\nDo you want add this list to your wishlist(yes or no)");
					choice = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						wishList.insertAll(ss);
					}
				}

				System.out.print("Press any key to keep searching or X to exit: ");
				choice = input.nextLine();
			}

		}
		return wishList;
	}

	public String loginInterface(Scanner input) {
		int pass = -1;
		String choice = "";
		while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
			printInTable("❤", "❤", "◊", "Welcome to Cosmetics Deal Station™!", 60, 6);
			System.out.println("\n1. Login\n2. Register\n3. Continue as a guest");
			System.out.print("\nEnter your choice: ");
			choice = input.nextLine();
			if (choice.equals("1")) {
				while (pass < 0) {
					System.out.println("\nlogin...");
					System.out.print("\nEnter the Member account(or Manager account): ");
					String username = input.nextLine();
					System.out.print("Enter the Password: ");
					String passwd = input.nextLine();
					User u = new User(username, passwd);
					pass = checkuser(u);
					if (pass == 1) {
						return "1 " + u.getUserName();
					} else if (pass == 2) {
						return "2 " + u.getUserName();
					} else {
						System.out.print(
								"\nAccount or Password is incorrect! \nPlease enter any key to try it again or enter \"r\" to regiter a new account,\"f\" to reset your password: ");
						String tryagain = input.nextLine();
						if (tryagain.equalsIgnoreCase("r")) {
							User u1 = registeredInterface(input, 1);
							customerHash.insert(u1);
							pass = 1;
							return "1 " + u1.getUserName();
						}
						else if(tryagain.equals("f")) {
							User u2 = resetPasswd(input);
							if(u2!=null) {
								pass =1;
								return "1 " + u2.getUserName();
								
							}
						}
					}
				}

			} else if (choice.equals("2")) {
				User u = registeredInterface(input, 1);
				customerHash.insert(u);
				return "1 " + u.getUserName();
			} else if (choice.equals("3")) {
				return "0 Guest";
			} else {
				System.out.println("Wrong input! Only digit 1, 2 or 3");
			}

		}
		return "";
		
	}
	
	public User resetPasswd(Scanner input) {
		String choice = "";
		while(!choice.equals("x")) {
			System.out.print("\nEnter your Member account: ");
			String username = input.nextLine();
			System.out.print("Enter your email: ");
			String email = input.nextLine();
			System.out.print("Answer following question to check Security Answer.\nWho is your bestfriend in your childhood?: ");
			String securityAnswer = input.nextLine();
			User temp = new User(username, email, securityAnswer);
			if(customerHash.search(temp)>=0||managerHash.search(temp)>=0) {
				User u = customerHash.searchAndGet(temp);
				if(u!=null) {
					customerHash.remove(temp);
					System.out.print("\nEnter New Password: ");
					u.setpassword(input.nextLine());
					customerHash.insert(u);
					
				}
				else {
					u = managerHash.searchAndGet(temp);
					managerHash.remove(temp);
					System.out.print("\nEnter New Password: ");
					u.setpassword(input.nextLine());
					managerHash.insert(u);
				}
				System.out.println("\nPassword already reset!");
				System.out.println(u);
				return u;
			}else {
				System.out.print("Could Not Find Your Account,Press Any Key Retry or Press \"x\" to Exit: ");
				choice = input.nextLine();
			}
		}
		return null;
	}

	public int checkuser(User u) {
		if (customerHash.compareSearch(u)>=0) {
			return 1;
		} else if (managerHash.compareSearch(u)>=0) {
			return 2;
		} else {
			return -1;
		}
	}

	public User registeredInterface(Scanner input, int i) {
		boolean pass = false;
		String username = "";
		String password = "";
		String password2;
		String email ="";
		String securityAnswer = "";
		if (i == 1) {
			System.out.println("");
			printInTable("▫", "-", " ", "Register to be a Deal Station™ Member! ", 60, 4);
		} else {
			System.out.println("");
			printInTable("||", "=", " ", "Add a Deal Station™ manager account!", 60, 2);
		}

		while (!pass) {
			System.out.print("\nEnter your new username: ");
			username = input.nextLine();
			User tmp = new User(username,"");
			if(customerHash.contain(tmp, username)) {
				System.out.println("\n"+username+" has been used,Please change one or Login!");
			}
			else {
				System.out.print("Create your password: ");
				password = input.nextLine();
				System.out.print("Re-enter the password: ");
				password2 = input.nextLine();
				if (password.equals("") || username.equals("")) {
					System.out.println("\nPassword cannot be empty!\n");
				} else if (password.equals(password2)) {
					pass = true;
				} else {
					System.out.println("\nThe two passwords are different, please try again\n");
				}
				System.out.print("Enter your Email: ");
				email = input.nextLine();
				System.out.print("Answer following question as Security Answer.\nWho is your bestfriend in your childhood?: ");
				securityAnswer = input.nextLine();
			}
			
		}

		User u = new User(username, password,email,securityAnswer);
		return u;

	}

	public void adding(Scanner input, String id) {

		String choice;
		boolean pass = true;
		System.out.println("");
		printInTable("|", "+", " ", "Add a cosmetic!", 60, 2);

		System.out.print("\nEnter the category: ");
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
		while (pass) {
			System.out.print("Add an occasion:");
			String occOption = input.nextLine();
			occ.add(occOption);
			System.out.print("Want add more occasion?(Enter any key to add more or \"x\" for Exit ): ");
			choice = input.nextLine();
			if (choice.equalsIgnoreCase("x")) {
				pass = false;
			}
		}
		System.out.print("Enter the link");
		String link = input.nextLine();
		System.out.print("Enter the number of the review");
		double reviewNum = input.nextDouble();
		input.nextLine();
		String review = reviewNum + "/5 stars";
		Cosmetic add = new Cosmetic(category, brand, name, price, color, link, occ, review);
		bstBrand.insert(add);
		bstPrice.insert(add);
		ht.insert(add);
		System.out.println("\n" + brand + "\'s " + name + " was added!");
		io.writeToLog(id + " had add " + brand + "\'s " + name);

	}

	public void displaying(Scanner input) {
		System.out.print("\n" + "Please select one of the following options:\n" + "\nS. Sorted by brands\n"
				+ "P. Sorted by price\n" + "U. Unsorted\n");
		System.out.print("\nEnter your choice: ");
		String choice = input.nextLine();
		System.out.println("\nDisplaying the Cosmetics list:");
		if (choice.equalsIgnoreCase("S")) {
			bstBrand.inOrderPrint();
			System.out.println();
		}

		if (choice.equalsIgnoreCase("P")) {
			bstPrice.inOrderPrint();
		}
		if (choice.equalsIgnoreCase("U")) {
			System.out.println(ht);

		}
	}

	public void editing(Scanner input, String id) {
		String category = "";
		String brand = "";
		String name = "";
		double price = 0.0;
		String color = "";
		String link = "";
		String review = "";
		String choice = "";
		System.out.println("");
		printInTable("|", "+", " ", "Edit a Cosmetic from the current database", 60, 2);
		System.out.print("Enter the brand to edit: ");
		brand = input.nextLine();
		System.out.print("Enter the name to edit: ");
		name = input.nextLine();
		Cosmetic edit = new Cosmetic(brand, name);
		Cosmetic c = ht.searchAndGet(edit);
		if (c != null) {
			bstBrand.remove(c);
			bstPrice.remove(c);
			ht.remove(c);
			System.out.println(c);

			while (!choice.equalsIgnoreCase("x")) {
				System.out.print("Which part do you want to edit(e.g. name; enter x to Exit): ");
				choice = input.nextLine();
				if (choice.equalsIgnoreCase("category")) {
					System.out.print("Enter the new category: ");
					category = input.nextLine();
					System.out.print(
							"Confirm change the category from " + c.getCategory() + " to " + category + ". (yes or no)");
					choice = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						io.writeToLog(id + " changed " + c.getBrand() + "'s " + c.getName() + " the category from "
								+ c.getCategory() + " to " + category);
						c.setCategory(category);
					}
				} else if (choice.equalsIgnoreCase("brand")) {
					System.out.print("Enter new brand: ");
					brand = input.nextLine();
					System.out.print("Confirm change the brand from " + c.getBrand() + " to " + brand + ". (yes or no)");
					choice = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						io.writeToLog(id + " changed " + c.getBrand() + "'s " + c.getName() + " the brand from " + c.getBrand()
								+ " to " + brand);
						c.setBrand(brand);
					}
				} else if (choice.equalsIgnoreCase("name")) {
					System.out.print("Enter new name: ");
					name = input.nextLine();
					System.out.print("Confirm change the name from " + c.getName() + " to " + name + ". (yes or no)");
					choice = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						io.writeToLog(id + " changed " + c.getBrand() + "'s " + c.getName() + " the name from " + c.getName()
								+ " to " + name);
						c.setName(name);
					}
				} else if (choice.equalsIgnoreCase("price")) {
					System.out.print("Enter new price: ");
					price = input.nextDouble();
					input.nextLine();
					System.out.print("Confirm change the price from $" + c.getPrice() + " to $" + price + ". (yes or no)");
					choice = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						io.writeToLog(id + " changed " + c.getBrand() + "'s " + c.getName() + " the price from $"
								+ c.getPrice() + " to $" + price);
						c.setPrice(price);
					}
				} else if (choice.equalsIgnoreCase("color")) {
					System.out.print("Enter new color: ");
					color = input.nextLine();
					System.out.print("Confirm change the color from " + c.getColor() + " to " + color + ". (yes or no)");
					color = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						io.writeToLog(id + " changed " + c.getBrand() + "'s " + c.getName() + " the color from " + c.getColor()
								+ " to " + color);
						c.setColor(color);
					}
				} else if (choice.equalsIgnoreCase("link")) {
					System.out.print("Enter new link: ");
					link = input.nextLine();
					System.out.print("Confirm change the link from " + c.getLink() + "\nto:" + link + ". (yes or no)");
					link = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						io.writeToLog(id + " changed " + c.getBrand() + "'s " + c.getName() + " the link from " + c.getLink()
								+ "\nto\n" + link);
						c.setLink(link);
					}
				} else if (choice.equalsIgnoreCase("review")) {
					System.out.print("Enter the new link: ");
					review = input.nextLine();
					System.out
							.print("Confirm change the review from " + c.getReview() + "\nto\n" + review + ". (yes or no)");
					review = input.nextLine();
					if (choice.equalsIgnoreCase("yes")) {
						io.writeToLog(id + " changed " + c.getBrand() + "'s " + c.getName() + " the review from "
								+ c.getReview() + "\nto\n" + review);
						c.setReview(review);
					}
				} else if (choice.equalsIgnoreCase("occasion") || choice.equalsIgnoreCase("occ")) {
					while (!choice.equalsIgnoreCase("x")) {
						ArrayList<String> occ = c.getOccasion();
						System.out.println("The current Occasion as following:");
						for (int i = 0; i < occ.size(); i++) {
							System.out.println((i + 1) +". "+ occ.get(i));
						}
						System.out.println("\nIf want add enter \"add\", delete enter \"del\" \n x for exit");
						System.out.print("Enter your choice: ");
						choice = input.nextLine();
						if (choice.equalsIgnoreCase("add")) {
							System.out.print("Enter an occasion: ");
							String occadd = input.nextLine();
							System.out.print("Confirm add " + occadd + " to occasion. (yes or no)");
							choice = input.nextLine();
							if (choice.equalsIgnoreCase("yes")) {
								c.addOccasion(occadd);
								System.out.println("\n" + occadd + " has been added.");
								io.writeToLog(id + " had added " + occadd + " to occasion");
							}

						} else if (choice.equalsIgnoreCase("del")) {
							System.out.print("Which occastion you want to delete(Enter digital 1 - "+occ.size()+"): ");
							int index = input.nextInt()-1;
							input.nextLine();
							System.out.print("Confirm delete " + c.getOccasion(index) + " from occasion. (yes or no)");
							choice = input.nextLine();
							if (choice.equalsIgnoreCase("yes")) {
								System.out.println("\n" + c.getOccasion(index) + " has been deleted.");
								io.writeToLog(id + " had deleted " + c.getOccasion(index) + " to occasion");
								c.delOccasion(index);	
							}
						}else if(choice.equalsIgnoreCase("x")){
							
						}
						else {
							System.out.println("\nInvalid Selection!\n");
						}
					}
				}
				else if (choice.equalsIgnoreCase("x")) {

				}

				else {
					System.out.println("\nError! Please enter again.");
				}
			}
			bstBrand.insert(c);
			bstPrice.insert(c);
			ht.insert(c);
		} else {
			System.out.println("\n" + brand + "\'s " + name + " is not in the database.");
		}

	}

	public void delete(Scanner input, String id) {
		String brand = "";
		String name = "";

		System.out.println("");
		printInTable("|", "+", " ", "Delete a Cosmetic from the database!", 60, 2);
		System.out.print("Enter the brand: ");
		brand = input.nextLine();
		System.out.print("Enter the name: ");
		name = input.nextLine();
		Cosmetic search = new Cosmetic(brand, name);
		if (bstBrand.search(search) == false) {
			System.out.println("\n" + brand + "\'s " + name + " is not in the database.");

		} else {
			bstBrand.remove(search);
			bstPrice.remove(search);
			ht.remove(search);
			System.out.println("\n" + brand + "\'s " + name + " has removed from the database.");
			io.writeToLog(id + " has removed " + brand + "\'s " + name);
		}

	}

	public void searching(Scanner input) {
		String brand = "";
		String name = "";

		System.out.println("");
		printInTable("|", "+", " ", "Search for a Cosmetic by brand and name!", 60, 2);
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
		System.out.println("\nCongradulations! You got your personal Promotion Code:\n");
		printInTable("||", "=", "$", "" + code[i], 60, 6);

		System.out
				.println("\nYou can use the code when you buying your love products on the official website and get "
						+ code[i].substring(code[i].length() - 2) + "% off! Enjoy!\n");

	}


	
	public String formatCenter(String s, int i) {
		i = (i - s.length()) / 2;
		String s1 = "";
		for (int j = 0; j < i; j++) {
			s1 += " ";
		}
		if (s.length() % 2 == 0) {
			return s1 + s + s1;
		} else {
			return s1 + s + s1 + " ";
		}
	}

	public void printInTable(String s1, String s2, String s3, String title, int c, int r) {
		for (int i = 0; i < r; i++) {
			System.out.print(s1);
			for (int j = 0; j < c; j++) {
				if (i == 0 || i == r - 1) {
					System.out.print(s2);
				} else if (i > 1 && i < r - 2) {
					System.out.print(" ");
				} else {
					System.out.print(s3);
				}
			}
			if (i == r / 2 - 1) {
				System.out.print(s1);
				System.out.println("\n" + s1 + "" + formatCenter(title, c) + "" + s1);
			} else {
				System.out.print(s1 + "\n");
			}
		}
	}
}