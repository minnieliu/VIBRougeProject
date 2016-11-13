package SephoraGUI;


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

    /**
     * Created by minnieliu on 2016-11-05.
     */
    public class CustomerPage extends JPanel {
        private JFrame frame;
        private JPanel panel;
        private JButton backButton;
        private JButton purchaseButton;
        private JButton returnButton;
        private JButton popularProduct;
        private JButton checkProduct;


        public CustomerPage(){
            super();

           // setUpPage();
        }

        public void setUpPage(){

            frame = new JFrame("Customer Page");
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            try {
                frame.setContentPane(panel=new JPanel(){
                    BufferedImage image = ImageIO.read(new File("./src/resources/customerglitter.jpg"));
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


            JButton purchaseButton = new JButton("Purchase");
            JButton returnButton = new JButton("Return");
            JButton backButton = new JButton("Go Back");

            frame.setLocationRelativeTo(null);
            frame.setResizable(true);
            panel.setLayout(null);


            backButton = new JButton("Go Back");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    CustomerLogInPage clp = new CustomerLogInPage();
                    clp.setUpPage();
                }
            });
            backButton.setBounds(480, 240, 110, 40);
            panel.add(backButton);

            purchaseButton = new JButton("Purchase Product");
            purchaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    PurchaseProduct pp = new PurchaseProduct();
                    pp.setUpPage();
                }
            });
            purchaseButton.setBounds(235,40,130,40);
            panel.add(purchaseButton);

           returnButton = new JButton("Return Product");
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    ReturnProduct rp = new ReturnProduct();
                    rp.setUpPage();
                }
            });
            returnButton.setBounds(235,90,130,40);
            panel.add(returnButton);

            popularProduct = new JButton("Popular Product");
            popularProduct.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    PopularProductReport pp = new PopularProductReport();
                    pp.setUpPage();
                }
            });
            popularProduct.setBounds(235,140,130,40);
            panel.add(popularProduct);

            checkProduct = new JButton("Check Product");
            checkProduct.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    CheckProduct cp = new CheckProduct();
                    cp.setUpPage();
                }
            });
            checkProduct.setBounds(235,190,130,40);
            panel.add(checkProduct);

            frame.setMinimumSize(new Dimension(600, 315));
            frame.pack();
            frame.setLocationRelativeTo(null);
        }

    }
