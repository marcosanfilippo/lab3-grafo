package mongo.manager;

import com.mongodb.*;
import com.mongodb.client.*;

public class MongoHandler {
	
	private static MongoHandler instance = null;
	
	private MongoClient mc;
	private MongoDatabase md;
	
	public static MongoHandler getInstance(String db){
		if(instance == null){
			instance = new MongoHandler(db);
		}
		return instance;
	}
	
	private MongoHandler(String db){
		mc = new MongoClient("localhost", 27017);
		md = mc.getDatabase(db);
	}

	public MongoDatabase getMongoDB() {
		return md;
	}
	
}
