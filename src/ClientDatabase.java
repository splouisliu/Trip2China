import java.io.*;
import java.util.Scanner;

public class ClientDatabase {
	private static final int MAX_CLIENTS = 500;					//The max number of clients the array can hold

	private Client clients[];									//It contains the array of clients
	private int numClients;										//This keeps track of the current number of clients

	private Scanner input = new Scanner(System.in);

	public ClientDatabase() {									//Constructor
		clients = new Client[MAX_CLIENTS];						//Create the client array

		try {
			BufferedReader in = new BufferedReader(new FileReader("Client.txt"));

			numClients = Integer.parseInt(in.readLine());		//Read in the first line of the file which is the number of clients

			for (int i = 0; i < numClients; i++) {				//Loop through each client
				in.readLine();
				String username = in.readLine();				//Read in the username
				String password = in.readLine();				//Read in the password
				String phoneNumber = in.readLine();				//Read in the phone number
				String address = in.readLine();					//Read in the address
				String email = in.readLine();					//Read in the email

				clients[i] = new Client(username, password, phoneNumber, address, email);
				//Create an object of client based on the informations from the file
			}
			
			sortClients();	// Clients are sorted alphabetically

			in.close();
		} catch (IOException ioe) {		//This exception checks the problems with using files
			numClients = 0;				//Set the number of clients to 0 if there's a problem
		} catch(NumberFormatException nfx) {	//This exception checks if the inputs from the file are the proper types
			numClients = 0;				//Set the number of clients to 0 if there's a problem
		}
	}

	public boolean clientListFull() {	//This method checks if the client list is full
		return numClients >= MAX_CLIENTS;
	}
	
	public boolean clientListEmpty() {	//This method checks if the client list is empty
		return numClients == 0;
	}
	
	public boolean noSpace(String username) {	//This method checks if there are spaces in the username
		for(int i=0; i<username.length();i++) 
			if(username.charAt(i) == ' ')
				return false;					//Return false if space exists
		
		return true;							//Return true if no space
	}
	
	public boolean clientExist(String username) {	//This method checks if the client exists based on the username entered
		username = username.toLowerCase();		// uses lowercase comparison because names are stored as lowercase in ClientDatabase
		
		for(int i=0; i<numClients;i++) 			//Loop through the clients to find the user
			if(clients[i].getUsername().equals(username))
				return true;					//Return true if he/she's found
			
		return false;							//Otherwise, return false
	}

	private void sortClients() {				//This method sort clients alphabetically using selection sort
		int maxClient;							//This indicates the index of the user with the greatest alphabetically name
		Client temp;							//This variable is for swapping

		for (int i = numClients - 1; i > 0; i--) { //Loop from upperbound to 0
			maxClient = i;						   //Set the maxClient to index i

			for (int j = 0; j < i; j++) {		   //Loop through 0 to upperbound, find maxClient
				if (clients[maxClient].getUsername().compareTo(clients[j].getUsername()) < 0) {
					maxClient = j;				   //Set the one with greater alphabetically name to maxClient
				}
			}
			//Swap the greatest with the last one in the array
			temp = clients[i];
			clients[i] = clients[maxClient];
			clients[maxClient] = temp;
		}
	}

	private Client findClient(String username, int start, int end) {  //This method uses recursive binary search to find a client
		int middle = (start + end) / 2;			//Find the middle index

		if (end < start) {						//If the last index is already smaller than the start index, then it's unable to find the user
			return null;						
		} else if (clients[middle].getUsername().equals(username)) {  //When the middle one has the same name as the input username
			return clients[middle];				//Then the client is found
		} else {
			if (username.compareTo(clients[middle].getUsername()) < 0) { //If username is alphabetically smaller than the middle's username
				return findClient(username, start, middle - 1);			//Then call the method by narrowing down the range to start to middle
			} else {
				return findClient(username, middle + 1, end);	//If it's greater, then call the method by narrowing down the range to middle to end
			}
		}
	}
	
	public void saveFile ()				//This method saves the clients' files
	   {
	      try
	      {
	         BufferedWriter out = new BufferedWriter (new FileWriter ("Client.txt"));	//Output to Client.txt
	         
	         out.write(numClients + "");					//Output the number of clients on the first line
	         out.newLine();
   
	         for (int i = 0; i < numClients; i ++)			//Loop through each client
	         {
	            out.newLine();							
	            out.write(clients[i].getUsername());		//Output the name
	            out.newLine();
	            out.write(clients[i].getPassword());		//Output the password
	            out.newLine();
	            out.write(clients[i].getPhoneNumber());		//Output the phone number
	            out.newLine();
	            out.write(clients[i].getEmail());			//Output the email
	            out.newLine();
	            out.write(clients[i].getAddress());			//Output the address
	            out.newLine();
	         }
	         
	         out.close();
	      } catch (IOException iox)		//This exception checks the problems with using files
	      {
	    	  System.out.println("Client saving - IO error");
	      }
	   }

	public Client addClient() {			//This method adds a new client to the array
		System.out.println("CREATE NEW ACCOUNT\n");			//Prompt to enter the information
		System.out.print("Please set your username: ");
		String username;						
		boolean exit;					//This boolean indicates the validity of the input
		do{
			exit = false;				//Initialize to false
			username = input.nextLine();	//The proper type of input should be String
			if(!noSpace(username)) {		//If space is entered, the program prompts the user to re-enter without any space
				System.out.print("Please remove space from your username, re-enter: ");
			}else if(clientExist(username)) { //If the username currently exists, the program prompts the user to re-enter with a different one
				System.out.print("Username exists already, please re-enter: ");
			}else							//If none of the problems occur, then set the exit to true
				exit = true;
		}while(!exit);	//Exit the loop when exit is true, which means the input is valid

		System.out.print("Please set your password: ");	//Prompt to enter password
		String password = input.nextLine();

		System.out.print("Please enter your phone number: "); //Prompt to enter phone number
		String phoneNumber = input.nextLine();

		System.out.print("Please enter your e-mail: ");	//Prompt to enter e-mail
		String email = input.nextLine();

		System.out.print("Please enter your address: "); //Prompt to enter address
		String address = input.nextLine();

		Client temp = new Client(username, password, phoneNumber, email, address);	//Create a client object based on input
		clients[numClients] = temp;	//Put it into the list
		numClients++; //Update the numClients
		
		sortClients();	//Sort the list
		
		return temp;	//Return the new client
	}

	public Client login() {				//This method helps to log in to specific account.
		Client client;
		
		System.out.println("LOGIN TO YOUR ACCOUNT\n");
		// Enter username
		System.out.print("Please enter your username: ");
		String username = input.nextLine();
		client = findClient(username.toLowerCase(), 0, numClients - 1);		//Searches for client object in the database

		while(client == null){		   //If the client doesn't exist, then output a message and prompt to enter again
			System.out.print("No account on record, please re-enter you name: ");
			username = input.nextLine(); 
			client = findClient(username.toLowerCase(), 0, numClients - 1); 
		}//Loop through until the a name that exists in our list is entered
		
		// Enter password
		System.out.print("Please enter your password: ");
		String password = input.nextLine();

		while(!client.matchPassword(password)) {
			System.out.print("Wrong password, please re-enter your password: ");
			password = input.nextLine();
		}	//Loop through and allow the user to re-enter their password until the password matches their username.

		return client;	//Return the client object
	}
}
