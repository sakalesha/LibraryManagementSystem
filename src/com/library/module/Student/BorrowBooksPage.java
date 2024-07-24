package com.library.module.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorrowBooksPage extends JDialog {

    private JTable booksTable;
    private JScrollPane scrollPane;
    private JButton borrowButton, cancelButton;

    public BorrowBooksPage(Frame owner) {
        super(owner, "Borrow Books", true);

        // Sample data for demonstration
        String[] columnNames = {"Book ID", "Title", "Author", "Publisher", "Year"};
        Object[][] data = {
            {"1", "Java Programming", "John Doe", "Tech Publishers", "2023"},
            {"2", "Data Structures", "Jane Smith", "Edu Books", "2022"},
            {"3", "Algorithms", "Alice Johnson", "Science Press", "2021"}
        };

        // Create table and scroll pane
        booksTable = new JTable(data, columnNames);
        booksTable.setFont(new Font("Arial", Font.PLAIN, 14));
        booksTable.setRowHeight(30);
        booksTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(booksTable);

        // Set up the dialog
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Footer Panel with buttons
        JPanel footerPanel = new JPanel();
        borrowButton = new JButton("Borrow");
        cancelButton = new JButton("Cancel");

        borrowButton.setFont(new Font("Arial", Font.BOLD, 14));
        borrowButton.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        borrowButton.setForeground(Color.WHITE);

        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(108, 117, 125)); // Grayish color
        cancelButton.setForeground(Color.WHITE);

        footerPanel.add(borrowButton);
        footerPanel.add(cancelButton);
        add(footerPanel, BorderLayout.SOUTH);

        // Set dialog properties
        setSize(500, 300);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        // Add action listeners
        borrowButton.addActionListener(new BorrowButtonListener());
        cancelButton.addActionListener(e -> dispose());
    }

    private class BorrowButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = booksTable.getSelectedRow();
            if (selectedRow >= 0) {
                String bookID = booksTable.getValueAt(selectedRow, 0).toString();
                // Perform the borrow book operation here
                JOptionPane.showMessageDialog(null, "Book with ID " + bookID + " borrowed.");
                dispose(); // Close the dialog
            } else {
                JOptionPane.showMessageDialog(null, "Please select a book to borrow.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BorrowBooksPage(null));
    }
}
