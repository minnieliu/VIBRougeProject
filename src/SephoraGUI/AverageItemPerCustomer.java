package SephoraGUI;


import VIBClass.PurchaseHistory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by minnieliu on 2016-11-11.
 */
public class AverageItemPerCustomer {
    private JFrame frame;
    private JPanel panel;

    public AverageItemPerCustomer() {
        super();
        setUpPage();
    }

    public void setUpPage() {

        panel = new JPanel();
        frame = new JFrame("Average Item Per Customer");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        PurchaseHistory ph = new PurchaseHistory();
        JTable averageItemPer  = ph.averageitemspercustomer();
        System.out.print("making table");
        JScrollPane tableContainer = new JScrollPane(averageItemPer);
        System.out.print("adding table");
        panel.add(tableContainer);
        frame.add(panel);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
