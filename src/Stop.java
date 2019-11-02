// Each stop represents one city and the planned attractions in that city
public class Stop {
	private String city;						//It contains the name of the city
	private Hotel hotel;						//The hotel chosen
	private Attraction attractions[];			//The list of attractions that are planning to go
	private Food food[];						//The list of food chosen
	private double cost;						//The total cost

	//Constructor
	public Stop(String c, Hotel h, Attraction[] at, Food[] fd, double ct) {
		city = c;
		hotel = h;
		attractions = at;
		food = fd;
		cost = ct;

	}
	//Accessor
	public double getCost() {
		return cost;
	}
	//toString method
	public String toString() {
		String str = "";

		str += "\nCity: " + city + "\n";	//Add the city name
		str += hotel;						//Add the hotel informations

		for (int i = 0; i < attractions.length; i++) { 		//Add the info of attractions
			str += "\n\n\tAttraction #" + (i+1) + ": " + attractions[i].getName() + "\n";
			str += attractions[i];
		}
		
		for (int i = 0; i < food.length; i++) {				//Add the info of foods
			str += "\n\n\tFood #" + (i+1) + ": " + food[i].getName() + "\n";
			str += food[i];
		}
		
		return str;							//Return the string
	}

}