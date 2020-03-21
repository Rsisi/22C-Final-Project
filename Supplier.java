
public class Supplier {
	private String brand;
	
	/**
	 * Constructor for the Supplier class
	 * @param brand the cosmetic brand
	 */
	public Supplier(String brand) {
		this.brand = brand;
	}
	     
	public void search(Hash<Cosmetic> hash, Cosmetic cos) {
		int index = hash.search(cos);
		if(index != -1) {
   			System.out.println("\n" + cos.getBrand() + " " + cos.getName() +" is in the database!\n");  		
   		} else {
   			System.out.println("\n" + cos.getBrand() + " " + cos.getName() + " is not in the database.\n");
   		}
	}
	
	public void add(Hash<Cosmetic> hash, BST<Cosmetic> bst1, BST<Cosmetic> bst2, Cosmetic cos) {
		bst1.insert(cos);
		bst2.insert(cos);
		hash.insert(cos);
	}
	
	public void delete(Hash<Cosmetic> hash, BST<Cosmetic> bst1, BST<Cosmetic> bst2, Cosmetic cos) {
		int index = hash.search(cos);
		if(index == -1) {
   			System.out.println("\n" + cos.getBrand() + " " + cos.getName() +" is not in the database!\n");  		
   		} else {
   			hash.remove(cos);
   			bst1.remove(cos);
   			bst2.remove(cos);
   		}
	}
	
	public void displayByBrand(BST<Cosmetic> bst1) {
		bst1.inOrderPrint();
		System.out.println();
	}
	
}
