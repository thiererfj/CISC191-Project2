package cisc191.sdmesa.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Lead Author(s):
 * @author Anthony Mayoral
 * @author Francis Thierer
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *  
 * Version/date: 4.4 05/22/22
 * 
 * Responsibilities of class:
 * The abstract User class is designed to be the superclass for User subclasses of the File System Program to extend depending on their unique behavior.   
 */
abstract class User implements Fileable
{
	// User has username
	private String username;
	
	// User has password
	private String password;
	
	// User has serial number
	private int serialNumber;
	
	// User has a ProgramView
	private ProgramView programView;
	
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
	 * Purpose: Setter method for programView
	 * 
	 * @param programView
	 */
	public void setProgramView(ProgramView programView) 
	{
		this.programView = programView;
	}
	
	/**
	 * Purpose: Getter method for programView
	 * 
	 * @return programView
	 */
	public ProgramView getProgramView() 
	{
		return programView;
	}
	
	/**
	 * Purpose: Create and return a String representing the filenames of this user's uploaded files
	 */
	public String viewUserFiles() 
	{
		// Create empty String to add filenames to
		String fileList = "";
		
		// Loop through this user's row in FileData[][]
		for (int i = 0; i < 10; i++)
		{
			// No file at index
			if (DATABASE.getGlobalStorage()[getSerialNumber()][i] == null)
			{
				// Add "empty" filename
				fileList = fileList + (i + 1) + " - Empty\n";
			}
			// File exists at index
			else
			{
				// Add correct filename
				fileList = fileList + (i + 1) + " - " + DATABASE.getGlobalStorage()[getSerialNumber()][i].getFileName() + "\n";
			}
		}
		
		// Return the completed String
		return fileList;
	}
	
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
			// Create BufferedReader to read input text file using filepath from User's
			// computer
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

			// Create new FileData object in DATABASE[serial number is row position][number
			// of files is column position]
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
				System.out.println("There was a filepath error");
				// Return error message
				return "filePath";
			}
		}
		// If file at input index does not exist, don't try writing and prompt user of failure
		else if (getDatabase().getGlobalStorage()[getSerialNumber()][fileNumber] == null)
		{	
			// Return error message
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
			// Return error message
			return "fileNumber";
		}
		else 
		{
			// "Deleting" a file just sets its array index to null
			getDatabase().getGlobalStorage()[userIndex][fileNumber] = null;
			
			// Return null error message for successful delete
			return null;
		}
	}
	
	/**
	 * Run subclass unique menu view
	 */
	abstract void runUserMenuView();
	
}
