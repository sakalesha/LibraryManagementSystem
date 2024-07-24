package com.library.module.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.library.service.BookService;
import com.library.service.StudentDashboard;
import java.awt.*;

public class ViewBooksPage extends JDialog {
    private JTable booksTable;
    private DefaultTableModel tableModel;
    private BookService bookService;

    public ViewBooksPage(Frame owner, BookService bookService) {
        super(owner, "View Books", true);
        this.bookService = bookService;

        setTitle("View Books");
        setSize(800, 600);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Title Label
        JLabel titleLabel = new JLabel("Available Books", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"Book ID", "Title", "Author", "Publisher", "Year"};
        Object[][] data = bookService.getAllBooks().stream()
            .map(book -> new Object[]{book.getBookID(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublisherYear()})
            .toArray(Object[][]::new);

        tableModel = new DefaultTableModel(data, columnNames);
        booksTable = new JTable(tableModel);
        booksTable.setFont(new Font("Arial", Font.PLAIN, 18));
        booksTable.setRowHeight(30);
        booksTable.setFillsViewportHeight(true);
        booksTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        booksTable.getTableHeader().setOpaque(false);
        booksTable.getTableHeader().setBackground(new Color(70, 130, 180));
        booksTable.getTableHeader().setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < booksTable.getColumnCount(); i++) {
            booksTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(booksTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("Back to Home");
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));
        backButton.setBackground(new Color(100, 149, 237));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.addActionListener(e -> {
            dispose();
            // Assuming you want to return to the previous window
            ((StudentDashboard) getOwner()).setVisible(true);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
