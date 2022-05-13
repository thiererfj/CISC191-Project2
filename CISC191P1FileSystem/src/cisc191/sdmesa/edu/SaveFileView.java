package cisc191.sdmesa.edu;

import java.awt.Color;

public class SaveFileView
{
	private UserMenuView userMenuView;
	private ProgramView programView;
	
	public SaveFileView (UserMenuView userMenuView, ProgramView programView) 
	{
		this.userMenuView = userMenuView;
		this.programView = programView;
		
		printView();
	}
	
	private void printView() 
	{
		programView.getContentPane().removeAll();
		programView.addTitleLabel("Save File", Color.lightGray, Color.white);
		userMenuView.addBackButton();
		programView.setVisible(true);
		programView.getContentPane().repaint();
	}
}
