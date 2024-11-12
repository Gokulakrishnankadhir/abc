package hotelmangementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HotelMangementSystem extends JFrame implements ActionListener {

    HotelMangementSystem() {
        // Set the frame properties
        setBounds(100, 100, 1366, 565);
        setLayout(null);

        // Set the background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1369, 565);
        add(image);

        // Centered text
        JLabel text = new JLabel("HOTEL MANAGEMENT SYSTEM", SwingConstants.CENTER);
        text.setBounds(183, 430, 1000, 90);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("serif", Font.PLAIN, 50));
        image.add(text);

        // Centered Next button
        JButton next = new JButton("Next");
        next.setBounds(608, 450, 150, 50); // Centered horizontally
        next.setBackground(Color.WHITE);
        next.setForeground(Color.MAGENTA);
        next.addActionListener(this);
        next.setFont(new Font("serif", Font.PLAIN, 18));
        image.add(next);

        // Show frame
        setVisible(true);

        // Blinking text effect using Timer instead of infinite loop
        Timer timer = new Timer(500, new ActionListener() {
            private boolean isVisible = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                text.setVisible(isVisible);
                isVisible = !isVisible;
            }
        });
        timer.start(); // Start the blinking effect
    }

    // Correct method name to match ActionListener interface
    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false); // Hide the current window
        new Login();       // Open the Login window
    }

    public static void main(String[] args) {
        new HotelMangementSystem();
    }
}
