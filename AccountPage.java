package com.bus.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPage extends JFrame {
    public AccountPage() {
        // Set up the JFrame
        setTitle("Account Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the heading label with padding
        JLabel headingLabel = new JLabel("Thanks for booking.");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        mainPanel.add(headingLabel, BorderLayout.NORTH);

        // Create the buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        // Create the logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Logged Out");
                dispose();  // Close the current window
                RegistrationPage registrationPage = new RegistrationPage();
                registrationPage.setVisible(true);
            }
        });
        buttonPanel.add(logoutButton);

        // Create the search buses button
        JButton searchBusesButton = new JButton("Search Buses");
        searchBusesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close the current window
                BusSelectionInterface busSelectionInterface = new BusSelectionInterface();
                busSelectionInterface.setVisible(true);
            }
        });
        buttonPanel.add(searchBusesButton);

        // Add the buttons panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the main panel to the JFrame
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AccountPage accountPage = new AccountPage();
                accountPage.setVisible(true);
            }
        });
    }
}
