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
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *  
 * Version/date: 4.5 05/25/22
 * 
 * Responsibilities of class:
 * ProgramModel is designed to work with the ProgramView, to run the user functions within the program.
 * There are three main user functions: create a new User account, log into a User account, and
 * view created User accounts. ProgramModel also runs the save data function when a user exits
 * out of the program using the "Quit Program (Save Data)" GUI component.    
 */
public class ProgramModel 
{
	// Singleton design pattern, only have one database instance throughout program
	private final Database DATABASE = Database.getInstance();
	
	// ProgramModel has a current User
	private User currentUser;
	
	// ProgramModel has a UserFactory to create users
	private UserFactory factory = new UserFactory();
	
	// ProgramModel has a ProgramView to display GUI
	private ProgramView programView;
	
	/**
	 * @return single shared Database instance
	 */
	public Database getDatabase() 
	{
		return DATABASE;
	}
	
	/**
	 * @return User Factory 
	 */
	public UserFactory getUserFactory() 
	{
		return factory;
	}
	
	/**
	 * @return current User
	 */
	public User getCurrentUser() 
	{
		return currentUser;
	}
	
	/**
	 * Purpose: Load any previous data, and create ProgramView to begin GUI 
	 * 
	 * @throws IOException 
	 */
	public void mainMenu() throws IOException
	{
		// Load any previous program data
		DataLoader dataLoader = new DataLoader(DATABASE, factory);
		dataLoader.loadSavedData();
		
		// Create JFrame object to display window
		programView = new ProgramView(this);
		
		// Call display method for ProgramView
		programView.printMainMenu();
	}
	
	/**
	 * Purpose: To create a new user account
	 * 
	 * A scanner is used as a parameter so that we don't have to keep creating new Scanner objects
	 * 
	 * @param userInput
	 */
	public void createNewAccount(String userType, String username, String password) 
	{
		// Create User object with UserFactory build method, then store User in DATABASE with add user method
		DATABASE.addUser(factory.buildUser(userType, username, password, DATABASE), userType);
	}
	
	/**
	 * Purpose: Logging in to a user's account, which runs the file system manager for this User
	 * 
	 * @param userInput
	 */
	public String logIntoUser(String username, String password)
	{
		// Create intermediary User object, check Database for matching username 
		User loginAttempt = DATABASE.findUsername(username);
		
		// If input username does not match any Database User, loginAttempt will be null
		if (Objects.isNull(loginAttempt)) 
		{
			// Return account does not exist error message
			return "That account does not exist";
		}
		else if (!password.equalsIgnoreCase(loginAttempt.getPassword()))
		{
			// Return password incorrect error message
			return "That password is incorrect!";
		}
		else 
		{
			// Assign current user to new verified login
			currentUser = loginAttempt;
			
			// Give current User the ProgramView
			currentUser.setProgramView(programView);
			
			// Return null for successful login
			return null;
		}
	}
	
	/**
	 * Purpose: To create a string of the created user accounts in order to display it in the GUI
	 * 
	 */
	public String viewUserAccounts()
	{
		String users = "";
		
		// If neither SuperUser or first BasicUser exist
		if (DATABASE.getUsers()[0] == null && DATABASE.getUsers()[1] == null) 
		{
			users = "\nNo accounts have been created yet";
		}
		else 
		{
			// Loop through this user's row in FileData[][]
			for (int i = 0; i < 10; i++)
			{
				// No file at index
				if (DATABASE.getUsers()[i] == null)
				{
					// Add "empty" filename
					users = users + (i + 1) + " - Empty\n";
				}
				// File exists at index
				else
				{
					// Add correct filename
					users = users + (i + 1) + " - " + DATABASE.getUsers()[i].getUsername() + "\n";
				}
			}
		}
		
		// Return String of usernames
		return users;
	}
	
	/**
	 * Saves the created accounts and files from this session to the program's utility files
	 * 
	 * @throws IOException 
	 */
	public void saveDataBeforeExit() throws IOException 
	{
		// Create DataSaver object to save session data to program utility files
		DataSaver dataSaver = new DataSaver(DATABASE);
		dataSaver.saveSessionDataToFiles();
		
		//Terminates the program
		System.exit(0);
	}

	
}
