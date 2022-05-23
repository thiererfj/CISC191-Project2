package cisc191.sdmesa.edu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;

/**
 * Lead Author(s):
 * @author Anthony Mayoral
 * @author Francis Thierer
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *  
 * Version/date: 4.4 05/22/22
 * 
 * Responsibilities of class:
 * SelectButtonListener is designed to customize an action listener for selection buttons in DeleteFileView and ViewFilesView.
 * When a user clicks a button with this action listener, a click sound will be played and the button is disabled.
 */
public class SelectButtonListener implements ActionListener
{
	// SelectButtonListener has a ProgramView
	private ProgramView programView;
	
	// // SelectButtonListener has an array of JButtons
	private JButton[] fileSelectButtons;
	
	// SelectButtonListener has a selected JButton 
	private JButton selectedButton;
	
	/**
	 * SelectButtonListener constructor
	 * 
	 * @param programView
	 * @param fileSelectButtons
	 * @param selectedButton
	 */
	public SelectButtonListener(ProgramView programView, JButton[] fileSelectButtons, JButton selectedButton)
	{
		// Set program view
		this.programView = programView;
		
		// Set button array
		this.fileSelectButtons = fileSelectButtons;
		
		// Set selected button
		this.selectedButton = selectedButton;
	}
	
	/**
	 * Play a click sound and disable this specific button in the button array
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Try to call clickSound method
		try
		{
			programView.clickSound();
		}
		// Catch LineUnavailableException
		catch (Exception e1)
		{
			// Do nothing, sound just won't play
		}

		// Loop through button array
		for (int i = 0; i < fileSelectButtons.length; i++) 
		{
			// If array button in loop matches user selected button
			if (selectedButton.equals(fileSelectButtons[i]))
			{
				// Disable button
				fileSelectButtons[i].setEnabled(false);
			}
			// If array button is not user selected button
			else 
			{
				// Enable button
				fileSelectButtons[i].setEnabled(true);
			}
		}
		
		// Refresh GUI to show button state
		programView.getContentPane().repaint();
	}
}
