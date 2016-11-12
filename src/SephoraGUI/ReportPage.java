package SephoraGUI;

import VIBClass.Employee;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import static oracle.net.aso.C05.e;

/**
 * Created by minnieliu on 2016-11-11.
 */
public class ReportPage extends JPanel implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    Employee employee;

    public ReportPage(){
        super();
        this.employee=new Employee();

           setUpPage();
    }

    public void setUpPage(){

        frame = new JFrame("Report Page");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        try {
            frame.setContentPane(panel=new JPanel(){
                BufferedImage image = ImageIO.read(new File("/Users/hailey/Desktop/CPSC304/VIBRougeProject/src/resources/sephora.jpg"));
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        panel.setBounds(100,100,450,300);
        frame.setContentPane(panel);
        panel.setLayout(null);
      //  panel.setLayout(new GridLayout(8,8));

        JButton lowStock = new JButton("Low Stock");
        lowStock.setBounds(50, 51, 110, 20);
        panel.add(lowStock);
        JButton highPrice = new JButton("High Price");
        highPrice.setBounds(170, 51, 110, 20);
        panel.add(highPrice);
        JButton lowPrice = new JButton("Low Price");
        lowPrice.setBounds(290, 51, 110, 20);
        panel.add(lowPrice);
        JButton birthday = new JButton("Birthday Gift");
        birthday.setBounds(410, 51, 110, 20);
        panel.add(birthday);
        final JLabel search = new JLabel("Enter Search: ");
        search.setBounds(50,20,110,20);
        panel.add(search);
        final JTextField textField = new JTextField(5);
        textField.setBounds(140, 20 , 380 ,20);
        panel.add(textField);

        lowStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.print("calling save" + textField.getText());
                    int lowStockNumber = Integer.parseInt(textField.getText());
                    LowStockReportPage rp = new LowStockReportPage(lowStockNumber);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        highPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.print("calling save" + textField.getText());
                    int highestPrice = Integer.parseInt(textField.getText());
                    HighPriceReport hp = new HighPriceReport(highestPrice);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        lowPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.print("calling save" + textField.getText());
                    int lowestprice = Integer.parseInt(textField.getText());
                    LowPriceReport hp = new LowPriceReport(lowestprice);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        birthday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.print("calling save" + textField.getText());
                    int birthdayMonth = Integer.parseInt(textField.getText());
                    BirthdayGiftReport hp = new BirthdayGiftReport(birthdayMonth);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(480,240,110,40);
        panel.add(backButton);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                EmployeePage employee = new EmployeePage();
                employee.setUpPage();
            }
        });
        panel.add(backButton);
        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
