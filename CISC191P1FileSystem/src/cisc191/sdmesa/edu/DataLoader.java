package cisc191.sdmesa.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
 * Version/date: 1.8 05/19/2022
 * 
 * Responsibilities of class:
 * DataLoader is designed to enable data persistence in the program by loading FileData objects (files) and User objects (accounts)
 * from program utility files. It will run upon program startup when ProgramModel is created.       
 */
public class DataLoader
{
	int deleteThis;
	
	// DataLoader has the shared single Database instance
	private final Database DATABASE;
	
	// DataLoader has a UserFactory
	private final UserFactory factory; 
	
	/**
	 * Construct DataLoader objects
	 * 
	 * @param DATABASE
	 * @param factory
	 */
	public DataLoader(Database DATABASE, UserFactory factory) 
	{
		this.DATABASE = DATABASE;
		this.factory = factory;
	}
	
	/**
	 * Control loading of any previously saved data from utility files into new session
	 * 
	 * @throws IOException 
	 */
	public void loadSavedData() throws IOException 
	{
		// Create new File object with destination path of previously saved SuperUser account data 
		File savedSuperUserAccountData = new File("SessionData/SuperUserData/SuperUserAccountData");
		
		// If actual file exists...
		if (savedSuperUserAccountData.exists())
		{
			// Load the data from saved utility file
			loadSuperUserAccountData(savedSuperUserAccountData);
		}
		
		//For every basic user in the users array...
		for (int i = 1; i < DATABASE.users.length; i++) 
		{
			//Creates an abstract file with a filepath as a parameter
			File savedBasicUserAccountData = new File("SessionData/BasicUser" + i + "Data/BasicUser" + i + "AccountData");
			
			//If actual file exists...
			if (savedBasicUserAccountData.exists())
			{
				loadBasicUserAccountData(savedBasicUserAccountData, i);
			}
		}
	}

	/**
	 * Load saved super user account data
	 * @throws IOException 
	 */
	private void loadSuperUserAccountData(File superUserAccountData) throws IOException 
	{
		//Creates a buffered and file reader in order to read superUserAccountData
		BufferedReader reader = new BufferedReader(new FileReader(superUserAccountData));
		
		// Sets username variable to the first line of saved file data
		String username = reader.readLine();
		
		// Sets password variable to the second line of saved file data
		String password = reader.readLine();
		
		// Close reader streams
		reader.close();
		
		// Sets userType variable to super type
		String userType = "super";
		
		//upload to database globalstorage 
		DATABASE.addUser(factory.buildUser(userType, username, password, DATABASE), userType);
		
		// Load the SuperUser's files 
		loadSuperUserFileData();
	}
	
	/**
	 * Load saved basic user account data to DATABASE
	 * 
	 * @param basicUserAccountData
	 * @param index
	 * @throws IOException
	 */
	private void loadBasicUserAccountData(File basicUserAccountData, int index) throws IOException
	{
		//Creates a buffered and file reader in order to read basicUserAccountData
		BufferedReader reader = new BufferedReader(new FileReader(basicUserAccountData));
				
		// Sets username variable to the first line of saved file data
		String username = reader.readLine();
				
		// Sets password variable to the second line of saved file data
		String password = reader.readLine();
		
		// Close reader streams
		reader.close();
				
		// Sets userType variable to super type
		String userType = "basic";
				
		//upload to database globalstorage 
		DATABASE.addUser(factory.buildUser(userType, username, password, DATABASE), userType);
		
		// Load the BasicUser's files
		loadBasicUserFileData(index);
	
	}
	
	/**
	 * Load saved super user files data to DATABASE
	 * 
	 * @throws IOException
	 */
	private void loadSuperUserFileData() throws IOException
	{
		// Loop through the SuperUser's 
		for (int i = 0; i < DATABASE.globalStorage[0].length; i++) 
		{
			// Create file object with destination path of previously saved file data
			File savedSuperUserFileData = new File("SessionData/SuperUserData/SuperUserFile" + (i + 1) + "Data");
			
			// If actual file exists...
			if (savedSuperUserFileData.exists())
			{
				// Create new BufferedReader object, using FileReader, in order to read saved txt file data
				BufferedReader reader = new BufferedReader(new FileReader(savedSuperUserFileData));
				
				// Create StringBuilder to build the contents of saved txt file data
				StringBuilder buildFileContents = new StringBuilder();
				
				// read the first line in saved file data (should be file name)
				String fileNameLine = reader.readLine();
				
				// Create new FileData object to store in database
				FileData newFile = new FileData("SessionData/SuperUserData/SuperUserFile" + (i + 1) + "Data");
				
				// Set the file name from first line in saved file data
				newFile.setFileName(fileNameLine);
				
				// Read the second line in saved file data, which is the beginning of file contents
				String line = reader.readLine();
				
				// Loop until end of text file, null line indicates end
				while (line != null) 
				{
					// Add line from BufferedReader to StringBuilder
					buildFileContents.append(line + "\n");
					
					// Read the next line
					line = reader.readLine();
				}
				
				// Close reader streams
				reader.close();
				
				// Set the contents new FileData object to StringBuilder result
				newFile.setContents(buildFileContents);
				
				// Create new FileData object in DATABASE[0 for SuperUser][i for file index]
				DATABASE.globalStorage[0][i] = newFile;
			}
		}
	}
	
	/**
	 * Load saved basic user files to the DATABASE
	 * 
	 * @param index
	 * @throws IOException
	 */
	private void loadBasicUserFileData(int index) throws IOException
	{
		// Loop through the BasicUser's Database indices 
		for (int i = 0; i < DATABASE.globalStorage[0].length; i++) 
		{
			// Create file object with destination path of previously saved file data
			File savedBasicUserFileData = new File("SessionData/BasicUser" + index + "Data/BasicUser" + index + "File" + (i + 1) + "Data");
					
			// If actual file exists...
			if (savedBasicUserFileData.exists())
			{
				// Create new BufferedReader object, using FileReader, in order to read saved txt file data
				BufferedReader reader = new BufferedReader(new FileReader(savedBasicUserFileData));
						
				// Create StringBuilder to build the contents of saved txt file data
				StringBuilder buildFileContents = new StringBuilder();
						
				// Read the first line in saved txt file data
				String fileNameLine = reader.readLine();
						
				// Create new FileData object to store in database
				FileData newFile = new FileData("SessionData/BasicUser" + index + "Data/BasicUser" + index + "File" + (i + 1) + "Data");
				
				// Set the file name from first line in saved file data
				newFile.setFileName(fileNameLine);
				
				// Read the second line in saved file data, which is the beginning of file contents
				String line = reader.readLine();
						
				// Loop until end of text file, null line indicates end
				while (line != null) 
				{
					// Add line from BufferedReader to StringBuilder
					buildFileContents.append(line + "\n");
							
					// Read the next line
					line = reader.readLine();
				}
				
				// Close reader streams 
				reader.close();
						
				// Set the contents new FileData object to StringBuilder result
				newFile.setContents(buildFileContents);
				
				// Create new FileData object in DATABASE[0 for SuperUser][i for file index]
				DATABASE.globalStorage[index][i] = newFile;
			}
		}
	}
}
