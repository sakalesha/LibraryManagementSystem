package com.library.module.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnBooksPage extends JDialog {

    private JTable borrowedBooksTable;
    private JScrollPane scrollPane;
    private JButton returnButton, cancelButton;

    public ReturnBooksPage(Frame owner) {
        super(owner, "Return Books", true);

        // Sample data for demonstration
        String[] columnNames = {"Book ID", "Title", "Author", "Borrower", "Borrow Date"};
        Object[][] data = {
            {"1", "Java Programming", "John Doe", "Student A", "2023-07-01"},
            {"2", "Data Structures", "Jane Smith", "Student B", "2023-06-15"},
            {"3", "Algorithms", "Alice Johnson", "Student C", "2023-05-20"}
        };

        // Create table and scroll pane
        borrowedBooksTable = new JTable(data, columnNames);
        borrowedBooksTable.setFont(new Font("Arial", Font.PLAIN, 14));
        borrowedBooksTable.setRowHeight(30);
        borrowedBooksTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(borrowedBooksTable);

        // Set up the dialog
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Footer Panel with buttons
        JPanel footerPanel = new JPanel();
        returnButton = new JButton("Return");
        cancelButton = new JButton("Cancel");

        returnButton.setFont(new Font("Arial", Font.BOLD, 14));
        returnButton.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        returnButton.setForeground(Color.WHITE);

        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(108, 117, 125)); // Grayish color
        cancelButton.setForeground(Color.WHITE);

        footerPanel.add(returnButton);
        footerPanel.add(cancelButton);
        add(footerPanel, BorderLayout.SOUTH);

        // Set dialog properties
        setSize(500, 300);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        // Add action listeners
        returnButton.addActionListener(new ReturnButtonListener());
        cancelButton.addActionListener(e -> dispose());
    }

    private class ReturnButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = borrowedBooksTable.getSelectedRow();
            if (selectedRow >= 0) {
                String bookID = borrowedBooksTable.getValueAt(selectedRow, 0).toString();
                // Perform the return book operation here
                JOptionPane.showMessageDialog(null, "Book with ID " + bookID + " returned.");
                dispose(); // Close the dialog
            } else {
                JOptionPane.showMessageDialog(null, "Please select a book to return.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReturnBooksPage(null));
    }
}
