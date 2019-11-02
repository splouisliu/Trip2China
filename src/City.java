import java.io.*;

public class City {
	private String name; 				// name of the city
	private Hotel[] hotels; 			// the list of hotels in the city
	private Attraction[] attractions; 	// the list of attractions in the city
	private Food[] food; 				// the list of local food in the city
	private Coordinate location; 		// the location of the city
	private int numAttractions; 		// number of attractions in the list
	private int numHotels; 				// number of hotels in the list
	private int numFood; 				// number of local food in the list
	private boolean chosen; 			// indicate whether the city is chosen or not
										// true for the city is chosen
										// false for the city is not chosen

	// constructor
	public City(String name, double latitude, double longtitude, String attFile, String hotelFile, String foodFile) {
		this.name = name;
		location = new Coordinate(latitude, longtitude);
		chosen = false; // set the city is not chosen initially

		// read the attraction file of the city
		try {
			BufferedReader in = new BufferedReader(new FileReader(attFile));

			numAttractions = Integer.parseInt(in.readLine()); 	// read the number of attractions in the city from the first row of the file
			attractions = new Attraction[numAttractions]; 		// set the list of attractions

			for (int i = 0; i < numAttractions; i++) {
				in.readLine(); 		// read the empty line
				String nameAtt = in.readLine(); 	// read the name of the attraction
				double timeTaken = Double.parseDouble(in.readLine()); 	// read the tour time in this attraction
				double price = Double.parseDouble(in.readLine()); 		// read the price of the attraction
				double rating = Double.parseDouble(in.readLine()); 		// read the rating of the attraction
				boolean ancient = Boolean.parseBoolean(in.readLine());	// read whether it is an ancient attraction
				boolean modern = Boolean.parseBoolean(in.readLine()); 	// read whether it is an modern attraction
				boolean natural = Boolean.parseBoolean(in.readLine());	// read whether it is an natural attraction
				attractions[i] = new Attraction(nameAtt, timeTaken, price, rating, ancient, modern, natural); 	// create the attraction in the list
			}

			sortAttractions(); // sort the list of attractions by rating

			in.close();
		} catch (IOException iox) {
		}

		// read the hotel file of the city
		try {
			BufferedReader in = new BufferedReader(new FileReader(hotelFile));

			numHotels = Integer.parseInt(in.readLine()); 		// read the number of hotels in the city from the first line of the file
			hotels = new Hotel[numHotels]; 						// set the list of hotels

			for (int i = 0; i < numHotels; i++) {
				in.readLine(); 			// read the empty line
				String nameHotel = in.readLine(); 		// read the name of the hotel
				double price = Double.parseDouble(in.readLine()); 	// read the price of the hotel
				int rating = Integer.parseInt(in.readLine()); 		// read the star rating of the hotel

				hotels[i] = new Hotel(nameHotel, price, rating); 	// create the hotel in the list
			}

			sortHotels(); 	// sort the list of hotels by star rating

			in.close();
		} catch (IOException iox) {
		}

		// read the food file of the city
		try {
			BufferedReader in = new BufferedReader(new FileReader(foodFile));

			numFood = Integer.parseInt(in.readLine()); 	// read the number of local food in the city
														// from the first line of the file
			food = new Food[numFood]; 	// set the list of local food

			for (int i = 0; i < numFood; i++) {
				in.readLine(); 		// read the empty lien
				String nameFood = in.readLine(); 	// read the name of the food
				double price = Double.parseDouble(in.readLine()); 	// read the price of the food
				double rating = Double.parseDouble(in.readLine()); 	// read the rating of the food

				food[i] = new Food(nameFood, price, rating); 	// create the food in the list
			}

			sortFood(); 	// sort the list of local food by rating

			in.close();
		} catch (IOException iox) {
		}
	} // City method

	// accessor
	public Coordinate getLocation() {
		return location;
	} // getLocation method

	public String getName() {
		return name;
	} // getName method

	public int getNumAttractions() {
		return numAttractions;
	} // getNumAttractions method

	public int getNumHotels() {
		return numHotels;
	} // getNumHotels method

