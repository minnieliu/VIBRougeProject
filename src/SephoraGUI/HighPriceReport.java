package SephoraGUI;

import VIBClass.Employee;

import javax.swing.*;
import java.awt.*;

/**
 * Created by minnieliu on 2016-11-11.
 */
public class HighPriceReport {

    private JFrame frame;
    private JPanel panel;

    public HighPriceReport(int highestprice) {
        super();
        setUpPage(highestprice);
    }

    public void setUpPage(int highestprice) {
        System.out.print("got to  reportpage");
        panel = new JPanel();
        frame = new JFrame("High Price Report Page");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);

        Employee employee = new Employee();
        JTable highPriceReport = employee.higherPriceReport(highestprice);
        System.out.print("making table");
        JScrollPane tableContainer = new JScrollPane(highPriceReport);
        System.out.print("adding table");
        panel.add(tableContainer);
        frame.add(panel);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

}
