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

import static com.sun.tools.doclint.Entity.image;

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

        try {
            frame.setContentPane(panel= new JPanel(){
                BufferedImage image = ImageIO.read(new File("/Users/minnieliu/VIBRougeProject/src/resources/sephora.jpg"));
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        JButton employeeButton = new JButton("Employee");
        panel.add(employeeButton);
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                EmployeePage ep = new EmployeePage();
                ep.setUpPage();

            }
        });
        JButton customerButton = new JButton("Customer");
        panel.add(customerButton);
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerPage cp = new CustomerPage();
                cp.setUpPage();

            }
        });

        final JTextField textField = new JTextField(10);
        String text = textField.getText();
        System.out.print("Text from GUI: " + text);
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.print("calling save");
                    saveToFile("FileName", textField);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });


        panel.add(saveButton);
        panel.add(textField);


        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
    }

    void saveToFile(String fileName, JTextField textField) throws Exception {
        System.out.print("in save");
        FileOutputStream out = new FileOutputStream(fileName, true);
        System.out.print("String from textbox: " + textField.getText());
        out.write(textField.getText().getBytes());
    }



    public static void main(String[] args){
        MainPage mp = new MainPage();
        mp.setUpPage();

    }

}

