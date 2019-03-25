package models;

import com.mongodb.*;
import java.util.Arrays;

import org.bson.Document;


public class ContinuousSound extends Sound {
	
	private Long mEndTime;
	public Boolean mIsConsequtiveEvent;

	
	public void insertDocToContinuousCollection() {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		System.out.println(mDB);
		mContinuousCollection = mDB.getCollection("continuous");

		DBObject document =  new BasicDBObject();
		document.put("type", type);
		document.put("startTime", startTime);
		document.put("endTime", mEndTime);
		document.put("senderIP", senderIP);
		mContinuousCollection.insert(document);
		
		DBCursor cursor = mContinuousCollection.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			System.out.println("****** Document is: ");
			System.out.println(obj);
		}
	}
	
	public Long getCountOfContinuousSounds() {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		
		mContinuousCollection = mDB.getCollection("continuous");

		Long count = mContinuousCollection.getCount();
		System.out.println("----The count is :"+count);
		return count;
	}
	
	public Long getEndTime() {
		return mEndTime;
	}
	
	public void setEndTime(Long newTime) {
		mEndTime = newTime;
	}

	public void checkIfContinuousIsConsequtive() {
		// TODO Auto-generated method stub
		mIsConsequtiveEvent = false;
		
	}
	
	
	


}
