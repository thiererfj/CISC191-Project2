package cisc191.sdmesa.edu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FileSelectButtonListener implements ActionListener
{
	private ProgramView programView;
	private JButton[] fileSelectButtons;
	private JButton selectedButton;
	
	public FileSelectButtonListener(ProgramView programView, JButton[] fileSelectButtons, JButton selectedButton)
	{
		this.programView = programView;
		this.fileSelectButtons = fileSelectButtons;
		this.selectedButton = selectedButton;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		for (int i = 0; i < fileSelectButtons.length; i++) 
		{
			if (selectedButton.equals(fileSelectButtons[i]))
			{
				fileSelectButtons[i].setEnabled(false);
			}
			else 
			{
				fileSelectButtons[i].setEnabled(true);
			}
		}
		
		programView.getContentPane().repaint();
	}
}
