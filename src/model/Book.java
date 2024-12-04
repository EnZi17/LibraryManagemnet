package model;

public class Book {
	
	private String id;
	private String title;
	private double price;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", price=" + price + "]";
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
	
	public Book(String id, String title, double price) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
	}
	public Book() {
		super();
	}
	public boolean isNull() {
		return this.id==null;
	}
}
