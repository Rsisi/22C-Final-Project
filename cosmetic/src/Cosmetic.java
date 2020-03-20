
public class Cosmetic implements Comparable <Cosmetic>{
		private String category;
		private String brand;
		private String name;
		private double price;
		private String color;
		private String link;
		private String occasion;
		private String review;
		
		/**
		 * Constructor for the Cosmetic class
		 * @param category the category of a cosmetic
		 * @param brand the brand of a cosmetic
		 * @param name the name of a cosmetic
		 * @param price the price of a cosmetic
		 * @param color the price of a cosmetic
		 * @param link the price of a cosmetic
		 * @param occasion the suitable occasion of a cosmetic
		 * @param review the review of a cosmetic
		 */
		public Cosmetic(String category, String brand, String name, double price,
				String color, String link, String occasion, String review) {
			this.category = category;
			this.brand = brand;
			this.name = name;
			this.price= price;
			this.color = color;
			this.link = link;
			this.occasion = occasion;
			this.review = review;		
		}

		/**mutator**/
		/**
		 * set category to the class 
		 * @param category a String
		 */
		public void setCategory(String category) {
			this.category = category;
		}
		/**
		 * set brand to the class 
		 * @param brand a String
		 */
		public void setBrand(String brand) {
			this.brand = brand;
		}
		/**
		 * set name to the class 
		 * @param name a String
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * set price to the class 
		 * @param price a double
		 */
		public void setPrice(double price) {
			this.price = price;
		}
		/**
		 * set color to the class 
		 * @param color a String
		 */
		public void setColor(String color) {
			this.color = color;
		}
		/**
		 * set link to the class 
		 * @param link a String
		 */
		public void setLink(String link) {
			this.link = link;
		}
		/**
		 * set occasion to the class 
		 * @param occasion a String
		 */
		public void setOccasion(String occasion) {
			this.occasion = occasion;
		}
		/**
		 * set review to the class 
		 * @param review a String
		 */
		public void setReviwe(String review) {
			this.review = review;	
		}

		/**accessor**/
	   /**
	    * Returns the category 
	    * @return category 
	    */
		public String getCategory() {
			return category;
		}
	   /**
	    * Returns the brand
	    * @return brand
	    */
		public String getBrand() {
			return brand;
		}
	   /**
	    * Returns the name
	    * @return name  
	    */
		public String getName() {
			return name;
		}
	   /**
	    * Returns the price
	    * @return price 
	    */
		public double getPrice() {
			return price;
		}
	   /**
	    * Returns the color
	    * @return color
	    */
		public String getColor() {
			return color;
		}
	   /**
	    * Returns the link of cosmetic website 
	    * @return link 
	    */
		public String getLink() {
			return link;
		}
	   /**
	    * Returns the occasion
	    * @return occasion
	    */
		public String getOccasion() {
			return occasion;
		}
	   /**
	    * Returns the review 
	    * @return review 
	    */
		public String getReview() {
			return review;
		}

		/**additional operation**/
		/**
		 * Creates a String of the Cosmetic information
		 */
		@Override public String toString() {
			return "\nCategory: " + category
					+"\nBrand: " + brand
					+"\nName " + name
					+"\nPrice: $"
				+ String.format("%,.2f", price) 
					+"\nColor: " + color
					+"\nReview: " + review
					+"\nLink: " + link;

		}
	
	/**
	 * Returns a consistent hash code for each Cosmetic by summing the Unicode values
	 * of each character in the key Key = brand + name
	 * 
	 * @return the hash code
	 */
	@Override
	public int hashCode() {
		int sum = 0;
		String key = brand + name;
		for (int i = 0; i < key.length(); i++) {
			sum += (int) key.charAt(i);
		}
		return sum;
	}

	/**
	 * Compares two Cosmetic objects to determine ordering Returns 0 if the two
	 * items are equal Return -1 if this Cosmetic's brand comes alphabetically
	 * before the C's brand. Returns 1 if the C's name comes alphabetically before
	 * this cosmetic's name If the two cosmetics' brands are the same, will
	 * differentiate by names (alphabetical comparison)
	 * 
	 * @param C Cosmetic object to compare to this
	 * @return 0 (same cosmetic), -1 (this cosmetic order first) or 1 (C order
	 *         first)
	 */
	@Override
	public int CompareTo(Cosmetic C) {
		if (this.equals(C)) {
			return 0;
		} else {
			if (brand.compareTo(C.brand) < 0) {
				return -1;
			} else if (brand.compareTo(C.brand) > 0) {
				return 1;
			} else {
				if (name.compareTo(C.name) < 0) {
					return -1;
				} else if (name.compareTo(C.name) > 0) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}

	/**
	 * Determines whether two Cosmetic objects are equal by comparing brands and
	 * names
	 * 
	 * @param o the object to compare
	 * @return whether the Cosmetics are equal
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (!(o instanceof Cosmetic)) {
			return false;
		} else {
			Cosmetic C = (Cosmetic) o;
			return brand.equals(C.brand) && name.equals(C.name);
		}
	}

	/**
	 * Compares two Cosmetic objects by the second key - prices.
	 * 
	 * @param c1, c2 Cosmetic object to compare to this
	 * @return 0 (same price), -1 (c1 order first) or 1 (c2 first)
	 */
	@Override
	public int CompareSecondaryKey(Cosmetic C) {
		return Double.compare(price, C.price);
	}
}


