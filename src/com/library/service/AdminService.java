package com.library.service;

import com.library.module.*;
import com.library.module.Admin.AddBook;
import com.library.module.Admin.DeleteBook;
import com.library.module.Admin.EditBook;
import com.library.module.Admin.GenerateReports;
import com.library.module.Admin.SearchBook;
import com.library.module.Admin.ViewBooksPage;
import com.library.module.Book.Book;
import com.library.module.LoginPage.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminService extends JFrame {
    private JButton addBookButton, editBookButton, deleteBookButton, viewAllBooksButton, searchBookButton, generateReportsButton, manageUsersButton, logoutButton;
    private BookService bookService; // Service for managing books

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminService());
    }

    public AdminService() {
        setTitle("Admin Service");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Initialize BookService using getInstance()
        bookService = BookService.getInstance();

        // Set look and feel to Nimbus for a modern UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // Title label
        JLabel titleLabel = new JLabel("Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(60, 63, 65));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Button panel with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Buttons
        addBookButton = new JButton("Add Book");
        editBookButton = new JButton("Edit Book");
        deleteBookButton = new JButton("Delete Book");
        viewAllBooksButton = new JButton("View All Books");
        searchBookButton = new JButton("Search Book");
        generateReportsButton = new JButton("Generate Reports");
        manageUsersButton = new JButton("Manage Users");
        logoutButton = new JButton("Logout");

        // Set button properties
        JButton[] buttons = {addBookButton, editBookButton, deleteBookButton, viewAllBooksButton, searchBookButton, generateReportsButton, manageUsersButton, logoutButton};
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setBackground(new Color(70, 130, 180));
            button.setForeground(Color.WHITE);
            button.setPreferredSize(new Dimension(200, 50));
        }

        // Add buttons to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(addBookButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(editBookButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(deleteBookButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(viewAllBooksButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(searchBookButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        buttonPanel.add(generateReportsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        buttonPanel.add(manageUsersButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        buttonPanel.add(logoutButton, gbc);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);

        // Action Listeners
        addBookButton.addActionListener(e -> new AddBook(this, bookService));
        editBookButton.addActionListener(e -> {
            // You should select a book to edit; this is just an example
            Book selectedBook = bookService.getAllBooks().stream().findFirst().orElse(null);
            if (selectedBook != null) {
                new EditBook(this, selectedBook, bookService);
            } else {
                JOptionPane.showMessageDialog(this, "No book selected for editing.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        deleteBookButton.addActionListener(e -> new DeleteBook(this, bookService));
        viewAllBooksButton.addActionListener(e -> new ViewBooksPage(this, bookService));
        searchBookButton.addActionListener(e -> new SearchBook());
        generateReportsButton.addActionListener(e -> new GenerateReports());
        manageUsersButton.addActionListener(e -> new ManageUsers());
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginPage();
        });

        setVisible(true);
    }
}
