package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DownloadFileView
{
	private UserMenuView userMenuView;
	private ProgramView programView;
	private String fileList;
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
		fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
		
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
		fileButtonPanel.setBounds(315, 345, 50, 250); //last bound was 500
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
		userFileArea.setBounds(385, 345, 300, 250);
		userFileArea.setBackground(Color.lightGray);
		userFileArea.setEditable(false);
		programView.add(userFileArea);
		
		JLabel filePathLabel = new JLabel("Enter the desired file path for the file being downloaded: ");
		filePathLabel.setBounds(315, 610, 400, 40);
		filePathLabel.setForeground(Color.white);
		programView.add(filePathLabel);
		
		JTextField filePathEntry = new JTextField();
		filePathEntry.setBounds(315, 650, 375, 50);
		filePathEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		programView.add(filePathEntry);
		
		JButton downloadFileButton = new JButton("Download File");
		downloadFileButton.setBackground(Color.gray);
		downloadFileButton.setFocusable(false);
		downloadFileButton.setBounds(330, 720, 345, 65);
		programView.add(downloadFileButton);
		
		downloadFileButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String filePath = filePathEntry.getText();
				int fileNumber = 0;
				
				for (int i = 0; i < 10; i++) 
				{
					if (!fileButtons[i].isEnabled())
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
