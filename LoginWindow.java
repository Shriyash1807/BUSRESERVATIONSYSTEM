package com.bus.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public LoginWindow() {
        initializeUI();
        connectToDatabase();
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void initializeUI() {
        setTitle("Login Window");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
            String username = "system"; 
            String password = "password"; 

            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            String query = "SELECT * FROM userdetails WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");

               
                resultSet.close();
                preparedStatement.close();
                connection.close();

               
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new BusSelectionInterface();
                    }
                });

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please register first!");

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new RegistrationPage();
                    }
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow();
            }
        });
    }
}
