package cisc191.sdmesa.edu;

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
 * Database is designed to be a singleton class which acts as the "database" for storing all User instances (user accounts),
 * and all their FileData objects (files) in the program. Its methods allow other objects to add users and view users/ files
 * and their characteristics.     
 */
public class Database 
{
	// Database has n number of Files per each User
	// Files[0][n] will always represent the SuperUser
	// Files[1 - 9][n] will represent the BasicUsers, with each BasicUser's serial number corresponding to their row index 
	// Columns (n) represent that User's Files objects, ranging 0 - 9 for a total of 10 Files for each User
	private FileData[][] globalStorage;
	
	// Database has Users
	// User[0] reserved for the SuperUser, if created
	// User[1 - 9] holds up to 9 BasicUsers, with each BasicUser's serial number corresponding to their index
	private User[] users;
	
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
	 * @return database Users
	 */
	public User[] getUsers() 
	{
		return users;
	}

	/**
	 * @return username of SuperUser
	 */
	public String[] getSuperUsername() 
	{
		// Create String array length 1 (for only SuperUser)
		String[] superUsername = new String[1];
		
		// If SuperUser does not exist in User[]
		if (users[0] == null) 
		{
			// Set username to "null"
			superUsername[0] = "null";
		}
		// If SuperUser exists
		else 
		{
			// Set username
			superUsername[0] = users[0].getUsername();
		}
		
		// Return super user username
		return superUsername;
	}
	
	/**
	 * @return usernames of BasicUsers
	 */
	public String[] getBasicUsernames() 
	{
		// Create String array length 9 (for nine BasicUsers)
		String[] basicUsernames = new String[9];
		
		// Loop through User[] array, begin at index 1 to grab first BasicUser
		for (int i = 1; i < users.length; i++) 
		{
			// If BasicUser does not exist
			if (users[i] == null) 
			{
				// Set username to "null" (i - 1 to handle index change)
				basicUsernames[i - 1] = "null";
			}
			// If BasicUser exists
			else 
			{
				// Set username (i - 1 to handle index change)
				basicUsernames[i - 1] = users[i].getUsername();
			}
		}
		
		//Return basic user usernames
		return basicUsernames;
	}
	
	/**
	 * @return true if SuperUser object has been created, otherwise false
	 */
	public boolean superUserExists() 
	{
		// If SuperUser does not exist in User[] array
		if (users[0] != null)
		{
			// Return true
			return true;
		}
		
		// SuperUser does not exist, return false
		return false;
	}
	
	/**
	 * @return true if at least 1 BasicUser object has been created, otherwise false
	 */
	public boolean basicUsersExist() 
	{
		// If first instance of BasicUser exists in User[] array
		if (users[1] != null) 
		{
			// Return true
			return true;
		}
		
		// No BasicUsers exist, return false
		return false;
	}
	
	/**
	 * @return true if all SuperUser and BasicUser accounts have been created, otherwise false
	 */
	public boolean allUsersCreated()
	{
		// If SuperUser and last BasicUser exist
		if (users[9] != null && users[0] != null)
		{
			// Return true
			return true;
		}
		
		// If not all Users have been created, return false
		return false;
	}
	
	/**
	 * @return true if all BasicUser accounts have been created, otherwise false
	 */
	public boolean allBasicUsersCreated()
	{
		// If last BasicUser exists
		if (users[9] != null)
		{
			// Return true
			return true;
		}
		
		// Return false
		return false;
	}
	
	/**
	 * @return formatted String representation of Users usernames
	 */
	public String viewUserAccounts() 
	{
		// Create empty String to add account usernames to
		String accountList = "";

		// Loop through this user's row in FileData[][]
		for (int i = 0; i < 10; i++)
		{
			// No file at index
			if (users[i] == null)
			{
				// Add "empty" filename
				accountList = accountList + (i + 1) + " - Empty\n";
			}
			// File exists at index
			else
			{
				// Add correct filename
				accountList = accountList + (i + 1) + " - " + users[i].getUsername() + "\n";
			}
		}

		// Return the completed String
		return accountList;
	}
	
}
