package hotelmangementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddEmployee extends JFrame implements ActionListener {

    JTextField tfname, tfemail, tfphone, tfage, tfaadhar, tfdate, tfrooms;
    JRadioButton rbmale, rbfemale;
    JButton submit;
    ButtonGroup bg;

    AddEmployee() {
        setLayout(null);

        // NAME label and text field
        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 30, 120, 30);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 30, 150, 30);
        add(tfname);

        // AGE label and text field
        JLabel lblage = new JLabel("AGE");
        lblage.setBounds(60, 80, 120, 30);
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblage);

        tfage = new JTextField();
        tfage.setBounds(200, 80, 150, 30);
        add(tfage);

        // GENDER label and radio buttons
        JLabel lblgender = new JLabel("GENDER");
        lblgender.setBounds(60, 130, 120, 30);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblgender);

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(200, 130, 70, 30);
        rbmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbmale.setBackground(Color.WHITE);
        add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(280, 130, 70, 30);
        rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);

        bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);

        // PHONE label and text field
        JLabel lblphone = new JLabel("PHONE");
        lblphone.setBounds(60, 180, 120, 30);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(200, 180, 150, 30);
        add(tfphone);

        // EMAIL label and text field
        JLabel lblemail = new JLabel("EMAIL");
        lblemail.setBounds(60, 230, 120, 30);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 230, 150, 30);
        add(tfemail);

        // AADHAR label and text field
        JLabel lblaadhar = new JLabel("AADHAR");
        lblaadhar.setBounds(60, 280, 120, 30);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(200, 280, 150, 30);
        add(tfaadhar);

        // DATE label and text field
        JLabel lbldate = new JLabel("DATE");
        lbldate.setBounds(60, 330, 120, 30);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbldate);

        tfdate = new JTextField();
        tfdate.setBounds(200, 330, 150, 30);
        add(tfdate);

        // NUMBER OF ROOMS label and text field
        JLabel lblrooms = new JLabel("NUMBER OF ROOMS");
        lblrooms.setBounds(60, 380, 120, 30);
        lblrooms.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblrooms);

        tfrooms = new JTextField();
        tfrooms.setBounds(200, 380, 150, 30);
        add(tfrooms);

        // SUBMIT button
        submit = new JButton("SUBMIT");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(200, 430, 150, 30);
        submit.addActionListener(this);
        add(submit);

        // Image for the UI
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380, 60, 450, 370);
        add(image);

        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 200, 850, 540);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Get input values from the fields
        String name = tfname.getText();
        String age = tfage.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String aadhar = tfaadhar.getText();
        String date = tfdate.getText();
        String rooms = tfrooms.getText();

        // Get the gender
        String gender = null;
        if (rbmale.isSelected()) {
            gender = "Male";
        } else if (rbfemale.isSelected()) {
            gender = "Female";
        }

        // Validate if all fields are filled
        if (name.isEmpty() || age.isEmpty() || phone.isEmpty() || email.isEmpty() || aadhar.isEmpty() || date.isEmpty() || rooms.isEmpty() || gender == null) {
            JOptionPane.showMessageDialog(null, "All fields must be filled!");
        } else {
            try {
                // Establish the connection using Conn class
                Conn conn = new Conn();

                // Using PreparedStatement to avoid SQL injection
                String query = "INSERT INTO employee (name, age, gender, phone, email, aadhar, date, rooms) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.c.prepareStatement(query);

                // Set the values in the PreparedStatement
                pst.setString(1, name);
                pst.setString(2, age);
                pst.setString(3, gender);
                pst.setString(4, phone);
                pst.setString(5, email);
                pst.setString(6, aadhar);
                pst.setString(7, date);
                pst.setString(8, rooms);

                // Execute the update
                int result = pst.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Employee added successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error adding employee.");
                }

                // Clear form fields after submission
                tfname.setText("");
                tfage.setText("");
                tfphone.setText("");
                tfemail.setText("");
                tfaadhar.setText("");
                tfdate.setText("");
                tfrooms.setText("");
                bg.clearSelection();

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new AddEmployee(); // Create and show the Add Employee window
    }
}