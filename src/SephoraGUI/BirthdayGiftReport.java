package SephoraGUI;

import VIBClass.Employee;

import javax.swing.*;
import java.awt.*;

/**
 * Created by minnieliu on 2016-11-11.
 */
public class BirthdayGiftReport {

    private JFrame frame;
    private JPanel panel;

    public BirthdayGiftReport(int birthdayMonth) {
        super();
        setUpPage(birthdayMonth);
    }

    public void setUpPage(int birthdayMonth) {
        System.out.print("got to  reportpage");
        panel = new JPanel();
        frame = new JFrame("Birthday Gift Report Page");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);

        Employee employee = new Employee();
        try {
            JTable birthdayGifts = employee.birthdayGift(birthdayMonth);
            JScrollPane tableContainer = new JScrollPane(birthdayGifts);
            panel.add(tableContainer);
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Fill in a valid month 1-12", "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame.add(panel);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
