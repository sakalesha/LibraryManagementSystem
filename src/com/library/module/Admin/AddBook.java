package com.library.module.Admin;

import com.library.module.Book.Book;
import com.library.service.BookService;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBook extends JFrame {
    private JTextField bookIDField, titleField, authorField, publisherField, yearField;
    private JButton addButton, cancelButton;
    private BookService bookService; // Service for managing books

    public AddBook(JFrame parent, BookService bookService) {
        setTitle("Add Book");
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));

        // Set look and feel to Nimbus for a modern UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize BookService
        this.bookService = bookService;

        // Main panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Book Details", 
            TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16), new Color(70, 130, 180)));
        inputPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Create and add components
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Book ID:"), gbc);
        bookIDField = new JTextField(25);
        gbc.gridx = 1;
        inputPanel.add(bookIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Title:"), gbc);
        titleField = new JTextField(25);
        gbc.gridx = 1;
        inputPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Author:"), gbc);
        authorField = new JTextField(25);
        gbc.gridx = 1;
        inputPanel.add(authorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Publisher:"), gbc);
        publisherField = new JTextField(25);
        gbc.gridx = 1;
        inputPanel.add(publisherField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Year:"), gbc);
        yearField = new JTextField(25);
        gbc.gridx = 1;
        inputPanel.add(yearField, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        addButton = new JButton("Add Book");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setPreferredSize(new Dimension(150, 40));
        addButton.addActionListener(new AddButtonListener());

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(255, 69, 58));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(150, 40));
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        // Add panels to frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String bookID = bookIDField.getText().trim();
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String publisher = publisherField.getText().trim();
            String year = yearField.getText().trim();

            // Basic validation
            if (bookID.isEmpty() || title.isEmpty() || author.isEmpty() || publisher.isEmpty() || year.isEmpty()) {
                JOptionPane.showMessageDialog(AddBook.this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Integer.parseInt(year); // Validate year is a number
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(AddBook.this, "Year must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Book newBook = new Book(bookID, title, author, publisher, year);
            bookService.addBook(newBook);

            JOptionPane.showMessageDialog(AddBook.this, "Book added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }
}
