package models;

import com.mongodb.*;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;


public class ContinuousSound extends Sound {
	
	private Long mEndTime;
	public Boolean mIsConsequtiveEvent;

	
	public void insertDocToContinuousCollection() {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		mContinuousCollection = mDB.getCollection("continuous");

		DBObject document =  new BasicDBObject();
		document.put("type", type);
		document.put("startTime", startTime);
		document.put("endTime", mEndTime);
		document.put("senderIP", senderIP);
		mContinuousCollection.insert(document);
		
	}
	
	public Long getCountOfContinuousSounds(BasicDBObject query) {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		
		mContinuousCollection = mDB.getCollection("continuous");

		Long count = mContinuousCollection.getCount(query);
		System.out.println("----The count is :"+count);
		return count;
	}
	
	public List<DBObject> getContinuousSounds(BasicDBObject query) {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		mContinuousCollection = mDB.getCollection("continuous");
		List<DBObject> obj = mContinuousCollection.find(query).toArray();
		return obj;
	}
	
	public Long getEndTime() {
		return mEndTime;
	}
	
	public void setEndTime(Long newTime) {
		mEndTime = newTime;
	}

	public void checkIfContinuousIsConsequtive(BasicDBObject query) {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		
		mContinuousCollection = mDB.getCollection("continuous");

		Long count = mContinuousCollection.getCount(query);
		System.out.println("----The count in CONSEQUTIVE CHECK** is :"+count);
		if (count>0) {
			System.out.println("going to set mISConseq to true");
			mIsConsequtiveEvent = true;
		}else {
			System.out.println("going to set mISConseq to false");
			mIsConsequtiveEvent = false;
		}
		
		
	}

	public void updateContinuousSound(BasicDBObject query, BasicDBObject update) {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		
		mContinuousCollection = mDB.getCollection("continuous");
		mContinuousCollection.update(query, update);
		
		
		DBCursor cursor = mContinuousCollection.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			System.out.println("****** Document POST UPDATE is: ");
			System.out.println(obj);
		}
		
	}
	
	
	


}
