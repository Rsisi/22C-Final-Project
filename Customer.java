public class Customer {
	private String occasion;
	private String color;
	
	/**
	 * Constructor for the Customer class
	 * 
	 * @param brand the cosmetic brand
	 * @param name the cosmetic name
	 */
	public Customer(String brand, String name) {
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
}
