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

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
        frame.setResizable(false);

        try {
            frame.setContentPane(panel=new JPanel(){

                BufferedImage image = ImageIO.read(new File("./src/resources/glitter.jpg"));

                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        panel.setBounds(100,100,450,300);
        frame.setContentPane(panel);
        panel.setLayout(null);

        JButton getReports = new JButton("Reports");
        getReports.setBounds(220,90,110,40);
        panel.add(getReports);


        getReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ReportPage rp = new ReportPage();
            }
        });


        JLabel productLabel = new JLabel("Product ID:");
        productLabel.setBounds(50, 51, 80, 20);
        panel.add(productLabel);
        final JTextField prodID = new JTextField("");
        prodID.setBounds(140, 51, 110, 20);
        panel.add(prodID);
        JLabel NumberAdded = new JLabel("Number added:");
        NumberAdded.setBounds(260, 51, 100, 20);
        panel.add(NumberAdded);
        final JTextField prodNum = new JTextField("");
        prodNum.setBounds(370, 51, 110, 20);
        panel.add(prodNum);


        JButton addInventory = new JButton("Add Inventory");
        addInventory.setBounds(100,90,110,40);
        panel.add(addInventory);
        addInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = new Employee();

                try {
                    int productid = Integer.parseInt(prodID.getText());
                    int pNum = Integer.parseInt(prodNum.getText());
                    boolean b = employee.addInventory(productid, pNum);
                    if (!b) {
                        JOptionPane.showMessageDialog(null, "Check that the product ID is valid", "Error!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Successfully Added Inventory!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch(Exception e1){
                    JOptionPane.showMessageDialog(null,"Check that inputs are valid","Error!",JOptionPane.ERROR_MESSAGE);}
                }

        });
        panel.add(addInventory);

        JButton managerButton = new JButton("Manager Actions");
        managerButton.setBounds(340,90,130,40);
        panel.add(managerButton);

        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ManagerReports mp = new ManagerReports();
                mp.setUpPage();
            }
        });



        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ManagerReports mp = new ManagerReports();
                mp.setUpPage();
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
        backButton.setBounds(480,240,110,40);
        panel.add(backButton);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
