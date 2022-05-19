package cisc191.sdmesa.edu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;

public class ViewUserAccountsView
{
    private ProgramView programView;
    private Color viewBackgroundColor;
	private Color viewTitleBoxColor;
	private Color viewButtonColor;
	private Color viewTextColor;

    public ViewUserAccountsView(ProgramView programView) 
    {
        this.programView = programView;
        this.viewBackgroundColor = programView.getViewBackgroundColor();
		this.viewTitleBoxColor = programView.getViewTitleBoxColor();
		this.viewButtonColor = programView.getViewButtonColor();
		this.viewTextColor = programView.getViewTextColor();
        
        printView();
    }

    private void printView() 
    {
        programView.getContentPane().removeAll();
        programView.getContentPane().setBackground(viewBackgroundColor);
        programView.addBackButton();
        programView.addTitleLabel("Accounts", viewTitleBoxColor, viewTextColor);
        
        JTextArea userList = new JTextArea(programView.getProgramModel().viewUserAccounts());
        userList.setBounds(350, 300, 500, 375);
        userList.setFont(new Font("Times New Roman", Font.BOLD, 30));
        
        userList.setBackground(viewBackgroundColor);
        userList.setForeground(viewTextColor);
        programView.add(userList);

        programView.getContentPane().repaint();
    }

}
