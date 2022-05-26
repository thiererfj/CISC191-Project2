package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
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
 * UserMenuView is designed to reprint the window with GUI components allowing a logged in User to interact with the
 * file system functions. This view displays the "main user menu" allowing a User to upload a file to the database, download a file from the database,
 * view files, and delete a file. 
 */
public class UserMenuView 
{
	//UserMenuView has a programView
	private ProgramView programView;
	//UserMenuView has a buttonPanel
	private JPanel buttonPanel;
	//UserMenuView has a variable that determines if currentUserIsSuper
	private boolean currentUserIsSuper;

	//UserMenuView has Color variables 
	private Color viewBackgroundColor;
	private Color viewTitleBoxColor;
	private Color viewButtonColor;
	private Color viewTextColor;
	
	/**
	 * Purpose: Constructor for UserMenuView
	 * 
	 * @param programView
	 * @param currentUserIsSuper
	 */
	public UserMenuView (ProgramView programView, boolean currentUserIsSuper)
	{
		//Sets programView to the one from the parameter
		this.programView = programView;
		//Sets currentUserIsSuper to the one from the parameter
		this.currentUserIsSuper = currentUserIsSuper;

		//Sets color variables to their corresponding variables in programView to allow for cleaner code
		this.viewBackgroundColor = programView.getViewBackgroundColor();
		this.viewTitleBoxColor = programView.getViewTitleBoxColor();
		this.viewButtonColor = programView.getViewButtonColor();
		this.viewTextColor = programView.getViewTextColor();

		//Calls the printView method
		printView();
	}
	
