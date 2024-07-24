package com.library.service;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.library.module.User.User.AddUser;
import com.library.module.User.User.EditUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageUsers extends JFrame {

    private JTable usersTable;
    private DefaultTableModel tableModel;
    private JButton addUserButton, editUserButton, deleteUserButton, backButton;

    public ManageUsers() {
        setTitle("Manage Users");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20)); // Add padding to the layout

        // Set look and feel to Nimbus for a modern UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Table setup
        String[] columnNames = {"User ID", "Username", "Role"};
        Object[][] data = {
                {"1", "admin", "Administrator"},
                {"2", "user1", "User"},
                {"3", "user2", "User"}
        };

        tableModel = new DefaultTableModel(data, columnNames);
        usersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        addUserButton = new JButton("Add User");
        styleButton(addUserButton);
        addUserButton.addActionListener(e -> new AddUser(tableModel));
        buttonPanel.add(addUserButton);

        editUserButton = new JButton("Edit User");
        styleButton(editUserButton);
        editUserButton.addActionListener(e -> editSelectedUser());
        buttonPanel.add(editUserButton);

        deleteUserButton = new JButton("Delete User");
        styleButton(deleteUserButton);
        deleteUserButton.addActionListener(e -> deleteSelectedUser());
        buttonPanel.add(deleteUserButton);

        backButton = new JButton("Back to Dashboard");
        styleButton(backButton);
        backButton.addActionListener(e -> {
            new AdminService();
            dispose();
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void editSelectedUser() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow != -1) {
            String userId = (String) tableModel.getValueAt(selectedRow, 0);
            String username = (String) tableModel.getValueAt(selectedRow, 1);
            String role = (String) tableModel.getValueAt(selectedRow, 2);

            new EditUser(tableModel, selectedRow, userId, username, role);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to edit.", "Edit User", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteSelectedUser() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this user?",
                    "Delete User",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "User deleted successfully.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.", "Delete User", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ManageUsers::new);
    }
}
