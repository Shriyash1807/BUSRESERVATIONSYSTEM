package com.bus.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationPage extends JFrame {
    private JTextField nameTextField;
    private JTextField usernameTextField;
    private JTextField contactTextField;
    private JPasswordField passwordField;

    public RegistrationPage() {
        setTitle("Registration Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("User Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleLabel, constraints);

        JLabel nameLabel = new JLabel("Name:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(nameLabel, constraints);

        nameTextField = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.ipady = 10; 
        panel.add(nameTextField, constraints);

        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.ipady = 0; 
        panel.add(usernameLabel, constraints);

        usernameTextField = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.ipady = 10; 
        panel.add(usernameTextField, constraints);

        JLabel contactLabel = new JLabel("Contact No:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.ipady = 0; 
        panel.add(contactLabel, constraints);

        contactTextField = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.ipady = 10; 
        panel.add(contactTextField, constraints);

        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.ipady = 0; 
        panel.add(passwordLabel, constraints);

        passwordField = new JPasswordField();
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.ipady = 10; 
        panel.add(passwordField, constraints);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(registerButton, constraints);

        JLabel loginLabel = new JLabel("<html><u>Already have an account?</u></html>");
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LoginWindow().setVisible(true);
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        panel.add(loginLabel, constraints);

        add(panel);
    }

    private void registerUser() {
        String name = nameTextField.getText();
        String username = usernameTextField.getText();
        String contact = contactTextField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "password");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO userdetails (name, username, contact_no, password) VALUES (?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, username);
            statement.setString(3, contact);
            statement.setString(4, password);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registration successful!");

            statement.close();
            connection.close();

            dispose();
            new LoginWindow().setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Registration failed: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegistrationPage().setVisible(true);
            }
        });
    }
}
