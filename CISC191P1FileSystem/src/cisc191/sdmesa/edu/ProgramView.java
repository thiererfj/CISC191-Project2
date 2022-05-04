package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class ProgramView extends JFrame
{
	private final String VERSION = "1.1";
	private final Database DATABASE;
	private UserFactory factory = new UserFactory();
	private String accountType = "";
	
    
    public ProgramView(Database DATABASE)
    {
        this.DATABASE = DATABASE;
		setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setTitle("Angry File System - Version: " + VERSION);

        //For the first frame
        //Program Title --> 
        //Create Account/Log in to User Account / View User Accounts / Quit Program
    }
    
    public void printMainMenu() 
    {
    	// Set black background here so sub frame doesn't change it when going back to main menu
    	getContentPane().setBackground(Color.black);
    	
    	// Add title JLabel to window
    	addTitleLabel("Angry File System", Color.LIGHT_GRAY, Color.WHITE);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 20, 20));
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.black);
        buttonPanel.setBounds(100, 600, 800, 100);
        
        
        if(DATABASE.getUsers()[9] == null)
        {
        	JButton createAccountButton = new JButton("Create Account");
            createAccountButton.setBackground(Color.gray);
            createAccountButton.setFocusable(false);
            buttonPanel.add(createAccountButton);
            
            // Add button panel to main window
            add(buttonPanel);

            createAccountButton.addActionListener(new ActionListener()
    	    {
    		      public void actionPerformed(ActionEvent e)
    		      {
    		    	createAccountVisuals();
    		      }
    		});
        }
        

        JButton loginButton = new JButton("Log In");
        loginButton.setBackground(Color.gray);
        loginButton.setFocusable(false);
        buttonPanel.add(loginButton);
        
        loginButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	loginVisuals();
		      }
		});

        JButton viewUserAccountsButton = new JButton("View Accounts");
        viewUserAccountsButton.setBackground(Color.gray);
        viewUserAccountsButton.setFocusable(false);
        buttonPanel.add(viewUserAccountsButton);
        
        viewUserAccountsButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	viewUserAccountsVisuals();
		      }
		});

        JButton quitProgramButton = new JButton("Quit Program");
        quitProgramButton.setBackground(Color.gray);
        quitProgramButton.setFocusable(false);
        buttonPanel.add(quitProgramButton);
        
        quitProgramButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	closeProgramVisuals(); 
		      }
		});
        
        
        
        // Make main window visible
        setVisible(true);
    }

    
    
    //Stuff that we need to instantiate for this method
    JButton basicUserButton;
    JButton superUserButton;
    JButton accountInstanceButton;
    
    public void createAccountVisuals()
    {
        getContentPane().removeAll();

        getContentPane().setBackground(Color.black);
        //GUI Visual Code for creating an account goes here
        
        // Add back to main menu button functionality
        addBackButton();
        
        // Add title JLabel to window
        addTitleLabel("Create Account", Color.LIGHT_GRAY, Color.WHITE);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.black);
        buttonPanel.setBounds(300, 325, 400, 50);
        
        //If no super user
        if(DATABASE.users[0] == null)
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
        
