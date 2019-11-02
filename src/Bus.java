public class Bus extends Transportation {
	private static final double PRICE = 3; // price of bus is 3 dollars per unit

	// calculate the bus cost between cities
	public double cost(double distance) {
		return PRICE * distance;
	} // cost method

	// return the information of bus
	public String toString() {
		return "Bus";
	} // toString method
} // Bus class