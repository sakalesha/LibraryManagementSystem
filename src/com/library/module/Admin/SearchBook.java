package com.library.module.Admin;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.library.module.Book.Book;
import com.library.service.AdminService;
import com.library.service.BookService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBook extends JFrame {
    private JTextField searchField;
    private JButton searchButton, backButton;
    private JTextArea resultArea;

    public SearchBook() {
        setTitle("Search Book");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20)); // Add padding to the layout

        // Set look and feel to Nimbus for a modern UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Panel for search input and buttons
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Search Book",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16), new Color(255, 69, 58)));
        searchPanel.setBackground(Color.WHITE);

        JLabel searchLabel = new JLabel("Search by ID, Title, or Author:");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setBackground(new Color(70, 130, 180));
        searchButton.setForeground(Color.WHITE);
        searchButton.setPreferredSize(new Dimension(100, 40));
        searchButton.addActionListener(new SearchButtonListener());

        backButton = new JButton("Back to Home");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.addActionListener(e -> {
            dispose();
            new AdminService(); // Assuming AdminService is the homepage
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(backButton);

        // Text area for displaying search results
        resultArea = new JTextArea(15, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String query = searchField.getText().toLowerCase();
            StringBuilder results = new StringBuilder();

            for (Book book : BookService.getInstance().getAllBooks()) {
                if (book.getBookID().toLowerCase().contains(query) ||
                    book.getTitle().toLowerCase().contains(query) ||
                    book.getAuthor().toLowerCase().contains(query)) {
                    results.append("ID: ").append(book.getBookID()).append("\n")
                           .append("Title: ").append(book.getTitle()).append("\n")
                           .append("Author: ").append(book.getAuthor()).append("\n")
                           .append("Publisher: ").append(book.getPublisher()).append("\n")
                           .append("Year: ").append(book.getPublisherYear()).append("\n")
                           .append("-------------\n");
                }
            }

            if (results.length() == 0) {
                results.append("No books found matching the search criteria.");
            }

            resultArea.setText(results.toString());
        }
    }
}
