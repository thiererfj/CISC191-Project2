package cisc191.sdmesa.edu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class UserMenuView 
{
	private ProgramView programView;
	private JPanel buttonPanel;
	private boolean currentUserIsSuper;
	private Color viewBackgroundColor;
	private Color viewTitleBoxColor;
	private Color viewButtonColor;
	private Color viewTextColor;
	
	public UserMenuView (ProgramView programView, boolean currentUserIsSuper)
	{
		this.programView = programView;
		this.currentUserIsSuper = currentUserIsSuper;
		this.viewBackgroundColor = programView.getViewBackgroundColor();
		this.viewTitleBoxColor = programView.getViewTitleBoxColor();
		this.viewButtonColor = programView.getViewButtonColor();
		this.viewTextColor = programView.getViewTextColor();
		printView();
	}
	
	public void printView() 
	{
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
    	programView.add(displayCurrentUsername);
        
    	// Set button panel layout depending on user type
    	if (currentUserIsSuper) 
    	{
    		buttonPanel = new JPanel(new GridLayout(1, 6, 20, 20));
            buttonPanel.setOpaque(true);
            buttonPanel.setBackground(viewBackgroundColor);
            buttonPanel.setBounds(100, 600, 1000, 100);
    	}
    	else 
    	{
    		buttonPanel = new JPanel(new GridLayout(1, 4, 20, 20));
            buttonPanel.setOpaque(true);
            buttonPanel.setBackground(viewBackgroundColor);
            buttonPanel.setBounds(200, 600, 800, 100);
    	}
        
        JButton uploadFileButton = new JButton("Upload File");
        uploadFileButton.setBackground(viewButtonColor);
        uploadFileButton.setForeground(viewTextColor);
        uploadFileButton.setFocusable(false);
        buttonPanel.add(uploadFileButton);
            
        // Add button panel to main window
        programView.add(buttonPanel);

		uploadFileButton.addActionListener(new ActionListener()
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
				
				uploadFileVisuals();
			}
		});
       
        //GOODDDDDD

		JButton downloadFileButton = new JButton("Download File");
		downloadFileButton.setBackground(viewButtonColor);
		downloadFileButton.setForeground(viewTextColor);
		downloadFileButton.setFocusable(false);
		buttonPanel.add(downloadFileButton);

		downloadFileButton.addActionListener(new ActionListener()
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
				
				downloadFileVisuals();
			}
		});
		
		// Add extra buttons if current user is super
		if (currentUserIsSuper)
		{
			
			// I don't think this is necessary
			
//			JButton deleteFileButton = new JButton();
//			deleteFileButton.setLayout(new BoxLayout(deleteFileButton, BoxLayout.Y_AXIS));
//			deleteFileButton.add(Box.createRigidArea(new Dimension(0, 30)));
//			JLabel firstLine = new JLabel("    Delete Your");
//			firstLine.setForeground(viewTextColor);
//			JLabel secondLine = new JLabel("       Own File");
//			secondLine.setForeground(viewTextColor);
//			deleteFileButton.add(firstLine);
//			deleteFileButton.add(secondLine);
//			deleteFileButton.setBackground(viewButtonColor);
//			deleteFileButton.setFocusable(false);
//			buttonPanel.add(deleteFileButton);
//
//			deleteFileButton.addActionListener(new ActionListener()
//			{
//				public void actionPerformed(ActionEvent e)
//				{
//					deleteFileVisuals();
//				}
//			});
			
			JButton deleteUserFilesButton = new JButton("Delete Files");
			//deleteUserFilesButton.setLayout(new BoxLayout(deleteUserFilesButton, BoxLayout.Y_AXIS));
			//deleteUserFilesButton.add(Box.createRigidArea(new Dimension(0, 30)));
			deleteUserFilesButton.setForeground(viewTextColor);
//			firstLine = new JLabel(" Delete Another");
//			firstLine.setForeground(viewTextColor);
//			secondLine = new JLabel("     User's File");
//			secondLine.setForeground(viewTextColor);
//			deleteUsersFileButton.add(firstLine);
//			deleteUsersFileButton.add(secondLine);
			deleteUserFilesButton.setBackground(viewButtonColor);
			deleteUserFilesButton.setFocusable(false);
			buttonPanel.add(deleteUserFilesButton);

			deleteUserFilesButton.addActionListener(new ActionListener()
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
					
					deleteFromAnyUsersFilesVisuals();
				}
			});
			
			
			
			
			// Is this really necessary?
			
