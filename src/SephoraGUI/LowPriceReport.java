package SephoraGUI;

import VIBClass.Employee;

import javax.swing.*;
import java.awt.*;

/**
 * Created by minnieliu on 2016-11-11.
 */
public class LowPriceReport {

    private JFrame frame;
    private JPanel panel;

    public LowPriceReport(int lowprice) {
        super();
        setUpPage(lowprice);
    }

    public void setUpPage(int lowprice) {
        System.out.print("got to  reportpage");
        panel = new JPanel();
        frame = new JFrame("Low Price Report Page");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);

        Employee employee = new Employee();
        JTable lowPriceReport = employee.lowerPriceReport(lowprice);
        System.out.print("making table");
        JScrollPane tableContainer = new JScrollPane(lowPriceReport);
        System.out.print("adding table");
        panel.add(tableContainer);
        frame.add(panel);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
