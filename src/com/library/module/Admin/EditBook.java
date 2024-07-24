package com.library.module.Admin;

import com.library.module.Book.Book;
import com.library.service.BookService;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBook extends JFrame {

    private JTextField bookIDField, titleField, authorField, publisherField, yearField;
    private JButton saveButton, cancelButton;
    private String originalBookID; // Store the original book ID for updating
    private BookService bookService; // Service for managing books

    // Constructor to edit an existing book
    public EditBook(JFrame parent, Book book, BookService bookService) {
        setTitle("Edit Book");
        setSize(500, 400);
        setLocationRelativeTo(parent); // Center the window relative to the parent
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(20, 20)); // Use BorderLayout with padding

        // Set look and feel to Nimbus for a modern UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize BookService
        this.bookService = bookService;

        // Store the original book ID for later update
        originalBookID = book.getBookID();

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

        // Create and add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Book ID:"), gbc);
        bookIDField = new JTextField(25);
        bookIDField.setText(book.getBookID());
        bookIDField.setEditable(false);
        gbc.gridx = 1;
        inputPanel.add(bookIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Title:"), gbc);
        titleField = new JTextField(25);
        titleField.setText(book.getTitle());
        gbc.gridx = 1;
        inputPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Author:"), gbc);
        authorField = new JTextField(25);
        authorField.setText(book.getAuthor());
        gbc.gridx = 1;
        inputPanel.add(authorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Publisher:"), gbc);
        publisherField = new JTextField(25);
        publisherField.setText(book.getPublisher());
        gbc.gridx = 1;
        inputPanel.add(publisherField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Year:"), gbc);
        yearField = new JTextField(25);
        yearField.setText(book.getPublisherYear());
        gbc.gridx = 1;
        inputPanel.add(yearField, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        saveButton = new JButton("Save Changes");
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(70, 130, 180));
        saveButton.setForeground(Color.WHITE);
        saveButton.setPreferredSize(new Dimension(150, 40));
        saveButton.addActionListener(new SaveButtonListener());

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(255, 69, 58));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(150, 40));
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        // Add panels to frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true); // Show the frame
    }

    // ActionListener for Save button
    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newID = bookIDField.getText();
            String newTitle = titleField.getText();
            String newAuthor = authorField.getText();
            String newPublisher = publisherField.getText();
            String newYear = yearField.getText();

            // Validate inputs
            if (newID.isEmpty() || newTitle.isEmpty() || newAuthor.isEmpty() ||
                newPublisher.isEmpty() || newYear.isEmpty()) {
                JOptionPane.showMessageDialog(EditBook.this, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update book information
            Book updatedBook = new Book(newID, newTitle, newAuthor, newPublisher, newYear);
            if (!originalBookID.equals(newID)) {
                bookService.getAllBooks().removeIf(book -> book.getBookID().equals(originalBookID));
            }
            bookService.addBook(updatedBook);

            JOptionPane.showMessageDialog(EditBook.this, "Book details updated successfully.");
            dispose(); // Close the frame
        }
    }
}
