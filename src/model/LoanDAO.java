package model;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

import myUtil.MongoDBConnection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LoanDAO {
    private MongoCollection<Document> collection;
    
    public LoanDAO() {
        collection = MongoDBConnection.getDatabase().getCollection("loans");
        
    }

    public void addLoan(Loan loan) {
        Document document = new Document()
        		.append("name", loan.getName())
                .append("phoneNumber", loan.getPhoneNumber())
                .append("bookId", loan.getBookId())
                .append("borrowedDate", loan.getBorrowedDate())
                .append("returnDate", loan.getReturnDate());
        collection.insertOne(document);
    }

    public ArrayList<Loan> getLoans() {
        ArrayList<Loan> loans = new ArrayList<>();
        for (Document doc : collection.find()) {
            Loan loan = new Loan();
            loan.setId(doc.getObjectId("_id"));
            loan.setName(doc.getString("name"));
            loan.setPhoneNumber(doc.getString("phoneNumber"));
            loan.setBookId(doc.getString("bookId"));
            loan.setBorrowedDate(doc.getDate("borrowedDate"));
            loan.setReturnDate(doc.getDate("returnDate"));
            loans.add(loan);
        }
        return loans;
    }

    public void deleteLoan(String id) {
    	ObjectId objectId = new ObjectId(id);
        Document query = new Document("_id", objectId);
        collection.deleteOne(query);
    }
    
    public static void main(String[] args) {
    	Date date = new Date();
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 10);
        Date newDate = calendar.getTime();
		Loan loan1= new Loan("1","a","1","2",date,newDate);
		LoanDAO loanDAO = new LoanDAO();
		ArrayList<Loan> loans = loanDAO.getLoans();
		for(Loan loan:loans) {
			System.out.println(loan.toString());
		}
	}
    
    public String findBookIDByID(String id) {
    	ObjectId objectId = new ObjectId(id);
        Document query = new Document("_id", objectId);
        return collection.find(query).first().getString("bookId");
    }
    
}
