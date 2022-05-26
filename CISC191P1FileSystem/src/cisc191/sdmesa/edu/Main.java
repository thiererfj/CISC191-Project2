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
 * Version/date: 4.5 05/25/22
 * 
 * Responsibilities of class:
 * Main is designed to be the entry point of the file system program.    
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
