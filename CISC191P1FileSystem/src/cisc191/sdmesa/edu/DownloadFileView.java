package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Lead Author(s):
 * @author Anthony Mayoral
 * @author Francis Thierer
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *  
 * Version/date: 1.8 05/19/2022
 * 
 * Responsibilities of class:
 * DownloadFileView is designed to reprint the window with GUI components allowing a user to "download" FileData object's (files) contents
 * to a file on their computer. This view gathers required inputs from the user, and communicates with the current User of ProgramModel
 * to execute the task. 
 */
public class DownloadFileView
{
	int deleteThis;
	
	// DownloadFileView has a UserMenuView
	private UserMenuView userMenuView;
	
	// DownloadFileView has a ProgramView
	private ProgramView programView;
	
	// String to hold list of user files
	private String fileList;
	
	// JPanel for file selection buttons
	private JPanel fileSelectButtonPanel;
	
	// JButton[] array for selection buttons
	private JButton[] fileSelectButtons;
	
	// Color objects to set the color scheme
	private Color viewBackgroundColor;
	private Color viewTitleBoxColor;
	private Color viewButtonColor;
	private Color viewTextColor;
	
	public DownloadFileView (UserMenuView userMenuView, ProgramView programView) 
	{
		this.userMenuView = userMenuView;
		this.programView = programView;
		this.fileSelectButtons = new JButton[10];
		this.viewBackgroundColor = programView.getViewBackgroundColor();
		this.viewTitleBoxColor = programView.getViewTitleBoxColor();
		this.viewButtonColor = programView.getViewButtonColor();
		this.viewTextColor = programView.getViewTextColor();
		printView();
	}
	
	private void printView() 
	{
		programView.getContentPane().removeAll();
		programView.addTitleLabel("Download File", viewTitleBoxColor, viewTextColor);
		userMenuView.addBackButton();
		programView.setVisible(true);
		programView.getContentPane().repaint();
		viewDownloadFiles();
		programView.getContentPane().repaint();
	}
	
	private void viewDownloadFiles()
	{
		// Label telling user to click a file button to set file index
		JLabel fileButtonLabel = new JLabel("Select which position to download the file from: ");
		fileButtonLabel.setBounds(100, 255, 300, 40);
		fileButtonLabel.setForeground(viewTextColor);
		programView.add(fileButtonLabel);
		
		fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
		
		// Loop to make file buttons
		for (int i = 0; i < 10; i++)
		{
			fileSelectButtons[i] = new JButton(Integer.toString(i + 1));
			fileSelectButtons[i].setBackground(viewButtonColor);
			fileSelectButtons[i].setForeground(viewTextColor);
			fileSelectButtons[i].setFocusable(false);
			
			fileSelectButtons[i].addActionListener(new SelectButtonListener(programView, fileSelectButtons, fileSelectButtons[i]));
		}
		
		fileSelectButtonPanel = new JPanel();
		fileSelectButtonPanel.setBounds(100, 300, 50, 250); //last bound was 500
		fileSelectButtonPanel.setLayout(new GridLayout(10, 1));
		fileSelectButtonPanel.setOpaque(true);
		
		// Loop to add file buttons to file button panel
		for (int i = 0; i < 10; i++) 
		{
			fileSelectButtonPanel.add(fileSelectButtons[i]);
		}
		
		programView.add(fileSelectButtonPanel);
		programView.setVisible(true);
		
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(170, 300, 300, 250);
		userFileArea.setBackground(viewButtonColor);
		userFileArea.setForeground(viewTextColor);
		userFileArea.setEditable(false);
		programView.add(userFileArea);
		
		JLabel filePathLabel = new JLabel("Enter the desired file path for the file being downloaded: ");
		filePathLabel.setBounds(600, 350, 400, 40);
		filePathLabel.setForeground(viewTextColor);
		programView.add(filePathLabel);
		
		JTextField filePathEntry = new JTextField();
		filePathEntry.setBounds(600, 395, 375, 50);
		filePathEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		programView.add(filePathEntry);
		
		JButton downloadFileButton = new JButton("Download File");
		downloadFileButton.setBackground(viewButtonColor);
		downloadFileButton.setForeground(viewTextColor);
		downloadFileButton.setFocusable(false);
		downloadFileButton.setBounds(420, 600, 345, 65);
		programView.add(downloadFileButton);
		
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
				
				String filePath = filePathEntry.getText();
				int fileNumber = 0;
				
				for (int i = 0; i < 10; i++) 
				{
					if (!fileSelectButtons[i].isEnabled())
					{
						fileNumber = i;
					}
				}
				
				String downloadError = programView.getProgramModel().getCurrentUser().downloadFileFromDatabase(filePath, fileNumber);
				
				if (Objects.isNull(downloadError))
				{
					JOptionPane.showMessageDialog(null, "File successfully downloaded to your computer");
					programView.getContentPane().removeAll();
					programView.getContentPane().repaint();
					userMenuView.printView();
				}
				else if (downloadError.equals("filePath"))
				{
					JOptionPane.showMessageDialog(null, "File download unsuccessful, check file path");
					printView();
				}
				else if (downloadError.equals("fileNumber"))
				{
					JOptionPane.showMessageDialog(null, "File download unsuccessful, check selected file number");
					printView();
				}
			}
		});
	}
}
