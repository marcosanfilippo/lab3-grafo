package mongo.real;

import maps.interfaces.*;
import mongo.manager.*;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;

public class MinPathCollection {
	
	private MongoHandler mh;
	
	/**
	 * REMEBER: Call this, to create a database with name as @param.
	 * Then, use the name ALWAYS, even to retrieve documents.
	 * Remember to have the same behaviour for collections,
	 * if not exist, they will be created.
	 * @param dbName
	 */
	public MinPathCollection(String dbName) {
		mh = MongoHandler.getInstance(dbName);
	}
	
	/**
	 * REMEBER: The @param used here will create an empty collection if doesn't exist!
	 * Then, use the name ALWAYS, even to retrieve documents.
	 * @param collName
	 */
	public MongoCollection<Document> getCollection(String collName){
		return mh.getMongoDB().getCollection(collName);
	}
	
	/**
	 * REMEBER: The @param used here will create an empty collection if doesn't exist!
	 * Then, use the name ALWAYS, even to retrieve documents.
	 * @param collName
	 */
	public void addDocument(String collName, Document d){
		mh.getMongoDB().getCollection(collName).insertOne(d);
	}
	
	/**
	 * REMEBER: The @param used here will create an empty collection if doesn't exist!
	 * Then, use the name ALWAYS, even to retrieve documents.
	 * @param collName
	 */
	public Document getDocument(String collName, BusStop sourceID, BusStop destinationID){
		MongoCollection<Document> collection = mh.getMongoDB().getCollection(collName);
		MongoCursor<Document> cursor = 
				collection.find(and(eq("sourceID", sourceID.getId()), 
						eq("destinationID", destinationID.getId()))).iterator();
		Document d = null;
		try {
		    while (cursor.hasNext()) {
		    	d = cursor.next();
		    	break; //we expect one path only!
		    }
		} finally {
		    cursor.close();
		}
		return d;
	}

}
