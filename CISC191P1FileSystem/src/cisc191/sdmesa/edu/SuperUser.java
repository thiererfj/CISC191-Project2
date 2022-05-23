package cisc191.sdmesa.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
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
 * SuperUser is a User, which is Fileable. SuperUser is designed to be the super user account type in the file 
 * system program. There is only one SuperUser. A SuperUser has more access to the database than a BasicUser, like
 * an administator account. The SuperUser unique functions are to view and delete any files in the database, either 
 * their own or from another user. 
 */
public class SuperUser extends User
{
	
	/**
	 * Implement inherited constructor
	 * 
	 * @param newUsername
	 * @param newPassword
	 * @param newSerialNumber
	 * @param DATABASE
	 */
	public SuperUser(String newUsername, String newPassword, int newSerialNumber, Database DATABASE) 
	{
		// Call superclass constructor
		super(newUsername, newPassword, newSerialNumber, DATABASE);
	}
	
	/**
	 * Runs the SuperUser version of UserMenuView 
	 */
	@Override
	public void runUserMenuView() 
	{
		// Create UserMenuView object to run logged in user view, pass true parameter for SuperUser
		UserMenuView userMenuView = new UserMenuView(getProgramView(), true);
	}
	
}
