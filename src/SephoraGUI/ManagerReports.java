package SephoraGUI;

import VIBClass.Employee;
import VIBClass.PurchaseHistory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by minnieliu on 2016-11-11.
 */
public class ManagerReports {
    private JFrame frame;
    private JPanel panel;

    Employee employee;

    public ManagerReports(){
        super();
        this.employee=new Employee();
        setUpPage();
    }

    public void setUpPage() {

        frame = new JFrame("Report Page");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        try {
            frame.setContentPane(panel = new JPanel() {
                BufferedImage image = ImageIO.read(new File("/Users/minnieliu/VIBRougeProject/src/resources/glitter.jpg"));

                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        panel.setBounds(100,100,450,300);
        frame.setContentPane(panel);
        panel.setLayout(null);

        JButton avgSales = new JButton("Average Item Purchased");
        avgSales.setBounds(100,90,180,60);
        panel.add(avgSales);

        avgSales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AverageItemPerCustomer table = new AverageItemPerCustomer();
            }
        });

        JButton avgPrice = new JButton("Average Price Per Type");
        avgPrice.setBounds(290,90,180,60);
        panel.add(avgPrice);
        avgPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AveragePricePerType table = new AveragePricePerType();
            }
        });

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                EmployeePage ep = new EmployeePage();
                ep.setUpPage();
            }
        });
        backButton.setBounds(480,240,110,40);
        panel.add(backButton);

        frame.setMinimumSize(new Dimension(600, 315));
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);


    }



}