	public int getNumFood() {
		return numFood;
	} // getNumFood method

	public boolean getChosen() {
		return chosen;
	} // getChosen method

	// mutator
	public void setChosen(boolean newChosen) {
		chosen = newChosen;
	} // setChosen method

	// sort the list of attractions by rating (from highest to lowest)
	private void sortAttractions() {
		// Selection Sorting
		int worstAtt; // the index of the best attraction (highest rating)
		Attraction swap;

		for (int i = numAttractions - 1; i > 0; i--) {
			worstAtt = i;

			for (int j = 0; j < i; j++) {
				if (attractions[worstAtt].getRating() > attractions[j].getRating())
					worstAtt = j;
			}

			swap = attractions[i];
			attractions[i] = attractions[worstAtt];
			attractions[worstAtt] = swap;
		}
	} // sortAttractions method

	// sort the list of hotels by star rating (from lowest to highest)
	private void sortHotels() {
		// Selection Sorting
		int bestHotel; // the index of the worst hotel (lowest star rating)
		Hotel swap;

		for (int i = numHotels - 1; i > 0; i--) {
			bestHotel = i;

			for (int j = 0; j < i; j++)
				if (hotels[bestHotel].getStarRating() < hotels[j].getStarRating())
					bestHotel = j;

			swap = hotels[i];
			hotels[i] = hotels[bestHotel];
			hotels[bestHotel] = swap;
		}
	} // sortHotels method

	// sort the list of local food by rating (from highest to lowest)
	private void sortFood() {
		// Selection Sorting
		int worstFood; // the index of the best local food (highest rating)
		Food swap;
		
		for (int i = numFood - 1; i > 0; i--) {
			worstFood = i;

			for (int j = 0; j < i; j++)
				if (food[worstFood].getRating() > food[j].getRating())
					worstFood = j;

			swap = food[i];
			food[i] = food[worstFood];
			food[worstFood] = swap;
		}
	} // sortFood method

