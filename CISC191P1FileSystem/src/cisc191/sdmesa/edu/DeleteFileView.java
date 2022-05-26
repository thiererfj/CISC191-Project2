package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
 * Version/date: 4.5 05/25/22
 * 
 * Responsibilities of class:
 * DeleteFileView is designed to reprint the window with GUI components allowing a user to delete FileData objects (files) from
 * User objects (accounts). This view gathers the required inputs from the user, and communicates with the current User of ProgramModel
 * to execute the task.     
 */
public class DeleteFileView
{
	// DeleteFileView has a UserMenuView
	private UserMenuView userMenuView;
	// DeleteFileView has a ProgramView
	private ProgramView programView;
	// String to hold list of user files
	private String fileList;
	// JPanel for file selection buttons
	private JPanel selectButtonPanel;
	// JButton array for the selection buttons
	private JButton[] selectButtons;
	// Index of User in database to delete from 
	private int deletionUserSerialNumber;
	
	// Color objects to set the color scheme
	private Color viewBackgroundColor;
	private Color viewTitleBoxColor;
	private Color viewButtonColor;
	private Color viewTextColor;
	
	// Changes display depending on User type
	private int viewType;
	
	/**
	 * Construct DeleteFileView object 
	 * 
	 * @param userMenuView
	 * @param programView
	 * @param viewType
	 */
	public DeleteFileView (UserMenuView userMenuView, ProgramView programView, int viewType) 
	{
		//Sets userMenuView to the one in the parameter
		this.userMenuView = userMenuView;
		//Sets programView to the one in the parameter
		this.programView = programView;
		//Sets viewType to the one in the parameter
		this.viewType = viewType;
		//Initializes the selectButtons array to a JButton array with 10 index's
		this.selectButtons = new JButton[10];

		//Sets Color variables to the ones inside programView to allow for cleaner code
		this.viewBackgroundColor = programView.getViewBackgroundColor();
		this.viewTitleBoxColor = programView.getViewTitleBoxColor();
		this.viewButtonColor = programView.getViewButtonColor();
		this.viewTextColor = programView.getViewTextColor();
		
		// Begin showing reprinted view
		printView();
	}
	
	/**
	 * Reprints window, beings initial check for which view to display
	 */
	private void printView() 
	{
		//Removes all of the components from the frame
		programView.getContentPane().removeAll();
		//Adds a title label given 3 parameters
		programView.addTitleLabel("Delete File", viewTitleBoxColor, viewTextColor);
		//Adds a back button that will allow the user to go to the previous menu
		userMenuView.addBackButton();
		//Allows the frame to be visible
		programView.setVisible(true);
		//Calls the initialViewCheck to determine whether the user is super or basic
		initialViewCheck();
		//Repaints the frame
		programView.getContentPane().repaint();
	}
	
