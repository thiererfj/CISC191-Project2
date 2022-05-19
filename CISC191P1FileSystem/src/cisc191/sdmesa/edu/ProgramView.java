package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class ProgramView extends JFrame 
{
	private final String VERSION = "1.5";
	private ProgramModel programModel;
//	private User currentUser;
	private Color viewBackgroundColor = Color.decode("#023859");
	private Color viewTitleBoxColor = Color.decode("#034B8A");
	private Color viewButtonColor = Color.decode("#B59C8D");
	private Color viewTextColor = Color.decode("#F2F2F2");

	/**
	 * Purpose: Constructor for ProgramView that sets information for the JFrame
	 * 
	 * @param DATABASE
	 */
	public ProgramView(ProgramModel programModel)
	{
		this.programModel = programModel;
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setTitle("Angry File System - Version: " + VERSION);

		// For the first frame
		// Program Title -->
		// Create Account/Log in to User Account / View User Accounts / Quit
		// Program
	}

	public ProgramModel getProgramModel()
	{
		return programModel;
	}

	/**
	 * Purpose: Changes view to the PrintMainMenu Menu
	 */
	public void printMainMenu() throws IOException
	{
		// Set black background here so sub frame doesn't change it when going back to main menu
		getContentPane().setBackground(viewBackgroundColor);

		// Add title JLabel to window
		addTitleLabel("Angry File System", viewTitleBoxColor, viewTextColor);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 20, 20));
		buttonPanel.setOpaque(true);
		buttonPanel.setBackground(viewBackgroundColor);
		buttonPanel.setBounds(150, 600, 900, 100);

		// Maybe we can rework this? What if there are 9 basic users but no super user
		if (programModel.getDatabase().getUsers()[9] == null)
		{
			JButton createAccountButton = new JButton("Create Account");
			createAccountButton.setBackground(viewButtonColor);
			createAccountButton.setForeground(viewTextColor);
			createAccountButton.setFocusable(false);
			buttonPanel.add(createAccountButton);

			// Add button panel to main window
			add(buttonPanel);

			createAccountButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					createAccountVisuals();
					
					try {
						clickSound();
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
					
					
				}
			});
		}

		JButton loginButton = new JButton("Log In");
		loginButton.setBackground(viewButtonColor);
		loginButton.setForeground(viewTextColor);
		loginButton.setFocusable(false);
		buttonPanel.add(loginButton);

		loginButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				loginVisuals();
				
				try {
					clickSound();
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
			}
		});

		JButton viewUserAccountsButton = new JButton("View Accounts");
		viewUserAccountsButton.setBackground(viewButtonColor);
		viewUserAccountsButton.setForeground(viewTextColor);
		viewUserAccountsButton.setFocusable(false);
		buttonPanel.add(viewUserAccountsButton);

		viewUserAccountsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				viewUserAccountsVisuals();
				
				try {
					clickSound();
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
			}
		});

		JButton quitProgramButton = new JButton("Quit Program (Save Data)");
		quitProgramButton.setBackground(viewButtonColor);
		quitProgramButton.setForeground(viewTextColor);
		quitProgramButton.setFocusable(false);
		buttonPanel.add(quitProgramButton);

		quitProgramButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					clickSound();
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
				
				try
				{
					closeProgramVisuals();
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});

		// Make main window visible
		setVisible(true);
	}

	/**
	 * Purpose: Changes view to the CreateAccountVisuals Menu
	 */
	private void createAccountVisuals()
	{
		CreateAccountView createAccountView = new CreateAccountView(this);
	}

	/**
	 * Purpose: Changes view to the LoginVisuals Menu
	 */
	private void loginVisuals()
	{
		LoginView loginView = new LoginView(this);
	}

	/**
	 * Purpose: Changes view to the ViewUserAccounts Menu
	 */
	private void viewUserAccountsVisuals()
	{
		// Create new VUAV object to display user accounts info
		ViewUserAccountsView viewUserAccountsView = new ViewUserAccountsView(this);
	}

	/**
	 * Purpose: Terminates the program
	 * 
	 * @throws IOException (should never happen)
	 */
	private void closeProgramVisuals() throws IOException
	{
		QuitView quitView = new QuitView(this);
	}

	/**
	 * Purpose: To add a back button to a certain menu view
	 */
	public void addBackButton()
	{
		JButton backButton = new JButton("Back");
		backButton.setBackground(viewButtonColor);
		backButton.setForeground(viewTextColor);
		backButton.setFocusable(false);
		backButton.setBounds(20, 20, 70, 40);

		// Add widget to this window
		add(backButton);

		backButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().repaint();
				
				try {
					clickSound();
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
				
				
				try {
					printMainMenu();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * Purpose: To add a title label to a certain menu view
	 * 
	 * @param title
	 * @param backgroundColor
	 * @param foregroundColor
	 */
	public void addTitleLabel(String title, Color backgroundColor, Color foregroundColor)
	{
		JLabel titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setOpaque(true);
		titleLabel.setBackground(backgroundColor);
		titleLabel.setBounds(400, 50, 400, 150);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		titleLabel.setForeground(foregroundColor);
		add(titleLabel);
	}
	
	public Color getViewTitleBoxColor() 
	{
		return viewTitleBoxColor;
	}
	
	public Color getViewTextColor()
	{
		return viewTextColor;
	}
	
	public Color getViewButtonColor()
	{
		return viewButtonColor;
	}
	
	public Color getViewBackgroundColor() 
	{
		return viewBackgroundColor;
	}
	
	public void clickSound() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		File barkSound = new File("ClickSound.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(barkSound);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
	}

}
