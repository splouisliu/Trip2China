public class Train extends Transportation {
	public static final double PRICE = 10;// price of train is 10 dollars per unit

	// calculate the train cost between cities
	public double cost(double distance) {
		return PRICE * distance;
	} // cost method

	// return the information of the train
	public String toString() {
		return "Train";
	} // toString method
} // Train class