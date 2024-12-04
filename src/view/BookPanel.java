package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.AppController;

public class BookPanel extends JPanel {
    public AppController appController;
    public JTable bookTable;
    public JTextField idTextField;
    public JTextField titleTextField;
    public JTextField priceTextField;
    public int row = -1;
    public JTextField findBookTextField;

    public BookPanel(AppController appController) {
        this.appController = appController;

        setLayout(new BorderLayout(10, 10));

        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.setPreferredSize(new Dimension(300, 400));

        
        JPanel searchPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        findBookTextField = new JTextField();
        findBookTextField.setPreferredSize(new Dimension(250, 30));
        searchPanel.add(new JLabel("Search Book:"));
        searchPanel.add(findBookTextField);
        leftPanel.add(searchPanel, BorderLayout.NORTH);

       
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.add(new JLabel("ID"));
        idTextField = new JTextField();
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Title"));
        titleTextField = new JTextField();
        inputPanel.add(titleTextField);
        inputPanel.add(new JLabel("Price"));
        priceTextField = new JTextField();
        inputPanel.add(priceTextField);
        leftPanel.add(inputPanel, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(this.appController);
        buttonPanel.add(addButton);
        JButton updateButton = new JButton("Update Book");
        updateButton.addActionListener(this.appController);
        buttonPanel.add(updateButton);
        JButton deleteButton = new JButton("Delete Book");
        deleteButton.addActionListener(this.appController);
        buttonPanel.add(deleteButton);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);

        
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));

        String[][] data = {};
        String[] columnNames = {"s.No", "ID", "Title", "Price"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(data, columnNames);
        bookTable = new JTable(defaultTableModel);
        bookTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        bookTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        bookTable.getColumnModel().getColumn(2).setPreferredWidth(250);
        bookTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(rightPanel, BorderLayout.CENTER);

        
        bookTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = bookTable.getSelectedRow();
                    if (selectedRow != -1) {
                        row = selectedRow;
                        idTextField.setText(bookTable.getValueAt(selectedRow, 1).toString());
                        titleTextField.setText(bookTable.getValueAt(selectedRow, 2).toString());
                        priceTextField.setText(bookTable.getValueAt(selectedRow, 3).toString());
                    }
                }
            }
        });

       
    }

    public AppController getAppController() {
        return appController;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
