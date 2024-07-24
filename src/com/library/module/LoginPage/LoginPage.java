package com.library.module.LoginPage;

import com.library.*;
import com.library.service.AdminService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPage extends JFrame {

    private JLabel titleLabel, usernameLabel, passwordLabel, roleLabel, forgotPasswordLabel, resetPasswordLabel;
    private JTextField usernameTextField, resetUsernameTextField;
    private JPasswordField passwordTextField;
    private JButton loginButton, resetButton, cancelResetButton;
    private JComboBox<String> roleComboBox;
    private JCheckBox showPasswordCheckBox;
    private JPanel loginPanel, resetPanel;
    private boolean isResetPanelVisible = false;

    public static void main(String[] args) {
        new LoginPage();
    }

    public LoginPage() {
        setTitle("Login");
        setSize(400, 400);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Set the layout manager
        setLayout(new CardLayout());

        // Login Panel
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Title label
        titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(34, 45, 65));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(titleLabel, gbc);

        // Role label and combo box
        roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        roleLabel.setForeground(new Color(34, 45, 65));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(roleLabel, gbc);

        String[] roles = {"Admin", "Student"};
        roleComboBox = new JComboBox<>(roles);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(roleComboBox, gbc);

        // Username label and text field
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setForeground(new Color(34, 45, 65));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(usernameLabel, gbc);

        usernameTextField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(usernameTextField, gbc);

        // Password label and text field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(34, 45, 65));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(passwordLabel, gbc);

        passwordTextField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(passwordTextField, gbc);

        // Show password checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 12));
        showPasswordCheckBox.setForeground(new Color(34, 45, 65));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(showPasswordCheckBox, gbc);

        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordTextField.setEchoChar((char) 0);
            } else {
                passwordTextField.setEchoChar('•');
            }
        });

        // Login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        // Forgot password label
        forgotPasswordLabel = new JLabel("Forgot Password?");
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPasswordLabel.setForeground(new Color(0, 102, 204));
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(forgotPasswordLabel, gbc);

        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                togglePanels();
            }
        });

        loginButton.addActionListener(e -> {
            String username = usernameTextField.getText();
            char[] password = passwordTextField.getPassword();
            String passwordString = new String(password);
            String selectedRole = (String) roleComboBox.getSelectedItem();

            if (username.isEmpty() || passwordString.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username or Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (validateCredentials(username, passwordString, selectedRole)) {
                    if ("Admin".equals(selectedRole)) {
                        new AdminService(); // Open AdminService page
                    } else if ("Student".equals(selectedRole)) {
                        //new StudentDashboard(); // Open StudentDashboard page
                    }
                    dispose(); // Close the login page
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Reset password panel
        resetPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcReset = new GridBagConstraints();
        gbcReset.insets = new Insets(10, 10, 10, 10); // Padding

        resetPasswordLabel = new JLabel("Reset Password");
        resetPasswordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resetPasswordLabel.setForeground(new Color(34, 45, 65));
        gbcReset.gridx = 0;
        gbcReset.gridy = 0;
        gbcReset.gridwidth = 2;
        gbcReset.anchor = GridBagConstraints.CENTER;
        resetPanel.add(resetPasswordLabel, gbcReset);

        JLabel resetUsernameLabel = new JLabel("Enter your email or username:");
        resetUsernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resetUsernameLabel.setForeground(new Color(34, 45, 65));
        gbcReset.gridx = 0;
        gbcReset.gridy = 1;
        gbcReset.anchor = GridBagConstraints.LINE_END;
        resetPanel.add(resetUsernameLabel, gbcReset);

        resetUsernameTextField = new JTextField(15);
        gbcReset.gridx = 1;
        gbcReset.gridy = 1;
        gbcReset.anchor = GridBagConstraints.LINE_START;
        resetPanel.add(resetUsernameTextField, gbcReset);

        resetButton = new JButton("Reset Password");
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setBackground(new Color(100, 149, 237));
        resetButton.setForeground(Color.WHITE);
        gbcReset.gridx = 1;
        gbcReset.gridy = 2;
        gbcReset.anchor = GridBagConstraints.LINE_START;
        resetPanel.add(resetButton, gbcReset);

        cancelResetButton = new JButton("Cancel");
        cancelResetButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelResetButton.setBackground(new Color(108, 117, 125));
        cancelResetButton.setForeground(Color.WHITE);
        gbcReset.gridx = 0;
        gbcReset.gridy = 2;
        gbcReset.anchor = GridBagConstraints.LINE_END;
        resetPanel.add(cancelResetButton, gbcReset);

        resetButton.addActionListener(e -> handlePasswordReset());
        cancelResetButton.addActionListener(e -> togglePanels());

        add(loginPanel, "loginPanel");
        add(resetPanel, "resetPanel");

        // Initially show the login panel
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "loginPanel");

        setVisible(true);
    }

    private void togglePanels() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        if (isResetPanelVisible) {
            cl.show(getContentPane(), "loginPanel");
        } else {
            cl.show(getContentPane(), "resetPanel");
        }
        isResetPanelVisible = !isResetPanelVisible;
    }

    private void handlePasswordReset() {
        String resetUsername = resetUsernameTextField.getText();
        if (resetUsername.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your email or username", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Simulate password reset process
            JOptionPane.showMessageDialog(this, "Password reset link sent to " + resetUsername, "Success", JOptionPane.INFORMATION_MESSAGE);
            togglePanels(); // Go back to login page
        }
    }

    private boolean validateCredentials(String username, String password, String role) {
        // Admin credentials
        String[] validAdminUsernames = {"admin1", "admin2"};
        String[] validAdminPasswords = {"adminpass1", "adminpass2"};

        // Student credentials
        String[] validStudentUsernames = {"student1", "student2"};
        String[] validStudentPasswords = {"studentpass1", "studentpass2"};

        if ("Admin".equals(role)) {
            for (int i = 0; i < validAdminUsernames.length; i++) {
                if (username.equals(validAdminUsernames[i]) && password.equals(validAdminPasswords[i])) {
                    return true; // Admin credentials are valid
                }
            }
        } else if ("Student".equals(role)) {
            for (int i = 0; i < validStudentUsernames.length; i++) {
                if (username.equals(validStudentUsernames[i]) && password.equals(validStudentPasswords[i])) {
                    return true; // Student credentials are valid
                }
            }
        }
        return false; // Invalid credentials
    }
}
