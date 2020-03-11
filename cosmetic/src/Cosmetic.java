
public class Cosmetic {
		private String category;
		private String brand;
		private String name;
		private double price;
		private String color;
		private String link;
		private String occasion;
		private String review;

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
		public void setCategory(String category) {
			this.category = category;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public void setOccasion(String occasion) {
			this.occasion = occasion;
		}
		public void setReviwe(String review) {
			this.review = review;	
		}

		/**accessor**/
		public String getCategory() {
			return category;
		}
		public String getBrand() {
			return brand;
		}
		public String getName() {
			return name;
		}
		public double getPrice() {
			return price;
		}
		public String getColor() {
			return color;
		}
		public String getLink() {
			return link;
		}
		public String getOccasion() {
			return occasion;
		}
		public String getReview() {
			return review;
		}

		/**additional operation**/
		@Override public String toString() {
			return "\nCategory: " + category
					+"\nBrand: " + brand
					+"\nName " + name
					+"\nPrice: " + price
					+"\nColor: " + color
					+"\nReview: " + review
					+"\nLink: " + link;

		}

}


