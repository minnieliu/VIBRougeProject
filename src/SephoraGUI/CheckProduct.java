package SephoraGUI;


import VIBClass.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        contentPane = new JPanel();
        frame.setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(8,8));

        final JLabel lblProductID= new JLabel("Product ID");
        contentPane.add(lblProductID);

        final JTextField txtProductID = new JTextField();
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
        contentPane.add(checkbyIDButton);

        final JLabel lblProductType= new JLabel("Product Type");
        contentPane.add(lblProductType);

        final JTextField txtProductType = new JTextField();
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
        contentPane.add(checkbyTypeButton);

        final JLabel lblProductBrand= new JLabel("Product Brand");
        contentPane.add(lblProductBrand);

        final JTextField txtProductBrand = new JTextField();
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
