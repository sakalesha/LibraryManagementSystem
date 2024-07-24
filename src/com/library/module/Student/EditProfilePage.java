package com.library.module.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfilePage extends JDialog {

    private JTextField nameField, emailField, phoneField;
    private JButton saveButton, cancelButton;

    public EditProfilePage(Frame owner) {
        super(owner, "Edit Profile", true);

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
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        saveButton.setForeground(Color.WHITE);

        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(108, 117, 125)); // Grayish color
        cancelButton.setForeground(Color.WHITE);

        buttonPanel.add(saveButton);
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
        saveButton.addActionListener(new SaveButtonListener());
        cancelButton.addActionListener(e -> dispose());
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve the updated data
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            // Implement save logic here
            // For demonstration purposes, we'll show a confirmation dialog
            int result = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to save the changes?\n\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone, 
                "Confirm Save", JOptionPane.YES_NO_OPTION);
            
            if (result == JOptionPane.YES_OPTION) {
                // Save the updated profile information
                // Replace this with actual save logic (e.g., updating a database)
                JOptionPane.showMessageDialog(null, "Profile updated successfully.");
                dispose(); // Close the dialog
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditProfilePage(null));
    }
}
