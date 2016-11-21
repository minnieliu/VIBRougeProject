package SephoraGUI;


import VIBClass.SephoraMember;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

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
    private int accountNo;

    public BookService(String name, String phone, int accountNo) // main method
    {
        super();
        this.sephoraMember=new SephoraMember();
        this.name=name;
        this.phone=phone;
        this.accountNo = accountNo;
    }

    public void setUpPage() //create constructor
    {
        frame = new JFrame("Book Service Page");

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
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

       // contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        JLabel lblServiceID = new JLabel("Service ID: ");
        lblServiceID.setBounds(215,30,80,20);
        contentPane.add(lblServiceID);
        txtServiceID = new JTextField();
        txtServiceID.setBounds(300,30,80,20);
        contentPane.add(txtServiceID);


        JButton bookButton = new JButton("Book");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                String textName = txtName.getText().trim();
//                String textPhone = txtPhone.getText().trim();
//                System.out.println(textName+" "+ textPhone+" "+ textServiceID);
                try{
                    int textServiceID=Integer.parseInt(txtServiceID.getText());
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
        bookButton.setBounds(235, 60, 130,40);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(480,240,110,40);
        contentPane.add(backButton);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MemberPage mp = new MemberPage(accountNo,name,phone);
                mp.setUpPage();
            }
        });
        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

    }
}
