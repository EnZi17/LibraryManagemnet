package model;

import org.bson.Document;
import com.mongodb.client.MongoCollection;

import myUtil.MongoDBConnection;
import java.lang.invoke.StringConcatFactory;
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
                    .append("price", book.getPrice())
                    .append("isBorrowed", book.isBorowed());
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
            book.setBorowed(doc.getBoolean("isBorrowed", false));
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
            book.setBorowed(doc.getBoolean("isBorrowed"));
    	}
    	return book;
    }
    
    public void setborrowed(String id ,boolean t) {
    	Document query = new Document("id", id);
    	Document update = new Document("$set", new Document("isBorrowed",t));
    	collection.updateMany(query, update);
    }
   
}
