package SephoraGUI;


import VIBClass.Customer;
import VIBClass.Employee;
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
                BufferedImage image = ImageIO.read(new File("/Users/hailey/Desktop/CPSC304/VIBRougeProject/src/resources/sephora.jpg"));
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        JButton lowStock = new JButton("Low Stock Report");
        final JTextArea textArea = new JTextArea();

        lowStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ReportPage rp = new ReportPage();
                rp.setUpPage();
                Employee employee = new Employee();
                JTable lowStockreport = employee.lowStockReport(50);
                StringBuilder sb = new StringBuilder();
                JScrollPane tableContainer = new JScrollPane(lowStockreport);
                System.out.print("adding table");
                panel.add(tableContainer, BorderLayout.CENTER);
           //     frame.getContentPane().add(panel);

            }
        });

        JButton backButton = new JButton("Go Back");


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainPage mp = new MainPage();
                mp.setUpPage();
            }
        });
        //panel.add(textArea);
        panel.add(backButton);
        panel.add(lowStock);



    //    frame.add(panel);

        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
    }

//    public void printTextField(String text) {
//        System.out.print("got to here");
//        //textArea = new JTextArea(5,20);
//        textArea.setText(text);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
