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
import javax.swing.JTextField;

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
 * DownloadFileView is designed to reprint the window with GUI components allowing a user to "download" FileData object's (files) contents
 * to a text file on their computer. This view gathers required inputs from the user, and communicates with the current User of ProgramModel
 * to execute the task. 
 */
public class DownloadFileView
{
	// DownloadFileView has a UserMenuView
	private UserMenuView userMenuView;
	// DownloadFileView has a ProgramView
	private ProgramView programView;
	// String to hold list of user files
	private String fileList;
	// JPanel for file selection buttons
	private JPanel fileSelectButtonPanel;
	// JButton[] array for selection buttons
	private JButton[] fileSelectButtons;
	
	// Color objects to set the color scheme
	private Color viewBackgroundColor;
	private Color viewTitleBoxColor;
	private Color viewButtonColor;
	private Color viewTextColor;
	
	/**
	 * DownloadFileView constructor
	 * 
	 * @param userMenuView
	 * @param programView
	 */
	public DownloadFileView (UserMenuView userMenuView, ProgramView programView) 
	{
		//userMenuView is set to the one in the parameter
		this.userMenuView = userMenuView;
		//programView is set to the one in the parameter
		this.programView = programView;
		//The fileSelectButtons array is initialized to a JButton array with 10 index's
		this.fileSelectButtons = new JButton[10];

		//The Color variables are set to the ones in programView to allow for cleaner code
		this.viewBackgroundColor = programView.getViewBackgroundColor();
		this.viewTitleBoxColor = programView.getViewTitleBoxColor();
		this.viewButtonColor = programView.getViewButtonColor();
		this.viewTextColor = programView.getViewTextColor();
		printView();
	}
	
	/**
	 * Print the core download view to window
	 */
	private void printView() 
	{
		//Removes all components in the frame
		programView.getContentPane().removeAll();
		//Adds a title label with parameters for title and color
		programView.addTitleLabel("Download File", viewTitleBoxColor, viewTextColor);
		//Adds a back button that will allow the user to go back to the previous menu
		userMenuView.addBackButton();
		//Allows the frame to be visible
		programView.setVisible(true);
		
		//Calls the viewDownloadFiles method
		viewDownloadFiles();
		//Repaints the frame
		programView.getContentPane().repaint();
	}
	
	/**
	 * Modify GUI for user interaction, view and download a file
	 */
	private void viewDownloadFiles()
	{
		// Label telling user to click a file button to set file index
		JLabel fileButtonLabel = new JLabel("Select which position to download the file from: ");
		fileButtonLabel.setBounds(100, 255, 300, 40);
		fileButtonLabel.setForeground(viewTextColor);
		//Adds fileButtonLabel to the frame
		programView.add(fileButtonLabel);
		
		//Sets fileList to the current users files
		fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
		
		// Loop to make file buttons
		for (int i = 0; i < 10; i++)
		{
			//The current iteration of the array is set to a new JButton labeled i + 1
			fileSelectButtons[i] = new JButton(Integer.toString(i + 1));
			fileSelectButtons[i].setBackground(viewButtonColor);
			fileSelectButtons[i].setForeground(viewTextColor);
			fileSelectButtons[i].setFocusable(false);
			
			//Adds an actionListener to each button
			fileSelectButtons[i].addActionListener(new SelectButtonListener(programView, fileSelectButtons, fileSelectButtons[i]));
		}
		
		//Creates a fileSelectButtonPanel panel
		fileSelectButtonPanel = new JPanel();
		fileSelectButtonPanel.setBounds(100, 300, 50, 250); //last bound was 500
		fileSelectButtonPanel.setLayout(new GridLayout(10, 1));
		//Sets it to Opaque so that it's background color can be visible
		fileSelectButtonPanel.setOpaque(true);
		
		// Loop to add file buttons to file button panel
		for (int i = 0; i < 10; i++) 
		{
			//Adds the current button to the fileSelectButtonPanel
			fileSelectButtonPanel.add(fileSelectButtons[i]);
		}
		
		//Adds fileSelectButtonPanel to the frame
		programView.add(fileSelectButtonPanel);
		//Allows the frame to be visible
		programView.setVisible(true);
		
		//Creates userFileArea JTextArea
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(170, 300, 300, 250);
		userFileArea.setBackground(viewButtonColor);
		userFileArea.setForeground(viewTextColor);
		//Makes it so the JTextArea can't be edited
		userFileArea.setEditable(false);
		//adds the userFileArea to the frame
		programView.add(userFileArea);
		
		//Creates filePathLabel label
		JLabel filePathLabel = new JLabel("Enter the desired file path for the file being downloaded: ");
		filePathLabel.setBounds(600, 350, 400, 40);
		filePathLabel.setForeground(viewTextColor);
		//Adds filePathLabel to the frame
		programView.add(filePathLabel);
		
		//Creates filePathEntry JTextField
		JTextField filePathEntry = new JTextField();
		filePathEntry.setBounds(600, 395, 375, 50);
		filePathEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		//Adds filePathEntry to the frame
		programView.add(filePathEntry);
		
		//Creates downloadFileButton JButton
		JButton downloadFileButton = new JButton("Download File");
		downloadFileButton.setBackground(viewButtonColor);
		downloadFileButton.setForeground(viewTextColor);
		downloadFileButton.setFocusable(false);
		downloadFileButton.setBounds(420, 600, 345, 65);
		//Adds downloadFileButton to the frame
		programView.add(downloadFileButton);
		
		//Adds ActionListener to downloadFileButton
		downloadFileButton.addActionListener(new ActionListener()
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
				}
				
				//The filePath String is set to the text in the filePathEntry JTextField
				String filePath = filePathEntry.getText();
				int fileNumber = 0;
				
				//For every button in the panel...
				for (int i = 0; i < 10; i++) 
				{
					//If the button is disabled...
					if (!fileSelectButtons[i].isEnabled())
					{
						//That is the button that was clicked and selected
						fileNumber = i;
					}
				}
				
				// Run the download function, receive possible error message
				String downloadError = programView.getProgramModel().getCurrentUser().downloadFileFromDatabase(filePath, fileNumber);
				
				// If download did not return error message (downloadError is null)
				if (Objects.isNull(downloadError))
				{
					//Send the user an error message
					JOptionPane.showMessageDialog(null, "File successfully downloaded to your computer");
					//Removes all of the components from the frame
					programView.getContentPane().removeAll();
					//calls the printView method
					userMenuView.printView();
				}
				//Else if the download error was from the filePath...
				else if (downloadError.equals("filePath"))
				{
					//Send the user an error message
					JOptionPane.showMessageDialog(null, "File download unsuccessful, check file path");
					//Calls the printView method
					printView();
				}
				//Else if the download error was from the fileNumber...
				else if (downloadError.equals("fileNumber"))
				{
					//Send the user an error message
					JOptionPane.showMessageDialog(null, "File download unsuccessful, check selected file number");
					//Calls the printView Method
					printView();
				}
			}
		});
	}
}
