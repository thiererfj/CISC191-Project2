package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DownloadFileView
{
	private UserMenuView userMenuView;
	private ProgramView programView;
	private String fileList = "";
	private JPanel fileButtonPanel;
	private JButton[] fileButtons;
	
	public DownloadFileView (UserMenuView userMenuView, ProgramView programView) 
	{
		this.userMenuView = userMenuView;
		this.programView = programView;
		this.fileButtons = new JButton[10];
		printView();
	}
	
	private void printView() 
	{
		programView.getContentPane().removeAll();
		programView.addTitleLabel("Download File", Color.lightGray, Color.white);
		userMenuView.addBackButton();
		programView.setVisible(true);
		programView.getContentPane().repaint();
		
		viewDownloadFiles();
		programView.getContentPane().repaint();
	}
	
	private void viewDownloadFiles()
	{
		for (int i = 0; i < 10; i++)
		{
			if (programView.getProgramModel().getDatabase().getGlobalStorage()[programView.getProgramModel().getCurrentUser().getSerialNumber()][i] == null)
			{
				fileList = fileList + (i + 1) + " - Empty\n";
			}
			else
			{ 
				fileList = fileList + (i + 1) + " - " + programView.getProgramModel().getDatabase().getGlobalStorage()[programView.getProgramModel().getCurrentUser().getSerialNumber()][i].getFileName();
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
		userFileArea.setEditable(false);
		programView.add(userFileArea);
		
		JTextField filePathEntry = new JTextField();
		filePathEntry.setBounds(300, 690, 375, 50);
		filePathEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		programView.add(filePathEntry);
		
		JButton uploadFileButton = new JButton("Download File");
		uploadFileButton.setBackground(Color.gray);
		uploadFileButton.setFocusable(false);
		uploadFileButton.setBounds(685, 620, 120, 120);
		programView.add(uploadFileButton);
		
		uploadFileButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String filePath = filePathEntry.getText();
				int fileNumber = 0;
				
				for (int i = 0; i < 10; i++) 
				{
					if (fileButtons[i].isEnabled())
					{
						fileNumber = i;
					}
				}
				
				String downloadError = programView.getProgramModel().getCurrentUser().downloadFileFromDatabase(filePath, fileNumber);
				
				if (Objects.isNull(downloadError))
				{
					JOptionPane.showMessageDialog(null, "File successfully downloaded to your computer");
					programView.getContentPane().removeAll();
					programView.getContentPane().repaint();
					userMenuView.printView();
				}
				else if (downloadError.equals("filePath"))
				{
					JOptionPane.showMessageDialog(null, "File download unsuccessful, check file path");
					printView();
				}
				else if (downloadError.equals("fileNumber"))
				{
					JOptionPane.showMessageDialog(null, "File download unsuccessful, check selected file number");
					printView();
				}
			}
		});
	}
}
