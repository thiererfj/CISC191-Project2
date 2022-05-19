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
 * Version/date: 1.8 05/19/2022
 * 
 * Responsibilities of class:
 * DeleteFileView is designed to reprint the window with GUI components allowing a user to delete FileData objects (files) from
 * User objects (accounts). This view gathers the required inputs from the user, and communicates with the current User of ProgramModel
 * to execute the task.     
 */
public class DeleteFileView
{
	int deleteThis;
	
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
		this.userMenuView = userMenuView;
		this.programView = programView;
		this.viewType = viewType;
		this.selectButtons = new JButton[10];
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
		programView.getContentPane().removeAll();
		programView.addTitleLabel("Delete File", viewTitleBoxColor, viewTextColor);
		userMenuView.addBackButton();
		programView.setVisible(true);
		initialViewCheck();
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
			programView.add(userButtonLabel);
			
			// Create JPanel for userSelectButtons
			selectButtonPanel = new JPanel();
			selectButtonPanel.setBounds(415, 300, 50, 250); //last bound was 500
			selectButtonPanel.setLayout(new GridLayout(10, 1));
			selectButtonPanel.setOpaque(true);
			
			// Loop to make file buttons and add to panel
			for (int i = 0; i < 10; i++)
			{
				selectButtons[i] = new JButton(Integer.toString(i + 1));
				selectButtons[i].setBackground(viewButtonColor);
				selectButtons[i].setForeground(viewTextColor);
				selectButtons[i].setFocusable(false);
				
				selectButtons[i].addActionListener(new SelectButtonListener(programView, selectButtons, selectButtons[i]));
				selectButtonPanel.add(selectButtons[i]);
			}
			
			programView.add(selectButtonPanel);
			programView.setVisible(true);
			
			// Create text area to display relevant user's files
			JTextArea userAccountListArea = new JTextArea(userAccountList);
			userAccountListArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
			userAccountListArea.setBounds(485, 300, 300, 250);
			userAccountListArea.setBackground(viewButtonColor);
			userAccountListArea.setForeground(viewTextColor);
			userAccountListArea.setEditable(false);
			programView.add(userAccountListArea);
			
			// Button to initiate the uploading
			JButton selectUserExecuteButton = new JButton("Select User");
			selectUserExecuteButton.setBackground(viewButtonColor);
			selectUserExecuteButton.setForeground(viewTextColor);
			selectUserExecuteButton.setFocusable(false);
			selectUserExecuteButton.setBounds(425, 600, 345, 65);
			programView.add(selectUserExecuteButton);
			
			// Add function to execute button
			selectUserExecuteButton.addActionListener(new ActionListener()
			{
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
					
					
					// Loop through file delete buttons to see which is disabled (the selected user)
					for (int i = 0; i < 10; i++) 
					{
						// If button is disabled (user clicked)
						if (!(selectButtons[i].isEnabled()))
						{
							// Try to view and delete selected users files
							try
							{
								fileList = programView.getProgramModel().getDatabase().getUsers()[i].viewUserFiles();
								deletionUserSerialNumber = i;
								
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
		programView.getContentPane().removeAll();
		programView.addTitleLabel("Delete File", viewTitleBoxColor, viewTextColor);
		userMenuView.addBackButton();
		programView.setVisible(true);
		programView.getContentPane().repaint();
		
		// Label telling user to click a file button to set file index
		JLabel fileButtonLabel = new JLabel("Select which position to delete the file from: ");
		fileButtonLabel.setBounds(415, 255, 300, 40);
		fileButtonLabel.setForeground(viewTextColor);
		programView.add(fileButtonLabel);
		
		// Create JPanel for fileDeleteButtons
		selectButtonPanel = new JPanel();
		selectButtonPanel.setBounds(415, 300, 50, 250); // last bound was 500
		selectButtonPanel.setLayout(new GridLayout(10, 1));
		selectButtonPanel.setOpaque(true);
		
		// Loop to make file buttons and add to panel
		for (int i = 0; i < 10; i++)
		{
			selectButtons[i] = new JButton(Integer.toString(i + 1));
			selectButtons[i].setBackground(viewButtonColor);
			selectButtons[i].setForeground(viewTextColor);
			selectButtons[i].setFocusable(false);
			selectButtons[i].addActionListener(new SelectButtonListener(programView, selectButtons, selectButtons[i]));
			selectButtonPanel.add(selectButtons[i]);
		}
		
		programView.add(selectButtonPanel);
		programView.setVisible(true);
		
		// Create text area to display relevant user's files
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(485, 300, 300, 250);
		userFileArea.setBackground(viewButtonColor);
		userFileArea.setForeground(viewTextColor);
		userFileArea.setEditable(false);
		programView.add(userFileArea);
		
		// Button to initiate the uploading
		JButton deleteFileExecuteButton = new JButton("Delete File");
		deleteFileExecuteButton.setBackground(viewButtonColor);
		deleteFileExecuteButton.setForeground(viewTextColor);
		deleteFileExecuteButton.setFocusable(false);
		deleteFileExecuteButton.setBounds(425, 600, 345, 65);
		programView.add(deleteFileExecuteButton);
		
		// Add function to execute button
		deleteFileExecuteButton.addActionListener(new ActionListener()
		{
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
				
				int fileNumber = 0;
				
				// Loop through file delete buttons to see which is disabled (the selected file number)
				for (int i = 0; i < 10; i++) 
				{
					if (!(selectButtons[i].isEnabled()))
					{
						fileNumber = i;
					}
				}
				
				// Calls User subclass delete file method, receives error String message (null if no error)
				String deleteError = programView.getProgramModel().getCurrentUser().deleteFile(deletionUserSerialNumber, fileNumber);
				
				// Checking error messages, first if null there was no error
				if (Objects.isNull(deleteError))
				{
					JOptionPane.showMessageDialog(null, "File successfully deleted from the database");
					programView.getContentPane().removeAll();
					programView.getContentPane().repaint();
					
					// Roll back to the user's file system menu view
					userMenuView.printView();
				}
				// If there was a file number error
				else if (deleteError.equals("fileNumber"))
				{
					JOptionPane.showMessageDialog(null, "File deletion unsuccessful, check selected file number");
					
					// Reprint this deletion view to reset from error
					printView();
				}
			}
		});
	}
}
