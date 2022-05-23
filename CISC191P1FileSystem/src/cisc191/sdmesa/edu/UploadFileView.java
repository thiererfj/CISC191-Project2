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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * Lead Author(s):
 * @author Anthony Mayoral
 * @author Francis Thierer
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *  
 * Version/date: 4.4 05/22/22
 * 
 * Responsibilities of class:
 * UploadFileView is designed to reprint the window with GUI components allowing a user to "upload" FileData object's (files)
 * contents to the database. This view gathers requires inputs from the user, and communicates with the
 * current User of ProgramModel to execute the task.    
 */
public class UploadFileView 
{
	//UploadFileView has a userMenuView
	private UserMenuView userMenuView;
	//UploadFileView has a programView
	private ProgramView programView;
	//UploadFileView has a fileList
	private String fileList;
	//UploadFileView has a fileSelectButtonPanel
	private JPanel fileSelectButtonPanel;
	//UploadFileView has a fileSelectButtons array
	private JButton[] fileSelectButtons;
	//UploadFileView has a uploadFileButton
	private JButton uploadFileButton;
	//UploadFileView has a buttonClicked Boolean
	private boolean buttonClicked;

	//UploadFileView has Color variables
	private Color viewBackgroundColor;
	private Color viewTitleBoxColor;
	private Color viewButtonColor;
	private Color viewTextColor;
	
	/**
	 * Purpose: Constructor for UploadFileView
	 * 
	 * @param userMenuView
	 * @param programView
	 */
	public UploadFileView (UserMenuView userMenuView, ProgramView programView)
	{
		//Sets userMenuView to the one in the parameter
		this.userMenuView = userMenuView;
		//Sets programView to the one in the parameter
		this.programView = programView;
		//Initializes the fileSelectButtons array
		this.fileSelectButtons = new JButton[10];

		//Sets color variables to the color variables in programView to allow for cleaner code
		this.viewBackgroundColor = programView.getViewBackgroundColor();
		this.viewTitleBoxColor = programView.getViewTitleBoxColor();
		this.viewButtonColor = programView.getViewButtonColor();
		this.viewTextColor = programView.getViewTextColor();

		//Calls the printView method
		printView();
	}
	
	/**
	 * Purpose: shows the Upload File Menu
	 * 
	 * 
	 */
	public void printView() 
	{
		//Deletes all of the components from the frame
		programView.getContentPane().removeAll();
		//Adds a title label to the frame with title and colors as parameters
		programView.addTitleLabel("Upload File", viewTitleBoxColor, viewTextColor);
		//Adds a backButton to the frame that will allow the user to go to the previous menu
		userMenuView.addBackButton();
		//Allows the frame to be visible
		programView.setVisible(true);
		
		//Calls the viewFiles method
		viewFiles();

		//Repaints the frame
		programView.getContentPane().repaint();
	}
	
