package SephoraGUI;

import VIBClass.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
    private JTextField txtgender;
    private JButton btnSignup;
    private JButton btngoBack;
    private Customer customer;
    private JFrame frame;

    public CustomerSignUp() // main method
    {
        super();
        customer = new Customer();
    }

    public void setUpPage() //create constructor
    {
        frame = new JFrame("Member Sign Up Page");
        frame.setVisible(true);
        frame.setBounds(100, 100, 400, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        try {
            frame.setContentPane(contentPane=new JPanel(){
                BufferedImage image = ImageIO.read(new File("/Users/hailey/Desktop/CPSC304/VIBRougeProject/src/resources/glitter.jpg"));
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.add(contentPane);
        contentPane.setLayout(null);

        txtemail = new JTextField();
        txtemail.setBounds(188, 51, 200, 20);
        contentPane.add(txtemail);
        txtemail.setColumns(10);

        txtPassword = new JTextField();
        txtPassword.setBounds(188, 100, 200, 20);
        contentPane.add(txtPassword);
        txtPassword.setColumns(10);

        txtbday = new JTextField();
        txtbday.setBounds(188, 150, 200, 20);
        contentPane.add(txtbday);
        txtbday.setColumns(10);

        txtname = new JTextField();
        txtname.setBounds(188, 200, 200, 20);
        contentPane.add(txtname);
        txtname.setColumns(10);

        txtphone = new JTextField();
        txtphone.setBounds(188, 250, 200, 20);
        contentPane.add(txtphone);
        //txtphone.setColumns();

        txtgender = new JTextField();
        txtgender.setBounds(188, 300, 50, 20);
        contentPane.add(txtgender);
        txtgender.setColumns(10);

        JLabel lblEmail = new JLabel("Email");
     //   lblEmail.setForeground(Color.white);
        lblEmail.setBounds(70, 54, 86, 14);
        contentPane.add(lblEmail);

        JLabel lblPassword = new JLabel("Password");
      //  lblPassword.setForeground(Color.white);
        lblPassword.setBounds(70, 96, 86, 14);
        contentPane.add(lblPassword);

        JLabel lblbday = new JLabel("Birthday (Y-M-D)");
     //   lblbday.setForeground(Color.white);
        lblbday.setBounds(50,150, 120, 14);
        contentPane.add(lblbday);

        JLabel lblname = new JLabel("Name");
     //   lblname.setForeground(Color.white);
        lblname.setBounds(70, 195, 86, 14);
        contentPane.add(lblname);

        JLabel lblphone = new JLabel("Phone");
     //   lblphone.setForeground(Color.white);
        lblphone.setBounds(70, 245, 86, 14);
        contentPane.add(lblphone);

        JLabel lblgender = new JLabel("Gender");
        //   lblphone.setForeground(Color.white);
        lblgender.setBounds(70, 300, 86, 14);
        contentPane.add(lblgender);


        //   frame.setMinimumSize(new Dimension(600, 315));

        btnSignup = new JButton("Sign Up");
        //add event handler on SignUp button

        btnSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textemail = txtemail.getText().trim();
                String textPassword = txtPassword.getText().trim();
                String textbday = txtbday.getText().trim();
                String textname = txtname.getText().trim();
                String textphone = txtphone.getText().trim();
                String textgender = txtgender.getText().trim();
                System.out.println(textemail+" "+ textPassword+" "+textbday+" "+textname+" "+textphone+" "+textgender);
                try {
                    customer.addCustomer(textname, textphone, textgender);
                    customer.addMember(textemail,textPassword,textbday,textname,textphone);
                    JOptionPane.showMessageDialog(null,"Welcome Beauty Insider","Message",JOptionPane.PLAIN_MESSAGE);
                    // go back to the log in page
                    frame.dispose();
                    CustomerLogInPage clp= new CustomerLogInPage();
                    clp.setUpPage();
                }
                catch (Exception error){
                    JOptionPane.showMessageDialog(null,"Invalid Input: Please fill in all blank; the birthay format is YYYY-MM-DD","Error",JOptionPane.ERROR_MESSAGE);
                    System.out.println(error.getMessage());
                }
            }
        });

        btnSignup.setBounds(70, 320, 89, 23);
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
        btngoBack.setBounds(200, 320, 89, 23);
        frame.setLocationRelativeTo(null);

    }
}


