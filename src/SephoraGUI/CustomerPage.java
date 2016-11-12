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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.*;


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
                    BufferedImage image = ImageIO.read(new File("/Users/minnieliu/VIBRougeProject/src/resources/glitter.jpg"));
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
            panel=new JPanel();
            panel.setLayout(new GridLayout(8,8));

//            try {
//                frame.setContentPane(panel=new JPanel(){
//                    BufferedImage image = ImageIO.read(new File("/Users/hailey/Desktop/CPSC304/VIBRougeProject/src/resources/sephora.jpg"));
//                    public void paintComponent(Graphics g) {
//                        super.paintComponent(g);
//                        g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
//                    }
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            frame.add(panel);

            backButton = new JButton("Go Back");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    CustomerLogInPage clp = new CustomerLogInPage();
                    clp.setUpPage();
                }
            });
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
            panel.add(checkProduct);

            frame.setMinimumSize(new Dimension(600, 315));
            frame.pack();
            frame.setLocationRelativeTo(null);
        }

    }
