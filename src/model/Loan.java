package model;

import java.util.Date;

public class Loan {
	private Object id;
	private String name;
	private String phoneNumber;
	private String bookId;
	private Date borrowedDate;
	private Date returnDate;
	
	public Loan(String id, String name, String phoneNumber, String bookId, Date borrowedDate, Date returnDate) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.bookId = bookId;
		this.borrowedDate = borrowedDate;
		this.returnDate = returnDate;
	}
	
	public Loan() {
		super();
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getBorrowedDate() {
		return borrowedDate;
	}

	public void setBorrowedDate(java.util.Date date) {
		this.borrowedDate = date;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", bookId=" + bookId
				+ ", borrowedDate=" + borrowedDate + ", returnDate=" + returnDate + "]";
	}
	
	
	
}
