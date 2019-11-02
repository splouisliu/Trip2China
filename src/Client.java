import java.util.*;

public class Client {
	private String username;      // client's username
	private String password;      // client's password
	private String phoneNumber;   // client's phone number
	private String address;       // client's address
	private String email;         // client's e-mail
   
   // constructor
	public Client(String username, String password, String phoneNumber, String address, String email) {
		this.username = username.toLowerCase();   // usernames are all converted and stored as lowercase
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
	} // Client method
   
   // check whether the client inputs the correct password or not
	public boolean matchPassword(String password) {
		return password.equals(this.password);		// compares two passwords
	} // matchPassword method
   
   // update the client's personal information
	public void updateInformation() {
		Scanner input = new Scanner(System.in);
		int choice = 0;      // client's choice of updating which part of his/her personal information
		
      // Menu
		System.out.println("\nWhat do you want to update?");
		System.out.println("\t(1) Password");
		System.out.println("\t(2) Phone number");
		System.out.println("\t(3) E-mail");
		System.out.println("\t(4) Address");
		
		boolean exit;        // exit condition indicates whether input is valid to stop operation
		do {
			try {
				exit = true;   // initialize exit to true

				choice = input.nextInt();  // the client inputs his/her choice
				input.nextLine();			// clears input, for handling bad input

				if (choice == 1) {                                       // if the client wants to update his/her password
					System.out.print("\nPlease update your password: ");
					String password = input.nextLine();                   // input the new password
					this.password = password;                             // set the client's password to the new one
				} else if (choice == 2) {                                // if the client wants to update his/her phone number
					System.out.print("\nPlease update your phone number: ");
					String phoneNumber = input.nextLine();                // input the new phone number
					this.phoneNumber = phoneNumber;                       // set the client's phone number to the new one
				} else if (choice == 3) {                                // if the client wants to update his/her e-mail
					System.out.print("\nPlease update your e-mail: ");
					String email = input.nextLine();                      // input the new e-mail
					this.email = email;                                   // set the client's e-mail to the new one
				} else if (choice == 4) {                                // if the client wants to update his/her address
					System.out.print("\nPlease update your address: ");
					String address = input.nextLine();                    // input the new address
					this.address = address;                               // set the client's address to the new one
				} else {                                                 // handles invalid integer inputs (not from menu)
					System.out.print("Invalid input, please retry: ");    // ask the client to input his/her choice again
					exit = false;                                         // set exit to false
				}

			} catch (InputMismatchException imx) {                      // handles invalid non-integer inputs
				System.out.print("Invalid input, please retry: ");       // ask the client to input his/her choice again
				exit = false;                                            // set exit to false
				input.nextLine();
			}
		} while (!exit);  // the loop will end when the client inputs correct selection
      
      System.out.println("Update successfully!");     // output a message to indicate the client has already updated his/her personal inforamtion
	} // updateInformation method
   
   // accessor
	public String getUsername() {
		return username;
	} // getUsername method

	public String getPassword() {
		return password;
	} // getPassword method

	public String getPhoneNumber() {
		return phoneNumber;
	} // getPhoneNumber method

	public String getAddress() {
		return address;
	} // getAddress method

	public String getEmail() {
		return email;
	} // getEmail method
   
   // mutator
	public void setName(String username) {
		this.username = username.toLowerCase();
	} // setName method

	public void setPassword(String password) {
		this.password = password;
	} // setPassword method

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	} // setPhoneNumber method

	public void setAddress(String address) {
		this.address = address;
	} // setAddress method

	public void setEmail(String email) {
		this.email = email;
	} // setEmail method
} // Client class