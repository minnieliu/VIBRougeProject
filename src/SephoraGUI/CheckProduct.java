package SephoraGUI;


import VIBClass.Customer;
import VIBClass.Product;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by hailey on 16/11/11.
 */
public class CheckProduct {
    private JFrame frame;
    private JPanel contentPane;
    private Product product;
    public CheckProduct() // main method
    {
        super();
        product= new Product();
    }

    public void setUpPage() //create constructor
    {
        frame = new JFrame("Check Product Page");

        try {
            frame.setContentPane(contentPane=new JPanel(){
                BufferedImage image = ImageIO.read(new File("./src/resources/customerglitter.jpg"));
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        contentPane.setLayout(null);
        //contentPane.setLayout(new GridLayout(8,8));
        final JLabel lblProductID= new JLabel("Product ID");
        lblProductID.setBounds(120,30,100,20);
        contentPane.add(lblProductID);

        final JTextField txtProductID = new JTextField();
        txtProductID.setBounds(220,30,80,20);
        contentPane.add(txtProductID);

        JButton checkbyIDButton = new JButton("Search Product by ID");
        checkbyIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int textPID = Integer.parseInt(txtProductID.getText());
                    frame.dispose();
                    CheckbyIDProductResult cp = new CheckbyIDProductResult(textPID);
                    cp.setUpPage();
                }
                catch (Exception error) {
                    JOptionPane.showMessageDialog(null, "please fill in productID as integer", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        checkbyIDButton.setBounds(140,55,180,40);
        contentPane.add(checkbyIDButton);

        final JLabel lblProductType= new JLabel("Product Type");
        contentPane.add(lblProductType);
        lblProductType.setBounds(120, 105, 100,20);

        final JTextField txtProductType = new JTextField();
        txtProductType.setBounds(220,105,80,20);
        contentPane.add(txtProductType);

        JButton checkbyTypeButton = new JButton("Search Product by Type");
        checkbyTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textType=txtProductType.getText().trim();
                frame.dispose();
                CheckbyTypeProductResult cp = new CheckbyTypeProductResult(textType);
                cp.setUpPage();
            }
        });
        checkbyTypeButton.setBounds(140,135,180,40);
        contentPane.add(checkbyTypeButton);

        final JLabel lblProductBrand= new JLabel("Product Brand");
        lblProductBrand.setBounds(120,185,100,20);
        contentPane.add(lblProductBrand);

        final JTextField txtProductBrand = new JTextField();
        txtProductBrand.setBounds(220,185,80,20);
        contentPane.add(txtProductBrand);

        JButton checkbyBrandButton = new JButton("Search Product by Brand");
        checkbyBrandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textBrand=txtProductBrand.getText().trim();
                frame.dispose();
                CheckbyBrandProductResult cp = new CheckbyBrandProductResult(textBrand);
                cp.setUpPage();
            }
        });
        checkbyBrandButton.setBounds(140,215,180,40);
        contentPane.add(checkbyBrandButton);

        JButton mostExpensive = new JButton("Most Expensive Product");
        mostExpensive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> result=product.mostExpensive();
                String txtproductID= result.get(0);
                String txtprice= result.get(1);
                String txtbrand= result.get(2).trim();
                String txtinv= result.get(3);
                String txttype= result.get(4);
                JOptionPane.showMessageDialog(null,"The most expensive product has a price as "+txtprice+ " with a product ID of "+txtproductID+" and an inventory of "+txtinv+". It is from "+txtbrand+", and is a type of "+txttype +".","Message",JOptionPane.PLAIN_MESSAGE);

            }
        });
        mostExpensive.setBounds(340,100,180,40);
        contentPane.add(mostExpensive);

        JButton leastExpensive = new JButton("Least Expensive Product");
        leastExpensive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> result=product.leastExpensive();
                String txtproductID= result.get(0);
                String txtprice= result.get(1);
                String txtbrand= result.get(2).trim();
                String txtinv= result.get(3);
                String txttype= result.get(4);
                JOptionPane.showMessageDialog(null,"The least expensive product has a price as "+txtprice+ " with a product ID of "+txtproductID+" and an inventory of "+txtinv+". It is from "+txtbrand+", and is a type of "+txttype+".","Message",JOptionPane.PLAIN_MESSAGE);
            }
        });
        leastExpensive.setBounds(340,150,180,40);
        contentPane.add(leastExpensive);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(480,240,110,40);
        contentPane.add(backButton);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerPage cp = new CustomerPage();
                cp.setUpPage();
            }
        });
        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
    }
}
