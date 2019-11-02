public class Hotel {
	private String name;    // name of the hotel
	private double price;   // price of the hotel
	private int starRating; // star rating of the hotel

   // constructor
	public Hotel(String name, double price, int starRating) {
		this.name = name;
		this.price = price;
		this.starRating = starRating;
	} // Hotel method
   
   // accessor
	public String getName() {
		return name;
	} // getName method

	public double getPrice() {
		return price;
	} // getPrice method

	public int getStarRating() {
		return starRating;
	} // getStarRating method

   // mutator
	public void setName(String newName) {
		this.name = newName;
	} // setName method
   
	public void setPrice(double newPrice) {
		this.price = newPrice;
	} // setPrice method
	
   public void setStarRating(int newStarRating) {
		this.starRating = newStarRating;
	} // setStarRating method
   
   // return the information of the hotel
	public String toString() {
		String str = "";
		
		str += "\tHotel: " + name + "\n";
		str += "\tStar Rating: " + starRating + "\n";
		str += "\tPrice: $" + String.format("%.2f", price);
		
		return str;
	} // toString method
} // Hotel class