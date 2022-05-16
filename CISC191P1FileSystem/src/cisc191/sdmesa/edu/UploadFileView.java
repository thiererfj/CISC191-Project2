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
	private JPanel fileButtonPanel;
	private JButton[] fileButtons;
	
	public UploadFileView (UserMenuView userMenuView, ProgramView programView)
	{
		this.userMenuView = userMenuView;
		this.programView = programView;
		this.fileButtons = new JButton[10];
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
		// Loop to make file buttons
		for (int i = 0; i < 10; i++) 
		{
			fileButtons[i] = new JButton(Integer.toString(i + 1));
			fileButtons[i].setBackground(Color.gray);
			fileButtons[i].setFocusable(false);
		}
		
		fileButtons[0].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(false);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[1].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(false);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[2].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(false);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[3].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(false);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[4].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(false);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[5].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(false);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[6].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(false);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[7].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(false);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[8].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(false);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[9].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(false);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtonPanel = new JPanel();
		fileButtonPanel.setBounds(315, 345, 50, 250); //last bound was 500
		fileButtonPanel.setLayout(new GridLayout(10, 1));
		fileButtonPanel.setOpaque(true);
		
		// Loop to add file buttons to file button panel
		for (int i = 0; i < 10; i++) 
		{
			fileButtonPanel.add(fileButtons[i]);
		}
		
		// Add and make visible the file button panel
		programView.add(fileButtonPanel);
		programView.setVisible(true);
		
		// Call User view files method to get formatted String representation of files in database
		fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
		
		// Create text area to display user's files
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(385, 345, 300, 250);
		userFileArea.setBackground(Color.lightGray);
		userFileArea.setEditable(false);
		programView.add(userFileArea);
		
		// Label asking user to enter the origin file path, to grab file form their computer
		JLabel filePathLabel = new JLabel("Enter the origin file path of the file being uploaded: ");
		filePathLabel.setBounds(315, 605, 300, 40);
		filePathLabel.setForeground(Color.white);
		programView.add(filePathLabel);
		
		// Text filed for user to enter the origin file path
		JTextField filePathEntry = new JTextField();
		filePathEntry.setBounds(315, 650, 375, 50);
		filePathEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		programView.add(filePathEntry);
		
		// Label asking for user to enter the desired database file name
		JLabel fileNameLabel = new JLabel("Enter the desired file name: ");
		fileNameLabel.setBounds(315, 705, 300, 40);
		fileNameLabel.setForeground(Color.white);
		programView.add(fileNameLabel);
		
		// Text field for user to enter the desired database file name
		JTextField fileNameEntry = new JTextField();
		fileNameEntry.setBounds(315, 750, 375, 50);
		fileNameEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		programView.add(fileNameEntry);
		
		// Button to initiate the uploading
		JButton uploadFileButton = new JButton("Upload File");
		uploadFileButton.setBackground(Color.gray);
		uploadFileButton.setFocusable(false);
		uploadFileButton.setBounds(330, 825, 345, 65);
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
					if (!fileButtons[i].isEnabled())
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
