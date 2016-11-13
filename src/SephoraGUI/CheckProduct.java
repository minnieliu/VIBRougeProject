package SephoraGUI;


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
                BufferedImage image = ImageIO.read(new File("./src/resources/glitter.jpg"));
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
        lblProductID.setBounds(120,30,110,20);
        contentPane.add(lblProductID);

        final JTextField txtProductID = new JTextField();
        txtProductID.setBounds(240,30,80,20);
        contentPane.add(txtProductID);

        JButton checkbyIDButton = new JButton("Search Product by ID");
        checkbyIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int textPID=Integer.parseInt(txtProductID.getText());
                frame.dispose();
                CheckbyIDProductResult cp = new CheckbyIDProductResult(textPID);
                cp.setUpPage();
            }
        });
        checkbyIDButton.setBounds(140,55,180,40);
        contentPane.add(checkbyIDButton);

        final JLabel lblProductType= new JLabel("Product Type");
        contentPane.add(lblProductType);
        lblProductType.setBounds(120, 105, 110,20);

        final JTextField txtProductType = new JTextField();
        txtProductType.setBounds(240,105,80,20);
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
        lblProductBrand.setBounds(120,185,110,20);
        contentPane.add(lblProductBrand);

        final JTextField txtProductBrand = new JTextField();
        txtProductBrand.setBounds(240,185,80,20);
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
        contentPane.add(leastExpensive);

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerPage cp = new CustomerPage();
                cp.setUpPage();
            }
        });
        contentPane.add(backButton);

        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
    }
}
