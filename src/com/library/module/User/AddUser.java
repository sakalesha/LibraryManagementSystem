package com.library.module.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddUser extends JFrame {

    private JTextField userIdField, usernameField, roleField;
    private JButton addButton, cancelButton;
    private DefaultTableModel tableModel;

    public AddUser(DefaultTableModel tableModel) {
        this.tableModel = tableModel;

        setTitle("Add User");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Set Look and Feel to Nimbus for a modern UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Panel for form content
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), "Add New User"));
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // User ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("User ID:"), gbc);

        userIdField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(userIdField, gbc);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Role
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Role:"), gbc);

        roleField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(roleField, gbc);

        // Buttons
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        formPanel.add(buttonPanel, gbc);

        addButton = new JButton("Add");
        styleButton(addButton, new Color(0, 123, 255)); // Blue
        addButton.addActionListener(this::addUser);
        buttonPanel.add(addButton);

        cancelButton = new JButton("Cancel");
        styleButton(cancelButton, new Color(220, 53, 69)); // Red
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        // Add formPanel to the main frame
        add(formPanel);

        // Set visibility
        setVisible(true);
    }

    private void addUser(ActionEvent e) {
        String userId = userIdField.getText().trim();
        String username = usernameField.getText().trim();
        String role = roleField.getText().trim();

        if (userId.isEmpty() || username.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if User ID already exists
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String existingUserId = (String) tableModel.getValueAt(i, 0);
            if (existingUserId.equals(userId)) {
                JOptionPane.showMessageDialog(this, "User ID already exists.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Add new row to tableModel
        tableModel.addRow(new Object[]{userId, username, role});

        JOptionPane.showMessageDialog(this, "User added successfully.");

        // Clear fields after adding
        userIdField.setText("");
        usernameField.setText("");
        roleField.setText("");
    }

    private void styleButton(JButton button, Color backgroundColor) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddUser(new DefaultTableModel(new Object[]{"User ID", "Username", "Role"}, 0)));
    }
}
