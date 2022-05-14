package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class UploadFileView 
{
	private UserMenuView userMenuView;
	private ProgramView programView;
	private String fileList = "";
	private JPanel fileButtonPanel;
	private JButton[] fileButtons;
	
	public UploadFileView (UserMenuView userMenuView, ProgramView programView)
	{
		this.userMenuView = userMenuView;
		this.programView = programView;
		this.fileButtons = new JButton[10];
		
		printView();
	}
	
	public void printView() 
	{
		programView.getContentPane().removeAll();
		programView.addTitleLabel("Upload File", Color.lightGray, Color.white);
		userMenuView.addBackButton();
		programView.setVisible(true);
		programView.getContentPane().repaint();
		
		viewFiles();
		programView.getContentPane().repaint();
	}
	
	public void viewFiles()
	{
		for (int i = 0; i < 10; i++)
		{
			if (programView.getProgramModel().getDatabase().getGlobalStorage()[programView.getProgramModel().getCurrentUser().getSerialNumber()][i] == null)
			{
				fileList = fileList + (i + 1) + " - Empty\n";
			}
			else
			{ 
				fileList = fileList + (i + 1) + " - " + getDatabase().getGlobalStorage()[programView.getProgramModel().getCurrentUser().getSerialNumber()][i].getFileName();
			}
			
		}
		
		// Loop to make file buttons
		for (int i = 0; i < 10; i++) 
		{
			fileButtons[i] = new JButton(Integer.toString(i + 1));
			fileButtons[i].setBackground(Color.gray);
			fileButtons[i].setFocusable(false);
		}
		
		fileButtons[0].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(false);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[1].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(false);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[2].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(false);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[3].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(false);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[4].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(false);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[5].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(false);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[6].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(false);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[7].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(false);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[8].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(false);
				fileButtons[9].setEnabled(true);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtons[9].addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fileButtons[0].setEnabled(true);
				fileButtons[1].setEnabled(true);
				fileButtons[2].setEnabled(true);
				fileButtons[3].setEnabled(true);
				fileButtons[4].setEnabled(true);
				fileButtons[5].setEnabled(true);
				fileButtons[6].setEnabled(true);
				fileButtons[7].setEnabled(true);
				fileButtons[8].setEnabled(true);
				fileButtons[9].setEnabled(false);
				
				programView.getContentPane().repaint();
			}
		});
		
		fileButtonPanel = new JPanel();
		fileButtonPanel.setBounds(300, 350, 50, 250); //last bound was 500
		fileButtonPanel.setLayout(new GridLayout(10, 1));
		fileButtonPanel.setOpaque(true);
		
		// Loop to add file buttons to file button panel
		for (int i = 0; i < 10; i++) 
		{
			fileButtonPanel.add(fileButtons[i]);
		}
		
		programView.add(fileButtonPanel);
		programView.setVisible(true);
		
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(370, 350, 300, 250);
		userFileArea.setBackground(Color.lightGray);
		
		programView.add(userFileArea);
		
		JTextField fileNameEntry = new JTextField();
		fileNameEntry.setBounds(300, 620, 375, 50);
		fileNameEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		programView.add(fileNameEntry);
		
		JTextField filePathEntry = new JTextField();
		filePathEntry.setBounds(300, 690, 375, 50);
		filePathEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		programView.add(filePathEntry);
		
		JButton uploadFileButton = new JButton("Upload File");
		uploadFileButton.setBackground(Color.gray);
		uploadFileButton.setFocusable(false);
		uploadFileButton.setBounds(685, 620, 50, 100);
		programView.add(uploadFileButton);
	}
	
	public Database getDatabase()
	{
		return null;
	}
	
	
	
	
	
	// Loop through this user's row in FileData[][]
		
}
