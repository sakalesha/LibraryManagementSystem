package com.library.module.Admin;

import com.library.module.Book.Book;
import com.library.service.BookService;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBook extends JFrame {
    private JTextField bookIDField;
    private JLabel titleLabel, authorLabel, publisherLabel, yearLabel;
    private JButton fetchButton, deleteButton, cancelButton;
    private BookService bookService; // Service for managing books
    private Book bookToDelete; // Store the book to be deleted

    public DeleteBook(JFrame parent, BookService bookService) {
        setTitle("Delete Book");
        setSize(500, 350);
        setLocationRelativeTo(parent);
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

        // Main panel for input field and book details
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Delete Book",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16), new Color(255, 69, 58)));
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Create and add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Book ID:"), gbc);
        bookIDField = new JTextField(25);
        gbc.gridx = 1;
        mainPanel.add(bookIDField, gbc);

        fetchButton = new JButton("Fetch Details");
        fetchButton.setFont(new Font("Arial", Font.BOLD, 14));
        fetchButton.setBackground(new Color(70, 130, 180));
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setPreferredSize(new Dimension(150, 40));
        fetchButton.addActionListener(new FetchButtonListener());
        gbc.gridx = 2;
        gbc.gridy = 0;
        mainPanel.add(fetchButton, gbc);

        titleLabel = new JLabel("Title: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(titleLabel, gbc);

        authorLabel = new JLabel("Author: ");
        gbc.gridy = 2;
        mainPanel.add(authorLabel, gbc);

        publisherLabel = new JLabel("Publisher: ");
        gbc.gridy = 3;
        mainPanel.add(publisherLabel, gbc);

        yearLabel = new JLabel("Year: ");
        gbc.gridy = 4;
        mainPanel.add(yearLabel, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        deleteButton = new JButton("Delete Book");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setBackground(new Color(255, 69, 58));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setPreferredSize(new Dimension(150, 40));
        deleteButton.addActionListener(new DeleteButtonListener());
        deleteButton.setEnabled(false); // Initially disabled

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(70, 130, 180));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(150, 40));
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);

        // Add panels to frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true); // Show the frame
    }

    private class FetchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String bookID = bookIDField.getText().trim();

            if (bookID.isEmpty()) {
                JOptionPane.showMessageDialog(DeleteBook.this, "Book ID is required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            bookToDelete = bookService.searchBookByIsbn(bookID);

            if (bookToDelete != null) {
                titleLabel.setText("Title: " + bookToDelete.getTitle());
                authorLabel.setText("Author: " + bookToDelete.getAuthor());
                publisherLabel.setText("Publisher: " + bookToDelete.getPublisher());
                yearLabel.setText("Year: " + bookToDelete.getPublisherYear());
                deleteButton.setEnabled(true); // Enable delete button
            } else {
                JOptionPane.showMessageDialog(DeleteBook.this, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
                deleteButton.setEnabled(false); // Disable delete button
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (bookToDelete != null) {
                int option = JOptionPane.showConfirmDialog(DeleteBook.this,
                        "Are you sure you want to delete this book?",
                        "Delete Book",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                    bookService.removeBook(bookToDelete);
                    JOptionPane.showMessageDialog(DeleteBook.this, "Book deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(DeleteBook.this, "No book selected for deletion.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
