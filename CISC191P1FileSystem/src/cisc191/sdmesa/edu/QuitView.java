package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class QuitView 
{
    private ProgramView programWindow;
    private int countdownNum = 11;

    public QuitView(ProgramView programWindow) 
    {
        this.programWindow = programWindow;
    }

    public void printView() 
    {
        programWindow.getContentPane().removeAll();
        programWindow.getContentPane().setBackground(Color.black);
        programWindow.addBackButton();

        // 11 second countdown to close program, if user does not click "X" to exit
        Timer timer = new Timer(11000, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				programWindow.dispose();
				System.exit(0);
			}
		});
        
        timer.start();

        JTextArea goodByeMessage = new JTextArea("This program will self destruct in 10 seconds...\n" +
        "Click the back button if you want to continue... and live...\nSee you later, friend! ;)");
        goodByeMessage.setBackground(Color.black);
        goodByeMessage.setForeground(Color.green);
        goodByeMessage.setEditable(false);
        goodByeMessage.setOpaque(true);
        goodByeMessage.setFont(new Font("Times New Roman", Font.BOLD, 36));
        goodByeMessage.setBounds(50, 100, 900, 400);
        
        programWindow.add(goodByeMessage);

        JLabel countdownTimer = new JLabel();
        countdownTimer.setText(Integer.toString(countdownNum));
        countdownTimer.setBackground(Color.black);
        countdownTimer.setForeground(Color.green);
        countdownTimer.setOpaque(true);
        countdownTimer.setFont(new Font("Times New Roman", Font.BOLD, 100));
        countdownTimer.setBounds(450, 400, 150, 100);

        programWindow.add(countdownTimer);

        Timer timer2 = new Timer(1000, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                countdownNum--;
                countdownTimer.setText(Integer.toString(countdownNum));
            }
        });

        timer2.start();
        
        programWindow.getContentPane().repaint();
    }

    public void addBackButton() 
    {
    	JButton backButton = new JButton("Back");
        backButton.setBackground(Color.lightGray);
        backButton.setFocusable(false);
        backButton.setBounds(20, 20, 70, 40);
        programWindow.add(backButton);
        
        backButton.addActionListener(new ActionListener()
	    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  programWindow.getContentPane().removeAll();
		    	  programWindow.getContentPane().repaint();
		    	  programWindow.printMainMenu(); 
		      }
		});
    }
	
}