	// create a stop in the plan
	public Stop createStop(Preference prefer, double transportCost) {
		Hotel hotelSuggestion = hotels[suggestHotel(prefer)]; 		// set the recommended hotel
		String attIndex = ""; 										// the indexes of all the recommended attractions
		Attraction[] attSuggestion; 								// the list of recommended attractions
		Food[] foodSuggestion = this.suggestFood(); 				// set the list of recommended local food

		// keep track of the time and money totaled so far as items get added (0 initially)
		double money = 0;
		double time = 0;

		money += transportCost; 		// add transportation cost
		money += hotelSuggestion.getPrice() * prefer.timeSpentEachStop(); 	// add hotel cost

		// find attractions that match the client's preference, 
		for (int i = 0; i < numAttractions && time < prefer.timeSpentEachStop() && money < prefer.maxMoneyPerStop(); i++) {
			
			// checks if the client wants to visit ancient attractions and the attraction is an ancient attraction
			// checks that money and time does not exceed preference limit
			if (attractions[i].getAncient() == true && prefer.getAncientAttraction() == true 
					&& money + attractions[i].getPrice() <= prefer.maxMoneyPerStop() 
					&& time + attractions[i].getTimeTaken() <= prefer.timeSpentEachStop()) {
				
				attIndex = attIndex + i + " "; 				// add the index of the attraction to attIndex
				attractions[i].setChosen(true); 			// set the attraction to chosen
				time += attractions[i].getTimeTaken(); 		// add the tour time in this attraction to the time spent in this city
				money += attractions[i].getPrice(); 		// add the price of the attraction to the amount of money spent in this city
			}
			
			// checks if the client wants to visit modern attractions and the attraction is an modern attraction
			// checks that money and time does not exceed preference limit
			else if (attractions[i].getModern() == true && prefer.getModernAttraction() == true 
					&& money + attractions[i].getPrice() <= prefer.maxMoneyPerStop() 
					&& time + attractions[i].getTimeTaken() <= prefer.timeSpentEachStop()) {
				
				attIndex = attIndex + i + " "; 				// add the index of the attraction to attIndex
				attractions[i].setChosen(true); 			// set the attraction to chosen
				time += attractions[i].getTimeTaken(); 		// add the tour time in this attraction to the time spent in this city
				money += attractions[i].getPrice(); 		// add the price of the attraction to the amount of money spent in this city
			}
			
			// checks if the client wants to visit natural attractions and the attraction is an natural attraction
			// checks that money and time does not exceed preference limit
			else if (attractions[i].getNatural() == true && prefer.getNaturalAttraction() == true 
					&& money + attractions[i].getPrice() <= prefer.maxMoneyPerStop() 
					&& time + attractions[i].getTimeTaken() <= prefer.timeSpentEachStop()) {
				
				attIndex = attIndex + i + " "; 				// add the index of the attraction to attIndex
				attractions[i].setChosen(true); 			// set the attraction to chosen
				time += attractions[i].getTimeTaken(); 		// add the tour time in this attraction to the time spent in this city
				money += attractions[i].getPrice(); 		// add the price of the attraction to the amount of money spent in this city
			}
		}

		// if there is still time left after the attractions matching the client's preference are chosen, add the best attractions to the list of recommended attractions
		for (int i = 0; i < numAttractions && time < prefer.timeSpentEachStop() && money < prefer.maxMoneyPerStop(); i++) {
			// checks if the attraction is not chosen, (the money spent in this city + the price of the attraction) does not exceed the budget at each stop
			// checks that money and time does not exceed preference limit
			if (!(attractions[i].getChosen()) && money + attractions[i].getPrice() <= prefer.maxMoneyPerStop()
					&& time + attractions[i].getTimeTaken() <= prefer.timeSpentEachStop()) {
				
				attIndex = attIndex + i + " "; 				// add the index of the attraction to attIndex
				attractions[i].setChosen(true); 			// set the attraction to chosen
				time += attractions[i].getTimeTaken(); 		// add the tour time in this attraction to the time spent in this city
				money += attractions[i].getPrice(); 		// add the price of the attraction to the amount of money spent in this city
			}
		}

		// attIndex separates indexes with a space. The spaces are removed to process them
		String[] index = attIndex.split(" "); 				// create the list of recommended attractions' indexes
		attSuggestion = new Attraction[index.length];		// set the list of recommended attractions

		for (int i = 0; i < index.length; i++) {
			// set the recommended attraction to the attraction in the list of attractions whose index matches the content in the list of recommended attractions' indexes in the list of attractions
			attSuggestion[i] = attractions[Integer.parseInt(index[i])];
		}

		return new Stop(name, hotelSuggestion, attSuggestion, foodSuggestion, money); // return the created stop
	} // createStop method

	// find the hotel which meets the client's preference
	private int suggestHotel(Preference prefer) {
		int match = -1; 			// initialize the index of matched hotel to -1
		int cheapest; 			// the index of the cheapest matched hotel

		// find the index of hotel which meets the client's preference
		for (int i = 0; i < numHotels && match == -1; i++) {
			// if the star rating of the hotel is greater than or equal to the minimum star rating of hotels the client can accept
			if (prefer.getMinimumStarRating() <= hotels[i].getStarRating()) {
				match = i; 		// set the index of matched hotel to the index of the current hotel
			}
		}

		cheapest = match; // initialize the cheapest matched hotel to the index of the matched hotel

		// find the cheapest matched hotel
		for (int i = match; i < numHotels; i++) {
			// if the price of the current hotel is less than the price of the cheapest current
			if (hotels[i].getPrice() < hotels[cheapest].getPrice()) {
				cheapest = i; 		// set the index of the cheapest matched hotel to the index of the current hotel
			}
		}

		return cheapest; // return the cheapest matched hotel
	} // suggestHotel method

	// find the top 3 local food
	private Food[] suggestFood() {
		Food[] foodSuggestion = new Food[3]; 	// create the list of recommended local food

		// set the list of recommended local food to the top 3 local food in the food list
		for (int i = 0; i < 3; i++) {
			foodSuggestion[i] = food[i];
		}

		return (foodSuggestion); // return the list of recommended local food
	} // suggestFood method

} // City class