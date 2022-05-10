package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

public class ViewUserAccountsView
{
    private ProgramView programWindow;
    private Database DATABASE;
	private JList superUserList;
	private JList basicUserList;

    public ViewUserAccountsView(ProgramView programWindow, Database DATABASE) 
    {
        this.programWindow = programWindow;
        this.DATABASE = DATABASE;
    }

    public void printView() 
    {
        programWindow.getContentPane().removeAll();
        programWindow.getContentPane().setBackground(Color.PINK);
        programWindow.addBackButton();
        programWindow.addTitleLabel("Accounts", Color.LIGHT_GRAY, Color.WHITE);

        // Create super user label and show list of account(s)
        JLabel superUserLabel = new JLabel("Super User Account:");
		superUserLabel.setBounds(200, 400, 150, 50);
		superUserLabel.setForeground(Color.white);
		programWindow.add(superUserLabel);

        if (DATABASE.users[0] == null) 
		{
			JLabel noSuperUserExistsLabel = new JLabel("No super user exists...");
			noSuperUserExistsLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
			noSuperUserExistsLabel.setBounds(160, 450, 250, 50);
			noSuperUserExistsLabel.setForeground(Color.BLACK);
			programWindow.add(noSuperUserExistsLabel);
		}
		else 
		{
			// Fix this
			superUserList = new JList<String>(DATABASE.getSuperUsername());
			superUserList.setBounds(180, 450, 150, 200);
			programWindow.add(superUserList);
		}

        // Create basic user label and show list of account(s)
		JLabel basicUsersLabel = new JLabel("Basic User Accounts:");
		basicUsersLabel.setBounds(650, 400, 150, 50);
		basicUsersLabel.setForeground(Color.white);
		programWindow.add(basicUsersLabel);
		
		if (DATABASE.users[1] == null) 
		{
			JLabel noBasicUserExistsLabel = new JLabel("No basic user exists...");
			noBasicUserExistsLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
			noBasicUserExistsLabel.setBounds(610, 450, 250, 50);
			noBasicUserExistsLabel.setForeground(Color.BLACK);
			programWindow.add(noBasicUserExistsLabel);
		}
		else 
		{
			// This might work
			basicUserList = new JList<String>(DATABASE.getBasicUsernames());
			basicUserList.setBounds(640, 450, 150, 200);
			programWindow.add(basicUserList);
		}

        programWindow.getContentPane().repaint();
    }

}
