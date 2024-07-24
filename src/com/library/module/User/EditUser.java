package com.library.module.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditUser extends JFrame {

    private JTextField usernameField, roleField;
    private JButton saveButton, cancelButton;
    private DefaultTableModel tableModel;
    private int selectedRow;

    public EditUser(DefaultTableModel tableModel, int selectedRow, String userId, String username, String role) {
        this.tableModel = tableModel;
        this.selectedRow = selectedRow;

        setTitle("Edit User");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Set Look and Feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Panel for form content
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), "Edit User"));
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // User ID (not editable)
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("User ID:"), gbc);

        JTextField userIdField = new JTextField(userId);
        userIdField.setEditable(false);
        userIdField.setBackground(Color.LIGHT_GRAY);
        userIdField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        formPanel.add(userIdField, gbc);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(username);
        usernameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Role
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Role:"), gbc);

        roleField = new JTextField(role);
        roleField.setPreferredSize(new Dimension(200, 30));
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

        saveButton = new JButton("Save");
        styleButton(saveButton, new Color(0, 123, 255)); // Blue
        saveButton.addActionListener(this::saveUser);
        buttonPanel.add(saveButton);

        cancelButton = new JButton("Cancel");
        styleButton(cancelButton, new Color(220, 53, 69)); // Red
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        // Add formPanel to the main frame
        add(formPanel);

        setVisible(true);
    }

    private void saveUser(ActionEvent e) {
        String updatedUsername = usernameField.getText().trim();
        String updatedRole = roleField.getText().trim();

        if (updatedUsername.isEmpty() || updatedRole.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update row in tableModel
        tableModel.setValueAt(updatedUsername, selectedRow, 1);
        tableModel.setValueAt(updatedRole, selectedRow, 2);

        JOptionPane.showMessageDialog(this, "User updated successfully.");

        dispose();
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
        SwingUtilities.invokeLater(() -> {
            // You need to provide an actual DefaultTableModel and selected row data here
            DefaultTableModel model = new DefaultTableModel(new Object[]{"User ID", "Username", "Role"}, 0);
            // Sample data for demonstration purposes
            model.addRow(new Object[]{"1", "admin", "Administrator"});
            model.addRow(new Object[]{"2", "user1", "User"});
            int selectedRow = 0;
            String userId = (String) model.getValueAt(selectedRow, 0);
            String username = (String) model.getValueAt(selectedRow, 1);
            String role = (String) model.getValueAt(selectedRow, 2);

            new EditUser(model, selectedRow, userId, username, role);
        });
    }
}
