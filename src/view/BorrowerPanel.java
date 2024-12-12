package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
import controller.BorrowerController;

public class BorrowerPanel extends JPanel {
    public BorrowerController appController;
    
    public JTextField nameTextField;
    public JTextField phoneTextField;
    public JTextField idTextField;
    public int row = -1;
    public JTextField findTextField;
	public JTable table;
	public DefaultTableModel defaultTableModel;
	public DatePanel borrowPanel;
	public DatePanel returnPanel;
	public JRadioButton lateFilter;
	public TableRowSorter<DefaultTableModel> sorter ;

    public BorrowerPanel(BorrowerController appController) {
        this.appController = appController;

        setLayout(new BorderLayout(10, 10));

        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        
        //Search
        JPanel searchPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        findTextField = new JTextField();
        findTextField.setPreferredSize(new Dimension(250, 30));
        searchPanel.add(new JLabel("Search Borower"));
        searchPanel.add(findTextField);
        leftPanel.add(searchPanel, BorderLayout.NORTH);

       //Input
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.add(new JLabel("Name"));
        nameTextField = new JTextField();
        inputPanel.add(nameTextField);
        inputPanel.add(new JLabel("Phone Number"));
        phoneTextField = new JTextField();
        inputPanel.add(phoneTextField);
        inputPanel.add(new JLabel("Book ID"));
        idTextField = new JTextField();
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Borrowed Date"));
        borrowPanel = new DatePanel();
        inputPanel.add(borrowPanel);
        inputPanel.add(new JLabel("Return Date"));
        returnPanel = new DatePanel();
        inputPanel.add(returnPanel);
        leftPanel.add(inputPanel, BorderLayout.CENTER);

        //Button
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
        lateFilter = new JRadioButton("Late Return Filter");
        lateFilter.addActionListener(this.appController);
        buttonPanel.add(lateFilter);
       
       
        
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        

        
        //Table
        table = new JTable();
        
        String[][] data = {};
        String[] columnNames = {"s.No","ID", "Name", "Phone Number", "Book Id","Borrowed Date","Return Date"};
        
        defaultTableModel = new DefaultTableModel(data,columnNames);
        
		table= new JTable();
		table.setModel(defaultTableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftPanel,rightPanel);
        
        add(splitPane,BorderLayout.CENTER);
		
		
			//Table Search
		sorter = new TableRowSorter<>(defaultTableModel);
		table.setRowSorter(sorter);
		
		findTextField.addKeyListener(new KeyAdapter() {
			
	    	  public void keyReleased(KeyEvent e) {
	    		  String search=findTextField.getText().trim();
		    	  if(search.isEmpty()) {
		    		  sorter.setRowFilter(null);
		    	  }else {
		    		  sorter.setRowFilter(RowFilter.regexFilter("(?i)"+search, 2));
		    	  }
	            }
		});
        
        
        

        //Update Text Field
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        row = selectedRow;
                        nameTextField.setText(table.getValueAt(selectedRow, 2).toString());
                        phoneTextField.setText(table.getValueAt(selectedRow, 3).toString());
                        idTextField.setText(table.getValueAt(selectedRow, 4).toString());
                        borrowPanel.dayComboBox.setSelectedIndex(myUtil.Util.StringToDay(table.getValueAt(selectedRow, 5).toString())-1);
                        borrowPanel.monthComboBox.setSelectedIndex(myUtil.Util.StringToMonth(table.getValueAt(selectedRow, 5).toString())-1);
                        borrowPanel.yearComboBox.setSelectedIndex(myUtil.Util.StringToYear(table.getValueAt(selectedRow, 5).toString())-1);
                        returnPanel.dayComboBox.setSelectedIndex(myUtil.Util.StringToDay(table.getValueAt(selectedRow, 6).toString())-1);
                        returnPanel.monthComboBox.setSelectedIndex(myUtil.Util.StringToMonth(table.getValueAt(selectedRow, 6).toString())-1);
                        returnPanel.yearComboBox.setSelectedIndex(myUtil.Util.StringToYear(table.getValueAt(selectedRow, 6).toString())-1);
                        
                    }
                }
            }
        });

       
    }

       
    

    public BorrowerController getAppController() {
        return appController;
    }

    public void setAppController(BorrowerController appController) {
        this.appController = appController;
    }
    
    
}
