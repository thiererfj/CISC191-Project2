package cisc191.sdmesa.edu;

import java.io.IOException;

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
		ProgramMenu fileProgram = new ProgramMenu();
		
		// Print sweet title art
//		System.out.println(	
//				"                                  ____     _       _____           _                 \n " +
//				"    /\\                          |  __| (_) |    / ____|          | |                \n" +
//				"    /  \\   _ __   __ _ _ __ _   _| |__   _| | ___| (___  _   _ ___| |_ ___ _ __ ___  \n" +
//				"   / /\\ \\ |  _ \\ / _  |  __| | | |  __| | | |/ _ \\\\___ \\| | | / __| __/ _ \\  _   _ \\ \n" +
//				"  / ____ \\| | | | (_| | |  | |_| | |    | | |  __/____) | |_| \\__ \\ ||  __/ | | | | |\n" +
//				" /_/    \\_\\_| |_|\\__, |_|   \\__, |_|    |_|_|\\___|_____/ \\__  |___/\\__\\___|_| |_| |_|\n" +
//				"                 __/  |      __/ |                        __/ |                      \n" +
//				"                 |___/      |___/                        |___/                       \n"
//				);
		
		// Greet user
//		System.out.println("\n\\_(o_o)_/ --> Hello, user! Welcome to The AngryFileSystem! Don't mess up...\n");
		
		// Run ProgramMenu mainMenu()
		fileProgram.mainMenu();
	}
	
}
