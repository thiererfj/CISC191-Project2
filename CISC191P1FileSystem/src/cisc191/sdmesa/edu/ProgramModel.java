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
public class ProgramModel 
{
	// Singleton design pattern, only have ONE database instance throughout program
	private final Database DATABASE = Database.getInstance();
	
	// ProgramMenu has a current user
	private User currentUser;
	
	// Program menu has a UserFactory to create users
	private UserFactory factory = new UserFactory();
	
	private ProgramView programView;

	public Database getDatabase() 
	{
		return DATABASE;
	}

	public UserFactory getUserFactory() 
	{
		return factory;
	}

	public User getCurrentUser() 
	{
		return currentUser;
	}
	
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
		programView = new ProgramView(this);
		
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
			
			currentUser.setProgramView(programView);
			// Set user activity so File System program can end on log out
//			currentUser.setIsActive(true);
			
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
		
		if (DATABASE.getUsers()[0] == null) 
		{
			users = "\nNo accounts have been created yet";
		}
		else 
		{
			for (int i = 0; i < getDatabase().getUsers().length; i++)
			{
				if(getDatabase().getUsers()[i] != null)
				{
					if (i == 0) 
					{
						users = users + "\n" + getDatabase().getUsers()[i].getUsername() + " - (Super User)";
					}
					else 
					{
						users = users + "\n	" + getDatabase().getUsers()[i].getUsername();
					}
				}
			}
		}
		
		return users;
	}
	
	/**
	 * Saves the created accounts and files from this session to the program's utility files
	 * 
	 * @throws IOException 
	 */
	public void saveDataBeforeExit() throws IOException 
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
