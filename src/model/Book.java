package model;

public class Book {
	
	private String id;
	private String title;
	private double price;
	private boolean isBorowed;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isBorowed() {
		return isBorowed;
	}
	public void setBorowed(boolean isBorowed) {
		this.isBorowed = isBorowed;
	}
	public Book(String id, String title, double price) {
		super();
		this.isBorowed=false;
		this.id = id;
		this.title = title;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", price=" + price + ", isBorowed=" + isBorowed + "]";
	}
	public Book() {
		super();
	}
	public boolean isNull() {
		return this.id==null;
	}
	
	
}
