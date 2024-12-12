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
		case "Add": {
			addBookManagement();
			updateData();
			break;
		}
		case "Delete":{
			deleteBookManagement();
			updateData();
			break;
		}
		case "Update":{
			updateBookManagement();
			updateData();
			break;
		}
		case "Lend":{
			lendBookManagement();
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + src);
		}
	}
	
	private void lendBookManagement() {
		if(this.appView.bookPanel.row==-1) {
			this.appView.showNotSelected();
		}else {
			
			this.appView.tabbedPane.setSelectedIndex(1);
			String id = this.appView.bookPanel.idTextField.getText();
			resetData();
			this.appView.borrowerPanel.idTextField.setText(id);
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
		if(this.appView.showDeleteConform()==1) {
			String id = this.appView.bookPanel.bookTable.getValueAt(this.appView.bookPanel.row, 1).toString();
			bookDAO.deleteBook(id);
			resetData();
		}
		
	}

	private void resetData() {
		this.appView.bookPanel.idTextField.setText("");
		this.appView.bookPanel.titleTextField.setText("");
		this.appView.bookPanel.priceTextField.setText("");
		this.appView.bookPanel.bookTable.clearSelection();
		this.appView.bookPanel.row=-1;
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
		this.appView.bookPanel.defaultTableModel.setRowCount(books.size());
		for(int i=0;i<books.size();i++) {
			this.appView.bookPanel.bookTable.setValueAt(i+1, i, 0);
			this.appView.bookPanel.bookTable.setValueAt(books.get(i).getId(), i, 1);
			this.appView.bookPanel.bookTable.setValueAt(books.get(i).getTitle(), i, 2);
			this.appView.bookPanel.bookTable.setValueAt(books.get(i).getPrice(), i, 3);
			this.appView.bookPanel.bookTable.setValueAt(books.get(i).isBorowed(), i, 4);
		}
	}
    
}
