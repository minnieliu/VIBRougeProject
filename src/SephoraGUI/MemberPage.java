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
    private SephoraMember sephoraMember;

    public MemberPage(){
        super();
        this.sephoraMember= new SephoraMember();
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UpdateAccountInfo uai = new UpdateAccountInfo();
                uai.setUpPage();
            }
        });
        contentPane.add(updateInfo);


        JButton checkStatusandPoint = new JButton("Check Status");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        contentPane.add(checkStatusandPoint);


        JButton checkCurrentService = new JButton("Check Service");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        contentPane.add(checkCurrentService);

        JButton bookService = new JButton("Book Service");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                BookService bs = new BookService();
                bs.setUpPage();
            }
        });
        contentPane.add(bookService);

        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
    }

}
