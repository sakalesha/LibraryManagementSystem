package com.library.module.Student;

import javax.swing.*;
import java.awt.*;

public class ViewBooksPage extends JDialog {

    private JTable booksTable;
    private JScrollPane scrollPane;

    public ViewBooksPage(Frame owner) {
        super(owner, "View Books", true);

        // Sample data for demonstration
        String[] columnNames = {"Book ID", "Title", "Author", "Publisher", "Year"};
        Object[][] data = {
            {"1", "Java Programming", "John Doe", "Tech Publishers", "2023"},
            {"2", "Data Structures", "Jane Smith", "Edu Books", "2022"},
            {"3", "Algorithms", "Alice Johnson", "Science Press", "2021"}
        };

        booksTable = new JTable(data, columnNames);
        scrollPane = new JScrollPane(booksTable);

        add(scrollPane, BorderLayout.CENTER);

        setSize(500, 300);
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewBooksPage(null));
    }
}
