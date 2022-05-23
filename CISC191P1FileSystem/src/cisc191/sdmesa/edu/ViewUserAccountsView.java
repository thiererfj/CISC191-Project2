package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 * Lead Author(s):
 * @author Anthony Mayoral
 * @author Francis Thierer
 * <<add additional lead authors here, with a full first and last name>>
 * 
 * Other contributors:
 * <<add additional contributors (mentors, tutors, friends) here, with contact information>>
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
 * Version/date: 4.3 05/22/22
 * 
 * Responsibilities of class:
 * ViewUserAccountsView is designed to reprint the window with gUI components allowing a user to view the existing
 * user accounts. 
 */
public class ViewUserAccountsView
{
    //A ViewUserAccountsView has a programView
    private ProgramView programView;
    //A ViewUserAccountsView has a viewBackgroundColor
    private Color viewBackgroundColor;
    //A ViewUserAccountsView has a viewTitleBoxColor
	private Color viewTitleBoxColor;
    //A ViewUserAccountsView has a viewTextColor
	private Color viewTextColor;

    /**
	 * Purpose: Constructor for ViewUserAccountsView
	 * 
	 * @param programView
	 */
    public ViewUserAccountsView(ProgramView programView) 
    {
        //Sets programView to object from the parameter
        this.programView = programView;

        //Sets the background, title, and text colors to the ones from programView to allow for cleaner code
        this.viewBackgroundColor = programView.getViewBackgroundColor();
		this.viewTitleBoxColor = programView.getViewTitleBoxColor();
		this.viewTextColor = programView.getViewTextColor();
        
        //calls the printView method that will show a list of all of the users
        printView();
    }

    /**
	 * Purpose: To show a user how many and what accounts have been created
     *
	 */
    private void printView() 
    {
        //Removes all components from the frame to allow for new components to be added
        programView.getContentPane().removeAll();
        //Sets the background color of the frame
        programView.getContentPane().setBackground(viewBackgroundColor);
        //Adds a back button that will all the user to go back to the previous menu
        programView.addBackButton();
        //Adds a title label with a preset title and colors
        programView.addTitleLabel("Accounts", viewTitleBoxColor, viewTextColor);
        
        //Creates a JTextArea that lists all of the created users
        JTextArea userList = new JTextArea(programView.getProgramModel().viewUserAccounts());
        //Sets location and size of the JTextArea
        userList.setBounds(350, 300, 500, 375);
        //Sets the font of the JTextArea
        userList.setFont(new Font("Times New Roman", Font.BOLD, 30));
        //Sets the background color of userList
        userList.setBackground(viewBackgroundColor);
        //Sets the font color of userList
        userList.setForeground(viewTextColor);
        //Adds userList to the frame
        programView.add(userList);

        //Makes it so the user can see the new components that were added
        programView.getContentPane().repaint();
    }

}
