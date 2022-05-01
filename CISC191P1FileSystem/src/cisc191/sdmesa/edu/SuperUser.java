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
public class SuperUser extends User implements Fileable 
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
	 * Purpose: Just prints out the File System Options a user has
	 */
	@Override
	public void printFileSystemOptions() 
	{
			
		System.out.println("\n--------------------------------");
		System.out.println("--- File System Manager Menu ---");
		System.out.println("--------------------------------\n");
		System.out.println("Current user: " + getUsername() + " (super user)\n");
		System.out.println("\\_(o_o)_/ --> Here are your options. Choose wisely...\n");
		System.out.println("1) Upload a file to the database");
		System.out.println("2) Save a database file to your machine");
		System.out.println("3) Delete a file in the database");
		System.out.println("4) View your database files");
		System.out.println("5) View another users database files");
		System.out.println("6) Log out of current user");
		System.out.println("\nEnter a menu option: ");
	}
	
	/**
	 * Purpose: To run the File System Option
	 */
	@Override
	public void runFileSystemOption(Scanner userInput) 
	{
		//Try to get an int as input from the user...
		try 
		{
			// Compare user int input and run corresponding method
			switch (userInput.nextInt()) 
			{
				// Option 1 uploads a file to the database
				case 1: userInput.nextLine();
						uploadFileToDatabase(userInput);
						break;
				
				// Option 2 saves a database file to this user's cpu
				case 2: userInput.nextLine();
						saveFileToUserMachine(userInput);
						break;
				
				// Option 3 deletes any file in the database
				case 3: userInput.nextLine();
						deleteFile(userInput);
						break;
						
				// Option 4 views this user's files in the database
				case 4: userInput.nextLine();
						viewUserFiles();
						break;
				
				// Option 5 views another user's files in the database
				case 5: userInput.nextLine();
						viewAnotherUsersFiles(userInput);
						break;
						
				// Option 6 logs this User out, sending user back to main menu
				case 6: logOut();
						break;
				
				// Default handles int inputs not 1 through 6		
				default: System.out.println("\\_(o_o)_/ --> Is that an option I gave you?? I'll answer that, NO IT'S NOT!");
						System.out.println("Returning to File System menu. Try an actual option this time.");
						break;
			}
		} 
		// Catch InputMismatches, print error message, return to file system menu loop
		catch (InputMismatchException e)
		{
			// Clear userInput, infinite loop without this
			userInput.nextLine();
			
			// Print error message
			System.out.println("\\_(o_o)_/ --> Looks like you've entered something COMPLETELY WRONG. Why am I not surprised?");
			System.out.println("Returning to File System menu. Again.");
			
			// Exit method
			return;
		}
	}
	
	/**
	 * Purpose: To "upload" a given file into the globalStorage[][] array
	 */
	@Override
	public void uploadFileToDatabase(Scanner userInput) 
	{	
		// Print header
		System.out.println("-------------------");
		System.out.println("--- File Upload ---");
		System.out.println("-------------------\n");
		
		// Print user's files
		viewUserFiles();
		
		// An InputMismatchException will be caught by runFileSystemOption() 
		System.out.println("\nWhich spot would you like to upload your file to?\nEnter a menu option: ");
		int fileNumber = userInput.nextInt() - 1;
		
		// Eat \n
		userInput.nextLine();
		
		// Handle file number out of bounds
		if (fileNumber < 0 || fileNumber > 9) 
		{
			// Print error message
			System.out.println("\\_(o_o)_/ --> Really? Another bad input? Enter a number ONE THROUGH NINE.");
			System.out.println("Upload unsuccessful - returning to File System menu");
			
			// Exit method
			return;
		}
		
		// Stop upload process if file already exists at chosen location
		if (getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] != null) 
		{
			// Print error message
			System.out.println("\\_(o_o)_/ --> A file already exists in that spot! IDIOT!");
			System.out.println("Upload unsuccessful - Returning to File System menu");
			
			// Exit method
			return;
		}
		
		// Need user to provide the file being uploaded
		System.out.println("Enter the filepath of the file you would like to upload to database: ");
		System.out.println("(Example: C:\\\\Users\\\\YourName\\\\FileToUpload.txt  -or-  C:/Users/YourName/FileToUpload.txt)");
		
		// Read user input into filepath
		String filePath = userInput.nextLine();
		
		// Ask user to enter the filename for the new file 
		System.out.println("Enter a name for the file: ");
		
		// Read user input into filename 
		String fileName = userInput.nextLine();
		
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
			
			// Prompt user with success
			System.out.println("Success! - File uploaded to database");
			
			// Close streams
			reader.close();
	
		}
		// Catch checked exception for missing file from bad user input
		catch (IOException e) 
		{
			// Print error message
			System.out.println("\\_(o_o)_/ --> Cannot find file specified, do you not know the names to your own files?");
			System.out.println("Upload unsuccessful - returning to File System menu");
			
			// Exit method
			return;
		}
	}
	
	/**
	 * To "download" a database file to the user's machine
	 */
	@Override
	public void saveFileToUserMachine(Scanner userInput)
	{
		// Print header
		System.out.println("-----------------------------------");
		System.out.println("--- Saving File to Your Machine ---");
		System.out.println("-----------------------------------\n");
		
		// Print list of user files to see what the options are
		viewUserFiles();
		
		// Ask user which file in global storage they want to save somewhere
		System.out.println("\nEnter the file number (1-10) of the database file you would like to save to your machine: ");
		
		// Store file number (file index - 1) for use
		int fileNumber = userInput.nextInt() - 1;
		
		// Eat \n nom nom nom
		userInput.nextLine();
		
		// Handle bad user input here (not 1 thru 10)
		if (fileNumber < 1 || fileNumber > 9) 
		{
			// Print error message
			System.out.println("\\_(o_o)_/ --> Enter a valid menu option! They're right there on the screen! ");
			System.out.println("Save unsuccessful - returning to File System menu");
			
			// Exit method
			return;
		}
		
		// Ask user where exactly they want the file to be saved to on their machine
		System.out.println("Enter the absolute path of where you want the file to be created: ");
		
		// Store input to create file for Writer
		String userPath = userInput.nextLine();
		
		// If file at input index exists, proceed with writing 
		if (getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] != null) 
		{
			try 
			{
				// Create new BufferedWriter object, using FileWriter to create and connect output file contents
				BufferedWriter writer = new BufferedWriter(new FileWriter(userPath));
				
				// Write the contents of selected file to output file
				writer.write(getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber].getContents());
				
				// Close streams
				writer.close();
			} 
			// Catch checked exception if file can't be created on user's machine
			catch (IOException e) 
			{
				// Tell user it failed and hopefully why
				System.out.println("\\_(o_o)_/ --> The file could not be created. Did you enter the correct filepath? Probaly not, knowing you.");
				System.out.println("Save unsuccessful - returning to File System menu");
				
				// Exit method
				return;
			}
			
			// Tell user it was successful
			System.out.println("Save successful! Enjoy your file :)");
		}
		// If file at input index does not exist, don't try writing and prompt user of failure
		else if (getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] == null)
		{
			// Tell user it failed and why
			System.out.println("\\_(o_o)_/ --> You chose to save a file that doesn't exist. You make me wish that, like this file, I didn't exist.");
			System.out.println("Save unsuccessful - returning to File System menu");
		}
	}
	
	/**
	 * To delete a file from the Database FileData[][] two-dimensional array for any user
	 */
	@Override 
	public void deleteFile(Scanner userInput) 
	{
		// Print header
		System.out.println("---------------------");
		System.out.println("--- File Deletion ---");
		System.out.println("---------------------\n");
		
		// Ask user where they want to delete from
		System.out.println("1) Personal Storage\n2) Basic User Storage\n\nDo you want to delete from your personal storage or the basic user storage?\nEnter a menu option: ");
		
		// Holds user's deletion menu choice
		int tempAnswer = 0;
		
		// Assign tempAnswer to user's choice
		tempAnswer = userInput.nextInt();
		
		// Eat \n from int input or invalid string input
		userInput.nextLine();
		
		// Handle invalid input
		if (tempAnswer < 1 || tempAnswer > 2)
		{
			// Print error message
			System.out.println("\\_(0_o)_/ --> Enter a valid menu option! For godsake");
			System.out.println("Deletion unsuccessful - returning to File System menu");
			
			// Exit method
			return;
		}
		
		// Personal storage deletion
		if (tempAnswer == 1)
		{
			// Print header
			System.out.println("-------------------------");
			System.out.println("--- Viewing " + getUsername() + "'s Files ---");
			System.out.println("-------------------------");
			
			// Loop through user's FileData[][] array row from database
			for (int i = 0; i < 10; i++)
			{
				// If file at current index is empty
				if (getDatabase().getGlobalStorage()[getSerialNumber()][i] == null)
				{
					// Print unit index and file state
					System.out.println((i + 1) + " - Empty");
				}
				// If file exists at current index
				else
				{
					// Print unit index and file name (path)
					System.out.println((i + 1) + " - " + getDatabase().getGlobalStorage()[getSerialNumber()][i].getFileName());
				}
			}
			
			// Ask user which file they want to delete
			System.out.println("\nWhich file number do you want to delete? ");
			
			// Holds file index user wants to delete (minus 1 for zero indexing)
			int filePosition = userInput.nextInt() - 1;
			
			// Eat \n
			userInput.nextLine();

			// Handle bad file number input
			if (filePosition < 0 || filePosition > 9)
			{
				// Print error message
				System.out.println("\\_(o_o)_/ --> ENTER A VALID MENU OPTION!");
				System.out.println("Deletion unsuccessful - returning to File System menu");
				
				// Exit method
				return;
			}
			
			// If no file exists at chosen index
			if (getDatabase().getGlobalStorage()[0][filePosition] == null) 
			{
				// Print error message
				System.out.println("\\_(o_o)_/ --> That file does not exist, cannot delete nothing!");
				System.out.println("Deletion unsuccessful - Returning to File System menu");
				
				// Exit method
				return;
			}
			
			// "Deleting" a file just sets its array index to null
			getDatabase().getGlobalStorage()[0][filePosition] = null;
			
			// Tell user the deletion worked
			System.out.println("File successfully deleted! :)");
			
		}
		 // Basic user storage deletion
		if (tempAnswer == 2)
		{
			// Holds how many basic user accounts have been accessed
			int usersLoopedThrough = 0;
			
			// Loop will throw NullPointerException if not all 9 BasicUser accounts exist
			try
			{
				// Print header
				System.out.println("-------------------");
				System.out.println("--- Basic users ---");
				System.out.println("-------------------");
				
				// Start at 1 to loop through only basic users in DATABASE User[]
				for (int i = 1; i < getDatabase().getUsers().length; i++)
				{
					// Print current basic user's username
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
					// Print error message
					System.out.println("\\_(o_o)_/ --> No basic users have been created! Why don't you create one!?");
					System.out.println("Deletion unsuccessful - returning to File System menu");
					
					// Exit method
					return;
				}
				
				// Otherwise do nothing and proceed with deletion process
			}
			
			// Ask user which BasicUser account has the file to be deleted
			System.out.println("\nWhich user account do you want to delete a file from?\nEnter a menu option: ");
			
			// Holds DATABASE Users[] index with file to be deleted (input matches array indexing)
			int userWanted = userInput.nextInt();
			
			// Eat \n
			userInput.nextLine();
			
			// Handle bad index input
			if (userWanted < 1 || userWanted > usersLoopedThrough)
			{
				// Print error message
				System.out.println("\\_(o_o)_/ --> Enter a valid menu option... You'll never become a Senior Developer at this rate");
				System.out.println("Deletion unsuccessful - returning to File System menu");
				
				// Exit method
				return;
			}
			// Print the basic user's files
			else
			{
				// Print header with basic user's username
				System.out.println("----------------------------");
				System.out.println("--- Viewing" + getDatabase().getUsers()[userWanted].getUsername()+ "'s Files ---");
				System.out.println("----------------------------");
				
				// Loop through basic user's FileData[][] in Database
				for (int i = 0; i < 10; i++)
				{
					// If file at current index does not exist 
					if(getDatabase().getGlobalStorage()[userWanted][i] == null)
					{
						// Print index + 1 (for readable list) and empty state
						System.out.println((i + 1) + " - Empty");
					}
					// If file exists at current index
					else
					{
						// Print index + 1 (for readable list) and filename 
						System.out.println((i + 1) + " - " + getDatabase().getGlobalStorage()[userWanted][i].getFileName());
					}
				}
			}
			
			// Ask user which file in list they want to delete
			System.out.println("\nWhich file do you want to delete?\nEnter a menu option: ");
			
			// Holds array index of file user wants to delete (subtract 1 for zero indexing)
			int filePosition = userInput.nextInt() - 1;
			
			// Eat \n
			userInput.nextLine();
			
			// Handle invalid user input
			if(filePosition < 0 || filePosition > 9)
			{
				// Print error message
				System.out.println("\\_(o_o)_/ --> Enter a valid menu option you JUNIOR DEVELOPER!");
				System.out.println("Deletion unsuccessful - returning to File System menu");
				
				// Exit method
				return;
			}
			
			// If no file exists at chosen index 
			if (getDatabase().getGlobalStorage()[userWanted][filePosition] == null) 
			{
				// Print error message
				System.out.println("\\_(o_o)_/ --> That file does not exist, cannot delete nothing!");
				System.out.println("Deletion unsuccessful - Returning to File System menu");
					
				// Exit method
				return;
			}
			// Otherwise delete file
			else 
			{
				// "Deleting" a file just sets its array index to null
				getDatabase().getGlobalStorage()[userWanted][filePosition] = null;
			}

			// Prompt user of success
			System.out.println("File successfully deleted! :)");
		}
	}
	
	/**
	 * Purpose: SuperUser option only, to see all files that every user has uploaded to the database
	 * 
	 */
	public void viewAnotherUsersFiles(Scanner userInput)
	{
		// Print header
		System.out.println("------------------------------------");
		System.out.println("--- Viewing another user's files ---");
		System.out.println("------------------------------------");
		
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
