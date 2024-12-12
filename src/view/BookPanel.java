package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controller.AppController;

public class BookPanel extends JPanel {
    public AppController appController;
    public JTable bookTable;
    public JTextField idTextField;
    public JTextField titleTextField;
    public JTextField priceTextField;
    public int row = -1;
    public JTextField findBookTextField;
    public DefaultTableModel defaultTableModel;
    public TableRowSorter<DefaultTableModel> sorter;

    public BookPanel(AppController appController) {
        this.appController = appController;

        setLayout(new BorderLayout(10, 10));

        JPanel leftPanel = new JPanel(new BorderLayout(10, 10)); 
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10)); 
        
        //Search
        JPanel searchPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        findBookTextField = new JTextField();
        findBookTextField.setPreferredSize(new Dimension(250, 30));
        searchPanel.add(new JLabel("Search Book"));
        searchPanel.add(findBookTextField);
        leftPanel.add(searchPanel, BorderLayout.NORTH);

        // Input
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 50));
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

        // Button
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this.appController);
        buttonPanel.add(addButton);
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(this.appController);
        buttonPanel.add(updateButton);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this.appController);
        buttonPanel.add(deleteButton);
        JButton lendButton = new JButton("Lend");
        lendButton.addActionListener(appController);
        buttonPanel.add(lendButton);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Table
        String[][] data = {};
        String[] columnNames = {"s.No", "ID", "Title", "Price","isBorrowed"};
        defaultTableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 4 ? Boolean.class : String.class;
            }
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        bookTable = new JTable();
        bookTable.setModel(defaultTableModel);
        bookTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        bookTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        bookTable.getColumnModel().getColumn(2).setPreferredWidth(300);
        bookTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        bookTable.getColumnModel().getColumn(4).setPreferredWidth(10);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // Table search 
        sorter = new TableRowSorter<>(defaultTableModel);
        bookTable.setRowSorter(sorter);
        findBookTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String search = findBookTextField.getText().trim();
                if (search.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + search, 2));
                }
            }
        });

        
        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        add(jSplitPane, BorderLayout.CENTER);

        //Update row
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