	/**
	 * Changes view depending on User type, 1 for Basic, 2 for Super
	 */
	private void initialViewCheck() 
	{
		// If current user is Basic, they don't get to choose which account to delete from
		if (viewType == 1) 
		{
			// Set file list to current user's file String representation 
			fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
			
			// Set number to current user's serial number (used for file deletion parameter)
			deletionUserSerialNumber = programView.getProgramModel().getCurrentUser().getSerialNumber();
			
			// Run view and deletion view
			viewAndDeleteFiles();
		}
		// If current user is Super, they do get to choose which account to delete from
		else if (viewType == 2)
		{
			// Create String representing User accounts in database by username
			String userAccountList = programView.getProgramModel().viewUserAccounts();
			
			// Label telling user to click a user account button to set user account to delete file from 
			JLabel userButtonLabel = new JLabel("Select which user account to delete file from: ");
			userButtonLabel.setBounds(415, 255, 300, 40);
			userButtonLabel.setForeground(viewTextColor);
			//Adds userButtonLabel to the frame
			programView.add(userButtonLabel);
			
			// Create JPanel for userSelectButtons
			selectButtonPanel = new JPanel();
			selectButtonPanel.setBounds(415, 300, 50, 250); //last bound was 500
			selectButtonPanel.setLayout(new GridLayout(10, 1));
			selectButtonPanel.setOpaque(true);
			
			// Loop to make file buttons and add to panel
			for (int i = 0; i < 10; i++)
			{
				//Sets the current iteration of the array to a new button labeled as i + 1
				selectButtons[i] = new JButton(Integer.toString(i + 1));
				selectButtons[i].setBackground(viewButtonColor);
				selectButtons[i].setForeground(viewTextColor);
				selectButtons[i].setFocusable(false);
				
				//Adds an ActionListener to each button
				selectButtons[i].addActionListener(new SelectButtonListener(programView, selectButtons, selectButtons[i]));
				//Adds each button to selectButtonPanel
				selectButtonPanel.add(selectButtons[i]);
			}
			
			//Adds selectButtonPanel to the frame
			programView.add(selectButtonPanel);
			//Allows the frame to be visible
			programView.setVisible(true);
			
			//Create text area to display relevant user's files
			JTextArea userAccountListArea = new JTextArea(userAccountList);
			userAccountListArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
			userAccountListArea.setBounds(485, 300, 300, 250);
			userAccountListArea.setBackground(viewButtonColor);
			userAccountListArea.setForeground(viewTextColor);
			userAccountListArea.setEditable(false);
			//Adds userAccountListArea to the frame
			programView.add(userAccountListArea);
			
			//Button to initiate the uploading
			JButton selectUserExecuteButton = new JButton("Select User");
			selectUserExecuteButton.setBackground(viewButtonColor);
			selectUserExecuteButton.setForeground(viewTextColor);
			selectUserExecuteButton.setFocusable(false);
			selectUserExecuteButton.setBounds(425, 600, 345, 65);
			//Adds selectUserExecuteButton to the frame
			programView.add(selectUserExecuteButton);
			
			// Add function to execute button
			selectUserExecuteButton.addActionListener(new ActionListener()
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
					
					
					// Loop through file delete buttons to see which is disabled (the selected user)
					for (int i = 0; i < 10; i++) 
					{
						// If button is disabled (user clicked)
						if (!(selectButtons[i].isEnabled()))
						{
							// Try to view and delete selected users files
							try
							{
								//Sets fileList to the current users files
								fileList = programView.getProgramModel().getDatabase().getUsers()[i].viewUserFiles();
								//The Serial number to the account we want to relate to
								deletionUserSerialNumber = i;
								
								//Calls the viewAndDeleteFiles method
								viewAndDeleteFiles();
							}
							// Handle npe if user selected null User account
							catch (NullPointerException e2)
							{
								// Print error message to screen
								JOptionPane.showMessageDialog(null, "That user account does not exist");
								
								// Reprint this deletion view to reset from error
								printView();
							}
						}
					}
				}
			});
		}
	}
	
	/**
	 * Display selected User's files to get deletion input
	 */
	private void viewAndDeleteFiles() 
	{
		//Removes all of the components from the frame
		programView.getContentPane().removeAll();
		programView.addTitleLabel("Delete File", viewTitleBoxColor, viewTextColor);
		userMenuView.addBackButton();
		programView.setVisible(true);
		//Repaints the frame
		programView.getContentPane().repaint();
		
		//Label telling user to click a file button to set file index
		JLabel fileButtonLabel = new JLabel("Select which position to delete the file from: ");
		fileButtonLabel.setBounds(415, 255, 300, 40);
		fileButtonLabel.setForeground(viewTextColor);
		//Adds fileButtonLabel to the frame
		programView.add(fileButtonLabel);
		
		// Create JPanel for fileDeleteButtons
		selectButtonPanel = new JPanel();
		selectButtonPanel.setBounds(415, 300, 50, 250); // last bound was 500
		selectButtonPanel.setLayout(new GridLayout(10, 1));
		selectButtonPanel.setOpaque(true);
		
		// Loop to make file buttons and add to panel
		for (int i = 0; i < 10; i++)
		{
			//For each iteration of the array it is set to a new button labeled i + 1
			selectButtons[i] = new JButton(Integer.toString(i + 1));
			selectButtons[i].setBackground(viewButtonColor);
			selectButtons[i].setForeground(viewTextColor);
			selectButtons[i].setFocusable(false);
			//An actionListener will be added to each button
			selectButtons[i].addActionListener(new SelectButtonListener(programView, selectButtons, selectButtons[i]));
			//Each button will be added to the selectButtonPanel
			selectButtonPanel.add(selectButtons[i]);
		}
		
		//Adds selectButtonPanel to the frame
		programView.add(selectButtonPanel);
		//Allows the frame to be visible
		programView.setVisible(true);
		
		// Create text area to display relevant user's files
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(485, 300, 300, 250);
		userFileArea.setBackground(viewButtonColor);
		userFileArea.setForeground(viewTextColor);
		userFileArea.setEditable(false);
		//Adds userFileArea to the frame
		programView.add(userFileArea);
		
		// Button to initiate the uploading
		JButton deleteFileExecuteButton = new JButton("Delete File");
		deleteFileExecuteButton.setBackground(viewButtonColor);
		deleteFileExecuteButton.setForeground(viewTextColor);
		deleteFileExecuteButton.setFocusable(false);
		deleteFileExecuteButton.setBounds(425, 600, 345, 65);
		//Adds deleteFileExecuteButton to the frame
		programView.add(deleteFileExecuteButton);
		
		//Adds actionListener to deleteFileExecuteButton
		deleteFileExecuteButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//We will try to call the clickSound method	
				try {
					programView.clickSound();
				} 
				//Will catch any Exception
				catch (Exception e1) 
				{
				}
				
				//Initializes a variable that keeps track of which button was clicked
				int fileNumber = 0;
				
				// Loop through file delete buttons to see which is disabled (the selected file number)
				for (int i = 0; i < 10; i++) 
				{
					//If the current button is disabled...
					if (!(selectButtons[i].isEnabled()))
					{
						//Set the fileNumber to this buttons number
						fileNumber = i;
					}
				}
				
				// Calls User subclass delete file method, receives error String message (null if no error)
				String deleteError = programView.getProgramModel().getCurrentUser().deleteFile(deletionUserSerialNumber, fileNumber);
				
				// Checking error messages, first if null there was no error
				if (Objects.isNull(deleteError))
				{
					//Shows the user a JOptionPane saying the file was deleted
					JOptionPane.showMessageDialog(null, "File successfully deleted from the database");
					//Removes all of the components from the frame
					programView.getContentPane().removeAll();
					
					// Roll back to the user's file system menu view
					userMenuView.printView();
				}
				// If there was a file number error
				else if (deleteError.equals("fileNumber"))
				{
					//Shows the user a JOptionPane saying the file was not deleted
					JOptionPane.showMessageDialog(null, "File deletion unsuccessful, check selected file number");
					
					// Reprint this deletion view to reset from error
					printView();
				}
			}
		});
	}
}
