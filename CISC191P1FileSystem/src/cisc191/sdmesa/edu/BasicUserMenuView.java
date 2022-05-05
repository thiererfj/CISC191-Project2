package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BasicUserMenuView {

	BasicUserMenuView(ProgramView programView)
	{
		programView.getContentPane().removeAll();
		
		
		
		
		
		
		
		
		
    	
    	// Add title JLabel to window
    	programView.addTitleLabel("User Menu", Color.LIGHT_GRAY, Color.WHITE);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 20, 20));
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.black);
        buttonPanel.setBounds(100, 600, 800, 100);
        
        
        
        JButton uploadFileButton = new JButton("Upload File");
        uploadFileButton.setBackground(Color.gray);
        uploadFileButton.setFocusable(false);
        buttonPanel.add(uploadFileButton);
            
        // Add button panel to main window
        programView.add(buttonPanel);

        uploadFileButton.addActionListener(new ActionListener()
   	    {
        	public void actionPerformed(ActionEvent e)
        	{
        		
        	}
    	});
       
        //GOODDDDDD
        

        JButton saveFileButton = new JButton("Receive File");
        saveFileButton.setBackground(Color.gray);
        saveFileButton.setFocusable(false);
        buttonPanel.add(saveFileButton);
        
        saveFileButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    
		      }
		});
        
        //GOODDDDDD
        
        

        JButton deleteFileButton = new JButton("Delete File");
        deleteFileButton.setBackground(Color.gray);
        deleteFileButton.setFocusable(false);
        buttonPanel.add(deleteFileButton);
        
        deleteFileButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	
		      }
		});

        JButton viewFilesButton = new JButton("View Files");
        viewFilesButton.setBackground(Color.gray);
        viewFilesButton.setFocusable(false);
        buttonPanel.add(viewFilesButton);
        
        viewFilesButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	 
		      }
		});
        
        JButton logOutButton = new JButton("Log Out");
        logOutButton.setBackground(Color.gray);
        logOutButton.setFocusable(false);
        buttonPanel.add(logOutButton);
        
        logOutButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	programView.getContentPane().removeAll();
		    	programView.repaint();
		    	programView.setCurrentUser(null);  //I'm pretty sure I would set it to null......
		    	programView.printMainMenu();
		      }
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		programView.addTitleLabel("User Menu", Color.lightGray, Color.white);
//		
//		
//		
//		
//		
//		
//		JButton uploadFileButton = new JButton();
//		uploadFileButton.setBounds(, 100);
//		uploadFileButton.setBackground(Color.gray);
//		
//		JButton saveFileButton = new JButton();
//		saveFileButton.setSize(50, 100);
//		saveFileButton.setBackground(Color.gray);
//		
//		JButton deleteFileButton = new JButton();
//		deleteFileButton.setSize(50, 100);
//		deleteFileButton.setBackground(Color.gray);
//		
//		JButton viewFilesButton = new JButton();
//		viewFilesButton.setSize(50, 100);
//		viewFilesButton.setBackground(Color.gray);
//		
//		JButton logOutButton = new JButton();
//		logOutButton.setSize(50, 100);
//		logOutButton.setBackground(Color.gray);
//		
//		JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 20, 20));
//        buttonPanel.setOpaque(true);
//        buttonPanel.setBackground(Color.black);
//        buttonPanel.setBounds(100, 600, 800, 100);
//		buttonPanel.add(uploadFileButton);
//		buttonPanel.add(saveFileButton);
//		buttonPanel.add(deleteFileButton);
//		buttonPanel.add(viewFilesButton);
//		buttonPanel.add(logOutButton);
//		
//		programView.add(buttonPanel);
//		
		
		//BasicUser Options
		System.out.println("1) Upload a file to the database");
		System.out.println("2) Save a database file to your machine");
		System.out.println("3) Delete a file in the database");
		System.out.println("4) View your database files");
		System.out.println("5) Log out of current user");
		
		
		
		//SuperUser Options
		System.out.println("1) Upload a file to the database");
		System.out.println("2) Save a database file to your machine");
		System.out.println("3) Delete a file in the database");
		System.out.println("4) View your database files");
		System.out.println("5) View another users database files");
		System.out.println("6) Log out of current user");
		
		
		programView.repaint();
		programView.setVisible(true);
	}
}
