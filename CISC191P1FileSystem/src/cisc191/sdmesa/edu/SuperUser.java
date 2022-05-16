package cisc191.sdmesa.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
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
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * <<add more references here>>
 * Java bufferedreader class - javatpoint. www.javatpoint.com. (n.d.). 
 * Retrieved March 10, 2022, from https://www.javatpoint.com/java-bufferedreader-class 
 * 
 * Java bufferedwriter class - javatpoint. www.javatpoint.com. (n.d.). 
 * Retrieved March 10, 2022, from https://www.javatpoint.com/java-bufferedwriter-class 
 * 
 *  
 * Version/date: 2.4 04/05/2022
 * 
 * Responsibilities of class:
 * SuperUser is a User, and is Fileable. SuperUser is designed to be the super user account in the File System Program, 
 * and there will only be one. They have more access to the database than a basic user, a super user is like an administrator account.
 * The things a super user can do are:
 * - Upload a file to the database
 * - Save a database file to the user's machine
 * - Delete a file in the database (any file, from super or basic accounts)
 * - View their own database files
 * - View any basic user's database files
 * - Log out 
 */
public class SuperUser extends User
{
	
	/**
	 * Implement inherited constructor
	 * 
	 * @param newUsername
	 * @param newPassword
	 * @param newSerialNumber
	 * @param DATABASE
	 */
	public SuperUser(String newUsername, String newPassword, int newSerialNumber, Database DATABASE) 
	{
		// Call superclass constructor
		super(newUsername, newPassword, newSerialNumber, DATABASE);
	}
	
	/**
	 * Purpose: To "upload" a given file into the globalStorage[][] array
	 */
	@Override
	public String uploadFileToDatabase(String filePath, String fileName, int fileNumber) 
	{	
		// Stop upload process if file already exists at chosen location
		if (getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] != null) 
		{
			// Return file number error message
			return "fileNumber";
		}
		
		try 
		{
			// Create BufferedReader to read input text file using filepath from User's computer
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			
			// Create new FileData object to store in global storage
			FileData newFile = new FileData(fileName);
			
			// Create StringBuilder to build text file content String
			StringBuilder buildFileContents = new StringBuilder();
			
			// Read the first line in text file
			String line = reader.readLine();
			
			// Loop until end of text file, null line indicates end
			while (line != null)
			{
				// Add line from BufferedReader to StringBuilder
				buildFileContents.append(line + "\n");
				
				// Read the next line
				line = reader.readLine();
			}
			
			// Create new FileData object in DATABASE[serial number is row position][number of files is column position] 
			getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] = newFile;
			
			// Put the contents of String builder contents into the new file just created 
			getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber].setContents(buildFileContents);
			
			// Close streams
			reader.close();
	
		}
		// Catch checked exception for missing file from bad user input
		catch (IOException e) 
		{
			// Return file path error
			return "filePath";
		}
		
		// Return null error message for successful upload
		return null;
	}
	
	/**
	 * To "download" a database file to the user's machine
	 */
	@Override
	public String downloadFileFromDatabase(String filePath, int fileNumber)
	{
		
		// If file at input index exists, proceed with writing 
		if (getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] != null) 
		{
			try 
			{
				// Create new BufferedWriter object, using FileWriter to create and connect output file contents
				BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
				
				// Write the contents of selected file to output file
				writer.write(getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber].getContents());
				
				// Close streams
				writer.close();
			} 
			// Catch checked exception if file can't be created on user's machine
			catch (IOException e) 
			{	
				// Return error message
				return "filePath";
			}
		}
		// If file at input index does not exist, don't try writing and prompt user of failure
		else if (getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] == null)
		{
			// Tell user it failed and why
			System.out.println("\\_(o_o)_/ --> You chose to save a file that doesn't exist. You make me wish that, like this file, I didn't exist.");
			System.out.println("Save unsuccessful - returning to File System menu");
			
			return "fileNumber";
		}
		
		// Return null error message for successful download
		return null;
	}
	
	/**
	 * To delete a file from the Database FileData[][] two-dimensional array for any user
	 */
	@Override 
	public String deleteFile(int userIndex, int fileNumber) 
	{
		
		// If no file exists at chosen index
		if (getDatabase().getGlobalStorage()[userIndex][fileNumber] == null)
		{
			return "fileNumber";
		}
		else 
		{
			// "Deleting" a file just sets its array index to null
			getDatabase().getGlobalStorage()[userIndex][fileNumber] = null;
			
			return null;
		}
	}
	
	/**
	 * Purpose: SuperUser option only, to see all files that every user has uploaded to the database
	 * 
	 */
	public void viewAnotherUsersFiles(Scanner userInput)
	{	
		// Holds a count of how many basic user accounts are accessed
		int usersLoopedThrough = 0;
		
		// Loop will throw null pointer exception no matter what
		try
		{
			// Start at 1 to loop through only basic users in DATABASE User[]
			for (int i = 1; i < getDatabase().getUsers().length; i++)
			{
				// Print active basic user's username
				System.out.println(i + " - " + getDatabase().getUsers()[i].getUsername());
				
				// Increment after println, or else counter becomes 1 even if no basic users exist
				usersLoopedThrough++;
			}
		}
		//Catches the NullPointerException and breaks the for loop
		catch(NullPointerException e)
		{		
			// If no basic users exist (counter == 0 and null pointer)
			if (usersLoopedThrough == 0)
			{
				System.out.println("\\_(o_o)_/ --> No basic users have been created! What is there to view?!");
				System.out.println("No files to view - returning to File System menu");
				return;
			}
			
			// Otherwise do nothing and proceed with viewing
		}
		
		// Ask user which basic user's files they want to delete from
		System.out.println("\nWhich user's files do you want to look at?\nEnter a menu option: ");
		
		// Holds basic user's index in DATABASE User[] of which user's files will be manipulated
		int userWanted = userInput.nextInt();
		
		// Handle bad int input
		if (userWanted < 1 || userWanted > usersLoopedThrough)
		{
			// Print error message
			System.out.println("\\_(o_o)_/ --> Enter a valid menu option man!");
			System.out.println("Returning to File System menu");
			
			// Exit method
			return;
		}
		// View selected user's files and delete
		else
		{
			// Eat \n
			userInput.nextLine();
			
			// Print header
			System.out.println("-----------------------------");
			System.out.println("--- Viewing " + getDatabase().getUsers()[userWanted].getUsername()+ "'s files ---");
			System.out.println("-----------------------------");
			
			// Loop through user's files
			for (int i = 0; i < 10; i++)
			{
				// If file at current index does not exist 
				if(getDatabase().getGlobalStorage()[userWanted][i] == null)
				{
					// Print null file state (index + 1 for readable list)
					System.out.println((i + 1) + " - Empty");
				}
				// If file exists
				else
				{
					// Print file number (index + 1 for readable list) and file name
					System.out.println((i + 1) + " - " + getDatabase().getGlobalStorage()[userWanted][i].getFileName());
				}
			}
		}
	}

}
