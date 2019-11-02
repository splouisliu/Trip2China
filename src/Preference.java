public class Preference {
	private static final int DAYS_BETWEEN_COUNTRIES = 3; 	// number of days the client will spend on flying two-way
															            // between his/her country and China
	private static final int MAX_DAYS_PER_STOP = 3;       // maximum number of days the client can spend at each stop
	
	private double money;                  // client's budget in dollars
	private double time;                   // number of days the client wants to spend on the trip
	private String city;                   // name of the city which the client wants to visit
	private boolean ancientAttraction;     // true for the client wants to visit ancient attractions
										            // false for the client does not want not want to visit ancient attractions
	private boolean modernAttraction;      // true for the client wants to visit modern attractions
	                                       // false for the client does not want not want to visit modern attractions
	private boolean naturalAttraction;     // true for the user wants to visit natural attractions
										            // false for the client does not want to visit natural attractions
	private int minimumStarRating;         // the minimum star rating of hotels the client can accept
	private Transportation transportation; // the client's preferred transportation using between cities


	// constructor
	public Preference(double money, double time, String city, boolean ancientAttraction, boolean modernAttraction,
			            boolean naturalAttraction, int minimumStarRating, int transportMethod) {
		this.money = money;
		this.time = time;
		this.city = city;
		this.ancientAttraction = ancientAttraction;
		this.modernAttraction = modernAttraction;
		this.naturalAttraction = naturalAttraction;
		this.minimumStarRating = minimumStarRating;

		if (transportMethod == 1) {
			transportation = new Bus();
		} else if (transportMethod == 2) {
			transportation = new Train();
		} else if (transportMethod == 3) {
			transportation = new Airplane();
		}
	} // Preference method

	// mutator
	public void setMoney(double money) {
		this.money = money;
	} // setMoney method

	public void setTime(double time) {
		this.time = time;
	} // setTime method

	public void setCity(String city) {
		this.city = city;
	} // setCity method

	public void setAncientAttraction(boolean ancientAttraction) {
		this.ancientAttraction = ancientAttraction;
	} // setAncientAttraction method

	public void setModernAttraction(boolean modernAttraction) {
		this.modernAttraction = modernAttraction;
	} // setModernAttraction method

	public void setNaturalAttraction(boolean naturalAttraction) {
		this.naturalAttraction = naturalAttraction;
	} // setNaturalAttraction method

	public void setMinimumStarRating(int minimumStarRating) {
		this.minimumStarRating = minimumStarRating;
	} // setMinimumStarRating method
   
   public void setTransportation (Transportation transportation) {
      this.transportation = transportation;
   } // setTransportation method

	// accessor
	public double getMoney() {
		return (money);
	} // getMoney method

	public double getTime() {
		return (time);
	} // getTime method

	public String getCity() {
		return city;
	} // getCity method

	public boolean getAncientAttraction() {
		return (ancientAttraction);
	} // getAncientAttraction method

	public boolean getModernAttraction() {
		return (modernAttraction);
	} // getModernAttraction method

	public boolean getNaturalAttraction() {
		return (naturalAttraction);
	} // getNaturalAttraction method

	public int getMinimumStarRating() {
		return (minimumStarRating);
	} // getMinimumStarRating method

	public Transportation getTransport() {
		return transportation;
	} // getTransportation method

	// calculate the number of stops the client will visit
	public int numStops() {
		return (int)Math.ceil(((time - DAYS_BETWEEN_COUNTRIES)/(MAX_DAYS_PER_STOP)));
	} // numStops method

	// calculate the maximum amount of money the client will spend per stop
	public double maxMoneyPerStop() {
		return (money /numStops());
	} // maxMoneyPerStop method
   
   // calculate the time the client will spend at each stop
	public double timeSpentEachStop() {
		return ((time - DAYS_BETWEEN_COUNTRIES) / numStops());
	} // timeSpentEachStop method

	// return the information of the user's preference
	public String toString() {
		String attractionPreference = "";      // initialize the client's attraction preference

		if (ancientAttraction) {               // If the client wants to visit ancient attractions,
			attractionPreference += "Ancient "; // add "Ancient" to his/her attraction preference
		}

		if (modernAttraction) {                // If the client wants to visit modern attractions,
			attractionPreference += "Modern ";  // add "Modern" to his/her attraction preference
		}

		if (naturalAttraction) {               // If the client wants to visit natural attractions,
			attractionPreference += "Natural "; // add "Natural" to his/her attraction preference
		}

		return ("Your budget: $" + String.format("%.2f", money) + "\nYour time: " + time + " days\nYour preferred city: " + city
				  + "\nYour attraction preference: " + attractionPreference + "\nThe minimum star rating of hotels: "
				  + minimumStarRating + "\nYour preferred transportation: " + transportation);
	} // toString method
} // Preference class