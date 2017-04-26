package mongo.real;

import org.bson.*;
import com.mongodb.client.*;
import mongo.manager.*;

public class MinPathCollection {
	
	private MongoHandler mh;
	
	public MinPathCollection(String dbName) {
		mh = MongoHandler.getInstance(dbName);
	}

	public MongoHandler getMh() {
		return mh;
	}
	
	public MongoCollection<Document> getCollection(String collName){
		return mh.getMd().getCollection(collName);
	}
	
	public void addDocument(String collName, Document d){
		this.getCollection(collName).insertOne(d);
	}
	
}
