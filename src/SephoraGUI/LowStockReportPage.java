package SephoraGUI;

/**
 * Created by minnieliu on 2016-11-10.
 */


import VIBClass.Employee;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.*;

public class LowStockReportPage {

    private JFrame frame;
    private JPanel panel;

    public LowStockReportPage(int lowStockNumber) {
        super();
        setUpPage(lowStockNumber);
    }

    public void setUpPage(int lowStockNumber) {
        System.out.print("got to  reportpage");
        panel = new JPanel();
        frame = new JFrame("Low Stock Report Page");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);

        Employee employee = new Employee();
        JTable lowStockreport = employee.lowStockReport(lowStockNumber);
        System.out.print("making table");
        JScrollPane tableContainer = new JScrollPane(lowStockreport);
        System.out.print("adding table");
        panel.add(tableContainer);
        frame.add(panel);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

}
