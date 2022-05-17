package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ViewFilesView
{
	private UserMenuView userMenuView;
	private ProgramView programView;
	private String fileList = "";
	
	// 1 to view their own files, 2 to view another user's files
	private int viewType;
	
	public ViewFilesView (UserMenuView userMenuView, ProgramView programView, int viewType) 
	{
		this.userMenuView = userMenuView;
		this.programView = programView;
		this.viewType = viewType;
		printView();
	}
	
	private void printView() 
	{
		programView.getContentPane().removeAll();
		programView.addTitleLabel("View Files", Color.lightGray, Color.white);
		userMenuView.addBackButton();
		programView.setVisible(true);
		initialViewCheck();
		programView.getContentPane().repaint();
	}
	
	private void initialViewCheck() 
	{
		// BasicUser can only view their own files
		if (viewType == 1) 
		{
			fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
			viewFiles();
		}
		else if (viewType == 2) 
		{
			// Create String representing User accounts in database by username
			String userAccountList = programView.getProgramModel().getDatabase().viewUserAccounts();

			// Label telling user to click a user account button to set user
			// account to delete file from
			JLabel userButtonLabel = new JLabel("Select which user account to delete file from: ");
			userButtonLabel.setBounds(415, 255, 300, 40);
			userButtonLabel.setForeground(Color.white);
			programView.add(userButtonLabel);

			// Create JPanel for userSelectButtons
			JPanel selectButtonPanel = new JPanel();
			selectButtonPanel.setBounds(415, 300, 50, 250); // last bound was
															// 500
			selectButtonPanel.setLayout(new GridLayout(10, 1));
			selectButtonPanel.setOpaque(true);
			
			JButton[] selectButtons = new JButton[10];

			// Loop to make file buttons and add to panel
			for (int i = 0; i < 10; i++)
			{
				selectButtons[i] = new JButton(Integer.toString(i + 1));
				selectButtons[i].setBackground(Color.gray);
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
			userAccountListArea.setBackground(Color.lightGray);
			userAccountListArea.setEditable(false);
			programView.add(userAccountListArea);

			// Button to initiate the uploading
			JButton selectUserExecuteButton = new JButton("Select User");
			selectUserExecuteButton.setBackground(Color.gray);
			selectUserExecuteButton.setFocusable(false);
			selectUserExecuteButton.setBounds(425, 600, 345, 65);
			programView.add(selectUserExecuteButton);

			// Add function to execute button
			selectUserExecuteButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					// Loop through file delete buttons to see which is disabled
					// (the selected user)
					for (int i = 0; i < 10; i++)
					{
						// If button is disabled (user clicked)
						if (!(selectButtons[i].isEnabled()))
						{
							// Try to view and delete selected users files
							try
							{
								fileList = programView.getProgramModel().getDatabase().getUsers()[i].viewUserFiles();
								viewFiles();
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
	
	private void viewFiles() 
	{
		programView.getContentPane().removeAll();
		programView.addTitleLabel("View Files", Color.lightGray, Color.white);
		userMenuView.addBackButton();
		programView.setVisible(true);
		programView.getContentPane().repaint();
		
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(450, 300, 300, 250);
		userFileArea.setBackground(Color.lightGray);
		userFileArea.setEditable(false);
		programView.add(userFileArea);
	}
}
