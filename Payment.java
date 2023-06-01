package com.bus.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment extends JFrame {
    private JButton proceedButton;
    private JLabel statusLabel;
    private JButton nextButton;

    public Payment() {
        setTitle("Payment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200)); // Set the preferred size of the window

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add empty border for spacing

        proceedButton = new JButton("Proceed to Payment");
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("<html><div style='font-size: 16px; font-weight: bold;'>Payment Successful!</div></html>");
                nextButton.setVisible(true);
            }
        });

        statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the text
        statusLabel.setBorder(new EmptyBorder(10, 0, 10, 0)); // Add empty border for spacing

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountPage accountPage = new AccountPage();
                accountPage.setVisible(true);
                dispose(); // Close the Payment window
            }
        });
        nextButton.setVisible(false);

        contentPanel.add(proceedButton, BorderLayout.NORTH);
        contentPanel.add(statusLabel, BorderLayout.CENTER);
        contentPanel.add(nextButton, BorderLayout.SOUTH);

        add(contentPanel);

        pack();
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Payment();
            }
        });
    }
}
