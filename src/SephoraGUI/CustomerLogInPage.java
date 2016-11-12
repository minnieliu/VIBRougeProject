package SephoraGUI;//import statement
import VIBClass.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by hailey on 16/11/11.
 */

public class CustomerLogInPage extends JFrame //create class NewUser
{
    private JPanel contentPane; //declare variable
    private JTextField txtName;
    private JButton btnSignup;
    private JButton btnLogIn;
    private JButton btnNoAccount;
    private JTextField txtPhone;
    private Customer customer;
    private JFrame frame;

    public CustomerLogInPage() // main method
    {
        super();
        customer = new Customer();
    }

    public void setUpPage() //create constructor
    {


        try {
            frame.add(contentPane = new JPanel(){
                BufferedImage image = ImageIO.read(new File("/Users/hailey/Desktop/CPSC304/VIBRougeProject/src/resources/glitter.jpg"));
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.customer=new Customer();
        frame = new JFrame("Customer Log In Page");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 450, 300);
       // contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        txtName = new JTextField();
        txtName.setBounds(188, 51, 99, 20);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtPhone = new JTextField();
        txtPhone.setBounds(188, 106, 99, 20);
        contentPane.add(txtPhone);
        txtPhone.setColumns(10);

        JLabel lblUserName = new JLabel("Name");
        lblUserName.setBounds(70, 54, 86, 14);
        contentPane.add(lblUserName);

        JLabel lblPassword = new JLabel("Phone Number");
        lblPassword.setBounds(70, 109, 100, 14);
        contentPane.add(lblPassword);

        btnSignup = new JButton("Sign Up");
        //add event handler on SignUp button

        btnSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerSignUp csp= new CustomerSignUp();
                csp.setUpPage();
            }
        });

		btnSignup.setBounds(70, 165, 89, 23);
		contentPane.add(btnSignup);

        btnLogIn = new JButton("Log In");
        //TODO: add event handler on Log In button
        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textName = txtName.getText().trim();
                String textPhone = txtPhone.getText().trim();
                System.out.println(textName+" "+ textPhone);
                try{
                    boolean membership= customer.isMember(textName,textPhone);
                    if(membership){
                        int accountNo= customer.getAccountNo(textName,textPhone);
                        JOptionPane.showMessageDialog(null,"Welcome Back!","Message",JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                        MemberPage mp= new MemberPage(accountNo,textName,textPhone);
                        mp.setUpPage();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"You are not a member yet, sign up first!","Error",JOptionPane.ERROR_MESSAGE);
                    }

                }
                catch (Exception error){
                    JOptionPane.showMessageDialog(null,"You are not a member yet, sign up first!","Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        contentPane.add(btnLogIn);
        btnLogIn.setBounds(151, 165, 89, 23);

        btnNoAccount= new JButton("Process without Account");

        //add event handler on No Account button
        btnNoAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerPage cp= new CustomerPage();
                cp.setUpPage();
            }
        });
        frame.setMinimumSize(new Dimension(600, 315));
        contentPane.add(btnNoAccount);
        btnNoAccount.setBounds(230, 165, 180, 23);

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainPage mp = new MainPage();
                mp.setUpPage();
            }
        });
        backButton.setBounds(480,240,110,40);
        contentPane.add(backButton);
        frame.setLocationRelativeTo(null);

    }
}