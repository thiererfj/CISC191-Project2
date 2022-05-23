package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Objects;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Lead Author(s):
 * @author Anthony Mayoral
 * @author Francis Thierer
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *  
 * Version/date: 4.4 05/22/22
 * 
 * Responsibilities of class:
 * LoginView is designed to reprint the window with GUI components allowing a user to "log in" to created User objects (accounts). 
 * The log in process is the same for both SuperUser and BasicUser, but their unique menu views will be run upon
 * a successful login attempt. This view gathers requires inputs from the user, and communicates with the
 * ProgramModel and its current User to execute the task. 
 */
public class LoginView 
{
	//LoginView has a ProgramView
	private ProgramView programView;
	
	//Color objects to set the color scheme
	private Color viewBackgroundColor;
	private Color viewTitleBoxColor;
	private Color viewButtonColor;
	private Color viewTextColor;

	/**
	 * LoginView constructor
	 * 
	 * @param programView
	 */
	public LoginView (ProgramView programView)
	{
		//Sets programView to the one in the parameter
		this.programView = programView;

		//Sets Color variables to the ones from programView to allow for cleaner code
		this.viewBackgroundColor = programView.getViewBackgroundColor();
		this.viewTitleBoxColor = programView.getViewTitleBoxColor();
		this.viewButtonColor = programView.getViewButtonColor();
		this.viewTextColor = programView.getViewTextColor();

		//Calls the printView method
		printView();
	}

	/**
	 * Print the login view to window
	 */
	private void printView() 
	{
		//Removes all of the components from the frame
		programView.getContentPane().removeAll();
        programView.getContentPane().setBackground(viewBackgroundColor);
        
        
        //Add back to main menu button functionality
        programView.addBackButton();
        
        //Add title JLabel to window
        programView.addTitleLabel("Log In", viewTitleBoxColor, viewTextColor);

        
        //Create label asking for login username, set bounds, set color, and add to window
      	JLabel enterName = new JLabel("Enter Username:");
      	enterName.setBounds(350, 375, 150, 50);
      	enterName.setForeground(viewTextColor);
		//Adds enterName JLabel to the frame
     	programView.add(enterName);
     	
     	//Create text area for user to enter login username, set bounds, set color, and add to window
     	JTextArea userName = new JTextArea();
      	userName.setBounds(350, 425, 500, 50);
      	userName.setFont(new Font("Times New Roman", Font.BOLD, 30));
		//Adds userName JTextArea to the frame
      	programView.add(userName);
     	
     	//Create label asking user for login password, set bounds, set color, and add to window
      	JLabel enterPassword = new JLabel("Enter Password:");
      	enterPassword.setBounds(350, 475, 150, 50);
      	enterPassword.setForeground(viewTextColor);
		//Adds enterPassword JLabel to the frame
      	programView.add(enterPassword);
      		
      	//Create text area for user to enter login password, set bounds, set color, and add to window
      	JTextArea userPassword = new JTextArea();
      	userPassword.setBounds(350, 525, 500, 50);
      	userPassword.setFont(new Font("Times New Roman", Font.BOLD, 30));
		//Adds userPassword JTextArea to the frame
      	programView.add(userPassword);
      	
      	//Create text area to display username error message, set bounds, set color, and add to window
      	JTextArea usernameError = new JTextArea();
		usernameError.setBounds(860, 433, 150, 50);
		usernameError.setBackground(viewBackgroundColor);
		usernameError.setForeground(viewTextColor);
		//Adds usernameError JTextArea to the frame
		programView.add(usernameError);
      	
		//Create text area to display passqord error message, set bounds, set color, and add to window
		JTextArea passwordError = new JTextArea();
		passwordError.setBounds(860, 533, 150, 50);
		passwordError.setBackground(viewBackgroundColor);	
		passwordError.setForeground(viewTextColor);
		//Adds passwordError JTextArea to the frame
		programView.add(passwordError);
      	
		//Create login button, set bounds, set color, set focus, and add to window
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(500, 600, 200, 50);
		loginButton.setBackground(viewButtonColor);
		loginButton.setForeground(viewTextColor);
		loginButton.setFocusable(false);
		//Adds loginButton to the frame
      	programView.add(loginButton);
		
		//Add action listener to login button
		loginButton.addActionListener(new ActionListener()
		{
			//If user clicks log in button
			public void actionPerformed(ActionEvent e)
			{
				//Try to play click sound
				try
				{
					programView.clickSound();
				}
				//Will catch any Exception
				catch (Exception e1)
				{
				}

				//Store login username parameter
				String username = userName.getText();
				
				// Store login password parameter
				String password = userPassword.getText();
				
				//Run login function, receive possible error message 
				String loginError = programView.getProgramModel().logIntoUser(username, password);

				//Check if login did not return error message (loginError is null)
				if (Objects.isNull(loginError))
				{
					//Successful login (no error returned) calls polymorphic user menu view run method
					programView.getProgramModel().getCurrentUser().runUserMenuView();
				}
				//Else if login returned account does not exist error message
				else if (loginError.equals("That account does not exist"))
				{
					//Sets the usernameError texg to the loginError variable
					usernameError.setText(loginError);
					//Makes sure there is no passwordError set
					passwordError.setText("");
					//Repaints the frame
					programView.getContentPane().repaint();
				}
				// Else if login returned password incorrect error message
				else if (loginError.equals("That password is incorrect!"))
				{
					//Makes sure there is no usernameError set
					usernameError.setText("");
					//Sets a password error message
					passwordError.setText("That password is incorrect!");
					//Repaints the frame
					programView.getContentPane().repaint();
				}
			}
		});

		// Repaints the frame
        programView.getContentPane().repaint();
	}
}
