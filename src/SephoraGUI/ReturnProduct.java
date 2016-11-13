package SephoraGUI;


import VIBClass.PurchaseHistory;

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
public class ReturnProduct {
    private JFrame frame;
    private JPanel contentPane;
    private PurchaseHistory purchaseHistory;

    public ReturnProduct() // main method
    {
        super();
        this.purchaseHistory=new PurchaseHistory();
    }

    public void setUpPage() //create constructor
    {
        frame = new JFrame("Return Product Page");
        try {
            frame.setContentPane(contentPane=new JPanel(){
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.add(contentPane);
        contentPane.setLayout(new GridLayout(8,8));

        final JLabel lblProductID= new JLabel("Product ID");
        contentPane.add(lblProductID);

        final JTextField txtProductID = new JTextField();
        contentPane.add(txtProductID);

        final JLabel lblPurchaseID= new JLabel("Purchase ID");
        contentPane.add(lblPurchaseID);

        final JTextField txtPurchaseID= new JTextField();
        contentPane.add(txtPurchaseID);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int textProductID = Integer.parseInt(txtProductID.getText());
                int textpurchaseID = Integer.parseInt(txtPurchaseID.getText());
                try {
                    boolean result= purchaseHistory.returnProduct(textProductID,textpurchaseID);
                    if(result)
                        JOptionPane.showMessageDialog(null,"You have succesfully returned your purchase with ID " +textpurchaseID +" for product ID " +textProductID,"Message",JOptionPane.PLAIN_MESSAGE);
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, "You cannot finish this return", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(returnButton);

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerPage cp = new CustomerPage();
                cp.setUpPage();
            }
        });
        contentPane.add(backButton);

        frame.setMinimumSize(new Dimension(600, 315));
        frame.pack();
    }
}
