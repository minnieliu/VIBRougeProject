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
    private String name;
    private String phone;

    public MemberPurchaseHistory(int accountNo, String name, String phone) {
        super();
        this.accountNo=accountNo;
        this.name = name;
        this.phone = phone;
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
            tableContainer.setPreferredSize(new Dimension(1200,600));
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
                MemberPage mp = new MemberPage(accountNo,name,phone);
                mp.setUpPage();
            }
        });
        panel.add(backButton);

        //TODO:Make the table larger
        //frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
