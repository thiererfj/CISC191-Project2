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

	private ProgramView programView;
	private String fileList = "";
	private JPanel fileButtonPanel;
	
	UploadFileView(ProgramView programView)
	{
		this.programView = programView;
		programView.getContentPane().removeAll();
		
		programView.getContentPane().removeAll();
		programView.addTitleLabel("File Upload", Color.lightGray, Color.white);
		programView.setVisible(true);
		programView.getContentPane().repaint();
		
		viewFiles();
		programView.getContentPane().repaint();
		
		
		
	}
	
	
	public void viewFiles()
	{
		for(int i = 0; i < 10; i++)
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
		
		
		
		
		
		
		
		
		
		JButton fileButton1 = new JButton("1");
		fileButton1.setBackground(Color.gray);
		fileButton1.setFocusable(false);
	
		JButton fileButton2 = new JButton("2");
		fileButton2.setBackground(Color.gray);
		fileButton2.setFocusable(false);
		
		JButton fileButton3 = new JButton("3");
		fileButton3.setBackground(Color.gray);
		fileButton3.setFocusable(false);
		
		JButton fileButton4 = new JButton("4");
		fileButton4.setBackground(Color.gray);
		fileButton4.setFocusable(false);
		
		JButton fileButton5 = new JButton("5");
		fileButton5.setBackground(Color.gray);
		fileButton5.setFocusable(false);
		
		JButton fileButton6 = new JButton("6");
		fileButton6.setBackground(Color.gray);
		fileButton6.setFocusable(false);
		
		JButton fileButton7 = new JButton("7");
		fileButton7.setBackground(Color.gray);
		fileButton7.setFocusable(false);
		
		JButton fileButton8 = new JButton("8");
		fileButton8.setBackground(Color.gray);
		fileButton8.setFocusable(false);
		
		JButton fileButton9 = new JButton("9");
		fileButton9.setBackground(Color.gray);
		fileButton9.setFocusable(false);
		
		JButton fileButton10 = new JButton("10");
		fileButton10.setBackground(Color.gray);
		fileButton10.setFocusable(false);
		
		
		
		
		fileButton1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				fileButton1.setEnabled(false);
				fileButton2.setEnabled(true);
				fileButton3.setEnabled(true);
				fileButton4.setEnabled(true);
				fileButton5.setEnabled(true);
				fileButton6.setEnabled(true);
				fileButton7.setEnabled(true);
				fileButton8.setEnabled(true);
				fileButton9.setEnabled(true);
				fileButton10.setEnabled(true);
				
				programView.getContentPane().repaint();
				
			}
		});
		
		fileButton2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				
				fileButton1.setEnabled(true);
				fileButton2.setEnabled(false);
				fileButton3.setEnabled(true);
				fileButton4.setEnabled(true);
				fileButton5.setEnabled(true);
				fileButton6.setEnabled(true);
				fileButton7.setEnabled(true);
				fileButton8.setEnabled(true);
				fileButton9.setEnabled(true);
				fileButton10.setEnabled(true);
				
				programView.getContentPane().repaint();
				
			}
		});
		
		fileButton3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				fileButton1.setEnabled(true);
				fileButton2.setEnabled(true);
				fileButton3.setEnabled(false);
				fileButton4.setEnabled(true);
				fileButton5.setEnabled(true);
				fileButton6.setEnabled(true);
				fileButton7.setEnabled(true);
				fileButton8.setEnabled(true);
				fileButton9.setEnabled(true);
				fileButton10.setEnabled(true);
				
				programView.getContentPane().repaint();
				
			}
		});
		
		fileButton4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				fileButton1.setEnabled(true);
				fileButton2.setEnabled(true);
				fileButton3.setEnabled(true);
				fileButton4.setEnabled(false);
				fileButton5.setEnabled(true);
				fileButton6.setEnabled(true);
				fileButton7.setEnabled(true);
				fileButton8.setEnabled(true);
				fileButton9.setEnabled(true);
				fileButton10.setEnabled(true);
				
				programView.getContentPane().repaint();
				
			}
		});
		
		fileButton5.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				fileButton1.setEnabled(true);
				fileButton2.setEnabled(true);
				fileButton3.setEnabled(true);
				fileButton4.setEnabled(true);
				fileButton5.setEnabled(false);
				fileButton6.setEnabled(true);
				fileButton7.setEnabled(true);
				fileButton8.setEnabled(true);
				fileButton9.setEnabled(true);
				fileButton10.setEnabled(true);
				
				programView.getContentPane().repaint();
				
			}
		});
		
		fileButton6.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				fileButton1.setEnabled(true);
				fileButton2.setEnabled(true);
				fileButton3.setEnabled(true);
				fileButton4.setEnabled(true);
				fileButton5.setEnabled(true);
				fileButton6.setEnabled(false);
				fileButton7.setEnabled(true);
				fileButton8.setEnabled(true);
				fileButton9.setEnabled(true);
				fileButton10.setEnabled(true);
				
				programView.getContentPane().repaint();
				
			}
		});
		
		fileButton7.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				fileButton1.setEnabled(true);
				fileButton2.setEnabled(true);
				fileButton3.setEnabled(true);
				fileButton4.setEnabled(true);
				fileButton5.setEnabled(true);
				fileButton6.setEnabled(true);
				fileButton7.setEnabled(false);
				fileButton8.setEnabled(true);
				fileButton9.setEnabled(true);
				fileButton10.setEnabled(true);
				
				programView.getContentPane().repaint();
				
			}
		});
		
		fileButton8.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				fileButton1.setEnabled(true);
				fileButton2.setEnabled(true);
				fileButton3.setEnabled(true);
				fileButton4.setEnabled(true);
				fileButton5.setEnabled(true);
				fileButton6.setEnabled(true);
				fileButton7.setEnabled(true);
				fileButton8.setEnabled(false);
				fileButton9.setEnabled(true);
				fileButton10.setEnabled(true);
				
				programView.getContentPane().repaint();
				
			}
		});
		
		fileButton9.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				fileButton1.setEnabled(true);
				fileButton2.setEnabled(true);
				fileButton3.setEnabled(true);
				fileButton4.setEnabled(true);
				fileButton5.setEnabled(true);
				fileButton6.setEnabled(true);
				fileButton7.setEnabled(true);
				fileButton8.setEnabled(true);
				fileButton9.setEnabled(false);
				fileButton10.setEnabled(true);
				
				programView.getContentPane().repaint();
				
			}
		});
		
		fileButton10.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				fileButton1.setEnabled(true);
				fileButton2.setEnabled(true);
				fileButton3.setEnabled(true);
				fileButton4.setEnabled(true);
				fileButton5.setEnabled(true);
				fileButton6.setEnabled(true);
				fileButton7.setEnabled(true);
				fileButton8.setEnabled(true);
				fileButton9.setEnabled(true);
				fileButton10.setEnabled(false);
				
				programView.getContentPane().repaint();
				
			}
		});
		
		fileButtonPanel = new JPanel();
		fileButtonPanel.setBounds(300, 350, 50, 250); //last bound was 500
		fileButtonPanel.setLayout(new GridLayout(10, 1));
		fileButtonPanel.setOpaque(true);
		
		fileButtonPanel.add(fileButton1);
		fileButtonPanel.add(fileButton2);
		fileButtonPanel.add(fileButton3);
		fileButtonPanel.add(fileButton4);
		fileButtonPanel.add(fileButton5);
		fileButtonPanel.add(fileButton6);
		fileButtonPanel.add(fileButton7);
		fileButtonPanel.add(fileButton8);
		fileButtonPanel.add(fileButton9);
		fileButtonPanel.add(fileButton10);
		
		
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
