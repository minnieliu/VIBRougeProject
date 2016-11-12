package SephoraGUI;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hailey on 16/11/11.
 */
public class UpdateAccountInfo {
    private JFrame frame;
    private JPanel contentPane;

    public UpdateAccountInfo() // main method
    {
        super();
    }

    public void setUpPage() //create constructor
    {
        frame = new JFrame("Update Account Information Page");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.add(contentPane);
        contentPane.setLayout(new GridLayout(8,8));

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
