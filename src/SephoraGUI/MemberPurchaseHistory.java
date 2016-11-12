package SephoraGUI;

import VIBClass.Employee;
import VIBClass.PurchaseHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hailey on 16/11/11.
 */
public class MemberPurchaseHistory {
    private JFrame frame;
    private JPanel panel;
    private int accountNo;
    private PurchaseHistory purchaseHistory;

    public MemberPurchaseHistory(int accountNo) {
        super();
        this.accountNo=accountNo;
        this.purchaseHistory=new PurchaseHistory();
    }

    public void setUpPage() {
        panel = new JPanel();
        frame = new JFrame("Member Purchase History Page");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.add(panel);

        Employee employee = new Employee();
        try {
            JTable memberHistory = purchaseHistory.checkMemberPurchaseHistory(accountNo);
            System.out.print("making table");
            JScrollPane tableContainer = new JScrollPane(memberHistory);
            System.out.print("adding table");
            panel.add(tableContainer);
        }
        catch (Exception error){
            JOptionPane.showMessageDialog(null,"AccountNo is Invalid","Error",JOptionPane.ERROR_MESSAGE);
        }

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

        //TODO:Make the table larger
        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
