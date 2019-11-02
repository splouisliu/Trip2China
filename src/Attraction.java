public class Attraction {
	private String name; // name of the attraction
	private double price; // price of the attraction
	private double timeTaken; // tour time in this attraction
	private double rating; // rating of the attraction
	private boolean ancient; // true for the attraction is an ancient attraction
								// false for the attraction is not an ancient attraction
	private boolean modern; // true for the attraction is a modern attraction
							// false for the attraction is not a modern attraction
	private boolean natural; // true for the attraction is a natural attraction
								// false for the attraction is not a natural attraction
	private boolean chosen; // indicate whether the attraction is chosen or not
							// true for the attraction is chosen
							// false for the attraction is not chosen

	// constructor
	public Attraction(String name, double timeTaken, double price, double rating, boolean ancient, boolean modern,
			boolean natural) {
		this.name = name;
		this.price = price;
		this.timeTaken = timeTaken;
		this.rating = rating;
		this.ancient = ancient;
		this.modern = modern;
		this.natural = natural;
	} // Attraction method

	// mutator
	public String getName() {
		return name;
	} // getName method

	public double getPrice() {
		return price;
	} // getPrice method

	public double getTimeTaken() {
		return timeTaken;
	} // getTimeTaken method

	public double getRating() {
		return rating;
	} // getRating method

	public boolean getAncient() {
		return ancient;
	} // getAncient method

	public boolean getModern() {
		return modern;
	} // getModern method

	public boolean getNatural() {
		return natural;
	} // getNatural method

	public boolean getChosen() {
		return chosen;
	} // getChosen method

	// accessor
	public void setName(String newName) {
		name = newName;
	} // setName method

	public void setPrice(double newPrice) {
		price = newPrice;
	} // setPrice method

	public void setTimeTaken(double newTimeTaken) {
		timeTaken = newTimeTaken;
	} // setTimeTaken method

	public void setRating(double newRating) {
		rating = newRating;
	} // setRating method

	public void setAncient(boolean newAncient) {
		ancient = newAncient;
	} // setAncient method

	public void setModern(boolean newModern) {
		modern = newModern;
	} // setModern method

	public void setNatural(boolean newNatural) {
		natural = newNatural;
	} // setNatural method

	public void setChosen(boolean newChosen) {
		chosen = newChosen;
	} // setChosen method

	// return the information of the attraction
	public String toString() {
		String str = "";
		str += "\tDuration: " + timeTaken + " days\n";
		str += "\tPrice: $" + String.format("%.2f", price);

		return str;
	} // toString method
} // Attraction class