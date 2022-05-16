package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DeleteFileView
{
	private UserMenuView userMenuView;
	private ProgramView programView;
	private String fileList;
	private JPanel fileSelectButtonPanel;
	private JButton[] fileSelectButtons;
	private int deletionUserSerialNumber;
	private int deleteType = 0;
	
	public DeleteFileView (UserMenuView userMenuView, ProgramView programView, int deleteType) 
	{
		this.userMenuView = userMenuView;
		this.programView = programView;
		this.fileSelectButtons = new JButton[10];
		this.deleteType = deleteType;
		printView();
	}
	
	private void printView() 
	{
		programView.getContentPane().removeAll();
		programView.addTitleLabel("Delete File", Color.lightGray, Color.white);
		userMenuView.addBackButton();
		programView.setVisible(true);
		viewAndDeleteFiles();
		programView.getContentPane().repaint();
	}
	
	private void viewAndDeleteFiles() 
	{
		if (deleteType == 1) 
		{
			fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
			deletionUserSerialNumber = programView.getProgramModel().getCurrentUser().getSerialNumber();
		}
		else if (deleteType == 2) 
		{
			fileList = "display users for super user to select?";
		}
		
		// Loop to make file buttons
		for (int i = 0; i < 10; i++)
		{
			fileSelectButtons[i] = new JButton(Integer.toString(i + 1));
			fileSelectButtons[i].setBackground(Color.gray);
			fileSelectButtons[i].setFocusable(false);
			
			fileSelectButtons[i].addActionListener(new FileSelectButtonListener(programView, fileSelectButtons, fileSelectButtons[i]));
		}
		
		// Create JPanel for fileDeleteButtons
		fileSelectButtonPanel = new JPanel();
		fileSelectButtonPanel.setBounds(315, 345, 50, 250); //last bound was 500
		fileSelectButtonPanel.setLayout(new GridLayout(10, 1));
		fileSelectButtonPanel.setOpaque(true);
		
		// Loop to add file buttons to file button panel
		for (int i = 0; i < 10; i++)
		{
			fileSelectButtonPanel.add(fileSelectButtons[i]);
		}
		
		programView.add(fileSelectButtonPanel);
		programView.setVisible(true);
		
		// Create text area to display relevant user's files
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(385, 345, 300, 250);
		userFileArea.setBackground(Color.lightGray);
		userFileArea.setEditable(false);
		programView.add(userFileArea);
		
		// Button to initiate the uploading
		JButton deleteFileButton = new JButton("Delete File");
		deleteFileButton.setBackground(Color.gray);
		deleteFileButton.setFocusable(false);
		deleteFileButton.setBounds(330, 650, 345, 65);
		programView.add(deleteFileButton);
		
		deleteFileButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int fileNumber = 0;
				
				// Loop through file delete buttons to see which is disabled (the selected file number)
				for (int i = 0; i < 10; i++) 
				{
					if (!fileSelectButtons[i].isEnabled())
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
