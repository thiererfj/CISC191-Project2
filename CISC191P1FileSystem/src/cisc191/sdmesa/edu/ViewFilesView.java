package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
 * <<add more references here>>
 * Design pattern - factory pattern. (n.d.). 
 * Retrieved March 10, 2022, from https://www.tutorialspoint.com/design_pattern/factory_pattern.htm 
 *  
 *  
 * Version/date: 4.4 05/22/22
 * 
 * Responsibilities of class:
 * ViewFilesView is designed to reprint the window with GUI components allowing a user to view a certain user's files.
 * A SuperUser can view any user's files, and BasicUser can only view their own. 
 */
public class ViewFilesView
{
	//ViewFilesView has a userMenuView
	private UserMenuView userMenuView;
	//ViewFilesView has a programView
	private ProgramView programView;
	//ViewFilesView has a fileList
	private String fileList = "";
	
	// variable for view Type, 1 to view their own files, 2 to view another user's files
	private int viewType;

	//ViewFilesView has Color variables
//	private Color viewBackgroundColor;
	private Color viewTitleBoxColor;
	private Color viewButtonColor;
	private Color viewTextColor;
	
	/**
	 * Purpose: Constructor for ViewFilesView
	 * 
	 * @param userMenuView
	 * @param programView
	 * @param viewType
	 */
	public ViewFilesView (UserMenuView userMenuView, ProgramView programView, int viewType) 
	{
		//Sets the userMenuView variable to the one in the parameter
		this.userMenuView = userMenuView;
		//Sets the programView variable to the one in the parameter
		this.programView = programView;
		//Sets the viewType to the one in the parameter
		this.viewType = viewType;

		//Sets the Color variables to the ones from programView to allow for cleaner code
//		this.viewBackgroundColor = programView.getViewBackgroundColor();
		this.viewTitleBoxColor = programView.getViewTitleBoxColor();
		this.viewButtonColor = programView.getViewButtonColor();
		this.viewTextColor = programView.getViewTextColor();

		//Calls the printView method that will all users to view files
		printView();
	}
	

	/**
	 * Purpose: A method that allows users to view their files
	 * 
	 */
	private void printView() 
	{
		//Removes all of the components from the frame so that new ones can be added
		programView.getContentPane().removeAll();
		//Adds as title label with given title and color parameters
		programView.addTitleLabel("View Files", viewTitleBoxColor, viewTextColor);
		//Adds a back button that allows the user to go to the previous menu
		userMenuView.addBackButton();
		//Sets the frame to visible
		programView.setVisible(true);
		//Calls the initialViewCheck to display correct file view
		initialViewCheck();
		//Allows the previously added elements to be seen
		programView.getContentPane().repaint();
	}
	
