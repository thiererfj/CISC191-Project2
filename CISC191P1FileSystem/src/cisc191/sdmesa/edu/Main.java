package cisc191.sdmesa.edu;

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
 * Version/date: 4.3 05/22/22
 * 
 * Responsibilities of class:
 * Main is designed to be the entry point of the File System Program. The user will be welcomed to the program,
 * and the method to start the File System main menu will be called.     
 */
public class Main 
{
	/**
	 * Entry point for program
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{	
		// Create ProgramMenu object, which begins the File System
		ProgramModel fileProgram = new ProgramModel();
		
		// Run ProgramMenu mainMenu
		fileProgram.mainMenu();
	}
	
}
