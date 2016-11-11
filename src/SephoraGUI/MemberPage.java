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
 * Created by hailey on 16/11/11.
 */
public class MemberPage extends JPanel {
    //TODO: ADD More Functions for Member Specific
    private JFrame frame;
    private JPanel panel;

    public MemberPage(){
        super();
        // setUpPage();
    }

    public void setUpPage(){
        frame = new JFrame("Member Page");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        try {
            frame.setContentPane(panel=new JPanel(){
                BufferedImage image = ImageIO.read(new File("/Users/hailey/Desktop/CPSC304/VIBRougeProject/src/resources/sephora.jpg"));
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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainPage mp = new MainPage();
                mp.setUpPage();
            }
        });

        panel.add(backButton);
        panel.add(purchaseButton);
        panel.add(returnButton);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
    }

}