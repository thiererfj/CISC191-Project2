package cisc191.sdmesa.edu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Lead Author(s):
 * @author Anthony Mayoral
 * @author Francis Thierer
 * <<add additional lead authors here, with a full first and last name>>
 * 
 * Other contributors:
 * <<add additional contributors (mentors, tutors, friends) here, with contact information>>
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * <<add more references here>>
 *  
 *  
 * Version/date: 2.4 04/05/2022
 * 
 * Responsibilities of class:
 * ProgramMenu is designed to run the File System Program's overarching user interface. It initially allows users to manipulate user accounts within the database,
 * and once logged in, facilitates a unique User's filing system operations.    
 */
public class ProgramMenu 
{
	// Singleton design pattern, only have ONE database instance throughout program
	private final Database DATABASE = Database.getInstance();
	
	// ProgramMenu has a current user
	private User currentUser;
	
	// Program menu has a UserFactory to create users
	private UserFactory factory = new UserFactory();
	
	/**
	 * Purpose: To create a main menu for the user to interact with
	 * @throws IOException 
	 */
	public void mainMenu() throws IOException
	{
		// Load any previous program data
		DataLoader dataLoader = new DataLoader(DATABASE, factory);
		dataLoader.loadSavedData();
		
		// Create JFrame object to display window
		ProgramView mainWindow = new ProgramView(DATABASE);
		
		//Creates a Scanner object for receiving input, will be passed around to all methods requiring input
		Scanner userInput = new Scanner(System.in);
		
		//If the end of this while block is reached, no matter what it will keep reprinting the menu
		while (true)
		{
			// Options... 
//			System.out.println("-------------------------------");
//			System.out.println("--- User Accounts Main Menu ---");
//			System.out.println("-------------------------------\n");
//			System.out.println("1) Create new account");
//			System.out.println("2) Log in to account");
//			System.out.println("3) View user accounts");
//			System.out.println("4) Quit program... because you quit at everything");
//			System.out.println("\nEnter a menu option: ");
			
			mainWindow.printMainMenu();
			
			try 
			{
				//Switch statement using Scanner object with nextInt() in order to select one of the 4 options
				switch (userInput.nextInt()) 
				{
							//If the user clicks 1 createNewAccount() is called
					case 1: userInput.nextLine();
//							createNewAccount(userInput);
							break;
							
							//If the user clicks 2 logIntoUser() is called
					case 2: userInput.nextLine();                       
							logIntoUser(userInput);
							break;
							
							//If the user clicks 3 viewUserAccounts() is called
//					case 3: viewUserAccounts();
//							break;
							
							//If the user clicks 4 exitProgram() is called
					case 4: exitProgram();
							break;
					
							//If default is reached, the while loop will restart, thus printing out the main menu again
					default: System.out.println("\\_(>_<)_/ --> Enter a valid menu option, STUPID!!!\n");
							break;
				}
			} 
			//If input isn't an int, then it will display an error message, the while loop will then restart, thus printing out the main menu again
			catch (InputMismatchException e) 
			{	
				// Print error message
				System.out.println("\\_(>_<)_/ --> ENTER A NUMBER YOU IDIOT!!! CAN YOU NOT COUNT TO 4???!!!\n");
				
				// Clear bad input
				userInput.nextLine();
			}
			catch (IOException e2) 
			{
				System.out.println("\\_(>_<)_/ --> DAMN. I guess I messed this one up. Your data didn't get saved. Deal with it.");
			}
		}
	}
	
	/**
	 * Purpose: To create a new user account
	 * 
	 * A scanner is used as a parameter so that we don't have to keep creating new Scanner objects
	 * 
	 * @param userInput
	 */
	public void createNewAccount(Scanner userInput, String username, String password) 
	{
		//Sets variables that we will need to set based on the users input
		String userType; 
		String username1 = null;  
		String password1 = null;
		
		// If no super user created yet, ask user which account type to create
		if (DATABASE.users[0] == null) 
		{
			//Asking the user if they want to create a super account or a basic account
			System.out.println("Is this a super user account or a basic user account?");
			
			// Holds type of User to be created
			userType = userInput.nextLine();
			
			// Handle invalid type input
			if (!userType.equalsIgnoreCase("super") && !userType.equalsIgnoreCase("basic"))
			{
				// Print error message
				System.out.println("\\_(>_<)_/ --> I ONLY GAVE YOU TWO OPTIONS DAMN IT!!! CAN YOU NOT SPELL!?!");
				
				// Exit method
				return;
			}
		}
		// Otherwise force basic account creation
		else 
		{
			// Set type of User being created to basic
			userType = "basic";
		}
		
		// Disallow basic user creation after all 9 basic user accounts have been created (basicUsersSerialNUmber will == 10)
		if (factory.getBasicUsersSerialNumber() == 10) 
		{
			// Print error message
			System.out.println("The max amount of basic users have already been created!");
			System.out.println("\\_(>_<)_/ --> DAMN HOW MANY USERS DID YOU WANT!?\n");
			
			// Exit method
			return;
		}
		
		// Print header
		System.out.println("------------------------");
		System.out.println("--- Creating account ---");
		System.out.println("------------------------\n");
		
		// Ask user to set the account username
		System.out.println("\\_(>_<)_/ --> Enter your stupid username: ");
		
		// Holds account username
		username = userInput.nextLine();
		
		// Check if username has already been created
		// Create User object as temp User to compare account usernames, Database returns matching User or null if no match
		User createAttempt = DATABASE.findUsername(username);
					
		// If Database returned an already created account with input username
		if (Objects.nonNull(createAttempt))
		{
			// Print error message
			System.out.println("\\_(>_<)_/ --> THAT USERNAME IS ALREADY TAKEN! GET YOUR OWN NAME!!!\n");
						
			// Exit method
			return;
		}
		
		// Ask user to set account password
		System.out.println("Enter your password (AND DON'T GIVE IT TO YOUR MOM!!!): ");
		
		// Holds account password
		password = userInput.nextLine();
		
		// Create User object with UserFactory build method, then store User in DATABASE with add user method
		DATABASE.addUser(factory.buildUser(userType, username, password, DATABASE), userType);
		
		// Print success message
		System.out.println("\\_(O_O)_/ --> Success! Account has been created. You finally did something right...\n");
	}
	
