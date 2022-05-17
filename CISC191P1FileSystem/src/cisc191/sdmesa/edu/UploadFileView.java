package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class UploadFileView 
{
	private UserMenuView userMenuView;
	private ProgramView programView;
	private String fileList;
	private JPanel fileSelectButtonPanel;
	private JButton[] fileSelectButtons;
	
	public UploadFileView (UserMenuView userMenuView, ProgramView programView)
	{
		this.userMenuView = userMenuView;
		this.programView = programView;
		this.fileSelectButtons = new JButton[10];
		printView();
	}
	
	public void printView() 
	{
		programView.getContentPane().removeAll();
		programView.addTitleLabel("Upload File", Color.lightGray, Color.white);
		userMenuView.addBackButton();
		programView.setVisible(true);
		programView.getContentPane().repaint();
		
		viewFiles();
		programView.getContentPane().repaint();
	}
	
	public void viewFiles()
	{
		// Label telling user to click a file button to set file index
		JLabel fileButtonLabel = new JLabel("Select which position to upload the file to: ");
		fileButtonLabel.setBounds(100, 255, 300, 40);
		fileButtonLabel.setForeground(Color.white);
		programView.add(fileButtonLabel);
		
		// Loop to make file buttons
		for (int i = 0; i < 10; i++) 
		{
			fileSelectButtons[i] = new JButton(Integer.toString(i + 1));
			fileSelectButtons[i].setBackground(Color.gray);
			fileSelectButtons[i].setFocusable(false);
			
			fileSelectButtons[i].addActionListener(new FileSelectButtonListener(programView, fileSelectButtons, fileSelectButtons[i]));
		}
		
		fileSelectButtonPanel = new JPanel();
		fileSelectButtonPanel.setBounds(100, 300, 50, 250); //last bound was 500
		fileSelectButtonPanel.setLayout(new GridLayout(10, 1));
		fileSelectButtonPanel.setOpaque(true);
		
		// Loop to add file buttons to file button panel
		for (int i = 0; i < 10; i++) 
		{
			fileSelectButtonPanel.add(fileSelectButtons[i]);
		}
		
		// Add and make visible the file button panel
		programView.add(fileSelectButtonPanel);
		programView.setVisible(true);
		
		// Call User view files method to get formatted String representation of files in database
		fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
		
		// Create text area to display user's files
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(170, 300, 300, 250);
		userFileArea.setBackground(Color.lightGray);
		userFileArea.setEditable(false);
		programView.add(userFileArea);
		
		// Label asking user to enter the origin file path, to grab file form their computer
		JLabel filePathLabel = new JLabel("Enter the origin file path of the file being uploaded: ");
		filePathLabel.setBounds(600, 305, 300, 40);
		filePathLabel.setForeground(Color.white);
		programView.add(filePathLabel);
		
		// Text filed for user to enter the origin file path
		JTextField filePathEntry = new JTextField();
		filePathEntry.setBounds(600, 350, 375, 50);
		filePathEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		programView.add(filePathEntry);
		
		// Label asking for user to enter the desired database file name
		JLabel fileNameLabel = new JLabel("Enter the desired file name: ");
		fileNameLabel.setBounds(600, 415, 300, 40);
		fileNameLabel.setForeground(Color.white);
		programView.add(fileNameLabel);
		
		// Text field for user to enter the desired database file name
		JTextField fileNameEntry = new JTextField();
		fileNameEntry.setBounds(600, 460, 375, 50);
		fileNameEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		programView.add(fileNameEntry);
		
		// Button to initiate the uploading
		JButton uploadFileButton = new JButton("Upload File");
		uploadFileButton.setBackground(Color.gray);
		uploadFileButton.setFocusable(false);
		uploadFileButton.setBounds(420, 600, 345, 65);
		programView.add(uploadFileButton);
		
		// User clicking upload button executes these tasks
		uploadFileButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String filePath = filePathEntry.getText();
				String fileName = fileNameEntry.getText();
				int fileNumber = 0;
				
				// Loop through file buttons to see which is disabled (the selected file number)
				for (int i = 0; i < 10; i++) 
				{
					if (!fileSelectButtons[i].isEnabled())
					{
						fileNumber = i;
					}
				}
				
				// Calls User subclass upload file method, receives error String message (null if no error)
				String uploadError = programView.getProgramModel().getCurrentUser().uploadFileToDatabase(filePath, fileName, fileNumber);
				
				// Checking error messages, first if null there was no error
				if (Objects.isNull(uploadError))
				{
					JOptionPane.showMessageDialog(null, "File successfully uploaded to the database");
					programView.getContentPane().removeAll();
					programView.getContentPane().repaint();
					
					// Roll back to the user's file system menu view
					userMenuView.printView();
				}
				// If there was a file path error
				else if (uploadError.equals("filePath"))
				{
					JOptionPane.showMessageDialog(null, "File upload unsuccessful, check file path");
					
					// Reprint this upload view to reset from error
					printView();
				}
				// If there was a file number error 
				else if (uploadError.equals("fileNumber"))
				{
					JOptionPane.showMessageDialog(null, "File upload unsuccessful, check selected file number");
					
					// Reprint this upload view to reset from error
					printView();
				}
			}
		});
	}
	
	// What is this for?
	public Database getDatabase()
	{
		return null;
	}
	
	
	// Loop through this user's row in FileData[][]
		
}
