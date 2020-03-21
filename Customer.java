public class Customer {
	private String occasion;
	private String color;
	
	/**
	 * Constructor for the Customer class
	 * @param brand the cosmetic occasion
	 * @param name the cosmetic color
	 */
	public Customer(String occasion, String color) {
		this.occasion = occasion;
		this.color = color;
	}
	
	public void search(Hash<Cosmetic> hash, Cosmetic cos) {
		int index = hash.search(cos);
		if(index != -1) {
   			System.out.println("\n" + cos.getBrand() + " " + cos.getName() +" is in the database!\n");  		
   		} else {
   			System.out.println("\n" + cos.getBrand() + " " + cos.getName() + " is not in the database.\n");
   		}
	}
	
	public void displayAll(Hash<Cosmetic> hash) {
		System.out.println(hash);
	}
	
	public void displayByPrice(BST<Cosmetic> bst2) {
		bst2.inOrderPrint();
		System.out.println();
	}
}
