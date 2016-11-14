package SephoraGUI;

import VIBClass.Customer;
import VIBClass.Employee;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by hailey on 16/11/13.
 */
public class EmployeeLogInPage extends JFrame {
    private JPanel contentPane; //declare variable
    private JTextField txtName;
    private JButton btnLogIn;
    private JTextField txtEmployeeID;
    private Employee employee;
    private JFrame frame;

    public EmployeeLogInPage() // main method
    {
        super();
        employee = new Employee();
    }

    public void setUpPage() //create constructor
    {
        frame = new JFrame("Employee Log In Page");

        try {
            frame.setContentPane(contentPane = new JPanel(){

                BufferedImage image = ImageIO.read(new File("./src/resources/glitter.jpg"));
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setBounds(100, 100, 450, 300);
        // contentPane = new JPanel();
        // contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //  frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        txtEmployeeID = new JTextField();
        txtEmployeeID.setBounds(188, 51, 99, 20);
        contentPane.add(txtEmployeeID);
        // txtName.setColumns(10);


        JLabel lblUserName = new JLabel("Employee ID");
        lblUserName.setBounds(70, 54, 86, 14);
        contentPane.add(lblUserName);


        btnLogIn = new JButton("Log In");
        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtEmployeeID.getText()==null){
                    JOptionPane.showMessageDialog(null,"Please fill in your employee ID","Error",JOptionPane.ERROR_MESSAGE);
                }
                try{
                    int textEmployeeID = Integer.parseInt(txtEmployeeID.getText().trim());
                    boolean isemployee=employee.isEmployee(textEmployeeID);
                    if(isemployee){
                        String name= employee.getName(textEmployeeID);
                        JOptionPane.showMessageDialog(null,"Welcome Back "+ name,"Message",JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                        EmployeePage ep= new EmployeePage();
                        ep.setUpPage();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"You are not a employee yet, please contact your manager!","Error",JOptionPane.ERROR_MESSAGE);
                    }

                }
                catch (Exception error){
                    JOptionPane.showMessageDialog(null,"You are not a employee yet, please contact your manager!","Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        contentPane.add(btnLogIn);
        btnLogIn.setBounds(151, 165, 89, 23);


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
        frame.setMinimumSize(new Dimension(600, 315));
        frame.setLocationRelativeTo(null);

    }
}
