package cisc191.sdmesa.edu;

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
 * 
 * <<add more references here>>
 * 
 *  
 * Version/date: 2.4 04/05/2022
 * 
 * Responsibilities of class:
 * The abstract User class is designed to be the superclass for User subclasses of the File System Program to extend depending on their unique behavior.   
 */
abstract class User 
{
	private String username;
	private String password;
	private int serialNumber;
	private boolean isActive = false;
	
	// Each User has a Database associated with it (the same one, singleton)
	private Database DATABASE;
	
	/**
	 * Purpose: To create an instance of User
	 * 
	 * @param newUsername
	 * @param newPassword
	 * @param newSerialNumber
	 * @param DATABASE
	 */
	public User(String newUsername, String newPassword, int newSerialNumber, Database DATABASE) 
	{
		//Sets object variables
		this.username = newUsername;
		this.password = newPassword;
		this.serialNumber = newSerialNumber; 
		this.DATABASE = DATABASE;
	}
	
	/**
	 * Purpose: Getter method for username
	 * 
	 * @return username
	 */
	public String getUsername() 
	{
		return username;
	}
	
	/**
	 * Purpose: Getter method for password
	 * 
	 * @return password
	 */
	public String getPassword() 
	{
		return password;
	}
	
	/**
	 * Purpose: Getter method for database
	 * 
	 * @return DATABASE
	 */
	public Database getDatabase() 
	{
		return DATABASE;
	}
	
	/**
	 * Purpose: Getter method for serialNumber
	 * 
	 * @return serialNumber
	 */
	public int getSerialNumber() 
	{
		return serialNumber;
	}
	
	/**
	 * Purpose: To set a user as active or inactive depending on the boolean value given in the parameter
	 * 
	 */
	public void setIsActive(boolean newActivity) 
	{
		this.isActive = newActivity;
	}
	
	/**
	 * Purpose: Getter method isActive
	 * 
	 * @return isActive
	 */
	public boolean getIsActive()
	{
		return isActive;
	}
	
	/**
	 * Purpose: To print this user's files to the console
	 */
	public void viewUserFiles() 
	{
		// Print header
		System.out.println("--------------------------------");
		System.out.println("--- Viewing " + getUsername() + "'s files ---"); 
		System.out.println("--------------------------------");
				
		// Loop through this user's row in FileData[][]
		for (int i = 0; i < 10; i++)
		{
			// No file at index
			if(getDatabase().getGlobalStorage()[getSerialNumber()][i] == null)
			{
				// Print file number (index + 1) and empty state status
				System.out.println((i + 1) + " - Empty");
			}
			// File exists at index
			else
			{
				// Print file number (index + 1) and file name
				System.out.println((i + 1) + " - " + getDatabase().getGlobalStorage()[getSerialNumber()][i].getFileName());
			}
		}
	}
	
	/**
	 * Purpose: Logs the user out of their account, returning back to the mainMenu()
	 * 
	 */
	public void logOut() 
	{
		// Set user activity status to false, which breaks ProgramMenu runUserFileSystem() loop
		setIsActive(false);
	}

	abstract void printFileSystemOptions();
	
	abstract void runFileSystemOption(Scanner userInput);
	
}
