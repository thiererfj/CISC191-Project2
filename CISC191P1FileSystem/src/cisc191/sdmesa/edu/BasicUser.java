package cisc191.sdmesa.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
 *  
 * Version/date: 2.4 04/05/2022
 * 
 * Responsibilities of class:
 * BasicUser is a User, and is Fileable. BasicUser is designed to be the basic user account in the File System Program,
 * and there can be up to nine of them. They have basic acces to the database.
 * The things a basic user can do are:
 * - Upload a file to the database
 * - Save a file from the database to the user's machine
 * - Delete a file from the database belonging to them
 * - View a file in the database belonging to them
 * - Log out  
 */
public class BasicUser extends User 
{	
	
	/**
	 * Implement inherited constructor
	 * 
	 * @param newUsername
	 * @param newPassword
	 * @param newSerialNumber
	 * @param DATABASE
	 */
	public BasicUser(String newUsername, String newPassword, int newSerialNumber, Database DATABASE) 
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
			// Create BufferedReader to read input text file using filepath from
			// User's computer
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

			// Create new FileData object in DATABASE[serial number is row
			// position][number of files is column position]
			getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] = newFile;

			// Put the contents of String builder contents into the new file
			// just created
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
	 * To delete a file from this user's FileData[][] row in Database
	 */
	@Override 
	public void deleteFile(Scanner userInput) 
	{
		// Print user's files
		viewUserFiles();
		
		// Handle bad user index input
		try
		{
			System.out.println("\nEnter the file number of the file you want to delete?: ");
			
			// Holds index of file to delete (input - 1 for zero indexing)
			int filePosition = userInput.nextInt() - 1;
			
			// Eat \n
			userInput.nextLine();
			
			// If no file exists at selected index
			if (getDatabase().getGlobalStorage()[getSerialNumber()][filePosition] == null) 
			{
				// Print error message
				System.out.println("\\_(O_O)_/ --> That file does not exist, cannot delete nothing! What's next, you want to divide by zero?!??!?");
				System.out.println("Deletion unsuccessful - Returning to File System menu");
				
				// Exit method
				return;
			}
			
			// If file exists at selected index, "delete" by setting index to null
			getDatabase().getGlobalStorage()[getSerialNumber()][filePosition] = null;
			
			// Print success message
			System.out.println("\\_(o_o)_/ --> File successfully deleted. Yay.");
		}
		// If user enters index that does not exist
		catch (ArrayIndexOutOfBoundsException e2) 
		{
			// Print error message
			System.out.println("\\_(o_o)_/ --> My circuits tell me you entered something bad. They are baffled you made it this far in life.");
			System.out.println("Deletion unsuccessful - returning to File System menu");
			
			// Exit method
			return;
		}
	}
	
}
