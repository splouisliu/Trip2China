import java.io.*;
import java.util.*;

public class TravellingSystem {
	private static final int MINIMUM_DAYS = 4; 					// minimum number of days the client should input for the time he/she wants to spend in China
	private static final double MINIMUM_MONEY_PER_DAY = 350; 	// minimum amount of money the client should input for the amount of money he/she wants to spend in China each day
	private static final double UNIT_DAY = 0.5; 				// time unit is 0.5 day (half day)
	private static final int DAYS_PER_STOP = 3; 				// number of days the client will spend at each stop
	private static final int DAYS_BETWEEN_COUNTRIES = 3; 		// number of days the client will use for flying two-way between his/her country and China

	private ClientDatabase clientDat; 	// client database
	private CityDatabase cityDat; 		// city database
	private int maximum_days; 			// maximum number of days the client can input for the time he/she wants to spend in China
	private Scanner input = new Scanner(System.in);

	// constructor
	public TravellingSystem() {
		clientDat = new ClientDatabase();
		cityDat = new CityDatabase();

		// calculate the maximum number of days the client can input for the time he/she wants to spend in China
		maximum_days = cityDat.getNumCities() * DAYS_PER_STOP + DAYS_BETWEEN_COUNTRIES;
	} // TravellingSystem method

	// create client's preference for his/her travel plan
	private Preference createPreference() {
		double money = 0; 				// amount of money the client wants to spend
		double time = 0; 				// number of days the client wants to spend
		int preferredTransport = 0; 	// client's preferred transportation using between cities
		int preferredCity = 0; 			// city the client wants to visit first
		boolean ancient = true; 		// whether the client wants to visit ancient attractions
		boolean modern = true; 			// whether the client wants to visit modern attractions
		boolean natural = true; 		// whether the client wants to visit natural attractions
		int minimumStarRating = 0; 		// minimum star rating of hotels the client can accept

		int choice; 		// answer the client will input for questions
		boolean exit; 		// exit condition for ensuring valid input

		// Time preference
		System.out.print("\nPlease enter the number of days you are willing to spend (minimum " + MINIMUM_DAYS + ", maximum " + maximum_days + "): ");
		do {
			try {
				exit = true; // initialize exit to true
				time = input.nextDouble(); // input the number of days
				input.nextLine();	// clears input (for handling invalid integer + space inputs)

				// Number of days has to be within minimum/maximum boundaries and a valid multiple of a unit day
				// otherwise re-prompt
				if (time < MINIMUM_DAYS || time > maximum_days || time % UNIT_DAY != 0) {
					
					System.out.print("Invalid input, please retry: "); // ask the client to input the number of days
																		// he/she wants to spend again
					exit = false; // set exit to false
				}
			
			// handles invalid non-integer inputs 
			} catch (InputMismatchException imx) { 
				System.out.print("Invalid input, please retry: "); 
				exit = false; // set exit to false

				input.nextLine();	// clears input (for handling invalid non-integer + space inputs)
			}
		} while (!exit); // the question will end when the client's input is valid

		// Budget preference
		System.out.println("\nThe amount of money you input should be greater than or equal to $" + (time - MINIMUM_DAYS + 1) * MINIMUM_MONEY_PER_DAY);	// give the client the reminder about the amount of money he/she inputs
		System.out.print("Please enter the amount of money you are willing to spend: $");
		do {
			try {
				exit = true;// This exit variable keeps track of the validity of user's input.
				money = input.nextDouble();// get the money as the user inputs
				input.nextLine();	// clears input (for handling invalid integer + space inputs)

				if (money < (time - MINIMUM_DAYS + 1) * MINIMUM_MONEY_PER_DAY) {
					System.out.print("Invalid input, please retry: $");
					exit = false;
				} // if the client enters a number less than the minimum money required, then
					// prompt the
					// user to enter a valid input again.
			} catch (InputMismatchException imx) {
				System.out.print("Invalid input, please retry: $");
				exit = false;

				input.nextLine();	// clears input (for handling invalid non-integer + space inputs)
			} // This checks if the user enter the wrong type of input. Since the proper type
				// is double
				// any other types of inputs will prompt the user to enter again.
		} while (!exit);// When exit is true, the loop ends.

		// Transportation method preference
		System.out.println("\nEnter preferred transportation:");
		System.out.println("\t(1) Bus");// Enter one for bus.
		System.out.println("\t(2) Train");// Enter two for train.
		System.out.println("\t(3) Airplane");// Enter three for airplane.

		do {
			try {
				exit = true;
				preferredTransport = input.nextInt();// The proper input should be type int.
				input.nextLine();	// clears input (for handling invalid integer + space inputs)

				if (preferredTransport < 1 || preferredTransport > 3) {
					System.out.print("Invalid input, please retry: ");
					exit = false;
				} // If the number input is not between 1 and 3, it prompts the user to enter
					// again.
			} catch (InputMismatchException imx) {
				System.out.print("Invalid input, please retry: ");
				exit = false;

				input.nextLine();	// clears input (for handling invalid non-integer + space inputs)
			} // Any other types of input (such as string or character) will prompt the user
				// to enter again
		} while (!exit);

		// First city preference
		System.out.println("\nEnter the first city you want to visit: ");
		
		for (int i = 0; i < cityDat.getNumCities(); i++) 					// Displays all of the available cities for the user to choose from.
			System.out.printf("\t(%d) %s\n", i + 1, cityDat.getCity(i).getName());	
		
		do {
			try {
				exit = true;
				preferredCity = input.nextInt() - 1;	// This gives the index of the preferred city.
				input.nextLine();	// clears input (for handling invalid integer + space inputs)
				
				// Handles invalid integer input (non-existent selection)
				if (preferredCity < 0 || preferredCity > cityDat.getNumCities() - 1) {
					System.out.print("Invalid input, please retry: ");
					exit = false;
				}
				
			// Handles invalid non-integer input
			} catch (InputMismatchException imx) {
				System.out.print("Invalid input, please retry: ");
				exit = false;

				input.nextLine();	// clears input (for handling invalid non-integer + space inputs)
			} // This exception checks if the input type is the proper type.
		} while (!exit);

		// Ancient attraction
		System.out.println("\nDo you want to visit ancient attractions?");// Confirm user's preference on attractions
		System.out.println("\t(1) Yes");
		System.out.println("\t(2) No");
		do {
			try {
				exit = true;
				choice = input.nextInt();
				input.nextLine();		// clears input (for handling invalid integer + space inputs)

				if (choice == 1) 		// if the input is 1, then ancient attractions will belong to their choice
					ancient = true; 
				else if (choice == 2) 	// if the input is 2, then ancient attractions no longer belong to the choice
					ancient = false; 
				else {
					System.out.print("Invalid input, please retry: ");	// Handles invalid integer input (non-existent selection)
					exit = false;
				}
				
			// Handles invalid non-integer input
			} catch (InputMismatchException imx) {
				System.out.print("Invalid input, please retry: ");
				exit = false;	// Other types except for int will mark the exit false where loop will be continued
				
				input.nextLine();	// clears input (for handling invalid non-integer + space inputs)
			}
		} while (!exit);	// When the input meets the standard, the exit will be true and it stops looping.

		// Modern attraction
		System.out.println("\nDo you want to visit modern attractions?");// Confirm user's preference on attractions
		System.out.println("\t(1) Yes");
		System.out.println("\t(2) No");
		do {
			try {
				exit = true;
				choice = input.nextInt();
				input.nextLine();		// clears input (for handling invalid integer + space inputs)

				if (choice == 1) 			// If the input is 1, modern attractions will belong to their choices
					modern = true;
				else if (choice == 2)		// If the input is 2, then modern attractions no longer belong to the options
					modern = false; 
				else {
					System.out.print("Invalid input, please retry: ");	// Handles invalid integer input (non-existent selection)
					exit = false;
				} 

			// Handles invalid non-integer input
			} catch (InputMismatchException imx) {
				System.out.print("Invalid input, please retry: ");
				exit = false;

				input.nextLine();	// clears input (for handling invalid non-integer + space inputs)
			}
		} while (!exit);// When the input meets the standard, the exit will be true and it stops looping.

		// Natural attraction
		System.out.println("\nDo you want to visit natural attractions?");// Confirm user's preference on attractions
		System.out.println("\t(1) Yes");
		System.out.println("\t(2) No");
		do {
			try {
				exit = true;
				choice = input.nextInt();
				input.nextLine();		// clears input (for handling invalid integer + space inputs)

				if (choice == 1) 			// If the input is 1, natural attractions will belong to their choices
					natural = true; 
				else if (choice == 2)		// If the input is 2, then natural attractions no longer belong to the options
					natural = false;
				else {
					System.out.print("Invalid input, please retry: ");		// Handles invalid integer input (non-existent selection)
					exit = false;
				}
			// Handles invalid non-integer input
			} catch (InputMismatchException imx) {
				System.out.print("Invalid input, please retry: ");
				exit = false;//

				input.nextLine();	// clears input (for handling invalid non-integer + space inputs)
			}
		} while (!exit);// When the input meets the standard, the exit will be true and it stops looping.

		// Hotel minimum rating
		System.out.println("\nWhat is the minimum star rating of hotels you can accept (1-5)?");
		do {
			try { 
				exit = true;
				minimumStarRating = input.nextInt();
				input.nextLine();		// clears input (for handling invalid integer + space inputs)
				
				// Handles invalid integer input (non-existent selection)
				if (minimumStarRating < 1 || minimumStarRating > 5) {
					System.out.print("Invalid input, please retry: ");
					exit = false;
				} // If the input in not within 1 and 5, the program prompts user to re-enter a
					// valid value.

			// Handles invalid non-integer input
			} catch (InputMismatchException imx) {
				System.out.print("Invalid input, please retry: ");
				exit = false;

				input.nextLine();	// clears input (for handling invalid non-integer + space inputs)
			}
		} while (!exit);// When the input meets the standard, the exit will be true and it stops looping.

		
		// Create a preference object from the constructor based on 8 parameters.
		return new Preference(money, time, cityDat.getCity(preferredCity).getName(), ancient, modern, natural,
				minimumStarRating, preferredTransport);
		
	}

