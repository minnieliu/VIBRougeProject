package SephoraGUI;


import VIBClass.Customer;
import VIBClass.PurchaseHistory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

//        try {
//            frame.setContentPane(contentPane=new JPanel(){
//                BufferedImage image = ImageIO.read(new File("./src/resources/glitter.jpg"));
//                public void paintComponent(Graphics g) {
//                    super.paintComponent(g);
//                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JLabel lblmop = new JLabel("Method of Payment (debit/credit)");
        contentPane.add(lblmop);

        final JTextField txtmop = new JTextField();
        contentPane.add(txtmop);

        JLabel lbldate = new JLabel("Date (YYYY-MM-DD)");
        contentPane.add(lbldate);

        final JTextField txtdate = new JTextField();
        contentPane.add(txtdate);

        JLabel lblGender = new JLabel("Gender (M/F)");
        contentPane.add(lblGender);

        final JTextField txtGender = new JTextField();
        contentPane.add(txtGender);


        JButton purchaseButton = new JButton("Purchase Now");
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int textPID = Integer.parseInt(txtProductID.getText());
                int textqt = Integer.parseInt(txtQuantity.getText());
                String textPhone = txtPhoneNumb.getText().trim();
                String textName = txtname.getText().trim();
                String textmop = txtmop.getText().trim();
                String textdate = txtdate.getText().trim();
                String txtgender = txtGender.getText().trim();


                Pattern p = Pattern.compile("^\\d{10}$");
                Matcher m = p.matcher(textPhone);
                boolean matchFound = m.matches();
                System.out.print(matchFound + textmop);

                if ((textmop.equals("credit") || textmop.equals("debit")) && matchFound){
                    try {
                        Customer c = new Customer();
                        if (!c.isCustomer(textName, textPhone)) {
                            c.addCustomer(textName, textPhone, txtgender);
                        }

                        try {
                            int record = purchaseHistory.purchaseProduct(textPID, textqt, textPhone, textName, txtgender, textmop, textdate);
                            JOptionPane.showMessageDialog(null, "You have succesfully purchased and your purchase history ID is " + record, "Message", JOptionPane.PLAIN_MESSAGE);
                        } catch (SQLException error) {
                            JOptionPane.showMessageDialog(null, "Not enough inventory to complete purchase!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(null, "You cannot finish this purchase, please ensure date is YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid purchase method or phone number!", "Error", JOptionPane.ERROR_MESSAGE);
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
        frame.setLocationRelativeTo(null);

    }
}
