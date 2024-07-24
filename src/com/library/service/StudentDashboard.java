package com.library.service;

import javax.swing.*;

import com.library.module.LoginPage.LoginPage;
import com.library.module.Student.ViewBooksPage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDashboard extends JFrame {

    private JLabel titleLabel;
    private JButton viewBooksButton, borrowBooksButton, returnBooksButton, viewProfileButton, logoutButton;
    private JPanel mainPanel, footerPanel;

    public StudentDashboard() {
        setTitle("Student Dashboard");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Set the layout manager
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Title label
        titleLabel = new JLabel("Student Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(34, 45, 65));
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        // Main Panel
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Buttons for student actions
        viewBooksButton = new JButton("View Books");
        borrowBooksButton = new JButton("Borrow Books");
        returnBooksButton = new JButton("Return Books");
        viewProfileButton = new JButton("View Profile");

        // Set button properties to match the design
        JButton[] buttons = {viewBooksButton, borrowBooksButton, returnBooksButton, viewProfileButton};
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBackground(new Color(100, 149, 237));
            button.setForeground(Color.WHITE);
            button.setPreferredSize(new Dimension(150, 50));
        }

        // Add buttons to the main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(viewBooksButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(borrowBooksButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(returnBooksButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(viewProfileButton, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Footer Panel
        footerPanel = new JPanel();
        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(108, 117, 125));
        logoutButton.setForeground(Color.WHITE);
        footerPanel.add(logoutButton);

        add(footerPanel, BorderLayout.SOUTH);

        // Add action listeners
        viewBooksButton.addActionListener(new ButtonClickListener());
        borrowBooksButton.addActionListener(new ButtonClickListener());
        returnBooksButton.addActionListener(new ButtonClickListener());
        viewProfileButton.addActionListener(new ButtonClickListener());
        logoutButton.addActionListener(e -> {
            // Close this frame and return to login page
            dispose();
            new LoginPage(); // Assuming you want to return to the login page
        });

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            switch (source.getText()) {
                case "View Books":
                    new ViewBooksPage(StudentDashboard.this);
                    break;
                case "Borrow Books":
                    JOptionPane.showMessageDialog(null, "Borrow Books feature clicked.");
                    // Implement borrow books functionality
                    break;
                case "Return Books":
                    JOptionPane.showMessageDialog(null, "Return Books feature clicked.");
                    // Implement return books functionality
                    break;
                case "View Profile":
                    JOptionPane.showMessageDialog(null, "View Profile feature clicked.");
                    // Implement view profile functionality
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentDashboard());
    }
}
