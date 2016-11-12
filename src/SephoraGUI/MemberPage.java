package SephoraGUI;


import VIBClass.SephoraMember;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by hailey on 16/11/11.
 */
public class MemberPage extends JPanel {
    //TODO: ADD More Functions for Member Specific
    private JFrame frame;
    private JPanel contentPane;
    private int accountNo;
    private String name;
    private String phone;
    private SephoraMember sephoraMember;

    public MemberPage(int accountNo,String name,String phone)
    {
        super();
        this.accountNo=accountNo;
        this.name=name;
        this.phone=phone;
        this.sephoraMember=new SephoraMember();
    }

    public void setUpPage(){
        frame = new JFrame("Member Page");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        contentPane = new JPanel();
        frame.add(contentPane);
        contentPane.setLayout(new GridLayout(8,8));


        final JLabel account= new JLabel("Account No."+this.accountNo);
        contentPane.add(account);

        JButton backButton = new JButton("Common Action");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerPage cp = new CustomerPage();
                cp.setUpPage();
            }
        });
        contentPane.add(backButton);

        JButton updateInfo = new JButton("Update Email and Password");
        updateInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UpdateAccountInfo uai = new UpdateAccountInfo(accountNo,name,phone);
                uai.setUpPage();
            }
        });
        contentPane.add(updateInfo);


        JButton checkStatusandPoint = new JButton("Check Status");
        checkStatusandPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String status= sephoraMember.checkStatus(accountNo);
                    int point= sephoraMember.getCurrentPointbyNameAndPhone(name,phone);
                    JOptionPane.showMessageDialog(null,"Your status is " + status+ " with points "+ point,"Message",JOptionPane.PLAIN_MESSAGE);
                }
                catch (Exception error){
                    JOptionPane.showMessageDialog(null,"Your information is not in the database","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(checkStatusandPoint);


        JButton checkCurrentService = new JButton("Check Service for this Month");
        checkCurrentService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:change the return to JTABLE
            }
        });
        contentPane.add(checkCurrentService);

        JButton bookService = new JButton("Book Service");
        bookService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                BookService bs = new BookService(name,phone);
                bs.setUpPage();
            }
        });
        contentPane.add(bookService);

        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
    }

}
