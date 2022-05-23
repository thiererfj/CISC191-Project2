package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 * Lead Author(s):
 * @author Anthony Mayoral
 * @author Francis Thierer
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * Tutorialspoint.com. 2022. SWING - WindowEvent Class. 
 * [online] Available at: <https://www.tutorialspoint.com/swing/swing_window_event.htm> [Accessed 22 May 2022].
 *  
 * Version/date: 4.4 05/22/22
 * 
 * Responsibilities of class:
 * QuitView is designed to reprint the window with GUI components allowing a user to exit the program while
 * saving the data entered during their session. Clicking yes to the dialog box will save and exit, clicking no or the 
 * upper right "X" will exit but not save. 
 */
public class QuitView 
{
    // QuitView has a ProgramView
	private ProgramView programView;
	
	// Holds value of user input from dialog box
    private int userCloseOption;
    
    // Color object to set the color scheme
//    private Color viewBackgroundColor;
    
    /**
     * QuitView constructor
     * 
     * @param programView
     * @throws IOException
     */
    public QuitView(ProgramView programView) throws IOException 
    {
		// Set program view
    	this.programView = programView;
    	
    	// Set background color
//		this.viewBackgroundColor = programView.getViewBackgroundColor();
		
		// Call this printView method
		printView();
    }
    
    /**
     * Print the quit view to window
     * 
     * @throws IOException
     */
    private void printView() throws IOException  
    {
        // Reset program view
    	programView.getContentPane().removeAll();
//        programView.getContentPane().setBackground(viewBackgroundColor);
    	programView.setVisible(true);
        programView.getContentPane().repaint();
        
        // Prompt user for program close confirmation
        userCloseOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Quit Program", JOptionPane.YES_NO_OPTION);
        
        // If user clicks yes
        if (userCloseOption == JOptionPane.YES_OPTION)
        {
        	// Save session data before program exit
        	programView.getProgramModel().saveDataBeforeExit();
        	
        	// Close the program view window
        	programView.dispatchEvent(new WindowEvent(programView, WindowEvent.WINDOW_CLOSING));
        }
        // If user clicks no
        else if (userCloseOption == JOptionPane.NO_OPTION)
        {
        	// Reset window and reprint main menu
        	programView.getContentPane().removeAll();
        	programView.getContentPane().repaint();
        	programView.printMainMenu(); 
        }
        // If user clicks dialog box "x"
        else 
        {
        	// Reset view and reprint main menu
        	programView.getContentPane().removeAll();
        	programView.getContentPane().repaint();
        	programView.printMainMenu(); 
        }
    }
	
}
