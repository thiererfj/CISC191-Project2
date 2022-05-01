package cisc191.sdmesa.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

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
 * Design pattern - singleton pattern. (n.d.). 
 * Retrieved March 10, 2022, from https://www.tutorialspoint.com/design_pattern/singleton_pattern.htm 
 *  
 *  
 *  
 * Version/date: 2.4 04/05/2022
 * 
 * Responsibilities of class:
 * Database is designed to be a singleton class which acts as the database for storing all user's files in the File System program. 
 * Database has a Files[][] to hold the Files of each User account, and a User[] to hold User accounts. 
 * Its methods allow the File System to add users, view users, find usernames, retrieve its Files[][], and retrieve its User[].
 */
public class Database 
{
	// Database has n number of Files per each User
	// Files[0][n] will always represent the SuperUser
	// Files[1 - 9][n] will represent the BasicUsers, with each BasicUser's serial number corresponding to their row index 
	// Columns (n) represent that User's Files objects, ranging 0 - 9 for a total of 10 Files for each User
	FileData[][] globalStorage;
	
	// Database has Users
	// User[0] reserved for the SuperUser, if created
	// User[1 - 9] holds up to 9 BasicUsers, with each BasicUser's serial number corresponding to their index
	User[] users;
	
	/**
	 *  Create SINGLE instance of Database 
	 */
	private static Database database = new Database();
	
	/**
	 *  Private no arg constructor for Singleton Database object
	 */
	private Database() 
	{
		// Initialize Files[][]
		globalStorage = new FileData[10][10];
		
		// Holds super and 9 basic users 
		users = new User[10];
	}
	
	/**
	 * Getter for SINGLE Database object
	 * 
	 * @return
	 */
	public static Database getInstance() 
	{
		return database;
	}
	
	/**
	 * Purpose: To add a user to the basicUsers array
	 * 
	 * @param newUser
	 * @param userType
	 */
	public void addUser(User newUser, String userType) 
	{
		//If the user said they wanted to make a superAccount...
		if (userType.equalsIgnoreCase("super"))
		{
			// Add the one and only superUser to the Database
			users[0] = newUser;
		}
		//If the user said they wanted to make a basicAccount...
		else if (userType.equalsIgnoreCase("basic"))
		{
			// Add basic user using serial number as index
			users[newUser.getSerialNumber()] = newUser;
		}
	}
	
	/**
	 * Purpose: To print out a list of users that have been created
	 * 
	 */
	public void viewUsers() 
	{
		// If no super user exists
		if (users[0] == null) 
		{
			// Tell users that no super user exists yet
			System.out.println("\\_(o_o)_/ --> What's that? No super user exists... there goes another second I won't get back.\n");
		}
		// Print out super user
		else 
		{
			// Print sub-header
			System.out.println("Super user account: ");
			
			// Print active superUser username
			System.out.println(users[0].getUsername());
			
			// Print nothing for spacing
			System.out.println();
		}
		
		// If no basic users exist
		if (users[1] == null) 
		{
			System.out.println("\\_(o_o)_/ --> Let me retrieve the basic users for you! JK, there are none.\n");
		}
		// Print out basic users
		else 
		{
			// Print sub-header
			System.out.println("Basic user accounts: ");
			
			// Start at 1 to loop through only basic users in users[] 
			for (int i = 1; i < users.length; i++) 
			{
				// If basicUser at index exists
				if (users[i] != null) 
				{
					// Print current basicUser username
					System.out.println(users[i].getUsername());
				}
			}
			
			// Print nothing for spacing
			System.out.println();
		}
	}
	
	/**
	 * Purpose: To find if the userName given has been taken by an instance of a User object
	 * 
	 * @param username
	 * @return
	 */
	public User findUsername(String username) 
	{
		// If superUser account exists
		if (users[0] != null) 
		{
			// Check for username match with active superUser account
			if (username.equalsIgnoreCase(users[0].getUsername())) 
			{
				// Return User to log into matching account
				return users[0];
			}
		}
		
		// Start at 1 to loop through only basic users in users[]
		for (int i = 1; i < users.length; i++) 
		{
			// If basicUser at index exists
			if (users[i] != null) 
			{
				// If username input matches current basicUser username
				if (username.equalsIgnoreCase(users[i].getUsername()))
				{
					// Return User to log into matching account
					return users[i];
				}
			}
		}
		
		// Returns null if userName parameter doesn't match any Users in Database
		return null;
	}

	/**
	 * @return global storage two-dimensional array
	 */
	public FileData[][] getGlobalStorage()
	{
		return globalStorage;
	}
	
	/**
	 * 
	 * @return database Users
	 */
	public User[] getUsers() 
	{
		return users;
	}
	
}
