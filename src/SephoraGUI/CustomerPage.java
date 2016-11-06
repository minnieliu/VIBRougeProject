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
        private JButton button;


        public CustomerPage(){
            super();
           // setUpPage();
        }

        public void setUpPage(){
            frame = new JFrame("Customer Page");
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            panel = new JPanel();

            try {
                frame.setContentPane(new JPanel(){
                    BufferedImage image = ImageIO.read(new File("/Users/minnieliu/Documents/VIBRouge/src/resources/sephora.jpg"));
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            frame.pack();
            frame.setSize(600,315);


            JButton purchaseButton = new JButton("Purchase");
            JButton returnButton = new JButton("Return");
            JButton backButton = new JButton("Go Back");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    MainPage mp = new MainPage();
                    mp.setUpPage();
                }
            });
            frame.add(backButton);
            frame.add(purchaseButton);
            frame.add(returnButton);
            frame.add(panel);

        }

    }
