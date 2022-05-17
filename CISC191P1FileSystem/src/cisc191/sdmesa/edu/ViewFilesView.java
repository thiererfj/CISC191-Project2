package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

public class ViewFilesView
{
	private UserMenuView userMenuView;
	private ProgramView programView;
	private String fileList = "";
	
	// 1 to view their own files, 2 to view another user's files
	private int viewType;
	
	public ViewFilesView (UserMenuView userMenuView, ProgramView programView, int viewType) 
	{
		this.userMenuView = userMenuView;
		this.programView = programView;
		this.viewType = viewType;
		printView();
	}
	
	private void printView() 
	{
		programView.getContentPane().removeAll();
		programView.addTitleLabel("View Files", Color.lightGray, Color.white);
		userMenuView.addBackButton();
		programView.setVisible(true);
		viewFiles();
		programView.getContentPane().repaint();
	}
	
	private void viewFiles() 
	{
		if (viewType == 1) 
		{
			fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
		}
		else if (viewType == 2) 
		{
			fileList = "Figure out how to get another user's files";
		}
		
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(450, 300, 300, 250);
		userFileArea.setBackground(Color.lightGray);
		userFileArea.setEditable(false);
		programView.add(userFileArea);
	}
}
