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
public class BasicUser extends User 
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
	 * Runs the BasicUser version of UserMenuView
	 */
	@Override
	public void runUserMenuView() 
	{
		// Create UserMenuView object to run logged in user view, pass true parameter for SuperUser
		UserMenuView userMenuView = new UserMenuView(getProgramView(), false);
	}
	
}
