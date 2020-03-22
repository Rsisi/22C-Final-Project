package IOPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import UserPackage.User;
import DataStructurePackage.*;

public class FileIO {
	private final int NUM_COSETICS = 25;
	private Hash<Cosmetic> ht = new Hash<>(NUM_COSETICS * 2);
	private BST<Cosmetic> bstBrand = new BST<>();
	private BSTSecondary<Cosmetic> bstPrice = new BSTSecondary<>();
	private Hash<User> customerHash = new Hash<>(100);
	private Hash<User> managerHash = new Hash<>(100);

	public BST<Cosmetic> getBstBrand(){
		return bstBrand;
	}
	
	public BSTSecondary<Cosmetic> getBstPrice(){
		return bstPrice;
	}
	
	public Hash<Cosmetic> getHT(){
		return ht;
	}
	
	public Hash<User> getCustomerHash(){
		return customerHash;
	}
	
	public Hash<User> getManagerHash(){
		return managerHash;
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
			bstBrand.insert(c);
			bstPrice.insert(c);
		}
	}

	public void populateUser(Scanner input) {
		User u;

		while (input.hasNextLine()) {
			String username = input.nextLine();
			String password = input.nextLine();
			String email = input.nextLine();
			String sa = input.nextLine();
			if (input.hasNextLine()) {
				input.nextLine();
			}

			u = new User(username, password,email,sa);
			customerHash.insert(u);
		}

	}

	public void populateManager(Scanner input) {
		User u;

		while (input.hasNextLine()) {
			String username = input.nextLine();
			String password = input.nextLine();
			String email = input.nextLine();
			String sa = input.nextLine();
			if (input.hasNextLine()) {
				input.nextLine();
			}
			u = new User(username, password,email,sa);
			managerHash.insert(u);
		}

	}
	
	public String writeWishListToFile(BST<Cosmetic> wishList, String id) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = dateFormat.format(calendar.getTime());
		String fileName = id + "WishListat" + time + ".txt";
		File file = new File(fileName);
		try {
			PrintWriter p = new PrintWriter(file);
			wishList.writeToFile(p);
			p.close();
		} catch (IOException e) {
			e.toString();
		}
		return fileName;
	}

	public void writeToLog(String s) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String time = dateFormat.format(calendar.getTime());
		File file = new File("ManagerLog.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file, true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		PrintWriter p = new PrintWriter(fw);
		p.println(s + " at " + time);
		p.close();
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void wirteToFile() {
		File file = new File("cosmetics.txt");
		PrintWriter p;
		try {
			p = new PrintWriter(file);
			bstBrand.writeToFile(p);
			p.close();
			file = new File("user.txt");
			p = new PrintWriter(file);
			customerHash.writeToFile(p);
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