	public void savePlan(Client client, String output) {	// This method saves the user's plan to a file
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Plans/" + client.getUsername() + "Plan.txt")); 	// The name of the file is username+plan.txt)
			String[] outputLines = output.split("\n");			// Split the plan to an array of string by each line
			for (int i = 0; i < outputLines.length; i++) { 		// Loop through all lines
				out.write(outputLines[i]);						// Write each line to the files
				out.newLine(); 									// Start a new line
			}
			out.close();
		} catch (IOException iox) { // This exception checks the problems with files.
			System.out.println("Plan saving - IO error");
		}
	}

	// BEGINS HERE
	public void run() { // This method is the main menu.
		System.out.println("Welcome to Nani Travels!\n");

		int ans = 0; 		// This int is for user's input
		boolean exit; 		// Exit condition for ensuring valid input
		Client client = null; 	// Create a client object to either log in or create new client
								// Set to null as default

		// Process Client Login
		if (clientDat.clientListEmpty()) { // If the initial list is empty, then it directly create a client
			client = clientDat.addClient();
		} else { // If not, the program has two options for the users:
			System.out.println("Have you registered with Nani Travels before?");
			System.out.println("\t(1) Yes. Login to my account");// Enter 1 to log in
			System.out.println("\t(2) No. Create a new account");// Enter 2 to create a new account
			do {
				try {
					exit = true; // Initialize the exit to true to exit the program
					ans = input.nextInt();
					input.nextLine();		// clears input (for handling invalid integer + space inputs)
					
					// Login
					if (ans == 1) { 
						System.out.println();
						client = clientDat.login();
					// Create new account
					} else if (ans == 2) { 
						if (!(clientDat.clientListFull())) { 	// Check if the client list is full
							System.out.println();
							client = clientDat.addClient(); 	// If not, it calls the addClient method to create new account
						} else { 								
							System.out.print("No more space for new account!");
							return; 							// If full, end the program.
						}
					// Handles invalid integer inputs
					} else {
						System.out.print("Invalid input, please retry: ");
						exit = false;
					}
				// Handles invalid non- integer inputs
				} catch (InputMismatchException imx) {
					System.out.print("Invalid input, please retry: ");
					exit = false;
					input.nextLine();	// clears input (for handling invalid non-integer + space inputs)

				}
			} while (!exit);// When exit is true, that means the user's input is valid and it stops looping.
		}

		// Displays Main Menu
		System.out.println("\nMAIN MENU");
		System.out.println("\nWhat would you like to do?");

		exit = false; // Reset the exit to false
		while (!exit) { // Loop through until the input is valid
			try {
				System.out.println("\t(1) Create new travel plan"); 
				System.out.println("\t(2) Review previous travel plan");
				System.out.println("\t(3) Update personal information");
				System.out.println("\t(4) Exit"); // Exit program
				ans = input.nextInt();
				input.nextLine();		// clears input (for handling invalid integer + space inputs)
				
				if (ans == 1) {
					createPlan(client); 					// If the input is 1, call the createPlan method
					System.out.println("\nMAIN MENU"); 		// Output the menu again after completing task
					System.out.println("\nWhat would you like to do next?");
				} else if (ans == 2) { 						// If the input is 2, call the reviewPlan method
					reviewPlan(client);
					System.out.println("\nMAIN MENU"); 		
					System.out.println("\nWhat would you like to do next?");
				} else if (ans == 3) { 						// If the input is 3, call the updateClientInfo method
					updateClientInfo(client);
					System.out.println("\nMAIN MENU");
					System.out.println("\nWhat would you like to do next?");
				} else if (ans == 4) { 						// If the input is 4, set exit to true to exit the program
					exit = true;
				} else {
					System.out.println("Invalid input, please retry: ");
				} 											// If the input is not between 1 to 4, then prompt the user to enter valid input
			} catch (InputMismatchException imx) {
				System.out.println("Invalid input, please retry: ");
				input.nextLine();	// clears input (for handling invalid non-integer + space inputs)
				
			} // This exception checks whether the proper type of input is entered.
		}

		clientDat.saveFile();// Save the client information into their files.
	}

	// ~~~~~~~~Menu functions~~~~~~~~~~
	public void createPlan(Client client) { 	// This method creates an object of plan
		Preference pr = createPreference(); 	// Create a preference object based on user's input
		Plan plan = cityDat.createPlan(pr); 	// Call the createPlan method to create a new plan

		String output = "------------------------------PLAN------------------------------\n";
		output += pr + "\n";
		output += "----------------------------------------------------------------\n";
		output += plan + "\n";
		output += "----------------------------------------------------------------";
		
		savePlan(client, output);				// Save the plan to the client's file
		System.out.println("\n" + output);		// Output the user's preference and the plan generated from the program
	}

	public void reviewPlan(Client client) { // This method helps the user to review their previous plan
		try {
			BufferedReader in = new BufferedReader(		// Read in the files from the user's file
					new FileReader("Plans/" + client.getUsername().replaceAll(" ", "") + "Plan.txt"));
			String line; 			// String variable to represent the details of each line.

			while ((line = in.readLine()) != null) {
				System.out.println(line); // Loop through the files and output the plan to the user
			}

			in.close();
		} catch (IOException iox) { // This exception checks the problems with files
			System.out.println("Sorry, you do not have a plan to review.");
		}
	}

	public void updateClientInfo(Client client) { // This method updates the info of user
		client.updateInformation(); // It calls the update information method in client class.
	}

} // TravellingSystem class