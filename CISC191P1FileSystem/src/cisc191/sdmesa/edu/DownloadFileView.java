package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	private JPanel fileSelectButtonPanel;
	private JButton[] fileSelectButtons;
	private Color viewBackgroundColor = Color.decode("#A67B8A");
	private Color viewTitleBoxColor = Color.decode("#79BED9");
	private Color viewButtonColor = Color.decode("#324759");
	private Color viewTextColor = Color.decode("#EBF2F2");
	
	public DownloadFileView (UserMenuView userMenuView, ProgramView programView) 
	{
		this.userMenuView = userMenuView;
		this.programView = programView;
		this.fileSelectButtons = new JButton[10];
		printView();
	}
	
	private void printView() 
	{
		programView.getContentPane().removeAll();
		programView.addTitleLabel("Download File", viewTitleBoxColor, viewTextColor);
		userMenuView.addBackButton();
		programView.setVisible(true);
		programView.getContentPane().repaint();
		viewDownloadFiles();
		programView.getContentPane().repaint();
	}
	
	private void viewDownloadFiles()
	{
		// Label telling user to click a file button to set file index
		JLabel fileButtonLabel = new JLabel("Select which position to download the file from: ");
		fileButtonLabel.setBounds(100, 255, 300, 40);
		fileButtonLabel.setForeground(viewTextColor);
		programView.add(fileButtonLabel);
		
		fileList = programView.getProgramModel().getCurrentUser().viewUserFiles();
		
		// Loop to make file buttons
		for (int i = 0; i < 10; i++)
		{
			fileSelectButtons[i] = new JButton(Integer.toString(i + 1));
			fileSelectButtons[i].setBackground(viewButtonColor);
			fileSelectButtons[i].setForeground(viewTextColor);
			fileSelectButtons[i].setFocusable(false);
			
			fileSelectButtons[i].addActionListener(new SelectButtonListener(programView, fileSelectButtons, fileSelectButtons[i]));
		}
		
		fileSelectButtonPanel = new JPanel();
		fileSelectButtonPanel.setBounds(100, 300, 50, 250); //last bound was 500
		fileSelectButtonPanel.setLayout(new GridLayout(10, 1));
		fileSelectButtonPanel.setOpaque(true);
		
		// Loop to add file buttons to file button panel
		for (int i = 0; i < 10; i++) 
		{
			fileSelectButtonPanel.add(fileSelectButtons[i]);
		}
		
		programView.add(fileSelectButtonPanel);
		programView.setVisible(true);
		
		JTextArea userFileArea = new JTextArea(fileList);
		userFileArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userFileArea.setBounds(170, 300, 300, 250);
		userFileArea.setBackground(viewButtonColor);
		userFileArea.setForeground(viewTextColor);
		userFileArea.setEditable(false);
		programView.add(userFileArea);
		
		JLabel filePathLabel = new JLabel("Enter the desired file path for the file being downloaded: ");
		filePathLabel.setBounds(600, 350, 400, 40);
		filePathLabel.setForeground(viewTextColor);
		programView.add(filePathLabel);
		
		JTextField filePathEntry = new JTextField();
		filePathEntry.setBounds(600, 395, 375, 50);
		filePathEntry.setFont(new Font("Times New Roman", Font.BOLD, 30));
		programView.add(filePathEntry);
		
		JButton downloadFileButton = new JButton("Download File");
		downloadFileButton.setBackground(viewButtonColor);
		downloadFileButton.setForeground(viewTextColor);
		downloadFileButton.setFocusable(false);
		downloadFileButton.setBounds(420, 600, 345, 65);
		programView.add(downloadFileButton);
		
		downloadFileButton.addActionListener(new ActionListener()
		{
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
				
				String filePath = filePathEntry.getText();
				int fileNumber = 0;
				
				for (int i = 0; i < 10; i++) 
				{
					if (!fileSelectButtons[i].isEnabled())
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
