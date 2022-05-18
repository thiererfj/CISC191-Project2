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
    private Color viewBackgroundColor = Color.decode("#A67B8A");
	private Color viewTitleBoxColor = Color.decode("#79BED9");
	private Color viewButtonColor = Color.decode("#324759");
	private Color viewTextColor = Color.decode("#EBF2F2");

    public ViewUserAccountsView(ProgramView programView) 
    {
        this.programView = programView;
        
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
