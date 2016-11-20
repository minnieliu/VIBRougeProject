package SephoraGUI;


import VIBClass.SephoraMember;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hailey on 16/11/11.
 */
public class UpdateAccountInfo {
    private JFrame frame;
    private JPanel contentPane;
    private int accountNo;
    private String name;
    private String phone;
    private SephoraMember sephoraMember;

    public UpdateAccountInfo(int accountNo,String name,String phone) // main method
    {
        super();
        this.accountNo=accountNo;
        this.name=name;
        this.phone=phone;
        this.sephoraMember=new SephoraMember();
    }

    public void setUpPage() //create constructor
    {
        frame = new JFrame("Update Account Information Page");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.add(contentPane);
        contentPane.setLayout(new GridLayout(8,8));

        final JLabel account= new JLabel("Account No."+this.accountNo);
        contentPane.add(account);

        JLabel lblEmail = new JLabel("New Email");
        contentPane.add(lblEmail);

        final JTextField txtEmail = new JTextField();
        contentPane.add(txtEmail);

        JLabel lblPassword = new JLabel("New Password");
        contentPane.add(lblPassword);

        final JTextField txtPassword = new JTextField();
        contentPane.add(txtPassword);

        JButton updateButton = new JButton("Update Email and Address");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textEmail = txtEmail.getText().trim();
                String textPassword = txtPassword.getText().trim();

                Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                Matcher m = p.matcher(textEmail);
                boolean matchFound = m.matches();
                try{
                    if (matchFound){
                        sephoraMember.updateAccountInfo(accountNo,textEmail,textPassword);
                        JOptionPane.showMessageDialog(null,"The information is successfully updated!","Message",JOptionPane.PLAIN_MESSAGE);

                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Incorrect email format!","Error",JOptionPane.ERROR_MESSAGE);
                    }

                }
                catch (Exception error){
                    JOptionPane.showMessageDialog(null,"You cannot update this information","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(updateButton);

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MemberPage mp = new MemberPage(accountNo,name,phone);
                mp.setUpPage();
            }
        });
        contentPane.add(backButton);

        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
    }
}
