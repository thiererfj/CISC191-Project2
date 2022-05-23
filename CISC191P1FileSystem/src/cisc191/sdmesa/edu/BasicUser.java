package cisc191.sdmesa.edu;

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
 * BasicUser is a subclass of User, designed to create instances of "basic users" in the program. These basic users
 * have more limited access to the file system database than a super user. There are 9 basic users allowed.
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
