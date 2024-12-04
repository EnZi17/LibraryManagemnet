package model;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import until.MongoDBConnection;
import java.util.ArrayList;

public class BookDAO {
    private MongoCollection<Document> collection;
    
    public BookDAO() {
        collection = MongoDBConnection.getDatabase().getCollection("books");
        
    }

    public int addBook(Book book) {
    	BookDAO bookDAO = new BookDAO();
    	if(bookDAO.findById(book.getId()).isNull()) {
    		Document document = new Document("id", book.getId())
            		.append("title", book.getTitle())
                    .append("price", book.getPrice());
            collection.insertOne(document);
            return 1;
    	}else {
    		return 0;
    	}
        
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();
        for (Document doc : collection.find()) {
            Book book = new Book();
            book.setId(doc.getString("id"));
            book.setTitle(doc.getString("title"));
            book.setPrice(doc.getDouble("price"));
            books.add(book);
        }
        return books;
    }

    public void deleteBook(String id) {
        Document query = new Document("id", id);
        collection.deleteOne(query);
    }
    
    public Book findById(String id) {
    	Document query = new Document("id", id);
    	Document doc = collection.find(query).first();
    	Book book = new Book();
    	if(doc!=null) {
    		book.setId(doc.getString("id"));
            book.setTitle(doc.getString("title"));
            book.setPrice(doc.getDouble("price"));
    	}
    	return book;
    }
    public static void main(String[] args) {
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.findById("1");
		System.out.println(book.toString());
		ArrayList<Book> books = bookDAO.getBooks();
		Book book3 = new Book("a","a",1.0);
		System.out.println(bookDAO.addBook(book3));
		for(Book book1:books) {
			System.out.println(book1.toString());
		}
	}
}