//			JButton viewFilesButton = new JButton();
//			viewFilesButton.setLayout(new BoxLayout(viewFilesButton, BoxLayout.Y_AXIS));
//			viewFilesButton.add(Box.createRigidArea(new Dimension(0, 30)));
//			firstLine = new JLabel("     View Your");
//			firstLine.setForeground(viewTextColor);
//			secondLine = new JLabel("     Own Files");
//			secondLine.setForeground(viewTextColor);
//			viewFilesButton.add(firstLine);
//			viewFilesButton.add(secondLine);
//			viewFilesButton.setBackground(viewButtonColor);
//			viewFilesButton.setFocusable(false);
//			buttonPanel.add(viewFilesButton);
//			
//			viewFilesButton.addActionListener(new ActionListener()
//			{
//				public void actionPerformed(ActionEvent e)
//				{
//					viewCurrentUserFilesVisuals();
//				}
//			});
			
			JButton viewFilesButton = new JButton("View Files");
			;
			viewFilesButton.setForeground(viewTextColor);
			viewFilesButton.setBackground(viewButtonColor);
			viewFilesButton.setFocusable(false);
			buttonPanel.add(viewFilesButton);
			
			viewFilesButton.addActionListener(new ActionListener()
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
					
					viewSelectedUsersFilesVisuals();
				}
			});
		}
		// Otherwise don't add extra button
		else 
		{
			JButton deleteFileButton = new JButton("Delete File");
			deleteFileButton.setBackground(viewBackgroundColor);
			deleteFileButton.setForeground(viewTextColor);
			deleteFileButton.setFocusable(false);
			buttonPanel.add(deleteFileButton);

			deleteFileButton.addActionListener(new ActionListener()
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
					
					deleteFileVisuals();
				}
			});
			
			JButton viewFilesButton = new JButton("View Files");
			viewFilesButton.setBackground(viewBackgroundColor);
			viewFilesButton.setForeground(viewTextColor);
			viewFilesButton.setFocusable(false);
			buttonPanel.add(viewFilesButton);
			
			viewFilesButton.addActionListener(new ActionListener()
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
					
					viewCurrentUserFilesVisuals();
				}
			});
		}
        
        JButton logOutButton = new JButton("Log Out");
        logOutButton.setBackground(viewButtonColor);
        logOutButton.setForeground(viewTextColor);
        logOutButton.setFocusable(false);
        buttonPanel.add(logOutButton);
        
        logOutButton.addActionListener(new ActionListener()
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
		    	  
		    	programView.getContentPane().removeAll();
		    	programView.repaint();
		    	
		    	
		    	try {
					programView.printMainMenu();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      }
		});
		
		
		//BasicUser Options
//		System.out.println("1) Upload a file to the database");
//		System.out.println("2) Save a database file to your machine");
//		System.out.println("3) Delete a file in the database");
//		System.out.println("4) View your database files");
//		System.out.println("5) Log out of current user");
		
		
		//SuperUser Options
//		System.out.println("1) Upload a file to the database");
//		System.out.println("2) Save a database file to your machine");
//		System.out.println("3) Delete a file in the database");
//		System.out.println("4) View your database files");
//		System.out.println("5) View another users database files");
//		System.out.println("6) Log out of current user");
		
		
		programView.repaint();
		programView.setVisible(true);
	}
	
	private void uploadFileVisuals() 
	{
		UploadFileView uploadFileView = new UploadFileView(this, programView);
	}
	
	private void downloadFileVisuals() 
	{
		DownloadFileView downloadFileView = new DownloadFileView(this, programView);
	}
	
	private void deleteFileVisuals() 
	{
		// Pass 1 for basic user view type
		DeleteFileView deleteFileView = new DeleteFileView(this, programView, 1);
	}
	
	private void deleteFromAnyUsersFilesVisuals() 
	{
		// Pass 2 for super user view type
		DeleteFileView deleteFileView = new DeleteFileView(this, programView, 2);
	}
	
	
	private void viewCurrentUserFilesVisuals() 
	{
		// Pass 1 for basic user view type
		ViewFilesView viewFilesView = new ViewFilesView(this, programView, 1);
	}
	
	private void viewSelectedUsersFilesVisuals() 
	{
		// Pass 2 for super user view type
		ViewFilesView viewFilesView = new ViewFilesView(this, programView, 2);
	}
	
	/**
	 * Purpose: To add a back button to a certain menu view
	 */
	public void addBackButton()
	{
		JButton backButton = new JButton("Back");
		backButton.setBackground(viewButtonColor);
		backButton.setForeground(viewTextColor);
		backButton.setFocusable(false);
		backButton.setBounds(20, 20, 70, 40);

		// Add widget to this window
		programView.add(backButton);

		backButton.addActionListener(new ActionListener()
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
				
				programView.getContentPane().removeAll();
				programView.getContentPane().repaint();
				printView();
			}
		});
	}
}
