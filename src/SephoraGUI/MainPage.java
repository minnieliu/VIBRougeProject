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
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.Border;



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

        try {
            frame.setContentPane(panel= new JPanel(){

                BufferedImage image = ImageIO.read(new File("./src/resources/sephora.jpg"));

                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        final JButton employeeButton = new JButton("Employee");
        employeeButton.setFont(new Font("Arial", Font.BOLD, 14));
        employeeButton.setBounds(150,20,100,20);
        panel.add(employeeButton);

        //frame.add(employeeButton);
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                EmployeeLogInPage elp = new EmployeeLogInPage();
                elp.setUpPage();

            }
        });

        JButton customerButton = new JButton("Customer");
        customerButton.setFont(new Font("Arial", Font.BOLD, 14));
        customerButton.setBounds(50,20,100,20);
        panel.add(customerButton);
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerLogInPage clp= new CustomerLogInPage();
                clp.setUpPage();
            }
        });

        frame.pack();
        frame.setMinimumSize(new Dimension(600, 315));
        frame.setLocationRelativeTo(null);
    }


    public static void main(String[] args){
        MainPage mp = new MainPage();
        mp.setUpPage();

    }

}