	/**
	 * Purpose: Logging in to a user's account, which runs the file system manager for this User
	 * 
	 * @param userInput
	 */
	public void logIntoUser(Scanner userInput)
	{
		//If no account have been made...
		if (DATABASE.users[0] == null && DATABASE.users[1] == null)
		{
			// Print error message
			System.out.println("\\_(>_<)_/ --> YOU HAVE TO CREATE AN ACCOUNT FIRST IDIOT!!!\n");
			
			//the return statement marks the end of the method, it will then go back to the while loop and print out the main menu again
			return;
		}
		
		// Print header
		System.out.println("------------------");
		System.out.println("--- Logging in ---");
		System.out.println("------------------\n");
		
		//Asking the user to enter a username of an already created account
		System.out.println("Enter your stupid username: ");
		
		// Holds username for login attempt
		String username = userInput.nextLine();
		
		// Create intermediary User object, check Database for matching username 
		User loginAttempt = DATABASE.findUsername(username);
		
		// If input username does not match any Database User, loginAttempt will be null
		if (Objects.isNull(loginAttempt)) 
		{
			// Print error message
			System.out.println("\\_(>_<)_/ --> That account doesn't exist! WHAT THE HELL ARE YOU DOING!?\n");
			
			// Exit method
			return;
		}
		
		// If input username matches intermediary User username, ask user for password
		System.out.println("Enter your stupid password: ");
		
		// Holds password for login attempt
		String password = userInput.nextLine();
		
		// If input password matches intermediary User password
		if (password.equalsIgnoreCase(loginAttempt.getPassword()))
		{
			// Assign current user to new verified login
			currentUser = loginAttempt;
			
			// Set user activity so File System program can end on log out
			currentUser.setIsActive(true);
			
			// Print success message
			System.out.println("\\_(o_o)_/ --> Success! You logged in, I am mildly pleased... ");
			
			// Run the File System menu for this user, uses Scanner object as param to keep one Scanner throughout program
			runUserFileSystem(userInput);
		}
		// If input password does not match intermediary User password
		else 
		{
			// Print error message, end of method so return to main menu 
			System.out.println("\\_(o_o)_/ --> That's not the correct password!\nCan you do anything right???\n");
		}
	}
	
//	/**
//	 * Purpose: To view the created user accounts
//	 * 
//	 */
//	public void viewUserAccounts()
//	{
//		// Print header
//		System.out.println("-----------------------------");
//		System.out.println("--- Viewing user accounts ---");
//		System.out.println("-----------------------------\n");
//		
//		// Call method to print users
//		DATABASE.viewUsers();
//	}
	
	/**
	 * Purpose: To run the current users FileSystemMenu
	 * 
	 * @param userInput
	 */
	public void runUserFileSystem(Scanner userInput) 
	{
		// Loop File System sub-program while User account is active
		while (currentUser.getIsActive()) 
		{
			//Prints out the File System Options
			currentUser.printFileSystemOptions();
			
			//Runs the File System Option, which is basically a switch statement
			currentUser.runFileSystemOption(userInput);
		}
	}
	
	/**
	 * Purpose: Exits the whole entire program
	 * @throws IOException 
	 */
	public void exitProgram() throws IOException 
	{
		//Says bye bye
		System.out.println("\\_(o_o)_/ --> I thought you would never leave! By the way, I saved your data for next time. You're not welcome...");
		
		// Create DataSaver object to save session data to program utility files
		DataSaver dataSaver = new DataSaver(DATABASE);
		dataSaver.saveSessionDataToFiles();
		
		//Terminates the program
		System.exit(0);
	}
}
