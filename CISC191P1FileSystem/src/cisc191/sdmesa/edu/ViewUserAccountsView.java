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

    public ViewUserAccountsView(ProgramView programView) 
    {
        this.programView = programView;
        
        printView();
    }

    private void printView() 
    {
        programView.getContentPane().removeAll();
        programView.getContentPane().setBackground(Color.black);
        programView.addBackButton();
        programView.addTitleLabel("Accounts", Color.LIGHT_GRAY, Color.WHITE);
        
        JTextArea userList = new JTextArea(programView.getProgramModel().viewUserAccounts());
        userList.setBounds(250,325, 500, 375);
        userList.setFont(new Font("Times New Roman", Font.BOLD, 30));
        
        userList.setBackground(Color.black);
        userList.setForeground(Color.white);
        programView.add(userList);

        programView.getContentPane().repaint();
    }

}
