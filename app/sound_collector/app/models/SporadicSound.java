package models;

import com.mongodb.*;
import java.util.Arrays;
import java.util.List;

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
	
	public Long getCountOfSporadicSounds(BasicDBObject query) {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		
		mContinuousCollection = mDB.getCollection("sporadic");

		Long count = mContinuousCollection.getCount(query);
		System.out.println("----The count is :"+count);
		return count;
	}
	
	public List<DBObject> getSporadicSounds(BasicDBObject query) {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		mContinuousCollection = mDB.getCollection("sporadic");
		List<DBObject> obj = mContinuousCollection.find(query).toArray();
		return obj;
	}
	
}
