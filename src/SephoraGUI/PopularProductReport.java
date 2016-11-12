package SephoraGUI;

import VIBClass.Employee;
import VIBClass.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hailey on 16/11/11.
 */
public class PopularProductReport {
    private JFrame frame;
    private JPanel panel;
    private Product product;

    public PopularProductReport() {
        super();
        this.product=new Product();
    }

    public void setUpPage() {
        panel = new JPanel();
        frame = new JFrame("Popular Product");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        JTable popularProduct = product.popularProduct();
        System.out.print("making table");
        JScrollPane tableContainer = new JScrollPane(popularProduct);
        System.out.print("adding table");
        panel.add(tableContainer);

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerPage cp = new CustomerPage();
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
