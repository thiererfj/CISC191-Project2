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
	private String fileList = "";
	private JPanel fileDeleteButtonPanel;
	private JButton[] fileDeleteButtons;
	private int deletionUserSerialNumber;
	
	// 1 to delete their own file, 2 to delete another user's file
	private int deleteType;
	
	public DeleteFileView (UserMenuView userMenuView, ProgramView programView, int deleteType) 
	{
		this.userMenuView = userMenuView;
		this.programView = programView;
		this.fileDeleteButtons = new JButton[10];
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
			fileList = "Figure out how to delete another user's files";
		}
		
		// Loop to make file buttons
		for (int i = 0; i < 10; i++)
		{
			fileDeleteButtons[i] = new JButton(Integer.toString(i + 1));
			fileDeleteButtons[i].setBackground(Color.gray);
			fileDeleteButtons[i].setFocusable(false);
		}
		
		fileDeleteButtons[0].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileDeleteButtons[0].setEnabled(false);
				fileDeleteButtons[1].setEnabled(true);
				fileDeleteButtons[2].setEnabled(true);
				fileDeleteButtons[3].setEnabled(true);
				fileDeleteButtons[4].setEnabled(true);
				fileDeleteButtons[5].setEnabled(true);
				fileDeleteButtons[6].setEnabled(true);
				fileDeleteButtons[7].setEnabled(true);
				fileDeleteButtons[8].setEnabled(true);
				fileDeleteButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileDeleteButtons[1].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileDeleteButtons[0].setEnabled(true);
				fileDeleteButtons[1].setEnabled(false);
				fileDeleteButtons[2].setEnabled(true);
				fileDeleteButtons[3].setEnabled(true);
				fileDeleteButtons[4].setEnabled(true);
				fileDeleteButtons[5].setEnabled(true);
				fileDeleteButtons[6].setEnabled(true);
				fileDeleteButtons[7].setEnabled(true);
				fileDeleteButtons[8].setEnabled(true);
				fileDeleteButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileDeleteButtons[2].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileDeleteButtons[0].setEnabled(true);
				fileDeleteButtons[1].setEnabled(true);
				fileDeleteButtons[2].setEnabled(false);
				fileDeleteButtons[3].setEnabled(true);
				fileDeleteButtons[4].setEnabled(true);
				fileDeleteButtons[5].setEnabled(true);
				fileDeleteButtons[6].setEnabled(true);
				fileDeleteButtons[7].setEnabled(true);
				fileDeleteButtons[8].setEnabled(true);
				fileDeleteButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileDeleteButtons[3].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileDeleteButtons[0].setEnabled(true);
				fileDeleteButtons[1].setEnabled(true);
				fileDeleteButtons[2].setEnabled(true);
				fileDeleteButtons[3].setEnabled(false);
				fileDeleteButtons[4].setEnabled(true);
				fileDeleteButtons[5].setEnabled(true);
				fileDeleteButtons[6].setEnabled(true);
				fileDeleteButtons[7].setEnabled(true);
				fileDeleteButtons[8].setEnabled(true);
				fileDeleteButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileDeleteButtons[4].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileDeleteButtons[0].setEnabled(true);
				fileDeleteButtons[1].setEnabled(true);
				fileDeleteButtons[2].setEnabled(true);
				fileDeleteButtons[3].setEnabled(true);
				fileDeleteButtons[4].setEnabled(false);
				fileDeleteButtons[5].setEnabled(true);
				fileDeleteButtons[6].setEnabled(true);
				fileDeleteButtons[7].setEnabled(true);
				fileDeleteButtons[8].setEnabled(true);
				fileDeleteButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileDeleteButtons[5].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileDeleteButtons[0].setEnabled(true);
				fileDeleteButtons[1].setEnabled(true);
				fileDeleteButtons[2].setEnabled(true);
				fileDeleteButtons[3].setEnabled(true);
				fileDeleteButtons[4].setEnabled(true);
				fileDeleteButtons[5].setEnabled(false);
				fileDeleteButtons[6].setEnabled(true);
				fileDeleteButtons[7].setEnabled(true);
				fileDeleteButtons[8].setEnabled(true);
				fileDeleteButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileDeleteButtons[6].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileDeleteButtons[0].setEnabled(true);
				fileDeleteButtons[1].setEnabled(true);
				fileDeleteButtons[2].setEnabled(true);
				fileDeleteButtons[3].setEnabled(true);
				fileDeleteButtons[4].setEnabled(true);
				fileDeleteButtons[5].setEnabled(true);
				fileDeleteButtons[6].setEnabled(false);
				fileDeleteButtons[7].setEnabled(true);
				fileDeleteButtons[8].setEnabled(true);
				fileDeleteButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileDeleteButtons[7].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileDeleteButtons[0].setEnabled(true);
				fileDeleteButtons[1].setEnabled(true);
				fileDeleteButtons[2].setEnabled(true);
				fileDeleteButtons[3].setEnabled(true);
				fileDeleteButtons[4].setEnabled(true);
				fileDeleteButtons[5].setEnabled(true);
				fileDeleteButtons[6].setEnabled(true);
				fileDeleteButtons[7].setEnabled(false);
				fileDeleteButtons[8].setEnabled(true);
				fileDeleteButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileDeleteButtons[8].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileDeleteButtons[0].setEnabled(true);
				fileDeleteButtons[1].setEnabled(true);
				fileDeleteButtons[2].setEnabled(true);
				fileDeleteButtons[3].setEnabled(true);
				fileDeleteButtons[4].setEnabled(true);
				fileDeleteButtons[5].setEnabled(true);
				fileDeleteButtons[6].setEnabled(true);
				fileDeleteButtons[7].setEnabled(true);
				fileDeleteButtons[8].setEnabled(false);
				fileDeleteButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileDeleteButtons[9].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileDeleteButtons[0].setEnabled(true);
				fileDeleteButtons[1].setEnabled(true);
				fileDeleteButtons[2].setEnabled(true);
				fileDeleteButtons[3].setEnabled(true);
				fileDeleteButtons[4].setEnabled(true);
				fileDeleteButtons[5].setEnabled(true);
				fileDeleteButtons[6].setEnabled(true);
				fileDeleteButtons[7].setEnabled(true);
				fileDeleteButtons[8].setEnabled(true);
				fileDeleteButtons[9].setEnabled(false);
				
				programView.getContentPane().repaint();
			}
		});
		
		// Create JPanel for fileDeleteButtons
		fileDeleteButtonPanel = new JPanel();
		fileDeleteButtonPanel.setBounds(315, 345, 50, 250); //last bound was 500
		fileDeleteButtonPanel.setLayout(new GridLayout(10, 1));
		fileDeleteButtonPanel.setOpaque(true);
		
		// Loop to add file buttons to file button panel
		for (int i = 0; i < 10; i++)
		{
			fileDeleteButtonPanel.add(fileDeleteButtons[i]);
		}
		
		programView.add(fileDeleteButtonPanel);
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
					if (!fileDeleteButtons[i].isEnabled())
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
