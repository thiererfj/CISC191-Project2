package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CreateAccountView {

	//CreateAccountView has a programView
	private ProgramView programView;
	//CreateAccountView has a programModel
	private ProgramModel programModel;
	//CreateAccountView has a basicUserButton
	private JButton basicUserButton;
	//CreateAccountView has a superUserButton
    private JButton superUserButton;
    //CreateAccountView has an accountInstanceButton
    private JButton accountInstanceButton;
	
	/**
	 * Purpose: Constructor for CreateAccountView
	 * @param programView
	 * @param programModel
	 */
    CreateAccountView(ProgramView programView, ProgramModel programModel)
	{
		this.programView = programView;
		this.programModel = programModel;
		//Calls the method that shows CreateAccountView's GUI components
		createAccountVisuals();
	}
    
    /**
     * Purpose: Creates the components for the GUI of the CreateAccountView
     */
    public void createAccountVisuals()
    {
        programView.getContentPane().removeAll();

        programView.getContentPane().setBackground(Color.black);
        //GUI Visual Code for creating an account goes here
        
        // Add back to main menu button functionality
        programView.addBackButton();
        
        // Add title JLabel to window
        programView.addTitleLabel("Create Account", Color.LIGHT_GRAY, Color.WHITE);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.black);
        buttonPanel.setBounds(300, 325, 400, 50);
        
        //If no super user
        if(!programModel.getDatabase().superUserExists())
        {
        	superUserButton = new JButton("Super User");
            superUserButton.setBackground(Color.gray);
            superUserButton.setFocusable(false);
            buttonPanel.add(superUserButton);
            superUserButton.addActionListener(new ActionListener()
    	    {
    		      public void actionPerformed(ActionEvent e)
    		      {
    		    	  superUserButton.setEnabled(false);
    		    	  basicUserButton.setEnabled(true);
    		    	  accountInstanceButton.setEnabled(true);
                    
    		    	 
    		      }
    		});
            
        	
        }
        
        
        //Click if you want to make basic user account
        basicUserButton = new JButton("Basic User");
        basicUserButton.setBackground(Color.gray);
        basicUserButton.setFocusable(false);

        basicUserButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	 basicUserButton.setEnabled(false);
		    	 accountInstanceButton.setEnabled(true);
		    	 
		    	 // If no super user exists
				 if(!programModel.getDatabase().superUserExists())
		    	 {
		    		 superUserButton.setEnabled(true);
		    	 }
		    	 
		      }
		});

        buttonPanel.add(basicUserButton);
        
        // Add button panel to window
        programView.add(buttonPanel);


        //Asking for Username
		JLabel enterName = new JLabel("Create a Username:");
		enterName.setBounds(250, 375, 150, 50);
		enterName.setForeground(Color.white);
		programView.add(enterName);
		
		//Username Text Box
		JTextArea userName = new JTextArea();
		userName.setBounds(250, 425, 500, 50);
		userName.setFont(new Font("Times New Roman", Font.BOLD, 30));
		programView.add(userName);
		
		
		
		//Asking for Password
		JLabel enterPassword = new JLabel("Create a Password:");
		enterPassword.setBounds(250, 475, 150, 50);
		enterPassword.setForeground(Color.white);
		programView.add(enterPassword);
		
		//User password Text Box
		JTextArea userPassword = new JTextArea();
		userPassword.setBounds(250, 525, 500, 50);
		userPassword.setFont(new Font("Times New Roman", Font.BOLD, 30));
		programView.add(userPassword);

		JTextArea usernameError = new JTextArea("");
        usernameError.setBounds(775, 433, 150, 50);
        usernameError.setBackground(Color.black);
        usernameError.setForeground(Color.red);
        programView.add(usernameError);
        
        JTextArea passwordError = new JTextArea();
        passwordError.setBounds(775, 533, 150, 50);
        passwordError.setBackground(Color.black);
        passwordError.setForeground(Color.red);
        programView.add(passwordError);
		
		
        accountInstanceButton = new JButton("Create Account");
        accountInstanceButton.setBounds(400, 600, 200, 50);
        accountInstanceButton.setEnabled(false);
        accountInstanceButton.setBackground(Color.gray);
        accountInstanceButton.setFocusable(false);
        accountInstanceButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  //Somehow we want a user object to be made.
		    	  String userType = "";
		    	  String username = userName.getText();
		    	  String password = userPassword.getText();
		    	  //If basic user Button is disabled   (I.E. We are creating a basic user)
		    	  if(!basicUserButton.isEnabled())
		    	  {
		    		  userType = "basic";
		    	  }
		    	  else
		    	  {
		    		  userType = "super";
		    	  }
		    	  
		    	  
		    	  //FOR LATER  --> If username and password input is allowed  --> Add user to database
		    	  if(programModel.getDatabase().findUsername(username)== null)
		    	  {
		    		  
		    		  if(username.equals(""))
		    		  {
		    			  usernameError.setText("Please enter a Username");
		    			  
		    			  if(password.equals(""))
		    			  {
		    				  passwordError.setText("Please enter a password");
		    			  }
		    			  else
		    			  {
		    				  passwordError.setText("");
		    			  }
		    			  programView.getContentPane().repaint();
		    			  
		    		  }
		    		  if(password.equals(""))
		    		  {
		    			  passwordError.setText("Please enter a password");
		    			  if(username.equals(""))
			    		  {
			    			  usernameError.setText("Please enter a Username");
			    		  }
		    			  else
		    			  {
		    				  usernameError.setText("");
		    			  }
		    			  programView.getContentPane().repaint();
		    		  }
		    		  
		    		  if(username.length() > 15)
		    		  {
		    			  usernameError.setText("Username cannot be greater\n than 15 characters");
		    			  
		    			  if(password.equals(""))
		    			  {
		    				  passwordError.setText("Please enter a password");
		    			  }
		    			  else
		    			  {
		    				  passwordError.setText("");
		    			  }
		    			  programView.getContentPane().repaint();
		    		  }
		    		  
		    		  if(!username.equals("") && !password.equals("") && username.length() < 16)
		    		  {
		    			  programModel.createNewAccount(userType, username, password);
				    	  programView.getContentPane().removeAll();
				    	  programView.getContentPane().repaint();
				    	  //System.out.println(DATABASE.getUsers()[0].getUsername());
				    	  programView.printMainMenu(); 
		    		  }
		    		  
		    		  
		    		  
		    		  
		    	  }
		    	  else
		    	  {
		    		 JTextArea userNameAlreadyTaken = new JTextArea("That Username has \nalready been taken");
		    		 userNameAlreadyTaken.setBounds(775, 433, 150, 50);
		    		 userNameAlreadyTaken.setBackground(Color.black);
		    		 userNameAlreadyTaken.setForeground(Color.red);
		    		 programView.add(userNameAlreadyTaken);
		    		 programView.getContentPane().repaint();
		    	  }
		    	
		    	  
		    	  
		 
		    	  
		      }
		});
        programView.add(accountInstanceButton);
        
        
        //String username = userNameArea.getText(); 
        //String password = passwordArea.getText();
        
        
        //Set Username / Set Password TextBoxes
        //Basic and SuperUser JButtons      (Which will disable if Super User is already created)
        
        programView.setVisible(true);
        programView.getContentPane().repaint();
    }
}
