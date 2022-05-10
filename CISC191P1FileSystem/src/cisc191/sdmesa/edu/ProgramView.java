package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
	private final String VERSION = "1.3";
	private final Database DATABASE;
	private ProgramMenu programMenu;
	private UserFactory factory = new UserFactory();
	private String accountType = "";
	private User currentUser;
	
    /**
     * Purpose: Constructor for ProgramView that sets information for the JFrame
     * @param DATABASE
     */
    public ProgramView(ProgramMenu programMenu, Database DATABASE)
    {
        this.programMenu = programMenu;
    	this.DATABASE = DATABASE;
		setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Angry File System - Version: " + VERSION);

        //For the first frame
        //Program Title --> 
        //Create Account/Log in to User Account / View User Accounts / Quit Program
    }
    
    
    /**
     * Purpose: Changes view to the PrintMainMenu Menu
     */
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
		    	try
				{
					closeProgramVisuals();
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				} 
		      }
		});
        
        
        
        // Make main window visible
        setVisible(true);
    }

    /**
     * Purpose: Changes view to the CreateAccountVisuals Menu
     */
    public void createAccountVisuals()
    {
    	CreateAccountView createAccountView = new CreateAccountView(this, DATABASE, factory);

    }

    /**
     * Purpose: Changes view to the LoginVisuals Menu
     */
    public void loginVisuals()
    {
    	LoginView loginView = new LoginView(this, DATABASE);

    }
   
    /**
     * Purpose: Changes view to the ViewUserAccounts Menu
     */
    public void viewUserAccountsVisuals()
    {
	   ViewUserAccountsView viewUserAccountsView = new ViewUserAccountsView(this, DATABASE);
	   viewUserAccountsView.printView();
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
        

    }

   /**
    * Purpose: Terminates the program 
 * @throws IOException 
    */
    public void closeProgramVisuals() throws IOException
    {
    	QuitView quitView = new QuitView(this, programMenu);
    	quitView.printView();
    }
    
    /**
     * Purpose: To add a back button to a certain menu view
     */
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
    
    /**
     * Purpose: To add a title label to a certain menu view
     * @param title
     * @param backgroundColor
     * @param foregroundColor
     */
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
    
    public void setCurrentUser(User user)
    {
    	currentUser = user;
    }
    
    public User getCurrentUser()
    {
    	return currentUser;
    }

}   
