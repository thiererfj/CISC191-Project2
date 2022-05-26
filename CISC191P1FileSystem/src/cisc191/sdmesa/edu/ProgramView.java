package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * Lead Author(s):
 * @author Anthony Mayoral
 * @author Francis Thierer
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *  
 * Version/date: 4.5 05/25/22
 * 
 * Responsibilities of class:
 * ProgramView is designed to initialize the main window that will display all the different views with unique GUI components
 * throughout the program. It also displays the initial "main menu" view that allows a user to create User accounts, log in to them,
 * view them, and then quit the program while saving the data entered during that session. Exiting the program at any time by
 * clicking the upper right "X" will NOT save anything. This class also sets the color scheme used everywhere.      
 */
public class ProgramView extends JFrame 
{
	// String to display program version in window title bar
	private final String VERSION = "4.5";
	
	// ProgramView has a ProgramModel to execute the program functions
	private ProgramModel programModel;
	
	// Color objects to set the color scheme
	private Color viewBackgroundColor = Color.decode("#023859");
	private Color viewTitleBoxColor = Color.decode("#034B8A");
	private Color viewButtonColor = Color.decode("#B59C8D");
	private Color viewTextColor = Color.decode("#F2F2F2");

	/**
	 * Purpose: Constructor for ProgramView that sets information for the JFrame
	 * 
	 * @param DATABASE
	 */
	public ProgramView(ProgramModel programModel)
	{
		// Set programModel
		this.programModel = programModel;
		
		// Set final window size
		setSize(1200, 800);
		
		// Set default window closing execution
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Do not allow window resizing
		setResizable(false);
		
		// Show window in center of screen
		setLocationRelativeTo(null);
		
		// Set window to have no layout
		setLayout(null);
		
		// Set window title
		setTitle("GUI File System - Version: " + VERSION);
	}
	
	/**
	 * @return ProgramModel instance
	 */
	public ProgramModel getProgramModel()
	{
		return programModel;
	}
	
	/**
	 * @return background color
	 */
	public Color getViewBackgroundColor() 
	{
		return viewBackgroundColor;
	}
	
	/**
	 * @return title box color
	 */
	public Color getViewTitleBoxColor() 
	{
		return viewTitleBoxColor;
	}
	
	/**
	 * @return text color
	 */
	public Color getViewTextColor()
	{
		return viewTextColor;
	}
	
	/**
	 * @return button color
	 */
	public Color getViewButtonColor()
	{
		return viewButtonColor;
	}

