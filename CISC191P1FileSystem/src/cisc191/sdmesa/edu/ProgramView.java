package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ProgramView extends JFrame
{
	String accountType = "";
	
    
    public ProgramView()
    {
        setSize(1000, 1000);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.black);
        setLayout(null);

        
        

        //For the first frame
        //Program Title --> 
        //Create Account/Log in to User Account / View User Accounts / Quit Program

    }
    
    public void printMainMenu() 
    {
    	JLabel programTitle = new JLabel("Angry File System");
        programTitle.setHorizontalAlignment(JLabel.CENTER);
        programTitle.setOpaque(true);
        programTitle.setBackground(Color.lightGray);
        programTitle.setBounds(250, 100, 500, 200);
        programTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
        programTitle.setForeground(Color.white);
        
        // Add title label to main window
        add(programTitle);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 20, 20));
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.black);
        buttonPanel.setBounds(100, 600, 800, 100);
        
        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setBackground(Color.gray);
        createAccountButton.setFocusable(false);
        buttonPanel.add(createAccountButton);

        createAccountButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	createAccountVisuals();
		      }
		});

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
        
        // Add button panel to main window
        add(buttonPanel);
        
        // Make main window visible
        setVisible(true);
    }

    

    public void createAccountVisuals()
    {
        getContentPane().removeAll();

        getContentPane().setBackground(Color.black);
        //GUI Visual Code for creating an account goes here
        
        JLabel createAccountTitle = new JLabel("Create Account");
        createAccountTitle.setHorizontalAlignment(JLabel.CENTER);
        createAccountTitle.setOpaque(true);
        createAccountTitle.setBackground(Color.lightGray);
        createAccountTitle.setBounds(250, 100, 500, 200);
        createAccountTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
        createAccountTitle.setForeground(Color.white);
        add(createAccountTitle);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.black);
        buttonPanel.setBounds(300, 400, 400, 50);
        
        //Click if you want to make super user account
        JButton superUserButton = new JButton("Super User");
        superUserButton.setBackground(Color.gray);
        superUserButton.setFocusable(false);
        buttonPanel.add(superUserButton);    

        superUserButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	accountType = "Super";
                
		    	 
		      }
		});

        //Click if you want to make basic user account
        JButton basicUserButton = new JButton("Basic User");
        basicUserButton.setBackground(Color.gray);
        basicUserButton.setFocusable(false);

        basicUserButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	 accountType = "basic";
		      }
		});

        buttonPanel.add(basicUserButton);

      
        // SuperUser Button, if clicked --> userType = Super
        
        //boolean accountState;    <--- false = basic user   true = super user

        //superUserButton ActionListener  ---> actionPerformec { accountState = false  ANDDDDD submitButton.setEnabled(true) }

        //basicUserButton ActionListener ---> actionPerformed {accountState = true  ANDDDDD submitButton.setEnabled(true)   }

        //BasicUser Button, if clicked --> userType = Basic

        //If username text box value  is already taken -
        // if superUser or basic user button is not clicked, do not enable the submit button

        //WHen submit button is clicked --> Save database.user[][i] = usernametextarea.getText();



        //Asking for Username
		JLabel enterName = new JLabel("Create a Username");
		enterName.setBounds(250, 500, 150, 50);
		enterName.setForeground(Color.white);
		add(enterName);
		//Player Name Text Box
		JTextArea playerName = new JTextArea("Username here");
		playerName.setBounds(250, 550, 500, 50);
		playerName.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(playerName);
		
		
		
		//Asking for Password
		JLabel enterPassword = new JLabel("Create a Password");
		enterPassword.setBounds(250, 600, 150, 50);
		enterPassword.setForeground(Color.white);
		add(enterPassword);
		//Player Password Text Box
		JTextArea playerPassword = new JTextArea("Password here");
		playerPassword.setBounds(250, 650, 500, 50);
		playerPassword.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(playerPassword);





        //JTextArea userNameArea = new JTextArea("Username Here");

        //JTextArea passwordArea = new JTextArea("Password Here");

        JButton accountInstanceButton = new JButton("Create Account");
        
        
        //String username = userNameArea.getText(); 
        //String password = passwordArea.getText();
        
        
        //Set Username / Set Password TextBoxes
        //Basic and SuperUser JButtons      (Which will disable if Super User is already created)
        
        
        add(buttonPanel);
        setVisible(true);

        getContentPane().repaint();
    }


    public void loginVisuals()
    {
        getContentPane().removeAll();

        getContentPane().setBackground(Color.orange);
        //GUI Visual Code for creating an account goes here



        JLabel loginTitle = new JLabel("Login");
        loginTitle.setHorizontalAlignment(JLabel.CENTER);
        loginTitle.setOpaque(true);
        loginTitle.setBackground(Color.lightGray);
        loginTitle.setBounds(250, 100, 500, 200);
        loginTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
        loginTitle.setForeground(Color.white);



        //Set Username / Set Password TextBoxes
        //Basic and SuperUser JButtons      (Which will disable if Super User is already created)


        getContentPane().repaint();
    }
    
   
   
   public void viewUserAccountsVisuals()
    {
        getContentPane().removeAll();

        getContentPane().setBackground(Color.blue);
        //GUI Visual Code for creating an account goes here


        JLabel loginTitle = new JLabel("Accounts");
        loginTitle.setHorizontalAlignment(JLabel.CENTER);
        loginTitle.setOpaque(true);
        loginTitle.setBackground(Color.lightGray);
        loginTitle.setBounds(250, 100, 500, 200);
        loginTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
        loginTitle.setForeground(Color.white);


        //Set Username / Set Password TextBoxes
        //Basic and SuperUser JButtons      (Which will disable if Super User is already created)


        getContentPane().repaint();
    }

    public void closeProgramVisuals()
    {
        getContentPane().removeAll();


        getContentPane().setBackground(Color.black);

        JLabel goodByeMessage = new JLabel("See you later friend! ;)");
        goodByeMessage.setForeground(Color.white);
        goodByeMessage.setFont(new Font("Times New Roman", Font.BOLD, 40));
        goodByeMessage.setBounds(100,500, 900, 900);
        add(goodByeMessage);
        getContentPane().repaint();
        try
		{
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        dispose();




       
    }



}   