	/**
	 * Purpose: Checks whether the user is basic or super in order to display the correct file view
	 * 
	 */
	private void initialViewCheck() 
	{
		// BasicUser can only view their own files
		//If the view type is 1...
		if (viewType == 1) 
		{
			//fileList is equal to the current users files
			fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
			//Calls the viewFiles method in order to display the appropriate users files
			viewFiles();
		}
		//Else if the viewtype is 2...
		else if (viewType == 2) 
		{
			// Create String representing User accounts in database by username
			String userAccountList = programView.getProgramModel().viewUserAccounts();

			// Label telling user to click a user account button to set user account to delete file from
			JLabel userButtonLabel = new JLabel("Select which user you want to view files from: ");
			//Sets the bound of the label
			userButtonLabel.setBounds(415, 255, 300, 40);
			//Sets the font color
			userButtonLabel.setForeground(viewTextColor);
			//Adds the label to the frame
			programView.add(userButtonLabel);

			// Create JPanel for userSelectButtons
			JPanel selectButtonPanel = new JPanel();
			//Sets the bounds of the panel
			selectButtonPanel.setBounds(415, 300, 50, 250); 
			//Sets the layout manager to GridLayout
			selectButtonPanel.setLayout(new GridLayout(10, 1));
			//Sets Opaque to be true so that we can see the color of the panel
			selectButtonPanel.setOpaque(true);
			
			//Creates a button array
			JButton[] selectButtons = new JButton[10];

			// Loop to make file buttons and add to panel
			for (int i = 0; i < 10; i++)
			{
				//This current iteration of the array is equal to a new button that is labeled as i + 1
				selectButtons[i] = new JButton(Integer.toString(i + 1));
				selectButtons[i].setBackground(viewButtonColor);
				selectButtons[i].setForeground(viewTextColor);
				selectButtons[i].setFocusable(false);
				//Adds actionlistener to each button to execute certain actions
				selectButtons[i].addActionListener(new SelectButtonListener(programView, selectButtons, selectButtons[i]));
				//Adds each button to the selectButtonPanel
				selectButtonPanel.add(selectButtons[i]);
			}
			//Adds selectButtonPanel to the frame
			programView.add(selectButtonPanel);
			//Allows the frame to be visible to the user
			programView.setVisible(true);

			// Create text area to display relevant user's files
			JTextArea userAccountListArea = new JTextArea(userAccountList);
			//Sets the font of the JTextArea
			userAccountListArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
			//Sets the bounds of the JTextArea
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
			//Adds the selectUserExectueButton to programView
			programView.add(selectUserExecuteButton);

			// Add function to execute button
			selectUserExecuteButton.addActionListener(new ActionListener()
			{
				
				public void actionPerformed(ActionEvent e)
				{
					//Try to call the clickSound method
					try 
					{
						programView.clickSound();
					} 
					// Catches possible exceptions
					catch (Exception e1) 
					{
						// Do nothing, sound will not play
					} 
					
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
								//Sets fileList to the user that was selected's files
								fileList = programView.getProgramModel().getDatabase().getUsers()[i].viewUserFiles();
								
								// Run viewFiles method
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
	
	/**
	 * Purpose: Show the viewFiles menu
	 * 
	 */
	private void viewFiles() 
	{
		//Removes all components from the frame
		programView.getContentPane().removeAll();
		//Adds a title label with title and colors as parameters
		programView.addTitleLabel("View Files", viewTitleBoxColor, viewTextColor);
		//Adds a back button that will send the user back to the previous menu
		userMenuView.addBackButton();
		//Allows the frame to be visible
		programView.setVisible(true);
		//Repaints the frame
		programView.getContentPane().repaint();
		
		//Creates a JTextArea that will display the files of the selected user
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(485, 300, 300, 250); 
		userFileArea.setBackground(viewButtonColor);
		userFileArea.setForeground(viewTextColor);
		userFileArea.setEditable(false);
		//Adds the JTextArea to the frame
		programView.add(userFileArea);
		
		
		
		//Create JPanel for userSelectButtons
		JPanel selectButtonPanel = new JPanel();
		selectButtonPanel.setBounds(415, 300, 50, 250); // last bound was 500
		selectButtonPanel.setLayout(new GridLayout(10, 1));
		selectButtonPanel.setOpaque(true);

		//Creates an array for selectButtons
		JButton[] selectButtons = new JButton[10];

		// Loop to make file buttons and add to panel
		for (int i = 0; i < 10; i++)
		{
			//Sets current iteration of the array to a new button with a name set to i + 1
			selectButtons[i] = new JButton(Integer.toString(i + 1));
			selectButtons[i].setBackground(viewButtonColor);
			selectButtons[i].setForeground(viewTextColor);
			selectButtons[i].setFocusable(false);

			//Adds an action listener to each of the buttons
			selectButtons[i].addActionListener(new SelectButtonListener(programView, selectButtons, selectButtons[i]));
			//Adds each button to the selectButtonPanel
			selectButtonPanel.add(selectButtons[i]);
		}

		//Adds the selectButtonPanel to the frame
		programView.add(selectButtonPanel);
		//Allows the frame to be visible
		programView.setVisible(true);

		//Creates a userButtonLabel that will let the user know to choose a file
		JLabel userButtonLabel = new JLabel("Select which file you want to view: ");
		userButtonLabel.setBounds(415, 255, 300, 40);
		userButtonLabel.setForeground(viewTextColor);
		//Adds the userButtonLabel to the frame
		programView.add(userButtonLabel);

		//Creates a selectFileExecuteButton that will select a file
		JButton selectFileExecuteButton = new JButton("Select File");
		selectFileExecuteButton.setBackground(viewButtonColor);
		selectFileExecuteButton.setForeground(viewTextColor);
		selectFileExecuteButton.setFocusable(false);
		selectFileExecuteButton.setBounds(425, 600, 345, 65);
		//Adds selectFileExecuteButton to the frame
		programView.add(selectFileExecuteButton);

		//Adds a new action listener to the selectFileExecuteButton
		selectFileExecuteButton.addActionListener(new ActionListener()
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

							//Removes all components from the frame
							programView.getContentPane().removeAll();

							// Creates a variable for currentUserSerialNumber so that the code is more easily readable in later code
							int currentUserSerialNumber = programView.getProgramModel().getCurrentUser().getSerialNumber();

							// String for fileContents so that the code is more
							// easily readable in later code
							String fileContents = programView.getProgramModel().getDatabase().getGlobalStorage()[currentUserSerialNumber][i].getContents();

							//Creates a JTextArea that displays selected file contents
							JTextArea fileContentsArea = new JTextArea(fileContents);
							//Makes it so you cannot edit the JTextArea
							fileContentsArea.setEditable(false);

							//Creates a JScrollPane that will allow a user to scroll through a text's contents if it is larger than the set bounds, also adds horizontal and vertical side bars
							JScrollPane fileContentsScrollPane = new JScrollPane(fileContentsArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

							fileContentsScrollPane.setBounds(100, 100, 1000, 600);

							//Adds fileContentsScrollPane to the frame
							programView.add(fileContentsScrollPane);

							//Adds a back button to the frame
							addBackButton();
							//Allows the frame to be visible
							programView.setVisible(true);
							//Repaints the frame
							programView.getContentPane().repaint();

						}
						// Handles NullPointerException if user selected null User account
						catch (NullPointerException e2)
						{
							// Print error message to screen
							JOptionPane.showMessageDialog(null, "There is no file in that spot");

							// Reprint this deletion view to reset from error
							viewFiles(); 
						}
					}
				}
			}
		});
	}
	
	/**
	 * Purpose: Adds a backButton to a certain menu on the frame
	 * 
	 */
	public void addBackButton()
	{
		//Creates a new JButton that will serve as a backbutton
		JButton backButton = new JButton("Back");
		backButton.setBackground(viewButtonColor);
		backButton.setForeground(viewTextColor);
		backButton.setFocusable(false);
		backButton.setBounds(20, 20, 70, 40);

		// Adds backButton to the frame
		programView.add(backButton);

		//Adds an actionListener to backbutton
		backButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Removes all of the old components from the frame
				programView.getContentPane().removeAll();
				//Calls the printView method
				printView();
			}
		});
	}
}
