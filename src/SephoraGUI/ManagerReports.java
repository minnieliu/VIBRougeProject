package SephoraGUI;

import VIBClass.Employee;
import VIBClass.Product;
import VIBClass.PurchaseHistory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.ExpandVetoException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by minnieliu on 2016-11-11.
 */
public class ManagerReports {
    private JFrame frame;
    private JPanel panel;

    Employee employee;

    public ManagerReports(){
        super();
        this.employee=new Employee();
    }

    public void setUpPage() {

        frame = new JFrame("Report Page");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        try {
            frame.setContentPane(panel = new JPanel() {
                BufferedImage image = ImageIO.read(new File("./src/resources/glitter.jpg"));

                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        panel.setBounds(100,100,450,300);
        frame.setContentPane(panel);
        panel.setLayout(null);

        JButton avgSales = new JButton("Average Item Purchased");
        avgSales.setBounds(100,155,180,40);
        panel.add(avgSales);

        avgSales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AverageItemPerCustomer table = new AverageItemPerCustomer();
            }
        });

        JButton avgPrice = new JButton("Average Price Per Type");
        avgPrice.setBounds(290,155,180,40);
        panel.add(avgPrice);
        avgPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AveragePricePerType table = new AveragePricePerType();
            }
        });
        //TODO need to add/remove employees
        //TODO make employees login with employeeID
        //TODO add add/delete product
        final JTextField textnumber = new JTextField();
        textnumber.setBounds(190,60,150,20);
        panel.add(textnumber);
        JLabel lblNumber = new JLabel("Customer Phone#:");
        lblNumber.setBounds(50,60,130,20);
        panel.add(lblNumber);
        JButton removeCustomer = new JButton("Remove Customer");
        removeCustomer.setBounds(350,60,150,20);
        panel.add(removeCustomer);

        final JTextField txtproductID = new JTextField();
        txtproductID.setBounds(190,90,150,20);
        panel.add(txtproductID);
        JLabel lblProductID = new JLabel("Product ID:");
        lblProductID.setBounds(50,90,130,20);
        panel.add(lblProductID);
        JButton removeProduct = new JButton("Remove Product");
        removeProduct.setBounds(350,90,150,20);
        panel.add(removeProduct);

        final JTextField employeeID = new JTextField();
        employeeID.setBounds(190,120,150,20);
        panel.add(employeeID);
        JLabel lblemployeeID = new JLabel("Employee ID:");
        lblemployeeID.setBounds(50,120,130,20);
        panel.add(lblemployeeID);
        JButton removeEmpoylee = new JButton("Remove Employee");
        removeEmpoylee.setBounds(350,120,150,20);
        panel.add(removeEmpoylee);

        removeProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int pid = Integer.parseInt(txtproductID.getText());
                    Product p = new Product();
                    boolean success = p.removeProduct(pid);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Product Successfully Removed!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ProductID invalid!", "Error!", JOptionPane.ERROR_MESSAGE);

                    }
                }catch(Exception err){
                    JOptionPane.showMessageDialog(null, "ProductID invalid!", "Error!", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        removeEmpoylee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int employeeId = Integer.parseInt(employeeID.getText());
                    Employee employee = new Employee();
                    if (employee.removeEmployee(employeeId)){
                        JOptionPane.showMessageDialog(null, "Employee Successfully Removed!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "EmployeeID invalid!", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(Exception err ){
                    JOptionPane.showMessageDialog(null, "EmployeeID invalid!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });




        removeCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String numberfromtext = textnumber.getText();
                    Employee employee = new Employee();
                    if (employee.removeCustomer(numberfromtext)){
                        JOptionPane.showMessageDialog(null, "Customer Successfully Deleted", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Please check that number is valid", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Please check that number is valid", "Error!", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                EmployeePage ep = new EmployeePage();
                ep.setUpPage();
            }
        });
        backButton.setBounds(480,240,110,40);
        panel.add(backButton);

        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);


    }



}
