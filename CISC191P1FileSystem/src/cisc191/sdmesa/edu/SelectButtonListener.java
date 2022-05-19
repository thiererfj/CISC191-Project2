package cisc191.sdmesa.edu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;

public class SelectButtonListener implements ActionListener
{
	private ProgramView programView;
	private JButton[] fileSelectButtons;
	private JButton selectedButton;
	
	public SelectButtonListener(ProgramView programView, JButton[] fileSelectButtons, JButton selectedButton)
	{
		this.programView = programView;
		this.fileSelectButtons = fileSelectButtons;
		this.selectedButton = selectedButton;
	}

	@Override
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
