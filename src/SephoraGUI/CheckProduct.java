package SephoraGUI;


import VIBClass.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JButton CheckbyIDButton = new JButton("Search Product by ID");
        CheckbyIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int textPID=Integer.parseInt(txtProductID.getText());
                frame.dispose();
                CheckbyIDProductResult cp = new CheckbyIDProductResult(textPID);
                cp.setUpPage();
            }
        });
        contentPane.add(CheckbyIDButton);

        final JLabel lblProductType= new JLabel("Product Type");
        contentPane.add(lblProductType);

        final JTextField txtProductType = new JTextField();
        contentPane.add(txtProductType);

        JButton CheckbyTypeButton = new JButton("Search Product by Type");
        CheckbyTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textType=txtProductType.getText().trim();
                frame.dispose();
                CheckbyTypeProductResult cp = new CheckbyTypeProductResult(textType);
                cp.setUpPage();
            }
        });
        contentPane.add(CheckbyTypeButton);

        final JLabel lblProductBrand= new JLabel("Product Brand");
        contentPane.add(lblProductBrand);

        final JTextField txtProductBrand = new JTextField();
        contentPane.add(txtProductBrand);

        JButton CheckbyBrandButton = new JButton("Search Product by Brand");
        CheckbyBrandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textBrand=txtProductBrand.getText().trim();
                frame.dispose();
                CheckbyBrandProductResult cp = new CheckbyBrandProductResult(textBrand);
                cp.setUpPage();
            }
        });
        contentPane.add(CheckbyBrandButton);

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
