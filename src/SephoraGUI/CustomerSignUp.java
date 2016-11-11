package SephoraGUI;

import VIBClass.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 * Created by hailey on 16/11/11.
 */
public class CustomerSignUp extends JFrame //create class NewUser
{
    private JPanel contentPane; //declare variable
    private JTextField txtemail;
    private JTextField txtPassword;
    private JTextField txtbday;
    private JTextField txtname;
    private JTextField txtphone;
    private JButton btnSignup;
    private JButton btngoBack;
    private Customer customer;
    private JFrame frame;

    public CustomerSignUp() // main method
    {
        super();
    }

    public void setUpPage() //create constructor
    {
        frame = new JFrame("Member Sign Up Page");
        frame.setVisible(true);
        frame.setBounds(100, 100, 400, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
//        try {
//            frame.setContentPane(contentPane=new JPanel(){
//                BufferedImage image = ImageIO.read(new File("/Users/hailey/Desktop/CPSC304/VIBRougeProject/src/resources/sephora.jpg"));
//                public void paintComponent(Graphics g) {
//                    super.paintComponent(g);
//                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        txtemail = new JTextField();
        txtemail.setBounds(188, 51, 99, 20);
        contentPane.add(txtemail);
        txtemail.setColumns(10);

        txtPassword = new JTextField();
        txtPassword.setBounds(188, 100, 99, 20);
        contentPane.add(txtPassword);
        txtPassword.setColumns(10);

        txtbday = new JTextField();
        txtbday.setBounds(188, 150, 99, 20);
        contentPane.add(txtbday);
        txtbday.setColumns(10);

        txtname = new JTextField();
        txtname.setBounds(188, 200, 99, 20);
        contentPane.add(txtname);
        txtname.setColumns(10);

        txtphone = new JTextField();
        txtphone.setBounds(188, 250, 99, 20);
        contentPane.add(txtphone);
        txtphone.setColumns(10);


        JLabel lblEmail = new JLabel("Email");
     //   lblEmail.setForeground(Color.white);
        lblEmail.setBounds(70, 54, 86, 14);
        contentPane.add(lblEmail);

        JLabel lblPassword = new JLabel("Password");
      //  lblPassword.setForeground(Color.white);
        lblPassword.setBounds(70, 96, 86, 14);
        contentPane.add(lblPassword);

        JLabel lblbday = new JLabel("Birthday");
     //   lblbday.setForeground(Color.white);
        lblbday.setBounds(70,144, 86, 14);
        contentPane.add(lblbday);

        JLabel lblname = new JLabel("Name");
     //   lblname.setForeground(Color.white);
        lblname.setBounds(70, 195, 86, 14);
        contentPane.add(lblname);

        JLabel lblphone = new JLabel("Phone");
     //   lblphone.setForeground(Color.white);
        lblphone.setBounds(70, 245, 86, 14);
        contentPane.add(lblphone);

     //   frame.setMinimumSize(new Dimension(600, 315));

        btnSignup = new JButton("Sign Up");
        //add event handler on SignUp button

        btnSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnSignup.setBounds(70, 300, 89, 23);
        contentPane.add(btnSignup);

        btngoBack= new JButton("Go Back");

        //add event handler on No Account button
        btngoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerLogInPage clp= new CustomerLogInPage();
                clp.setUpPage();
            }
        });

        contentPane.add(btngoBack);
        btngoBack.setBounds(200, 300, 89, 23);


    }
}


