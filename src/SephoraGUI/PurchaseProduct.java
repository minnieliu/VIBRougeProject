package SephoraGUI;


import VIBClass.PurchaseHistory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hailey on 16/11/11.
 */
public class PurchaseProduct {
    private JFrame frame;
    private JPanel contentPane;
    private PurchaseHistory purchaseHistory;
    public PurchaseProduct() // main method
    {
        super();
        this.purchaseHistory= new PurchaseHistory();
    }

    public void setUpPage() //create constructor
    {
        frame = new JFrame("Purchase Product Page");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        contentPane = new JPanel();
        frame.add(contentPane);
        contentPane.setLayout(new GridLayout(8,8));

        final JLabel lblProductID= new JLabel("Product ID");
        contentPane.add(lblProductID);

        final JTextField txtProductID = new JTextField();
        contentPane.add(txtProductID);

        JLabel lblquantity = new JLabel("Quantity");
        contentPane.add(lblquantity);

        final JTextField txtQuantity = new JTextField();
        contentPane.add(txtQuantity);

        JLabel lblPhoneNum = new JLabel("Phone Number");
        contentPane.add(lblPhoneNum);

        final JTextField txtPhoneNumb = new JTextField();
        contentPane.add(txtPhoneNumb);

        JLabel lblName = new JLabel("Name");
        contentPane.add(lblName);

        final JTextField txtname = new JTextField();
        contentPane.add(txtname);

        JLabel lblmop = new JLabel("Method of Payment");
        contentPane.add(lblmop);

        final JTextField txtmop = new JTextField();
        contentPane.add(txtmop);

        JLabel lbldate = new JLabel("Date (YYYY-MM-DD)");
        contentPane.add(lbldate);

        final JTextField txtdate = new JTextField();
        contentPane.add(txtdate);

        JButton purchaseButton = new JButton("Purchase Now");
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int textPID=Integer.parseInt(txtProductID.getText());
                int textqt=Integer.parseInt(txtQuantity.getText());
                String textPhone = txtPhoneNumb.getText().trim();
                String textName = txtname.getText().trim();
                String textmop = txtmop.getText().trim();
                String textdate=txtdate.getText().trim();

                try{
                    int record= purchaseHistory.purchaseProduct(textPID,textqt,textPhone,textName,textmop,textdate);
                    JOptionPane.showMessageDialog(null,"You have succesfully purchased and your purchase history ID is " +record,"Message",JOptionPane.PLAIN_MESSAGE);
                }
                catch (Exception error){
                    JOptionPane.showMessageDialog(null,"You cannot finish this purchase, please ensure date is YYYY-MM-DD","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(purchaseButton);


        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerPage cp = new CustomerPage();
                cp.setUpPage();
            }
        });
        contentPane.add(backButton);

        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();

    }
}
