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

public class QuitView 
{
    private ProgramView programView;
    private int userCloseOption;
    private Color viewBackgroundColor = Color.decode("#A67B8A");

    public QuitView(ProgramView programView) throws IOException 
    {
		this.programView = programView;
		
		printView();
    }

    private void printView() throws IOException 
    {
        programView.getContentPane().removeAll();
        programView.getContentPane().setBackground(viewBackgroundColor);
        programView.getContentPane().repaint();
        
        // Prompt user for program close confirmation
        userCloseOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Quit Program", JOptionPane.YES_NO_OPTION);
        
        // If user clicks yes
        if (userCloseOption == JOptionPane.YES_OPTION)
        {
        	programView.getProgramModel().saveDataBeforeExit();
        	programView.dispatchEvent(new WindowEvent(programView, WindowEvent.WINDOW_CLOSING));
        }
        // If user clicks no
        else if (userCloseOption == JOptionPane.NO_OPTION)
        {
        	programView.getContentPane().removeAll();
        	programView.getContentPane().repaint();
        	programView.printMainMenu(); 
        }
        // If user clicks dialog box "x"
        else 
        {
        	programView.getContentPane().removeAll();
        	programView.getContentPane().repaint();
        	programView.printMainMenu(); 
        }
    }
	
}