	/**
	 * Purpose: Changes view to the PrintMainMenu view
	 * 
	 * @throws IOException for audio exceptions or data saving exceptions (shouldn't happen)
	 */
	public void printMainMenu() 
	{
		// Set black background here so sub frame doesn't change it when going back to main menu
		getContentPane().setBackground(viewBackgroundColor);

		// Add title box label to window
		addTitleLabel("GUI File System", viewTitleBoxColor, viewTextColor);
		
		// Create button panel for user option buttons, set colors, bounds, visibility, and add to window
		JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 20, 20));
		buttonPanel.setOpaque(true);
		buttonPanel.setBackground(viewBackgroundColor);
		buttonPanel.setBounds(150, 600, 900, 100);
		buttonPanel.setVisible(true);
		add(buttonPanel);
		
		// If not all User accounts have been created yet, create account button will be present
		if (!programModel.getDatabase().allUsersCreated())
		{
			// Create create account button, set colors, set focus, and add to window
			JButton createAccountButton = new JButton("Create Account");
			createAccountButton.setBackground(viewButtonColor);
			createAccountButton.setForeground(viewTextColor);
			createAccountButton.setFocusable(false);
			buttonPanel.add(createAccountButton);
			
			// Add action listener to create account button
			createAccountButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					// Try to play click sound
					try
					{
						clickSound();
					}
					// Catch possible exceptions
					catch (Exception e1)
					{
						// Do nothing, sound will not play 
					}
					
					// Run create account visuals 
					createAccountVisuals();
				}
			});
		}
		
		// Create log in button, set colors, set focus, and add to window
		JButton loginButton = new JButton("Log In");
		loginButton.setBackground(viewButtonColor);
		loginButton.setForeground(viewTextColor);
		loginButton.setFocusable(false);
		buttonPanel.add(loginButton);
		
		// Add action listener to log in button
		loginButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Run login visuals
				loginVisuals();
				
				// Try to play click sound
				try
				{
					clickSound();
				}
				// Catch possible exceptions
				catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		
		// Create view accounts button, set colors, set focus, and add to window
		JButton viewUserAccountsButton = new JButton("View Accounts");
		viewUserAccountsButton.setBackground(viewButtonColor);
		viewUserAccountsButton.setForeground(viewTextColor);
		viewUserAccountsButton.setFocusable(false);
		buttonPanel.add(viewUserAccountsButton);
		
		// Add action listener to view accounts button
		viewUserAccountsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Run view user accounts visuals
				viewUserAccountsVisuals();
				
				// Try to play click sound
				try
				{
					clickSound();
				}
				catch (Exception e1)
				{
					// Do nothing, sound will not play
				}
			}
		});
		
		// Create quit program button, set colors, set focus, and add to window
		JButton quitProgramButton = new JButton("Quit Program (Save Data)");
		quitProgramButton.setBackground(viewButtonColor);
		quitProgramButton.setForeground(viewTextColor);
		quitProgramButton.setFocusable(false);
		buttonPanel.add(quitProgramButton);
		
		// Add action listener to quit program button
		quitProgramButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Try to play click sound
				try
				{
					clickSound();
				}
				catch (Exception e1)
				{
					// Do nothing, sound will not play
				}
	
				// Try to run close program visuals
				try
				{
					closeProgramVisuals();
				}
				// Catch possible exception from data saving function
				catch (IOException e1)
				{
					// Should not happen, but print error dialog box if so
					JOptionPane.showMessageDialog(null, "Oopsie! Your data cannot be saved, and you'll have to exit out of the window to quit");
				}
			}
		});

		// Make main window visible
		setVisible(true);
	}

	/**
	 * Purpose: Changes view to the CreateAccountVisuals Menu
	 */
	private void createAccountVisuals()
	{
		// Create new CreateAccountView object to display create account functionality
		CreateAccountView createAccountView = new CreateAccountView(this);
	}

	/**
	 * Purpose: Changes view to the LoginVisuals Menu
	 */
	private void loginVisuals()
	{
		// Create new LoginView object to display log in functionality
		LoginView loginView = new LoginView(this);
	}

	/**
	 * Purpose: Changes view to the ViewUserAccounts Menu
	 */
	private void viewUserAccountsVisuals()
	{
		// Create new ViewUserAccountsView object to display view user accounts functionality
		ViewUserAccountsView viewUserAccountsView = new ViewUserAccountsView(this);
	}

	/**
	 * Purpose: Terminates the program
	 * 
	 * @throws IOException (should never happen)
	 */
	private void closeProgramVisuals() throws IOException
	{
		// Create new QuitView object to display quit program functionality
		QuitView quitView = new QuitView(this);
	}

	/**
	 * Purpose: To add a back button to a certain menu view
	 */
	public void addBackButton()
	{
		// Create back button, set colors, set focus, set bounds, and add to window
		JButton backButton = new JButton("Back");
		backButton.setBackground(viewButtonColor);
		backButton.setForeground(viewTextColor);
		backButton.setFocusable(false);
		backButton.setBounds(20, 20, 70, 40);
		add(backButton);
		
		// Add action listener to back button 
		backButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Reset window
				getContentPane().removeAll();
				getContentPane().repaint();
				
				// Try to play click sound
				try
				{
					clickSound();
				}
				catch (Exception e1)
				{
					// Do nothing, sound will not play
				}
				
				printMainMenu();
			}
		});
	}

	/**
	 * Purpose: To add a title label to a certain menu view
	 * 
	 * @param title
	 * @param backgroundColor
	 * @param foregroundColor
	 */
	public void addTitleLabel(String title, Color backgroundColor, Color foregroundColor)
	{
		// Create title label, set alignment, set colors, set bounds, set font, and add to window
		JLabel titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setOpaque(true);
		titleLabel.setBackground(backgroundColor);
		titleLabel.setBounds(400, 50, 400, 150);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		titleLabel.setForeground(foregroundColor);
		add(titleLabel);
	}
	
	/**
	 * Play an audio file containing a clicking sound
	 * 
	 * @throws LineUnavailableException
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 */
	public void clickSound() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		// Create file object containing audio file
		File clickSound = new File("ClickSound.wav");
		
		// Create audio input stream from audio file
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(clickSound);
        
        // Creates clip object instance in order to open and start the audio file
        Clip clip = AudioSystem.getClip();
        
        // Open clip in audio stream 
        clip.open(audioStream);
        
        // Play the clip 
        clip.start();
	}

}
