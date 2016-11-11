package SephoraGUI;


import VIBClass.Customer;
import VIBClass.Employee;
import org.omg.PortableInterceptor.Interceptor;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;


public class EmployeePage extends JPanel implements ActionListener{
    private JFrame frame;
    private JPanel panel;

    Employee employee;

    public EmployeePage(){
        super();
        this.employee=new Employee();

     //   setUpPage();
    }

    public void setUpPage(){

        frame = new JFrame("Employee Page");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
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

            JButton getReports = new JButton("Reports");
            getReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ReportPage rp = new ReportPage();
            }
        });


        panel.setLayout(new GridLayout(10,2));

        JLabel productLabel = new JLabel("Product ID:");
        final JTextField prodID = new JTextField("");
        final JLabel NumberAdded = new JLabel("Number of Inventory to Add:");
        final JTextField prodNum = new JTextField("");

        panel.add(productLabel,BorderLayout.WEST);
        panel.add(prodID,BorderLayout.CENTER);
        panel.add(NumberAdded,BorderLayout.WEST);
        panel.add(prodNum,BorderLayout.CENTER);

        JButton addInventory = new JButton("Add Inventory");
        addInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = new Employee();
                int productid = Integer.parseInt(prodID.getText());
                int pNum = Integer.parseInt(prodNum.getText());
                employee.addInventory(productid,pNum);
               // JOptionPane.showMessageDialog(null, "Success", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panel.add(addInventory);
        final JTextField textField = new JTextField(5);
        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainPage mp = new MainPage();
                mp.setUpPage();
            }
        });
        panel.add(getReports);
        panel.add(backButton);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
