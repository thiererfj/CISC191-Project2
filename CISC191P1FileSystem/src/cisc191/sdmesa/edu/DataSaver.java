package cisc191.sdmesa.edu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
 * Files (java platform SE 8 ). (2022, January 5). 
 * Retrieved April 2, 2022, from https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#deleteIfExists-java.nio.file.Path- 
 *  
 *  
 *  
 * Version/date: 2.4 04/05/2022
 * 
 * Responsibilities of class:
 * DataSaver is designed to enable data persistence by saving locally created FileData objects and User accounts to program utility files. It will run just before shutdown of the FileSystem program,
 * and save all session data: created user accounts and created user FileData objects.    
 */
public class DataSaver
{
	// DataSaver has the shared single Database instance
	private final Database DATABASE;
	
	/**
	 * Construct DataSaver object
	 * 
	 * @param DATABASE
	 */
	public DataSaver(Database DATABASE) 
	{
		this.DATABASE = DATABASE;
	}
	
	/**
	 * Control saving process of this session's user account data to program utility files
	 */
	public void saveSessionDataToFiles() throws IOException
	{
		// Check if SuperUser exists
		if (DATABASE.users[0] != null) 
		{
			// Begin process to save the SuperUser account data
			saveSuperUserAccountData();
		}

		for (int i = 1; i < DATABASE.users.length; i++)
		{
			if (DATABASE.users[i] != null)
			{
				saveBasicUserAccountData(i);
			}
		}
	}
	
	/**
	 * Save this session's SuperUser account info to program utility file
	 * 
	 * @throws IOException
	 */
	private void saveSuperUserAccountData() throws IOException
	{
		// Create BufferedWriter object, using FileWriter object, to create destination file if not already created (from previous session)
		BufferedWriter writer = new BufferedWriter(new FileWriter("SessionData/SuperUserData/SuperUserAccountData"));
			
		// Write SuperUser's username to file
		writer.write(DATABASE.users[0].getUsername() + "\n");
			
		// Write SuperUser's password to file (plaintext)
		writer.write(DATABASE.users[0].getPassword());
			
		// Close writer streams
		writer.close();
		
		// Begin process to save this SuperUser's FileData data
		saveSuperUserFileData();
	}
	
	/**
	 * Save this session's BasicUser account info to program utility file
	 * 
	 * @throws IOException
	 */
	private void saveBasicUserAccountData(int index) throws IOException
	{		
		// Create new BufferedWriter object, using FileWriter object, to create destination file if not already created (from previous session)
		BufferedWriter writer = new BufferedWriter(new FileWriter("SessionData/BasicUser" + index + "Data/BasicUser" + index + "AccountData"));
			
		// Write BasicUser's username to file
		writer.write(DATABASE.users[index].getUsername() + "\n");
			
		// Write BasicUser's password to file
		writer.write(DATABASE.users[index].getPassword());
			
		// Close writer streams
		writer.close();
			
		// Being process to save this BasicUser's FileData data
		saveBasicUserFileData(index);	
	} 
	
	/**
	 * Save this session's SuperUser FileData data to program utility file
	 * 
	 * @throws IOException
	 */
	private void saveSuperUserFileData() throws IOException
	{
		// Loop through all SuperUser's FileData objects
		for (int i = 0; i < DATABASE.globalStorage[0].length; i++) 
		{
			// If FileData object exists
			if (DATABASE.globalStorage[0][i] != null) 
			{
				// Create new BufferedWriter object, using FileWriter object, to create destination file if not already created (from previous session)
				BufferedWriter writer = new BufferedWriter(new FileWriter("SessionData/SuperUserData/SuperUserFile" + (i + 1) + "Data"));
				
				// Write FileData object filename to file
				writer.write(DATABASE.globalStorage[0][i].getFileName() + "\n");
							
				// Write FileData object contents to file 
				writer.write(DATABASE.globalStorage[0][i].getContents());
								
				// Close writer streams
				writer.close();
			}
			// If FileData object does not exist
			else 
			{
				// Delete any previously saved utility file from deleted FileData object
				Files.deleteIfExists(Paths.get("SessionData/SuperUserData/SuperUserFile" + (i + 1) + "Data"));
			}
		}
	}
	
	/**
	 * Save this session's BasicUsers FileData data to program utility file
	 * 
	 * @param index of BasicUser whose data is being saved
	 * @throws IOException
	 */
	private void saveBasicUserFileData(int index) throws IOException
	{
		// Loop through all BasicUser's FileData objects
		for (int i = 0; i < DATABASE.globalStorage[index].length; i++) 
		{
			// If FileData object exists
			if (DATABASE.globalStorage[index][i] != null) 
			{
				// Create new BufferedWriter object, using FileWriter object, to create destination file if not already created (from previous session)
				BufferedWriter writer = new BufferedWriter(new FileWriter("SessionData/BasicUser" + index + "Data/BasicUser" + index + "File" + (i + 1) + "Data"));
				
				// Write FileData object filename to file
				writer.write(DATABASE.globalStorage[index][i].getFileName() + "\n");
				
				// Write FileData object contents to file
				writer.write(DATABASE.globalStorage[index][i].getContents());
					
				// Close writer streams
				writer.close();
			}
			// If FileData object does not exist
			else 
			{
				// Delete any previously saved utility file from deleted FileData object
				Files.deleteIfExists(Paths.get("SessionData/BasicUser" + index + "Data/BasicUser" + index + "File" + (i + 1) + "Data"));
			}
		}
	}
}
