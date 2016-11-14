package SephoraGUI;


import VIBClass.SephoraMember;
import VIBClass.Service;

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
    private JFrame frame;
    private JPanel contentPane;
    private int accountNo;
    private String name;
    private String phone;
    private SephoraMember sephoraMember;
    private Service service;

    public MemberPage(int accountNo,String name,String phone)
    {
        super();
        this.accountNo=accountNo;
        this.name=name;
        this.phone=phone;
        this.sephoraMember=new SephoraMember();
        this.service=new Service();
    }

    public void setUpPage() {
        frame = new JFrame("Member Page");

        try {
            frame.setContentPane(contentPane=new JPanel(){

                BufferedImage image = ImageIO.read(new File("./src/resources/customerglitter.jpg"));

                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

      //  frame.add(contentPane);
        contentPane.setLayout(null);

        final JLabel account = new JLabel("Account No." + this.accountNo);
        account.setFont(new Font("Arial", Font.BOLD, 14));
        account.setBounds(30,10,180,20);
        contentPane.add(account);

        final JLabel myAccount = new JLabel("My Account");
        myAccount.setBounds(80,35,110,20);
        contentPane.add(myAccount);

        JButton updateInfo = new JButton("Update Info");
        updateInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UpdateAccountInfo uai = new UpdateAccountInfo(accountNo, name, phone);
                uai.setUpPage();
            }
        });
        updateInfo.setBounds(80,65,130,40);
        contentPane.add(updateInfo);

        JButton purchaseHistory = new JButton("Purchase History");
        purchaseHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MemberPurchaseHistory mph = new MemberPurchaseHistory(accountNo, name, phone);
                mph.setUpPage();
            }
        });
        purchaseHistory.setBounds(80,115,130,40);
        contentPane.add(purchaseHistory);

        JButton checkStatusandPoint = new JButton("Check Status");
        checkStatusandPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int point = sephoraMember.getCurrentPointbyNameAndPhone(name, phone);
                    String status = sephoraMember.updateStatus(point,name,phone);
                    JOptionPane.showMessageDialog(null, "Your status is " + status + " with points " + point, "Message", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, "Your information is not in the database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        checkStatusandPoint.setBounds(80,165,130,40);
        contentPane.add(checkStatusandPoint);


        final JLabel productsServices = new JLabel("Products/Services");
        productsServices.setBounds(260,35,180,20);
        contentPane.add(productsServices);

        JButton purchase = new JButton("Purchase/Return");
        purchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerPage cp = new CustomerPage();
                cp.setUpPage();
            }
        });
        purchase.setBounds(260,65,130,40);
        contentPane.add(purchase);

        JLabel lblMonth = new JLabel("Service Month:");
        lblMonth.setBounds(260,115, 110, 40);
        contentPane.add(lblMonth);
        final JTextField txtMonth = new JTextField();
        txtMonth.setBounds(380,115,60,40);
        contentPane.add(txtMonth);

        JButton bookService = new JButton("Book Service");
        bookService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                BookService bs = new BookService(name, phone, accountNo);
                bs.setUpPage();
            }
        });
        bookService.setBounds(260,215,130,40);
        contentPane.add(bookService);

        JButton checkCurrentService = new JButton("Check Monthly Service");
        checkCurrentService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int month = Integer.parseInt(txtMonth.getText());
                frame.dispose();
                ServiceThisMonth stm = new ServiceThisMonth(month, accountNo, name, phone);
                stm.setUpPage();
            }
        });
        checkCurrentService.setBounds(260,165,160,40);
        contentPane.add(checkCurrentService);

        JButton back = new JButton("Go Back");
        back.setBounds(480, 240, 110, 40);
        contentPane.add(back);


        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerLogInPage cp = new CustomerLogInPage();
                cp.setUpPage();
            }
        });

        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

    }

}
