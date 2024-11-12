package hotelmangementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField username;
    JPasswordField password; 
    JButton login, cancel;

    Login() {
        getContentPane().setBackground(Color.WHITE);

        setLayout(null);
        
        // Username label and text field
        JLabel user = new JLabel("Username");
        user.setBounds(40, 20, 100, 30);
        add(user);

        username = new JTextField();
        username.setBounds(150, 20, 150, 30);
        add(username);

        // Password label and text field
        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 70, 100, 30);
        add(pass);

        password = new JPasswordField(); // Changed to JPasswordField
        password.setBounds(150, 70, 150, 30);
        add(password);

        // Login button
        login = new JButton("Login");
        login.setBounds(40, 150, 120, 30);
        login.setBackground(Color.black);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.setBounds(180, 150, 120, 30);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        // Image icon (for the UI)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        add(image);

        // Set frame properties
        setBounds(500, 200, 600, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String user = username.getText();
            String pass = new String(password.getPassword()); // Retrieve password properly

            try {
                Conn c = new Conn();

                // Query to check the username and password from the database
                String query = "SELECT * FROM login WHERE username = '" + user + "' AND password = '" + pass + "'";

                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    setVisible(false); // Close the login window
                    new Dashboard();  // Open the dashboard window
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false); // Close the login window
        }
    }

    public static void main(String[] args) {
        new Login();  // Create and show the login window
    }
}
