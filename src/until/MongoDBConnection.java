package until;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    
    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            String uri = "mongodb+srv://enzi:enzi117@cluster0.uomnk.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("library"); 
        }
        return database;
    }
}