	/**
	 * Purpose: Shows file positions of the current user in the frame
	 * 
	 * @param userMenuView
	 * @param programView
	 */
	public void viewFiles()
	{
		// Label telling user to click a file button to set file index
		JLabel fileButtonLabel = new JLabel("Select which position to upload the file to: ");
		fileButtonLabel.setBounds(100, 255, 300, 40);
		fileButtonLabel.setForeground(Color.white);
		//Adds fileButtonLabel to the frame
		programView.add(fileButtonLabel);
		
		// Loop to make file buttons
		for (int i = 0; i < 10; i++) 
		{
			//Sets the current iteration in the array to a new JButton labeled as i + 1
			fileSelectButtons[i] = new JButton(Integer.toString(i + 1));
			fileSelectButtons[i].setBackground(viewButtonColor);
			fileSelectButtons[i].setForeground(viewTextColor);
			fileSelectButtons[i].setFocusable(false);
			
			//Adds an actionListener to each button
			fileSelectButtons[i].addActionListener(new SelectButtonListener(programView, fileSelectButtons, fileSelectButtons[i]));
		}
		
		//Creates a fileSelectButtonPanel JPanel
		fileSelectButtonPanel = new JPanel();
		fileSelectButtonPanel.setBounds(100, 300, 50, 250);
		fileSelectButtonPanel.setLayout(new GridLayout(10, 1));
		fileSelectButtonPanel.setOpaque(true);
		
		// Loop to add file buttons to file button panel
		for (int i = 0; i < 10; i++) 
		{
			fileSelectButtonPanel.add(fileSelectButtons[i]);
		}
		
		//Adds fileSelectButtonPanel to the frame
		programView.add(fileSelectButtonPanel);
		//Allows the frame to be visible
		programView.setVisible(true);
		
		//Call User view files method to get formatted String representation of files in database
		fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
		
		//Create text area to display user's files
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(170, 300, 300, 250);
		userFileArea.setBackground(viewButtonColor);
		userFileArea.setForeground(viewTextColor);
		userFileArea.setEditable(false);
		//Adds userFileArea to the frame
		programView.add(userFileArea);
		
		//Label asking user to enter the origin file path, to grab file form their computer
		JLabel filePathLabel = new JLabel("Enter the origin file path of the file being uploaded: ");
		filePathLabel.setBounds(600, 305, 300, 40);
		filePathLabel.setForeground(viewTextColor);
		//Adds filePathLabel to the frame
		programView.add(filePathLabel);
		
		//Text filed for user to enter the origin file path
		JTextField filePathEntry = new JTextField();
		filePathEntry.setBounds(600, 350, 375, 50);
		filePathEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		//Adds filePathEntry to the frame
		programView.add(filePathEntry);
		
		//Label asking for user to enter the desired database file name
		JLabel fileNameLabel = new JLabel("Enter the desired file name: ");
		fileNameLabel.setBounds(600, 415, 300, 40);
		fileNameLabel.setForeground(viewTextColor);
		//Adds fileNameLabel to the frame
		programView.add(fileNameLabel);
		
		//Text field for user to enter the desired database file name
		JTextField fileNameEntry = new JTextField();
		fileNameEntry.setBounds(600, 460, 375, 50);
		fileNameEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		//Adds fileNameEntry to the frame
		programView.add(fileNameEntry);
		
		//Button to initiate the uploading
		uploadFileButton = new JButton("Upload File");
		uploadFileButton.setBackground(viewButtonColor);
		uploadFileButton.setForeground(viewTextColor);
		uploadFileButton.setFocusable(false);
		uploadFileButton.setBounds(420, 600, 345, 65);
		//Adds uploadFileButton to the frame
		programView.add(uploadFileButton);
		
		//Adds an actionListener to uploadFileButton
		uploadFileButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//We will try to call the clickSound button
				try 
				{
					programView.clickSound();
				} 
				//Will catch any Exception
				catch (Exception e1) 
				{
					// Do nothing, sound will not play
				}
				
				//Sets the filePath string to the text from the filePathEntry JTextField
				String filePath = filePathEntry.getText();
				//Sets the fileName string to the text from the FileNameEntry JTextField
				String fileName = fileNameEntry.getText();

				//Creates a fileNumber variable to differentiate between the disabled button and the enabled buttons
				int fileNumber = 0;
				
				// Loop through file buttons to see which is disabled (the selected file number)
				for (int i = 0; i < 10; i++) 
				{
					//If a button is disabled...
					if (!fileSelectButtons[i].isEnabled())
					{
						//Then the fileNumber equals that buttons number
						fileNumber = i;
						
						// Set button clicked to true
						buttonClicked = true;
					}
				}
				
				// If user has clicked a file selection button
				if (buttonClicked == true)
				{
					// Calls User subclass upload file method, receives error String message (null if no error)
					String uploadError = programView.getProgramModel().getCurrentUser().uploadFileToDatabase(filePath, fileName, fileNumber);
					
					// Checking error messages, first if null there was no error
					if (Objects.isNull(uploadError))
					{
						//Shows a JOptionPane that tells the user that the file was uploaded
						JOptionPane.showMessageDialog(null, "File successfully uploaded to the database");
						//Repaints the frame
						programView.getContentPane().removeAll();
						
						// Roll back to the user's file system menu view
						userMenuView.printView();
					}
					// If there was a file path error
					else if (uploadError.equals("filePath"))
					{
						//Shows a JOptionPane that tells the user that the file was not uploaded
						JOptionPane.showMessageDialog(null, "File upload unsuccessful, check file path");
						
						// Reprint this upload view to reset from error
						printView();
					}
					// If there was a file number error 
					else if (uploadError.equals("fileNumber"))
					{
						//Shows a JOptionPane that tells the user that the file was unsuccessful
						JOptionPane.showMessageDialog(null, "File upload unsuccessful, check selected file number");
						
						// Reprint this upload view to reset from error
						printView();
					}
				}
				// If user has not clicked any file selection button 
				else 
				{
					// Show a JOptionPane with error message
					JOptionPane.showMessageDialog(null, "You have not selected any file index");
					
					// Reprint this view
					printView();
				}
			}
		});
	}
	
}
