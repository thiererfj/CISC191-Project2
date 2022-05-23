package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
 * Version/date: 1.8 05/19/2022
 * 
 * Responsibilities of class:
 * CreateAccountView is designed to reprint the window with GUI components allowing a user to create User objects (accounts).
 * This view gathers the required inputs from the user, and communicates with ProgramModel to execute the task.      
 */
public class CreateAccountView 
{	
	//CreateAccountView has a programView
	private ProgramView programView;
	
	//CreateAccountView has a basicUserButton
	private JButton basicUserButton;
	
	//CreateAccountView has a superUserButton
    private JButton superUserButton;
    
    //CreateAccountView has an accountInstanceButton
    private JButton accountInstanceButton;
    
    // Color objects to set the color scheme
    private Color viewBackgroundColor;
	private Color viewTitleBoxColor;
	private Color viewButtonColor;
	private Color viewTextColor;
	
	/**
	 * Purpose: Constructor for CreateAccountView
	 * @param programView
	 * @param programModel
	 */
    public CreateAccountView(ProgramView programView)
	{
		//sets programView to the one in the parameter
		this.programView = programView;

		//Sets color variables to the ones in programView to allow for cleaner code
		this.viewBackgroundColor = programView.getViewBackgroundColor();
		this.viewTitleBoxColor = programView.getViewTitleBoxColor();
		this.viewButtonColor = programView.getViewButtonColor();
		this.viewTextColor = programView.getViewTextColor();
		
		//Calls the method that shows CreateAccountView's GUI components
		printView();
	}
    
