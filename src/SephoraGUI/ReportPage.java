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
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        try {
            frame.setContentPane(panel=new JPanel(){
                BufferedImage image = ImageIO.read(new File("/Users/minnieliu/VIBRougeProject/src/resources/glitter.jpg"));
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image,0,0,image.getWidth(),image.getHeight(),this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        panel.setLayout(new GridLayout(8,8));

        JButton lowStock = new JButton("Low Stock Report");
        JButton highPrice = new JButton("High Price Report");
        JButton lowPrice = new JButton("Low Price Report");
        JButton birthday = new JButton("Birthday Gift Report");
        final JTextField textField = new JTextField(5);

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
        panel.add(textField);
        panel.add(lowStock);
        panel.add(lowPrice);
        panel.add(highPrice);
        panel.add(birthday);
        JButton backButton = new JButton("Go Back");


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


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
