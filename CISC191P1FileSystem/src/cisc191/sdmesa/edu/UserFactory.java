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
 * <<add more references here>>
 * Design pattern - factory pattern. (n.d.). 
 * Retrieved March 10, 2022, from https://www.tutorialspoint.com/design_pattern/factory_pattern.htm 
 *  
 *  
 * Version/date: 4.4 05/22/22
 * 
 * Responsibilities of class:
 * UserFactory is designed to create User objects in a factory-design like process, which will be added to the File System Database.   
 */
public class UserFactory 
{	
	// This is final because the only SuperUser will be at row 0 in Database FileData[][]
	private static final int superUserSerialNumber = 0;
	
	// basicUsersSerialNumber is made static so that we can increment it, 
	// which will help us keep track of how many users have been made
	private static int basicUsersSerialNumber = 1;
	
	/**
	 * This method only exists so ProgramMenu can call to see how many BasicUsers have been created
	 * 
	 * @return static basic user serial number count
	 */
	public int getBasicUsersSerialNumber() 
	{
		return basicUsersSerialNumber;
	}
	
	/**
	 * To create User instances with Factory design pattern
	 * 
	 * @param userType
	 * @param username
	 * @param password
	 * @param DATABASE
	 * @return User account, can technically be null but shouldn't ever actually be null
	 */
	public User buildUser(String userType, String username, String password, Database DATABASE) 
	{
		// If user wants to create SuperUser
		if (userType.equalsIgnoreCase("super"))
		{
			// Create SuperUser instance for super user account creation
			SuperUser superUser = new SuperUser(username, password, superUserSerialNumber, DATABASE);
			
			// Return new SuperUser 
			return superUser;
		}
		// If user wants to create BasicUser
		else if (userType.equalsIgnoreCase("basic"))
		{
			// Create BasicUser instance for basic user account creation
			BasicUser basicUser = new BasicUser(username, password, basicUsersSerialNumber, DATABASE);
			
			// Increment to keep track of how many basic users have been created, max 9
			basicUsersSerialNumber++;
			
			// Return new BasicUser
			return basicUser;
		}
		
		// Should never execute, method can only be called with "super" or "basic" userType
		return null;
	}

}
