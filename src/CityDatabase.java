import java.io.*;

public class CityDatabase {
	private static final int MAXCITY = 50; 	//The max number of cities the array can take in is 50

	private City[] cities;				   	//Cities array type City
	private int numCities;				   	//This int keeps track of the current number of cites in the array

	public CityDatabase() {				  	//Constructor of city database
		double longtitude;				  	//The longtitue of the city
		double latitude;					//The latitude of the city
		String cityName;					//The name of the city
		String attFile;						//A file that contains the information of attractions in that city
		String hotelFile;					//A file that contains the information of hotels in that city
		String foodFile;                	//A file that contains the information of food in that city

		cities = new City[MAXCITY];       	//Create the city array of length 50

		try {
			BufferedReader in = new BufferedReader(new FileReader("City.txt")); //Read in the file from City.txt
			numCities = Integer.parseInt(in.readLine());//The first line in file is the total numbers of cities
			for (int i = 0; i < numCities; i++) {       //Loop through the numOfCities
				in.readLine();							//Read in an empty line between each city
				cityName = in.readLine();				//Read in the city name
				latitude = Double.parseDouble(in.readLine());//Read in the latitude
				longtitude = Double.parseDouble(in.readLine());//Read in the longtitude
				attFile = in.readLine();				//Read in the attraction file
				hotelFile = in.readLine();				//Read in the hotel file
				foodFile = in.readLine();				//Read in the food file
				cities[i] = new City(cityName, latitude, longtitude, attFile, hotelFile, foodFile);//Create a city object based on the info from the files
			}

			in.close();
		} catch (IOException e) {//This exception checks the problem with using files
			System.out.println("Problem accessing files.");
		}

	}
	//Accessors
	public City getCity(int idx) {
		return cities[idx];
	}
	//getCity method to get the index of a city
	public City getCity(String name) {
		for (int i = 0; i < numCities; i++)
			if (cities[i].getName().equals(name))
				return cities[i];
		
		return null;
	}
	//getCity method to get a city object based on name input
	public int getNumCities() {
		return numCities;
	}
	//getNumCities method
	private void reset() {
		for(int i=0; i<numCities;i++) {
			cities[i].setChosen(false);
		}
	}
	//This method reset the chosen fields in all the cities to false 
	
	
	// Finds the city which has the minimum distance from a given city
	private City chooseCity(City newCity) {
		City closestCity = null;			//null = not found
		boolean foundUnvisited = false; 	//This determines if any unchosen city exists
		int firstIdx = 0;

		double dist;					//The distance of a city to another
		double minDist = 0;				//minimum distance between the given city and the chosen city, this is used as comparison to find closer cities

		// Loops through list to find the first city that's unchosen 
		for (int i = 0; i < numCities && !foundUnvisited; i++) {
			if (cities[i].getChosen() == false) { //If getChosen is false, then the first city is found
				foundUnvisited = true;			  //Set found to true
				firstIdx = i;                     //Set first index to the first unchosen city
				minDist = cities[i].getLocation().distance(newCity.getLocation()); // Set minimum distance to the distance between the first unchosen city and the current city
				closestCity = cities[i];			  //Set the final chosen city to the first unchosen city							  
			}
		}
		
		// Loops from the first unchosen city to the end of the list to find the closest unchosen city
		if (foundUnvisited) {		
			for (int i = firstIdx + 1; i < numCities; i++) {
				if (cities[i].getChosen() == false) {		  //Only consider the cities that are unchosen before
					dist = cities[i].getLocation().distance(newCity.getLocation());		//Calculate the distance from the 2 cities
					if (dist < minDist) {		//If the distance is less than the current minimum distance, update minimum distance to this distance
						minDist = dist; 
						closestCity = cities[i]; //Set this city to chosenCity
					}
				}
			}
		}
		
		return closestCity;
	}

	// Creates a travel plan given preferences
	public Plan createPlan(Preference pr) {	
		reset();							//set all the cities to unchosen
		
		Plan plan = new Plan(pr.numStops(), pr.getMoney()); 	//Create an empty plan filled with user budget and number of stops
		City currentCity = getCity(pr.getCity());			//Sets current city as the first city to visit (from user preference)
		City nextCity;										//Next city on the list	
		double dist;										//Distance between cities
		double transportCost = 0;							//Transportation cost between cities
		
		currentCity.setChosen(true);						//Set the first city to chosen since it's guaranteed to be on the plan
		
		// Continuously adds stops to plan until the plan is filled up (addStop verifies number of stops filled is within limit)
		// createStop will create a stop object based on preference
		while (plan.addStop(currentCity.createStop(pr, transportCost))) {	
			nextCity = chooseCity(currentCity);				// Finds the closest city to the current city
			
			if(nextCity == null) 							// If nextCity is null, it means that no more cities are available to add to plan.
				return plan;								// At this point, just return the plan (should be filled up)
			
			dist = currentCity.getLocation().distance(nextCity.getLocation());	//Calculate the distance between currentCity and nextCity
			transportCost = pr.getTransport().cost(dist);						//Calculate the transport cost between the two cities
			
			currentCity = nextCity;							// Set currentCity to nextCity
			currentCity.setChosen(true);					// Set the new currentCity to chosen
		}
		
		
		return plan;										//return the plan to travellingSystem class					
	}
}