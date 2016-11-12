package SephoraGUI;


import VIBClass.SephoraMember;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hailey on 16/11/11.
 */
public class BookService {
    private JFrame frame;
    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtPhone;
    private JTextField txtServiceID;
    private SephoraMember sephoraMember;
    private String name;
    private String phone;

    public BookService(String name, String phone) // main method
    {
        super();
        this.sephoraMember=new SephoraMember();
        this.name=name;
        this.phone=phone;
    }

    public void setUpPage() //create constructor
    {
        frame = new JFrame("Book Service Page");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.add(contentPane);
        contentPane.setLayout(new GridLayout(8,8));

//        JLabel lblUserName = new JLabel("Name");
//        contentPane.add(lblUserName);
//
//        txtName = new JTextField();
//        contentPane.add(txtName);
//
//        JLabel lblPassword = new JLabel("Phone Number");
//        contentPane.add(lblPassword);
//
//         txtPhone = new JTextField();
//        contentPane.add(txtPhone);

        JLabel lblServiceID = new JLabel("Service ID");
        contentPane.add(lblServiceID);

         txtServiceID = new JTextField();
        contentPane.add(txtServiceID);


        JButton bookButton = new JButton("Book");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int textServiceID=Integer.parseInt(txtServiceID.getText());
//                String textName = txtName.getText().trim();
//                String textPhone = txtPhone.getText().trim();
//                System.out.println(textName+" "+ textPhone+" "+ textServiceID);
                try{
                    boolean book= sephoraMember.bookService(textServiceID,name,phone);
                    if(book)
                        JOptionPane.showMessageDialog(null,"The service is booked successfully!","Message",JOptionPane.PLAIN_MESSAGE);
                    else{
                        JOptionPane.showMessageDialog(null,"You cannot book this service","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (Exception error){
                    JOptionPane.showMessageDialog(null,"You cannot book this service","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(bookButton);

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainPage mp = new MainPage();
                mp.setUpPage();
            }
        });
        contentPane.add(backButton);

        
        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
    }
}
