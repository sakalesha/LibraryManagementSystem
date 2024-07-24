package com.library.module.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewProfilePage extends JDialog {

    private JTextField nameField, emailField, phoneField;
    private JButton editButton, cancelButton;
    private boolean isEditing = false;

    public ViewProfilePage(Frame owner) {
        super(owner, "View Profile", true);

        // Sample data for demonstration
        String name = "John Doe";
        String email = "johndoe@example.com";
        String phone = "123-456-7890";

        // Create and set up the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.WEST;

        // Labels and fields
        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel phoneLabel = new JLabel("Phone:");

        nameField = new JTextField(name, 20);
        emailField = new JTextField(email, 20);
        phoneField = new JTextField(phone, 20);

        nameField.setEditable(false);
        emailField.setEditable(false);
        phoneField.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        editButton = new JButton("Edit");
        cancelButton = new JButton("Cancel");

        editButton.setFont(new Font("Arial", Font.BOLD, 14));
        editButton.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        editButton.setForeground(Color.WHITE);

        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(108, 117, 125)); // Grayish color
        cancelButton.setForeground(Color.WHITE);

        buttonPanel.add(editButton);
        buttonPanel.add(cancelButton);

        // Add components to the dialog
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set dialog properties
        setSize(400, 250);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        // Add action listeners
        editButton.addActionListener(new EditButtonListener());
        cancelButton.addActionListener(e -> dispose());
    }

    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isEditing) {
                // Save changes (implement as needed)
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                
                // Implement save logic here
                JOptionPane.showMessageDialog(null, "Profile updated:\nName: " + name + "\nEmail: " + email + "\nPhone: " + phone);

                // After saving, switch back to view mode
                nameField.setEditable(false);
                emailField.setEditable(false);
                phoneField.setEditable(false);
                editButton.setText("Edit");
                isEditing = false;
            } else {
                // Switch to edit mode
                nameField.setEditable(true);
                emailField.setEditable(true);
                phoneField.setEditable(true);
                editButton.setText("Save");
                isEditing = true;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewProfilePage(null));
    }
}
