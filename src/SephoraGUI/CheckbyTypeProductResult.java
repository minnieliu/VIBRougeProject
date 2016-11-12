package SephoraGUI;

import VIBClass.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hailey on 16/11/11.
 */
public class CheckbyTypeProductResult {
    private JFrame frame;
    private JPanel panel;
    private Product product;
    private String type;

    public CheckbyTypeProductResult(String type) {
        super();
        this.product=new Product();
        this.type=type;
    }

    public void setUpPage() {
        panel = new JPanel();
        frame = new JFrame("Product Searched by ID Result");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        JTable Product = null;
        try {
            Product = product.checkProductbyType(type);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Search Error","Error",JOptionPane.ERROR_MESSAGE);
        }
        System.out.print("making table");
        JScrollPane tableContainer = new JScrollPane(Product);
        System.out.print("adding table");
        panel.add(tableContainer);

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CheckProduct cp = new CheckProduct();
                cp.setUpPage();
            }
        });
        panel.add(backButton);


        frame.add(panel);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
    }
}