	/**
	 * Purpose: To show the UserMenu
	 * 
	 */
	public void printView() 
	{
		//Removes all of the components from the frame
		programView.getContentPane().removeAll();
    	
    	// Add title JLabel to window
    	programView.addTitleLabel("User Menu", viewTitleBoxColor, viewTextColor);
    	
    	// Add JTextArea to show username of user logged in
    	JTextArea displayCurrentUsername = new JTextArea();
    	displayCurrentUsername.setText("Current User: " + programView.getProgramModel().getCurrentUser().getUsername());
    	displayCurrentUsername.setFont(new Font("Times New Roman", Font.BOLD, 20));
    	displayCurrentUsername.setBounds(20, 20, 200, 25);
    	displayCurrentUsername.setBackground(viewBackgroundColor);
    	displayCurrentUsername.setForeground(viewTextColor);
    	displayCurrentUsername.setEditable(false);
		//Adds displayCurrentUsername to the frame
    	programView.add(displayCurrentUsername);
    	
    	// Create button panel for user menu function buttons
    	buttonPanel = new JPanel(new GridLayout(1, 4, 20, 20));
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(viewBackgroundColor);
        buttonPanel.setBounds(200, 600, 800, 100);
        
        
        // Create upload file button
        JButton uploadFileButton = new JButton("Upload File");
        uploadFileButton.setBackground(viewButtonColor);
        uploadFileButton.setForeground(viewTextColor);
        uploadFileButton.setFocusable(false);
		//Adds uploadFileButton to buttonPanel
        buttonPanel.add(uploadFileButton);
            
        // Add button panel to main window
        programView.add(buttonPanel);

		//Adds an actionListener to uploadFileButton
		uploadFileButton.addActionListener(new ActionListener()
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
					// No sound will play
				}
				
				//Calls the uploadFileVisuals method
				uploadFileVisuals();
			}
		});
       
		// Create download file button
		JButton downloadFileButton = new JButton("Download File");
		downloadFileButton.setBackground(viewButtonColor);
		downloadFileButton.setForeground(viewTextColor);
		downloadFileButton.setFocusable(false);
		//Adds downloadFileButton to buttonPanel
		buttonPanel.add(downloadFileButton);

		//Adds an actionListener to downloadFileButton
		downloadFileButton.addActionListener(new ActionListener()
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
					// No sound will play
				} 
				
				//Calls the downloadFileVisuals method
				downloadFileVisuals();
			}
		});
		
		// Create view files button
		JButton viewFilesButton = new JButton("View Files");
		viewFilesButton.setForeground(viewTextColor);
		viewFilesButton.setBackground(viewButtonColor);
		viewFilesButton.setFocusable(false);
		//Adds the viewFilesButton to buttonPanel
		buttonPanel.add(viewFilesButton);
		
		//Adds an actionListener to viewFilesButton
		viewFilesButton.addActionListener(new ActionListener()
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
					// No sound will play
				}
				
				//Calls the viewFilesVisuals method
				viewFilesVisuals();
			}
		});
		
		// Create delete file button
		JButton deleteFileButton = new JButton("Delete File");
		deleteFileButton.setBackground(viewButtonColor);
		deleteFileButton.setForeground(viewTextColor);
		deleteFileButton.setFocusable(false);
		//Adds deleteFileButton to buttonPanel
		buttonPanel.add(deleteFileButton);

		//Adds actionListener to deleteFileButton
		deleteFileButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//We will try to call the clickSound method
				try 
				{
					programView.clickSound();
				} 
				//Catches any Exception
				catch (Exception e1) 
				{
					// No sound will play
				}
				
				//Calls the deleteFileVisuals method
				deleteFileVisuals();
			}
		});
        
		// Create log out button
        JButton logOutButton = new JButton("Log Out");
        logOutButton.setBackground(viewButtonColor);
        logOutButton.setForeground(viewTextColor);
        logOutButton.setFocusable(false);
		//Adds logOutButton to buttonPanel
        buttonPanel.add(logOutButton);
        
		//Adds ActionListener to logOutButtoN
        logOutButton.addActionListener(new ActionListener()
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
		    	
				//Will remove all of the components from the frame
		    	programView.getContentPane().removeAll();
		    	
		    	// Refresh GUI components 
		    	programView.getContentPane().repaint();
		    	
		    	// Print main menu to "log out"
		    	programView.printMainMenu();
		    }
		});
		
		///Repaints the frame
		programView.repaint();
		//Allows the frame to be visible
		programView.setVisible(true);
	}
	
	/**
	 * Purpose: To make an instance of uploadFileView
	 *
	 */
	private void uploadFileVisuals() 
	{
		UploadFileView uploadFileView = new UploadFileView(this, programView);
	}
	
	/**
	 * Purpose: To make an instance of downloadFileView
	 *
	 */
	private void downloadFileVisuals() 
	{
		DownloadFileView downloadFileView = new DownloadFileView(this, programView);
	}
	
	/**
	 * Purpose: To make an instance of deleteFileView
	 *
	 */
	private void deleteFileVisuals() 
	{
		// If current User is SuperUser
		if (currentUserIsSuper)
		{
			DeleteFileView deleteFileView = new DeleteFileView(this, programView, 2);
		}
		// If current User is BasicUser
		else 
		{
			DeleteFileView deleteFileView = new DeleteFileView(this, programView, 1);
		}
	}
	
	/**
	 * Purpose: To make an instance of viewFilesView
	 *
	 */
	private void viewFilesVisuals() 
	{
		// If current User is SuperUser
		if (currentUserIsSuper)
		{
			ViewFilesView viewFilesView = new ViewFilesView(this, programView, 2);
		}
		// If current User is BasicUser
		else 
		{
			// Pass 1 for basic user view type
			ViewFilesView viewFilesView = new ViewFilesView(this, programView, 1);
		}
	}
	
	/**
	 * Purpose: To add a back button to a certain menu view
	 */
	public void addBackButton()
	{
		//Creates a backButton that will be used to go back to the previous menu
		JButton backButton = new JButton("Back");
		backButton.setBackground(viewButtonColor);
		backButton.setForeground(viewTextColor);
		backButton.setFocusable(false);
		backButton.setBounds(20, 20, 70, 40);

		//Adds backButton to the frame
		programView.add(backButton);

		//Adds an actionListener to backButton
		backButton.addActionListener(new ActionListener()
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
				
				//Removes all of the components from the frame
				programView.getContentPane().removeAll();

				//Calls the printView method
				printView();
			}
		});
	}
}
