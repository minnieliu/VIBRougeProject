package SephoraGUI;

import VIBClass.Employee;
import VIBClass.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hailey on 16/11/11.
 */
public class ServiceThisMonth {
    private JFrame frame;
    private JPanel panel;
    private Service service;
    private int month;
    private int accountNo;
    private String name;
    private String phone;

    public ServiceThisMonth(int month,int accountNo,String name,String phone) {
        super();
        this.service=new Service();
        this.month=month;
        this.accountNo=accountNo;
        this.name=name;
        this.phone=phone;
    }

    public void setUpPage() {
        panel = new JPanel();
        frame = new JFrame("Service For this Month");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        Employee employee = new Employee();
        try {
            JTable result = service.checkServiceForThisMonth(month);
            System.out.print("making table");
            JScrollPane tableContainer = new JScrollPane(result);
            System.out.print("adding table");
            panel.add(tableContainer);
        }
        catch (Exception error){
            JOptionPane.showMessageDialog(null,"The month is invalid","Error",JOptionPane.ERROR_MESSAGE);
        }

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MemberPage mp = new MemberPage(accountNo,name,phone);
                mp.setUpPage();
            }
        });
        panel.add(backButton);

        frame.add(panel);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
    }
}
