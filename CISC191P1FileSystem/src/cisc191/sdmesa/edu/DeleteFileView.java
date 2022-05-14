package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DeleteFileView
{
	private UserMenuView userMenuView;
	private ProgramView programView;
	private String fileList = "";
	private JPanel fileDeleteButtonPanel;
	private JButton[] fileDeleteButtons;
	
	// 1 to delete their own file, 2 to delete another user's file
	private int deleteType;
	
	public DeleteFileView (UserMenuView userMenuView, ProgramView programView, int deleteType) 
	{
		this.userMenuView = userMenuView;
		this.programView = programView;
		this.fileDeleteButtons = new JButton[10];
		this.deleteType = deleteType;
		printView();
	}
	
	private void printView() 
	{
		programView.getContentPane().removeAll();
		programView.addTitleLabel("Delete File", Color.lightGray, Color.white);
		userMenuView.addBackButton();
		programView.setVisible(true);
		viewAndDeleteFiles();
		programView.getContentPane().repaint();
	}
	
	private void viewAndDeleteFiles() 
	{
		if (deleteType == 1) 
		{
			fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
		}
		else if (deleteType == 2) 
		{
			fileList = "Figure out how to delete another user's files";
		}
		
		// Loop to make file buttons
		for (int i = 0; i < 10; i++)
		{
			fileDeleteButtons[i] = new JButton(Integer.toString(i + 1));
			fileDeleteButtons[i].setBackground(Color.gray);
			fileDeleteButtons[i].setFocusable(false);
		}
		
		// Create JPanel for fileDeleteButtons
		fileDeleteButtonPanel = new JPanel();
		fileDeleteButtonPanel.setBounds(300, 350, 50, 250); //last bound was 500
		fileDeleteButtonPanel.setLayout(new GridLayout(10, 1));
		fileDeleteButtonPanel.setOpaque(true);
		
		// Loop to add file buttons to file button panel
		for (int i = 0; i < 10; i++)
		{
			fileDeleteButtonPanel.add(fileDeleteButtons[i]);
		}
		
		programView.add(fileDeleteButtonPanel);
		programView.setVisible(true);
		
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(370, 350, 300, 250);
		userFileArea.setBackground(Color.lightGray);
		userFileArea.setEditable(false);
		programView.add(userFileArea);
	}
}
