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

public class LoginView 
{
	// LoginView has a ProgramView
	private ProgramView programView;
	
	private Color viewBackgroundColor;
	private Color viewTitleBoxColor;
	private Color viewButtonColor;
	private Color viewTextColor;

	// Constructor
	public LoginView (ProgramView programView)
	{
		this.programView = programView;
		this.viewBackgroundColor = programView.getViewBackgroundColor();
		this.viewTitleBoxColor = programView.getViewTitleBoxColor();
		this.viewButtonColor = programView.getViewButtonColor();
		this.viewTextColor = programView.getViewTextColor();
		printView();
	}

	// Print the login view
	private void printView() 
	{
		programView.getContentPane().removeAll();

        programView.getContentPane().setBackground(viewBackgroundColor);
        //GUI Visual Code for creating an account goes here
        
        // Add back to main menu button functionality
        programView.addBackButton();
        
        // Add title JLabel to window
        programView.addTitleLabel("Log In", viewTitleBoxColor, viewTextColor);

        //Set Username / Set Password TextBoxes
        //Basic and SuperUser JButtons      (Which will disable if Super User is already created)
        
        // Label asking user for login username
      	JLabel enterName = new JLabel("Enter Username:");
      	enterName.setBounds(350, 375, 150, 50);
      	enterName.setForeground(viewTextColor);
     	programView.add(enterName);
     	
     	// Text area for user to enter login username
     	JTextArea userName = new JTextArea();
      	userName.setBounds(350, 425, 500, 50);
      	userName.setFont(new Font("Times New Roman", Font.BOLD, 30));
      	programView.add(userName);
     	
     	// Label asking user for login password
      	JLabel enterPassword = new JLabel("Enter Password:");
      	enterPassword.setBounds(350, 475, 150, 50);
      	enterPassword.setForeground(viewTextColor);
      	programView.add(enterPassword);
      		
      	// Text area for user to enter login password
      	JTextArea userPassword = new JTextArea();
      	userPassword.setBounds(350, 525, 500, 50);
      	userPassword.setFont(new Font("Times New Roman", Font.BOLD, 30));
      	programView.add(userPassword);
      	
		// Add Tab key listener to username JTextArea so you can type username
		// and then hit tab to switch to password JTextArea
      	// not working quite right - could be helpful if bugs worked out
//		userName.addKeyListener(new KeyAdapter()
//		{
//			@Override
//			public void keyPressed(KeyEvent e)
//			{
//				if (e.getKeyCode() == KeyEvent.VK_TAB)
//				{
//					userPassword.requestFocus();
//				}
//				e.consume();
//			}
//		});
      	
      	JTextArea usernameError = new JTextArea();
		usernameError.setBounds(860, 433, 150, 50);
		usernameError.setBackground(viewBackgroundColor);
		usernameError.setForeground(viewTextColor);
		programView.add(usernameError);
      	
		JTextArea passwordError = new JTextArea();
		passwordError.setBounds(860, 533, 150, 50);
		passwordError.setBackground(viewBackgroundColor);	
		passwordError.setForeground(viewTextColor);
		programView.add(passwordError);
      	
      	JButton loginButton = new JButton("Login");
      	loginButton.setBounds(500, 600, 200, 50);
      	loginButton.setBackground(viewButtonColor);
      	loginButton.setForeground(viewTextColor);
      	loginButton.setFocusable(false);
      	loginButton.addActionListener(new ActionListener()
	    {
		
      		// If user clicks log in button
			  public void actionPerformed(ActionEvent e)
		      {
				  try {
						programView.clickSound();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  
				  // Store login parameters
		    	  String username = userName.getText();
		    	  String password = userPassword.getText();
		    	  
		    	  String loginError = programView.getProgramModel().logIntoUser(username, password);
		    	  
		    	  // Check if login did not return error message (loginError is null)
		    	  if (Objects.isNull(loginError))
		    	  {
					// Successful login (no error returned) calls polymorphic user menu view run method
					programView.getProgramModel().getCurrentUser().runUserMenuView();
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
