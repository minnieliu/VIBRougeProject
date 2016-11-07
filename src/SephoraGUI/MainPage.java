package SephoraGUI;

import VIBClass.Employee;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;

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

/**
 * Created by minnieliu on 2016-11-05.
 */
public class MainPage extends JPanel{
    private JFrame frame;
    private JPanel panel;
    private JButton button;


    public MainPage(){
        super();
       // this.setUpPage();

    }

    public void setUpPage(){
        frame = new JFrame("VIB Rouge");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
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

        JButton employeeButton = new JButton("Employee");
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                EmployeePage ep = new EmployeePage();
                ep.setUpPage();

            }
        });

        JButton customerButton = new JButton("Customer");
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerPage cp = new CustomerPage();
                cp.setUpPage();
            }
        });
        frame.add(employeeButton);
        frame.add(customerButton);


    }

    public static void main(String[] args){
        MainPage mp = new MainPage();
        mp.setUpPage();

    }

}