    /**
     * Purpose: Creates the components for the GUI of the CreateAccountView
     */
    private void printView()
    {
        //Removes all the components from the frame
		programView.getContentPane().removeAll();

		//Sets the background color of the frame
        programView.getContentPane().setBackground(viewBackgroundColor);
        
        // Add back to main menu button functionality
        programView.addBackButton();
        
        // Add title JLabel to window
        programView.addTitleLabel("Create Account", viewTitleBoxColor, viewTextColor);
        
		//Creates a buttonPanel JPanel with a GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 20));
		//Sets Opaque to true so you can see the panels background color
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(viewBackgroundColor);
        buttonPanel.setBounds(400, 310, 400, 50);
        
        // If no super user...
        if(!programView.getProgramModel().getDatabase().superUserExists())
        {
			//Create a superUser Button
        	superUserButton = new JButton("Super User");
            superUserButton.setBackground(viewButtonColor);
            superUserButton.setForeground(viewTextColor);
            superUserButton.setFocusable(false);
			//Adds the superUserButton to buttonPanel
            buttonPanel.add(superUserButton);
			
			//Adds an actionListener to superUserButton
            superUserButton.addActionListener(new ActionListener()
    	    {
    		    public void actionPerformed(ActionEvent e)
    		    {
    		    	//Sets this button to false
					superUserButton.setEnabled(false);
					//Sets the opposite button, basicUserButton, to true
    		    	basicUserButton.setEnabled(true);
					//Enables the accountInstanceButton 
    		    	accountInstanceButton.setEnabled(true);
    		    	  
					//We will try to vall the clickSound method  
    		    	try 
					{
  						programView.clickSound();
  					} 
					//Will catch any Exception
					catch (Exception e1) 
					{
  					} 
    		    	 
    		      }
    		});
            
        	
        }
        
        //Creates a basicUserButton that will be clicked on if a user wants to create a basic user
        basicUserButton = new JButton("Basic User");
        basicUserButton.setBackground(viewButtonColor);
        basicUserButton.setForeground(viewTextColor);
        basicUserButton.setFocusable(false);
		//Adds basicUserButton to buttonPanel
        buttonPanel.add(basicUserButton);

		//Adds an ActionListener to basicUserButton
        basicUserButton.addActionListener(new ActionListener()
	    {
		    public void actionPerformed(ActionEvent e)
		    {
		    	//We will try to call the clickSound Method 
		    	try 
		    	{
					programView.clickSound();
		    	} 
				//Will catch any Exception
				catch (Exception e1) 
				{
				}
						
		    	//Disables the basicUserButton because it was clicked  
		    	basicUserButton.setEnabled(false);
				//Allows the accountInstanceButton to be clicked
		    	accountInstanceButton.setEnabled(true);
		    	 
		    	 // If no super user exists...
				 if(!programView.getProgramModel().getDatabase().superUserExists())
		    	 {
					 //The superUserButton will be enabled because a new super user can be created
		    		 superUserButton.setEnabled(true);
		    	 }
		    	 
		      }
		});
        
        //Adds buttonPanel to the frame
        programView.add(buttonPanel);

        //JLabel to ask user for creation username
		JLabel enterName = new JLabel("Create a Username:");
		enterName.setBounds(350, 375, 150, 50);
		enterName.setForeground(viewTextColor);
		//Adds enterName to the frame
		programView.add(enterName);
		
		//JLabel to ask user for creation password
		JLabel enterPassword = new JLabel("Create a Password:");
		enterPassword.setBounds(350, 475, 150, 50);
		enterPassword.setForeground(viewTextColor);
		//Adds enterPassword to the frame
		programView.add(enterPassword);
		
		//Text box where user enters username
		JTextArea userName = new JTextArea();
		userName.setBounds(350, 425, 500, 50);
		userName.setFont(new Font("Times New Roman", Font.BOLD, 30));	
		//Adds userName JTextArea to the frame
		programView.add(userName);
		
		//Text box where user enters password
		JTextArea userPassword = new JTextArea();
		userPassword.setBounds(350, 525, 500, 50);
		userPassword.setFont(new Font("Times New Roman", Font.BOLD, 30));
		//Adds userPassword JTextArea to the frame
		programView.add(userPassword);

		//Creates a usernameError JTextArea
		JTextArea usernameError = new JTextArea("");
        usernameError.setBounds(860, 433, 150, 50);
        usernameError.setBackground(viewBackgroundColor);
        usernameError.setForeground(viewTextColor);
		//Adds usernameError to the frame
        programView.add(usernameError);
        
		//Creates passwordError JTextArea
        JTextArea passwordError = new JTextArea();
        passwordError.setBounds(860, 533, 150, 50);
        passwordError.setBackground(viewBackgroundColor);
        passwordError.setForeground(viewTextColor);
		//Adds passwordError to the frame
        programView.add(passwordError);
		
		//Creates accountInstanceButton
        accountInstanceButton = new JButton("Create Account");
        accountInstanceButton.setBounds(500, 600, 200, 50);
        accountInstanceButton.setEnabled(false);
        accountInstanceButton.setBackground(viewButtonColor);
        accountInstanceButton.setForeground(viewTextColor);
        accountInstanceButton.setFocusable(false);
		//Adds accountInstanceButton to the frame
        programView.add(accountInstanceButton);
		//Adds ActionListener to accountInstanceButton
		accountInstanceButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//We will try to call the clickSound method
				try
				{
					programView.clickSound();
				}
				//Will catch any Exception
				catch (Exception e1)
				{
				}

				//Creates a userType String variable to be used to create a user
				String userType = "";
				//Creates a username String that is set to what ever the user puts in the userName JTextArea
				String username = userName.getText();
				//Creates a password String that is set to what ever the user puts in the password JTextArea
				String password = userPassword.getText();

				// If basic user Button is disabled
				if (!basicUserButton.isEnabled())
				{
					//The userType will be basic
					userType = "basic";
				}
				//Else...
				else
				{
					//The userType will be super
					userType = "super";
				}

				//If that username hasn't been taken...
				if (programView.getProgramModel().getDatabase().findUsername(username) == null)
				{
					//If the username is empty...
					if (username.equals(""))
					{
						//Send the user an error message
						usernameError.setText("Please enter a Username");

						//If the password is empty...
						if (password.equals(""))
						{
							//Send the user an error message
							passwordError.setText("Please enter a password");
						}
						//Else...
						else
						{
							//Set the Text to empty so that the user does not see an error message
							passwordError.setText("");
						}
						//Repaints the frame
						programView.getContentPane().repaint();

					}
					//If the password is empty...
					if (password.equals(""))
					{
						//Send the user an error message
						passwordError.setText("Please enter a password");
						//If the username is empty...
						if (username.equals(""))
						{
							//Send the user an error message
							usernameError.setText("Please enter a username");
						}
						//Else...
						else
						{
							//Set the usernameError to an empty string so the user does not see an error
							usernameError.setText("");
						}
						//Repaints the frame
						programView.getContentPane().repaint();
					}

					//If the length of the given username is over 15...
					if (username.length() > 15)
					{
						//Send the user an error
						usernameError.setText("Username cannot be greater\n than 15 characters");

						//If the password is empty...
						if (password.equals(""))
						{
							//Send the user an error
							passwordError.setText("Please enter a password");
						}
						//Else...
						else
						{
							//Set the passwordError to an empty string so the user does not see an error
							passwordError.setText("");
						}
						//Repaints the frame
						programView.getContentPane().repaint();
					}

					//If the username and the password are not equal and the user name is less than 16 characters long...
					if (!username.equals("") && !password.equals("") && username.length() < 16)
					{
						// Create a new account given the parameters
						programView.getProgramModel().createNewAccount(userType, username, password);
						//Removes all of the components from the frame
						programView.getContentPane().removeAll();
						
						// Reprint window
						programView.getContentPane().repaint();

						//We wil try to call the printMainMenu method
						try
						{
							programView.printMainMenu();
						}
						//Will catch any IOExceptions
						catch (IOException e1)
						{
						}
					}

				}
				//Else...
				else
				{
					//Send the user an error message
					usernameError.setText("That username has \nalready been taken");
				}

			}
		});
        
        //Allows the frame to be visible
        programView.setVisible(true);
		//Repaints the frame
        programView.getContentPane().repaint();
    }
}
