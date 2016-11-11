package SephoraGUI;

/**
 * Created by minnieliu on 2016-11-10.
 */


import VIBClass.Employee;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.*;

public class ReportPage {

    private JFrame frame;
    private JPanel panel;

    public ReportPage() {
        super();
        // setUpPage();
    }

    public void setUpPage() {
        panel = new JPanel();
        frame = new JFrame("Report Page");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        Employee employee = new Employee();
        JTable lowStockreport = employee.lowStockReport(20);
        JScrollPane tableContainer = new JScrollPane(lowStockreport);
        System.out.print("adding table");
        panel.add(tableContainer);
        frame.add(panel);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();


    }

}
