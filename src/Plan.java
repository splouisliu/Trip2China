public class Plan {
	private Stop stops[];					//Each plan contains an array of stops
	private int numStops;					//This variable keeps track of the number of stops
	private int stopIdx;					//This represents the index of stops, for when adding stops to the array
	private double cost;					//This indicates the total cost of the plan
	private double balance;					//This indicates the remaining balance afterwards
	
	public Plan(int n, double bal) {		//Constructor
		numStops = n;						//Initialize the numStops 
		stops = new Stop[numStops];			//Create the stops array based on numStops
		stopIdx = 0;						//Initialize the stop index to 0
		cost = 0;							//Initialize the cost to 0
		balance = bal;						//Initialize the balance to money that the user enters
	}
	
	public double getBalance() {			//Accessor
		return balance;
	}
	
	public boolean listFull() {				//This method checks if the list is full
		return stopIdx >= numStops;
	}

	public boolean addStop(Stop st) {		//This method add a stop to the plan
		if (stopIdx < numStops) {			//Check if the list is full
			stops[stopIdx] = st;			//Set the new stop to the stop index
			stopIdx ++;						//Set the stop index to one more
			cost += st.getCost();			//Updates cost
			balance -= st.getCost();		//Updates remaining balance

			return true;					//Return true if successfully adds the stop
		}
		return false;						//Otherwise, return false
	}
	
	public String toString() {				//toString method
		String str = "";
		
		str += "Total cost: $" + String.format("%.2f", cost) + "\n";  //String.format to limit the cost to 2 decimals
		str += "Remaining balance: $" + String.format("%.2f", balance) + "\n"; //String.format to limit the balance to 2 decimals
		str += "Number of cities: " + numStops;	
		
		for(int i=0; i<numStops;i++)		//Add the info for each stop
			str += "\n" + stops[i];
		
		return str;							
	}

}