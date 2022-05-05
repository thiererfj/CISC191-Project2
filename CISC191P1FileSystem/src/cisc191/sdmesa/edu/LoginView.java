package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class LoginView {
	
	
	LoginView(ProgramView programView, Database DATABASE)
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
		      public void actionPerformed(ActionEvent e)
		      {
		    	  //Somehow we want a user object to be made.
		    	  String userType = "";
		    	  String username = userName.getText();
		    	  String password = userPassword.getText();
		    	  
		    	  
		    	  
		    	  
		    	  User loginAttempt = DATABASE.findUsername(username);
		    	  
		    	  if (Objects.isNull(loginAttempt))
		    	  {
		    		  usernameError.setText("That account doesn't exist");
		    		  passwordError.setText("");
	    			  programView.getContentPane().repaint();
		    	  }
		    	  
		    	  else
		    	  {
		    		  usernameError.setText("");
		    		  
		    		  if(password.equalsIgnoreCase(loginAttempt.getPassword()))
		    		  {
		    			  programView.setCurrentUser(loginAttempt);
		    			  programView.getCurrentUser().setIsActive(true); //At the moment I don't remember why we need this
		    			  
		    			  BasicUserMenuView userMenuView = new BasicUserMenuView(programView);
		    		  }
		    		  else
		    		  {
		    			  passwordError.setText("That password is incorrect!");
		    			  programView.getContentPane().repaint();
		    		  }
		    	  }
		      }
		});
      	
      	
      	
  
     		
     		
      	
      	
      	
      	
      	programView.add(loginButton);
      	
        programView.getContentPane().repaint();
	}
}
