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
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.*;


public class EmployeePage extends JPanel {
    private JFrame frame;
    private JPanel panel;
    private JButton button;


    public EmployeePage(){
        super();
     //   setUpPage();
    }

    public void setUpPage(){

        frame = new JFrame("Employee Page");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        panel = new JPanel();

        JPanel picturePanel = new JPanel();
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

       // mainpanel.add(picturePanel, BorderLayout.NORTH);
       // mainpanel.add(panel, BorderLayout.SOUTH);

        JButton lowStock = new JButton("Low Stock Report");
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
        frame.add(lowStock);


        frame.add(panel);

    }

    public void setBackGround(JPanel panel){

        ImageIcon background = new ImageIcon("/Users/minnieliu/Documents/VIBRouge/src/resources/sephora.jpg");
        JLabel label = new JLabel();
        label.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
        label.setIcon(background);

        panel.setLayout(null);
        panel.add(label);

    }

}