//        superUserButton = new JButton("Super User");
//        superUserButton.setBackground(Color.gray);
//        superUserButton.setFocusable(false);
//        buttonPanel.add(superUserButton);    

        
        
        

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
		    	 
		    	 if(DATABASE.users[0] == null)
		    	 {
		    		 superUserButton.setEnabled(true);
		    	 }
		    	 
		      }
		});

        buttonPanel.add(basicUserButton);
        
        // Add button panel to window
        add(buttonPanel);

      
        // SuperUser Button, if clicked --> userType = Super
        
        //boolean accountState;    <--- false = basic user   true = super user

        //superUserButton ActionListener  ---> actionPerformec { accountState = false  ANDDDDD submitButton.setEnabled(true) }

        //basicUserButton ActionListener ---> actionPerformed {accountState = true  ANDDDDD submitButton.setEnabled(true)   }

        //BasicUser Button, if clicked --> userType = Basic

        //If username text box value  is already taken -
        // if superUser or basic user button is not clicked, do not enable the submit button

        //WHen submit button is clicked --> Save database.user[][i] = usernametextarea.getText();



        //Asking for Username
		JLabel enterName = new JLabel("Create a Username:");
		enterName.setBounds(250, 375, 150, 50);
		enterName.setForeground(Color.white);
		add(enterName);
		
		//Username Text Box
		JTextArea userName = new JTextArea();
		userName.setBounds(250, 425, 500, 50);
		userName.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(userName);
		
		
		
		//Asking for Password
		JLabel enterPassword = new JLabel("Create a Password:");
		enterPassword.setBounds(250, 475, 150, 50);
		enterPassword.setForeground(Color.white);
		add(enterPassword);
		
		//User password Text Box
		JTextArea userPassword = new JTextArea();
		userPassword.setBounds(250, 525, 500, 50);
		userPassword.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(userPassword);

        //JTextArea userNameArea = new JTextArea("Username Here");

        //JTextArea passwordArea = new JTextArea("Password Here");

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
		    	  if(DATABASE.findUsername(username)== null)
		    	  {
		    		  
		    		  if(username.equals(""))
		    		  {
		    			  JTextArea enterUsername = new JTextArea("Please enter a Username");
		    			  enterUsername.setBounds(775, 433, 150, 50);
		    			  enterUsername.setBackground(Color.black);
		    			  enterUsername.setForeground(Color.red);
		    			  add(enterUsername);
		    			  getContentPane().repaint();
		    		  }
		    		  if(password.equals(""))
		    		  {
		    			  JTextArea enterPassword = new JTextArea("Please enter a Password");
		    			  enterPassword.setBounds(775, 533, 150, 50);
		    			  enterPassword.setBackground(Color.black);
		    			  enterPassword.setForeground(Color.red);
		    			  add(enterPassword);
		    			  getContentPane().repaint();
		    		  }
		    		  
		    		  
		    		  if(!username.equals("") && !password.equals(""))
		    		  {
		    			  DATABASE.addUser(factory.buildUser(userType, username, password, DATABASE), userType);
				    	  getContentPane().removeAll();
				    	  getContentPane().repaint();
				    	  //System.out.println(DATABASE.getUsers()[0].getUsername());
				    	  printMainMenu(); 
		    		  }
		    		  
		    		  
		    	  }
		    	  else
		    	  {
		    		 JTextArea userNameAlreadyTaken = new JTextArea("That Username has \nalready been taken");
		    		 userNameAlreadyTaken.setBounds(775, 433, 150, 50);
		    		 userNameAlreadyTaken.setBackground(Color.black);
		    		 userNameAlreadyTaken.setForeground(Color.red);
		    		 add(userNameAlreadyTaken);
		    		 getContentPane().repaint();
		    	  }
		    	
		    	  
		    	  
		 
		    	  
		      }
		});
        add(accountInstanceButton);
        
        
        //String username = userNameArea.getText(); 
        //String password = passwordArea.getText();
        
        
        //Set Username / Set Password TextBoxes
        //Basic and SuperUser JButtons      (Which will disable if Super User is already created)
        
        setVisible(true);
        getContentPane().repaint();
    }

    public void loginVisuals()
    {
        getContentPane().removeAll();

        getContentPane().setBackground(Color.orange);
        //GUI Visual Code for creating an account goes here
        
        // Add back to main menu button functionality
        addBackButton();
        
        // Add title JLabel to window
        addTitleLabel("Login", Color.LIGHT_GRAY, Color.WHITE);

        //Set Username / Set Password TextBoxes
        //Basic and SuperUser JButtons      (Which will disable if Super User is already created)

        getContentPane().repaint();
    }
   
   public void viewUserAccountsVisuals()
    {
        getContentPane().removeAll();

        getContentPane().setBackground(Color.PINK);
        //GUI Visual Code for creating an account goes here
        
        // Add back to main menu button functionality
        addBackButton();
        
        // Add title JLabel to window
        addTitleLabel("Accounts", Color.LIGHT_GRAY, Color.WHITE);

        //Set Username / Set Password TextBoxes
        //Basic and SuperUser JButtons      (Which will disable if Super User is already created)
        
        // Super user account label
        JLabel superUserLabel = new JLabel("Super User Account:");
		superUserLabel.setBounds(200, 400, 150, 50);
		superUserLabel.setForeground(Color.white);
		add(superUserLabel);
		
		if (DATABASE.users[0] == null) 
		{
			JLabel noSuperUserExistsLabel = new JLabel("No super user exists...");
			noSuperUserExistsLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
			noSuperUserExistsLabel.setBounds(160, 450, 250, 50);
			noSuperUserExistsLabel.setForeground(Color.BLACK);
			add(noSuperUserExistsLabel);
		}
		else 
		{
			// Fix this
			JList<Object> superUserList = new JList<Object>(DATABASE.getUsers());
			superUserList.setBounds(180, 450, 150, 200);
			add(superUserList);
		}
		
		// Basic user account label
		JLabel basicUsersLabel = new JLabel("Basic User Accounts:");
		basicUsersLabel.setBounds(650, 400, 150, 50);
		basicUsersLabel.setForeground(Color.white);
		add(basicUsersLabel);
		
		if (DATABASE.users[1] == null) 
		{
			JLabel noBasicUserExistsLabel = new JLabel("No basic user exists...");
			noBasicUserExistsLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
			noBasicUserExistsLabel.setBounds(610, 450, 250, 50);
			noBasicUserExistsLabel.setForeground(Color.BLACK);
			add(noBasicUserExistsLabel);
		}
		else 
		{
			// This might work
			JList<Object> basicUserList = new JList<Object>(DATABASE.getUsers());
			basicUserList.setBounds(640, 450, 150, 200);
			add(basicUserList);
		}
        

        getContentPane().repaint();
    }

    public void closeProgramVisuals()
    {
        getContentPane().removeAll();

        getContentPane().setBackground(Color.black);
        
        // 5 second countdown to close program, if user does not click "X" to exit
        Timer timer = new Timer(5000, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				System.exit(0);
			}
		});
        
        timer.start();

        JLabel goodByeMessage = new JLabel("See you later, friend! ;)");
        goodByeMessage.setBackground(Color.black);
        goodByeMessage.setForeground(Color.green);
        goodByeMessage.setOpaque(true);
        goodByeMessage.setFont(new Font("Times New Roman", Font.BOLD, 40));
        goodByeMessage.setBounds(100, 100, 800, 800);
        
        add(goodByeMessage);
        
        getContentPane().repaint();
        

    }
    
    public void addBackButton() 
    {
    	JButton backButton = new JButton("Back");
        backButton.setBackground(Color.lightGray);
        backButton.setFocusable(false);
        backButton.setBounds(20, 20, 70, 40);
        add(backButton);
        
        backButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  getContentPane().removeAll();
		    	  getContentPane().repaint();
		    	  printMainMenu(); 
		      }
		});
    }
    
    public void addTitleLabel(String title, Color backgroundColor, Color foregroundColor) 
    {
    	JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(backgroundColor);
        titleLabel.setBounds(250, 100, 500, 200);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titleLabel.setForeground(foregroundColor);
        add(titleLabel);
    }

}   
