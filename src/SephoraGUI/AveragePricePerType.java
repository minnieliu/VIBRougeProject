package SephoraGUI;

import VIBClass.Product;


import javax.swing.*;
import java.awt.*;

/**
 * Created by minnieliu on 2016-11-11.
 */
public class AveragePricePerType {

    private JFrame frame;
    private JPanel panel;

    public AveragePricePerType() {
        super();
        setUpPage();
    }

    public void setUpPage() {

        panel = new JPanel();
        frame = new JFrame("Average Price Per Type");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        Product p = new Product();
        JTable avgpricepertype  = p.avgpricepertype();
        System.out.print("making table");
        JScrollPane tableContainer = new JScrollPane(avgpricepertype);
        System.out.print("adding table");
        panel.add(tableContainer);
        frame.add(panel);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
