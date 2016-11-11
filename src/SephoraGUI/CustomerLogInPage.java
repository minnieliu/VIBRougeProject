package SephoraGUI;//import statement
import VIBClass.Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Created by hailey on 16/11/11.
 */

public class CustomerLogInPage extends JFrame //create class NewUser
{
    private JPanel contentPane; //declare variable
    private JTextField txtUser;
    private JButton btnSignup;
    private JButton btnLogIn;
    private JButton btnNoAccount;
    private JTextField txtPassword;
    private Customer customer;
    private JFrame frame;

    public CustomerLogInPage() // main method
    {
        super();
    }

    public void setUpPage() //create constructor
    {
        this.customer=new Customer();
        frame = new JFrame("Customer Log In Page");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        txtUser = new JTextField();
        txtUser.setBounds(188, 51, 99, 20);
        contentPane.add(txtUser);
        txtUser.setColumns(10);

        txtPassword = new JTextField();
        txtPassword.setBounds(188, 106, 99, 20);
        contentPane.add(txtPassword);
        txtPassword.setColumns(10);

        JLabel lblUserName = new JLabel("Email");
        lblUserName.setBounds(70, 54, 86, 14);
        contentPane.add(lblUserName);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(70, 109, 86, 14);
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

        contentPane.add(btnNoAccount);
        btnNoAccount.setBounds(230, 165, 180, 23);

    }
}