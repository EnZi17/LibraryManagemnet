package controller;

import model.Book;
import model.BookDAO;
import model.Loan;
import model.LoanDAO;
import view.AppView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import myUtil.Util;


public class BorrowerController implements ActionListener {
	public ArrayList<Loan> loans;
    private BookDAO bookDAO;
    private AppView appView;
    private LoanDAO loanDAO;

    public BorrowerController(AppView appView) {
        this.appView = appView;
        this.loanDAO = new LoanDAO();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		switch (src) {
		case "Add": {
			addManagement();
			updateData();
			break;
		}
		case "Delete":{
			deleteManagement();
			updateData();
			break;
		}
		case "Update":{
			updateManagement();
			updateData();
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + src);
		}
	}
	
	private void updateManagement() {
		if(this.appView.borrowerPanel.row==-1) {
			this.appView.showNotSelected();
		}else {
			String id = this.appView.bookPanel.bookTable.getValueAt(this.appView.bookPanel.row, 1).toString();
			bookDAO.deleteBook(id);
			addManagement();
			
		}
	}
	

	private void deleteManagement() {
		if(this.appView.showDeleteConform()==1) {
			String id = this.appView.borrowerPanel.table.getValueAt(this.appView.borrowerPanel.row, 1).toString();
			String bookId = loanDAO.findBookIDByID(id);
			loanDAO.deleteLoan(id);
			bookDAO = new BookDAO();
			bookDAO.setborrowed(bookId, false);
			resetInput();
			appView.appController.updateData();
		}
		
	}

	private void addManagement() {
		String name = appView.borrowerPanel.nameTextField.getText();
		String phone = appView.borrowerPanel.phoneTextField.getText();
		String bookId = appView.borrowerPanel.idTextField.getText();
		if(name.equals("")||phone.equals("")||bookId.equals("")) {
			this.appView.showEmpty();
		}else {
			bookDAO = new BookDAO();
			if(!bookDAO.findById(bookId).isNull()) {
				if(bookDAO.findById(bookId).isBorowed()) {
					this.appView.showBookHasBeenBorrowed();
				}else {
					loanDAO = new LoanDAO();
					String borrowedDay=appView.borrowerPanel.borrowPanel.dayComboBox.getSelectedItem().toString();
					if(appView.borrowerPanel.borrowPanel.dayComboBox.getSelectedIndex()+1<10) {
						borrowedDay="0"+borrowedDay;
					}
					String borrowedMonth=String.valueOf(appView.borrowerPanel.borrowPanel.monthComboBox.getSelectedIndex()+1);
					if(appView.borrowerPanel.borrowPanel.monthComboBox.getSelectedIndex()+1<10) {
						borrowedMonth="0"+borrowedMonth;
					}
					String borrowedYear=appView.borrowerPanel.borrowPanel.yearComboBox.getSelectedItem().toString();
					String returnDay=appView.borrowerPanel.returnPanel.dayComboBox.getSelectedItem().toString();
					if(appView.borrowerPanel.returnPanel.dayComboBox.getSelectedIndex()+1<10) {
						returnDay="0"+returnDay;
					}
					String returnMonth=String.valueOf(appView.borrowerPanel.returnPanel.monthComboBox.getSelectedIndex()+1);
					if(appView.borrowerPanel.returnPanel.monthComboBox.getSelectedIndex()+1<10) {
						returnMonth="0"+returnMonth;
					}
					String returnYear=appView.borrowerPanel.returnPanel.yearComboBox.getSelectedItem().toString();
					
					
					String borowedDate = borrowedDay+"-"+borrowedMonth+"-"+borrowedYear;
					String returnDate = returnDay+"-"+returnMonth+"-"+returnYear;
					
					loanDAO.addLoan(new Loan(name,phone,bookId,myUtil.Util.StringToDate(borowedDate),myUtil.Util.StringToDate(returnDate)));
					
					
					bookDAO.setborrowed(bookId,true);
					appView.appController.updateData();
					
					resetInput();
				}
				
				
				
			}else {
				appView.showBookNotExist();
			}
		}
		
		this.appView.bookPanel.idTextField.setText("");
		this.appView.bookPanel.titleTextField.setText("");
		this.appView.bookPanel.priceTextField.setText("");
		updateData();
		
	}

	public void updateData() {
		loans= loanDAO.getLoans();
		this.appView.borrowerPanel.defaultTableModel.setRowCount(loans.size());
		for(int i=0;i<loans.size();i++) {
			this.appView.borrowerPanel.table.setValueAt(i+1, i, 0);
			this.appView.borrowerPanel.table.setValueAt(loans.get(i).getId(), i, 1);
			this.appView.borrowerPanel.table.setValueAt(loans.get(i).getName(), i, 2);
			this.appView.borrowerPanel.table.setValueAt(loans.get(i).getPhoneNumber(), i, 3);
			this.appView.borrowerPanel.table.setValueAt(loans.get(i).getBookId(), i, 4);
			this.appView.borrowerPanel.table.setValueAt(myUtil.Util.DateTotring(loans.get(i).getBorrowedDate()), i, 5);
			this.appView.borrowerPanel.table.setValueAt(myUtil.Util.DateTotring(loans.get(i).getReturnDate()), i, 6);
			
		}
	}
	
	public void resetInput() {
		this.appView.borrowerPanel.nameTextField.setText("");
		this.appView.borrowerPanel.phoneTextField.setText("");
		this.appView.borrowerPanel.idTextField.setText("");
		Calendar calendar = Calendar.getInstance();
		this.appView.borrowerPanel.borrowPanel.dayComboBox.setSelectedIndex(calendar.getTime().getDate()-1);
		this.appView.borrowerPanel.borrowPanel.monthComboBox.setSelectedIndex(calendar.getTime().getMonth());
		this.appView.borrowerPanel.borrowPanel.yearComboBox.setSelectedIndex(calendar.getTime().getYear()+1901);
		calendar.add(Calendar.DAY_OF_MONTH,14);
		this.appView.borrowerPanel.returnPanel.dayComboBox.setSelectedIndex(calendar.getTime().getDate()-1);
		this.appView.borrowerPanel.returnPanel.monthComboBox.setSelectedIndex(calendar.getTime().getMonth());
		this.appView.borrowerPanel.returnPanel.yearComboBox.setSelectedIndex(calendar.getTime().getYear()+1901);
		
	}
    
}
