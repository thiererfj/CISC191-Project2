package cisc191.sdmesa.edu;

import java.io.FileReader;
import java.util.Scanner;

/**
 * Lead Author(s):
 * @author Anthony Mayoral
 * @author Francis Thierer
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *  
 * Version/date: 4.3 05/22/22
 * 
 * Responsibilities of class:
 * Fileable is designed to provide an interface framework for User types to implement, which creates file system-like 
 * behavior. There are three operations: upload, download, and delete. 
 */
public interface Fileable 
{	
	/**
	 * Allow user to upload a file on their machine to the program database
	 * 
	 * @param userInput
	 */
	public String uploadFileToDatabase(String filePath, String fileName, int fileNumber);
	
	/**
	 * Allow user to download a file from the program database to their machine
	 * 
	 * @param userInput
	 */
	public String downloadFileFromDatabase(String filePath, int fileNumber);
	
	/**
	 * Allow user to delete file from database
	 * 
	 * @param userInput
	 */
	public String deleteFile(int userIndex, int fileNumber);

}
