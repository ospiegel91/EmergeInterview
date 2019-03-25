package models;

import com.mongodb.*;
import java.util.Arrays;


public class ContinuousSound extends Sound {
	
	private Long mEndTime;
	MongoClient mMongoClient;
	DB mDB;
	DBCollection mContinuousCollection;
	
	public void connectToDataBase() {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		System.out.println(mDB);
		mContinuousCollection = mDB.getCollection("sporadic");
		DBCursor cursor = mContinuousCollection.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			System.out.println("****** Document is: ");
			System.out.println(obj);
		}
	}
	
	public Long getEndTime() {
		return mEndTime;
	}
	
	public void setEndTime(Long newTime) {
		mEndTime = newTime;
	}
	


}
