package controller;

import model.Book;
import model.BookDAO;
import view.AppView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class AppController implements ActionListener {
	public ArrayList<Book> books;
    private BookDAO bookDAO;
    private AppView appView;

    public AppController(AppView appView) {
        this.appView = appView;
        this.bookDAO = new BookDAO();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		switch (src) {
		case "Add Book": {
			addBookManagement();
			updateData();
			break;
		}
		case "Delete Book":{
			deleteBookManagement();
			updateData();
			break;
		}
		case "Update Book":{
			updateBookManagement();
			updateData();
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + src);
		}
	}
	
	private void updateBookManagement() {
		if(this.appView.bookPanel.row==-1) {
			this.appView.showNotSelected();
		}else {
			String id = this.appView.bookPanel.bookTable.getValueAt(this.appView.bookPanel.row, 1).toString();
			bookDAO.deleteBook(id);
			addBookManagement();
			
		}
	}

	private void deleteBookManagement() {
		if(this.appView.showDeleteBookConform()==1) {
			String id = this.appView.bookPanel.bookTable.getValueAt(this.appView.bookPanel.row, 1).toString();
			bookDAO.deleteBook(id);
			this.appView.bookPanel.idTextField.setText("");
			this.appView.bookPanel.titleTextField.setText("");
			this.appView.bookPanel.priceTextField.setText("");
		}
		
	}

	private void addBookManagement() {
		String id = this.appView.bookPanel.idTextField.getText();
		String title = this.appView.bookPanel.titleTextField.getText();
		if(id.equals("")||title.equals("")) {
			this.appView.showEmpty();
		}else {
			Double price =0.0;
			try {
				price = Double.parseDouble(this.appView.bookPanel.priceTextField.getText());
				
				if(bookDAO.addBook(new Book(id,title,price))==0) {
					this.appView.showIdExitsted();
					this.appView.bookPanel.idTextField.setText("");
					this.appView.bookPanel.titleTextField.setText("");
					this.appView.bookPanel.priceTextField.setText("");
				}
			} catch (Exception e) {
				this.appView.showInvalidPrice();
				this.appView.bookPanel.priceTextField.setText("");
			}
		}
		
		this.appView.bookPanel.idTextField.setText("");
		this.appView.bookPanel.titleTextField.setText("");
		this.appView.bookPanel.priceTextField.setText("");
		
	}

	public void updateData() {
		
		books=bookDAO.getBooks();
		Object[][] data = new Object[books.size()][4];
		String[] columNames = {"s.No","ID","Title","Price"};
		for(int i=0;i<books.size();i++) {
			Book book = books.get(i);
			data[i][0]=i+1;
			data[i][1]=book.getId();
			data[i][2]=book.getTitle();
			data[i][3]=book.getPrice();
		}
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(data,columNames);
		
		this.appView.bookPanel.bookTable.setModel(defaultTableModel);
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(defaultTableModel);
		this.appView.bookPanel.bookTable.setRowSorter(sorter);
		
		this.appView.bookPanel.findBookTextField.addKeyListener(new KeyAdapter() {
			
	    	  public void keyReleased(KeyEvent e) {
	    		  String search=appView.bookPanel.findBookTextField.getText().trim();
		    	  if(search.isEmpty()) {
		    		  sorter.setRowFilter(null);
		    	  }else {
		    		  sorter.setRowFilter(RowFilter.regexFilter("(?i)"+search, 2));
		    		  
		    	  }
	            }
		});
	}
    
}
