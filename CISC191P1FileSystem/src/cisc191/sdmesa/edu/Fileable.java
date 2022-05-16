package cisc191.sdmesa.edu;

import java.io.FileReader;
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
 * 
 * 
 *  
 * Version/date: 2.4 04/05/2022
 * 
 * Responsibilities of class:
 * The Fileable interface is designed to be implemented in objects that can perform filing operations (uploading/ downloading) across their machine and a database.   
 */
public interface Fileable 
{	
	/**
	 * Allow user to upload a file on their machine to the program database
	 * 
	 * @param userInput
	 */
	String uploadFileToDatabase(String filePath, String fileName, int fileNumber);
	
	/**
	 * Allow user to save a file from the program database to their machine
	 * 
	 * @param userInput
	 */
	String downloadFileFromDatabase(String filePath, int fileNumber);
	
	/**
	 * Allow user to delete file from database
	 * 
	 * @param userInput
	 */
	String deleteFile(int userIndex, int fileNumber);
}
