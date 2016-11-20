package SephoraGUI;

import VIBClass.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
      //  frame.setBounds(100, 100, 400, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        try {
            frame.setContentPane(contentPane=new JPanel(){
                BufferedImage image = ImageIO.read(new File("./src/resources/customerglitter.jpg"));
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        //contentPane = new JPanel();
        //contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //frame.add(contentPane);
        contentPane.setLayout(null);

        txtemail = new JTextField();
        txtemail.setBounds(188, 25, 200, 20);
        contentPane.add(txtemail);
        txtemail.setColumns(10);

        txtPassword = new JTextField();
        txtPassword.setBounds(188, 60, 200, 20);
        contentPane.add(txtPassword);
        txtPassword.setColumns(10);

        txtbday = new JTextField();
        txtbday.setBounds(188, 90, 200, 20);
        contentPane.add(txtbday);
        txtbday.setColumns(10);

        txtname = new JTextField();
        txtname.setBounds(188, 125, 200, 20);
        contentPane.add(txtname);
        txtname.setColumns(10);

        txtphone = new JTextField();
        txtphone.setBounds(188, 160, 200, 20);
        contentPane.add(txtphone);
        //txtphone.setColumns();

        txtgender = new JTextField();
        txtgender.setBounds(188, 195, 50, 20);
        contentPane.add(txtgender);
        txtgender.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
     //   lblEmail.setForeground(Color.white);
        lblEmail.setBounds(70, 29, 86, 14);
        contentPane.add(lblEmail);

        JLabel lblPassword = new JLabel("Password:");
      //  lblPassword.setForeground(Color.white);
        lblPassword.setBounds(70, 64, 86, 14);
        contentPane.add(lblPassword);

        JLabel lblbday = new JLabel("Birthday (Y-M-D):");
     //   lblbday.setForeground(Color.white);
        lblbday.setBounds(70,94, 120, 14);
        contentPane.add(lblbday);

        JLabel lblname = new JLabel("Name:");
     //   lblname.setForeground(Color.white);
        lblname.setBounds(70, 129, 86, 14);
        contentPane.add(lblname);

        JLabel lblphone = new JLabel("Phone:");
     //   lblphone.setForeground(Color.white);
        lblphone.setBounds(70, 164, 86, 14);
        contentPane.add(lblphone);

        JLabel lblgender = new JLabel("Gender (M/F):");
        //   lblphone.setForeground(Color.white);
        lblgender.setBounds(70, 199, 86, 14);
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
                Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                Matcher m = p.matcher(textemail);
                boolean matchFound = m.matches();

                if (!matchFound){
                    JOptionPane.showMessageDialog(null,"Invalid Input: Incorrect format for email","Error",JOptionPane.ERROR_MESSAGE);
                }
                Pattern p1 = Pattern.compile("^\\d{10}$");
                Matcher m1 = p1.matcher(textphone);
                boolean phoneMatch = m1.matches();
                if (!phoneMatch){
                    JOptionPane.showMessageDialog(null,"Invalid Input: Incorrect phone number","Error",JOptionPane.ERROR_MESSAGE);
                }

                if(txtemail.getText()==null || txtPassword.getText()==null || txtbday.getText()==null || txtgender.getText()==null || txtphone.getText()==null){
                    JOptionPane.showMessageDialog(null,"Invalid Input: Please fill in all blank","Error",JOptionPane.ERROR_MESSAGE);
                }

               if (matchFound && phoneMatch && txtemail.getText()!=null && txtPassword.getText()!= null && txtbday.getText()!=null && txtgender.getText()!=null && txtphone.getText()!= null){
                    try {
                        customer.addCustomer(textname, textphone, textgender);
                        customer.addMember(textemail, textPassword, textbday, textname, textphone);
                        JOptionPane.showMessageDialog(null, "Welcome Beauty Insider", "Success", JOptionPane.PLAIN_MESSAGE);
                        // go back to the log in page
                        frame.dispose();
                        CustomerLogInPage clp = new CustomerLogInPage();
                        clp.setUpPage();
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(null, "Invalid Input: Please fill in all blank; the birthay format is YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println(error.getMessage());
                    }
                }
            }
        });

        btnSignup.setBounds(70, 240, 110, 40);
        contentPane.add(btnSignup);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(480,240,110,40);
        contentPane.add(backButton);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerLogInPage c = new CustomerLogInPage();
                c.setUpPage();
            }
        });
        contentPane.add(backButton);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

    }
}


