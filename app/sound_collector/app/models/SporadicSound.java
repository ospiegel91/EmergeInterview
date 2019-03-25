package models;

import com.mongodb.*;
import java.util.Arrays;

import org.bson.Document;

public class SporadicSound extends Sound {
	

	public SporadicSound() {
		
	}
	
	public void insertDocToSporadicCollection() {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		System.out.println(mDB);
		mContinuousCollection = mDB.getCollection("sporadic");
		
		DBObject document =  new BasicDBObject();
		document.put("type", type);
		document.put("startTime", startTime);
		document.put("senderIP", senderIP);
		mContinuousCollection.insert(document);
		
		DBCursor cursor = mContinuousCollection.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			System.out.println("****** Document is: ");
			System.out.println(obj);
		}
	}
	
}
