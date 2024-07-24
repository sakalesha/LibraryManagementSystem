package com.library.module.Admin;

import com.library.service.AdminService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateReports extends JFrame {

    private JComboBox<String> reportTypeComboBox;
    private JTextField startDateField, endDateField;
    private JButton generateButton, homeButton;
    private JTable reportTable;
    private DefaultTableModel tableModel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public GenerateReports() {
        setTitle("Generate Reports");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20)); // Add padding to the layout

        // Set look and feel to Nimbus for a modern UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Top panel for inputs
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Report Parameters",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16), new Color(70, 130, 180)));

        JLabel reportTypeLabel = new JLabel("Select Report Type:");
        String[] reportTypes = {"Books Issued", "Books Returned", "Overdue Books", "Fines Collected", "Monthly Summary", "Yearly Summary"};
        reportTypeComboBox = new JComboBox<>(reportTypes);

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateField = new JTextField();

        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateField = new JTextField();

        generateButton = new JButton("Generate Report");
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateButton.setBackground(new Color(70, 130, 180));
        generateButton.setForeground(Color.WHITE);
        generateButton.addActionListener(new GenerateButtonListener());

        inputPanel.add(reportTypeLabel);
        inputPanel.add(reportTypeComboBox);
        inputPanel.add(startDateLabel);
        inputPanel.add(startDateField);
        inputPanel.add(endDateLabel);
        inputPanel.add(endDateField);
        inputPanel.add(generateButton);

        add(inputPanel, BorderLayout.NORTH);

        // Table panel for displaying reports
        tableModel = new DefaultTableModel();
        reportTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);
        add(scrollPane, BorderLayout.CENTER);

        // Home button
        homeButton = new JButton("Return to Home Page");
        homeButton.setFont(new Font("Arial", Font.BOLD, 14));
        homeButton.setBackground(new Color(70, 130, 180));
        homeButton.setForeground(Color.WHITE);
        homeButton.addActionListener(new HomeButtonListener());
        JPanel homePanel = new JPanel();
        homePanel.add(homeButton);
        add(homePanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class GenerateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String reportType = (String) reportTypeComboBox.getSelectedItem();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();

            if (!isValidDate(startDate) || !isValidDate(endDate)) {
                JOptionPane.showMessageDialog(GenerateReports.this, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            switch (reportType) {
                case "Books Issued":
                    generateBooksIssuedReport(startDate, endDate);
                    break;
                case "Books Returned":
                    generateBooksReturnedReport(startDate, endDate);
                    break;
                case "Overdue Books":
                    generateOverdueBooksReport(startDate, endDate);
                    break;
                case "Fines Collected":
                    generateFinesCollectedReport(startDate, endDate);
                    break;
                case "Monthly Summary":
                    generateMonthlySummaryReport(startDate, endDate);
                    break;
                case "Yearly Summary":
                    generateYearlySummaryReport(startDate, endDate);
                    break;
            }
        }
    }

    private boolean isValidDate(String dateStr) {
        try {
            dateFormat.setLenient(false); // Disable lenient parsing
            Date date = dateFormat.parse(dateStr);
            return date != null;
        } catch (ParseException e) {
            return false;
        }
    }

    private void generateBooksIssuedReport(String startDate, String endDate) {
        String[] columnNames = {"Book ID", "Title", "Issued To", "Issue Date"};
        Object[][] data = {
                {"1", "Book One", "User A", "2023-07-01"},
                {"2", "Book Two", "User B", "2023-07-02"},
        };

        setTableData(columnNames, data);
    }

    private void generateBooksReturnedReport(String startDate, String endDate) {
        String[] columnNames = {"Book ID", "Title", "Returned By", "Return Date"};
        Object[][] data = {
                {"1", "Book One", "User A", "2023-07-10"},
                {"2", "Book Two", "User B", "2023-07-12"},
        };

        setTableData(columnNames, data);
    }

    private void generateOverdueBooksReport(String startDate, String endDate) {
        String[] columnNames = {"Book ID", "Title", "Issued To", "Due Date", "Days Overdue"};
        Object[][] data = {
                {"1", "Book One", "User A", "2023-06-30", "10"},
                {"2", "Book Two", "User B", "2023-06-28", "12"},
        };

        setTableData(columnNames, data);
    }

    private void generateFinesCollectedReport(String startDate, String endDate) {
        String[] columnNames = {"User ID", "User Name", "Fine Amount (₹)", "Payment Date"};
        Object[][] data = {
                {"1", "User A", "₹800.00", "2023-07-05"},
                {"2", "User B", "₹1200.00", "2023-07-06"},
        };

        setTableData(columnNames, data);
    }

    private void generateMonthlySummaryReport(String startDate, String endDate) {
        String[] columnNames = {"Month", "Books Issued", "Books Returned", "Overdue Books", "Fines Collected (₹)"};
        Object[][] data = {
                {"July", "20", "18", "2", "₹4000.00"},
        };

        setTableData(columnNames, data);
    }

    private void generateYearlySummaryReport(String startDate, String endDate) {
        String[] columnNames = {"Year", "Books Issued", "Books Returned", "Overdue Books", "Fines Collected (₹)"};
        Object[][] data = {
                {"2023", "200", "180", "20", "₹40000.00"},
        };

        setTableData(columnNames, data);
    }

    private void setTableData(String[] columnNames, Object[][] data) {
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.setDataVector(data, columnNames);
    }

    private class HomeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            SwingUtilities.invokeLater(AdminService::new);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GenerateReports::new);
    }
}
