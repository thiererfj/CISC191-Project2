package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class LoginView 
{
	
	// LoginView has a ProgramView
	private ProgramView programView;

	// Constructor
	public LoginView (ProgramView programView)
	{
		this.programView = programView;
		
		printView();
	}

	// Print the login view
	private void printView() 
	{
		programView.getContentPane().removeAll();

        programView.getContentPane().setBackground(Color.black);
        //GUI Visual Code for creating an account goes here
        
        // Add back to main menu button functionality
        programView.addBackButton();
        
        // Add title JLabel to window
        programView.addTitleLabel("Login", Color.LIGHT_GRAY, Color.WHITE);

        //Set Username / Set Password TextBoxes
        //Basic and SuperUser JButtons      (Which will disable if Super User is already created)
        
        //Asking for Username
      	JLabel enterName = new JLabel("Enter Username:");
      	enterName.setBounds(250, 375, 150, 50);
      	enterName.setForeground(Color.white);
     		programView.add(enterName);
     		
     	//Username Text Box
     	JTextArea userName = new JTextArea();
      	userName.setBounds(250, 425, 500, 50);
      	userName.setFont(new Font("Times New Roman", Font.BOLD, 30));
      	programView.add(userName);
      		
      	//Asking for Password
      	JLabel enterPassword = new JLabel("Enter Password:");
      	enterPassword.setBounds(250, 475, 150, 50);
      	enterPassword.setForeground(Color.white);
      	programView.add(enterPassword);
      		
      	//User password Text Box
      	JTextArea userPassword = new JTextArea();
      	userPassword.setBounds(250, 525, 500, 50);
      	userPassword.setFont(new Font("Times New Roman", Font.BOLD, 30));
      	programView.add(userPassword);
      	
      	JTextArea usernameError = new JTextArea();
		usernameError.setBounds(775, 433, 150, 50);
		usernameError.setBackground(Color.black);
		usernameError.setForeground(Color.red);
		programView.add(usernameError);
      	
		JTextArea passwordError = new JTextArea();
		passwordError.setBounds(775, 533, 150, 50);
		passwordError.setBackground(Color.black);	
		passwordError.setForeground(Color.red);
		programView.add(passwordError);
      	
      	JButton loginButton = new JButton("Login");
      	loginButton.setBounds(400, 600, 200, 50);
      	loginButton.setBackground(Color.gray);
      	loginButton.setFocusable(false);
      	loginButton.addActionListener(new ActionListener()
	    {
		      // If user clicks log in button
			  public void actionPerformed(ActionEvent e)
		      {
		    	  // Store login parameters
		    	  String username = userName.getText();
		    	  String password = userPassword.getText();
		    	  
		    	  String loginError = programView.getProgramModel().logIntoUser(username, password);
		    	  
		    	  // Check if login did not return error message (loginError is null)
		    	  if (Objects.isNull(loginError))
		    	  {
					// Logging in to a user account resets GUI to show their file system options
					UserMenuView userMenuView = new UserMenuView(programView);
					
					// Print logged in user's menu view
					userMenuView.printView();
		    	  }
		    	  // Else if login returned account does not exist error message
		    	  else if (loginError.equals("That account does not exist"))
		    	  {
		    		  usernameError.setText(loginError);
		    		  passwordError.setText("");
	    			  programView.getContentPane().repaint();
		    	  }
		    	  // Else if login returned password incorrect error message
		    	  else if (loginError.equals("That password is incorrect!"))
		    	  {
		    		  usernameError.setText("");
					  passwordError.setText("That password is incorrect!");
					  programView.getContentPane().repaint();
		    	  }
		      }
		});
      	
		// Adds login button to view
      	programView.add(loginButton);

		// Update GUI
        programView.getContentPane().repaint();
	}
}
