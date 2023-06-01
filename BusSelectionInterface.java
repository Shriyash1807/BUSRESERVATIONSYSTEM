package com.bus.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BusSelectionInterface extends JFrame {
    private JLabel headingLabel;
    private JComboBox<String> startPlaceComboBox;
    private JComboBox<String> destinationPlaceComboBox;
    private JButton searchButton;
    private JPanel resultPanel;

    public BusSelectionInterface() {
        setTitle("Bus Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        headingLabel = new JLabel("Welcome to Maharashtra State Transport");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));

        startPlaceComboBox = new JComboBox<>();
        destinationPlaceComboBox = new JComboBox<>();

        searchButton = new JButton("Search");
        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel selectionPanel = new JPanel(new FlowLayout());
        selectionPanel.add(new JLabel("Start Place:"));
        selectionPanel.add(startPlaceComboBox);
        selectionPanel.add(new JLabel("Destination Place:"));
        selectionPanel.add(destinationPlaceComboBox);

        mainPanel.add(headingLabel, BorderLayout.NORTH);
        mainPanel.add(selectionPanel, BorderLayout.CENTER);
        mainPanel.add(searchButton, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(mainPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBuses();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        populateComboBoxes();
    }

    private void populateComboBoxes() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "password");
            Statement statement = connection.createStatement();

            ResultSet startPlaceResultSet = statement.executeQuery("SELECT DISTINCT start_place FROM buses");
            populateComboBox(startPlaceResultSet, startPlaceComboBox);

            ResultSet destinationPlaceResultSet = statement.executeQuery("SELECT DISTINCT destination_place FROM buses");
            populateComboBox(destinationPlaceResultSet, destinationPlaceComboBox);

            startPlaceResultSet.close();
            destinationPlaceResultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateComboBox(ResultSet resultSet, JComboBox<String> comboBox) throws SQLException {
        while (resultSet.next()) {
            String place = resultSet.getString(1);
            comboBox.addItem(place);
        }
        resultSet.close();
    }

    private void searchBuses() {
        resultPanel.removeAll();
        resultPanel.revalidate();
        resultPanel.repaint();

        String startPlace = (String) startPlaceComboBox.getSelectedItem();
        String destinationPlace = (String) destinationPlaceComboBox.getSelectedItem();

        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "password");
            Statement statement = connection.createStatement();

            String query = "SELECT bus_no, start_place, destination_place, cooling, dep_tim, dep_dat FROM buses " +
                    "WHERE start_place = '" + startPlace + "' AND destination_place = '" + destinationPlace + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String busNo = resultSet.getString("bus_no");
                String start = resultSet.getString("start_place");
                String destination = resultSet.getString("destination_place");
                String cooling = resultSet.getString("cooling");
                String departureTime = resultSet.getString("dep_tim");
                String departureDate = resultSet.getString("dep_dat");

                JPanel entryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                entryPanel.add(new JLabel("Bus No: " + busNo));
                entryPanel.add(new JLabel("Start: " + start));
                entryPanel.add(new JLabel("Destination: " + destination));
                entryPanel.add(new JLabel("Cooling: " + cooling));
                entryPanel.add(new JLabel("Departure Time: " + departureTime));
                entryPanel.add(new JLabel("Departure Date: " + departureDate));

                JButton bookButton = new JButton("Book");
                bookButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Handle book button action
                        Payment paymentWindow = new Payment();
                        paymentWindow.setVisible(true);
                    }
                });

                entryPanel.add(bookButton);

                resultPanel.add(entryPanel);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resultPanel.revalidate();
        resultPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BusSelectionInterface busSelectionInterface = new BusSelectionInterface();
                busSelectionInterface.setPreferredSize(new Dimension(600, 400));
            }
        });
    }
}
