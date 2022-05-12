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
    private ProgramView programWindow;
    private ProgramModel programModel;
    private int userCloseOption;

    public QuitView(ProgramView programWindow, ProgramModel programModel) 
    {
		this.programWindow = programWindow;
		this.programModel = programModel;
    }

    public void printView() throws IOException 
    {
        programWindow.getContentPane().removeAll();
        programWindow.getContentPane().setBackground(Color.black);
        programWindow.getContentPane().repaint();
        
        userCloseOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Quit Program", JOptionPane.YES_NO_OPTION);
        
        if (userCloseOption == JOptionPane.YES_OPTION)
        {
        	programModel.saveDataBeforeExit();
        	programWindow.dispatchEvent(new WindowEvent(programWindow, WindowEvent.WINDOW_CLOSING));
        }
        else if (userCloseOption == JOptionPane.NO_OPTION)
        {
        	programWindow.getContentPane().removeAll();
        	programWindow.getContentPane().repaint();
        	programWindow.printMainMenu(); 
        }
    }
	
}
