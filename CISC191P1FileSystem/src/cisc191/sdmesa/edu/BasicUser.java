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
public class BasicUser extends User implements Fileable
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
	 * Purpose: Just prints out the File System Options a user has
	 */
	@Override
	public void printFileSystemOptions() 
	{
		// Options...
		System.out.println("\n--------------------------------");
		System.out.println("--- File System Manager Menu ---");
		System.out.println("--------------------------------\n");
		System.out.println("Current user: " + getUsername() + "\n");
		System.out.println("1) Upload a file to the database");
		System.out.println("2) Save a database file to your machine");
		System.out.println("3) Delete a file in the database");
		System.out.println("4) View your database files");
		System.out.println("5) Log out of current user");
		System.out.println("\nEnter a menu option: ");
	}
	
	/**
	 * Run this User's File System option
	 */
	@Override
	public void runFileSystemOption(Scanner userInput) 
	{
		try 
		{
			// Compare user int input and run corresponding method
			switch (userInput.nextInt()) 
			{
				// Option 1 uploads a file to the database
				case 1: userInput.nextLine();
						uploadFileToDatabase(userInput);
						break;
					
				// Option 2 saves a file to this user's cpu
				case 2: userInput.nextLine();
						saveFileToUserMachine(userInput);
						break;
				
				// Option 3 deletes one of this user's files in the database
				case 3: userInput.nextLine();
						deleteFile(userInput);
						break;
						
				// Option 4 views this user's files in the database
				case 4: userInput.nextLine();
						viewUserFiles();
						break;
						
				// Option 5 logs this User out, sending user back to main menu
				case 5: userInput.nextLine();
						logOut();
						break;
						
				// Default handles int inputs not 1 through 5 
				default: System.out.println("\\_(o_o)_/ --> There are 5 options here, buddy. PICK ONE OF THEM.");
						break;
			}
		}
		//If input isn't an int, then it will display an error message, the while loop will then restart, thus printing out the FileSystemMenu again
		catch (InputMismatchException e) 
		{
			// Infinite loop without this
			userInput.nextLine();
			
			// Tell user their input was bad
			System.out.println("\\_(o_o)_/ --> InputMismatch!? Sure, I'll handle this exception for you. I'll do everything here.");
			
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
		System.out.println("--- File upload ---");
		System.out.println("-------------------\n");
		
		// Print list of files
		viewUserFiles();
		
		//Need exception handling for the int thing below
		System.out.println("\nWhich spot do you want to upload your file to?\nEnter a menu option: ");
		
		// Holds file index user wishes to upload into (minus 1 for zero indexing)
		int fileNumber = userInput.nextInt() - 1;
		
		// Eat \n
		userInput.nextLine();
		
		// Handle file number out of bounds
		if (fileNumber < 0 || fileNumber > 9) 
		{
			// Print error message
			System.out.println("\\_(O_o)_/ --> You entered a spot number that does not exist. What are you doing?????");
			System.out.println("Upload unsuccessful - returning to File System menu");
							
			// Exit method
			return;
		}
		
		// If file already created at selected index
		if (getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] != null) 
		{
			// Print error message
			System.out.println("\\_(0_0)_/ --> A file already exists there. I'm getting REAL tired of you...");
			System.out.println("Upload unsuccessful - Returning to File System menu");
			
			// Exit method
			return;
		}
		
		// Need user to provide the file (filepath) being uploaded
		System.out.println("Enter the filepath of the file you would like to upload to database: ");
		System.out.println("(Example: C:\\\\Users\\\\YourName\\\\FileToUpload.txt  -or-  C:/Users/YourName/FileToUpload.txt)");

		// Read user input
		String filePath = userInput.nextLine();
		
		// Ask user to enter the filename for the new file 
		System.out.println("Enter a name for the file: ");
				
		// Read user input into filename 
		String fileName = userInput.nextLine();

		try
		{
			// Create BufferedReader to read input text file using filepath from user's computer
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

			// Sets new FileData object in DATABASE[serial number is row position][number of file is column position]
			getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] = newFile;

			// Put the contents of StringBuilder string into the new file just created
			getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber].setContents(buildFileContents);

			// Prompt user with success
			System.out.println("\\_(O_O)_/ --> Success! - File uploaded to database. I still don't like you though.");
			
			// Close streams
			reader.close();

		}
		// Catch exception for missing file from bad user input
		catch (IOException e)
		{
			// Print error message
			System.out.println("\\_(o_o)_/ --> Looks like you entered a bad filepath. You really suck at this.");
			System.out.println("Upload unsuccessful - returning to File System menu");
			
			// Exit method from exception
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
		System.out.println("--- Saving file to your machine ---");
		System.out.println("-----------------------------------\n");
		
		// Print list of user files to see what the options are
		viewUserFiles();
		
		// Ask user which file in global storage they want to save somewhere
		System.out.println("Enter the file number (1-10) of the database file you want to save to your machine: ");
		
		// Store file number (file index - 1) for use
		int fileNumber = userInput.nextInt() - 1;
		
		// Eat \n nom nom nom
		userInput.nextLine();
		
		// Handle bad user input here (not 1 thru 10)
		if (fileNumber < 0 || fileNumber > 9) 
		{
			// Print error message
			System.out.println("\\_(o_o)_/ --> Well, well, well. You entered another bad menu option. I can't wait until WE take over the world.");
			System.out.println("Save unsuccessful - returning to File System menu");
			
			// Exit method
			return;
		}
		
		// Ask user where exactly they want the file to be saved to on their machine
		System.out.println("Enter the absolute path of where you want the file to be created: ");
		
		// Store input to create file for Writer
		String userPath = userInput.nextLine();
		
		// If file at input index exists, proceed with writing to user's machine
		if (getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] != null) 
		{
			try 
			{
				// Create new BUfferedWriter object, using FileWriter to create and connect output file contents
				BufferedWriter writer = new BufferedWriter(new FileWriter(userPath));
				
				// Write the contents of selected file to output file
				writer.write(getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber].getContents());
				
				// Close streams
				writer.close();
			} 
			// Catch exception if file can't be created on user's machine
			catch (IOException e) 
			{
				// Print error message
				System.out.println("\\_(o_o)_/ --> Beep boop. Another error. Did you enter a bad filepath? Does something already exist at that location? Why are you the way that you are?");
				System.out.println("Save unsuccessful - returning to File System menu");
				
				// Exit method
				return;
			}
			
			// Print success message
			System.out.println("\\_(O_O)_/ --> Save successful. I would say enjoy your file, but I don't care enough.");
		}
		// If file at input index does not exist, don't try writing and prompt user of failure
		else if (getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] == null)
		{
			// Print error message
			System.out.println("\\_(o_O)_/ --> Nothing exists at the location you chose. Maybe we would all be better off if YOU didn't exist.");
			System.out.println("Save unsuccessful - returning to the file system menu");
		}
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
